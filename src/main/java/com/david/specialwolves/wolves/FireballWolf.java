package com.david.specialwolves.wolves;

import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Fireball Wolf")
public class FireballWolf extends ProjectileWolf {

	public FireballWolf(World world) {
		super(world);
	}

	@Override
	public Entity getProjectile() {
		return new EntityLargeFireball(EntityTypes.FIREBALL, world);
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof FireballWolf;
	}

	public static Material getIcon() {
		return Material.GUNPOWDER;
	}

}
