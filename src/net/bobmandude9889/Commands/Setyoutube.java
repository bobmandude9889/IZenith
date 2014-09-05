package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.IZUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Setyoutube extends IZUtil implements IZCommand {

	@Override
	public String getName() {
		return "setyoutube";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		if (args.length > 0) {
			Server server = Bukkit.getServer();
			@SuppressWarnings("deprecation")
			Player player = server.getPlayer(args[0]);
			set((Player) sender, player);
		} else {
			sender.sendMessage(ChatColor.RED + "Enter a player");
		}
	}

	public static void set(Player sender, Player player) {
		sender.performCommand("pex user " + player.getName()
				+ " prefix \"&f&l(&4You&8Tube&f&l)&"
				+ getGroupColor(getPermissions().getPrimaryGroup(player)) + " \"");
		sender.performCommand("pex user " + player.getName()
				+ " add essentials.kits.YouTube");
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

	@Override
	public boolean hasPermission() {
		return true;
	}

	@Override
	public Permission getPermission() {
		return new Permission("izenith.setyoutube");
	}

}
