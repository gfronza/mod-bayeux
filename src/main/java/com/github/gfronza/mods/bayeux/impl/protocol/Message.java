package com.github.gfronza.mods.bayeux.impl.protocol;

import java.util.List;

import org.vertx.java.core.http.HttpServerRequest;

import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;

/**
 * Represents a message of the Bayeux Protocol.
 * @author Germano
 *
 */
public class Message {

	// TODO
	
	public void pipeThrough(List<BayeuxExtension> extensions, HttpServerRequest request) {
		for (BayeuxExtension e : extensions) {
			e.handle(this, request);
		}
	}
}
