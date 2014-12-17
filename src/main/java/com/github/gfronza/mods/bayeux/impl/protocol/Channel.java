package com.github.gfronza.mods.bayeux.impl.protocol;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a server channel where clients subscribe to.
 * @author Germano
 *
 */
public class Channel {

	// default channels.
	public static final String SERVICE = "/service";
    public static final String META = "/meta";
    public final static String META_HANDSHAKE = META + "/handshake";
    public final static String META_CONNECT = META + "/connect";
    public final static String META_SUBSCRIBE = META + "/subscribe";
    public final static String META_UNSUBSCRIBE = META + "/unsubscribe";
    public final static String META_DISCONNECT = META + "/disconnect";
	
	private String id;
	private final Set<Session> subscribers = new HashSet<>();

	public Channel(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public boolean subscribe(Session session) {
        return this.subscribers.add(session);
        // TODO: obviously incomplete.
    }
	
	public boolean unsubscribe(Session session) {
        return this.subscribers.remove(session);
        // TODO: obviously incomplete.
    }
}
