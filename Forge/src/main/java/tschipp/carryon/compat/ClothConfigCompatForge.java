package tschipp.carryon.compat;

import net.minecraft.client.gui.screens.Screen;
import tschipp.carryon.config.BuiltConfig;
import tschipp.carryon.config.forge.ConfigLoaderImpl;

public class ClothConfigCompatForge {

    public static Screen createScreen(BuiltConfig client, BuiltConfig common, Screen screen) {

        return ClothConfigCompat.getConfigScreen(client, common, screen, () -> {
            ConfigLoaderImpl.saveConfig(client); ConfigLoaderImpl.saveConfig(common);});
    }
}



