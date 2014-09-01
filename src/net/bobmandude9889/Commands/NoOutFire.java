package net.bobmandude9889.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

public class NoOutFire implements IZCommand {

	@Override
	public String getName() {
		return "nooutfire";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (player.isOp()) {
			ItemStack fire = new ItemStack(Material.FIRE);
			ItemMeta fireIM = fire.getItemMeta();
			fireIM.setDisplayName(ChatColor.RED + "NO OUT FIRE!");
			fire.setItemMeta(fireIM);
			player.getInventory().addItem(fire);
		}
	}

	@Override
	public boolean onlyPlayers() {
		return true;
	}

	@Override
	public boolean hasPermission() {
		return true;
	}

	@Override
	public Permission getPermission() {
		return new Permission("iZenith.nooutfire");
	}

}
