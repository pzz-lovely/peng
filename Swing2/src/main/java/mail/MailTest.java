package mail;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;


/**
 * @Auther lovely
 * @Create 2020-03-15 17:31
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class MailTest {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("mail","mail.properties"))) {
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> lines = Files.readAllLines(Paths.get("mail", "message.txt"));

        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        Console console = System.console();
        String password = new String(console.readPassword("Password: "));

    }
}
