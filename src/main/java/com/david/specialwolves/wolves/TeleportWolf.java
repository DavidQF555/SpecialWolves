package com.david.specialwolves.wolves;

import org.bukkit.Location;
import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;
import net.minecraft.server.v1_15_R1.World;

@CustomWolf(name = "Teleport Wolf")
public class TeleportWolf extends SpecialWolf {

	public TeleportWolf(World world) {
		super(world);
		goalSelector.a(new PathfinderGoalTeleport(this, 16, 3));
	}

	@Override
	public void initPathfinder() {
		super.initPathfinder();
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof TeleportWolf;
	}

	public static Material getIcon() {
		return Material.ENDER_PEARL;
	}

	private class PathfinderGoalTeleport extends PathfinderGoal {

		private EntityInsentient entity;
		private int max;
		private int min;

		public PathfinderGoalTeleport(EntityInsentient e, int max, int min) {
			entity = e;
			this.max = max;
			this.min = min;

		}

		@Override
		public boolean a() {
			EntityLiving target = entity.getGoalTarget();
			if(target != null && entity.getPositionVector().distanceSquared(target.getPositionVector()) > max) {
				return true;
			}
			return false;
		}

		@Override
		public void c() {
			Vec3D loc = getNearby();
			if(loc != null) {
				entity.enderTeleportTo(loc.x, loc.y, loc.z);
			}
		}

		private Vec3D getNearby() {
			Vec3D target = entity.getGoalTarget().getPositionVector();
			int range = min;
			while(range <= max) {
				for(int dY = -range; dY <= range; dY ++) {
					for(int dX = -range; dX <= range; dX ++) {
						for(int dZ = -range; dZ <= range; dZ ++) {
							Vec3D vec = new Vec3D(entity.locX() + dX, entity.locY() + dY, entity.locZ() + dZ);
							if(vec.distanceSquared(target) >= min) {
								Material type = new Location((org.bukkit.World) world, vec.x, vec.y, vec.z).getBlock().getType();
								Material below = new Location((org.bukkit.World) world, vec.x, vec.y - 1, vec.z).getBlock().getType();
								if(type == Material.AIR && below != Material.AIR && below != Material.LAVA) {
									return vec;
								}
							}
						}
					}
				}
				range ++;
			}
			return null;
		}
	}
}
