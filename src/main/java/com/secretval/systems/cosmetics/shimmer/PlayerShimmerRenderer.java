package com.secretval.systems.cosmetics.shimmer;

import com.secretval.Viok;
import com.secretval.systems.cosmetics.Cosmetics;

import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Identifier;

public class PlayerShimmerRenderer extends EnergySwirlOverlayFeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
	private final Identifier SKIN;
    private final PlayerEntityModel model;
    public boolean active = false;

    public PlayerShimmerRenderer(
            FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> featureRendererContext, LoadedEntityModels loader, Identifier SKIN) {
        super(featureRendererContext);
        this.model = new PlayerEntityModel(loader.getModelPart(EntityModelLayers.PLAYER), false);
        this.SKIN = SKIN;
    }

    @SuppressWarnings("unchecked")
    protected boolean shouldRender(PlayerEntityRenderState state) {
        return active && Viok.systems.get(Cosmetics.class).getPlayer() == Viok.mc.player;
    }

    @Override
	protected float getEnergySwirlX(float partialAge) {
		return partialAge * 0.01F;
	}

	@Override
	protected Identifier getEnergySwirlTexture() {
		return SKIN;
	}

    @Override
    protected PlayerEntityModel getEnergySwirlModel() {
        return this.model;
    }
}
