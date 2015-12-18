package com.dummyc0m.forgemod.amethyst.api;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public interface IBlockBase {
    void setFlammable(boolean flammability);

    String getBlockName();

    String[] getSubNames();

    void setTooltip(boolean showToolTip);

    boolean isTooltip();
}
