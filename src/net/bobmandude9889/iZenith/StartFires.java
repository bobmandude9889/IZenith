package net.bobmandude9889.iZenith;

import org.bukkit.Location;
import org.bukkit.Material;

public class StartFires {
	public static void start(Variables vars){
		for(int i = 0 ; i < vars.fires.size() ; i++){
			Location loc = vars.fires.get(i);
			loc.getBlock().setType(Material.FIRE);
		}
	}
}
