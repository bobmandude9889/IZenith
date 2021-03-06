package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class SetNoMobs extends IZUtil implements IZCommand {

	JavaPlugin plugin = null;
	Variables vars = null;
	
	public SetNoMobs() {
		this.plugin = getMain();
		this.vars = getVars();
	}

	@Override
	public String getName() {
		return "setnomobs";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (vars.selection1.containsKey(player.getName()) && vars.selection1.containsKey(player.getName()) && vars.selectors.contains(player)) {
			Location location = vars.selection1.get(player.getName());
			plugin.getConfig().set("no_mob_area.s1.x", location.getBlockX());
			plugin.getConfig().set("no_mob_area.s1.y", location.getBlockY());
			plugin.getConfig().set("no_mob_area.s1.z", location.getBlockZ());
			location = vars.selection2.get(player.getName());
			plugin.getConfig().set("no_mob_area.s2.x", location.getBlockX());
			plugin.getConfig().set("no_mob_area.s2.y", location.getBlockY());
			plugin.getConfig().set("no_mob_area.s2.z", location.getBlockZ());
			plugin.getConfig().set("no_mob_area.world", location.getWorld().getName());
			plugin.saveConfig();
			plugin.reloadConfig();
			player.sendMessage(ChatColor.GOLD + "Area set!");
		} else {
			player.sendMessage(ChatColor.RED + "Select an area first!");
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
		return new Permission("izenith.setnomobs");
	}

}
