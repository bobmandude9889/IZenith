package net.bobmandude9889.iZenith;

import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

public class BroadcastingTask extends Util{
	
	JavaPlugin plugin = null;
	Variables vars = null;
	
	public BroadcastingTask(JavaPlugin plugin, Variables vars){
		this.plugin = plugin;
		this.vars = vars;
	}
	
	public void start(){
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable(){
			@Override
			public void run() {
				List<String> messages = plugin.getConfig().getStringList("broadcast_messages");
				String[] message = parseColors(messages.get(vars.broadcastMessage));
				for(int i = 0 ; i < message.length ; i++){
					plugin.getServer().broadcastMessage(message[i]);
				}
				if((vars.broadcastMessage + 1) == plugin.getConfig().getStringList("broadcast_messages").size()){
					vars.broadcastMessage = 0;
				} else {
					vars.broadcastMessage++;
				}
			}
		}, 0L, 2400L);
	}
	
}
