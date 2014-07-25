package net.bobmandude9889.iZenith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.bobmandude9889.Commands.Donator;
import net.bobmandude9889.Commands.End;
import net.bobmandude9889.Commands.IZCommand;
import net.bobmandude9889.Commands.NoMobs;
import net.bobmandude9889.Commands.NoOutFire;
import net.bobmandude9889.Commands.Pvp;
import net.bobmandude9889.Commands.Ranks;
import net.bobmandude9889.Commands.SetNoMobs;
import net.bobmandude9889.Commands.Setyoutube;
import net.bobmandude9889.Commands.StartBroadcast;
import net.bobmandude9889.Commands.VoteShop;
import net.bobmandude9889.Events.BlockBreakEventHandler;
import net.bobmandude9889.Events.BlockFadeEventHandler;
import net.bobmandude9889.Events.BlockPlaceEventHandler;
import net.bobmandude9889.Events.CreatureSpawnEventHandler;
import net.bobmandude9889.Events.EntityDamageByEntityEventHandler;
import net.bobmandude9889.Events.EntityDamageEventHandler;
import net.bobmandude9889.Events.EventRegisterer;
import net.bobmandude9889.Events.PlayerCommandPreprocessEventHandler;
import net.bobmandude9889.Events.PlayerInteractEntityEventHandler;
import net.bobmandude9889.Events.PlayerInteractEventHandler;
import net.bobmandude9889.Events.PlayerJoinEventHandler;
import net.bobmandude9889.Events.PlayerQuitEventHandler;
import net.bobmandude9889.Events.PluginEnableEventHandler;
import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.GUI.GUIHandler;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Variables {

	public final List<Player> selectors = new ArrayList<Player>();
	public final HashMap<String, Location> selection1 = new HashMap<String, Location>();
	public final HashMap<String, Location> selection2 = new HashMap<String, Location>();
	public List<Location> fires = null;
	public GUIHandler handler = null;
	public GUI donate = null;
	public GUI vote = null;
	public EventRegisterer er = null;
	public int broadcastMessage = 0;
	public JavaPlugin plugin;
	public List<Listener> listeners = new ArrayList<Listener>();
	public List<IZCommand> commands = new ArrayList<IZCommand>();
	public List<Player> createVoteShop = new ArrayList<Player>();
	public List<Player> deleteVoteShop = new ArrayList<Player>();
	public FileConfiguration voteShopConfig;
	public GUI voteShopMain;
	public List<GUI> voteShopKits;
	public List<GUI> voteShopRanks;
	public GUI voteShopSpawners;
	public GUI voteShopWeapons;
	public HashMap<Player,Integer> points = new HashMap<Player,Integer>();

	public Variables(JavaPlugin plugin) {
		this.plugin = plugin;
		er = new EventRegisterer(this.plugin, this);
		
		listeners.add(new BlockBreakEventHandler(this.plugin, this));
		listeners.add(new BlockFadeEventHandler(this.plugin, this));
		listeners.add(new CreatureSpawnEventHandler(this.plugin, this));
		listeners.add(new EntityDamageEventHandler(this.plugin, this));
		listeners.add(new PlayerCommandPreprocessEventHandler(this.plugin, this));
		listeners.add(new PlayerInteractEventHandler(this.plugin, this));
		listeners.add(new PlayerJoinEventHandler(this.plugin, this));
		listeners.add(new PlayerQuitEventHandler(this.plugin, this));
		listeners.add(new EntityDamageByEntityEventHandler(this));
		listeners.add(new PluginEnableEventHandler(er));
		listeners.add(new BlockPlaceEventHandler(this));
		listeners.add(new PlayerInteractEntityEventHandler(this));
		commands.add(new StartBroadcast((Main) this.plugin));
		commands.add(new Donator());
		commands.add(new End());
		commands.add(new NoMobs(this));
		commands.add(new NoOutFire());
		commands.add(new Pvp());
		commands.add(new Ranks(this.plugin, this));
		commands.add(new SetNoMobs(this.plugin, this));
		commands.add(new VoteShop(this));
		commands.add(new Setyoutube());
	}

}
