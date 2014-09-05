package net.bobmandude9889.Events;

import java.util.List;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerInteractEventHandler extends IZUtil implements Listener{
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public PlayerInteractEventHandler(){
		this.plugin = getMain();
		this.vars = getVars();
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& event.hasItem() && event.getItem().getType().equals(Material.IRON_AXE)) {
			if (vars.selectors.contains(player)) {
				event.setCancelled(true);
				if (vars.selection2.containsKey(player.getName())) {
					vars.selection2.remove(player.getName());
				}
				vars.selection2.put(player.getName(), new Location(event
						.getClickedBlock().getWorld(), event.getClickedBlock()
						.getX(), event.getClickedBlock().getY(), event
						.getClickedBlock().getZ()));
				player.sendMessage(ChatColor.GOLD + "Position 2 set at "
						+ event.getClickedBlock().getX() + " "
						+ event.getClickedBlock().getY() + " "
						+ event.getClickedBlock().getZ());
			}
		} else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& event.hasItem()
				&& event.getItem().hasItemMeta()
				&& event.getItem().getItemMeta().hasDisplayName()
				&& event.getItem().getItemMeta().getDisplayName()
						.equals(ChatColor.RED + "NO OUT FIRE!")) {
			List<String> stringFires = plugin.getConfig().getStringList("fireLocs");
			BlockFace bf = event.getBlockFace();
			Location loc = event.getClickedBlock().getLocation();
			if (bf.equals(BlockFace.DOWN))
				loc.subtract(0, 1, 0);
			else if (bf.equals(BlockFace.UP))
				loc.add(0, 1, 0);
			else if (bf.equals(BlockFace.NORTH))
				loc.subtract(0, 0, 1);
			else if (bf.equals(BlockFace.SOUTH))
				loc.add(0, 0, 1);
			else if (bf.equals(BlockFace.EAST))
				loc.add(1, 0, 0);
			else if (bf.equals(BlockFace.WEST))
				loc.subtract(1, 0, 0);
			stringFires.add(loc.getBlockX() + "," + loc.getBlockY() + ","
					+ loc.getBlockZ() + "," + loc.getWorld().getName());
			vars.fires.add(loc);
			plugin.getConfig().set("fireLocs", stringFires);
			plugin.saveConfig();
		} else if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			BlockFace bf = event.getBlockFace();
			Location loc = event.getClickedBlock().getLocation();
			if (bf.equals(BlockFace.DOWN))
				loc.subtract(0, 1, 0);
			else if (bf.equals(BlockFace.UP))
				loc.add(0, 1, 0);
			else if (bf.equals(BlockFace.NORTH))
				loc.subtract(0, 0, 1);
			else if (bf.equals(BlockFace.SOUTH))
				loc.add(0, 0, 1);
			else if (bf.equals(BlockFace.EAST))
				loc.add(1, 0, 0);
			else if (bf.equals(BlockFace.WEST))
				loc.subtract(1, 0, 0);
			if (vars.fires.contains(loc)) {
				if (player.isOp()) {
					List<String> stringFires = plugin.getConfig().getStringList(
							"fireLocs");
					stringFires.remove(loc.getBlockX() + "," + loc.getBlockY()
							+ "," + loc.getBlockZ() + ","
							+ loc.getWorld().getName());
					vars.fires.remove(loc);
					plugin.getConfig().set("fireLocs", stringFires);
					plugin.saveConfig();
				} else {
					event.setCancelled(true);
				}
			}
		}
	}
	
	public int find(String m, List<String> list){
		for(int i = 0; i < list.size(); i++){
			String s = list.get(i);
			if(s.equals(m)){
				return i;
			}
		}
		return 0;
	}
	
}
