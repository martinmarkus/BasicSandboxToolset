package hu.martinmarkus.basichytools.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.martinmarkus.basichytools.configmanagement.managers.DefaultConfigManager;
import hu.martinmarkus.basichytools.configmanagement.managers.GroupManager;
import hu.martinmarkus.basichytools.configmanagement.managers.LanguageConfigManager;
import hu.martinmarkus.basichytools.globalmechanisms.chatmechanisms.Informer;
import hu.martinmarkus.basichytools.models.placeholders.placeholderhelpers.PlaceholderReplacer;
import hu.martinmarkus.basichytools.permissionmanagement.PermissionValidator;
import hu.martinmarkus.basichytools.permissionmanagement.UserPermissionValidator;

import java.util.ArrayList;
import java.util.List;

public class User {
    @JsonIgnore
    private boolean isValidated = false;        //Signs the validation state of the User.

    private String name;
    private String permissionGroupName;
    private double balance;
    private double exp;
    private boolean isOnline;
    private String loginIp;
    private String loginTime;
    private BasicHyToolsLocation location;
    private List<String> uniquePermissions;
    private boolean isOperator;
    private boolean isMuted;
    private boolean isBanned;
    private boolean isIpBanned;

    public User(String name, String permissionGroupName, double balance, double exp,
                boolean isOnline, String loginIp, String loginTime,
                BasicHyToolsLocation location, List<String> uniquePermissions,
                boolean isOperator, boolean isMuted, boolean isBanned, boolean isIpBanned) {
        this.name = name;
        this.permissionGroupName = permissionGroupName;
        this.balance = balance;
        this.exp = exp;
        this.isOnline = isOnline;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.location = location;
        this.uniquePermissions = uniquePermissions;
        this.isOperator = isOperator;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
        this.isIpBanned = isIpBanned;
    }

    @JsonIgnore
    public void sendMessage(String message) {
        // TODO: implement default message sending to User

    }

    @JsonIgnore
    public void teleportToSpawn() {
        BasicHyToolsLocation spawnLocation =
                DefaultConfigManager.getInstance().getDefaultConfig().getSpawnLocation();
        teleport(spawnLocation);
    }

    @JsonIgnore
    public void teleportToSpawnOnFirstJoin() {
        float userX = location.getX();
        float userY = location.getY();
        float userZ = location.getZ();
        String userWorldName = location.getWorldName();

        BasicHyToolsLocation spawnLocation =
                DefaultConfigManager.getInstance().getDefaultConfig().getSpawnLocation();

        float spawnX = spawnLocation.getX();
        float spawnY = spawnLocation.getY();
        float spawnZ = spawnLocation.getZ();
        String spawnWorldName = spawnLocation.getWorldName();

        if (userX == spawnX && userY == spawnY && userZ == spawnZ &&
                userWorldName.equalsIgnoreCase(spawnWorldName)) {
            teleport(spawnLocation);
        }
    }

    public void teleport(BasicHyToolsLocation location) {
        // TODO teleport user to location
    }

    @JsonIgnore
    public boolean hasPermission(String permission) {
        if (isOperator || uniquePermissions.contains(permission)) {
            return true;
        }

        PermissionValidator validator = new UserPermissionValidator();
        return validator.validate(this, permission);
    }

    @JsonIgnore
    public List<String> getAllPermissions() {
        List<String> allPermissions = new ArrayList<>(uniquePermissions);
        GroupManager configManager = GroupManager.getInstance();
        Group group = configManager.getPermissionGroup(permissionGroupName);

        allPermissions.addAll(group.getAllPermissions());

        return allPermissions;
    }

    public void decreaseBalance(double value) {
        balance -= Math.abs(value);
        DefaultConfig config = DefaultConfigManager.getInstance().getDefaultConfig();
        double minMoney = config.getMinMoney();
        if (balance < minMoney) {
            balance = minMoney;
        }

        LanguageConfig languageConfig = LanguageConfigManager.getInstance().getLanguageConfig();
        PlaceholderReplacer replacer = new PlaceholderReplacer();
        String message = replacer.replace(languageConfig.getBalanceDecreased(), String.valueOf(balance));
        sendMessage(message);
    }

    public void increaseBalance(double value) {
        balance += Math.abs(value);
        DefaultConfig config = DefaultConfigManager.getInstance().getDefaultConfig();
        double maxMoney = config.getMaxMoney();
        if (balance > maxMoney) {
            balance = maxMoney;
        }

        LanguageConfig languageConfig = LanguageConfigManager.getInstance().getLanguageConfig();
        PlaceholderReplacer replacer = new PlaceholderReplacer();
        String message = replacer.replace(languageConfig.getBalanceIncreased(), String.valueOf(balance));
        sendMessage(message);
    }

    public void resetBalance() {
        DefaultConfig config = DefaultConfigManager.getInstance().getDefaultConfig();
        balance = config.getStartingBalance();

        LanguageConfig languageConfig = LanguageConfigManager.getInstance().getLanguageConfig();
        PlaceholderReplacer replacer = new PlaceholderReplacer();
        String message = replacer.replace(languageConfig.getBalanceSet(), String.valueOf(balance));
        sendMessage(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissionGroupName() {
        return permissionGroupName;
    }

    public void setPermissionGroupName(String permissionGroupName) {
        this.permissionGroupName = permissionGroupName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public BasicHyToolsLocation getLocation() {
        return location;
    }

    public void setLocation(BasicHyToolsLocation location) {
        this.location = location;
    }

    public List<String> getUniquePermissions() {
        return uniquePermissions;
    }

    public void setUniquePermissions(List<String> uniquePermissions) {
        this.uniquePermissions = uniquePermissions;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public void setOperator(boolean isOperator) {
        this.isOperator = isOperator;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean isMuted) {
        this.isMuted = isMuted;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    public boolean isIpBanned() {
        return isIpBanned;
    }

    public void setIpBanned(boolean ipBanned) {
        isIpBanned = ipBanned;
    }

    @JsonIgnore
    public boolean isValidated() {
        return isValidated;
    }

    @JsonIgnore
    public void setValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }
}
