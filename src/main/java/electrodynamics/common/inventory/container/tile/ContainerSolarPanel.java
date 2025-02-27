package electrodynamics.common.inventory.container.tile;

import electrodynamics.common.item.subtype.SubtypeItemUpgrade;
import electrodynamics.common.tile.TileSolarPanel;
import electrodynamics.prefab.inventory.container.GenericContainerBlockEntity;
import electrodynamics.prefab.inventory.container.slot.item.type.SlotUpgrade;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;

public class ContainerSolarPanel extends GenericContainerBlockEntity<TileSolarPanel> {

	public static final SubtypeItemUpgrade[] VALID_UPGRADES = new SubtypeItemUpgrade[] { SubtypeItemUpgrade.improvedsolarcell };

	public ContainerSolarPanel(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(1), new SimpleContainerData(3));
	}

	public ContainerSolarPanel(int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(electrodynamics.DeferredRegisters.CONTAINER_SOLARPANEL.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(Container inv, Inventory playerinv) {
		addSlot(new SlotUpgrade(inv, nextIndex(), 25, 42, VALID_UPGRADES));
	}
}
