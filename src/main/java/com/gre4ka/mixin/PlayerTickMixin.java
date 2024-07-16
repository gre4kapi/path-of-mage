package com.gre4ka.mixin;

import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerTickMixin {
	ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
	int timer = 0;
	@Inject(method = "tick", at = @At("HEAD"))
	protected void tick(CallbackInfo ci) {
		if (timer % 600 == 0) {
			PlayerData.addPlayerMana(((IDataSaver) player), 1);
		}
		timer++;
	}
}