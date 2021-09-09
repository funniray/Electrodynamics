package electrodynamics.compatability.jei.recipecategories.psuedorecipes;

import java.util.ArrayList;

import electrodynamics.common.block.subtype.SubtypeMachine;
import electrodynamics.common.block.subtype.SubtypeOre;
import electrodynamics.common.item.subtype.SubtypeCrystal;
import electrodynamics.common.item.subtype.SubtypeMineralFluid;
import electrodynamics.common.item.subtype.SubtypeOxide;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PsuedoRecipes {

    /* Item/Fluid Storage */

    public static ArrayList<ArrayList<ItemStack>> ELECTRODYNAMICS_ITEMS = new ArrayList<>();
    public static ArrayList<Fluid> ELECTRODYNAMICS_FLUIDS = new ArrayList<>();
    public static ArrayList<ItemStack> ELECTRODYNAMICS_MACHINES = new ArrayList<>();

    public static void addElectrodynamicsRecipes() {
	addElectrodynamicsMachines();
	addElectrodynamicsFluids();
	addElectrodynamicsItems();
    }

    public static void addElectrodynamicsMachines() {
	// Coal Generator
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.coalgenerator)));
	// Upgrade Transformer
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.upgradetransformer)));
	// Downgrade Transformer
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.downgradetransformer)));
	// Solar Panel
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.solarpanel)));
	// Advanced Solar Panel
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.advancedsolarpanel)));
	// Thermoelectric Generator
	ELECTRODYNAMICS_MACHINES
		.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.thermoelectricgenerator)));
	// Combustion Chamber
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.combustionchamber)));
	// Hydroelectric Generator
	ELECTRODYNAMICS_MACHINES
		.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.hydroelectricgenerator)));
	// Wind Generator
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.windmill)));
	// Steel Fluid Tank
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.tanksteel)));
	// Reinforced Fluid Tank
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.tankreinforced)));
	// HSLA Fluid Tank
	ELECTRODYNAMICS_MACHINES.add(new ItemStack(electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeMachine.tankhsla)));
    }

    public static void addElectrodynamicsFluids() {
	ELECTRODYNAMICS_FLUIDS.add(Fluids.WATER.getFluid());
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.fluidSulfuricAcid);
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.iron));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.gold));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.copper));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.tin));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.lead));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.silver));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.vanadium));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.SUBTYPEMINERALFLUID_MAPPINGS.get(SubtypeMineralFluid.lithium));
	ELECTRODYNAMICS_FLUIDS.add(electrodynamics.DeferredRegisters.fluidEthanol);

    }

    public static void addElectrodynamicsItems() {
	/* ORES : 0 */
	Item[] ores = { Blocks.IRON_ORE.getBlock().asItem(), Blocks.GOLD_ORE.getBlock().asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.copper).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.tin).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.lead).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.silver).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.vanadinite).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.uraninite).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.sulfur).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.thorianite).asItem(),
		electrodynamics.DeferredRegisters.SUBTYPEBLOCK_MAPPINGS.get(SubtypeOre.lepidolite).asItem()

	};

	ELECTRODYNAMICS_ITEMS.add(formItemStacks(ores, 1));

	/* Ore Crystals : 1 and 2 */
	Item[] oreCrystals = { electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.iron),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.gold),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.copper),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.tin),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.lead),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.silver),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.vanadium),
		electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeCrystal.lithium)

	};

	// Adds individual items for reference
	ELECTRODYNAMICS_ITEMS.add(formItemStacks(oreCrystals, 1));

	// Adds Crystalyzation output stack size of crystals
	ELECTRODYNAMICS_ITEMS.add(formItemStacks(oreCrystals, 5));

	/* MISC ITEMS : 3 */
	Item[] miscIngredients = { electrodynamics.DeferredRegisters.SUBTYPEITEM_MAPPINGS.get(SubtypeOxide.trisulfur), Items.WATER_BUCKET

	};
	ELECTRODYNAMICS_ITEMS.add(formItemStacks(miscIngredients, 1));

    }

    private static ArrayList<ItemStack> formItemStacks(Item[] items, int countPerItemStack) {
	ArrayList<ItemStack> inputItems = new ArrayList<>();

	for (int i = 0; i < items.length; i++) {
	    inputItems.add(new ItemStack(items[i]));
	    inputItems.get(i).setCount(countPerItemStack);
	}
	return inputItems;
    }

}
