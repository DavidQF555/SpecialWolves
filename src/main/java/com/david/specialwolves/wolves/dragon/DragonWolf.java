package com.david.specialwolves.wolves.dragon;

import java.util.*;

import org.bukkit.Material;

import com.david.specialwolves.wolves.*;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Dragon Wolf")
public class DragonWolf extends ProjectileWolf {

	protected DragonMount mount;

	public DragonWolf(World world) {
		super(world);
		mount = new DragonMount(this, world);
	}

	@Override
	public void spawn(Vec3D loc) {
		setPosition(loc.x, loc.y, loc.z);
		mount.setPosition(loc.x, loc.y, loc.z);
		world.addEntity(mount);
		world.addEntity(this);
		startRiding(mount);
	}

	@Override
	public List<DamageSource> immunities(){
		return new ArrayList<DamageSource>(Arrays.asList(DamageSource.FLY_INTO_WALL, DamageSource.STUCK));
	}

	@Override
	public Entity getProjectile() {
		return new EntityDragonFireball(EntityTypes.DRAGON_FIREBALL, world);
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof DragonWolf;
	}

	public static Material getIcon() {
		return Material.DRAGON_BREATH;
	}

}
