package net.bobmandude9889.Methods;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VotePoints {

	public static int get(Player p, Variables vars) {
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
	
	public static void set(Player p, int points, Variables vars){
		if(vars.points.containsKey(p)){
			vars.points.remove(p);
		}
		vars.points.put(p, points);
		saveConfig(vars);
	}
	
	public static void saveConfig(Variables vars){
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
	
}
