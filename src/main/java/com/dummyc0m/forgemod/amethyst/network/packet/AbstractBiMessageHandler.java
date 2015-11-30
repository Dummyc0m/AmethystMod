package com.dummyc0m.forgemod.amethyst.network.packet;

/**
 * Created by Dummyc0m on 11/26/15.
 */

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * For messages which can be sent to either Side
 */
public abstract class AbstractBiMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {

}
