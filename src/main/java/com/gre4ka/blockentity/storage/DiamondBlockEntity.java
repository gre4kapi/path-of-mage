package com.gre4ka.blockentity.storage;

import com.gre4ka.util.ModRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class DiamondBlockEntity extends ManaStorageBlockEntity {
    /*public DiamondBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, String name, int maxStorage, int maxInput, int maxOutput) {
        super(ModBlockEntities.DIAMOND_MANA_STORAGE, pos, state, "DIAMOND_MANA_STORAGE", 1000, 100, 100);
    }*/
    public DiamondBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.DIAMOND_MANA_STORAGE, pos, state, "DIAMOND_MANA_STORAGE", 1000, 100, 100);
    }
}
