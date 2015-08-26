/*
 * Copyright (c) 2014 Germano Fronza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.gfronza.mods.bayeux;

import io.vertx.core.http.HttpServerRequest;

import org.junit.Test;

import com.github.gfronza.mods.bayeux.impl.DefaultBayeuxServer;
import com.github.gfronza.mods.bayeux.impl.functions.BayeuxExtension;
import com.github.gfronza.mods.bayeux.impl.protocol.Message;

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
