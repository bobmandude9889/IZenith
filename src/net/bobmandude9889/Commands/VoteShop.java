package net.bobmandude9889.Commands;

import java.util.List;

import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.iZenith.IZUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public class VoteShop extends IZUtil implements IZCommand {

	@Override
	public String getName() {
		return "voteshop";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			if (sender instanceof Player) {
				switch (args[0]) {
				case "create":
					getVars().createVoteShop.add((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "Place a block to create a voteshop!");
					break;
				case "delete":
					getVars().deleteVoteShop.add((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "Right click a voteshop to delete it!");
					break;
				case "set":
					setPoints(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]), getVars());
					saveVoteShopConfig(getVars());
					sender.sendMessage(Bukkit.getPlayer(args[1]).getName() + " now has " + getPoints(Bukkit.getPlayer(args[1]), getVars()) + " points.");
					break;
				case "add":
					setPoints(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]) + getPoints(Bukkit.getPlayer(args[1]), getVars()), getVars());
					saveVoteShopConfig(getVars());
					sender.sendMessage(Bukkit.getPlayer(args[1]).getName() + " now has " + getPoints(Bukkit.getPlayer(args[1]), getVars()) + " points.");
					break;
				case "get":
					Player player = Bukkit.getPlayer(args[1]);
					sender.sendMessage(player.getName() + " has " + getPoints(player, getVars()) + " points.");
					break;
				case "open":
					VoteShop.openVoteShop((Player) sender);
					break;
				default:
					sender.sendMessage(ChatColor.RED + "Invalid arguments");
				}
			} else {
				switch (args[0]) {
				case "set":
					setPoints(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]), getVars());
					saveVoteShopConfig(getVars());
					sender.sendMessage(Bukkit.getPlayer(args[1]).getName() + " now has " + getPoints(Bukkit.getPlayer(args[1]), getVars()) + " points.");
					break;
				case "add":
					setPoints(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]) + getPoints(Bukkit.getPlayer(args[1]), getVars()), getVars());
					saveVoteShopConfig(getVars());
					sender.sendMessage(Bukkit.getPlayer(args[1]).getName() + " now has " + getPoints(Bukkit.getPlayer(args[1]), getVars()) + " points.");
					break;
				case "get":
					Player player = Bukkit.getPlayer(args[1]);
					sender.sendMessage(player.getName() + " has " + getPoints(player, getVars()) + " points.");
					break;
				default:
					sender.sendMessage(ChatColor.RED + "Invalid arguments");
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			sender.sendMessage(ChatColor.RED + "Invalid arguments");
		}
	}

	@Override
	public boolean onlyPlayers() {
		return false;
	}

	@Override
	public boolean hasPermission() {
		return true;
	}

	@Override
	public Permission getPermission() {
		return new Permission("izenith.voteshop");
	}

	public static void openVoteShop(Player player) {
		GUI shop = getVars().voteShopMain;
		shop.addButton(newItemMeta(Material.DIAMOND, ChatColor.BLUE + "" + getPoints(player, getVars()) + " Points", null, 1), 8, new Runnable() {
			public void run() {
			}
		});
		shop.open(player);
	}

	public static void openKitShop(final Player player) {
		final List<String> kitStringList = getVars().voteShopConfig.getStringList("kit_shop");
		final GUI shop = getVars().voteShopKits;
		for (String s : kitStringList) {
			final String[] split = s.split(";");
			final String name = split[0];
			final String lore = split[1];
			final Material material = Material.getMaterial(split[2]);
			final String kit = split[3];
			final int cost = Integer.parseInt(split[4]);
			shop.addButton(newItemMeta(material, (getPoints(player, getVars()) >= cost ? ChatColor.GREEN : ChatColor.RED) + name + ChatColor.DARK_PURPLE + " [" + cost + "]", lore, 1), kitStringList.indexOf(s), new Runnable() {
				public void run() {
					if (getPoints(player, getVars()) >= cost) {
						Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kit " + kit + " " + player.getName());
						setPoints(player, getPoints(player, getVars()) - cost, getVars());
						for (String s : kitStringList) {
							if (getPoints(player, getVars()) < Integer.parseInt(s.split(";")[4])) {
								openKitShop(player);
								break;
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "You do not have enough points to buy that.");
					}
				}
			});
		}
		shop.open(player);
	}

	public static void openSpawnerShop(final Player player) {
		final List<String> spawnerStringList = getVars().voteShopConfig.getStringList("spawner_shop");
		final GUI shop = getVars().voteShopSpawners;
		for (String s : spawnerStringList) {
			final String[] split = s.split(";");
			final String name = split[0];
			final String lore = split[1];
			final short type = Short.parseShort(split[2]);
			final int cost = Integer.parseInt(split[3]);
			shop.addButton(newItemMeta(Material.MOB_SPAWNER, (getPoints(player, getVars()) >= cost ? ChatColor.GREEN : ChatColor.RED) + name + ChatColor.DARK_PURPLE + " [" + cost + "]", lore, 1), spawnerStringList.indexOf(s), new Runnable() {
				public void run() {
					if (getPoints(player, getVars()) >= cost) {
						ItemStack spawner = new ItemStack(Material.MOB_SPAWNER, 1, type);
						player.getInventory().addItem(spawner);
						setPoints(player, getPoints(player, getVars()) - cost, getVars());
						for (String s : spawnerStringList) {
							if (getPoints(player, getVars()) < Integer.parseInt(s.split(";")[3])) {
								openSpawnerShop(player);
								break;
							}
						}
					} else {
						player.sendMessage(ChatColor.RED + "You do not have enough points to buy that.");
					}
				}
			});
			shop.open(player);
		}
	}

}
