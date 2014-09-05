package net.bobmandude9889.Duels;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DuelPlayer {
	public Location prevLoc;
	public UUID player;
	public ItemStack[] prevInv;
	public ItemStack[] prevArm;
	
	public DuelPlayer(Player player){
		this.prevLoc = player.getLocation();
		this.player = player.getUniqueId();
		this.prevInv = player.getInventory().getContents();
		this.prevArm = player.getInventory().getArmorContents();
	}
	
	public void resetInv(){
		Bukkit.getPlayer(player).getInventory().setContents(prevInv);
		Bukkit.getPlayer(player).getInventory().setArmorContents(prevArm);
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(player);
	}
}
