package net.bobmandude9889.Events;

import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.GUI.GUIHandler;
import net.bobmandude9889.Methods.ConvertColors;
import net.bobmandude9889.Methods.Item;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegisterer {
	JavaPlugin plugin = null;
	Variables vars = null;
	PluginManager pm = null;

	public EventRegisterer(JavaPlugin plugin, Variables vars) {
		this.plugin = plugin;
		this.vars = vars;
		pm = plugin.getServer().getPluginManager();
	}

	public void registerEvents() {
		for (int i = 0; i < vars.listeners.size(); i++) {
			pm.registerEvents(vars.listeners.get(i), plugin);
		}
		plugin.getLogger().info("[iZenith] Registered events");
	}

	public void registerGUIEvents() {
		vars.handler = new GUIHandler(plugin);
		vars.donate = new GUI(9, ChatColor.GREEN + "Donate", vars.handler);
		vars.vote = new GUI(9, ChatColor.GREEN + "Vote", vars.handler);
		pm.registerEvents(vars.handler, plugin);

		vars.voteShopMain = new GUI(9, ConvertColors.convertColors("&6&lPoint&8&lShop")[0], vars.handler);
		ItemStack kits = Item.newItemMeta(Material.DIAMOND_CHESTPLATE, "&a&l&oKits", "&b&l&oYou cannot buy kits ,&b&l&oout of order. You ,&b&l&omust upgrade your kits.");
		vars.voteShopMain.addButton(kits, 0, new Runnable() {
			@Override
			public void run() {
				openKitShop(vars.voteShopMain.getWhoClicked());
			}
		});
		ItemStack ranks = Item.newItemMeta(Material.NAME_TAG, "&5&l&oRanks", "&b&l&oYou cannot buy ranks ,&b&l&oout of order. You must ,&b&l&oupgrade your ranks.");
		vars.voteShopMain.addButton(ranks, 2, new Runnable() {
			@Override
			public void run() {
				// TODO Add voteShopRanks
			}
		});
		ItemStack spawners = Item.newItemMeta(Material.MOB_SPAWNER, "&2&l&oSpawners", "&b&l&oPurchase mob spawners ,&b&l&ohere!");
		vars.voteShopMain.addButton(spawners, 4, new Runnable() {
			@Override
			public void run() {
				// TODO Add voteShopSpawners
			}
		});
		ItemStack weapons = Item.newItemMeta(Material.DIAMOND_PICKAXE, "&e&l&oWeapons", "&b&l&oCustom sword/ ,&b&l&oenchanted tools.");
		vars.voteShopMain.addButton(weapons, 6, new Runnable() {
			@Override
			public void run() {
				//TODO Add voteShopWeapons
			}
		});

	}
	
	public void openKitShop(Player player) {
		final GUI shop = new GUI(9, ConvertColors.convertColors("&a&l&oKits")[0], vars.handler);
		shop.addButton(new ItemStack(Material.DIAMOND_SWORD), 4, new Runnable() {
			@Override
			public void run() {
				
			}
		});
		shop.open(player);
	}

}
