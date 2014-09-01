package net.bobmandude9889.Commands;

import java.util.List;

import net.bobmandude9889.iZenith.Util;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Ranks extends Util implements IZCommand{
	
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public Ranks(JavaPlugin plugin, Variables vars){
		this.plugin = plugin;
		this.vars = vars;
	}
	
	@Override
	public String getName() {
		return "ranks";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		List<String> ranks = plugin.getConfig().getStringList("ranks");
		for (int i = 0; i < ranks.size(); i++) {
			sender.sendMessage(parseColors(ranks.get(i)));
		}
	}

	@Override
	public boolean onlyPlayers() {
		return false;
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
