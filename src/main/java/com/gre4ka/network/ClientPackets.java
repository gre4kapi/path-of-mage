package com.gre4ka.network;

import com.gre4ka.network.payload.SpeechPayload;
import com.gre4ka.spells.Spells;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ClientPackets {
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SpeechPayload.ID, (payload, context) -> {context.server().execute(() ->{
            Spells.spell(payload, context);
            });
        });
        /*ServerPlayNetworking.registerGlobalReceiver(.ID, (payload, context) -> {context.server().execute(() ->{
            Spells.spell(payload, context);
        });
        });*/
    }
}
