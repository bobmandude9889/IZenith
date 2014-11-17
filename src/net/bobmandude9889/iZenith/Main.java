package net.bobmandude9889.iZenith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.bobmandude9889.Commands.IZCommand;
import net.bobmandude9889.Duels.Arena;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public Variables vars = null;
	public BroadcastingTask ab = null;
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		IZUtil.loadColors(this);
		if (getServer().getPluginManager().isPluginEnabled("CustomGUIAPI")) {
			loadVars();
			vars.er.registerGUIEvents();
		}
		IZUtil.initScoreboard(vars);
		List<String> list = getConfig().getStringList("arenas");
		if (list != null) {
			for (String s : list) {
				try {
					Arena a = new Arena();
					String[] split = s.split(",");
					a.spawn1 = new Location(Bukkit.getWorld(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
					a.spawn2 = new Location(Bukkit.getWorld(split[0]), Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]));
					vars.arenas.add(a);
					getServer().getPluginManager().registerEvents(a, this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void onDisable() {
		for (Player p : vars.handler.getOpenInvs().keySet()) {
			p.closeInventory();
			p.sendMessage(ChatColor.RED + "A reload forced you to exit the GUI.");
		}
		reloadConfig();
		List<String> list = new ArrayList<String>();
		for (Arena arena : vars.arenas) {
			list.add(arena.spawn1.getWorld().getName() + "," + arena.spawn1.getBlockX() + "," + arena.spawn1.getBlockY() + "," + arena.spawn1.getBlockZ() + "," + arena.spawn2.getBlockX() + "," + arena.spawn2.getBlockY() + "," + arena.spawn2.getBlockZ());
		}
		getConfig().set("arenas", list);
		saveConfig();
	}

	public void loadVars() {
		this.vars = new Variables(this);
		GetConfig.loadConfigVars(this, vars);
		vars.er.registerEvents();
		ab = new BroadcastingTask(this, vars);
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
			if (command.getName().equalsIgnoreCase(cmd.getName())) {
				if (command.hasPermission() && !sender.hasPermission(command.getPermission())) {
					sender.sendMessage(ChatColor.RED + "You do not have permission for that command.");
					return true;
				}
				if (command.onlyPlayers() && !(sender instanceof Player)) {
					sender.sendMessage(ChatColor.RED + command.getName() + " is only for players!");
					return false;
				} else {
					command.onCommand(sender, cmd, commandLabel, args);
					return true;
				}
			}
		}
		return false;
	}

}
