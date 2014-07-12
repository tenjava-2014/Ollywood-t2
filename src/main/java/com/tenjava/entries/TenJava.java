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
	 * I know its not much. But I was stumped on ideas.
	 */
	
	public void onEnable() {
		saveDefaultConfig();
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandString, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Console cannot create new game!");
			return true;
		}
		
		
		final Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("findores")) {
			
			//Config Variables
			int gameDuration = getConfig().getInt("gameDuration");
			int gameTime = gameDuration*20;
			final int item = getConfig().getInt("item");
			final String itemName = getConfig().getString("itemName");
			final boolean keepItems = getConfig().getBoolean("keepItems");
			final int startItem = getConfig().getInt("startItem");
			final boolean keepStartItem = getConfig().getBoolean("keepStartItem");
			int startItemAmount = getConfig().getInt("startItemAmount");
			//Config Variables
			
			/**
			 * Start of main game
			 */
			
			p.sendMessage(ChatColor.GREEN+"Timer started! You have "+gameDuration+" seconds to find as much "+ChatColor.AQUA+itemName+ChatColor.GREEN+" as you can!");
			
			p.getInventory().clear();
			
			p.getInventory().addItem(new ItemStack(startItem, startItemAmount));
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				 
				@SuppressWarnings("deprecation")
				public void run() {
				      if (p.getInventory().contains(item)) {
				    	  
				    	  ItemStack[] inv = p.getInventory().getContents();
                          
				    	  	//Checks the amount of diamonds in players inventory
				    	    int quantity= 0;
				    	    for(int i = 0; i < inv.length; i++) {
				    	        if(inv[i] != null){
				    	            if(inv[i].getTypeId() == item) {
				    	                int cant = inv[i].getAmount();
				    	                quantity= quantity + cant;
				    	            }
				    	        }
				    	    }
				    	  
				    	  p.sendMessage(ChatColor.GOLD+"Well done! You found "+quantity+" "+itemName+"!!");
				    	  if (keepItems == false) {
				    		  p.getInventory().remove(item);
				    	  }
				    	  if (keepStartItem == false) {
				    		  p.getInventory().remove(startItem);
				    	  }
				      } else {
				    	  //Displays message is timmer ends and you have no diamonds in your inventory
				    	  p.sendMessage(ChatColor.RED+"Bad luck! Try better next time!");
				    	  if (keepStartItem == false) {
				    		  p.getInventory().remove(startItem);
				    	  }
				      }
				  }
				}, gameTime);
			
			/*
			 * End of Main Game
			 */
		}
		
		
		
		
		return false;
	}

}

	
	

