package com.gre4ka.network;

import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.SpeechPayload;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.ManaData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class ServerPackets {
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(ManaSyncPayload.ID, (payload, context) -> {context.client().execute(() ->{
            ((IDataSaver) context.player()).getPersistentData().putInt("mana", payload.mana());
        });
        });
    }
}