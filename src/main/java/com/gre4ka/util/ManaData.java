package com.gre4ka.util;

import com.gre4ka.network.payload.ManaSyncPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class ManaData {
    public static int addMana(IDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if (mana + amount >= 100){
            mana = 100;
        }
        else{
            mana += amount;
        }
        nbt.putInt("mana", mana);
        //sync the data
        return mana;
    }

    public static int removeMana(IDataSaver player, int amount){
        NbtCompound nbt = player.getPersistentData();
        int mana = nbt.getInt("mana");
        if (mana - amount < 0){
            mana = 0;
        }
        else{
            mana -= amount;
        }
        nbt.putInt("mana", mana);
        //sync the data
        return mana;
    }
    public static void syncMana(int mana, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new ManaSyncPayload(mana));
    }
}
