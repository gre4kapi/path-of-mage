package com.gre4ka.blockentity.storage;

import com.gre4ka.util.ModRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class RefinedDiamondBlockEntity extends ManaStorageBlockEntity {

    public RefinedDiamondBlockEntity(BlockPos pos, BlockState state) {
        super(ModRegistry.REFINED_DIAMOND_MANA_STORAGE, pos, state, "REFINED_DIAMOND_MANA_STORAGE", 100000, 10000, 10000);
    }
}