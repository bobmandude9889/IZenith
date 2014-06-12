package net.bobmandude9889.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class End implements IZCommand {

	@Override
	public String getName() {
		return "end";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Bukkit.getServer().dispatchCommand(sender, "warp end");
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

}
