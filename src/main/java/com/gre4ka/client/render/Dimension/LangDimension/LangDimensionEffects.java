package com.gre4ka.client.render.Dimension.LangDimension;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.math.Vec3d;

public class LangDimensionEffects extends DimensionEffects{
    public LangDimensionEffects() {
        super(Float.NaN, false, DimensionEffects.SkyType.NONE, false, true);
    }

    @Override
    public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
        return color;
    }

    @Override
    public boolean useThickFog(int camX, int camY) {
        return true;
    }
}
