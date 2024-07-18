package com.gre4ka.network;

import com.gre4ka.network.payload.ManaGenSpdSyncPayload;
import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.MaxManaSyncPayload;
import com.gre4ka.network.payload.TrueVisionSyncPayload;
import com.gre4ka.util.IDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ServerPackets {
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(ManaSyncPayload.ID, (payload, context) -> {context.client().execute(() ->{
            ((IDataSaver) context.player()).getPersistentData().putInt("mana", payload.mana());
        });
        });

        ClientPlayNetworking.registerGlobalReceiver(MaxManaSyncPayload.ID, (payload, context) -> {context.client().execute(() ->{
            ((IDataSaver) context.player()).getPersistentData().putInt("maxMana", payload.maxMana());
        });
        });
        ClientPlayNetworking.registerGlobalReceiver(ManaGenSpdSyncPayload.ID, (payload, context) -> {context.client().execute(() ->{
            ((IDataSaver) context.player()).getPersistentData().putFloat("manaGenSpd", payload.manaGenSpd());
        });
        });
        ClientPlayNetworking.registerGlobalReceiver(TrueVisionSyncPayload.ID, (payload, context) -> {context.client().execute(() ->{
            ((IDataSaver) context.player()).getPersistentData().putBoolean("trueVision", payload.trueVision());
        });
        });
    }
}