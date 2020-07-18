package com.david.specialwolves.wolves;

import java.util.*;

import net.minecraft.server.v1_15_R1.*;

public abstract class SpecialWolf extends EntityWolf {

	public SpecialWolf(World world) {
		super(EntityTypes.WOLF, world);
	}

	public void spawn(Vec3D loc) {
		setPosition(loc.x, loc.y, loc.z);
		world.addEntity(this);
	}

	public List<DamageSource> immunities() {
		return new ArrayList<DamageSource>();
	}

	public void onDamaged(final DamageSource damagesource, float f) {}

	public abstract boolean sameType(Entity entity);

	public void onAttackEntity(Entity target) {}

	public void spawn(double x, double y, double z) {}

	@Override
	public boolean isInvulnerable(DamageSource damagesource) {
		return immunities().contains(damagesource) || (isInvulnerable() && damagesource != DamageSource.OUT_OF_WORLD && !damagesource.v());
	}

	@Override
	public boolean B(Entity entity) {
		boolean attack = super.B(entity);
		if(attack) {
			onAttackEntity(entity);
		}
		return attack;
	}

	@Override
	protected boolean damageEntity0(final DamageSource damagesource, float f) {
		boolean ret = super.damageEntity0(damagesource, f);
		onDamaged(damagesource, f);
		return ret;
	}

	@Override
	public boolean mate(EntityAnimal entityanimal) {
		if (entityanimal == this) {
			return false;
		} else if (!this.isTamed()) {
			return false;
		} else if (!sameType(entityanimal)) {
			return false;
		} else {
			SpecialWolf entitywolf = (SpecialWolf) entityanimal;
			return !entitywolf.isTamed() ? false : (entitywolf.isSitting() ? false : this.isInLove() && entitywolf.isInLove());
		}
	}

	@Override
	public EntityWolf createChild(EntityAgeable entityageable) {
		SpecialWolf entitywolf = null;
		try {
			entitywolf = (SpecialWolf) getClass().getConstructor(World.class).newInstance(world);
		}
		catch(Exception e) {
			return null;
		}
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			entitywolf.setOwnerUUID(uuid);
			entitywolf.setTamed(true);
		}
		return entitywolf;
	}
}