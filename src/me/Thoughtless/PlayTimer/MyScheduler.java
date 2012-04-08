package me.Thoughtless.PlayTimer;

import java.text.DateFormat;
import java.util.Date;

import org.bukkit.entity.Player;

public class MyScheduler {
	
	public MyScheduler(final PlayTimer _plugin,final Player _player){
		_plugin.getServer().getScheduler().scheduleSyncRepeatingTask(_plugin, new Runnable() {			
	    public void run() {		
    	Date now = new Date();
    	Long timePlayed = now.getTime() - _plugin._players.get(_player.getUniqueId());
    	String timePlayed2 = getElapsedTime(timePlayed);
    	String currTime = DateFormat.getTimeInstance().format(now);
    	
        _plugin.getServer().broadcastMessage("Server Time Is " + currTime + ". You Have Been Playing for " + timePlayed2);
    	}
		}, 60L, 200L);
	}
	public String getElapsedTime(Long time) {       
	    long elapsedTime = time;  
	    String format = String.format("%%0%dd", 2);  
	    elapsedTime = elapsedTime / 1000;  
	    String seconds = String.format(format, elapsedTime % 60);  
	    String minutes = String.format(format, (elapsedTime % 3600) / 60);  
	    String hours = String.format(format, elapsedTime / 3600);  
	    String outTime =  hours + ":" + minutes + ":" + seconds;  
	    return outTime;  
	}
}
