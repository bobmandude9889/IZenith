package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NoMobs implements IZCommand {

	Variables vars = null;
	
	public NoMobs(Variables vars) {
		this.vars = vars;
	}

	@Override
	public String getName() {
		return "nomobs";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player && sender.isOp()) {
			if (vars.selectors.containsKey(player.getName())) {
				player.sendMessage(ChatColor.GOLD + "Selection disabled!");
				vars.selectors.remove(player.getName());
			} else {
				player.sendMessage(ChatColor.GOLD + "Selection enabled!");
				vars.selectors.put(player.getName(), player.getLocation());
			}
		}
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

}
