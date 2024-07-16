package com.gre4ka.network.payload;

import com.gre4ka.PathOfMage;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MaxManaSyncPayload(Integer maxMana) implements CustomPayload {
    public static final Id<MaxManaSyncPayload> ID = new Id<>(Identifier.of(PathOfMage.MOD_ID, "maxmanasync"));
    public static final PacketCodec<RegistryByteBuf, MaxManaSyncPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, MaxManaSyncPayload::maxMana,
            MaxManaSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
