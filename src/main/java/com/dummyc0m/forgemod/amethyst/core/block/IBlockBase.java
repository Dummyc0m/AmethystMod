package com.dummyc0m.forgemod.amethyst.core.block;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public interface IBlockBase {
    void setFlammable(boolean flammability);

    String getBlockName();

    String[] getSubNames();

    boolean isTooltip();
}
