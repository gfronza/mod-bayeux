/*
 * Copyright (c) 2014 Germano Fronza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
