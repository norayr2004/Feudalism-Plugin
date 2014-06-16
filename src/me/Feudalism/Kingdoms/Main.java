package me.Feudalism.Kingdoms;

//import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	
	public void onEnable() {
		getLogger().info("Plugin Enabled");
		new Kingdom(this);
		new KingdomArea(this);
	}
	
	public void onDisable(){
		getLogger().info("Plugin Disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("k help")){
				//String hub = "help " + player.getName();  --This was from TUT
				//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), hub);
				player.sendMessage(ChatColor.AQUA + "Kingdom Help:");
				player.sendMessage(ChatColor.WHITE + "k create <name>: Creates a kingdom");
			}
		}
	return false;	
	}
}
