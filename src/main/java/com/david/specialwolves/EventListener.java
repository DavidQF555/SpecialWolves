package com.david.specialwolves;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.david.specialwolves.wolves.SpecialWolf;

import net.minecraft.server.v1_15_R1.*;

public class EventListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent event) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Player p = (Player) event.getWhoClicked();
		if(event.getInventory().equals(Main.menu)) {
			String name = event.getCurrentItem().getItemMeta().getDisplayName();
			for(String wolf : Main.wolves.keySet()) {
				if(name.equals(wolf)) {
					SpecialWolf w = Main.wolves.get(wolf).getConstructor(World.class).newInstance(((CraftWorld) p.getWorld()).getHandle());
					Location loc = p.getLocation();
					w.spawn(new Vec3D(loc.getX(), loc.getY(), loc.getZ()));
				}
			}
			event.setCancelled(true);
		}
	}
}
