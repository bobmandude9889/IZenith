package net.bobmandude9889.iZenith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public class IZUtil{
	
	public static String[] parseColors(String message) {
		for (ChatColor color : ChatColor.values()) {
            message = message.replaceAll(String.format("&%c", color.getChar()), color.toString());
        }
		String[] messageA = message.split("<next>");
		return messageA;
	}
	
	public static String getItemName(ItemStack is){
		if(is != null && is.hasItemMeta() && is.getItemMeta().hasDisplayName()){
			return is.getItemMeta().getDisplayName();
		}
		return null;
	}
	
	public static Permission getPermissions(){
        Permission permission = null;
		RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return permission;
    }
	
	public static boolean isIn(Location loc1, Location loc2, Location loc3) {
		int n = 0;
		if (loc2.getBlockX() > loc3.getBlockX()
				&& loc1.getBlockX() >= loc3.getBlockX()
				&& loc1.getBlockX() <= loc2.getBlockX()) {
			n++;
		} else if (loc1.getBlockX() >= loc2.getBlockX()
				&& loc1.getBlockX() <= loc3.getBlockX()) {
			n++;
		}
		if (loc2.getBlockY() > loc3.getBlockY()
				&& loc1.getBlockY() >= loc3.getBlockY()
				&& loc1.getBlockY() <= loc2.getBlockY()) {
			n++;
		} else if (loc1.getBlockY() >= loc2.getBlockY()
				&& loc1.getBlockY() <= loc3.getBlockY()) {
			n++;
		}
		if (loc2.getBlockZ() > loc3.getBlockZ()
				&& loc1.getBlockZ() >= loc3.getBlockZ()
				&& loc1.getBlockZ() <= loc2.getBlockZ()) {
			n++;
		} else if (loc1.getBlockZ() >= loc2.getBlockZ()
				&& loc1.getBlockZ() <= loc3.getBlockZ()) {
			n++;
		}
		if (n == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ItemStack newItemMeta(Material material, String name, String lore, int i) {
		name = parseColors(name)[0];
		ItemStack is = new ItemStack(material, i);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		if (lore != null) {
			String[] loreA = lore.split(",");
			List<String> loreL = new ArrayList<String>();
			for (String l : loreA) {
				l = parseColors(l)[0];
				loreL.add(l);
			}
			im.setLore(loreL);
		}
		is.setItemMeta(im);
		return is;
	}
	
	public static void loadColors(Plugin plugin){
		for(Player player : plugin.getServer().getOnlinePlayers()){
			player.setPlayerListName(getColoredName(player));
		}
	}

	public static void initScoreboard(Variables vars) {
		try {
			vars.scoreboard.registerNewTeam("beginner").setPrefix(parseColors("&7")[0]);
			vars.scoreboard.registerNewTeam("donator").setPrefix(parseColors("&d")[0]);
			vars.scoreboard.registerNewTeam("knight").setPrefix(parseColors("&9")[0]);
			vars.scoreboard.registerNewTeam("slayer").setPrefix(parseColors("&e")[0]);
			vars.scoreboard.registerNewTeam("dragon").setPrefix(parseColors("&6")[0]);
			vars.scoreboard.registerNewTeam("lord").setPrefix(parseColors("&2")[0]);
			vars.scoreboard.registerNewTeam("god").setPrefix(parseColors("&b")[0]);
			vars.scoreboard.registerNewTeam("mod").setPrefix(parseColors("&3")[0]);
			vars.scoreboard.registerNewTeam("admin").setPrefix(parseColors("&5")[0]);
			vars.scoreboard.registerNewTeam("dev").setPrefix(parseColors("&c")[0]);
			vars.scoreboard.registerNewTeam("owner").setPrefix(parseColors("&1")[0]);
		} catch (Exception e) {
		}
		for (Team t : vars.scoreboard.getTeams()) {
			vars.teams.add(t);
		}
	}

	public static void setTeam(Player player, Variables vars) {
		for (Team t : vars.teams) {
			if (t.getName().equalsIgnoreCase(getPermissions().getPrimaryGroup(player))) {
				t.addPlayer(player);
				return;
			}
		}
	}
	
	public static String getColoredName(Player player) {
		ChatColor color = getGroupColor(getPermissions().getPrimaryGroup(player));
		String name = player.getName();
		int length = name.length();
		if(length > 14){
			int subtract = length - 14;
			name = name.substring(0,length - subtract - 1);
		}
		return color + name;
	}
	
	public static ChatColor getGroupColor(String group){
		ChatColor color = ChatColor.WHITE;
		for(String s : Bukkit.getPluginManager().getPlugin("iZenith").getConfig().getStringList("colors")){
			String[] sp = s.split(",");
			if(group.equalsIgnoreCase(sp[0])){
				color = ChatColor.getByChar(sp[1].toCharArray()[0]);
				break;
			}
		}
		return color;
	}
	
	public static int getPoints(Player p, Variables vars) {
		if (vars.points.containsKey(p)) {
			return vars.points.get(p);
		} else {
			Integer points = vars.voteShopConfig.getInt(p.getUniqueId().toString());
			if(points != null){
				vars.points.put(p, points);
				return points;
			} else {
				return 0;
			}
		}
	}
	
	public static void setPoints(Player p, int points, Variables vars){
		if(vars.points.containsKey(p)){
			vars.points.remove(p);
		}
		vars.points.put(p, points);
		saveVoteShopConfig(vars);
	}
	
	public static void saveVoteShopConfig(Variables vars){
		HashMap<Player,Integer> points = vars.points;
		JavaPlugin plugin = vars.plugin;
		for(Player p : points.keySet()){
			vars.voteShopConfig.set(p.getUniqueId() + "", points.get(p));
		}
		try {
			vars.voteShopConfig.save(new File(plugin.getDataFolder(),"VoteShop.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	public static Main getMain(){
		return (Main)Bukkit.getPluginManager().getPlugin("iZenith");
	}
	
	public static Variables getVars(){
		return getMain().vars;
	}
	
	public int findArena() {
		for (int i = 0; i < getVars().arenas.size(); i++) {
			if (getVars().arenas.get(i).isEmpty())
				return i;
		}
		return -1;
	}
	
	public Server getServer(){
		return getMain().getServer();
	}
	
}
