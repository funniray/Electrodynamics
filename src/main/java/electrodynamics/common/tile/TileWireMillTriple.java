package electrodynamics.common.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TileWireMillTriple extends TileWireMill {
	public TileWireMillTriple(BlockPos worldPosition, BlockState blockState) {
		super(2, worldPosition, blockState);
	}
}
