package fr.stan1712.srp;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;

public class phone implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		
		Player p = event.getPlayer();
		
		ItemStack phone = new ItemStack(Material.COMPASS, 1);
		ItemMeta customM = phone.getItemMeta();
		customM.setDisplayName("�bT�l�phone");
		customM.setLore(Arrays.asList("�9Voici votre t�l�phone","�9Click droit pour l'ouvrir","�6Propri�taire : "+p.getDisplayName()));
		customM.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		phone.setItemMeta(customM);
		
		p.getInventory().setItemInMainHand(phone);
		p.updateInventory();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		Player p = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		
		if(it == null) return;
		
		if(it.getType() == Material.COMPASS && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("�bT�l�phone")){
			if(action == Action.RIGHT_CLICK_AIR){
				
				Inventory inv = Bukkit.createInventory(null, 36, "�bT�l�phone de "+p.getDisplayName());
				
				ItemStack headskull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
				SkullMeta headskullM = (SkullMeta) headskull.getItemMeta();
				headskullM.setOwner(p.getName());
				headskullM.setDisplayName(ChatColor.AQUA+p.getDisplayName());
				headskull.setItemMeta(headskullM);
				
				inv.setItem(0, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(1, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(2, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(3, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(5, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(6, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(7, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(8, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(9, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(17, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(18, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(26, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(27, getItem(Material.STAINED_GLASS_PANE, " "));
				inv.setItem(35, getItem(Material.STAINED_GLASS_PANE, " "));
				
				inv.setItem(4, headskull);
				
				//inv.setItem(4, getItem(Material.SKULL_ITEM, ChatColor.AQUA+p.getDisplayName()));
				inv.setItem(10, getItem(Material.EMERALD_BLOCK, "�aR�pondre"));
				inv.setItem(11, getItem(Material.REDSTONE_BLOCK, "�cRaccrocher/Refuser"));
				inv.setItem(28, getItem(Material.BARRIER, "�cEteindre"));
				inv.setItem(34, getItem(Material.REDSTONE_LAMP_OFF, "�bVotre num�ro de t�l�phone :"));
				
				p.openInventory(inv);
				
			}
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event){
		Inventory inv = event.getInventory();
		Player p = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current == null) return;
		
		if(inv.getName().equalsIgnoreCase("�bT�l�phone de "+p.getDisplayName())){
			
			event.setCancelled(true);
			
			if(current.getType() == Material.EMERALD_BLOCK){
				p.closeInventory();
				p.sendMessage("�bVous avez �ad�croch�");
			}
			
			if(current.getType() == Material.REDSTONE_BLOCK){
				p.closeInventory();
				p.sendMessage("�bVous avez �craccroch�");
			}
			
			if(current.getType() == Material.BARRIER){
				p.closeInventory();
			}
		}
	}
	
	
	public ItemStack getItem(Material material, String customName){
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(customName);
		it.setItemMeta(itM);
		return it;
	}
}
