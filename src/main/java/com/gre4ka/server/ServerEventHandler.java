package com.gre4ka.server;

import com.gre4ka.PathOfMage;
import com.gre4ka.blockentity.storage.ManaStorageBlockEntity;
import com.gre4ka.blocks.ManaStorageBlock;
import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.TrueVisionSyncPayload;
import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class ServerEventHandler {
    static int timer = 0;
    static float manaGenSpd;
    static int maxMana;

    public static void register() {
        ServerTickEvents.START_SERVER_TICK.register(ServerEventHandler::serverTickEvent);
        ServerPlayConnectionEvents.JOIN.register(ServerEventHandler::joinEvent);
        AttackBlockCallback.EVENT.register(ServerEventHandler::onLeftClickBlock);
    }

    private static void serverTickEvent(MinecraftServer minecraftServer) {
        timer++;
    }

    private static ActionResult onLeftClickBlock(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
        if(timer > 3) {
            if (!world.isClient()) {
                BlockState state = world.getBlockState(pos);
                if (state.getBlock() instanceof ManaStorageBlock) {
                    if (world.getBlockEntity(pos) instanceof ManaStorageBlockEntity blockEntity) {
                        if (player.getMainHandStack().isEmpty()) {
                            blockEntity.retrieveStorageMana(player, pos, state);
                            timer = 0;
                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return ActionResult.PASS;
    }

    private static void joinEvent(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
        IDataSaver player = (IDataSaver) serverPlayNetworkHandler.player;
        ServerPlayerEntity srvPlayer = serverPlayNetworkHandler.player;
        NbtCompound nbt = player.getPersistentData();
        if (!nbt.contains("firstlog")){
            int rnd = new Random().nextInt(100);
            if (rnd == 0) {
                manaGenSpd = 0.25f;
                maxMana = 1000;
            }
            else if (rnd < 5){
                manaGenSpd = 0.1f;
                maxMana = 500;
            }
            else if (rnd < 25) {
                manaGenSpd = 0.01f;
                maxMana = 50;
            }
            else if (rnd < 100){
                manaGenSpd = 0f;
                maxMana = 1;
            }
            PlayerData.setPlayerManaGenSpd(player, manaGenSpd);
            PlayerData.setPlayerMaxMana(player, maxMana);
            PathOfMage.LOGGER.info("ManaGenSpd: " + manaGenSpd + " MaxMana: " + maxMana);
            nbt.putBoolean("firstlog", true);
            nbt.putBoolean("trueVision", false);
            ServerPlayNetworking.send(srvPlayer, new TrueVisionSyncPayload(false));
        }
        ServerPlayNetworking.send(srvPlayer, new TrueVisionSyncPayload(true));
        int mana = player.getPersistentData().getInt("mana");
        int maxMana = player.getPersistentData().getInt("maxMana");
        float manaGenSpd = player.getPersistentData().getFloat("manaGenSpd");
        PlayerData.syncAll(mana, maxMana, manaGenSpd, serverPlayNetworkHandler.player);

    }
}
