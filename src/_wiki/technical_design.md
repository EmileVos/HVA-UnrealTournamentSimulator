#Technical design
Bukkit/ Spigot javadocs: https://hub.spigotmc.org/javadocs/bukkit/overview-summary.html

Paper javadocs: https://papermc.io/javadocs/paper/1.13/overview-summary.html
## events.PlayerBlockEvent
###blockPlaceEvent(@BukkitEvent:BlockPlaceEvent)
Cancelling all block breaks

###blockBreakEvent(@BukkitEvent:BlockBreakEvent)
Cancelling all block placements

## events.PlayerDamageEvent
###damageByEntity(@BukkitEvent:EntityDamageByEntityEvent)
Cancelling crossfire. Also update the last used item so that this item will drop after the player dies.

###damage(@BukkitEvent:EntityDamageEvent)
Canceling all damage to spectators (i.e. fall damage, arrows etc..)

## events.PlayerInventoryClickEvent
## events.PlayerItemDropEvent
## events.PlayerJoinEvent
## events.PlayerPickupItemEvent
## items.armor.DiamondArmor
## items.armor.GoldArmor
## items.armor.IronArmor
## items.armor.LeatherArmor
## items.weapons.DiamondCleaver