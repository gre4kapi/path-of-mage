package com.gre4ka.mixin;

import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerTickMixin {
	ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;


	int timer = 0;
	@Inject(method = "tick", at = @At("HEAD"))
	protected void tick(CallbackInfo ci) {
		float manaGenSpd = ((IDataSaver) player).getPersistentData().getFloat("manaGenSpd");
		int m = 0;
		if (manaGenSpd != 0f) {
			if (manaGenSpd == 0.01f) {
				m = 2000;
			} else if (manaGenSpd == 0.1f) {
				m = 200;
			} else if (manaGenSpd == 0.2f) {
				m = 100;
			}
			if (timer % m == 0) {
				PlayerData.addPlayerMana(((IDataSaver) player), 1);
			}
		}
		timer++;
	}
}