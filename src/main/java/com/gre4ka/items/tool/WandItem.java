package com.gre4ka.items.tool;

import com.gre4ka.PathOfMage;
import com.gre4ka.util.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class WandItem extends BowItem {
    public static final Predicate<ItemStack> NULL_PROJECTILE = (stack) -> stack.isOf(ModRegistry.WAND);
    public WandItem() {
        super(new Item.Settings().maxDamage(20));
    }
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        user.playSound(SoundEvents.ITEM_BOTTLE_FILL);
    }
    @Override
    public Predicate<ItemStack> getProjectiles() {
        return NULL_PROJECTILE;
    }
}
