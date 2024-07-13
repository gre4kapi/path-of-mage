package com.gre4ka.network.payload;

import com.gre4ka.PathOfMage;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SpeechPayload(String speech) implements CustomPayload {
    public static final Id<SpeechPayload> ID = new Id<>(Identifier.of(PathOfMage.MOD_ID, "speech"));
    public static final PacketCodec<RegistryByteBuf, SpeechPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, SpeechPayload::speech,
            SpeechPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
