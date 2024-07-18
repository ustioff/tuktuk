package me.usti.took.event;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
                ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
                //проверка предмета в руке
                Entity entity = event.getRightClicked();
                if (event.getRightClicked().getType() == EntityType.PLAYER){
                        if (hand.getType() == Material.AIR) {
                                if (event.getPlayer().isSneaking()) {
                                        event.setCancelled(true);
                                        Player took = (Player) entity;
                                        took.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.WHITE + "Вас отвлекает " + ChatColor.RED +  event.getPlayer().getName() ));
                                }
                        }
                }
        }


        @EventHandler
        public void PlayerOnClickDoor(PlayerInteractEvent event) {
                ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();
                if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                        if (hand.getType() == Material.AIR) {
                                if (event.getPlayer().isSneaking()) {
                                        if (event.getClickedBlock().toString().contains("DOOR")) {
                                                if (event.getPlayer().isOp() == true){
                                                        event.getPlayer().chat("/playsound minecraft:entity.zombie.attack_wooden_door block @e[type=minecraft:player,distance=..12]");
                                                }
                                                else {
                                                        event.setCancelled(true);
                                                        event.getPlayer().setOp(true);
                                                        event.getPlayer().chat("/playsound minecraft:entity.zombie.attack_wooden_door block @e[type=minecraft:player,distance=..12]");
                                                        event.getPlayer().setOp(false);
                                                }
                                        }
                                }
                        }

                }
        }
}

