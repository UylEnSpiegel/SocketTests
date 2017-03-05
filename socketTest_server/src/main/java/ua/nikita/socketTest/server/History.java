package ua.nikita.socketTest.server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikyt on 05.03.2017.
 */
public class History extends Thread {

    private static History instance;
    public static List<String> historyLinesList = new ArrayList<>();
    public static boolean working = false;

    private History(){
        String lineText;
        System.out.println();
        try{
            BufferedReader in = new BufferedReader(new FileReader("SomeFiles"+ File.separator+"History.txt"));
            while ((lineText = in.readLine()) != null) {
                historyLinesList.add(lineText);
            }
        in.close();
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }

    }

    public void run(){
        File file = new File("SomeFiles"+ File.separator+"History.txt");

        while (true){
            try{
            PrintWriter writers = new PrintWriter(file.getAbsoluteFile());

            StringBuilder sb = new StringBuilder();
            working = true;
            String s;
            for(int i=0;i<15;i++ ){
                writers.println(historyLinesList.get(i));
            }
                writers.close();
            }catch (java.io.FileNotFoundException f){f.printStackTrace();}


            working = false;
            try {
                Thread.sleep(5000);
            } catch (java.lang.InterruptedException e){
                e.printStackTrace();
            }
        }


    }

    public void UpdateList(String s){
        historyLinesList.add(s);
        if(historyLinesList.size()>15){
            historyLinesList.remove(0);
        }
    }




    public static History getInstance() throws IOException {
        if (instance == null) instance = new History();
        return instance;
    }

}
