package com.slimeclient.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.TntEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.slimeclient.ExampleMod;

@Mixin(TntEntityRenderer.class)
public abstract class ExampleMixin extends EntityRenderer<TntEntity> {
	protected ExampleMixin(EntityRendererFactory.Context ctx) {
		super(ctx);
	}

	@Inject(
			method = "render(Lnet/minecraft/entity/TntEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderer;render(Lnet/minecraft/entity/Entity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
	)
	private void renderTntLabel(TntEntity entity, float yaw, float delta, MatrixStack matrixStack,
								VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo ci) {
		super.renderLabelIfPresent(entity, ExampleMod.getTime(entity.getFuse()), matrixStack, vertexConsumerProvider, light);
	}
}