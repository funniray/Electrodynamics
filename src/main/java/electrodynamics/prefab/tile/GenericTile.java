package electrodynamics.prefab.tile;

import java.util.ArrayList;
import java.util.List;

import electrodynamics.api.References;
import electrodynamics.api.capability.ElectrodynamicsCapabilities;
import electrodynamics.common.item.ItemUpgrade;
import electrodynamics.prefab.tile.components.Component;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.generic.AbstractFluidHandler;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.tile.components.type.ComponentName;
import electrodynamics.prefab.tile.components.type.ComponentPacketHandler;
import electrodynamics.prefab.tile.components.type.ComponentProcessor;
import electrodynamics.prefab.utilities.CapabilityUtils;
import electrodynamics.prefab.utilities.Scheduler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Nameable;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.TriPredicate;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class GenericTile extends BlockEntity implements Nameable {
	private Component[] components = new Component[ComponentType.values().length];
	private ComponentProcessor[] processors = new ComponentProcessor[5];

	public boolean hasComponent(ComponentType type) {
		return components[type.ordinal()] != null;
	}

	public <T extends Component> T getComponent(ComponentType type) {
		return !hasComponent(type) ? null : (T) components[type.ordinal()];
	}

	public ComponentProcessor getProcessor(int id) {
		return processors[id];
	}

	public ComponentProcessor[] getProcessors() {
		return processors;
	}

	public GenericTile addProcessor(ComponentProcessor processor) {
		for (int i = 0; i < processors.length; i++) {
			if (processors[i] == null) {
				processors[i] = processor;
				processor.holder(this);
				break;
			}
		}
		return this;
	}

	public GenericTile addComponent(Component component) {
		component.holder(this);
		if (hasComponent(component.getType())) {
			throw new ExceptionInInitializerError("Component of type: " + component.getType().name() + " already registered!");
		}
		components[component.getType().ordinal()] = component;
		return this;
	}

	@Deprecated(forRemoval = false, since = "Try not using this method.")
	public GenericTile forceComponent(Component component) {
		component.holder(this);
		components[component.getType().ordinal()] = component;
		return this;
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		for (Component component : components) {
			if (component != null) {
				component.holder(this);
				component.loadFromNBT(compound);
			}
		}
		for (ComponentProcessor pr : processors) {
			if (pr != null) {
				pr.holder(this);
				pr.loadFromNBT(compound);
			}
		}
	}

	@Override
	public void saveAdditional(CompoundTag compound) {
		for (Component component : components) {
			if (component != null) {
				component.holder(this);
				component.saveToNBT(compound);
			}
		}
		for (ComponentProcessor pr : processors) {
			if (pr != null) {
				pr.holder(this);
				pr.saveToNBT(compound);
			}
		}
		super.saveAdditional(compound);
	}

	protected GenericTile(BlockEntityType<?> tileEntityTypeIn, BlockPos worldPos, BlockState blockState) {
		super(tileEntityTypeIn, worldPos, blockState);
	}

	@Override
	public void onLoad() {
		super.onLoad();
		// JSON recipe fluids have to be added at load time
		if (hasComponent(ComponentType.FluidHandler)) {
			AbstractFluidHandler<?> tank = this.getComponent(ComponentType.FluidHandler);
			tank.addFluids();
		}
		if (hasComponent(ComponentType.PacketHandler)) {
			Scheduler.schedule(1, () -> {
				this.<ComponentPacketHandler>getComponent(ComponentType.PacketHandler).sendCustomPacket();
				this.<ComponentPacketHandler>getComponent(ComponentType.PacketHandler).sendGuiPacketToTracking();
			});
		}
	}

	@Override
	public net.minecraft.network.chat.Component getName() {
		return hasComponent(ComponentType.Name) ? this.<ComponentName>getComponent(ComponentType.Name).getName() : new TextComponent(References.ID + ".default.tile.name");
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == ElectrodynamicsCapabilities.ELECTRODYNAMIC && components[ComponentType.Electrodynamic.ordinal()] != null) {
			return components[ComponentType.Electrodynamic.ordinal()].getCapability(cap, side);
		}
		if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && components[ComponentType.FluidHandler.ordinal()] != null) {
			return components[ComponentType.FluidHandler.ordinal()].getCapability(cap, side);
		}
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && components[ComponentType.Inventory.ordinal()] != null) {
			return components[ComponentType.Inventory.ordinal()].getCapability(cap, side);
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setRemoved() {
		super.setRemoved();
		for (Component component : components) {
			if (component != null) {
				component.holder(this);
				component.remove();
			}
		}
		for (ComponentProcessor pr : processors) {
			if (pr != null) {
				pr.holder(this);
				pr.remove();
			}
		}
	}

	public SimpleContainerData getCoordsArray() {
		SimpleContainerData array = new SimpleContainerData(3);
		array.set(0, worldPosition.getX());
		array.set(1, worldPosition.getY());
		array.set(2, worldPosition.getZ());
		return array;
	}

	public boolean isPoweredByRedstone() {
		return level.getDirectSignalTo(worldPosition) > 0;
	}

	protected static TriPredicate<Integer, ItemStack, ComponentInventory> machineValidator() {
		return (x, y, i) -> x < i.getOutputStartIndex() || x >= i.getInputBucketStartIndex() && x < i.getUpgradeSlotStartIndex() && CapabilityUtils.hasFluidItemCap(y) || x >= i.getUpgradeSlotStartIndex() && y.getItem() instanceof ItemUpgrade upgrade && i.isUpgradeValid(upgrade.subtype);
	}

	protected static TriPredicate<Integer, ItemStack, ComponentInventory> machineValidator(int[] ints) {
		List<Integer> list = new ArrayList<>();
		for (int i : ints) {
			list.add(i);
		}
		return (x, y, i) -> list.contains(x) || x >= i.getInputBucketStartIndex() && x < i.getUpgradeSlotStartIndex() && CapabilityUtils.hasFluidItemCap(y) || x >= i.getUpgradeSlotStartIndex() && y.getItem() instanceof ItemUpgrade upgrade && i.isUpgradeValid(upgrade.subtype);
	}

}
