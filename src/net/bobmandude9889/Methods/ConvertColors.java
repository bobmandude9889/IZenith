package net.bobmandude9889.Methods;

import org.bukkit.ChatColor;

public class ConvertColors {
	public static String[] convertColors(String message) {
		String chars = "0123456789abcdefrmlno";
		for(int i = 0 ; i < chars.length() ; i++){
			message = message.replace("&" + chars.substring(i, i + 1), ChatColor.getByChar(chars.substring(i)) + "");
		}
		String[] messageA = message.split("<next>");
		return messageA;
	}
}
