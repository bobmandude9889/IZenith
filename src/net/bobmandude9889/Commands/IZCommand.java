package net.bobmandude9889.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface IZCommand {
	public String getName();
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args);
	public boolean onlyPlayers();
	public boolean hasPermission();
	public Permission getPermission();
}

