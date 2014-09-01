package net.bobmandude9889.Arena;

import net.bobmandude9889.Duels.Arena;
import net.bobmandude9889.Duels.Main;
import net.bobmandude9889.Duels.User;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Arena {

	Location spawn1;
	Location spawn2;
	DuelPlayer p1;
	DuelPlayer p2;

	public static boolean isIn(Player player, Main main) {
		for (Arena a : main.arenas) {
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
		this.p1 = new User(p);
	}

	public void setP2(Player p) {
		this.p2 = new User(p);
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

	@EventHandler
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
				p2.getPlayer().sendMessage(ChatColor.BLUE + "You won the duel!");
			} else if (p2.getPlayer().equals(player) && p1 != null) {
				p1.getPlayer().sendMessage(ChatColor.BLUE + "You won the duel!");
			}
			stop();
		}
	}

	public void stop() {
		if (p1 != null && p2 != null) {
			healPlayers();
			p1.getPlayer().teleport(p1.prevLoc);
			p2.getPlayer().teleport(p2.prevLoc);
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Xarox_Duels"), new Runnable() {
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
		final Main plugin = (Main) Bukkit.getPluginManager().getPlugin("Xarox_Duels");
		String teleportMessage = plugin.getConfig().getString("teleport_message");
		String chars = "1234567890abcdefklmno";
		for (Character c : chars.toCharArray()) {
			teleportMessage = teleportMessage.replace("&" + c, ChatColor.getByChar(c) + "");
		}
		sendPlayerMessages(teleportMessage);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				p1.getPlayer().getInventory().clear();
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kit " + plugin.getConfig().getString("kit") + " " + p1.getPlayer().getName());
				p2.getPlayer().getInventory().clear();
				Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "kit " + plugin.getConfig().getString("kit") + " " + p2.getPlayer().getName());
				healPlayers();
				teleportPlayers();
			}
		}, (long) plugin.getConfig().getDouble("teleport_delay") * 20);
	}

}
