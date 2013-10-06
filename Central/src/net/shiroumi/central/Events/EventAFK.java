package net.shiroumi.central.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class EventAFK extends PlayerEvent {

	private static final HandlerList handlers = new HandlerList();

	private EventType type;
	private AFKReason reason;

	public EventAFK(Player who, AFKReason reason, EventType type) {
		super(who);
		this.setReason(reason);
		this.setType(type);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}


	public EventType getType() {
		return type;
	}

	public AFKReason getReason() {
		return reason;
	}

	public void setType(EventType type) {
		this.type = type;
	}


	public void setReason(AFKReason reason) {
		this.reason = reason;
	}


	public static enum AFKReason {
		AUTO,
		DECIDED
	}

	public static enum EventType {
		Go_in_AFK,
		Returned_AFK
	}
}
