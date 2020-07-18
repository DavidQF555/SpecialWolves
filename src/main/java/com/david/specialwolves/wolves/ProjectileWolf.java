package com.david.specialwolves.wolves;

import net.minecraft.server.v1_15_R1.*;

public abstract class ProjectileWolf extends SpecialWolf {

	protected long cooldown;

	public ProjectileWolf(World world) {
		super(world);
		cooldown = 0;
	}

	public void launchProjectile(Entity e) {
		launchProjectile(e.locX(), e.getHeadY(), e.locZ());
	}

	public void launchProjectile(double x, double y, double z) {
		Entity proj = getProjectile();
		proj.setPosition(locX(), getHeadY(), locZ());
		double vX = x - locX();
		double vY = y - getHeadY();
		double vZ = z - locZ();
		if(!proj.isNoGravity()) {
			vY += 0.20000000298023224D * (double) MathHelper.sqrt(vX * vX + vZ * vZ);
		}
		proj.setMot(vX, vY, vZ);
		world.addEntity(proj);
	}

	public abstract Entity getProjectile();

	public boolean isRanged() {
		return true;
	}

	public long getCooldown() {
		return 100;
	}

	@Override
	public void tick() {
		super.tick();
		cooldown --;
		EntityLiving target = getGoalTarget();
		if(target != null && !target.equals(getOwner()) && cooldown <= 0) {
			launchProjectile(target);
			cooldown = getCooldown();
		}
	}

	@Override
	protected void initPathfinder() {
		goalSit = new PathfinderGoalSit(this);
		goalSelector.a(1, new PathfinderGoalFloat(this));
		goalSelector.a(2, goalSit);
		goalSelector.a(3, new a<>(this, EntityLlama.class, 24.0F, 1.5D, 1.5D));
		goalSelector.a(4, new PathfinderGoalLeapAtTarget(this, 0.4F));
		goalSelector.a(5, new PathfinderGoalMeleeAttack(this, 1.0D, true));
		goalSelector.a(6, new PathfinderGoalFollowOwner(this, 1.0D, 10.0F, 2.0F, false));
		goalSelector.a(7, new PathfinderGoalBreed(this, 1.0D));
		goalSelector.a(8, new PathfinderGoalRandomStrollLand(this, 1.0D));
		goalSelector.a(9, new PathfinderGoalBeg(this, 8.0F));
		goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
		goalSelector.a(10, new PathfinderGoalRandomLookaround(this));
		targetSelector.a(1, new PathfinderGoalOwnerHurtByTarget(this));
		targetSelector.a(2, new PathfinderGoalOwnerHurtTarget(this));
		targetSelector.a(3, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0])); // CraftBukkit - decompile error
		targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed<>(this, EntityAnimal.class, false, EntityWolf.bz));
		targetSelector.a(4, new PathfinderGoalRandomTargetNonTamed<>(this, EntityTurtle.class, false, EntityTurtle.bw));
		targetSelector.a(5, new PathfinderGoalNearestAttackableTarget<>(this, EntitySkeletonAbstract.class, false));
	}

	private class a<T extends EntityLiving> extends PathfinderGoalAvoidTarget<T> {

		private final EntityWolf j;

		public a(EntityWolf entitywolf, Class<T> oclass, float f, double d0, double d1) {
			super(entitywolf, oclass, f, d0, d1);
			this.j = entitywolf;
		}

		@SuppressWarnings("all")
		@Override
		public boolean a() {
			return super.a() && b instanceof EntityLlama ? !this.j.isTamed() && b((EntityLlama) b) : false;
		}

		private boolean b(EntityLlama entityllama) {
			return entityllama.getStrength() >= random.nextInt(5);
		}

		@Override
		public void c() {
			setGoalTarget(null);
			super.c();
		}

		@Override
		public void e() {
			setGoalTarget(null);
			super.e();
		}
	}
}
