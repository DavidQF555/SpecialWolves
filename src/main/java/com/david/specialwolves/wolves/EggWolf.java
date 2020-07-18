package com.david.specialwolves.wolves;

import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Egg Wolf")
public class EggWolf extends ProjectileWolf {

	public EggWolf(World world) {
		super(world);
	}

	@Override
	public Entity getProjectile() {
		return new EntityEgg(EntityTypes.EGG, world);
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof EggWolf;
	}

	public static Material getIcon() {
		return Material.EGG;
	}

}
