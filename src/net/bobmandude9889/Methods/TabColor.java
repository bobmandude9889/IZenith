package net.bobmandude9889.Methods;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TabColor {
	public static String getName(Player player) {
		ChatColor color = getColor(player);
		String name = player.getName();
		int length = name.length();
		if(length > 14){
			int subtract = length - 14;
			name = name.substring(0,length - subtract - 1);
		}
		return color + name;
	}
	
	public static ChatColor getColor(Player player){
		Permission perm = IPermission.get();
		ChatColor color = ChatColor.WHITE;
		if(perm.getPrimaryGroup(player).equalsIgnoreCase("beginner")){
			color = ChatColor.GRAY;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("donator")){
			color = ChatColor.LIGHT_PURPLE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("knight")){
			color = ChatColor.BLUE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("slayer")){
			color = ChatColor.YELLOW;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("dragon")){
			color = ChatColor.GOLD;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("donator")){
			color = ChatColor.LIGHT_PURPLE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("lord")){
			color = ChatColor.DARK_GREEN;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("god")){
			color = ChatColor.AQUA;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("mod")){
			color = ChatColor.DARK_AQUA;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("admin")){
			color = ChatColor.DARK_PURPLE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("dev")){
			color = ChatColor.RED;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("owner")){
			color = ChatColor.DARK_BLUE;
		}
		return color;
	}
}
