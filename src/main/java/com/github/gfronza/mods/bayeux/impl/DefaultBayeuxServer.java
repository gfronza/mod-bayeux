package com.github.gfronza.mods.bayeux.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

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
	
	private void initialize() {
		this.incomingExtensions = new ArrayList<>();
		this.outgoingExtensions = new ArrayList<>();
		
		this.sessions = new HashMap<>();
		this.channels = new HashMap<>();
		this.transports = new LinkedHashMap<>(); // order matters here.
	}
}
