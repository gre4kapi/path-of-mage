package com.gre4ka.server;

import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class ServerEventHandler {
    static int timer = 0;

    public static void register() {
        //ServerTickEvents.START_SERVER_TICK.register(ServerEventHandler::serverTickEvent);
        ServerPlayConnectionEvents.JOIN.register(ServerEventHandler::joinEvent);
    }

    private static void joinEvent(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer minecraftServer) {
        IDataSaver player = (IDataSaver) serverPlayNetworkHandler.player;
        NbtCompound nbt = player.getPersistentData();
        if (!nbt.contains("firstlog")){
            PlayerData.setPlayerMaxMana(player, 10);
            nbt.putBoolean("firstlog", true);
        }

    }

    private static void serverTickEvent(MinecraftServer minecraftServer) {
        if ((timer % 600) == 0){

            timer = 0;
        }
        timer++;
    }
}
