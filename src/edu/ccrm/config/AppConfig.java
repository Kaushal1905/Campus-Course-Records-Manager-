package edu.ccrm.config;

public final class AppConfig {
    private static AppConfig instance;
    private String dataFolderPath;

    private AppConfig() {
        this.dataFolderPath = "data/";
        System.out.println("AppConfig initialized. Data folder set to: " + this.dataFolderPath);
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getDataFolderPath() {
        return dataFolderPath;
    }
}