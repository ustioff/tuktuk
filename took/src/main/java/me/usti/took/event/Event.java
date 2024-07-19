package me.usti.took.event;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class Event implements Listener {


        @EventHandler
        public void PlayerOnClickPlayer(PlayerInteractEntityEvent event) {
                Player player = event.getPlayer();
                ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
                //проверка предмета в руке
                if (event.getRightClicked() instanceof Player) {
                        Player entity = (Player) event.getRightClicked(); // The entity you are knocking at.

                        if (hand.getType().equals(Material.AIR) && player.isSneaking()) {
                              entity.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.WHITE + "Вас отвлекает " + ChatColor.RED +  event.getPlayer().getName()));
                              event.setCancelled(true);
                        }
                }
        }

        @EventHandler
        public void PlayerOnClickDoor(PlayerInteractEvent event) {
                Player player = event.getPlayer();
                ItemStack hand = player.getInventory().getItemInMainHand();
                Block clickedBlock = event.getClickedBlock();
                if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) && clickedBlock != null) {
                    if (player.isSneaking()
                            && clickedBlock.toString().contains("DOOR")
                            && hand.getType().equals(Material.AIR)) {
                            player.getWorld().playSound(
                                    player.getLocation(),
                                    Sound.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR,
                                    2.0F,
                                    1.0F
                            );
                            event.setCancelled(true);
                    }
                }
        }
}
