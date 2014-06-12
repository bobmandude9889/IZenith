package net.bobmandude9889.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Pvp implements IZCommand{

	@Override
	public String getName() {
		return "pvp";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Bukkit.getServer().dispatchCommand(sender, "warp pvp");
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}
	
}
