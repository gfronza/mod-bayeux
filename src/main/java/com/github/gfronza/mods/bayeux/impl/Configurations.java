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
package com.github.gfronza.mods.bayeux.impl;

import java.util.ArrayList;
import java.util.List;

import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import com.github.gfronza.mods.bayeux.impl.protocol.transports.CallbackPollingTransport;
import com.github.gfronza.mods.bayeux.impl.protocol.transports.LongPollingTransport;

/**
 * Configurations for the Bayeux Server.
 * @author Germano
 *
 */
public class Configurations {
	
	public static final String MOUNT = "mount";
	public static final String TIMEOUT = "timeout";
	public static final String PING = "ping";
	public static final String TRANSPORTS = "transports";
	
	private final JsonObject configs;
	
	/**
	 * Hidden Constructor.
	 * Use the method getDefault instead.
	 */
	private Configurations() {
		this.configs = new JsonObject();
		this.configs.putString(MOUNT, "/rt");
		this.configs.putNumber(TIMEOUT, 60);
		this.configs.putNumber(PING, 60);
		this.configs.putArray(TRANSPORTS, new JsonArray()
			.add(CallbackPollingTransport.class.getName())
			.add(LongPollingTransport.class.getName())
		);
	}
	
	/**
	 * Returns the default configuratuins.
	 * @return
	 */
	public static Configurations getDefault() {
		return new Configurations();
	}
	
	/**
	 * Extends the default configurations.
	 * @param configs to be merged into the defaults.
	 */
	public Configurations extend(JsonObject configs) {
		configs.mergeIn(this.configs);
		return this;
	}
	
	/**
	 * Returns the mount path (where the bayeux protocol will operate).
	 * @return
	 */
	public String getMount() {
		return this.configs.getString(MOUNT);
	}

	/**
	 * Returns the list of transports class names.
	 * @return
	 */
	public List<String> getTransports() {
		List<String> list = new ArrayList<>();
		
		this.configs.getArray(TRANSPORTS).forEach(o -> {
			list.add((String)o);
		});
		
		return list;
	}
}
