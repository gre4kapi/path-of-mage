package com.gre4ka.spells;

import com.gre4ka.network.payload.SpeechPayload;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class Spells {
    public static void spell(SpeechPayload payload, ServerPlayNetworking.Context context){
        if (payload.speech().equals("свинья")) {
            if ((((IDataSaver) context.player()).getPersistentData().getInt("mana")) >= 10) {
                EntityType.PIG.spawn((ServerWorld) context.player().getWorld(), context.player().getBlockPos(), SpawnReason.TRIGGERED);
                PlayerData.removePlayerMana(((IDataSaver) context.player()), 10);
                context.player().sendMessage(Text.of("Mana: " + ((IDataSaver) context.player()).getPersistentData().getInt("mana")), true);
            }
            else {
                context.player().sendMessage(Text.of("Not enough Mana"), true);
            }
        }
        else if (payload.speech().equals("мана")) {
            PlayerData.addPlayerMana(((IDataSaver) context.player()), 100);
            context.player().sendMessage(Text.of("Mana: " + ((IDataSaver) context.player()).getPersistentData().getInt("mana")), true);
        }
    }
}
