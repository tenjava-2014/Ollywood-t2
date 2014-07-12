package com.tenjava.entries;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
	
	/**
	 * Created by OllyWood
	 * www.youtube.com/Olliwoodification
	 * dev.bukkit.org: olllirules
	 * Minecraft Username: ollirules
	 * Skill level: Intermediate. Thanks to you Pogostick29 :)
	 */
	
	public void onEnable() {
		saveDefaultConfig();
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandString, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Console cannot create new game!");
		}
		
		
		final Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("new")) {
			
			//Game duration set by user in config
			int gameDuration = getConfig().getInt("gameDuration");
			int gameTime = gameDuration*20;
			final int item = getConfig().getInt("item");
			final String itemName = getConfig().getString("itemName");
			
			/**
			 * Start of main game
			 */
			
			p.sendMessage(ChatColor.GREEN+"Timer started! You have "+gameDuration+" seconds to find as much "+itemName+" as you can!");
			
			p.getInventory().clear();
			
			p.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				 
				@SuppressWarnings("deprecation")
				public void run() {
				      if (p.getInventory().contains(item)) {
				    	  
				    	  ItemStack[] inv = p.getInventory().getContents();
                          
				    	  	//Checks the amount of diamonds in players inventory
				    	    int cuantity= 0;
				    	    for(int i = 0; i < inv.length; i++) {
				    	        if(inv[i] != null){
				    	            if(inv[i].getTypeId() == item) {
				    	                int cant = inv[i].getAmount();
				    	                cuantity= cuantity + cant;
				    	            }
				    	        }
				    	    }
				    	  
				    	  p.sendMessage(ChatColor.GOLD+"Well done! You found "+cuantity+" "+itemName+"!!");
				      } else {
				    	  //Displays message is timmer ends and you have no diamonds in your inventory
				    	  p.sendMessage(ChatColor.RED+"Bad luck! Try better next time!");
				      }
				  }
				}, gameTime);
			
		}
		
		
		
		
		return false;
	}

}

	
	

