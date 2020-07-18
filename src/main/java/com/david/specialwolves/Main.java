package com.david.specialwolves;

import java.util.*;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import com.david.specialwolves.wolves.*;

public class Main extends JavaPlugin {

	public static Main plugin;
	public final static Map<String, Class<? extends SpecialWolf>> wolves = new HashMap<String, Class<? extends SpecialWolf>>();
	public static Inventory menu;

	@SuppressWarnings("all")
	public void onEnable() {
		Reflections reflections = new Reflections("com.david.specialwolves");
		Set<Class<?>> set = reflections.getTypesAnnotatedWith(CustomWolf.class);
		menu = getServer().createInventory(null, 54);
		for(Class<?> clazz : set) {
			CustomWolf wolf = clazz.getAnnotation(CustomWolf.class);
			wolves.put(wolf.name(), (Class<? extends SpecialWolf>) clazz);
			ItemStack item = new ItemStack(Material.BONE);
			try {
				item = new ItemStack((Material) clazz.getMethod("getIcon").invoke(null, null));
			}
			catch(Exception e) {}
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(wolf.name());
			item.setItemMeta(meta);
			menu.addItem(item);
		}
		plugin = this;
		getServer().getPluginManager().registerEvents(new EventListener(), plugin);
	}

	public void onDisable() {
		plugin = null;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if(player instanceof Player) {
			String lowerCaseCommand = command.getName().toLowerCase();
			if(lowerCaseCommand.equals("wolf")) {
				if(player.isOp()) {
					player.openInventory(menu);
					return true;
				} 
				player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
				return true;
			}
		} 
		return true;
	}
}