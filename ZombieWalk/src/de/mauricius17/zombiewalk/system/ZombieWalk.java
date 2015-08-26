package de.mauricius17.zombiewalk.system;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;

import de.mauricius17.zombiewalk.zombie.CustomEntityType;

public class ZombieWalk extends JavaPlugin {

	@Override
	public void onEnable() {
		CustomEntityType.registerEntities();
		registerEvents();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		
		try {
			for(ClassPath.ClassInfo classInfo : ClassPath.from(getClassLoader()).getTopLevelClasses("de.mauricius17.zombiewalk.listener")) {
				Class<?> clazz = Class.forName(classInfo.getName());
				
				if(Listener.class.isAssignableFrom(clazz)) {
					pluginManager.registerEvents((Listener) clazz.newInstance(), this);
				}
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		
	}
}