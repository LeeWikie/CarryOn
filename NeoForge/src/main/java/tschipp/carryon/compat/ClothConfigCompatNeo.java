package tschipp.carryon.compat;

import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import tschipp.carryon.config.BuiltConfig;
import tschipp.carryon.config.neoforge.ConfigLoaderImpl;


public record ClothConfigCompatNeo(BuiltConfig client, BuiltConfig common) implements IConfigScreenFactory {

    @Override
    public Screen createScreen(ModContainer modContainer, Screen screen) {
        return ClothConfigCompat.getConfigScreen(client, common, screen, () -> {ConfigLoaderImpl.saveConfig(client); ConfigLoaderImpl.saveConfig(common);});
    }
}



