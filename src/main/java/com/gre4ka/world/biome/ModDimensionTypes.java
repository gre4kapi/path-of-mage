package com.gre4ka.world.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionType;

public class ModDimensionTypes {
    public static final RegistryKey<DimensionType> LANG = of("lang");
    public static final Identifier LANG_ID = Identifier.ofVanilla("lang");

    public ModDimensionTypes() {
    }

    private static RegistryKey<DimensionType> of(String id) {
        return RegistryKey.of(RegistryKeys.DIMENSION_TYPE, Identifier.ofVanilla(id));
    }
}
