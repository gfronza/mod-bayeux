package com.github.gfronza.mods.bayeux.impl.functions;

import com.github.gfronza.mods.bayeux.impl.protocol.Channel;
import com.github.gfronza.mods.bayeux.impl.protocol.Session;

/**
 * Used to notify channel subscriptions to registered listeners. 
 * @author Germano
 *
 */
public interface ChannelSubscriptionHandler extends ChannelHandler {

	/**
	 * Handle subscribe events.
	 * PS: default implementation is empty.
	 * @param session
	 * @param channel
	 */
	default public void subscribed(Session session, Channel channel) {
		
	}
	
	/**
	 * Handle unsubscribe events.
	 * PS: default implementation is empty.
	 * @param session
	 * @param channel
	 */
	default public void unsubscribed(Session session, Channel channel) {
		
	}
	
}
