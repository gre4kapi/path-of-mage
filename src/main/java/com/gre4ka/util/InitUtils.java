package com.gre4ka.util;

import com.gre4ka.PathOfMage;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class InitUtils {
    public static <I extends Item> I setup(I item, String name) {
        ModRegistry.registerIdent(item, Identifier.of(PathOfMage.MOD_ID, name));
        return item;
    }

    public static <B extends Block> B setup(B block, String name) {
        ModRegistry.registerIdent(block, Identifier.of(PathOfMage.MOD_ID, name));
        return block;
    }

    public static SoundEvent setup(String name) {
        Identifier identifier = Identifier.of(PathOfMage.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static boolean isDatagenRunning() {
        return System.getProperty("fabric-api.datagen") != null;
    }

    private InitUtils() {/* No instantiation. */}
}