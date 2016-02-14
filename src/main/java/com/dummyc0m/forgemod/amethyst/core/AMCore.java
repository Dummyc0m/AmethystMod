package com.dummyc0m.forgemod.amethyst.core;

import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Dummyc0m on 2/12/2016.
 */
public class AMCore {
    public static final AMCore INSTANCE = new AMCore();

    public void init() {
        AMCoreEvents events = new AMCoreEvents();
        MinecraftForge.EVENT_BUS.register(events);
    }
}
