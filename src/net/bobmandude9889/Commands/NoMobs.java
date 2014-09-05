package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class NoMobs extends IZUtil implements IZCommand {

	Variables vars = null;
	
	public NoMobs() {
		this.vars = getVars();
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
			if (vars.selectors.contains(player)) {
				player.sendMessage(ChatColor.GOLD + "Selection disabled!");
				vars.selectors.remove(player);
			} else {
				player.sendMessage(ChatColor.GOLD + "Selection enabled!");
				vars.selectors.add(player);
			}
		}
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

	@Override
	public boolean hasPermission() {
		return false;
	}

	@Override
	public Permission getPermission() {
		return null;
	}

}
