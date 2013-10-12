package net.shiroumi.central.Listener;

import net.shiroumi.central.CTServer;
import net.shiroumi.central.i18n;
import net.shiroumi.central.Events.LockdownEvent;
import net.shiroumi.central.Util.Util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class LockdownListener implements Listener {

	public LockdownListener(JavaPlugin par1Plugin) {
		par1Plugin.getServer().getPluginManager().registerEvents(this, par1Plugin);
	}
	@EventHandler(ignoreCancelled=true, priority = EventPriority.HIGHEST)
	public void onLockdown(final LockdownEvent event) {
		Thread task_lockdown = new Thread() {
			public void run() {
				Util.broadcastMessage(i18n._("server" + (CTServer.isLocked()? "unlock" : "lockdown")), new String[][]{
					{"%reason", event.getMessage()},
					{"%locktime", CTServer.getConfiguration().getInteger("locktime").toString()}
				});
				try {
					Thread.sleep(CTServer.getConfiguration().getInteger("locktime") * 1000);
				} catch(InterruptedException e) {
					
				}
				CTServer.toggleLock();
				if(CTServer.isLocked()) {
					Util.broadcastMessage(i18n._("serverlockmsg"), new String[][]{
						{"%reason", CTServer.getLockReason()}
					});
					CTServer.kickAll(Util.maskedStringReplace(i18n._("serverlockmsg"), new String[][]{
						{"%reason", CTServer.getLockReason()}
					}));
				}
			}
		};
		task_lockdown.start();
	}
}
