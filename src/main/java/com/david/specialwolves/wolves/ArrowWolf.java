package com.david.specialwolves.wolves;

import org.bukkit.Material;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Arrow Wolf")
public class ArrowWolf extends ProjectileWolf {

	public ArrowWolf(World world) {
		super(world);
	}

	@Override
	public Entity getProjectile() {
		ItemStack itemstack = f(b(ProjectileHelper.a(this, Items.BOW)));
		return ProjectileHelper.a(this, itemstack, 1);
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof ArrowWolf;
	}

	public static Material getIcon() {
		return Material.ARROW;
	}

}
