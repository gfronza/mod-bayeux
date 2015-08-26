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
package com.github.gfronza.mods.bayeux.impl.functions;

import io.vertx.core.http.HttpServerRequest;

import com.github.gfronza.mods.bayeux.impl.protocol.Message;

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
