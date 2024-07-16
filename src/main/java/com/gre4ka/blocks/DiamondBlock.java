package com.gre4ka.blocks;

import com.gre4ka.PathOfMage;
import com.gre4ka.blockentity.storage.DiamondBlockEntity;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DiamondBlock extends ManaStorageBlock implements BlockEntityProvider {
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DiamondBlockEntity(pos, state);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            DiamondBlockEntity blockEntity = (DiamondBlockEntity) world.getBlockEntity(pos);
            int playermana = PlayerData.readPlayerMana((IDataSaver) player);
            int blockmana = blockEntity.getStorageMana();
            blockEntity.addStorageMana(player, pos, state);
        }

        return ActionResult.SUCCESS;
    }

}