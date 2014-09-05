package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinEventHandler extends IZUtil implements Listener{
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public PlayerJoinEventHandler(){
		this.plugin = getMain();
		this.vars = getVars();
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String message = plugin.getConfig().getString("join-message");
		message = message.replace("%PLAYER%", player.getName());
		message = parseColors(message)[0];
		e.setJoinMessage(message);
		player.setPlayerListName(getColoredName(player));
		setTeam(player, vars);
	}
}
