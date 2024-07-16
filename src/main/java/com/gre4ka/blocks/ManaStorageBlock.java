package com.gre4ka.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class ManaStorageBlock extends Block{
    //private static int maxStorage;
    public static final IntProperty MANA = IntProperty.of("mana", 0, 15);

    public ManaStorageBlock() {
        super(AbstractBlock.Settings.create().requiresTool().nonOpaque().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).luminance(ManaStorageBlock::getLuminance));
        setDefaultState(getDefaultState().with(MANA, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(MANA);
    }

    public static int getLuminance(BlockState currentBlockState) {
        return currentBlockState.get(MANA);
    }
}