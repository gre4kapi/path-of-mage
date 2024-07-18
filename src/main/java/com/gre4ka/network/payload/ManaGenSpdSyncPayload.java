package com.gre4ka.network.payload;

import com.gre4ka.PathOfMage;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ManaGenSpdSyncPayload(Float manaGenSpd) implements CustomPayload {
    public static final Id<ManaGenSpdSyncPayload> ID = new Id<>(Identifier.of(PathOfMage.MOD_ID, "managenspdsync"));
    public static final PacketCodec<RegistryByteBuf, ManaGenSpdSyncPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ManaGenSpdSyncPayload::manaGenSpd,
            ManaGenSpdSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}