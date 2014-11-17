package net.bobmandude9889.Duels;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DuelPlayer {
	public Location prevLoc;
	public UUID player;
	public ItemStack[] prevInv;
	public ItemStack[] prevArm;
	public double prevHealth;
	public GameMode prevGameMode;
	
	public DuelPlayer(Player player){
		this.prevLoc = player.getLocation();
		this.player = player.getUniqueId();
		this.prevInv = player.getInventory().getContents();
		this.prevArm = player.getInventory().getArmorContents();
		this.prevHealth = ((Damageable) player).getHealth();
		this.prevGameMode = player.getGameMode();
	}
	
	public void resetInv(){
		Bukkit.getPlayer(player).getInventory().setContents(prevInv);
		Bukkit.getPlayer(player).getInventory().setArmorContents(prevArm);
		Bukkit.getPlayer(player).setHealth(prevHealth);
		Bukkit.getPlayer(player).setGameMode(prevGameMode);
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(player);
	}
}
