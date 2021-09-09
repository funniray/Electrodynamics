package electrodynamics.common.recipe.categories.fluid2item;

import java.util.List;

import electrodynamics.common.recipe.ElectrodynamicsRecipe;
import electrodynamics.common.recipe.recipeutils.FluidIngredient;
import electrodynamics.common.recipe.recipeutils.IFluidRecipe;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentProcessor;
import electrodynamics.prefab.tile.components.utils.AbstractFluidHandler;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class Fluid2ItemRecipe extends ElectrodynamicsRecipe implements IFluidRecipe {

    private FluidIngredient INPUT_FLUID;
    private ItemStack ITEM_OUTPUT;

    public Fluid2ItemRecipe(ResourceLocation recipeID, FluidIngredient fluidInput, ItemStack itemOutput) {
	super(recipeID);
	INPUT_FLUID = fluidInput;
	ITEM_OUTPUT = itemOutput;
    }

    @Override
    public boolean matchesRecipe(ComponentProcessor pr) {
    	AbstractFluidHandler<?> fluid = pr.getHolder().getComponent(ComponentType.FluidHandler);
	List<Fluid> inputFluids = fluid.getValidInputFluids();
	for (int i = 0; i < inputFluids.size(); i++) {
	    FluidTank tank = fluid.getTankFromFluid(inputFluids.get(i), true);
	    if (tank != null && tank.getFluid().getFluid().isEquivalentTo(INPUT_FLUID.getFluidStack().getFluid())
		    && tank.getFluidAmount() >= INPUT_FLUID.getFluidStack().getAmount()) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
	return ITEM_OUTPUT;
    }

    @Override
    public ItemStack getRecipeOutput() {
	return ITEM_OUTPUT;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
	return NonNullList.from(null, INPUT_FLUID);
    }
}
