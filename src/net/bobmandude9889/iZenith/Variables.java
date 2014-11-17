package net.bobmandude9889.iZenith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.bobmandude9889.Commands.Contest;
import net.bobmandude9889.Commands.Donator;
import net.bobmandude9889.Commands.Duel;
import net.bobmandude9889.Commands.End;
import net.bobmandude9889.Commands.IZCommand;
import net.bobmandude9889.Commands.NoMobs;
import net.bobmandude9889.Commands.NoOutFire;
import net.bobmandude9889.Commands.PayCheck;
import net.bobmandude9889.Commands.Pvp;
import net.bobmandude9889.Commands.Ranks;
import net.bobmandude9889.Commands.SetNoMobs;
import net.bobmandude9889.Commands.Setyoutube;
import net.bobmandude9889.Commands.Spectate;
import net.bobmandude9889.Commands.StartBroadcast;
import net.bobmandude9889.Commands.VoteShop;
import net.bobmandude9889.Duels.Arena;
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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

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
	public GUI voteShopKits;
	public GUI voteShopSpawners;
	public HashMap<Player,Integer> points = new HashMap<Player,Integer>();
	public ScoreboardManager manager = Bukkit.getScoreboardManager();
	public Scoreboard scoreboard = manager.getMainScoreboard();
	public List<Team> teams = new ArrayList<Team>();
	public HashMap<Player,Player> spectate = new HashMap<Player,Player>();
	public List<Arena> arenas = new ArrayList<Arena>();
	public HashMap<Player, Arena> duels = new HashMap<Player, Arena>();
	
	public Variables(JavaPlugin plugin) {
		this.plugin = plugin;
		er = new EventRegisterer(this.plugin, this);
		
		listeners.add(new BlockBreakEventHandler());
		listeners.add(new BlockFadeEventHandler());
		listeners.add(new CreatureSpawnEventHandler());
		listeners.add(new EntityDamageEventHandler());
		listeners.add(new PlayerCommandPreprocessEventHandler());
		listeners.add(new PlayerInteractEventHandler());
		listeners.add(new PlayerJoinEventHandler());
		listeners.add(new PlayerQuitEventHandler());
		listeners.add(new EntityDamageByEntityEventHandler());
		listeners.add(new PluginEnableEventHandler(this.er));
		listeners.add(new BlockPlaceEventHandler());
		listeners.add(new PlayerInteractEntityEventHandler());
		commands.add(new StartBroadcast());
		commands.add(new Donator());
		commands.add(new End());
		commands.add(new NoMobs());
		commands.add(new NoOutFire());
		commands.add(new Pvp());
		commands.add(new Ranks());
		commands.add(new SetNoMobs());
		commands.add(new VoteShop());
		commands.add(new Setyoutube());
		commands.add(new PayCheck());
		commands.add(new Spectate());
		commands.add(new Duel());
		commands.add(new Contest());
	}

}
