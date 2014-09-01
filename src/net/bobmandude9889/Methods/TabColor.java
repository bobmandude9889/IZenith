package net.bobmandude9889.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TabColor {
	public static String getName(Player player) {
		ChatColor color = getColor(IPermission.get().getPrimaryGroup(player));
		String name = player.getName();
		int length = name.length();
		if(length > 14){
			int subtract = length - 14;
			name = name.substring(0,length - subtract - 1);
		}
		return color + name;
	}
	
	public static ChatColor getColor(String group){
		ChatColor color = ChatColor.WHITE;
		for(String s : Bukkit.getPluginManager().getPlugin("iZenith").getConfig().getStringList("colors")){
			String[] sp = s.split(",");
			if(group.equalsIgnoreCase(sp[0])){
				color = ChatColor.getByChar(sp[1].toCharArray()[0]);
				break;
			}
		}
		return color;
	}
}
