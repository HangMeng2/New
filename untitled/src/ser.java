
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author WL
 * @version 1.0
 */
public class ser {
    public static void main(String[] args) throws IOException {
        //客户端连接服务端ip、端口
        Socket s1 = new Socket(InetAddress.getLocalHost(), 8888);

        //创建输入流
        String path = "C:\\Users\\一生之敌\\Pictures\\Saved Pictures\\1.jpg";
        BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(path));

        //读取文件内容到字节数组
        byte[] bytes = streamToArray(bis1);

        //发送到数据通道
        BufferedOutputStream bos1 = new BufferedOutputStream(s1.getOutputStream());
        bos1.write(bytes);//写入数据
        s1.shutdownOutput();

        //接收消息
        InputStream inputStream = s1.getInputStream();
        byte[] s = streamToArray(inputStream);
        System.out.println("收到的消息:" + new String(s, 0, s.length));

        //释放资源
        inputStream.close();
        bis1.close();
        bis1.close();
        s1.close();
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
