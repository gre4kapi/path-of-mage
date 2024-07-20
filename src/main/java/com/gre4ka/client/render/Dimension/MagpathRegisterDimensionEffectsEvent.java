package com.gre4ka.client.render.Dimension;

import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public record MagpathRegisterDimensionEffectsEvent(BiConsumer<Identifier, DimensionEffects> effects) {
    public static final DimensionEventHandler<MagpathRegisterDimensionEffectsEvent> EVENT = new DimensionEventHandler<>();

    public void register(Identifier dimension, DimensionEffects effect) {
        effects.accept(dimension, effect);
    }
}