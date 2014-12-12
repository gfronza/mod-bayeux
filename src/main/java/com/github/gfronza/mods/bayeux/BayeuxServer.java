package com.github.gfronza.mods.bayeux;

import org.vertx.java.core.Vertx;
import org.vertx.java.core.http.HttpServer;

import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;

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
	 * Adds an extension to the bayeux server. Extensions allows you to 
	 * intercept incoming and outgoing messages as they pass in and out.
	 * This lets you mofidy the content of them for whatever purpose.
	 * @param extension to be added.
	 * @return BayeuxExtension the extension instance.
	 */
	public BayeuxExtension addExtension(BayeuxExtension extension);
	
	/**
	 * Removes the given extension if exists.
	 * @param extension to be removed.
	 */
	public void removeExtension(BayeuxExtension extension);
}
