package net.shiroumi.central.Events;

import net.shiroumi.central.CTServer;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LockdownEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private EventType type;
	private String    Message;

	public LockdownEvent(EventType type, String Message) {
		super();
		this.setType(type);
		this.setMessage(Message);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	// getter群

	public EventType getType() {
		return type;
	}

	public String getMessage() {
		return Message;
	}

	// setter群

	public void setType(EventType type) {
		this.type = type;
	}

	public void setMessage(String message) {
		Message = message;
		CTServer.setLockReason(message);
	}

	public static enum EventType {
		LOCKDOWN,
		UNLOCK
	}
}
