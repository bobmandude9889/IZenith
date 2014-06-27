package net.bobmandude9889.Events;

import net.bobmandude9889.Methods.ConvertColors;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityEventHandler implements Listener {

	private Variables vars;

	public PlayerInteractEntityEventHandler(Variables vars) {
		this.vars = vars;
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		Entity ent = e.getRightClicked();
		if (ent.getType().equals(EntityType.VILLAGER)) {
			Villager villager = (Villager) ent;
			if (villager.getCustomName().equals(
					ConvertColors.convertColors("&6&lPoint &f&lShop")[0])) {
				e.setCancelled(true);
				if (vars.deleteVoteShop.contains(e.getPlayer())) {
					villager.setHealth(0.0);
					vars.deleteVoteShop.remove(e.getPlayer());
				} else {
					vars.voteShopMain.open(e.getPlayer());
				}
			}
		}
	}
}
