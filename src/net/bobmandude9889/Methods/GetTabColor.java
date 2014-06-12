package net.bobmandude9889.Methods;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetTabColor {
	public static ChatColor getTabColor(Player player) {
		Permission perm = IPermission.get();
		if(perm.getPrimaryGroup(player).equalsIgnoreCase("beginner")){
			return ChatColor.GRAY;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("donator")){
			return ChatColor.LIGHT_PURPLE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("knight")){
			return ChatColor.BLUE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("slayer")){
			return ChatColor.YELLOW;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("dragon")){
			return ChatColor.GOLD;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("donator")){
			return ChatColor.LIGHT_PURPLE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("lord")){
			return ChatColor.DARK_GREEN;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("god")){
			return ChatColor.AQUA;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("moderator")){
			return ChatColor.DARK_AQUA;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("admin")){
			return ChatColor.DARK_PURPLE;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("developer")){
			return ChatColor.RED;
		}else if(perm.getPrimaryGroup(player).equalsIgnoreCase("owner")){
			return ChatColor.DARK_BLUE;
		}
		return ChatColor.WHITE;
	}
}
