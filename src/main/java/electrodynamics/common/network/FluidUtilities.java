package electrodynamics.common.network;

import java.util.ArrayList;
import java.util.List;

import electrodynamics.api.network.pipe.IPipe;
import electrodynamics.common.tile.generic.GenericTilePipe;
import electrodynamics.prefab.tile.GenericTile;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.generic.AbstractFluidHandler;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import electrodynamics.prefab.utilities.BlockEntityUtils;
import electrodynamics.prefab.utilities.CapabilityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidUtilities {

	public static boolean isFluidReceiver(BlockEntity acceptor) {
		for (Direction dir : Direction.values()) {
			boolean is = isFluidReceiver(acceptor, dir);
			if (is) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFluidReceiver(BlockEntity acceptor, Direction dir) {
		if (acceptor != null) {
			if (acceptor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, dir).isPresent()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isConductor(BlockEntity acceptor) {
		return acceptor instanceof IPipe;
	}

	public static Integer receiveFluid(BlockEntity acceptor, Direction direction, FluidStack perReceiver, boolean debug) {
		if (isFluidReceiver(acceptor, direction)) {
			LazyOptional<IFluidHandler> cap = acceptor.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction);
			if (cap.isPresent()) {
				IFluidHandler handler = cap.resolve().get();
				boolean canPass = false;
				for (int i = 0; i < handler.getTanks(); i++) {
					if (handler.isFluidValid(i, perReceiver)) {
						canPass = true;
						break;
					}
				}
				if (canPass) {
					return handler.fill(perReceiver, debug ? FluidAction.SIMULATE : FluidAction.EXECUTE);
				}
			}
		}
		return 0;
	}

	public static boolean canInputFluid(BlockEntity acceptor, Direction direction) {
		return isFluidReceiver(acceptor, direction);
	}

	public static void outputToPipe(GenericTile tile, FluidTank[] tanks, Direction... outputDirections) {
		ComponentDirection componentDirection = tile.getComponent(ComponentType.Direction);
		for (Direction relative : outputDirections) {
			Direction direction = BlockEntityUtils.getRelativeSide(componentDirection.getDirection(), relative.getOpposite());
			BlockPos face = tile.getBlockPos().relative(direction.getOpposite());
			BlockEntity faceTile = tile.getLevel().getBlockEntity(face);
			if (faceTile != null) {
				boolean electroPipe = faceTile instanceof GenericTilePipe;
				LazyOptional<IFluidHandler> cap = faceTile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, direction);
				if (cap.isPresent()) {
					IFluidHandler fHandler = cap.resolve().get();
					for (FluidTank fluidTank : tanks) {
						FluidStack tankFluid = fluidTank.getFluid();
						if (electroPipe) {
							if (fluidTank.getFluidAmount() > 0) {
								fluidTank.getFluid().shrink(fHandler.fill(tankFluid, FluidAction.EXECUTE));
							}
						} else {
							int amtAccepted = fHandler.fill(tankFluid, FluidAction.SIMULATE);
							FluidStack taken = new FluidStack(tankFluid.getFluid(), amtAccepted);
							fHandler.fill(taken, FluidAction.EXECUTE);
							fluidTank.drain(taken, FluidAction.EXECUTE);
						}
					}
				}
			}
		}
	}

	public static void drainItem(GenericTile tile) {
		ComponentInventory inv = tile.getComponent(ComponentType.Inventory);
		AbstractFluidHandler<?> handler = tile.getComponent(ComponentType.FluidHandler);
		// should stop the crashes now
		if (handler != null) {
			FluidTank[] tanks = handler.getInputTanks();
			List<ItemStack> buckets = inv.getInputBucketContents();
			if (tanks.length >= buckets.size()) {
				for (int i = 0; i < buckets.size(); i++) {
					FluidTank tank = tanks[i];
					ItemStack stack = buckets.get(i);
					if (!stack.isEmpty() && !CapabilityUtils.isFluidItemNull()) {
						FluidStack containerFluid = CapabilityUtils.simDrain(stack, Integer.MAX_VALUE);
						if (handler.getValidInputFluids().contains(containerFluid.getFluid()) && tank.isFluidValid(containerFluid)) {
							int amtDrained = tank.fill(containerFluid, FluidAction.SIMULATE);
							FluidStack drained = new FluidStack(containerFluid.getFluid(), amtDrained);
							CapabilityUtils.drain(stack, drained);
							tank.fill(drained, FluidAction.EXECUTE);
							if (stack.getItem() instanceof BucketItem) {
								inv.setItem(inv.getInputBucketStartIndex() + i, new ItemStack(Items.BUCKET, 1));
							}
						}
					}
				}
			}
		}
	}

	public static void fillItem(GenericTile tile) {
		ComponentInventory inv = tile.getComponent(ComponentType.Inventory);
		AbstractFluidHandler<?> handler = tile.getComponent(ComponentType.FluidHandler);
		// should stop the crashes now
		if (handler != null) {
			FluidTank[] tanks = handler.getOutputTanks();
			List<ItemStack> buckets = inv.getOutputBucketContents();
			if (tanks.length >= buckets.size()) {
				for (int i = 0; i < buckets.size(); i++) {
					ItemStack stack = buckets.get(i);
					FluidTank tank = tanks[i];
					if (!stack.isEmpty() && !(stack.getItem() instanceof BucketItem) && !CapabilityUtils.isFluidItemNull()) {
						FluidStack fluid = tank.getFluid();
						int amtFilled = CapabilityUtils.simFill(stack, fluid);
						FluidStack taken = new FluidStack(fluid.getFluid(), amtFilled);
						CapabilityUtils.fill(stack, taken);
						tank.drain(taken, FluidAction.EXECUTE);
					}
				}
			}
		}
	}

	public static Fluid[] getAllRegistryFluids() {
		List<Fluid> list = new ArrayList<>(ForgeRegistries.FLUIDS.getValues());
		Fluid[] fluids = new Fluid[list.size()];
		for (int i = 0; i < list.size(); i++) {
			fluids[i] = list.get(i);
		}
		return fluids;
	}

}
