# Carry On Fabric 26.1.2 Community Build

This is an unofficial community build of Carry On for Minecraft 26.1.2 on Fabric.

## Community.3 Hotfix

- Fabric attachment auto-sync for CarryOnData was disabled. Attachments remain persistent storage only; they are no longer synced as player attachments over the network.
- Carry-data client updates (including join/respawn tracking resync) now use explicit `sync_carry_data` packets instead of relying on Fabric's attachment sync system.
- CarryOnData/NBT state is snapshotted at serialization, attachment, and packet boundaries to prevent sharing mutable `CompoundTag` state across the server tick and Netty encode threads.
- community.2 only increased the attachment sync payload allowance and guarded `activeScript` serialization. It did not remove the underlying auto-sync crash path. community.3 supersedes community.2 for this specific issue.
- SHA-256: `c4ae79984f98864a77960fd9126c3e92158454dd00d6973d10b74a2ba4cfe14a`.

## Community.2 Hotfix (superseded by community.3)

- Increased the Fabric attachment sync payload allowance for carried data to reduce disconnects when carried blocks or entities contain large NBT.
- Prevented failed `activeScript` serialization from aborting attachment sync; the failed script field is logged and omitted from the sync payload instead.
- Hotfix jar SHA-256: `1ba56b29597d5b0b10f96027fe0d59da00b0e6c0245de49c92b795576f2537f9`.

## Artifact

- Runtime jar: `carryon-fabric-26.1.2-2.9.1.jar`
- Minecraft: `26.1.2`
- Fabric Loader: `0.19.2`
- Fabric API: `0.150.0+26.1.2`
- Java: `25`

## Verification

Build command:

```bash
JAVA_HOME=/usr/lib/jvm/java-25-openjdk ./gradlew -PincludeForge=false -PincludeNeoForge=false :Fabric:build --stacktrace --console=plain
```

Verified for community.3:

- `:Fabric:build` passes for community.3.
- `git diff --check` passes for community.3.

Not covered / not rerun for community.3 in this environment:

- Package jar-content verification (`fabric.mod.json`, `CarryOnFabricMod`, `CarryOnFabricClientMod`).
- Packaged metadata (Minecraft `>=26.1.2 <26.2`, Java `>=25`).
- Dedicated server startup and clean shutdown.
- Client startup (resource atlas creation, pre-fix crash regression).
- Interactive block carry/drop gameplay.
- Interactive entity carry/drop gameplay.
- Death/respawn/reconnect while carrying.
- CarryOn-specific carried-state save/reload after an in-world carry action.

## Important Notes

- This is not an official upstream release.
- Forge and NeoForge sources are preserved but excluded from this Fabric-only release build.
- Optional ModMenu / Cloth Config GUI compatibility is disabled in this build because the available dependencies do not compile cleanly against the non-obfuscated Minecraft 26.1.2 Fabric classpath.
- Minecraft 26.1+ uses Fabric's non-obfuscated build flow. Use `:Fabric:jar` or `:Fabric:build`; do not use `remapJar` as the release gate for this version line.

## Main Porting Changes

- Updated Gradle wrapper to 9.5.1 with distribution checksum.
- Switched Fabric Loom plugin ID to `net.fabricmc.fabric-loom`.
- Updated Fabric target metadata and dependency versions for Minecraft 26.1.2.
- Adjusted Fabric networking/keymapping APIs for 26.1.2.
- Compiled Common sources directly under Fabric's 26.1.2 classpath for the Fabric release build.
- Hardened carry-state serialization and placement/drop paths against invalid carried data.
- Replaced eager static `ItemStack` initialization in `InventoryMixin` with lazy construction to avoid client startup crashes before item components are bound.
