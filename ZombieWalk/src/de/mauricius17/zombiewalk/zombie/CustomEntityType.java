package de.mauricius17.zombiewalk.zombie;

import java.lang.reflect.Field;
import java.util.Map;

import org.bukkit.entity.EntityType;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.EntityZombie;

public enum CustomEntityType {

	FORZENZOMBIE("FrozenZombie", 54, EntityType.ZOMBIE, EntityZombie.class, FrozenZombie.class);
	
	private String name;
	private int id;
	private EntityType entityType;
	private Class<? extends Entity> nmsClass;
	private Class<? extends Entity> customClass;
	
	private CustomEntityType(String name, int id, EntityType entityType, Class<? extends Entity> nmsClass,
		    Class<? extends Entity> customClass) {
		this.name = name;
	    this.id = id;
	    this.entityType = entityType;
	    this.nmsClass = nmsClass;
	    this.customClass = customClass;
	}
	
	public Class<? extends Entity> getCustomClass() {
		return customClass;
	}
	
	public EntityType getEntityType() {
		return entityType;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<? extends Entity> getNmsClass() {
		return nmsClass;
	}
	
	/**
	 * Register our entities
	 */
	
	public static void registerEntities() {
		for(CustomEntityType entity : values()) {
			a(entity.getCustomClass(), entity.getName(), entity.getId());
		}
	}
	
	/**
     * A convenience method.
     * @param clazz The class.
     * @param f The string representation of the private static field.
     * @return The object found
     * @throws Exception if unable to get the object.
     */
	
	private static Object getPrivateStatic(Class<?> clazz, String f) throws Exception {
		Field field = clazz.getDeclaredField(f);
		field.setAccessible(true);
		
		return field.get(null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void a(Class<?> paramClass, String paramString, int paramInt) {
		try {
			((Map) getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
			((Map) getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
		    ((Map) getPrivateStatic(EntityTypes.class, "e")).put(Integer.valueOf(paramInt), paramClass);
		    ((Map) getPrivateStatic(EntityTypes.class, "f")).put(paramClass, Integer.valueOf(paramInt));
		    ((Map) getPrivateStatic(EntityTypes.class, "g")).put(paramString, Integer.valueOf(paramInt));
		} catch (Exception e) {}
	}
}