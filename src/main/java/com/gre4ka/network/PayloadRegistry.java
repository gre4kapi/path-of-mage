package com.gre4ka.network;

import com.gre4ka.network.payload.*;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public class PayloadRegistry {
    public static void registerPacketPayloads(){
        PayloadTypeRegistry.playS2C().register(ManaSyncPayload.ID, ManaSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(MaxManaSyncPayload.ID, MaxManaSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ManaGenSpdSyncPayload.ID, ManaGenSpdSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(TrueVisionSyncPayload.ID, TrueVisionSyncPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(SpeechPayload.ID, SpeechPayload.CODEC);
    }
}
