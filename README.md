# Carry On [![](http://cf.way2muchnoise.eu/carry-on.svg)](https://minecraft.curseforge.com/projects/carry-on) [![](http://cf.way2muchnoise.eu/versions/carry-on.svg)](https://minecraft.curseforge.com/projects/carry-on)

## Unofficial Fabric 26.1.2 Community Build

This fork publishes an unofficial Fabric build for Minecraft 26.1.2 while the upstream project catches up. It is intended for players and pack authors who need a working Fabric 26.1.x build now.

- **Release:** [fabric-26.1.2-community.2](https://github.com/LeeWikie/CarryOn/releases/tag/fabric-26.1.2-community.2)
- **Runtime jar:** `carryon-fabric-26.1.2-2.9.1.jar`
- **Target:** Minecraft `26.1.2`, Fabric Loader `0.19.2`, Fabric API `0.150.0+26.1.2`, Java `25`
- **Branch:** [`fabric-26x-port`](https://github.com/LeeWikie/CarryOn/tree/fabric-26x-port)
- **Hotfix:** `community.2` fixes `fabric:attachment_sync_v1` encode disconnects for large carried NBT / script serialization edge cases.

This is not an official upstream release. Original project, authorship, and license remain with Carry On and its maintainers.

---

To use CarryOn in your projects, include this in your build.gradle:
```
repositories {
	maven {
		url "https://maven.blamejared.com/"
	}
}

dependencies {
	deobfCompile "tschipp.carryon:carryon-LOADER-MCVERSION:MODVERSION" 
}
```
Make sure to replace `LOADER`, `MCVERSION` and `MODVERSION` with the appropriate versions. One example would be `tschipp.carryon:carryon-forge-1.19.2:2.0.0.5`  

# Development

This project provides a Gradle project template that can compile mods for both Forge and Fabric using a common sourceset. This project does not require any third party libraries or dependencies.

## IntelliJ IDEA
This guide will show how to import the MultiLoader Template into IntelliJ IDEA. The setup process is roughly equivalent to setting up Forge and Fabric independently and should be very familiar to anyone who has worked with their MDKs.

1. Clone or download this repository to your computer.
2. Open the template's root folder as a new project in IDEA. This is the folder that contains this README file and the gradlew executable.
3. If your default JVM/JDK is not Java 17 you will encounter an error when opening the project. This error is fixed by going to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM`and changing the value to a valid Java 17 JVM. You will also need to set the Project SDK to Java 17. This can be done by going to `File > Project Structure > Project SDK`. Once both have been set open the Gradle tab in IDEA and click the refresh button to reload the project.
4. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Common > Tasks > vanilla gradle > decompile`. Run this task to decompile Minecraft.
5. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Forge > Tasks > forgegradle runs > genIntellijRuns`. Run this task to set up run configurations for Forge.
6. Open your Run/Debug Configurations. Under the Application category there should now be options to run Forge and Fabric projects. Select one of the client options and try to run it.
7. Assuming you were able to run the game in step 7 your workspace should now be set up.

### Eclipse
While it is possible to use this template in Eclipse it is not recommended. During the development of this template multiple critical bugs and quirks related to Eclipse were found at nearly every level of the required build tools. While we continue to work with these tools to report and resolve issues support for projects like these are not there yet. For now Eclipse is considered unsupported by this project. The development cycle for build tools is notoriously slow so there are no ETAs available.
