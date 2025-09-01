package tschipp.carryon.carry;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jetbrains.annotations.Nullable;
import tschipp.carryon.common.carry.CarryOnData;

public class CarryOnDataSyncHandler implements AttachmentSyncHandler<CarryOnData> {
    @Override
    public void write(RegistryFriendlyByteBuf registryFriendlyByteBuf, CarryOnData carryOnData, boolean b) {
        CarryOnData.STREAM_CODEC.encode(registryFriendlyByteBuf, carryOnData);
    }

    @Override
    public @Nullable CarryOnData read(IAttachmentHolder iAttachmentHolder, RegistryFriendlyByteBuf registryFriendlyByteBuf, @Nullable CarryOnData carryOnData) {
        return CarryOnData.STREAM_CODEC.decode(registryFriendlyByteBuf);
    }

    @Override
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer to) {
        if (to.connection == null)
            return false;
        return AttachmentSyncHandler.super.sendToPlayer(holder, to);
    }
}
