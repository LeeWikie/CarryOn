package tschipp.carryon.carry;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import tschipp.carryon.common.carry.CarryOnData;
import tschipp.carryon.common.carry.CarryOnDataManager;

@AutoRegisterCapability
public interface ICarryOnDataCapability  {

    CarryOnData getCarryData();

    void setCarryData(CarryOnData data);

}
