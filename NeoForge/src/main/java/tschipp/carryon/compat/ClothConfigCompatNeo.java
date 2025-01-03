package tschipp.carryon.compat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import tschipp.carryon.config.AnnotationData;
import tschipp.carryon.config.BuiltCategory;
import tschipp.carryon.config.BuiltConfig;
import tschipp.carryon.config.neoforge.ConfigLoaderImpl;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public record ClothConfigCompatNeo(BuiltConfig client, BuiltConfig common) implements IConfigScreenFactory {

    @Override
    public Screen createScreen(ModContainer modContainer, Screen screen) {
        return ClothConfigCompat.getConfigScreen(client, common, screen, () -> {ConfigLoaderImpl.saveConfig(client); ConfigLoaderImpl.saveConfig(common);});
    }
}



