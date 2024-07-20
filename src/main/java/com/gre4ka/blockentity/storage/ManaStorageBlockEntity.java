package com.gre4ka.blockentity.storage;

import com.gre4ka.PathOfMage;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.ModRegistry;
import com.gre4ka.util.PlayerData;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.gre4ka.blocks.ManaStorageBlock.MANA;

public class ManaStorageBlockEntity extends BlockEntity {

    public final String name;
    public int maxInput;
    public int maxOutput;
    public int maxStorage;
    public int mana;

    //public ComponentType<Integer> manaComponent = new ComponentType<Integer>() {

    public ManaStorageBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state, String name, int maxStorage, int maxInput, int maxOutput)
    {
        super(blockEntityType, pos, state);
        this.name = name;
        this.maxStorage = maxStorage;
        this.maxInput = maxInput;
        this.maxOutput = maxOutput;
    }

    @Override
    public void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper) {
        // Save the current value of the number to the nbt
        nbt.putInt("mana", mana);

        super.writeNbt(nbt, wrapper);
    }
    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapper) {
        super.readNbt(nbt, wrapper);

        mana = nbt.getInt("mana");
    }
    public int getStorageMana(){
        return mana;
    }
    public void setStorageMana(Integer setMana){
        mana = setMana;
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return new NbtCompound();
    }

    public void updateInClientWorld() {
        ((ServerWorld) world).getChunkManager().markForUpdate(pos);
    }

    public void addStorageMana(PlayerEntity player, BlockPos pos, BlockState state) {
        int tmpMana;
        int playermana = PlayerData.readPlayerMana((IDataSaver) player);
        if (playermana >= maxInput){
            tmpMana = maxInput;
        }
        else{
            tmpMana = playermana;
        }
        if (tmpMana + mana >= maxStorage){
            tmpMana = maxStorage - mana;
        }
        mana += tmpMana;
        PlayerData.removePlayerMana(player, tmpMana);
        setState(state, mana, maxStorage);
        PathOfMage.LOGGER.info("Block: " + mana);
        markDirty();
    }
    public void retrieveStorageMana(PlayerEntity player, BlockPos pos, BlockState state) {
        int tmpMana;
        int playermana = PlayerData.readPlayerMana((IDataSaver) player);
        if (playermana >= maxInput){
            tmpMana = maxInput;
        }
        else{
            tmpMana = playermana;
        }
        if (tmpMana + mana >= maxStorage){
            tmpMana = maxStorage - mana;
        }
        mana += tmpMana;
        PlayerData.removePlayerMana(player, tmpMana);
        setState(state, mana, maxStorage);
        PathOfMage.LOGGER.info("Block: " + mana);
        markDirty();
    }

    public void setState(BlockState state, int mana, int maxStorage){
        if (mana >= maxStorage / 2){
            world.setBlockState(pos, state.with(MANA, 15));
        }
        else if (mana > 0){
            world.setBlockState(pos, state.with(MANA, 7));
        }
        else {
            world.setBlockState(pos, state.with(MANA, 0));
        }
    }
    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder)
    {
        componentMapBuilder.add(ModRegistry.MANA_COMPONENT, this.mana);
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components)
    {

        int mana = components.getOrDefault(ModRegistry.MANA_COMPONENT, -1);
        if (mana != -1)
        {
            this.mana = mana;
        }
    }
}
