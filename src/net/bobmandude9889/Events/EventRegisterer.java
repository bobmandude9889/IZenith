package net.bobmandude9889.Events;

import java.util.List;

import net.bobmandude9889.Commands.VoteShop;
import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.GUI.GUIHandler;
import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegisterer extends IZUtil{
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

		vars.voteShopMain = new GUI(9, parseColors("&6&lPoint&8&lShop")[0], vars.handler);

		//Setting up kits
		List<String> kitStringList = vars.voteShopConfig.getStringList("kit_shop");
		int kitAmount = kitStringList.size();
		int kitShopSize = kitAmount + (9 - kitAmount);
		vars.voteShopKits = new GUI(kitShopSize, parseColors("&a&lKits")[0], vars.handler);

		//Adds back button for kits
		vars.voteShopKits.addButton(newItemMeta(Material.IRON_FENCE, parseColors("&c&lBack")[0], "Back to categories", 1), kitShopSize - 1, new Runnable() {
			public void run() {
				VoteShop.openVoteShop(vars.voteShopKits.getWhoClicked());
			}
		});
		
		//Setting up spawners
		List<String> spawnerStringList = vars.voteShopConfig.getStringList("spawner_shop");
		int spawnerAmount = spawnerStringList.size();
		int spawnerShopSize = spawnerAmount + (9 - spawnerAmount);
		vars.voteShopSpawners = new GUI(spawnerShopSize, parseColors("&1&lSpawners")[0], vars.handler);

		//Adds back button for spawners
		vars.voteShopSpawners.addButton(newItemMeta(Material.IRON_FENCE, parseColors("&c&lBack")[0], "Back to categories", 1), spawnerShopSize - 1, new Runnable() {
			public void run() {
				VoteShop.openVoteShop(vars.voteShopSpawners.getWhoClicked());
			}
		});
		
		//Adds kits and spawners button to shop
		ItemStack kits = newItemMeta(Material.DIAMOND_CHESTPLATE, "&a&l&oKits", "&b&l&oPurchase kits here!", 1);
		vars.voteShopMain.addButton(kits, 3, new Runnable() {
			@Override
			public void run() {
				VoteShop.openKitShop(vars.voteShopMain.getWhoClicked());
			}
		});
		ItemStack spawners = newItemMeta(Material.MOB_SPAWNER, "&2&l&oSpawners", "&b&l&oPurchase mob spawners ,&b&l&ohere!", 1);
		vars.voteShopMain.addButton(spawners, 5, new Runnable() {
			@Override
			public void run() {
				VoteShop.openSpawnerShop(vars.voteShopMain.getWhoClicked());
			}
		});

	}

}
