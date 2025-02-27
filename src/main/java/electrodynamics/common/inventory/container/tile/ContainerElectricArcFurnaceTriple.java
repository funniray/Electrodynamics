package electrodynamics.common.inventory.container.tile;

import electrodynamics.DeferredRegisters;
import electrodynamics.common.tile.TileElectricArcFurnaceTriple;
import electrodynamics.prefab.inventory.container.GenericContainerBlockEntity;
import electrodynamics.prefab.inventory.container.slot.item.SlotGeneric;
import electrodynamics.prefab.inventory.container.slot.item.type.SlotUpgrade;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.SimpleContainerData;

public class ContainerElectricArcFurnaceTriple extends GenericContainerBlockEntity<TileElectricArcFurnaceTriple> {

	public ContainerElectricArcFurnaceTriple(int id, Inventory playerinv) {
		this(id, playerinv, new SimpleContainer(9));
	}

	public ContainerElectricArcFurnaceTriple(int id, Inventory playerinv, Container inventory) {
		this(id, playerinv, inventory, new SimpleContainerData(3));
	}

	public ContainerElectricArcFurnaceTriple(int id, Inventory playerinv, Container inventory, ContainerData inventorydata) {
		super(DeferredRegisters.CONTAINER_ELECTRICARCFURNACETRIPLE.get(), id, playerinv, inventory, inventorydata);
	}

	@Override
	public void addInventorySlots(Container inv, Inventory playerinv) {
		playerInvOffset = 20;
		addSlot(new SlotGeneric(inv, nextIndex(), 56, 24));
		addSlot(new FurnaceResultSlot(playerinv.player, inv, nextIndex(), 116, 24));
		addSlot(new SlotGeneric(inv, nextIndex(), 56, 44));
		addSlot(new FurnaceResultSlot(playerinv.player, inv, nextIndex(), 116, 44));
		addSlot(new SlotGeneric(inv, nextIndex(), 56, 64));
		addSlot(new FurnaceResultSlot(playerinv.player, inv, nextIndex(), 116, 64));
		addSlot(new SlotUpgrade(inv, nextIndex(), 153, 14, ContainerElectricArcFurnace.VALID_UPGRADES));
		addSlot(new SlotUpgrade(inv, nextIndex(), 153, 34, ContainerElectricArcFurnace.VALID_UPGRADES));
		addSlot(new SlotUpgrade(inv, nextIndex(), 153, 54, ContainerElectricArcFurnace.VALID_UPGRADES));
	}
}
