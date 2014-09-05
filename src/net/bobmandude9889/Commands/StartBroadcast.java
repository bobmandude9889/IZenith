package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class StartBroadcast extends IZUtil implements IZCommand{
	
	Main main;
	
	public StartBroadcast(){
		this.main = getMain();
	}
	
	@Override
	public String getName() {
		return "startbroadcast";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,String commandLabel, String[] args) {
		main.ab.start();
	}

	@Override
	public boolean onlyPlayers() {
		return false;
	}

	@Override
	public boolean hasPermission() {
		return true;
	}

	@Override
	public Permission getPermission() {
		return new Permission("izenith.startbroadcast");
	}

}
