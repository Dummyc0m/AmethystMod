package com.dummyc0m.forgemod.amethyst.core.network.packet.client;

/**
 * Created by Dummyc0m on 11/26/15.
 */

import com.dummyc0m.forgemod.amethyst.core.network.packet.AbstractMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * This is just a convenience class that will prevent the server-side message handling
 * method from appearing in our client message handler classes.
 */
public abstract class AbstractClientMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {
    // implementing a final version of the server message handler both prevents it from
// appearing automatically and prevents us from ever accidentally overriding it
    public final IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx) {
        return null;
    }
}
