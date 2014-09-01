package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Spectate implements IZCommand {
	
	Variables vars;
	
	public Spectate(Variables vars){
		this.vars = vars;
	}
	
	@Override
	public String getName() {
		return "spectate";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		@SuppressWarnings("deprecation")
		Player target = Bukkit.getPlayer(args[1]);
		vars.spectate.put((Player)sender, target);
		for(Player p : Bukkit.getOnlinePlayers()){
			p.hidePlayer((Player)sender);
		}
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
		return new Permission("izenith.spectate");
	}

}
