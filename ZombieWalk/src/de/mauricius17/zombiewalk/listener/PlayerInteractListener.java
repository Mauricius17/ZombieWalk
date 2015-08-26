package de.mauricius17.zombiewalk.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEvent;

import de.mauricius17.zombiewalk.utils.Utils;
import de.mauricius17.zombiewalk.zombie.FrozenZombie;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.World;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getItem() != null) {
				if(e.getItem().getType().equals(Material.STICK)) {
					spawnFrozenVillager(e.getPlayer(), "Zombietyp");
					e.getPlayer().sendMessage("§6Frozen Zombie gespawnt!");
				}	
			}
			
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Utils.setLocation(e.getClickedBlock().getLocation());
			}
		}
	}
	
	public static void walkToLocation(Creature creature, Location location, double speed) {
        ((EntityInsentient) ((CraftEntity) creature).getHandle()).getNavigation().a(location.getX(), location.getY(), location.getZ(), speed);
	}
	
	private void spawnFrozenVillager(Player p, String name) {
				
		World mcWorld = ((CraftWorld)p.getLocation().getWorld()).getHandle();
		FrozenZombie zombie = new FrozenZombie(mcWorld);
		
		zombie.setPosition(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ());
		
		zombie.setCustomName(name);
		zombie.setCustomNameVisible(true);

		mcWorld.addEntity(zombie, SpawnReason.CUSTOM);	
	}
}