package com.gre4ka.blocks;

import com.gre4ka.blockentity.storage.DiamondBlockEntity;
import com.gre4ka.blockentity.storage.ManaStorageBlockEntity;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;

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
    /*@Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!world.isClient) {
                ItemStack itemStack = new ItemStack(this);
                itemStack.applyComponentsFrom(blockEntity.createComponentMap());
                ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
            return super.onBreak(world, pos, state, player);
    }*/
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            if(world.getBlockEntity(pos) instanceof ManaStorageBlockEntity blockEntity){
                blockEntity.addStorageMana(player, pos, state);
            }
        }
        return ActionResult.SUCCESS;
    }


}