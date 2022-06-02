package ru.gb.java_core.level_2.Homework_6;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final int PORT = 8189;

    private List<Handler> handlers;

    public Server() {
        this.handlers = new ArrayList<>();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                System.out.println("Waiting for connection....");
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket, this);
                System.out.println("Client connected " + socket);
                handlers.add(handler);
                handler.handle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast (String message) {
        for (Handler handler : handlers) {
            handler.send(message);
        }
    }


}
