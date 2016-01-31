package com.dummyc0m.forgemod.amethyst.common;

import com.dummyc0m.forgemod.amethyst.common.energy.NetManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Dummyc0m on 1/10/2016.
 */
public class AMEvents {
    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            NetManager.INSTANCE.updateNetworks();
        }
    }
}
