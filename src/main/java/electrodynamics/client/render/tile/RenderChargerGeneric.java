package electrodynamics.client.render.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;

import electrodynamics.api.item.IItemElectric;
import electrodynamics.common.tile.generic.GenericTileCharger;
import electrodynamics.prefab.tile.components.ComponentType;
import electrodynamics.prefab.tile.components.type.ComponentDirection;
import electrodynamics.prefab.tile.components.type.ComponentInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;

public class RenderChargerGeneric implements BlockEntityRenderer<GenericTileCharger> {
	public RenderChargerGeneric(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(GenericTileCharger tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
		Direction dir = tileEntityIn.<ComponentDirection>getComponent(ComponentType.Direction).getDirection();
		ComponentInventory inv = tileEntityIn.getComponent(ComponentType.Inventory);
		ItemStack chargingItem = inv.getItem(0);
		if (chargingItem.isEmpty()) {
			chargingItem = inv.getItem(1);
		}

		if (chargingItem != null && !chargingItem.isEmpty() && chargingItem.getItem() instanceof IItemElectric) {
			matrixStackIn.pushPose();

			if (chargingItem.getItem() instanceof DiggerItem) {
				switch (dir) {
				case NORTH:
					matrixStackIn.translate(0.5f, 1.25f, 0.47f);
					matrixStackIn.scale(0.5f, 0.5f, 0.5f);
					matrixStackIn.mulPose(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 180, true));
					break;
				case EAST:
					matrixStackIn.translate(0.53f, 1.25f, 0.5f);
					matrixStackIn.scale(0.5f, 0.5f, 0.5f);
					matrixStackIn.mulPose(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 90, true));
					break;
				case SOUTH:
					matrixStackIn.translate(0.5f, 1.25f, 0.53f);
					matrixStackIn.scale(0.5f, 0.5f, 0.5f);
					break;
				case WEST:
					matrixStackIn.translate(0.47f, 1.25f, 0.5f);
					matrixStackIn.scale(0.5f, 0.5f, 0.5f);
					matrixStackIn.mulPose(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 270, true));
					break;
				default:
				}
			} else {
				switch (dir) {
				case NORTH:
					matrixStackIn.translate(0.5f, 1.15f, 0.47f);
					matrixStackIn.scale(0.28f, 0.28f, 0.28f);
					matrixStackIn.mulPose(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 270, true));
					break;
				case EAST:
					matrixStackIn.translate(0.53f, 1.15f, 0.5f);
					matrixStackIn.scale(0.28f, 0.28f, 0.28f);
					matrixStackIn.mulPose(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 180, true));
					break;
				case SOUTH:
					matrixStackIn.translate(0.5f, 1.15f, 0.53f);
					matrixStackIn.scale(0.28f, 0.28f, 0.28f);
					matrixStackIn.mulPose(new Quaternion(new Vector3f(0.0F, 1.0F, 0.0F), 90, true));
					break;
				case WEST:
					matrixStackIn.translate(0.47f, 1.15f, 0.5f);
					matrixStackIn.scale(0.28f, 0.28f, 0.28f);
					break;
				default:
				}
			}
			Minecraft.getInstance().getItemRenderer().renderStatic(chargingItem, TransformType.NONE, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn, 0);
			matrixStackIn.popPose();
		}

	}

}
