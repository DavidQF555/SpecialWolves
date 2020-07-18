package com.david.specialwolves.wolves;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Poison Wolf")
public class PoisonousWolf extends SpecialWolf {

	public PoisonousWolf(World world) {
		super(world);
	}

	@Override
	public void onAttackEntity(Entity target) {
		if(target instanceof EntityLiving) {
			((EntityLiving) target).addEffect(new MobEffect(MobEffectList.fromId(19), 200, 2), Cause.ATTACK);
		}
	}

	@Override
	public List<DamageSource> immunities(){
		return new ArrayList<DamageSource>(Arrays.asList(DamageSource.WITHER));
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof PoisonousWolf;
	}

	public static Material getIcon() {
		return Material.POISONOUS_POTATO;
	}
}
