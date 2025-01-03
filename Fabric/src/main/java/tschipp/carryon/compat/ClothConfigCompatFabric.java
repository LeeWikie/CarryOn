package tschipp.carryon.compat;

import net.minecraft.client.gui.screens.Screen;
import tschipp.carryon.config.BuiltConfig;
import tschipp.carryon.config.fabric.ConfigLoaderImpl;

public class ClothConfigCompatFabric {

    public static Screen createScreen(BuiltConfig client, BuiltConfig common, Screen screen) {

        return ClothConfigCompat.getConfigScreen(client, common, screen, ConfigLoaderImpl::saveConfigs);
    }
}



