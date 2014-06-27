package net.bobmandude9889.Commands;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteShop implements IZCommand {
	
	Variables vars;
	
	public VoteShop(Variables vars){
		this.vars = vars;
	}
	
	@Override
	public String getName() {
		return "voteshop";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		try{
			switch(args[0]){
			case "create":
				vars.createVoteShop.add((Player) sender);
				sender.sendMessage(ChatColor.GREEN + "Place a block to create a voteshop!");
				break;
			case "delete":
				vars.deleteVoteShop.add((Player)sender);
				sender.sendMessage(ChatColor.GREEN + "Right click a voteshop to delete it!");
				break;
			default:
				sender.sendMessage(ChatColor.RED + "Invalid arguments");
			}
		}catch(ArrayIndexOutOfBoundsException e){
			sender.sendMessage(ChatColor.RED + "Invalid arguments");
		}
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}
	
}
