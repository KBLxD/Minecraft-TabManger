package de.kbl.tabmanger.tabmanager;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

public class JoinEventListener implements Listener {
    @EventHandler
    public void onEvent(PlayerJoinEvent event) {
        PlayerEditor.setTabPrefix(event.getPlayer());
    }

    private static RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String msg = event.getMessage();

        PlayerTeam playerTeam = PlayerEditor.getUserTeam(player);

        for(Player allPlayer : Bukkit.getOnlinePlayers())
            allPlayer.sendMessage(playerTeam.getTabPrefix() + player.getName() + " ➥ " + msg);
    }
}
