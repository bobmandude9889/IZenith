package net.bobmandude9889.Duels;

import net.bobmandude9889.iZenith.Main;
import net.bobmandude9889.iZenith.IZUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Arena extends IZUtil implements Listener{

	public Location spawn1;
	public Location spawn2;
	public DuelPlayer p1;
	public DuelPlayer p2;
	public int stake;
	public boolean stakeChanged = false;
	public boolean accepted = false;

	public static boolean isIn(Player player) {
		for (Arena a : getVars().arenas) {
			if (a.containsPlayer(player))
				return true;
		}
		return false;
	}

	public void setSpawn1(Location loc) {
		spawn1 = loc;
	}

	public void setSpawn2(Location loc) {
		spawn2 = loc;
	}

	public void setP1(Player p) {
		this.p1 = new DuelPlayer(p);
	}

	public void setP2(Player p) {
		this.p2 = new DuelPlayer(p);
	}
	
	public boolean sendPlayerMessages(String m) {
		if (p1 != null && p2 != null) {
			p1.getPlayer().sendMessage(m);
			p2.getPlayer().sendMessage(m);
			return true;
		}
		return false;
	}

	public boolean teleportPlayers() {
		if (p1 != null && p2 != null) {
			p1.getPlayer().teleport(this.spawn1);
			p2.getPlayer().teleport(this.spawn2);
			return true;
		}
		return false;
	}

	public boolean healPlayers() {
		if (p1 != null && p2 != null) {
			p1.getPlayer().setHealth(20D);
			p2.getPlayer().setHealth(20D);
			return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return (p1 == null && p2 == null);
	}

	public boolean containsPlayer(Player player) {
		return ((p1 != null && p1.getPlayer() == player) || (p2 != null && p2.getPlayer() == player));
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if (containsPlayer(e.getPlayer())) {
			leave(e.getPlayer());
		}
	}

	public void leave(Player p) {
		if (p1 != null && p2 != null) {
			stop();
		}
		if (p1.getPlayer().equals(p)) {
			p2.getPlayer().sendMessage(ChatColor.RED + "The other player left!");
		} else {
			p1.getPlayer().sendMessage(ChatColor.RED + "The other player left!");
		}
		p1 = null;
		p2 = null;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		if (containsPlayer(player)) {
			e.getDrops().clear();
			if (p1.getPlayer().equals(player) && p2 != null) {
				p2.getPlayer().sendMessage(ChatColor.BLUE + "You won the duel and received $" + stake + "!");
				getEconomy().depositPlayer(p2.getPlayer(), stake);
				getEconomy().withdrawPlayer(p1.getPlayer(), stake);
			} else if (p2.getPlayer().equals(player) && p1 != null) {
				p1.getPlayer().sendMessage(ChatColor.BLUE + "You won the duel and received $" + stake + "!");
				getEconomy().depositPlayer(p1.getPlayer(), stake);
				getEconomy().withdrawPlayer(p2.getPlayer(), stake);
			}
			stop();
		}
	}

	public void stop() {
		if (p1 != null && p2 != null) {
			healPlayers();
			p1.getPlayer().teleport(p1.prevLoc);
			p2.getPlayer().teleport(p2.prevLoc);
			Bukkit.getScheduler().scheduleSyncDelayedTask(getMain(), new Runnable() {
				public void run() {
					p1.resetInv();
					p2.resetInv();
					p1 = null;
					p2 = null;
				}
			});
		} else {
			p1 = null;
			p2 = null;
		}
	}

	public void start() {
		final Main plugin = getMain();
		String teleportMessage = plugin.getConfig().getString("duels_teleport_message");
		teleportMessage = parseColors(teleportMessage)[0]; 
		sendPlayerMessages(teleportMessage);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				p1.getPlayer().getInventory().clear();
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kit " + plugin.getConfig().getString("duels_kit") + " " + p1.getPlayer().getName());
				p2.getPlayer().getInventory().clear();
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kit " + plugin.getConfig().getString("duels_kit") + " " + p2.getPlayer().getName());
				healPlayers();
				teleportPlayers();
				p1.getPlayer().setGameMode(GameMode.SURVIVAL);
				p2.getPlayer().setGameMode(GameMode.SURVIVAL);
			}
		}, (long) plugin.getConfig().getDouble("duels_teleport_delay") * 20);
	}

	public Player getOther(Player p){
		if(p1.getPlayer() == p){
			return p2.getPlayer();
		}
		return p1.getPlayer();
	}
	
}
