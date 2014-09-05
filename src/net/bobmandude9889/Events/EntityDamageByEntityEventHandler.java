package net.bobmandude9889.Events;

import net.bobmandude9889.Commands.VoteShop;
import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.PlayerInventory;

public class EntityDamageByEntityEventHandler extends IZUtil implements Listener {

	Variables vars;

	public EntityDamageByEntityEventHandler() {
		this.vars = getVars();
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			PlayerInventory inv = player.getInventory();
			String hName = getItemName(inv.getHelmet());
			if (hName != null
					&& hName.equals("[" + ChatColor.GREEN + "Emerald hat"
							+ ChatColor.WHITE + "]")) {
				e.setDamage(e.getDamage() - 2);
			}
			String cName = getItemName(inv.getChestplate());
			if (cName != null
					&& cName.equals("[" + ChatColor.GREEN + "Emerald top"
							+ ChatColor.WHITE + "]")) {
				e.setDamage(e.getDamage() - 5);
			}
			String lName = getItemName(inv.getLeggings());
			if (lName != null
					&& lName.equals("[" + ChatColor.GREEN + "Emerald bottom"
							+ ChatColor.WHITE + "]")) {
				e.setDamage(e.getDamage() - 4);
			}
			String bName = getItemName(inv.getBoots());
			if (bName != null
					&& bName.equals("[" + ChatColor.GREEN + "Emerald boots"
							+ ChatColor.WHITE + "]")) {
				e.setDamage(e.getDamage() - 2);
			}
		} else if(e.getDamager() instanceof Player){
			Player player = (Player) e.getDamager();
			Entity ent = e.getEntity();
			if (ent.getType().equals(EntityType.VILLAGER)) {
				Villager villager = (Villager) ent;
				if (villager.getCustomName().equals(
						parseColors("&6&lPoint &f&lShop")[0])) {
					e.setCancelled(true);
					if (vars.deleteVoteShop.contains(player)) {
						villager.setHealth(0.0);
						vars.deleteVoteShop.remove(player);
					} else {
						VoteShop.openVoteShop(player,vars);
					}
				}
			}
		}
	}

}
