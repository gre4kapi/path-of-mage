package com.gre4ka.client.render.Dimension.LangDimension;
import com.gre4ka.PathOfMage;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class LangDimensionEffects extends DimensionEffects{

    public LangDimensionEffects() {
        super(Float.NaN, false, DimensionEffects.SkyType.NONE, true, true);
        PathOfMage.LOGGER.info("AAAAAAAAAAAAAA");
    }

    @Override
    public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
        PathOfMage.LOGGER.info("BBBBBBBBBBBBBBBB");
        return Vec3d.ZERO;
    }

    @Override
    public boolean useThickFog(int camX, int camY) {
        PathOfMage.LOGGER.info("CCCCCCCCCCCCCCCCCCCCCC");
        return false;
    }

    @Override
    public float[] getFogColorOverride(float skyAngle, float tickDelta) {
        PathOfMage.LOGGER.info("DDDDDDDDDDDDDDDDDD");
        return null;
    }
}
