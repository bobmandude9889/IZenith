package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityDamageEventHandler extends IZUtil implements Listener{
	JavaPlugin plugin = getMain();
	Variables vars = getVars();
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			PlayerInventory inv = player.getInventory();
			if (inv.getHelmet() != null && inv.getHelmet().getType().equals(Material.LEATHER_HELMET)
					&& inv.getHelmet()
							.getItemMeta()
							.getDisplayName()
							.equals("[" + ChatColor.GREEN + "Emerald hat"
									+ ChatColor.WHITE + "]")) {
				e.setDamage(e.getDamage() - ((e.getDamage() / 12.5) * 6));
			}
		}
	}
}
