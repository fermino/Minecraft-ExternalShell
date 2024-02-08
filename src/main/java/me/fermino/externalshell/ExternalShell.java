package me.fermino.externalshell;

import me.fermino.externalshell.Commands.ShellCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExternalShell extends JavaPlugin implements Listener
{
    @Override
    public void onEnable() {
        this.getCommand("shell").setExecutor(new ShellCommand());
    }
}
