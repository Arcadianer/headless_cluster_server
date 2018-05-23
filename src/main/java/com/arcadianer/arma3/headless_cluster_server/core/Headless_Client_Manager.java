package com.arcadianer.arma3.headless_cluster_server.core;

import com.arcadianer.arma3.headless_cluster_server.data.Headless_Client;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Headless_Client_Manager {
    @Autowired
    Server_Config server_config;
    List<Headless_Client> active_clients;

    public void update_config(){

        try {

            File server_config=new File(this.server_config.getPath_to_mod_config_file());

            BufferedReader reader=new BufferedReader(new FileReader(server_config));
            List<String> config_line=reader.lines().collect(Collectors.toList());
            reader.close();
            String replace_line="headlessClients[]={";
            for (Headless_Client client:active_clients) {
                replace_line=replace_line+'"'+client.getInetAddress().getHostAddress()+'"'+';';
            }
            replace_line=replace_line.substring(0,replace_line.length()-1);
            replace_line=replace_line+"}\n";



            final String final_replace_line=replace_line;

            config_line.stream().filter((line)->line.contains("headlessClients[]")).allMatch((line) -> {
                 line = final_replace_line;
                return true;
            });
            FileWriter fw = new FileWriter(server_config);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : config_line)
                out.write(s);
            out.flush();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void 
}
