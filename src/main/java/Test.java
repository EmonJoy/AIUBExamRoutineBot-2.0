import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("Data.txt"));
            String line;
            while ((line = bf.readLine()) != null){
                Long chatID = Long.parseLong(line.trim());
                System.out.println(chatID);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
