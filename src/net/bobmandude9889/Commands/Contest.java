package net.bobmandude9889.Commands;

import java.util.ArrayList;
import java.util.List;

import net.bobmandude9889.iZenith.IZUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class Contest extends IZUtil implements IZCommand {

	@Override
	public String getName() {
		return "contest";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String c1 = "";
		try {
			c1 = args[0];
		} catch (Exception e) {
		}
		if (c1.equalsIgnoreCase("enter")) {
			List<String> contest = getMain().getConfig().getStringList("contest");
			if (contest == null) {
				contest = new ArrayList<String>();
			}
			if (contest.contains(sender.getName())) {
				sender.sendMessage(ChatColor.RED + "You already entered.");
			} else {
				contest.add(sender.getName());
				getMain().getConfig().set("contest", contest);
				getMain().saveConfig();
				sender.sendMessage("Entered the contest");
			}
		} else if (c1.equalsIgnoreCase("leave")) {
			List<String> contest = getMain().getConfig().getStringList("contest");
			if (contest == null) {
				contest = new ArrayList<String>();
			}
			if (contest.contains(sender.getName())) {
				contest.remove(sender.getName());
				getMain().getConfig().set("contest", contest);
				getMain().saveConfig();
				sender.sendMessage(ChatColor.GREEN + "You are no longer in the contest.");
			} else {
				sender.sendMessage(ChatColor.RED + "You have not entered the contest.");
			}
		} else if (c1.equalsIgnoreCase("list")) {
			List<String> contest = getMain().getConfig().getStringList("contest");
			if (contest == null) {
				sender.sendMessage(ChatColor.RED + "No one has entered the contest.");
			} else {
				sender.sendMessage(ChatColor.GREEN + "Contestants:");
				for (String name : contest) {
					sender.sendMessage(ChatColor.BLUE + name);
				}
			}
		} else {
			sender.sendMessage("/contest [enter,list]");
		}
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

	@Override
	public boolean hasPermission() {
		return false;
	}

	@Override
	public Permission getPermission() {
		return null;
	}

}
