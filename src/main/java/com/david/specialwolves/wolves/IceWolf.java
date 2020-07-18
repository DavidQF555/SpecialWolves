package com.david.specialwolves.wolves;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityPotionEffectEvent.Cause;

import net.minecraft.server.v1_15_R1.*;

@CustomWolf(name = "Ice Wolf")
public class IceWolf extends SpecialWolf {

	public IceWolf(World world) {
		super(world);
	}

	protected static void addFreeze(EntityLiving liv) {
		int max = 1;
		for(MobEffect eff : liv.getEffects()) {
			if(eff.getMobEffect() == MobEffectList.fromId(2) && eff.getAmplifier() > max) {
				max = eff.getAmplifier();
			}
			if(eff.getMobEffect() == MobEffectList.fromId(4) && eff.getAmplifier() > max) {
				max = eff.getAmplifier();
			}
		}
		liv.addEffect(new MobEffect(MobEffectList.fromId(2), max * 100, max + 1), Cause.ATTACK);
		liv.addEffect(new MobEffect(MobEffectList.fromId(4), max * 100, max + 1), Cause.ATTACK);
		if(max > 9) {
			liv.addEffect(new MobEffect(MobEffectList.fromId(8), max * 50, 128), Cause.ATTACK);
		}
	}

	@Override
	public void onAttackEntity(Entity target) {
		if(target instanceof EntityLiving) {
			addFreeze((EntityLiving) target);
		}
	}

	@Override
	public boolean sameType(Entity entity) {
		return entity instanceof IceWolf;
	}

	public static Material getIcon() {
		return Material.ICE;
	}
}
