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
package com.github.gfronza.mods.bayeux.impl;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.gfronza.mods.bayeux.BayeuxServer;
import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;
import com.github.gfronza.mods.bayeux.impl.protocol.Channel;
import com.github.gfronza.mods.bayeux.impl.protocol.Session;
import com.github.gfronza.mods.bayeux.impl.protocol.Transport;

/**
 * Default implementation of the BayeuxServer.
 * @author Germano
 *
 */
public class DefaultBayeuxServer implements BayeuxServer {

	private HttpServer httpServer;
	private Vertx vertx;
	private Configurations configurations;

	private List<BayeuxExtension> incomingExtensions;
	private List<BayeuxExtension> outgoingExtensions;

	private Map<String, Session> sessions;
	private Map<String, Channel> channels;
	private Map<String, Transport> transports;

	// Later on I'll need to use a Scheduller in this class:
	// https://github.com/sendgridlabs/vertx-scheduler

	/**
	 * Creates the default bayeux server overriding the default configs.
	 * @param config
	 */
	public DefaultBayeuxServer(JsonObject configs) {
		this.configurations = Configurations.getDefault().extend(configs);
		this.initialize();
	}

	/**
	 * Creates the default bayeux server using the default configs.
	 * @param config
	 */
	public DefaultBayeuxServer() {
		this.configurations = Configurations.getDefault();
		this.initialize();
	}

	@Override
	public BayeuxServer attach(final HttpServer httpServer, final Vertx vertx) {
		this.httpServer = httpServer;
		this.vertx = vertx;

		registerRequestHandler();

		return this;
	}

	@Override
	public BayeuxExtension addIncomingExtension(BayeuxExtension extension) {
		this.incomingExtensions.add(extension);
		return extension;
	}

	@Override
	public BayeuxExtension addOutgoingExtension(BayeuxExtension extension) {
		this.outgoingExtensions.add(extension);
		return extension;
	}

	@Override
	public void removeIncomingExtension(BayeuxExtension extension) {
		this.incomingExtensions.remove(extension);
	}

	@Override
	public void removeOutgoingExtension(BayeuxExtension extension) {
		this.outgoingExtensions.remove(extension);
	}

	/**
	 * Creates a channel with the given name (if absent).
	 * @param channelName name of the channel.
	 * @return Channel created.
	 */
	@Override
	public Channel createChannel(String channelName) {
		Channel c = new Channel(channelName);
		return this.channels.putIfAbsent(channelName, c);
	}

	private void initialize() {
		this.incomingExtensions = new ArrayList<>();
		this.outgoingExtensions = new ArrayList<>();

		this.sessions = new HashMap<>();
		this.channels = new HashMap<>();
		this.transports = new LinkedHashMap<>(); // order matters here.

		createTransports();
		createMetaChannels();
	}

	private void createTransports() {
		for (String transportClass : this.configurations.getTransports()) {
			try {
				// create a new instance of the transport.
				Transport t = (Transport) Class.forName(transportClass).newInstance();

				// put it into the map.
				this.transports.put(t.getName(), t);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				Logger.getLogger("mod-bayeux.transports").log(
						Level.FINE, "Exception creating the transport: " + transportClass, e
				);
			}
		}
	}

	private void createMetaChannels() {
		this.createChannel(Channel.META_CONNECT);
		this.createChannel(Channel.META_DISCONNECT);
		this.createChannel(Channel.META_HANDSHAKE);
		this.createChannel(Channel.META_SUBSCRIBE);
		this.createChannel(Channel.META_UNSUBSCRIBE);
	}

	private void registerRequestHandler() {
	    Router router = Router.router(vertx);

	    router.route().handler(routingContext -> {
	        HttpServerRequest request = routingContext.request();
            Transport transport = getProperHttpTransport(request);

            if (transport != null) {
                transport.handle(request);
            }
            else {
                request.response().setStatusCode(400) // BAD REQUEST
                                  .setStatusMessage("Bayeux Transport Not Found")
                                  .end();
            }
	    });

	    httpServer.requestHandler(router::accept);
	}

	private Transport getProperHttpTransport(HttpServerRequest request) {
		for (Transport t : this.transports.values()) {
			if (t.accept(request)) {
				return t;
			}
		}

		return null;
	}
}
