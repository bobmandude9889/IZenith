package net.bobmandude9889.iZenith;

import net.bobmandude9889.Commands.IZCommand;
import net.bobmandude9889.Methods.LoadColors;
import net.bobmandude9889.Methods.SyncRepeatingTask;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	Variables vars = new Variables(this);

	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		GetConfig.loadConfigVars(this, vars);
		vars.er.registerEvents();
		LoadColors.loadColors(this);
		SyncRepeatingTask srt = new SyncRepeatingTask(this,vars);
		srt.start();	
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		for(int i = 0 ; i < vars.commands.size() ; i++){
			IZCommand command = vars.commands.get(i);
			if(command.onlyPlayers() && !(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "/" + command.getName() + " is only for players!");
			} else if(command.getName().equalsIgnoreCase(cmd.getName())){
				command.onCommand(sender, cmd, commandLabel, args);
				return true;
			}
		}
		return false;
	}

}
