package com.arcadianer.arma3.headless_cluster_server.data;

import org.springframework.scheduling.annotation.Scheduled;

import java.net.InetAddress;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class Headless_Client {
    String name;
    InetAddress inetAddress;
    Instant last_call_back;


    public Headless_Client(String name, InetAddress inetAddress) {
        this.name = name;
        this.inetAddress = inetAddress;
    }

    public String getName() {
        return name;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public Instant getLast_call_back() {
        return last_call_back;
    }
    public void still_alive(){
       last_call_back=Instant.now();
    }

}
