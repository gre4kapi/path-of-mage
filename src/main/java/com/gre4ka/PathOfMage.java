package com.gre4ka;

import com.gre4ka.config.ConfigUI;
import com.gre4ka.network.ClientPackets;
import com.gre4ka.network.ServerPackets;
import com.gre4ka.network.payload.ManaSyncPayload;
import com.gre4ka.network.payload.MaxManaSyncPayload;
import com.gre4ka.network.payload.SpeechPayload;
import com.gre4ka.server.ServerEventHandler;
import com.gre4ka.util.ModRegistry;
import net.fabricmc.api.ModInitializer;
import eu.midnightdust.lib.config.MidnightConfig;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PathOfMage implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "magpath";

    public static final Logger LOGGER = LoggerFactory.getLogger("magpath");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Path of Mage mod is initializing...");
		MidnightConfig.init(MOD_ID, ConfigUI.class);

		PayloadTypeRegistry.playS2C().register(ManaSyncPayload.ID, ManaSyncPayload.CODEC);
		PayloadTypeRegistry.playS2C().register(MaxManaSyncPayload.ID, MaxManaSyncPayload.CODEC);

		PayloadTypeRegistry.playC2S().register(SpeechPayload.ID, SpeechPayload.CODEC);
		ClientPackets.registerC2SPackets();

		ServerEventHandler.register();

		ModRegistry.register();
	}
}