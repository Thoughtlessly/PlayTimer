package me.Thoughtless.PlayTimer;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class PlayerAnnounceTask extends Task {
	private UUID _UID;
	private String _playerName;
	public PlayerAnnounceTask(PlayTimer plugin, Object[] arguments) {		
		super(plugin, arguments);
		_UID = (UUID) arguments[0];
		_playerName = (String) arguments[1];
		
	}
	@Override
	public void run() {
			PlayTimer _plugin = (PlayTimer) Bukkit.getPluginManager().getPlugin("PlayTimer");
	    	Date now = new Date();
	    	if(_plugin._players.get(_UID) != null){
		    	Long timePlayed = now.getTime() - _plugin._players.get(_UID);
		    	getElapsedTime(timePlayed);
		    	String currTime = DateFormat.getTimeInstance().format(now);
		    	if(timePlayed < 20000){
		        _plugin.getServer().getPlayerExact(_playerName).sendMessage("Server Time Is " + currTime + "Welcome to MineCraft");
		    	}
		    	else{
		    		_plugin.getServer().getPlayerExact(_playerName).sendMessage("Server Time Is " + currTime + ". You Have Been Playing for " + getElapsedTime(timePlayed));
		    		}
	    	}
	}
	public String getElapsedTime(Long time) {       
	    long elapsedTime = time;  
	    String format = String.format("%%0%dd", 2);  
	    elapsedTime = elapsedTime / 1000;  	      
	    String minutes = String.format(format, (elapsedTime % 3600) / 60);  
	    String hours = String.format(format, elapsedTime / 3600);
	    String outTime = "";
	    if(hours == "00"){
		    outTime = minutes + " minutes";
		    
		}
	    else{
	    	outTime = hours + " Hours " + minutes + " minutes";
	    }
	    return outTime;  
	}
}
