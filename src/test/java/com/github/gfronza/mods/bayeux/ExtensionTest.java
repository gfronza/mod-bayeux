/**
 * 
 */
package com.github.gfronza.mods.bayeux;

import org.junit.Test;
import org.vertx.java.core.http.HttpServerRequest;

import com.github.gfronza.mods.bayeux.impl.DefaultBayeuxServer;
import com.github.gfronza.mods.bayeux.impl.Message;
import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;

/**
 * Test the usage of an Extension.
 * @author Germano
 *
 */
public class ExtensionTest {

	@Test
	public void testAddIncomingExtension() {
		// server instance, not important right now.
		BayeuxServer server = new DefaultBayeuxServer();
		
		// add the extension (incoming only).
		server.addIncomingExtension((message, request) -> {
			// do something with the message.
		});
	}
	
	@Test
	public void testAddOutgoingExtension() {
		// server instance, not important right now.
		BayeuxServer server = new DefaultBayeuxServer();
		
		// add the extension (incoming only).
		server.addOutgoingExtension((message, request) -> {
			// do something with the message.
		});
	}

	@Test
	public void testAddIncomingAsAnonymousClassExtension() {
		// server instance, not important right now.
		BayeuxServer server = new DefaultBayeuxServer();
		
		// add the extension (incoming only).
		server.addIncomingExtension(new BayeuxExtension() {
			@Override
			public void handle(Message message, HttpServerRequest request) {
				// do something with the message.
			}
		});
	}
}
