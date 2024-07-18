package com.gre4ka.network.payload;

import com.gre4ka.PathOfMage;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record TrueVisionSyncPayload(Boolean trueVision) implements CustomPayload {
    public static final Id<TrueVisionSyncPayload> ID = new Id<>(Identifier.of(PathOfMage.MOD_ID, "truevisionsync"));
    public static final PacketCodec<RegistryByteBuf, TrueVisionSyncPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, TrueVisionSyncPayload::trueVision,
            TrueVisionSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}