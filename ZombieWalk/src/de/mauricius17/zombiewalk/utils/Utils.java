package de.mauricius17.zombiewalk.utils;

import org.bukkit.Location;

public class Utils {

	private static Location location = null;
	
	public static void setLocation(Location location) {
		Utils.location = location;
	}
	
	public static Location getLocation() {
		return location;
	}
}