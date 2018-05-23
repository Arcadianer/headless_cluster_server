package com.arcadianer.arma3.headless_cluster_server.core;

public class Server_Config {
   private String path_to_mods;
    private String path_to_mod_config_file;
    private String path_to_arma_server_config_file;

    public Server_Config(String path_to_mods, String path_to_config,String path_to_arma_server_config_file) {
        if(!path_to_mods.endsWith("\\")){
            path_to_mods= path_to_mods +"\\";
        }
        this.path_to_mods = path_to_mods;
        this.path_to_mod_config_file = path_to_config;
        this.path_to_arma_server_config_file=path_to_arma_server_config_file;
    }

    public String getPath_to_mods() {
        return path_to_mods;
    }

    public void setPath_to_mods(String path_to_mods) {
        if(!path_to_mods.endsWith("\\")){
            path_to_mods= path_to_mod_config_file +"\\";
        }
        this.path_to_mods = path_to_mods;
    }

    public String getPath_to_mod_config_file() {
        return path_to_mod_config_file;
    }

    public void setPath_to_mod_config_file(String path_to_mod_config_file) {

        this.path_to_mod_config_file = path_to_mod_config_file;
    }
}
