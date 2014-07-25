package net.bobmandude9889.Commands;

import java.awt.List;
import java.util.ArrayList;

import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.Methods.Item;
import net.bobmandude9889.Methods.VotePoints;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteShop implements IZCommand {

	Variables vars;

	public VoteShop(Variables vars) {
		this.vars = vars;
	}

	@Override
	public String getName() {
		return "voteshop";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			switch (args[0]) {
			case "create":
				vars.createVoteShop.add((Player) sender);
				sender.sendMessage(ChatColor.GREEN + "Place a block to create a voteshop!");
				break;
			case "delete":
				vars.deleteVoteShop.add((Player) sender);
				sender.sendMessage(ChatColor.GREEN + "Right click a voteshop to delete it!");
				break;
			case "set":
				VotePoints.set(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]), vars);
				VotePoints.saveConfig(vars);
				sender.sendMessage(Bukkit.getPlayer(args[1]).getName() + " now has " + VotePoints.get(Bukkit.getPlayer(args[1]),vars) + " points.");
				break;
			case "add":
				VotePoints.set(Bukkit.getPlayer(args[1]), Integer.parseInt(args[2]) + VotePoints.get(Bukkit.getPlayer(args[1]), vars), vars);
				VotePoints.saveConfig(vars);
				sender.sendMessage(Bukkit.getPlayer(args[1]).getName() + " now has " + VotePoints.get(Bukkit.getPlayer(args[1]),vars) + " points.");
				break;
			case "get":
				Player player = Bukkit.getPlayer(args[1]);
				sender.sendMessage(player.getName() + " has " + VotePoints.get(player, vars) + " points.");
				break;
			default:
				sender.sendMessage(ChatColor.RED + "Invalid arguments");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			sender.sendMessage(ChatColor.RED + "Invalid arguments");
		}
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

	public static void openVoteShop(Player player, Variables vars) {
		GUI shop = vars.voteShopMain;
		shop.addButton(Item.newItemMeta(Material.DIAMOND, ChatColor.BLUE + "" + VotePoints.get(player, vars), new ArrayList<String>()), 8, new Runnable(){
			public void run(){
				
			}
		});
		shop.open(player);
	}

}
