package de.kbl.tabmanger

import de.kbl.tabmanger.tabmanager.JoinEventListener
import org.bukkit.plugin.java.JavaPlugin

class TabManger : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        server.pluginManager.registerEvents(JoinEventListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}