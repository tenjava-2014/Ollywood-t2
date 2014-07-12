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
	
	public void onEnable() {
		saveDefaultConfig();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandString, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED+"Console cannot create new game!");
		}
		
		final Player p = (Player) sender;
		
		if(commandString.equalsIgnoreCase("new")) {
			
			int gameDuration = getConfig().getInt("gameDuration");
			int gameTime = gameDuration*20;
			
			p.getInventory().clear();
			
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				 
				  public void run() {
				      if (p.getInventory().contains(Material.DIAMOND)) {
				    	  
				    	  ItemStack[] inv = p.getInventory().getContents();
                          
				    	    int cuantity= 0;
				    	    for(int i = 0; i < inv.length; i++) {
				    	        if(inv[i] != null){
				    	            if( inv[i].getType() == Material.DIAMOND) {
				    	                int cant = inv[i].getAmount();
				    	                cuantity= cuantity + cant;
				    	            }
				    	        }
				    	    }
				    	  
				    	  p.sendMessage(ChatColor.GOLD+"Well done! You found "+cuantity+" diamond!");
				      } else {
				    	  p.sendMessage(ChatColor.RED+"Bad luck! Try better next time!");
				      }
				  }
				}, gameTime);
			
		}
		
		
		
		
		return false;
	}

}

	
	

