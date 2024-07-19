package com.gre4ka.spells;

import com.gre4ka.PathOfMage;
import com.gre4ka.network.payload.SpeechPayload;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class Spells {
    private static HashMap<String, Runnable> spellsMap = new HashMap<>();
    public static void spell(SpeechPayload payload, ServerPlayNetworking.Context context){
        ServerPlayerEntity player = context.player();
        ServerWorld world = player.getServerWorld();
        int mana = ((IDataSaver) player).getPersistentData().getInt("mana");

        spellsMap.put("боров других жирнее мобов", () -> SpawnHoglinSpell(context));
        spellsMap.put("доспех огня", () -> FireResistanceSpell(context));
        spellsMap.put("огонь", () -> FireballSpell(context, player, world));

        Runnable spell = spellsMap.get(payload.speech());
        if (spell != null){
            spell.run();
        }
    }

    private static void SpawnHoglinSpell(ServerPlayNetworking.Context context) {
        EntityType.HOGLIN.spawn((ServerWorld) context.player().getWorld(), context.player().getBlockPos(), SpawnReason.TRIGGERED);
        PlayerData.removePlayerMana(context.player(), 100);
    }

    private static void FireResistanceSpell(ServerPlayNetworking.Context context) {
        context.player().addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 60 * 20, 0));
        PlayerData.removePlayerMana(context.player(), 100);
    }
    private static void FireballSpell(ServerPlayNetworking.Context context, ServerPlayerEntity player, ServerWorld world) {
        Vec3d vec3d = player.getRotationVec(1.0f);
        FireballEntity fireballEntity = new FireballEntity(world, player, vec3d, 200);
        fireballEntity.setPosition(player.getX() + vec3d.x * 4.0, player.getBodyY(0.5) + 0.5, fireballEntity.getZ() + vec3d.z * 4.0);
        fireballEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 2.5f, 1.0f);
        fireballEntity.setNoGravity(true);
        world.spawnEntity(fireballEntity);
        fireballEntity.checkDespawn();
        PlayerData.removePlayerMana(context.player(), 100);
    }

}
