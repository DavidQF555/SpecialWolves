package com.david.specialwolves;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Vex;
import org.bukkit.entity.WitherSkull;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener {

	public static Main instance;
	public Inventory wolfMenu;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Inventory wolfMenu = Bukkit.createInventory(null, 18, "Wolf Menu");
		ItemStack normal = new ItemStack(Material.BONE);
		ItemMeta normalMeta = normal.getItemMeta();
		normalMeta.setDisplayName("Normal");
		normal.setItemMeta(normalMeta);
		ItemStack poison = new ItemStack(Material.POISONOUS_POTATO);
		ItemMeta poisonMeta = poison.getItemMeta();
		poisonMeta.setDisplayName(ChatColor.GREEN + "Poison");
		poison.setItemMeta(poisonMeta);
		ItemStack fire = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta fireMeta = fire.getItemMeta();
		fireMeta.setDisplayName(ChatColor.RED + "Fire");
		fire.setItemMeta(fireMeta);
		ItemStack wither = new ItemStack(Material.SOUL_SAND);
		ItemMeta witherMeta = wither.getItemMeta();
		witherMeta.setDisplayName(ChatColor.DARK_GRAY + "Wither");
		wither.setItemMeta(witherMeta);
		ItemStack ice = new ItemStack(Material.ICE);
		ItemMeta iceMeta = ice.getItemMeta();
		iceMeta.setDisplayName(ChatColor.BLUE + "Ice");
		ice.setItemMeta(iceMeta);
		ItemStack troll = new ItemStack(Material.WEB);
		ItemMeta trollMeta = troll.getItemMeta();
		trollMeta.setDisplayName(ChatColor.MAGIC + "Troll");
		troll.setItemMeta(trollMeta);
		ItemStack snowball = new ItemStack(Material.SNOW_BALL);
		ItemMeta snowballMeta = snowball.getItemMeta();
		snowballMeta.setDisplayName(ChatColor.WHITE + "Snowball");
		snowball.setItemMeta(snowballMeta);
		ItemStack shulker = new ItemStack(Material.SHULKER_SHELL);
		ItemMeta shulkerMeta = shulker.getItemMeta();
		shulkerMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Shulker");
		shulker.setItemMeta(shulkerMeta);
		ItemStack fireball = new ItemStack(Material.FIREBALL);
		ItemMeta fireballMeta = fireball.getItemMeta();
		fireballMeta.setDisplayName(ChatColor.DARK_RED + "Fireball");
		fireball.setItemMeta(fireballMeta);
		ItemStack egg = new ItemStack(Material.EGG);
		ItemMeta eggMeta = egg.getItemMeta();
		eggMeta.setDisplayName(ChatColor.YELLOW + "Egg");
		egg.setItemMeta(eggMeta);
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arrowMeta = arrow.getItemMeta();
		arrowMeta.setDisplayName(ChatColor.WHITE + "Arrow");
		arrow.setItemMeta(arrowMeta);
		ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
		ItemMeta teleportMeta = teleport.getItemMeta();
		teleportMeta.setDisplayName(ChatColor.DARK_PURPLE + "Teleport");
		teleport.setItemMeta(teleportMeta);
		ItemStack dragon = new ItemStack(Material.DRAGON_EGG);
		ItemMeta dragonMeta = dragon.getItemMeta();
		dragonMeta.setDisplayName(ChatColor.BLACK + "Dragon");
		dragon.setItemMeta(dragonMeta);
		wolfMenu.addItem(new ItemStack[] {normal});
		wolfMenu.addItem(new ItemStack[] {poison});
		wolfMenu.addItem(new ItemStack[] {fire});
		wolfMenu.addItem(new ItemStack[] {wither});
		wolfMenu.addItem(new ItemStack[] {ice});
		wolfMenu.addItem(new ItemStack[] {troll});
		wolfMenu.addItem(new ItemStack[] {snowball});
		wolfMenu.addItem(new ItemStack[] {shulker});
		wolfMenu.addItem(new ItemStack[] {fireball});
		wolfMenu.addItem(new ItemStack[] {egg});
		wolfMenu.addItem(new ItemStack[] {arrow});
		wolfMenu.addItem(new ItemStack[] {teleport});
		wolfMenu.addItem(new ItemStack[] {dragon});
		this.wolfMenu = wolfMenu;
		instance = this;
	}

	public void onDisable() {}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if(player instanceof Player) {
			String lowerCaseCommand = command.getName().toLowerCase();
			if(lowerCaseCommand.equals("wolf")) {
				if(player.isOp()) {
					player.openInventory(this.wolfMenu);
					return true;
				} 
				player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use this command");
				return true;
			}
		} 
		return true;
	}

	@EventHandler
	public void spawnWolfCheck(InventoryClickEvent e) {
		Player user = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		if(e.getInventory().equals(this.wolfMenu)) {
			user.sendMessage("in wolfMenu");
			if(item.getData().getItemType() == Material.BONE) {
				user.getLocation().getWorld().spawnEntity(user.getLocation(), EntityType.WOLF);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.POISONOUS_POTATO) {
				spawnPoisonWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.BLAZE_POWDER) {
				spawnFireWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.SOUL_SAND) {
				spawnWitherWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.ICE) {
				spawnIceWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.WEB) {
				spawnTrollWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.SNOW_BALL) {
				spawnSnowballWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.SHULKER_SHELL) {
				spawnShulkerWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.FIREBALL) {
				spawnFireballWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.EGG) {
				spawnEggWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.ARROW) {
				spawnArrowWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.ENDER_PEARL) {
				spawnTeleportWolf(instance, user);
				e.setCancelled(true);
			}
			else if(item.getData().getItemType() == Material.DRAGON_EGG) {
				spawnDragonWolf(instance, user);
				e.setCancelled(true);
			} 
			e.setCancelled(true);
		} 
	}

	public void spawnPoisonWolf(Main plugin, Entity entity) {
		Wolf poison = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		poison.setMetadata("wolfType", new FixedMetadataValue(plugin, "poison"));
		poison.setCollarColor(DyeColor.LIME);
		poison.setCustomName(ChatColor.GREEN + "Poison Wolf");
		poison.setCustomNameVisible(false);
	}

	public void spawnFireWolf(Main plugin, Entity entity) {
		Wolf fire = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		fire.setMetadata("wolfType", new FixedMetadataValue(plugin, "fire"));
		fire.setCollarColor(DyeColor.ORANGE);
		fire.setCustomName(ChatColor.RED + "Fire Wolf");
		fire.setCustomNameVisible(false);
	}

	public void spawnWitherWolf(Main plugin, Entity entity) {
		Wolf wither = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		wither.setMetadata("wolfType", new FixedMetadataValue(plugin, "wither"));
		wither.setCollarColor(DyeColor.BLACK);
		wither.setCustomName(ChatColor.DARK_GRAY + "Wither Wolf");
		wither.setCustomNameVisible(false);
	}

	public void spawnIceWolf(Main plugin, Entity entity) {
		Wolf ice = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		ice.setMetadata("wolfType", new FixedMetadataValue(plugin, "ice"));
		ice.setCollarColor(DyeColor.LIGHT_BLUE);
		ice.setCustomName(ChatColor.BLUE + "Ice Wolf");
		ice.setCustomNameVisible(false);
	}

	public void spawnTrollWolf(Main plugin, Entity entity) {
		Wolf troll = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		troll.setMetadata("wolfType", new FixedMetadataValue(plugin, "troll"));
		troll.setCollarColor(DyeColor.RED);
		troll.setCustomName(ChatColor.MAGIC + "Troll Wolf");
		troll.setCustomNameVisible(false);
	}

	public void spawnSnowballWolf(Main plugin, Entity entity) {
		Wolf snowball = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		snowball.setMetadata("wolfType", new FixedMetadataValue(plugin, "snowball"));
		snowball.setCollarColor(DyeColor.WHITE);
		snowball.setCustomName(ChatColor.WHITE + "Snowball Wolf");
		snowball.setCustomNameVisible(false);
	}

	public void spawnShulkerWolf(Main plugin, Entity entity) {
		Wolf shulker = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		shulker.setMetadata("wolfType", new FixedMetadataValue(plugin, "shulker"));
		shulker.setCollarColor(DyeColor.PINK);
		shulker.setCustomName(ChatColor.LIGHT_PURPLE + "Shulker Wolf");
		shulker.setCustomNameVisible(false);
	}

	public void spawnFireballWolf(Main plugin, Entity entity) {
		Wolf fireball = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		fireball.setMetadata("wolfType", new FixedMetadataValue(plugin, "fireball"));
		fireball.setCollarColor(DyeColor.RED);
		fireball.setCustomName(ChatColor.DARK_RED + "Fireball Wolf");
		fireball.setCustomNameVisible(false);
	}

	public void spawnEggWolf(Main plugin, Entity entity) {
		Wolf egg = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		egg.setMetadata("wolfType", new FixedMetadataValue(plugin, "egg"));
		egg.setCollarColor(DyeColor.YELLOW);
		egg.setCustomName(ChatColor.YELLOW + "Egg Wolf");
		egg.setCustomNameVisible(false);
	}

	public void spawnArrowWolf(Main plugin, Entity entity) {
		Wolf arrow = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		arrow.setMetadata("wolfType", new FixedMetadataValue(plugin, "arrow"));
		arrow.setCollarColor(DyeColor.GRAY);
		arrow.setCustomName(ChatColor.WHITE + "Arrow Wolf");
		arrow.setCustomNameVisible(false);
	}

	public void spawnTeleportWolf(Main plugin, Entity entity) {
		Wolf teleport = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		teleport.setMetadata("wolfType", new FixedMetadataValue(plugin, "teleport"));
		teleport.setCollarColor(DyeColor.PURPLE);
		teleport.setCustomName(ChatColor.DARK_PURPLE + "Teleport Wolf");
		teleport.setCustomNameVisible(false);
	}

	public void spawnDragonWolf(Main plugin, Entity entity) {
		Wolf dragon = (Wolf) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
		dragon.setMetadata("wolfType", new FixedMetadataValue(plugin, "dragon"));
		dragon.setCollarColor(DyeColor.BLACK);
		dragon.setCustomName(ChatColor.BLACK + "Dragon Wolf");
		dragon.setCustomNameVisible(false);
		Vex dragonRide = (Vex) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.VEX);
		dragonRide.setMetadata("wolfType", new FixedMetadataValue(plugin, "dragonRide"));
		dragonRide.setCollidable(false);
		dragonRide.setSilent(true);
		dragonRide.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 1, true));
		dragonRide.addPassenger(dragon);
	}

	@EventHandler
	public void wolfAttack(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		LivingEntity victim = (LivingEntity) e.getEntity();
		if(damager.hasMetadata("wolfType")) {
			for(MetadataValue metadata : damager.getMetadata("wolfType")) {
				if(metadata.asString() == "poison") {
					victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2));
					continue;
				} 
				if(metadata.asString().equals("fire")) {
					victim.setFireTicks(10);
					continue;
				} 
				if(metadata.asString().equals("wither")) {
					victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3));
					continue;
				} 
				if(metadata.asString().equals("ice")) {
					victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 5));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 10));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 5));
					continue;
				} 
				if(metadata.asString().equals("troll")) {
					e.setCancelled(true);
					victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, -1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, -10000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2500000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 100, 1000000));
					victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1000000));
					continue;
				} 
				if(metadata.asString().equals("dragon")) {
					victim.setVelocity(victim.getLocation().getDirection().multiply(5));
					continue;
				} 
				if(metadata.asString().equals("dragonRide")) {
					e.setCancelled(true);
				}
			} 
		}
	}

	@EventHandler
	public void wolfImmunities(EntityDamageEvent e) {
		LivingEntity entity = (LivingEntity) e.getEntity();
		EntityDamageEvent.DamageCause cause = e.getCause();
		if(entity.getPassengers().size() > 0) {
			LivingEntity passenger = (LivingEntity) entity.getPassengers().get(0);
			double damage = e.getDamage();
			if(entity.hasMetadata("wolfType")) {
				for(MetadataValue metadata : entity.getMetadata("wolfType")) {
					if(metadata.asString().equals("poison")) {
						if(cause == EntityDamageEvent.DamageCause.POISON) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("fire")) {
						if(cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.HOT_FLOOR || cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.MELTING) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("wither")) {
						if(cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || cause == EntityDamageEvent.DamageCause.WITHER) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("troll")) {
						if(cause == EntityDamageEvent.DamageCause.DROWNING || cause == EntityDamageEvent.DamageCause.FALL || cause == EntityDamageEvent.DamageCause.FALLING_BLOCK || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.MELTING || cause == EntityDamageEvent.DamageCause.THORNS) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("shulker")) {
						if(cause == EntityDamageEvent.DamageCause.FALL || cause == EntityDamageEvent.DamageCause.SUFFOCATION) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("fireball")) {
						if(cause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.HOT_FLOOR || cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.MELTING) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("teleport")) {
						if(cause == EntityDamageEvent.DamageCause.FALL) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("dragon")) {
						if(cause == EntityDamageEvent.DamageCause.DRAGON_BREATH || cause == EntityDamageEvent.DamageCause.FALL || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.MAGIC || cause == EntityDamageEvent.DamageCause.MELTING || cause == EntityDamageEvent.DamageCause.SUFFOCATION) {
							e.setCancelled(true);
						}
						continue;
					} 
					if(metadata.asString().equals("dragonRide")) {
						if(cause == EntityDamageEvent.DamageCause.DRAGON_BREATH || cause == EntityDamageEvent.DamageCause.FALL || cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.FIRE_TICK || cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.MAGIC || cause == EntityDamageEvent.DamageCause.MELTING || cause == EntityDamageEvent.DamageCause.SUFFOCATION) {
							e.setCancelled(true);
							continue;
						} 
						if(passenger != null) {
							e.setCancelled(true);
							passenger.damage(damage);
						} 
					} 
				} 
			}
		}
	}

	@EventHandler
	public void wolfAgro(final EntityTargetLivingEntityEvent e) {
		if(!e.getEntityType().equals(EntityType.EXPERIENCE_ORB)) {
			final LivingEntity entity = (LivingEntity) e.getEntity();
			final LivingEntity target = e.getTarget();
			if(entity.hasMetadata("wolfType")) {
				for(final MetadataValue metadata : entity.getMetadata("wolfType")) {
					(new BukkitRunnable() {
						public void run() {
							if(!entity.isDead() && !target.isDead() && entity.getLocation().distance(target.getLocation()) <= 20.0D && entity instanceof Wolf && !((Wolf) entity).isSitting()) {
								if(entity.hasLineOfSight(target)) {
									if(metadata.asString().equals("wither")) {
										WitherSkull witherSkull = (WitherSkull) entity.getWorld().spawn( entity.getLocation(), WitherSkull.class);
										witherSkull.setShooter(entity);
										witherSkull.setGravity(false);
										witherSkull.setVelocity(entity.getLocation().getDirection().multiply(1.5D));
									}
									else if(metadata.asString().equals("snowball")) {
										Snowball snowball = (Snowball) entity.getWorld().spawn( entity.getLocation(), Snowball.class);
										snowball.setShooter(entity);
										snowball.setGravity(true);
										snowball.setVelocity(entity.getLocation().getDirection().multiply(1.5D));
									}
									else if(metadata.asString().equals("shulker")) {
										ShulkerBullet shulkerBullet = (ShulkerBullet) entity.getWorld().spawn(entity.getLocation(), ShulkerBullet.class);
										shulkerBullet.setShooter(entity);
										shulkerBullet.setTarget( target);
										shulkerBullet.setVelocity(entity.getLocation().getDirection().multiply(1.5D));
									}
									else if(metadata.asString().equals("fireball")) {
										Fireball fireball = (Fireball) entity.getWorld().spawn(entity.getLocation(), Fireball.class);
										fireball.setShooter(entity);
										fireball.setIsIncendiary(true);
										fireball.setGravity(false);
										fireball.setVelocity(entity.getLocation().getDirection().multiply(1.5D));
									}
									else if(metadata.asString().equals("egg")) {
										Egg egg = (Egg)entity.getWorld().spawn(entity.getLocation(), Egg.class);
										egg.setShooter(entity);
										egg.setGravity(true);
										egg.setVelocity(entity.getLocation().getDirection().multiply(1.5D));
									}
									else if(metadata.asString().equals("arrow")) {
										Arrow arrow = (Arrow) entity.getWorld().spawn(entity.getLocation(), Arrow.class);
										arrow.setShooter(entity);
										arrow.setGravity(true);
										arrow.setVelocity(entity.getLocation().getDirection().multiply(2));
									}
									else if(metadata.asString().equals("teleport")) {
										if(entity.getLocation().distance(target.getLocation()) > 10.0D) {
											entity.teleport( target);
										}
									}
									else if(metadata.asString().equals("dragon")) {
										((Vex) entity.getVehicle()).setTarget(target);
										DragonFireball dragonFireball = (DragonFireball) entity.getWorld().spawn(entity.getLocation(), DragonFireball.class);
										dragonFireball.setShooter(entity);
										dragonFireball.setIsIncendiary(true);
										dragonFireball.setGravity(false);
										dragonFireball.setVelocity(entity.getLocation().getDirection().multiply(2));
									}
								}
							}
							else if(!entity.isDead() && !target.isDead() && entity.getLocation().distance(target.getLocation()) <= 20.0D && entity instanceof Vex) {
								if(metadata.asString().equals("dragonRide") && entity.getPassengers().size() > 0) {
									if(!((Wolf) entity.getPassengers().get(0)).getTarget().equals(((Vex)entity).getTarget())) {
										e.setCancelled(true);
									}
								}
							}
							else {
								cancel();
							}
						}
					}).runTaskTimer(instance, 0L, 20L);
				} 
			}
		} 
	}
}