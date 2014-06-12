package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockFadeEventHandler implements Listener{
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public BlockFadeEventHandler(JavaPlugin plugin, Variables vars){
		this.plugin = plugin;
		this.vars = vars;
	}
	
	@EventHandler
	public void onBlockFade(BlockFadeEvent e) {
		Location loc = e.getBlock().getLocation();
		loc.setZ(loc.getZ() - 1);
		if (e.getBlock().getType().equals(Material.FIRE)
				&& vars.fires.contains(e.getBlock().getLocation())) {
			e.setCancelled(true);
		}
	}
}
