package com.arcadianer.arma3.headless_cluster_server.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
public class Mod_Manager {
    @Autowired
    Server_Config server_config;
    List<String> active_mods=new ArrayList<>();
    Map<String,String> mod_hash_map=new HashMap<>();
    ZipUtils zip;


    private void update_hash_of_mod(String mod_name){

        String hash_s;
        try {
            byte[] b = new byte[0];
            b = Files.readAllBytes(Paths.get(server_config.getPath_to_mods()+"@"+mod_name+".zip"));
            byte[] hash = MessageDigest.getInstance("MD5").digest(b);
            hash_s= DatatypeConverter.printHexBinary(hash);
            mod_hash_map.put(mod_name,hash_s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
    private void create_zip_from_mod(String mod_name){
        zip=new ZipUtils(server_config.getPath_to_mod_config_file()+"@"+mod_name);
        zip.zipIt(server_config.getPath_to_mods()+"@"+mod_name+".zip");
    }
    public void update_mod_list(){

        try {

            File mod_config=new File(server_config.getPath_to_mod_config_file());

            BufferedReader reader=new BufferedReader(new FileReader(mod_config));
            Optional<String> mod_line=reader.lines().filter((line)->(line.contains("mod"))).findFirst();
            String[] raw_mod_array=mod_line.get().split(";");
            for (String raw_mod:raw_mod_array) {
                String mod_name=raw_mod.split("@")[1];
                if(!active_mods.contains(mod_name)){
                    active_mods.add(mod_name);
                    update_mod(mod_name);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update_mod(String mod_name){
        create_zip_from_mod(mod_name);
        update_hash_of_mod(mod_name);
    }
    public void update_all_mods(){
        for (String mod_name:active_mods) {
            update_mod(mod_name);
        }
    }



}
