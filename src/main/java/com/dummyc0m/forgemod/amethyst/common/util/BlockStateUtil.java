package com.dummyc0m.forgemod.amethyst.common.util;

import net.minecraft.util.EnumFacing;

/**
 * Created by Dummyc0m on 1/17/2016.
 */
public class BlockStateUtil {

    public static int getMetaFromFacing(EnumFacing facing) {
        switch (facing) {
            case EAST:
                return 0;
            case WEST:
                return 1;
            case SOUTH:
                return 2;
            case NORTH:
                return 3;
            case DOWN:
                return 4;
            default:
                return 5;
        }
    }

    public static EnumFacing getFacingFromMeta(int meta) {
        switch (meta) {
            case 0:
                return EnumFacing.EAST;
            case 1:
                return EnumFacing.WEST;
            case 2:
                return EnumFacing.SOUTH;
            case 3:
                return EnumFacing.NORTH;
            case 4:
                return EnumFacing.DOWN;
            default:
                return EnumFacing.UP;
        }
    }

    private BlockStateUtil() {
    }
}
