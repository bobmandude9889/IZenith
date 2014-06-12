package net.bobmandude9889.iZenith;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class GetConfig {
	
	public static void loadConfigVars(JavaPlugin plugin, Variables vars){
		List<String> stringFires = plugin.getConfig().getStringList("fireLocs");
		for (int i = 0; i < stringFires.size(); i++) {
			String[] splitString = stringFires.get(i).split(",");
			double x = 0;
			double y = 0;
			double z = 0;
			World world = null;
			try {
				x = Double.parseDouble(splitString[0]);
				y = Double.parseDouble(splitString[1]);
				z = Double.parseDouble(splitString[2]);
				world = plugin.getServer().getWorld(splitString[3]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Location loc = new Location(world, x, y, z);
			vars.fires.add(loc);
		}
		StartFires.start(vars);
	}
	
}
