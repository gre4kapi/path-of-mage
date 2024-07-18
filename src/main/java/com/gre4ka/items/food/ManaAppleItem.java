package com.gre4ka.items.food;

import com.gre4ka.util.IDataSaver;
import com.gre4ka.util.PlayerData;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ManaAppleItem extends Item {

    public ManaAppleItem() {
        super(new Item.Settings().food(new FoodComponent.Builder().alwaysEdible().nutrition(10).saturationModifier(1f).build()));
    }
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user){
        if (!world.isClient && user.isPlayer()){
            PlayerData.addPlayerMana((IDataSaver) user, 1000);
        }
        return Items.APPLE.finishUsing(stack, world, user);
    }
}
