package net.bobmandude9889.Methods;

import net.bobmandude9889.iZenith.Variables;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class ScoreboardHandler {

	public static void init(Variables vars) {
		try {
			vars.scoreboard.registerNewTeam("beginner").setPrefix(ConvertColors.convertColors("&7")[0]);
			vars.scoreboard.registerNewTeam("donator").setPrefix(ConvertColors.convertColors("&d")[0]);
			vars.scoreboard.registerNewTeam("knight").setPrefix(ConvertColors.convertColors("&9")[0]);
			vars.scoreboard.registerNewTeam("slayer").setPrefix(ConvertColors.convertColors("&e")[0]);
			vars.scoreboard.registerNewTeam("dragon").setPrefix(ConvertColors.convertColors("&6")[0]);
			vars.scoreboard.registerNewTeam("lord").setPrefix(ConvertColors.convertColors("&2")[0]);
			vars.scoreboard.registerNewTeam("god").setPrefix(ConvertColors.convertColors("&b")[0]);
			vars.scoreboard.registerNewTeam("mod").setPrefix(ConvertColors.convertColors("&3")[0]);
			vars.scoreboard.registerNewTeam("admin").setPrefix(ConvertColors.convertColors("&5")[0]);
			vars.scoreboard.registerNewTeam("dev").setPrefix(ConvertColors.convertColors("&c")[0]);
			vars.scoreboard.registerNewTeam("owner").setPrefix(ConvertColors.convertColors("&1")[0]);
		} catch (Exception e) {
		}
		for (Team t : vars.scoreboard.getTeams()) {
			vars.teams.add(t);
		}
	}

	public static void setTeam(Player player, Variables vars) {
		for (Team t : vars.teams) {
			if (t.getName().equalsIgnoreCase(IPermission.get().getPrimaryGroup(player))) {
				t.addPlayer(player);
				return;
			}
		}
	}

}
