package de.kbl.tabmanger.tabmanager;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * @author KeksGauner
 */
public class PlayerTeam {
    private String permission;
    private String group;
    private String tabPrefix;
    private String tabSuffix;
    private Integer id;

    public PlayerTeam(String group, String tabPrefix, String tabSuffix, String chatPrefix, int id) {
        this.permission = "core.group." + group;
        this.group = group;
        this.tabPrefix = tabPrefix;
        this.tabSuffix = tabSuffix;
        this.id = Integer.valueOf(id);
    }

    public PlayerTeam(String toConvert) {
        String[] strings = toConvert.split(";");
        this.group = strings[0];
        this.permission = "core.group." + strings[0];
        this.id = Integer.valueOf(strings[1]);
        this.tabPrefix = strings[2];
        this.tabSuffix = strings[3];
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTabPrefix() {
        return this.tabPrefix.replace('&', '\u00A7');
    }
    public String getTabSuffix() {
        return this.tabSuffix.replace('&', '\u00A7');
    }

    public void setTabPrefix(String tabPrefix) {
        this.tabPrefix = tabPrefix;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getId() {
        NumberFormat numberFormat = new DecimalFormat("0000");
        return numberFormat.format(this.id);
    }

    public PlayerTeam setId(Integer id) {
        this.id = id;
        return this;
    }
}
