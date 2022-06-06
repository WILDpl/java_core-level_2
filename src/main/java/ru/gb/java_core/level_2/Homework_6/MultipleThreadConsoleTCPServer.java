package ru.gb.java_core.level_2.Homework_6;

import java.io.*;
import java.net.*;
import java.util.*;

public class MultipleThreadConsoleTCPServer extends Thread {
    private static final int PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Thread serverThread;

    public static void main(String[] args) {
        new MultipleThreadConsoleTCPServer().start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {  // создаем socket и слушаем заданный порт
            System.out.println("Server started");


            waitConnection(serverSocket);   // вызываем метод ожидания подключения клиента

            startConsoleInput();    // вызываем метод отправки сообщений клиенту


            while (true) {  // бесконечный цикл чтения входящего потока
                String income = in.readUTF();   // ожидаем входящих данных
                if (income.startsWith("/end")) {    // если прислана команда /end
                    shutdown(); // то вызываем метод выключения
                    break;  // и прерываем бесконечный цикл входящего потока
                }
                System.out.println("Received: " + income);
            }
        } catch (IOException /*| InterruptedException*/ e) {
            e.printStackTrace();
        } finally { // всегда
            try {
                shutdown(); // выключаем сервер и все сокеты
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // метод выключения сервера и всех сокетов
    private void shutdown() throws IOException {
        if (serverThread != null && serverThread.isAlive()) {   // если сервер включен и "живой"
            serverThread.interrupt();   // то прерываем работы сервера
        }

        if (socket != null && !socket.isClosed()) { // если сокет включен и не закрыт
            socket.close(); // то закрываем сокет
        }
        System.out.println("Server stopped");
    }

    // создаем метод для отправки сообщений клиенту
    private void startConsoleInput() {
        serverThread = new Thread(() -> {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {    // создаем буфер для чтения из консоли
                System.out.print("Enter your message >>>>> ");
                while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) { // пока не прервано и не закрыто
                    if (br.ready()) {   // если что-то написано в консоли
                        String outcome = br.readLine(); // то читаем всю строку
                        out.writeUTF(outcome);  // и передаем ее в исходящий поток
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();   // запускаем поток
    }

    // метод ожидания подключения клиента
    private void waitConnection(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for connection....");
        socket = serverSocket.accept(); // ожидаем подключение нового клиента
        System.out.println("Client connected " + socket);
        in = new DataInputStream(socket.getInputStream());  // создаем поток входящих сообщений
        out = new DataOutputStream(socket.getOutputStream());   // создаем поток исходящих сообщений
    }


}

