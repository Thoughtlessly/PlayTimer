package me.Thoughtless.PlayTimer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayTimer extends JavaPlugin {
	public Map<UUID,Long> _players;
	
	public void onEnable(){
	_players = new HashMap<UUID,Long>();
	PluginManager _pm = this.getServer().getPluginManager();
	PlayerListener _pl = new PlayerListener(this);
	_pm.registerEvents(_pl, this);
	
	}
	
	public void onDisable(){
	this.getServer().getScheduler().cancelTasks(this);
	}
}
