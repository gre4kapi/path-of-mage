package com.gre4ka.mixin;

import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@Mixin(PlayerEntity.class)
public abstract class PlayerFoodMixin {
    PlayerEntity player = (PlayerEntity) (Object) this;


    int timer = 0;
    @Inject(method = "eatFood", at = @At("HEAD"))
    protected void eatFood(World world, ItemStack stack, FoodComponent foodComponent, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient){
            //if(stack.getItem() instanceof)
        }
    }
}