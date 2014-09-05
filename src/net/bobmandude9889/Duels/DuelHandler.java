package net.bobmandude9889.Duels;

import java.util.ArrayList;
import java.util.List;

import net.bobmandude9889.iZenith.Main;
import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

public class DuelHandler extends IZUtil implements Listener{

	public DuelHandler(){
		Main main = getMain();
		Variables vars = getVars();
		List<String> list = main.getConfig().getStringList("duels_arenas");
		if (list != null) {
			for (String s : list) {
				try {
					Arena a = new Arena();
					String[] split = s.split(",");
					a.spawn1 = new Location(Bukkit.getWorld(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
					a.spawn2 = new Location(Bukkit.getWorld(split[0]), Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]));
					vars.arenas.add(a);
					main.getServer().getPluginManager().registerEvents(a, main);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void disable(){
		Main main = getMain();
		Variables vars = getVars();
		main.reloadConfig();
		List<String> list = new ArrayList<String>();
		for (Arena arena : vars.arenas) {
			list.add(arena.spawn1.getWorld().getName() + "," + arena.spawn1.getBlockX() + "," + arena.spawn1.getBlockY() + "," + arena.spawn1.getBlockZ() + "," + arena.spawn2.getBlockX() + "," + arena.spawn2.getBlockY() + "," + arena.spawn2.getBlockZ());
		}
		main.getConfig().set("arenas", list);
		main.saveConfig();
	}
	
}
