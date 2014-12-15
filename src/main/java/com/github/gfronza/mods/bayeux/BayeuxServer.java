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
}
