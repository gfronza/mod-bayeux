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
package com.github.gfronza.mods.bayeux.impl.protocol;

import io.vertx.core.http.HttpServerRequest;

import java.util.List;

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
