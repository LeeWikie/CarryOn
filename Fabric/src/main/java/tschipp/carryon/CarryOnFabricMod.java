/*
 * GNU Lesser General Public License v3
 * Copyright (C) 2024 Tschipp
 * mrtschipp@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package tschipp.carryon;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import tschipp.carryon.common.carry.CarryOnData;
import tschipp.carryon.config.fabric.ConfigLoaderImpl;
import tschipp.carryon.events.CommonEvents;

import java.io.IOException;

public class CarryOnFabricMod implements ModInitializer {

    private static final int MAX_CARRY_SYNC_SIZE = 4 * 1024 * 1024;

    public static final AttachmentType<CarryOnData> CARRY_ON_DATA_ATTACHMENT_TYPE = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(Constants.MOD_ID, "carry_on_data"),
            builder -> builder
                    .initializer(() -> new CarryOnData(new CompoundTag()))
                    .persistent(CarryOnData.CODEC)
                    .syncWith(CarryOnData.STREAM_CODEC, (target, player) ->{
                        if (!(target instanceof ServerPlayer carryingPlayer) || player == null || player.connection == null)
                            return false;
                        // the isAlive check avoids us syncing attachment data about dead players. Which causes a disconnect
                        // carryingPlayer.tickCount > 0 avoids us syncing attachment data about players the instant they spawn.
                        // Which also causes a disconnect as the player entity may not be synced yet.
                        return carryingPlayer.isAlive() && !player.isRemoved() && carryingPlayer.tickCount > 0;
                    }, MAX_CARRY_SYNC_SIZE)



    );

    @Override
    public void onInitialize() {

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.
        CarryOnCommon.registerConfig();


        try {
            ConfigLoaderImpl.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CommonEvents.registerEvents();
        CarryOnCommon.registerServerPackets();
        CarryOnCommon.registerClientPackets(false);

    }
}
