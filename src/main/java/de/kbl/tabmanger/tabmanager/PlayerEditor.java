package de.kbl.tabmanger.tabmanager;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author KeksGauner
 */
public class PlayerEditor {

    private static RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    public static PlayerTeam getUserTeam(Player player) {
        List<String> list ;

        //Main.getInstance().getLogger().log(Level.WARNING, "It is Luckperms used");
        LuckPerms luckPerms = provider.getProvider();

        Set<Group> groups = luckPerms.getGroupManager().getLoadedGroups();
        list = new ArrayList<>();


        for(Group group : groups) {
            // format -> name;wight;prefix
            list.add(group.getName() + ";" + group.getWeight().getAsInt() + ";" + group.getCachedData().getMetaData().getPrefix());
        }



        if (list == null) {
            throw new NullPointerException();
        }

        for (Object toConvert : list) {
            PlayerTeam playerTeam = new PlayerTeam(String.valueOf(toConvert));
            if (provider != null) {
                User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
                String group = user.getCachedData().getMetaData().getPrimaryGroup();
                if(playerTeam.getGroup().equals(group))
                    return playerTeam;
            } else
                if (player.hasPermission(playerTeam.getPermission()))
                    return playerTeam;
        }
        return new PlayerTeam(String.valueOf(list.get(list.size() - 1))); // -1 is becaus the List start not at 1
    }

    /*public static void setTabPrefix(Player player) {
        Thread thread = new Thread(() -> {
            for (Player target : Bukkit.getOnlinePlayers()) {
                Scoreboard sb = player.getScoreboard();
                PlayerTeam playerTeam = getUserTeam(target);
                String team = playerTeam.getId();
                if (sb.getTeam(team) == null)
                    sb.registerNewTeam(team);
                sb.getTeam(team).setPrefix(playerTeam.getTabPrefix());
                sb.getTeam(team).addPlayer(target);
            }
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (target == player)
                    continue;
                Scoreboard sb = target.getScoreboard();
                PlayerTeam playerTeam = getUserTeam(player);
                String team = playerTeam.getId();
                if (sb.getTeam(team) == null)
                    sb.registerNewTeam(team);
                sb.getTeam(team).setPrefix(playerTeam.getTabPrefix());
                sb.getTeam(team).addPlayer(player);
            }
            Thread.currentThread().stop();
        });
        thread.start();
    } */
    public static void setTabPrefix(Player player) {
        Thread thread = new Thread(() -> {
            for (Player target : Bukkit.getOnlinePlayers()) {
                Scoreboard sb = player.getScoreboard();
                PlayerTeam playerTeam = getUserTeam(target);
                String team = playerTeam.getId();
                if (sb.getTeam(team) == null) {
                    sb.registerNewTeam(team);
                }
                sb.getTeam(team).setPrefix("§7" + playerTeam.getTabPrefix()); // prefix is set to gray
                sb.getTeam(team).addPlayer(target);
            }
            for (Player target : Bukkit.getOnlinePlayers()) {
                if (target == player) {
                    continue;
                }
                Scoreboard sb = target.getScoreboard();
                PlayerTeam playerTeam = getUserTeam(player);
                String team = playerTeam.getId();
                if (sb.getTeam(team) == null) {
                    sb.registerNewTeam(team);
                }
                sb.getTeam(team).setPrefix("§7" + playerTeam.getTabPrefix()); // prefix is set to gray
                sb.getTeam(team).addPlayer(player);
            }
            Thread.currentThread().stop();
        });
        thread.start();
    }
}
