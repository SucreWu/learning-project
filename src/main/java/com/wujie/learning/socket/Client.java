package com.wujie.learning.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Description: socket客户端
 * @Auther: wujie
 * @Date: 2018/12/12 15:23
 * @version: V1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 8123);
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        bw.write("hello\n");
        bw.flush();
        String str = br.readLine();
        System.out.println(str);

        bw.write("world\n");
        bw.flush();
        str = br.readLine();
        System.out.println(str);

        br.close();
        bw.close();
        client.close();
    }
}
