package net.bobmandude9889.Commands;

import net.bobmandude9889.Duels.Arena;
import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Main;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Duel extends IZUtil implements IZCommand {

	@Override
	public String getName() {
		return "duel";
	}

	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		final Variables vars = getVars();
		final Main main = getMain();
		try {
			if (Arena.isIn((Player) sender)) {
				if (args[0].equalsIgnoreCase("leave")) {
					Player player = (Player) sender;
					if (Arena.isIn(player)) {
						if (vars.duels.containsKey(player)) {
							vars.duels.get(player).leave(player);
						}
					} else {
						player.sendMessage(ChatColor.RED + "You are not in a duel.");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "You are already in a duel");
				}
			} else {
				if (args[0].equalsIgnoreCase("accept")) {
					Player pReceiver = (Player) sender;
					@SuppressWarnings("deprecation")
					Player pSender = Bukkit.getPlayer(args[1]);
					if (vars.duels.containsKey(pReceiver) && vars.duels.get(pReceiver).containsPlayer(pSender)) {
						Arena arena = vars.duels.get(pReceiver);
						arena.setP2(pReceiver);
						arena.start();
						vars.duels.remove(pReceiver);
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have a request from " + pSender.getName());
					}
				} else if (args[0].equalsIgnoreCase("deny")) {
					Player pReceiver = (Player) sender;
					@SuppressWarnings("deprecation")
					Player pSender = Bukkit.getPlayer(args[1]);
					if (vars.duels.get(pReceiver).containsPlayer(pSender)) {
						vars.duels.get(pReceiver).sendPlayerMessages(ChatColor.RED + "Request denied");
						vars.duels.remove((Player) sender);
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have a request from " + pSender.getName());
					}
				} else if (sender.hasPermission("duel.admin")) {
					try {
						if (args[0].equalsIgnoreCase("createarena")) {
							Player player = (Player) sender;
							Arena arena = new Arena();
							getServer().getPluginManager().registerEvents(arena, main);
							vars.arenas.add(arena);
							player.sendMessage(ChatColor.GREEN + "Created arena number " + vars.arenas.size() + ". use \"/xd setspawn " + vars.arenas.size() + " [1,2]\" to set spawnpoints");
						} else if (args[0].equalsIgnoreCase("setspawn")) {
							if (!(sender instanceof Player)) {
								sender.sendMessage(ChatColor.RED + "Players only");
							}
							Player player = (Player) sender;
							int a = 0;
							try {
								a = Integer.parseInt(args[1]) - 1;
							} catch (Exception e) {
								player.sendMessage(ChatColor.RED + "Enter a number");
							}
							Arena arena = vars.arenas.get(a);
							if (args[2].equalsIgnoreCase("1")) {
								arena.setSpawn1(player.getLocation());
								player.sendMessage(ChatColor.GREEN + "Spawn 1 set.");
							} else {
								arena.setSpawn2(player.getLocation());
								player.sendMessage(ChatColor.GREEN + "Spawn 2 set.");
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						sender.sendMessage(ChatColor.RED + "Invalid arguments");
					}
				} else {
					@SuppressWarnings("deprecation")
					final Player receiver = getServer().getPlayer(args[0]);
					if (receiver == null) {
						sender.sendMessage(ChatColor.RED + "Invalid arguments");
					} else {
						if (!(sender instanceof Player)) {
							sender.sendMessage(ChatColor.RED + "Players only");
						}
						Player player = (Player) sender;
						int a = findArena();
						if (a == -1) {
							player.sendMessage(ChatColor.RED + "All arenas are full");
						}
						final Arena arena = vars.arenas.get(a);
						arena.p1 = null;
						arena.p2 = null;
						arena.setP1(player);
						vars.duels.put(receiver, arena);
						player.sendMessage(ChatColor.BLUE + "Found an empty arena!");
						player.sendMessage(ChatColor.BLUE + "Request sent!");
						receiver.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " requested to duel! Use");
						receiver.sendMessage(ChatColor.YELLOW + "/duel <accept,deny> " + player.getName());
						receiver.sendMessage(ChatColor.BLUE + "This request will timeout in " + ChatColor.YELLOW + main.getConfig().getInt("duel_request_timeout") + " seconds");
						Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
							public void run() {
								if (vars.duels.containsKey(receiver) && vars.duels.get(receiver).equals(arena)) {
									vars.duels.remove(receiver);
									arena.p1 = null;
									arena.p2 = null;
									receiver.sendMessage(ChatColor.BLUE + "Request timed out");
								}
							}
						}, (long) main.getConfig().getInt("duel_request_timeout") * 20);
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			sender.sendMessage(ChatColor.RED + "Invalid arguments");
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
