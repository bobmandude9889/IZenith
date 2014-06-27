package net.bobmandude9889.Methods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	
	public static ItemStack newItemMeta(Material material, String name, String lore){
		name = ConvertColors.convertColors(name)[0];
		ItemStack is = new ItemStack(material);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		String[] loreA = lore.split(",");
		List<String> loreL = new ArrayList<String>();
		for(String l : loreA){
			l = ConvertColors.convertColors(l)[0];
			loreL.add(l);
		}
		im.setLore(loreL);
		is.setItemMeta(im);
		return is;
	}
	
}
