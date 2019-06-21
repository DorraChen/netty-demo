package com.example.biotest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: clz
 * @date: 2019/06/21 
 * @description:
 */
public class BioTimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0){
            port = Integer.valueOf(args[0]);
        }
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The time server is start in port : "+port);
            Socket socket = null;
            // 无限循环监听客户端的连接
            while (true){
                // 如果没有客户端接入, 主线程会阻塞在ServerSocket的accept操作上
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null){
                serverSocket.close();
                serverSocket = null;
                System.out.println("The time server close");
            }
        }
    }

}
