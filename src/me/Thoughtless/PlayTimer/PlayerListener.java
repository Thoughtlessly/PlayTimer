package me.Thoughtless.PlayTimer;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener{
	PlayTimer _plugin;
	public Map<UUID, Integer> _tasks;
	public PlayerListener(PlayTimer Plugin){
		_plugin = Plugin;
		_tasks = new HashMap<UUID,Integer>();
		
	}
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event){
		Player p = event.getPlayer();		
		if(!_plugin._players.containsKey(p.getUniqueId())){
			_plugin._players.put(p.getUniqueId(),new Date().getTime());
			
			Object[] args = new Object[2];
			args[0] = p.getUniqueId();
			args[1] = p.getName();
			PlayerAnnounceTask PAT = new PlayerAnnounceTask(_plugin,args);
			_tasks.put(p.getUniqueId(),PAT.startRepeating(0,18000,false));
			
		}
	}		
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player p = event.getPlayer();		
		if(_plugin._players.containsKey(p.getUniqueId())){
			_plugin._players.remove(p.getUniqueId());
			_plugin.getServer().getScheduler().cancelTask(_tasks.get(p.getUniqueId()));
		}		
	}	
}
