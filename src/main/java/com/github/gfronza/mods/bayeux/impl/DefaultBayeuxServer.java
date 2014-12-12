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
	
	public DefaultBayeuxServer(JsonObject config) {
		// TODO
	}

	@Override
	public BayeuxServer attach(final HttpServer httpServer, final Vertx vertx) {
		// TODO:
		return this;
	}

	@Override
	public BayeuxExtension addExtension(BayeuxExtension extension) {
		// TODO:
		return extension;
	}

	@Override
	public void removeExtension(BayeuxExtension extension) {
		// TODO:
	}

}
