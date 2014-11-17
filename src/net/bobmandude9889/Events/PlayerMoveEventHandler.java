package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveEventHandler extends IZUtil implements Listener{
	
	Variables vars = getVars();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		if(vars.spectate.containsValue(e.getPlayer())){
			e.getPlayer().teleport(find(e.getPlayer()));
		} else if(vars.spectate.containsKey(e.getPlayer())){
			e.setCancelled(true);
		}
	}
	
	private Player find(Player player){
		for(Player p : vars.spectate.keySet()){
			if(vars.spectate.get(p).equals(player)) return p;
		}
		return null;
	}
	
}
