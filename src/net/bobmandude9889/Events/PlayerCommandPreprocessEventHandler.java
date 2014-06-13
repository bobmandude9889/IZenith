package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCommandPreprocessEventHandler implements Listener{
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public PlayerCommandPreprocessEventHandler(JavaPlugin plugin, Variables vars){
		this.plugin = plugin;
		this.vars = vars;
	}

	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		String m = e.getMessage();
		if (m.startsWith("/plugins")
				|| m.startsWith("/pl")
				&& !e.getPlayer().isOp()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					ChatColor.RED + "You do not have access to that!");
		} else if(m.startsWith("/killall all") || m.startsWith("/killall itemframes")){
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot kill that type!");
			e.setCancelled(true);
		} else if(m.startsWith("/kit") && e.getPlayer().getWorld().getName().equals("plotworld")){
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot use \"/kit\" in the plotworld");
		} else if(m.startsWith("/sethome") && e.getPlayer().getWorld().getName().equals("plotworld")){
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot use \"/sethome\" in the plotworld");
		}
	}
}
