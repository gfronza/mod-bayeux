package com.github.gfronza.mods.bayeux.impl;

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
	
	/**
	 * Creates the default bayeux server overriding the default configs.
	 * @param config
	 */
	public DefaultBayeuxServer(JsonObject config) {
		// TODO
	}

	/**
	 * Creates the default bayeux server using the default configs.
	 * @param config
	 */
	public DefaultBayeuxServer() {
		// TODO
	}

	@Override
	public BayeuxServer attach(final HttpServer httpServer, final Vertx vertx) {
		// TODO:
		return this;
	}

	@Override
	public BayeuxExtension addIncomingExtension(BayeuxExtension extension) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BayeuxExtension addOutgoingExtension(BayeuxExtension extension) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeIncomingExtension(BayeuxExtension extension) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeOutgoingExtension(BayeuxExtension extension) {
		// TODO Auto-generated method stub		
	}
}
