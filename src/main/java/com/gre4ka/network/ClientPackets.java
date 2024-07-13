package com.gre4ka.network;

import com.gre4ka.PathOfMage;
import com.gre4ka.network.payload.SpeechPayload;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.ManaData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class ClientPackets {
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(SpeechPayload.ID, (payload, context) -> {context.server().execute(() ->{
            if (payload.speech().equals("свинья")) {
                if ((((IDataSaver) context.player()).getPersistentData().getInt("mana")) >= 10) {
                    EntityType.PIG.spawn((ServerWorld) context.player().getWorld(), context.player().getBlockPos(), SpawnReason.TRIGGERED);
                    ManaData.removeMana(((IDataSaver) context.player()), 10);
                    context.player().sendMessage(Text.of("Mana: " + ((IDataSaver) context.player()).getPersistentData().getInt("mana")), true);
                }
                else {
                    context.player().sendMessage(Text.of("Not enough Mana"), true);
                }
            }
            else if (payload.speech().equals("мана")) {
                ManaData.addMana(((IDataSaver) context.player()), 5);
                context.player().sendMessage(Text.of("Mana: " + ((IDataSaver) context.player()).getPersistentData().getInt("mana")), true);
            }
            });
        });
    }
}
