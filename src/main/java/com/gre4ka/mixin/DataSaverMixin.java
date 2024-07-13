package com.gre4ka.mixin;

import com.gre4ka.PathOfMage;
import com.gre4ka.util.IDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class DataSaverMixin implements IDataSaver {
	private NbtCompound persistentData;

	@Override
	public NbtCompound getPersistentData() {
		if(this.persistentData == null) {
			this.persistentData = new NbtCompound();
		}
		return persistentData;
	}

	@Inject(method = "writeNbt", at = @At("HEAD"))
	protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable info) {
		if(persistentData != null) {
			nbt.put(PathOfMage.MOD_ID + ".mana_data", persistentData);
		}
	}

	@Inject(method = "readNbt", at = @At("HEAD"))
	protected void injectReadMethod(NbtCompound nbt, CallbackInfo info) {
		if (nbt.contains(PathOfMage.MOD_ID + ".mana_data", 10)) {
			persistentData = nbt.getCompound(PathOfMage.MOD_ID + ".mana_data");
		}
	}
}