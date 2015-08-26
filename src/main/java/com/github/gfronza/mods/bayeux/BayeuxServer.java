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
package com.github.gfronza.mods.bayeux;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;
import com.github.gfronza.mods.bayeux.impl.protocol.Channel;

/**
 * Definition of a Bayeux server.
 * @author Germano
 *
 */
public interface BayeuxServer {

	/**
	 * Attachs an HttpServer and Vertx instance.
	 * @param httpServer to be attached.
	 * @return this
	 */
	public BayeuxServer attach(final HttpServer httpServer, final Vertx vertx);

	/**
	 * Adds an incoming extension to the bayeux server. Extensions allows you to
	 * intercept incoming or outgoing messages as they pass in and out.
	 * This lets you mofidy the content of them for whatever purpose.
	 * @param extension to be added.
	 * @return BayeuxExtension the extension instance.
	 */
	public BayeuxExtension addIncomingExtension(BayeuxExtension extension);

	/**
	 * Adds an outgoing extension to the bayeux server. Extensions allows you to
	 * intercept incoming or outgoing messages as they pass in and out.
	 * This lets you mofidy the content of them for whatever purpose.
	 * @param extension to be added.
	 * @return BayeuxExtension the extension instance.
	 */
	public BayeuxExtension addOutgoingExtension(BayeuxExtension extension);

	/**
	 * Removes the given incoming extension if exists.
	 * @param extension to be removed.
	 */
	public void removeIncomingExtension(BayeuxExtension extension);

	/**
	 * Removes the given outgoing extension if exists.
	 * @param extension to be removed.
	 */
	public void removeOutgoingExtension(BayeuxExtension extension);

	/**
	 * Creates a channel with the given name (if absent).
	 * @param channelName name of the channel.
	 * @return Channel created.
	 */
	public Channel createChannel(String channelName);
}
