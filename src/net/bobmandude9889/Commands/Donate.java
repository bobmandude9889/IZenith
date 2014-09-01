package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Donate implements IZCommand{
	
	Variables vars = null;
	
	public Donate(Variables vars){
		this.vars = vars;
	}
	
	@Override
	public String getName() {
		return "donate";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		vars.donate.open((Player) sender);
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
