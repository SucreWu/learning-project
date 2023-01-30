package com.wujie.learning.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description: socket服务端
 * @Auther: wujie
 * @Date: 2018/12/12 15:22
 * @version: V1.0
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8123);
        Socket client = server.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        String str = br.readLine();
        while (str != null){
            System.out.println(str);
            bw.write(str.toUpperCase() + "\n");
            bw.flush();
            str = br.readLine();
        }

        br.close();
        bw.close();
        server.close();
    }
}
