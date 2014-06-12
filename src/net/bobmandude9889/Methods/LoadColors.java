package net.bobmandude9889.Methods;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class LoadColors {
	public static void loadColors(Plugin plugin){
		for(Player player : plugin.getServer().getOnlinePlayers()){
			player.setPlayerListName(GetTabColor.getTabColor(player) + player.getName());
		}
	}
}
