package net.bobmandude9889.Methods;

import org.bukkit.inventory.ItemStack;

public class GetDisplayName {
	
	public static String getName(ItemStack is){
		if(is.hasItemMeta() && is.getItemMeta().hasDisplayName()){
			return is.getItemMeta().getDisplayName();
		}
		return null;
	}
	
}
