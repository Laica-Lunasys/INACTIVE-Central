package net.shiroumi.central.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AFKEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private EventType                type;
	private AFKReason                reason;
	private String                   Message;
	private Player                   player;

	public AFKEvent(Player who, AFKReason reason, EventType type, String Message) {
		super();
		this.setPlayer(who);
		this.setReason(reason);
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

	public AFKReason getReason() {
		return reason;
	}

	public String getMessage() {
		return Message;
	}

	public Player getPlayer() {
		return player;
	}

	// setter群

	public void setType(EventType type) {
		this.type = type;
	}

	public void setReason(AFKReason reason) {
		this.reason = reason;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public static enum AFKReason {
		AUTO,
		DECIDED
	}

	public static enum EventType {
		Go_in_AFK,
		Returned_AFK,
		Long_AFK_Kick
	}
}
