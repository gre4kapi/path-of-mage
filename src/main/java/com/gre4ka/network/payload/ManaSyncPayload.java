package com.gre4ka.network.payload;

import com.gre4ka.PathOfMage;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ManaSyncPayload(Integer mana) implements CustomPayload {
    public static final Id<ManaSyncPayload> ID = new Id<>(Identifier.of(PathOfMage.MOD_ID, "manasync"));
    public static final PacketCodec<RegistryByteBuf, ManaSyncPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, ManaSyncPayload::mana,
            ManaSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
