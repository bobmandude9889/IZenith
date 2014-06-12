package net.bobmandude9889.Methods;

import org.bukkit.Location;

public class IsIn {
	public static boolean isIn(Location loc1, Location loc2, Location loc3) {
		int n = 0;
		if (loc2.getBlockX() > loc3.getBlockX()
				&& loc1.getBlockX() >= loc3.getBlockX()
				&& loc1.getBlockX() <= loc2.getBlockX()) {
			n++;
		} else if (loc1.getBlockX() >= loc2.getBlockX()
				&& loc1.getBlockX() <= loc3.getBlockX()) {
			n++;
		}
		if (loc2.getBlockY() > loc3.getBlockY()
				&& loc1.getBlockY() >= loc3.getBlockY()
				&& loc1.getBlockY() <= loc2.getBlockY()) {
			n++;
		} else if (loc1.getBlockY() >= loc2.getBlockY()
				&& loc1.getBlockY() <= loc3.getBlockY()) {
			n++;
		}
		if (loc2.getBlockZ() > loc3.getBlockZ()
				&& loc1.getBlockZ() >= loc3.getBlockZ()
				&& loc1.getBlockZ() <= loc2.getBlockZ()) {
			n++;
		} else if (loc1.getBlockZ() >= loc2.getBlockZ()
				&& loc1.getBlockZ() <= loc3.getBlockZ()) {
			n++;
		}
		if (n == 3) {
			return true;
		} else {
			return false;
		}
	}
}
