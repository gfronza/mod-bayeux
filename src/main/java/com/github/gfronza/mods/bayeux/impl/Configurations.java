package com.github.gfronza.mods.bayeux.impl;

import org.vertx.java.core.json.JsonObject;

/**
 * Configurations for the Bayeux Server.
 * @author Germano
 *
 */
public class Configurations {

	public static final String MOUNT = "mount";
	public static final String TIMEOUT = "timeout";
	public static final String PING = "ping";
	
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
	
}
