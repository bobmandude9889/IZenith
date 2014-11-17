package net.bobmandude9889.Events;

import net.bobmandude9889.Commands.VoteShop;
import net.bobmandude9889.iZenith.IZUtil;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractEntityEventHandler extends IZUtil implements Listener {

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		Entity ent = e.getRightClicked();
		if (ent.getType().equals(EntityType.VILLAGER)) {
			Villager villager = (Villager) ent;
			if (villager.getCustomName().equals(
					parseColors("&6&lPoint &f&lShop")[0])) {
				e.setCancelled(true);
				if (getVars().deleteVoteShop.contains(e.getPlayer())) {
					villager.setHealth(0.0);
					getVars().deleteVoteShop.remove(e.getPlayer());
				} else {
					VoteShop.openVoteShop(e.getPlayer());
				}
			}
		}
	}
}
