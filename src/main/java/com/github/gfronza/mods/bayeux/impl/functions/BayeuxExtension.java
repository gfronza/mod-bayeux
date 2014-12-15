package com.github.gfronza.mods.bayeux.impl.functions;

import org.vertx.java.core.http.HttpServerRequest;

import com.github.gfronza.mods.bayeux.impl.Message;

/**
 * Represents an incoming or outgoing extension.
 * Extensions allows you to intercept incoming or outgoing messages as they pass in and out.
 * This lets you mofidy the content of them for whatever purpose.<br/><br/>
 * 
 * PS: you can use this as a lambda expression.
 * @author Germano
 *
 */
@FunctionalInterface
public interface BayeuxExtension  {

	public void handle(Message message, HttpServerRequest request);
	
}
