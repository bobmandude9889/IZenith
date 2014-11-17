package net.bobmandude9889.Events;

import net.bobmandude9889.Commands.Setyoutube;
import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerCommandPreprocessEventHandler extends IZUtil implements Listener {
	JavaPlugin plugin = getMain();
	Variables vars = getVars();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		String m = e.getMessage();
		String[] aM = m.split(" ");
		if (aM[0].equalsIgnoreCase("/plugins") || aM[0].equalsIgnoreCase("/pl") && !e.getPlayer().isOp()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "You do not have access to that!");
		} else if (m.startsWith("/killall all") || m.startsWith("/killall itemframe")) {
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot kill that type!");
			e.setCancelled(true);
		} else if (m.startsWith("/kit") && e.getPlayer().getWorld().getName().equals("plotworld") && !e.getPlayer().isOp()) {
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot use \"/kit\" in the plotworld");
			e.setCancelled(true);
		} else if (m.startsWith("/sethome") && e.getPlayer().getWorld().getName().equals("plotworld") && !e.getPlayer().isOp()) {
			e.getPlayer().sendMessage(ChatColor.RED + "You cannot use \"/sethome\" in the plotworld");
			e.setCancelled(true);
		} else if (m.startsWith("/pex user") && m.contains("group set")) {
			if (e.getPlayer().isOp()) {
				Player receiver = Bukkit.getPlayer(aM[1]);
				if (PermissionsEx.getUser(receiver).getPrefix().startsWith(parseColors("&f&l(&4You&8Tube&f&l)")[0])) {
					Setyoutube.set(e.getPlayer(), Bukkit.getPlayer(aM[1]));
				}
			}
		} else if (m.startsWith("/kit")) {
			Player player = e.getPlayer();
			if(player.getWorld().equals(Bukkit.getWorld("swlobby"))){
				e.setCancelled(true);
				return;
			}
			if (aM.length < 3 || aM[2] == player.getName()) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " remove essentials.kits." + aM[1]);
			}
		} else if (aM[0].equalsIgnoreCase("/pv") || aM[0].equalsIgnoreCase("/playervaults")){
			Player player = e.getPlayer();
			if(player.getLocation().getWorld().getName().equals("plotworld") && !player.isOp()){
				e.setCancelled(true);
				player.sendMessage(ChatColor.RED + "Player Vaults are disabled in this world!");
			}
		}
	}
}
