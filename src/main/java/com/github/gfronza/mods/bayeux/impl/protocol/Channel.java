package com.github.gfronza.mods.bayeux.impl.protocol;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.gfronza.mods.bayeux.impl.functions.ChannelHandler;
import com.github.gfronza.mods.bayeux.impl.functions.ChannelSubscriptionHandler;

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
	private final Set<Session> subscribers;
	private final List<ChannelHandler> listeners; 

	public Channel(String id) {
		this.subscribers = new HashSet<>();
		this.listeners = new ArrayList<>();
		
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	/**
	 * Subscribes a client (session) to this channel.<br/>
	 * Listeners of this channel will be notified of the subscription event.<br/><br/>
	 * 
	 * TODO: I need to study sync listeners are really a good idea here.
	 * Maybe I could use vertx event bus for this purpose.
	 * 
	 * @param session
	 * @return
	 */
	public boolean subscribe(Session session) {
        boolean added = this.subscribers.add(session);
        
		if (added) {
        	listeners.stream()
			   		 .filter(c -> c instanceof ChannelSubscriptionHandler)
			   		 .forEach(c -> ((ChannelSubscriptionHandler)c).subscribed(session, this));
        }
        
        return added;
    }
	
	/**
	 * Unsubscribes a client (session) from this channel.<br/>
	 * Listeners of this channel will be notified of the subscription event.<br/><br/>
	 * 
	 * TODO: I need to study sync listeners are really a good idea here.
	 * Maybe I could use vertx event bus for this purpose.
	 * 
	 * @param session
	 * @return
	 */
	public boolean unsubscribe(Session session) {
        boolean removed = this.subscribers.remove(session);
        
		if (removed) {
        	listeners.stream()
			   		 .filter(c -> c instanceof ChannelSubscriptionHandler)
			   		 .forEach(c -> ((ChannelSubscriptionHandler)c).unsubscribed(session, this));
        }
        
        return removed;
    }
}
