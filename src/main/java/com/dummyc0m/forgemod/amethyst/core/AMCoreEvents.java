package com.dummyc0m.forgemod.amethyst.core;

import com.dummyc0m.forgemod.amethyst.core.energy.NetManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Dummyc0m on 1/10/2016.
 */
public class AMCoreEvents {
    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            NetManager.INSTANCE.updateNetworks();
        }
    }
}
