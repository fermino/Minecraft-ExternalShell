package me.fermino.externalshell.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        try {
            sendMessage(sender, String.format("[ExternalShell] Executing $ %s", String.join(" ", args)));
            Process process = new ProcessBuilder(args).redirectErrorStream(true).start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sendMessage(sender, String.format("> %s", line));
            }

            int exit_code = process.waitFor();
            sendMessage(sender, String.format("[ExternalShell] Exit code: %d", exit_code));
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private void sendMessage(CommandSender sender, String message) {
        Bukkit.getLogger().info(message);
        sender.sendMessage(message);
    }
}
