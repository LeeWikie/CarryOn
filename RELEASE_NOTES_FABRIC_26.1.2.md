# Carry On Fabric 26.1.2 Community Build

This is an unofficial community build of Carry On for Minecraft 26.1.2 on Fabric.

## Community.2 Hotfix

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

Verified:

- `:Fabric:build` passes.
- The runtime jar contains `fabric.mod.json`, `CarryOnFabricMod`, and `CarryOnFabricClientMod`.
- Packaged metadata declares Minecraft `>=26.1.2 <26.2` and Java `>=25`.
- Dedicated server startup reaches `Done` and stops cleanly.
- Client startup under `xvfb-run` reaches resource atlas creation with Carry On loaded and does not reproduce the pre-fix `Inventory.<clinit>` / `Components not bound yet` crash.

Not covered in this environment:

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
