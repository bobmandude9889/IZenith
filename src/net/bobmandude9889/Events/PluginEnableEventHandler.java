package net.bobmandude9889.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public class PluginEnableEventHandler implements Listener{
	
	EventRegisterer er = null;
	
	public PluginEnableEventHandler(EventRegisterer er){
		this.er = er;
	}
	
	@EventHandler
	public void onPluginEnable(PluginEnableEvent e){
		if(e.getPlugin().getName().equals("CustomGUIAPI")){
			er.registerGUIEvents();
		}
	}

}
