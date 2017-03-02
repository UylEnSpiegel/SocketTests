package ua.nikita.socketTest.server.FIlesBitch;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikyt on 02.03.2017.
 */
public class StaticHistoryTest {
    public static List<String> historyLinesList = new ArrayList<>();
        public static void main() {
            String lineText;
            try{
            BufferedReader in = new BufferedReader(new FileReader("SomeFiles"+File.separator+"text.txt"));
            while ((lineText = in.readLine()) != null) {
                historyLinesList.add(lineText);
            }}
            catch (java.io.IOException e){
                e.printStackTrace();
            }
        }

}
