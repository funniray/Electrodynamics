package electrodynamics.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IWrenchItem {
	boolean onRotate(ItemStack stack, BlockPos pos, Player player);

	boolean onPickup(ItemStack stack, BlockPos pos, Player player);
}
