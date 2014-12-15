package com.github.gfronza.mods.bayeux.impl;

import java.util.List;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.json.JsonObject;

import com.github.gfronza.mods.bayeux.BayeuxServer;
import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;

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

	/**
	 * Creates the default bayeux server overriding the default configs.
	 * @param config
	 */
	public DefaultBayeuxServer(JsonObject configs) {
		configurations = Configurations.getDefault().extend(configs);
	}

	/**
	 * Creates the default bayeux server using the default configs.
	 * @param config
	 */
	public DefaultBayeuxServer() {
		configurations = Configurations.getDefault();
	}

	@Override
	public BayeuxServer attach(final HttpServer httpServer, final Vertx vertx) {
		this.httpServer = httpServer;
		this.vertx = vertx;
		return this;
	}

	@Override
	public BayeuxExtension addIncomingExtension(BayeuxExtension extension) {
		incomingExtensions.add(extension);
		return extension;
	}

	@Override
	public BayeuxExtension addOutgoingExtension(BayeuxExtension extension) {
		outgoingExtensions.add(extension);
		return extension;
	}

	@Override
	public void removeIncomingExtension(BayeuxExtension extension) {
		incomingExtensions.remove(extension);
	}

	@Override
	public void removeOutgoingExtension(BayeuxExtension extension) {
		outgoingExtensions.remove(extension);		
	}
}
