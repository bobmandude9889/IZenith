package net.bobmandude9889.Events;

import net.bobmandude9889.GUI.GUI;
import net.bobmandude9889.GUI.GUIHandler;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EventRegisterer {
	JavaPlugin plugin = null;
	Variables vars = null;
	PluginManager pm = null;

	public EventRegisterer(JavaPlugin plugin, Variables vars) {
		this.plugin = plugin;
		this.vars = vars;
		pm = plugin.getServer().getPluginManager();
	}

	public void registerEvents() {
		for (int i = 0; i < vars.listeners.size(); i++) {
			pm.registerEvents(vars.listeners.get(i), plugin);
		}
		plugin.getLogger().info("[iZenith] Registered events");
	}
	
	public void registerGUIEvents(){
		vars.handler = new GUIHandler(plugin);
		vars.donate = new GUI(9, ChatColor.GREEN + "Donate", vars.handler);
		vars.vote = new GUI(9, ChatColor.GREEN + "Vote", vars.handler);
		pm.registerEvents(vars.handler, plugin);
	}
	
}
