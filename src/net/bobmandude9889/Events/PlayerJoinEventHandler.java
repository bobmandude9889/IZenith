package net.bobmandude9889.Events;

import net.bobmandude9889.Methods.ConvertColors;
import net.bobmandude9889.Methods.ScoreboardHandler;
import net.bobmandude9889.Methods.TabColor;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinEventHandler implements Listener{
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public PlayerJoinEventHandler(JavaPlugin plugin, Variables vars){
		this.plugin = plugin;
		this.vars = vars;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String message = plugin.getConfig().getString("join-message");
		message = message.replace("%PLAYER%", player.getName());
		message = ConvertColors.convertColors(message)[0];
		e.setJoinMessage(message);
		player.setPlayerListName(TabColor.getName(player));
		ScoreboardHandler.setTeam(player, vars);
	}
}
