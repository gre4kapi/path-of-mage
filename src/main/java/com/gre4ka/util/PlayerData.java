package com.gre4ka.util;

import com.gre4ka.PathOfMage;
import com.gre4ka.network.payload.ManaGenSpdSyncPayload;
import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.MaxManaSyncPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

import java.nio.file.Path;

public class PlayerData {

    public static int mana;
    public static int maxMana;
    public static float manaGenSpd;

    public static int addPlayerMana(IDataSaver player, int amount){
        PathOfMage.LOGGER.info("tryind add mana: " + amount);
        NbtCompound nbt = player.getPersistentData();
        mana = nbt.getInt("mana");
        maxMana = nbt.getInt("maxMana");
        if (mana + amount >= maxMana){
            mana = maxMana;
        }
        else{
            mana += amount;
        }
        nbt.putInt("mana", mana);
        syncMana(mana, (ServerPlayerEntity) player);
        return mana;
    }

    public static int removePlayerMana(IDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        mana = nbt.getInt("mana");
        maxMana = nbt.getInt("maxMana");
        if (mana - amount < 0){
            mana = 0;
        }
        else{
            mana -= amount;
        }
        nbt.putInt("mana", mana);
        syncMana(mana, (ServerPlayerEntity) player);
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
        syncMaxMana(maxMana, (ServerPlayerEntity) player);
    }
    public static int getPlayerMaxMana(IDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        return nbt.getInt("maxMana");
    }
    public static void setPlayerManaGenSpd(IDataSaver player, float amount){
        NbtCompound nbt = player.getPersistentData();
        manaGenSpd = amount;
        nbt.putFloat("manaGenSpd", manaGenSpd);
        syncManaGenSpd(manaGenSpd, (ServerPlayerEntity) player);
    }
    public static float getPlayerManaGenSpd(IDataSaver player){
        NbtCompound nbt = player.getPersistentData();
        return nbt.getFloat("manaGenSpd");
    }
    public static void syncMana(int mana, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new ManaSyncPayload(mana));
    }
    public static void syncMaxMana(int maxMana, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new MaxManaSyncPayload(maxMana));
    }
    public static void syncManaGenSpd(float manaGenSpd, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new ManaGenSpdSyncPayload(manaGenSpd));
    }
    public static void syncAll(int mana, int maxMana, float manaGenSpd, ServerPlayerEntity player){
        ServerPlayNetworking.send(player, new ManaSyncPayload(mana));
        ServerPlayNetworking.send(player, new MaxManaSyncPayload(maxMana));
        ServerPlayNetworking.send(player, new ManaGenSpdSyncPayload(manaGenSpd));
    }
}
