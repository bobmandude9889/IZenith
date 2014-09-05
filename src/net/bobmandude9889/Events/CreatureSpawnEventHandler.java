package net.bobmandude9889.Events;

import net.bobmandude9889.iZenith.IZUtil;
import net.bobmandude9889.iZenith.Variables;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CreatureSpawnEventHandler extends IZUtil implements Listener {
	JavaPlugin plugin = null;
	Variables vars = null;

	public CreatureSpawnEventHandler() {
		this.plugin = getMain();
		this.vars = getVars();
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		Location location = event.getLocation();
		if (plugin.getConfig().contains("no_mob_area")) {
			if (!((event.getEntityType().equals(EntityType.COW)
					|| event.getEntityType().equals(EntityType.PIG) || event
					.getEntityType().equals(EntityType.SHEEP))
					&& location.getWorld().getName().equals("survival") && (isIn(location, new Location(
								location.getWorld(), 0, 73, 85),
								new Location(location.getWorld(), 23, 74,
										95)) || isIn(location, new Location(
												location.getWorld(), 57, 74, 95),
												new Location(location.getWorld(), 80, 73,
														85))))
					&& isIn(
							new Location(location.getWorld(), location
									.getBlockX(), location.getBlockY(),
									location.getBlockZ()),
							new Location(location.getWorld(), plugin
									.getConfig().getDouble("no_mob_area.s1.x"),
									plugin.getConfig().getDouble(
											"no_mob_area.s1.y"), plugin
											.getConfig().getDouble(
													"no_mob_area.s1.z")),
							new Location(location.getWorld(), plugin
									.getConfig().getDouble("no_mob_area.s2.x"),
									plugin.getConfig().getDouble(
											"no_mob_area.s2.y"), plugin
											.getConfig().getDouble(
													"no_mob_area.s2.z")))) {
				event.setCancelled(true);
			}
		}
	}
}
