package me.Thoughtless.PlayTimer;

import org.bukkit.Server;


public class Task implements Runnable {
    private PlayTimer plugin;
    private Object[] arguments;
    private int taskID = 0;

    public static Task create(PlayTimer plugin, Object... arguments) {
        return new Task(plugin, arguments);
    }
    public Task(PlayTimer plugin, Object... arguments) {
        this.plugin = plugin;
        if(arguments != null){
        this.arguments = arguments;
        }
    }

    public PlayTimer getPlugin() {
        return this.plugin;
    }
    public Server getServer() {
        return this.plugin.getServer();
    }
    public Object getArg(int index) {
    	if(arguments != null){
        return arguments[index];
    	}
    	else{
    		return null;
    	}
    }
    public int getIntArg(int index) {
    	if(arguments != null){
        return (Integer) getArg(index);
    	}
        else{
    		return 0;
    	}
    }
    public long getLongArg(int index) {
    	if(arguments != null){
    
        return (Long) getArg(index);
    	}
        else{
    		return 0;
    	}
    }
    public float getFloatArg(int index) {
    	if(arguments != null){
    
        return (Float) getArg(index);
    	}
        else{
    		return 0;
    	}
    }
    public double getDoubleArg(int index) {
    	if(arguments != null){
        return (Double) getArg(index);
    }
    else{
		return 0;
	}
    }
    public String getStringArg(int index) {
    	if(arguments != null){
        return (String) getArg(index);
    }
    else{
		return "";
	}
    }

    public void run() {
    	
    }

    public boolean isQueued() {
        return this.getServer().getScheduler().isQueued(this.taskID);
    }
    public boolean isRunning() {
        return this.getServer().getScheduler().isCurrentlyRunning(this.taskID);
    }
    public void stop() {
        this.getServer().getScheduler().cancelTask(this.taskID);
    }
    public void start() {
        start(false);
    }
    public void start(boolean Async) {
        startDelayed(0, Async);
    }
    public void startDelayed(long tickDelay) {
        startDelayed(tickDelay, false);
    }
    public void startDelayed(long tickDelay, boolean Async) {
        if (Async) {
            this.taskID = this.getServer().getScheduler().scheduleAsyncDelayedTask(this.plugin, this, tickDelay);
        } else {
            this.taskID = this.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, this, tickDelay);
        }
    }
    public void startRepeating(long tickInterval) {
        startRepeating(tickInterval, false);
    }
    public void startRepeating(long tickInterval, boolean Async) {
        startRepeating(0, tickInterval, Async);
    }
    public Integer startRepeating(long tickDelay, long tickInterval, boolean Async) {
        if (Async) {
            this.taskID = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this.plugin, this, tickDelay, tickInterval);
            return this.taskID;
        } else {
            this.taskID = this.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, this, tickDelay, tickInterval);
            return this.taskID;
        }
    }

}