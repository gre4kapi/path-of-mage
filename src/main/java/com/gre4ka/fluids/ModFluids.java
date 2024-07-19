package com.gre4ka.fluids;

import com.gre4ka.PathOfMage;
import com.mojang.serialization.Decoder;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static FlowableFluid STILL_SKHARNA;
    public static FlowableFluid FLOWING_SKHARNA;
    public static Block SKHARNA_FLUID_BLOCK;
    public static Item SKHARNA_BUCKET;

    public static void registerFluids(){
        STILL_SKHARNA = Registry.register(Registries.FLUID, Identifier.of(PathOfMage.MOD_ID, "skharna"), new SkharnaFluid.Still());
        FLOWING_SKHARNA = Registry.register(Registries.FLUID, Identifier.of(PathOfMage.MOD_ID, "flowing_skharna"), new SkharnaFluid.Flowing());
        SKHARNA_FLUID_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(PathOfMage.MOD_ID, "skharna_fluid_block"), new FluidBlock(STILL_SKHARNA, FabricBlockSettings.copyOf(Blocks.WATER)));
        SKHARNA_BUCKET = Registry.register(Registries.ITEM, Identifier.of(PathOfMage.MOD_ID, "skharna_bucket"),
                new BucketItem(STILL_SKHARNA, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));
    }
    public static void fluidRender(){
        FluidRenderHandlerRegistry.INSTANCE.register(STILL_SKHARNA, FLOWING_SKHARNA,
                new SimpleFluidRenderHandler(
                    Identifier.of("minecraft:block/water_still"),
                    Identifier.of("minecraft:block/water_flow"), 0xC1CCCCCC
                ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), STILL_SKHARNA, FLOWING_SKHARNA);
    }
}