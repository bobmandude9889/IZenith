package net.bobmandude9889.Commands;

import net.bobmandude9889.Duels.Arena;
import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.iZenith.IZUtil;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class Duel extends IZUtil implements IZCommand {

	@Override
	public String getName() {
		return "duel";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			if (Arena.isIn((Player) sender)) {
				if (args[0].equalsIgnoreCase("leave")) {
					Player player = (Player) sender;
					if (Arena.isIn(player)) {
						if (getVars().duels.containsKey(player)) {
							getVars().duels.get(player).leave(player);
							getVars().duels.remove(player);
							for (Arena a : getVars().arenas) {
								if (a.containsPlayer(player)) {
									a.leave(player);
								}
							}
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
					Arena arena = getVars().duels.get(pReceiver);
					arena.setP2(pReceiver);
					open(pReceiver, arena);
					arena.accepted = true;
					return;
				} else if (args[0].equalsIgnoreCase("deny")) {
					Player pReceiver = (Player) sender;
					Player pSender = Bukkit.getPlayer(args[1]);
					if (getVars().duels.containsKey(pReceiver) && getVars().duels.get(pReceiver).containsPlayer(pSender)) {
						Arena arena = getVars().duels.get(pReceiver);
						getVars().duels.remove(pReceiver);
						arena.p1 = null;
						arena.p2 = null;
						pReceiver.sendMessage(ChatColor.RED + "Request denied");
						pSender.sendMessage(ChatColor.RED + "Request denied");
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have a request from " + pSender.getName());
					}
					return;
				} else if (sender.hasPermission("duel.admin")) {
					try {
						if (args[0].equalsIgnoreCase("createarena")) {
							Player player = (Player) sender;
							Arena arena = new Arena();
							getServer().getPluginManager().registerEvents(arena, getMain());
							getVars().arenas.add(arena);
							player.sendMessage(ChatColor.GREEN + "Created arena number " + getVars().arenas.size() + ". use \"/duel setspawn " + getVars().arenas.size() + " [1,2]\" to set spawnpoints");
							return;
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
							Arena arena = getVars().arenas.get(a);
							if (args[2].equalsIgnoreCase("1")) {
								arena.setSpawn1(player.getLocation());
								player.sendMessage(ChatColor.GREEN + "Spawn 1 set.");
							} else {
								arena.setSpawn2(player.getLocation());
								player.sendMessage(ChatColor.GREEN + "Spawn 2 set.");
							}
							return;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						help(sender);
					}
				}

				final Player receiver = getServer().getPlayer(args[0]);
				if (receiver == null) {
					help(sender);
				} else {
					Player player = (Player) sender;
					int a = findArena();
					if (a == -1) {
						player.sendMessage(ChatColor.RED + "All arenas are full");
					}
					final Arena arena = getVars().arenas.get(a);
					arena.stake = Integer.parseInt(args[1]);
					if (arena.stake > getEconomy().getBalance(player)) {
						player.sendMessage(ChatColor.RED + "You do not have the much money.");
						arena.stake = 0;
					} else {
						arena.accepted = false;
						arena.p1 = null;
						arena.p2 = null;
						arena.setP1(player);
						getVars().duels.put(receiver, arena);
						player.sendMessage(ChatColor.BLUE + "Found an empty arena!");
						player.sendMessage(ChatColor.BLUE + "Request sent!");
						receiver.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.BLUE + " requested to duel! Use");
						receiver.sendMessage(ChatColor.YELLOW + "/duel <accept,deny> " + player.getName() + " and select the reward amount.");
						receiver.sendMessage(ChatColor.BLUE + "This request will timeout in " + ChatColor.YELLOW + getMain().getConfig().getInt("duels_request_timeout") + " seconds");
						Bukkit.getScheduler().scheduleSyncDelayedTask(getMain(), new Runnable() {
							public void run() {
								if (getVars().duels.containsKey(receiver) && !getVars().duels.get(receiver).accepted) {
									getVars().duels.remove(receiver);
									arena.p1 = null;
									arena.p2 = null;
									receiver.sendMessage(ChatColor.BLUE + "Request timed out");
								}
							}
						}, (long) getMain().getConfig().getInt("duels_request_timeout") * 20);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			help(sender);
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

	public void open(Player p, Arena a) {
		getGUI(a).open(p);
	}

	public GUI getGUI(final Arena a) {
		final GUI gui = new GUI(27, "Duel", getVars().handler);
		gui.canClose = false;
		gui.addButton(newItemMeta(Material.WOOL, "Accept", null, 1), 0, new Runnable() {
			@Override
			public void run() {
				Player p = gui.getWhoClicked();
				Player oP = a.getOther(p);
				if (a.stakeChanged) {
					if (a.stake > getEconomy().getBalance(p)) {
						p.sendMessage(ChatColor.RED + "You do not have that much money.");
						open(p, a);
						return;
					} else {
						a.stakeChanged = false;
						open(oP, a);
					}
				} else {
					a.start();
					if (getVars().duels.containsKey(p)) {
						a.setP2(p);
						getVars().duels.remove(p);
					} else {
						a.setP2(oP);
						getVars().duels.remove(oP);
					}
				}
				gui.close(p);
			}
		});
		gui.addButton(newItemMeta(Material.WOOL, "Cancel", null, 1, (short) 15), 8, new Runnable() {
			@Override
			public void run() {
				Player p = gui.getWhoClicked();
				Player oP = a.getOther(p);
				a.sendPlayerMessages(ChatColor.RED + "The duel has been cancelled.");
				if (getVars().duels.containsKey(p))
					getVars().duels.remove(p);
				else
					getVars().duels.remove(oP);
				a.p1 = null;
				a.p2 = null;
				gui.close(p);
			}
		});
		for (int i = 0; i < 5; i++) {
			final int n = (int) (Math.pow(10, i));
			gui.addButton(newItemMeta(Material.WOOL, "+" + n, null, 1, (short) 5), 11 + i, new Runnable() {
				@Override
				public void run() {
					a.stake += n;
					getVars().handler.getOpenInvs().get(gui.getWhoClicked()).inv.setItem(4, newItemMeta(Material.DIAMOND, a.stake + "", null, 1));
					a.stakeChanged = true;
				}
			});
			gui.addButton(newItemMeta(Material.WOOL, "-" + n, null, 1, (short) 5), 20 + i, new Runnable() {
				@Override
				public void run() {
					a.stake -= n;
					if (a.stake < 0) {
						a.stake = 0;
					}
					getVars().handler.getOpenInvs().get(gui.getWhoClicked()).inv.setItem(4, newItemMeta(Material.DIAMOND, a.stake + "", null, 1));
					a.stakeChanged = true;
				}
			});
		}
		gui.addButton(newItemMeta(Material.DIAMOND, a.stake + "", null, 1), 4, new Runnable() {
			@Override
			public void run() {
			}
		});
		return gui;
	}

	public void help(CommandSender p){
		p.sendMessage(ChatColor.GRAY + "Duel help");
		p.sendMessage(ChatColor.GREEN + "/duel <player> <reward amount>" + ChatColor.AQUA + " sends duel request to player.");
		p.sendMessage(ChatColor.GREEN + "/duel accept <player>" + ChatColor.AQUA + " accepts duel request from player.");
		p.sendMessage(ChatColor.GREEN + "/duel deny <player>" + ChatColor.AQUA + " denies duel request from player.");
		p.sendMessage(ChatColor.GREEN + "/duel leave" + ChatColor.AQUA + " leaves a duel.");
		if(p.hasPermission("duel.admin")){
			p.sendMessage(ChatColor.GREEN + "/duel createarena" + ChatColor.AQUA + " creates arena.");
			p.sendMessage(ChatColor.GREEN + "/duel setspawn [arena #] [1,2]" + ChatColor.AQUA + " sets spawn 1 or 2 for an arena.");
		}
	}
	
}
