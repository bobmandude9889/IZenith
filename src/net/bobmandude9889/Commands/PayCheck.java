package net.bobmandude9889.Commands;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public class PayCheck implements IZCommand {

	@Override
	public String getName() {
		return "paycheck";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			switch (args[0]) {
			case "vote":
				int diamondQual = 10;
				int smallMoneyQual = diamondQual;
				int largeMoneyQual = diamondQual;
				int godApplesQual = diamondQual;
				int kitLordQual = diamondQual;
				int hugeMoneyQual = diamondQual;
				int diamondRate = 10;
				int smallMoneyRate = 25;
				int largeMoneyRate = 75;
				int godApplesRate = 100;
				int kitLordRate = 500;
				int hugeMoneyRate = 1000;
				boolean diamond = false;
				boolean smallMoney = false;
				boolean largeMoney = false;
				boolean godApples = false;
				boolean kitLord = false;
				boolean hugeMoney = false;
				sender.sendMessage("Generating Diamonds");
				Random blah = new Random();
				int diRan = blah.nextInt(diamondRate) + 1;
				if (diRan == diamondQual)
					diamond = true;
				sender.sendMessage("Generating Small Money");
				int smRan = blah.nextInt(smallMoneyRate) + 1;
				if (smRan == smallMoneyQual)
					smallMoney = true;
				sender.sendMessage("Generating Large Money");
				int lmRan = blah.nextInt(largeMoneyRate) + 1;
				if (lmRan == largeMoneyQual)
					largeMoney = true;
				sender.sendMessage("Generating God Apples");
				int gaRan = blah.nextInt(godApplesRate) + 1;
				if (gaRan == godApplesQual)
					godApples = true;
				sender.sendMessage("Generating Kit Lord");
				int klRan = blah.nextInt(kitLordRate) + 1;
				if (klRan == kitLordQual)
					kitLord = true;
				sender.sendMessage("Generating Huge Money");
				int hmRan = blah.nextInt(hugeMoneyRate) + 1;
				if (hmRan == hugeMoneyQual)
					hugeMoney = true;

				sender.sendMessage("\nYour Results are:");
				sender.sendMessage("Item: bool, amount, hit, listener");
				sender.sendMessage("Diamond: " + diamond + " 10 " + diRan + " " + diamondQual);
				sender.sendMessage("Small Money: " + smallMoney + " $2000 " + smRan + " " + smallMoneyQual);
				sender.sendMessage("Large Money: " + largeMoney + " $20000 " + lmRan + " " + largeMoneyQual);
				sender.sendMessage("God Apples: " + godApples + " 5 " + gaRan + " " + godApplesQual);
				sender.sendMessage("Kit Lord: " + kitLord + " 1 " + klRan + " " + kitLordQual);
				sender.sendMessage("Huge Money: " + hugeMoney + " $200000 " + hmRan + " " + hugeMoneyQual);
				sender.sendMessage("Normal Rewards:");
				sender.sendMessage("Obsidian x 32");
				sender.sendMessage("XP Bottles x 32");
				sender.sendMessage("$1000");

				break;
			default:
				sender.sendMessage(ChatColor.RED + "Invalid arguments");
				break;
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
		return new Permission("iZenith.paycheck");
	}

}
