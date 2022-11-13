package com.wl.TCPSocketTest.all;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author WL
 * @version 1.0
 */
public class CLI {
    public static void main(String[] args) throws IOException {
        //服务端本机监听
        ServerSocket ss1 = new ServerSocket(8888);//未传ip，默认绑定127.0.0.1
        Socket s1 = ss1.accept();//保存连接到的客户端

        //读取客户端发来的数据
        BufferedInputStream bis1 = new BufferedInputStream(s1.getInputStream());
        byte[] bytes = streamToArray(bis1);//读取数据

        //存发来的二进制文件
        String path = "D:\\personal\\1.jpg";
        BufferedOutputStream bos1 = new BufferedOutputStream(new FileOutputStream(path));
        bos1.write(bytes);

        //回复客户端
        //字符流方式发送
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s1.getOutputStream()));
        bw.write("收到图片!");
        bw.flush();
        s1.shutdownOutput();

        //释放资源
        bw.close();
        bos1.close();
        bis1.close();
        s1.close();
        ss1.close();
    }

    public static byte[] streamToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int readLen = 0;
        while((readLen = is.read(b)) != -1){
            bos.write(b, 0, readLen);
        }
        byte[] array = bos.toByteArray();//将bos转字节数组
        bos.close();
        return array;
    }
}
