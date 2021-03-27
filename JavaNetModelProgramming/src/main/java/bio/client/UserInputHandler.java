package bio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther lovely
 * @Create 2020-04-01 13:28
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class UserInputHandler implements Runnable {
    private ChatClient client;


    public UserInputHandler(ChatClient client) {
        this.client = client;
    }


    @Override
    public void run() {
        //等待用户输入消息
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        try{
            while (true) {
                String line = consoleReader.readLine();
                //向服务器发送消息
                client.send(line);
                if (client.readyToQuit(line)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
