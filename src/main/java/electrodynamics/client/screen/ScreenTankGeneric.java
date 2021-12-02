package electrodynamics.client.screen;

import electrodynamics.common.inventory.container.ContainerTankGeneric;
import electrodynamics.common.tile.generic.TileGenericTank;
import electrodynamics.prefab.screen.GenericScreen;
import electrodynamics.prefab.screen.component.ScreenComponentFluid;
import electrodynamics.prefab.screen.component.ScreenComponentProgress;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.utils.AbstractFluidHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ScreenTankGeneric extends GenericScreen<ContainerTankGeneric> {

    public ScreenTankGeneric(ContainerTankGeneric screenContainer, Inventory inv, Component titleIn) {
	super(screenContainer, inv, titleIn);

	components.add(new ScreenComponentProgress(() -> 0, this, 52, 33));
	components.add(new ScreenComponentProgress(() -> 0, this, 102, 33));
	components.add(new ScreenComponentFluid(() -> {
	    TileGenericTank boiler = menu.getHostFromIntArray();
	    if (boiler != null) {
		return ((AbstractFluidHandler<?>) boiler.getComponent(ComponentType.FluidHandler)).getOutputTanks()[0];
	    }
	    return null;
	}, this, 81, 18));
    }

}
