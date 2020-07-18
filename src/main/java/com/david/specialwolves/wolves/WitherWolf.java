package com.david.specialwolves.wolves;

import java.util.*;

import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Wither Wolf")
public class WitherWolf extends ProjectileWolf {

	public WitherWolf(World world) {
		super(world);
	}

	@Override
	public Entity getProjectile() {
		return new EntityWitherSkull(EntityTypes.WITHER_SKULL, world);
	}

	@Override
	public List<DamageSource> immunities(){
		return new ArrayList<DamageSource>(Arrays.asList(DamageSource.WITHER));
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof WitherWolf;
	}

	public static Material getIcon() {
		return Material.WITHER_ROSE;
	}
}
