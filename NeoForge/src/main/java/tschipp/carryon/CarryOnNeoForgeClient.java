package tschipp.carryon;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import tschipp.carryon.compat.ClothConfigCompatNeo;
import tschipp.carryon.config.BuiltConfig;
import tschipp.carryon.config.neoforge.ConfigLoaderImpl;
import tschipp.carryon.platform.Services;

import java.util.function.Supplier;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class CarryOnNeoForgeClient {

    public CarryOnNeoForgeClient(ModContainer container) {

        if(Services.PLATFORM.isModLoaded("cloth_config")) {

            BuiltConfig[] configs = ConfigLoaderImpl.CONFIGS.values().toArray(new BuiltConfig[0]);

            container.registerExtensionPoint(IConfigScreenFactory.class, (Supplier<IConfigScreenFactory>) () -> new ClothConfigCompatNeo(configs[1], configs[0]));
        }
    }

}
