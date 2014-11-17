package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerQuitEventHandler extends IZUtil implements Listener{
	JavaPlugin plugin = getMain();
	Variables vars = getVars();
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String message = plugin.getConfig().getString("quit-message");
		message = message.replace("%PLAYER%", player.getName());
		message = parseColors(message)[0];
		event.setQuitMessage(message);
		if (vars.selectors.contains(player)) {
			vars.selectors.remove(player);
		}
	}
	
}
