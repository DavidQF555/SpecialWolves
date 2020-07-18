package com.david.specialwolves.wolves;

import java.util.*;

import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Fire Wolf")
public class FireWolf extends SpecialWolf {

	public FireWolf(World world) {
		super(world);
	}

	@Override
	public void onAttackEntity(Entity target) {
		target.setOnFire(200, true);
	}

	@Override
	public void onDamaged(final DamageSource damagesource, float f) {
		Entity e = damagesource.getEntity();
		if(e != null) {
			e.setOnFire(200, true);
		}
	}

	@Override
	public List<DamageSource> immunities() {
		return new ArrayList<DamageSource>(Arrays.asList(DamageSource.LAVA, DamageSource.FIRE));
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof FireWolf;
	}

	public static Material getIcon() {
		return Material.BLAZE_POWDER;
	}
}