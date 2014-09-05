package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceEventHandler extends IZUtil implements Listener {

	private Variables vars;

	public BlockPlaceEventHandler() {
		this.vars = getVars();
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (vars.createVoteShop.contains(e.getPlayer())) {
			Block block = e.getBlock();
			block.setType(Material.AIR);
			Location loc = block.getLocation();
			loc = loc.add(0.5, 0, 0.5);
			World world = loc.getWorld();
			Villager villager = (Villager) world.spawnEntity(loc,
					EntityType.VILLAGER);
			villager.setProfession(Profession.LIBRARIAN);
			villager.setCustomName(parseColors("&6&lPoint &f&lShop")[0]);
			villager.setCustomNameVisible(true);
			vars.createVoteShop.remove(e.getPlayer());
		}
	}
	
}