package com.github.gfronza.mods.bayeux.impl.functions;

import org.vertx.java.core.http.HttpServerRequest;
import com.github.gfronza.mods.bayeux.impl.Message;

/**
 * Definition of an extension.
 * With this extension interface you can handle both incoming and outgoing messages.
 * The methods contains default implementations, so you don't have to implement a method
 * that is not interesting to you.
 * @author Germano
 *
 */
public interface BayeuxExtension {

	/**
	 * Intercepts any incoming messages.
	 * @param message
	 * @param request
	 */
	default public void incoming(Message message, HttpServerRequest request) {
		
	}
	
	/**
	 * Intercepts any outgoing messages.
	 * @param message
	 * @param request
	 */
	default public void outgoing(Message message, HttpServerRequest request) {
		
	}
	
}
