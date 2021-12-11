package electrodynamics.client.screen;

import java.util.ArrayList;

import electrodynamics.api.electricity.formatting.ChatFormatter;
import electrodynamics.api.electricity.formatting.ElectricUnit;
import electrodynamics.common.inventory.container.ContainerMineralWasher;
import electrodynamics.common.item.subtype.SubtypeItemUpgrade;
import electrodynamics.common.tile.TileMineralWasher;
import electrodynamics.prefab.inventory.container.slot.SlotRestricted;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentElectricInfo;
import electrodynamics.prefab.screen.component.ScreenComponentFluid;
import electrodynamics.prefab.screen.component.ScreenComponentInfo;
import electrodynamics.prefab.screen.component.ScreenComponentProgress;
import electrodynamics.prefab.screen.component.ScreenComponentSlot;
import electrodynamics.prefab.screen.component.ScreenComponentSlot.EnumSlotType;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentElectrodynamic;
import electrodynamics.prefab.tile.components.type.ComponentProcessor;
import electrodynamics.prefab.tile.components.utils.AbstractFluidHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenMineralWasher extends GenericScreen<ContainerMineralWasher> {
    public ScreenMineralWasher(ContainerMineralWasher container, Inventory playerInventory, Component title) {
	super(container, playerInventory, title);
	components.add(new ScreenComponentProgress(() -> {
	    GenericTile furnace = container.getHostFromIntArray();
	    if (furnace != null) {
		ComponentProcessor processor = furnace.getComponent(ComponentType.Processor);
		if (processor.operatingTicks > 0) {
		    return Math.min(1.0, processor.operatingTicks / (processor.requiredTicks / 2.0));
		}
	    }
	    return 0;
	}, this, 42, 30));
	components.add(new ScreenComponentProgress(() -> {
	    GenericTile furnace = container.getHostFromIntArray();
	    if (furnace != null) {
		ComponentProcessor processor = furnace.getComponent(ComponentType.Processor);
		if (processor.operatingTicks > processor.requiredTicks / 2.0) {
		    return Math.min(1.0, (processor.operatingTicks - processor.requiredTicks / 2.0) / (processor.requiredTicks / 2.0));
		}
	    }
	    return 0;
	}, this, 98, 30));
	components.add(new ScreenComponentProgress(() -> 0, this, 42, 50).left());
	components.add(new ScreenComponentFluid(() -> {
	    TileMineralWasher boiler = container.getHostFromIntArray();
	    if (boiler != null) {
		return ((AbstractFluidHandler<?>) boiler.getComponent(ComponentType.FluidHandler)).getInputTanks()[0];
	    }
	    return null;
	}, this, 21, 18));
	components.add(new ScreenComponentFluid(() -> {
	    TileMineralWasher boiler = container.getHostFromIntArray();
	    if (boiler != null) {
		return ((AbstractFluidHandler<?>) boiler.getComponent(ComponentType.FluidHandler)).getOutputTanks()[0];
	    }
	    return null;
	}, this, 127, 18));
	components.add(new ScreenComponentElectricInfo(this::getEnergyInformation, this, -ScreenComponentInfo.SIZE + 1, 2));
    }

    @Override
    protected ScreenComponentSlot createScreenSlot(Slot slot) {
	return new ScreenComponentSlot(slot instanceof SlotRestricted res
		&& res.mayPlace(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeItemUpgrade.basicspeed)))
			? EnumSlotType.SPEED
			: slot instanceof SlotRestricted ? EnumSlotType.LIQUID : EnumSlotType.NORMAL,
		this, slot.x - 1, slot.y - 1);
    }

    private ArrayList<FormattedCharSequence> getEnergyInformation() {
	ArrayList<FormattedCharSequence> list = new ArrayList<>();
	GenericTile box = menu.getHostFromIntArray();
	if (box != null) {
	    ComponentElectrodynamic electro = box.getComponent(ComponentType.Electrodynamic);
	    ComponentProcessor processor = box.getComponent(ComponentType.Processor);

	    list.add(new TranslatableComponent("gui.chemicalmixer.usage",
		    new TextComponent(ChatFormatter.getElectricDisplayShort(processor.getUsage() * 20, ElectricUnit.WATT))
			    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
	    list.add(new TranslatableComponent("gui.chemicalmixer.voltage",
		    new TextComponent(ChatFormatter.getElectricDisplayShort(electro.getVoltage(), ElectricUnit.VOLTAGE))
			    .withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.DARK_GRAY).getVisualOrderText());
	}
	return list;
    }

}