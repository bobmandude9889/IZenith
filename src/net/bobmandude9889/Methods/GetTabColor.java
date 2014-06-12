package net.bobmandude9889.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetTabColor {
	public static ChatColor getTabColor(Player player) {
		if (player.hasPermission("tabcol.0"))
			return ChatColor.BLACK;
		if (player.hasPermission("tabcol.1"))
			return ChatColor.DARK_BLUE;
		if (player.hasPermission("tabcol.2"))
			return ChatColor.DARK_GREEN;
		if (player.hasPermission("tabcol.3"))
			return ChatColor.DARK_AQUA;
		if (player.hasPermission("tabcol.4"))
			return ChatColor.DARK_RED;
		if (player.hasPermission("tabcol.5"))
			return ChatColor.DARK_PURPLE;
		if (player.hasPermission("tabcol.6"))
			return ChatColor.GOLD;
		if (player.hasPermission("tabcol.7"))
			return ChatColor.GRAY;
		if (player.hasPermission("tabcol.8"))
			return ChatColor.DARK_GRAY;
		if (player.hasPermission("tabcol.9"))
			return ChatColor.BLUE;
		if (player.hasPermission("tabcol.a"))
			return ChatColor.GREEN;
		if (player.hasPermission("tabcol.b"))
			return ChatColor.AQUA;
		if (player.hasPermission("tabcol.c"))
			return ChatColor.RED;
		if (player.hasPermission("tabcol.d"))
			return ChatColor.LIGHT_PURPLE;
		if (player.hasPermission("tabcol.e"))
			return ChatColor.YELLOW;
		if (player.hasPermission("tabcol.f"))
			return ChatColor.WHITE;
		return ChatColor.RESET;
	}
}
