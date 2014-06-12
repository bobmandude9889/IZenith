package net.bobmandude9889.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GetTabColor {
	public static ChatColor getTabColor(Player player) {
		if (player.hasPermission("tabcol.0"))
			return ChatColor.BLACK;
		else if (player.hasPermission("tabcol.1"))
			return ChatColor.DARK_BLUE;
		else if (player.hasPermission("tabcol.2"))
			return ChatColor.DARK_GREEN;
		else if (player.hasPermission("tabcol.3"))
			return ChatColor.DARK_AQUA;
		else if (player.hasPermission("tabcol.4"))
			return ChatColor.DARK_RED;
		else if (player.hasPermission("tabcol.5"))
			return ChatColor.DARK_PURPLE;
		else if (player.hasPermission("tabcol.6"))
			return ChatColor.GOLD;
		else if (player.hasPermission("tabcol.7"))
			return ChatColor.GRAY;
		else if (player.hasPermission("tabcol.8"))
			return ChatColor.DARK_GRAY;
		else if (player.hasPermission("tabcol.9"))
			return ChatColor.BLUE;
		else if (player.hasPermission("tabcol.a"))
			return ChatColor.GREEN;
		else if (player.hasPermission("tabcol.b"))
			return ChatColor.AQUA;
		else if (player.hasPermission("tabcol.c"))
			return ChatColor.RED;
		else if (player.hasPermission("tabcol.d"))
			return ChatColor.LIGHT_PURPLE;
		else if (player.hasPermission("tabcol.e"))
			return ChatColor.YELLOW;
		else if (player.hasPermission("tabcol.f"))
			return ChatColor.WHITE;
		return ChatColor.RESET;
	}
}
