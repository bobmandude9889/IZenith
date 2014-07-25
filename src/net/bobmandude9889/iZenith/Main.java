package net.bobmandude9889.iZenith;

import java.io.File;
import java.io.IOException;

import net.bobmandude9889.Commands.IZCommand;
import net.bobmandude9889.Methods.LoadColors;
import net.bobmandude9889.Methods.SyncRepeatingTask;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	Variables vars = null;
	public SyncRepeatingTask ab = null;

	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		LoadColors.loadColors(this);
		if (getServer().getPluginManager().isPluginEnabled("CustomGUIAPI")) {
			loadVars();
			vars.er.registerGUIEvents();
		}
	}

	public void loadVars() {
		this.vars = new Variables(this);
		GetConfig.loadConfigVars(this, vars);
		vars.er.registerEvents();
		ab = new SyncRepeatingTask(this, vars);
		ab.start();
		File voteShopFile = new File(this.getDataFolder(), "VoteShop.yml");
		FileConfiguration voteShopConfig;
		if (voteShopFile.exists()) {
			voteShopConfig = YamlConfiguration.loadConfiguration(voteShopFile);
		} else {
			voteShopConfig = new YamlConfiguration();
		}
		vars.voteShopConfig = voteShopConfig;
		try {
			vars.voteShopConfig.save(new File(this.getDataFolder(), "VoteShop.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		getLogger().info("[iZenith] loaded variables");

	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		for (int i = 0; i < vars.commands.size(); i++) {
			IZCommand command = vars.commands.get(i);
			if (command.onlyPlayers() && !(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED + "/" + command.getName() + " is only for players!");
			} else if (command.getName().equalsIgnoreCase(cmd.getName())) {
				command.onCommand(sender, cmd, commandLabel, args);
				return true;
			}
		}
		return false;
	}

}
