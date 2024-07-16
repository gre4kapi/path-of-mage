package com.gre4ka.util;

import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.MaxManaSyncPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerData {
    //public static final int maxPlayerMana = 200;
    public static int mana;
    public static int maxMana;
    public static int addPlayerMana(IDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        mana = nbt.getInt("mana");
        if (mana + amount >= maxMana){
            mana = maxMana;
        }
        else{
            mana += amount;
        }
        nbt.putInt("mana", mana);
        syncMana(mana, maxMana, (ServerPlayerEntity) player);
        return mana;
    }

    public static int removePlayerMana(IDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        mana = nbt.getInt("mana");
        if (mana - amount < 0){
            mana = 0;
        }
        else{
            mana -= amount;
        }
        nbt.putInt("mana", mana);
        syncMana(mana, maxMana, (ServerPlayerEntity) player);
        return mana;
    }
    public static int readPlayerMana(IDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("mana");
    }
    public static void setPlayerMaxMana(IDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        maxMana = amount;
        nbt.putInt("maxMana", maxMana);
        syncMana(mana, amount, (ServerPlayerEntity) player);
    }
    public static int getPlayerMaxMana(IDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("maxMana");
    }
    public static void syncMana(int mana, int maxMana, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new ManaSyncPayload(mana));
        ServerPlayNetworking.send(player, new MaxManaSyncPayload(maxMana));
    }
}
