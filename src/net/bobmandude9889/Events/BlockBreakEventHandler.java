package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBreakEventHandler implements Listener{
	
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public BlockBreakEventHandler(JavaPlugin plugin, Variables vars){
		this.plugin = plugin;
		this.vars = vars;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		ItemStack axe = new ItemStack(Material.IRON_AXE, 1);
		if (event.getPlayer().getItemInHand().equals(axe)) {
			if (vars.selectors.contains(player)) {
				event.setCancelled(true);
				if (vars.selection1.containsKey(player.getName())) {
					vars.selection1.remove(player.getName());
				}
				vars.selection1.put(player.getName(), new Location(event.getBlock()
						.getWorld(), event.getBlock().getX(), event.getBlock()
						.getY(), event.getBlock().getZ()));
				player.sendMessage(ChatColor.GOLD + "Position 1 set at "
						+ event.getBlock().getX() + " "
						+ event.getBlock().getY() + " "
						+ event.getBlock().getZ());
			}
		}
	}
	
}
