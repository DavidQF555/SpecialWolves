package com.david.specialwolves.wolves;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Snowball Wolf")
public class SnowballWolf extends ProjectileWolf {

	public SnowballWolf(World world) {
		super(world);
	}

	@Override
	public void onAttackEntity(Entity target) {
		if(target instanceof EntityLiving) {
			((EntityLiving) target).addEffect(new MobEffect(MobEffectList.fromId(2), 200, 2), Cause.ATTACK);
		}
	}

	@Override
	public long getCooldown() {
		return 10;
	}

	@Override
	public Entity getProjectile() {
		return new EntitySnowball(EntityTypes.SNOWBALL, world);
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof SnowballWolf;
	}

	public static Material getIcon() {
		return Material.SNOW_BLOCK;
	}
}
