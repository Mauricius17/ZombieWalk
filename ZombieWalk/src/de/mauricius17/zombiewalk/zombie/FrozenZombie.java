package de.mauricius17.zombiewalk.zombie;

import de.mauricius17.zombiewalk.utils.Utils;
import net.minecraft.server.v1_8_R3.DamageSource;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.World;

public class FrozenZombie extends EntityZombie {

	public FrozenZombie(World world) {
		super(world);
	}
	
	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
		return false;
	}
	
	@Override 
	public void m() {
		if(Utils.getLocation() != null) {
			super.m();
			((EntityInsentient) (bukkitEntity).getHandle()).getNavigation().a(Utils.getLocation().getX(), Utils.getLocation().getY(), Utils.getLocation().getZ(), 1.3);
		}
	}
}
