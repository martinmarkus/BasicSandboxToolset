package hu.martinmarkus.basichytools.models;

import java.util.List;

public class FunctionParameter {
    private String name;
    private String command;
    private List<String> aliases;
    private String permission;
    private double usagePrice;
    private String description;
    private int requiredParameterCount;
    private boolean concreteParameterCount;
    private int cooldown;
    private boolean doLogging;

    public FunctionParameter(String name, String command, List<String> aliases,
                             String permission, double usagePrice, String description,
                             int requiredParameterCount, boolean concreteParameterCount,
                             int cooldown, boolean doLogging) {
        this.name = name;
        this.command = command;
        this.aliases = aliases;
        this.permission = permission;
        this.usagePrice = usagePrice;
        this.description = description;
        this.requiredParameterCount = requiredParameterCount;
        this.concreteParameterCount = concreteParameterCount;
        this.cooldown = cooldown;
        this.doLogging = doLogging;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public double getUsagePrice() {
        return usagePrice;
    }

    public void setUsagePrice(double usagePrice) {
        this.usagePrice = usagePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRequiredParameterCount() {
        return requiredParameterCount;
    }

    public void setRequiredParameterCount(int requiredParameterCount) {
        this.requiredParameterCount = requiredParameterCount;
    }

    public boolean isConcreteParameterCount() {
        return concreteParameterCount;
    }

    public void setConcreteParameterCount(boolean concreteParameterCount) {
        this.concreteParameterCount = concreteParameterCount;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isDoLogging() {
        return doLogging;
    }

    public void setDoLogging(boolean doLogging) {
        this.doLogging = doLogging;
    }

}
