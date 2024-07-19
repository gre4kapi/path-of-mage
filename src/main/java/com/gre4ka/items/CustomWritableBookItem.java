package com.gre4ka.items;

import net.minecraft.item.Item;
import net.minecraft.item.WritableBookItem;

public class CustomWritableBookItem extends WritableBookItem {
    public CustomWritableBookItem() {
        super(new Item.Settings().maxDamage(20));
    }
}
