package com.david.specialwolves.wolves.dragon;

import net.minecraft.server.v1_15_R1.*;

public class DragonMount extends EntityVex {

	protected DragonWolf owner;

	public DragonMount(DragonWolf owner, World world) {
		super(EntityTypes.VEX, world);
		this.owner = owner;
		setInvisible(true);
		setInvulnerable(true);
		setSilent(true);
	}

	@Override
	protected boolean damageEntity0(final DamageSource damagesource, float f) {
		owner.damageEntity(damagesource, f);
		return super.damageEntity0(damagesource, f);
	}

	@Override
	public void tick() {
		if(owner.dead) {
			killEntity();
			return;
		}
		setGoalTarget(owner.getGoalTarget());
		super.tick();
	}

	@Override
	public void initPathfinder() {
		goalSelector.a(8, new PathfinderGoalRandomStrollLand(this, 1.0D));
		goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		goalSelector.a(10, new PathfinderGoalRandomLookaround(this));
		targetSelector.a(3, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
	}
}
