package electrodynamics.common.block.subtype;

import java.lang.reflect.InvocationTargetException;

import electrodynamics.api.ISubtype;
import electrodynamics.common.block.BlockMachine;
import electrodynamics.common.tile.TileAdvancedSolarPanel;
import electrodynamics.common.tile.TileBatteryBox;
import electrodynamics.common.tile.TileCarbyneBatteryBox;
import electrodynamics.common.tile.TileChargerHV;
import electrodynamics.common.tile.TileChargerLV;
import electrodynamics.common.tile.TileChargerMV;
import electrodynamics.common.tile.TileChemicalCrystallizer;
import electrodynamics.common.tile.TileChemicalMixer;
import electrodynamics.common.tile.TileCircuitBreaker;
import electrodynamics.common.tile.TileCoalGenerator;
import electrodynamics.common.tile.TileCobblestoneGenerator;
import electrodynamics.common.tile.TileCombustionChamber;
import electrodynamics.common.tile.TileCoolantResavoir;
import electrodynamics.common.tile.TileCreativeFluidSource;
import electrodynamics.common.tile.TileCreativePowerSource;
import electrodynamics.common.tile.TileElectricArcFurnace;
import electrodynamics.common.tile.TileElectricArcFurnaceDouble;
import electrodynamics.common.tile.TileElectricArcFurnaceTriple;
import electrodynamics.common.tile.TileElectricFurnace;
import electrodynamics.common.tile.TileElectricFurnaceDouble;
import electrodynamics.common.tile.TileElectricFurnaceTriple;
import electrodynamics.common.tile.TileElectricPump;
import electrodynamics.common.tile.TileElectrolyticSeparator;
import electrodynamics.common.tile.TileEnergizedAlloyer;
import electrodynamics.common.tile.TileFermentationPlant;
import electrodynamics.common.tile.TileFluidVoid;
import electrodynamics.common.tile.TileHydroelectricGenerator;
import electrodynamics.common.tile.TileLathe;
import electrodynamics.common.tile.TileLithiumBatteryBox;
import electrodynamics.common.tile.TileMineralCrusher;
import electrodynamics.common.tile.TileMineralCrusherDouble;
import electrodynamics.common.tile.TileMineralCrusherTriple;
import electrodynamics.common.tile.TileMineralGrinder;
import electrodynamics.common.tile.TileMineralGrinderDouble;
import electrodynamics.common.tile.TileMineralGrinderTriple;
import electrodynamics.common.tile.TileMineralWasher;
import electrodynamics.common.tile.TileMotorComplex;
import electrodynamics.common.tile.TileMultimeterBlock;
import electrodynamics.common.tile.TileOxidationFurnace;
import electrodynamics.common.tile.TileQuarry;
import electrodynamics.common.tile.TileReinforcedAlloyer;
import electrodynamics.common.tile.TileSeismicRelay;
import electrodynamics.common.tile.TileSolarPanel;
import electrodynamics.common.tile.TileTankHSLA;
import electrodynamics.common.tile.TileTankReinforced;
import electrodynamics.common.tile.TileTankSteel;
import electrodynamics.common.tile.TileThermoelectricGenerator;
import electrodynamics.common.tile.TileTransformer;
import electrodynamics.common.tile.TileWindmill;
import electrodynamics.common.tile.TileWireMill;
import electrodynamics.common.tile.TileWireMillDouble;
import electrodynamics.common.tile.TileWireMillTriple;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public enum SubtypeMachine implements ISubtype {

	electricfurnace(true, TileElectricFurnace.class),
	electricfurnacerunning(false, TileElectricFurnace.class),
	electricfurnacedouble(true, TileElectricFurnaceDouble.class),
	electricfurnacedoublerunning(false, TileElectricFurnaceDouble.class),
	electricfurnacetriple(true, TileElectricFurnaceTriple.class),
	electricfurnacetriplerunning(false, TileElectricFurnaceTriple.class),
	electricarcfurnace(true, TileElectricArcFurnace.class),
	electricarcfurnacerunning(false, TileElectricArcFurnace.class),
	electricarcfurnacedouble(true, TileElectricArcFurnaceDouble.class),
	electricarcfurnacedoublerunning(false, TileElectricArcFurnaceDouble.class),
	electricarcfurnacetriple(true, TileElectricArcFurnaceTriple.class),
	electricarcfurnacetriplerunning(false, TileElectricArcFurnaceTriple.class),
	coalgenerator(true, TileCoalGenerator.class),
	coalgeneratorrunning(false, TileCoalGenerator.class),
	wiremill(true, TileWireMill.class),
	wiremilldouble(true, TileWireMillDouble.class),
	wiremilltriple(true, TileWireMillTriple.class),
	mineralcrusher(true, TileMineralCrusher.class, true),
	mineralcrusherdouble(true, TileMineralCrusherDouble.class, true),
	mineralcrushertriple(true, TileMineralCrusherTriple.class, true),
	mineralgrinder(true, TileMineralGrinder.class, true),
	mineralgrinderdouble(true, TileMineralGrinderDouble.class, true),
	mineralgrindertriple(true, TileMineralGrinderTriple.class, true),
	batterybox(true, TileBatteryBox.class, true),
	lithiumbatterybox(true, TileLithiumBatteryBox.class, true),
	carbynebatterybox(true, TileCarbyneBatteryBox.class, true),
	oxidationfurnace(true, TileOxidationFurnace.class),
	oxidationfurnacerunning(false, TileOxidationFurnace.class),
	downgradetransformer(true, TileTransformer.class),
	upgradetransformer(true, TileTransformer.class),
	solarpanel(true, TileSolarPanel.class),
	advancedsolarpanel(true, TileAdvancedSolarPanel.class),
	electricpump(true, TileElectricPump.class),
	thermoelectricgenerator(true, TileThermoelectricGenerator.class),
	fermentationplant(true, TileFermentationPlant.class),
	combustionchamber(true, TileCombustionChamber.class),
	hydroelectricgenerator(true, TileHydroelectricGenerator.class),
	windmill(true, TileWindmill.class),
	mineralwasher(true, TileMineralWasher.class),
	chemicalmixer(true, TileChemicalMixer.class, true),
	chemicalcrystallizer(true, TileChemicalCrystallizer.class),
	circuitbreaker(true, TileCircuitBreaker.class),
	multimeterblock(true, TileMultimeterBlock.class),
	energizedalloyer(true, TileEnergizedAlloyer.class),
	energizedalloyerrunning(false, TileEnergizedAlloyer.class),
	lathe(true, TileLathe.class, true),
	reinforcedalloyer(true, TileReinforcedAlloyer.class),
	reinforcedalloyerrunning(false, TileReinforcedAlloyer.class),
	chargerlv(true, TileChargerLV.class),
	chargermv(true, TileChargerMV.class),
	chargerhv(true, TileChargerHV.class),
	tanksteel(true, TileTankSteel.class),
	tankreinforced(true, TileTankReinforced.class),
	tankhsla(true, TileTankHSLA.class),
	cobblestonegenerator(true, TileCobblestoneGenerator.class),
	creativepowersource(true, TileCreativePowerSource.class),
	creativefluidsource(true, TileCreativeFluidSource.class),
	fluidvoid(true, TileFluidVoid.class),
	electrolyticseparator(true, TileElectrolyticSeparator.class),
	seismicrelay(true, TileSeismicRelay.class),
	quarry(true, TileQuarry.class),
	coolantresavoir(true, TileCoolantResavoir.class),
	motorcomplex(true, TileMotorComplex.class);

	public final Class<? extends BlockEntity> tileclass;
	public final boolean showInItemGroup;
	private RenderShape type = RenderShape.MODEL;

	SubtypeMachine(boolean showInItemGroup, Class<? extends BlockEntity> tileclass) {
		this.showInItemGroup = showInItemGroup;
		this.tileclass = tileclass;
	}

	SubtypeMachine(boolean showInItemGroup, Class<? extends BlockEntity> tileclass, boolean customModel) {
		this(showInItemGroup, tileclass);
		if (customModel) {
			type = RenderShape.ENTITYBLOCK_ANIMATED;
		}
	}

	public RenderShape getRenderType() {
		return type;
	}

	public static boolean shouldBreakOnReplaced(BlockState before, BlockState after) {
		Block bb = before.getBlock();
		Block ba = after.getBlock();
		if (bb instanceof BlockMachine tb && ba instanceof BlockMachine ta) {
			SubtypeMachine mb = tb.machine;
			SubtypeMachine ma = ta.machine;
			return (mb != electricfurnace || ma != electricfurnacerunning) && (mb != electricfurnacerunning || ma != electricfurnace) && (mb != electricfurnacedouble || ma != electricfurnacedoublerunning) && (mb != electricfurnacedoublerunning || ma != electricfurnacedouble) && (mb != electricfurnacetriple || ma != electricfurnacetriplerunning) && (mb != electricfurnacetriplerunning || ma != electricfurnacetriple) && (mb != coalgenerator || ma != coalgeneratorrunning) && (mb != coalgeneratorrunning || ma != coalgenerator) && (mb != oxidationfurnace || ma != oxidationfurnacerunning) && (mb != oxidationfurnacerunning || ma != oxidationfurnace) && (mb != energizedalloyer || ma != energizedalloyerrunning) && (ma != energizedalloyer || mb != energizedalloyerrunning) && (ma != reinforcedalloyer || mb != reinforcedalloyerrunning) && (mb != reinforcedalloyer || ma != reinforcedalloyerrunning) && (mb != electricarcfurnace || ma != electricarcfurnacerunning) && (mb != electricarcfurnacerunning || ma != electricarcfurnace) && (mb != electricarcfurnacedouble || ma != electricarcfurnacedoublerunning) && (mb != electricarcfurnacedoublerunning || ma != electricarcfurnacedouble) && (mb != electricarcfurnacetriple || ma != electricarcfurnacetriplerunning) && (mb != electricarcfurnacetriplerunning || ma != electricarcfurnacetriple);
		}
		return true;
	}

	public BlockEntity createTileEntity(BlockPos pos, BlockState state) {
		if (tileclass != null) {
			try {
				return tileclass.getConstructor(BlockPos.class, BlockState.class).newInstance(pos, state);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String tag() {
		return name();
	}

	@Override
	public String forgeTag() {
		return tag();
	}

	@Override
	public boolean isItem() {
		return false;
	}

	public boolean isPlayerStorable() {
		return this == quarry;
	}
}
