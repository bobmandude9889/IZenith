package net.bobmandude9889.Events;

import net.bobmandude9889.Methods.GetDisplayName;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.PlayerInventory;

public class EntityDamageByEntityEventHandler implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){
			Player player = (Player) e.getEntity();
			PlayerInventory inv = player.getInventory();
			String hName = GetDisplayName.getName(inv.getHelmet());
			if(!hName.equals(null) && hName.equals("[" + ChatColor.GREEN + "Emerald hat" + ChatColor.WHITE + "]")){
				e.setDamage(e.getDamage() - 2);
			}
			String cName = GetDisplayName.getName(inv.getChestplate());
			if(!cName.equals(null) && cName.equals("[" + ChatColor.GREEN + "Emerald top" + ChatColor.WHITE + "]")){
				e.setDamage(e.getDamage() - 5);	
			}
			String lName = GetDisplayName.getName(inv.getLeggings());
			if(!lName.equals(null) && lName.equals("[" + ChatColor.GREEN + "Emerald bottom" + ChatColor.WHITE + "]")){
				e.setDamage(e.getDamage() - 4);
			}
			String bName = GetDisplayName.getName(inv.getBoots());
			if(!bName.equals(null) && bName.equals("[" + ChatColor.GREEN + "Emerald boots" + ChatColor.WHITE + "]")){
				e.setDamage(e.getDamage() - 2);
			}
		}
	}
	
}
