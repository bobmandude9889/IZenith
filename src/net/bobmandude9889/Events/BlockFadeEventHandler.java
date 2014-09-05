package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockFadeEventHandler extends IZUtil implements Listener{
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public BlockFadeEventHandler(){
		this.plugin = getMain();
		this.vars = getVars();
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
