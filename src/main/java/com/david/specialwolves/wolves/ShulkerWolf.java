package com.david.specialwolves.wolves;

import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Shulker Wolf")
public class ShulkerWolf extends ProjectileWolf {

	public ShulkerWolf(World world) {
		super(world);
	}

	@Override
	public void launchProjectile(Entity e) {
		EntityShulkerBullet proj = (EntityShulkerBullet) getProjectile();
		proj.setTarget(e);
		proj.setPosition(locX(), getHeadY(), locZ());
		world.addEntity(proj);
	}

	@Override
	public Entity getProjectile() {
		return new EntityShulkerBullet(EntityTypes.SHULKER_BULLET, world);
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof ShulkerWolf;
	}

	public static Material getIcon() {
		return Material.SHULKER_SHELL;
	}

}
