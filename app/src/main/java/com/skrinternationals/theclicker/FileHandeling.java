package com.skrinternationals.theclicker;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileHandeling {
        public static String readFile(String path){
                File file = new File(Environment.getExternalStorageDirectory(), path);
                StringBuilder text = new StringBuilder();
                try{
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;

                        while((line = br.readLine()) != null){
                                text.append(line);
                                text.append("\n");
                        }
                        br.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return text.toString().toUpperCase().replace("\n","");
        }

        public static void writeFile(String data, String filename){
                data = data.toUpperCase().replace("\n","");
                File textFile = new File(Environment.getExternalStorageDirectory(), filename+System.currentTimeMillis()+".txt");
                FileOutputStream fos;
                try {
                        fos = new FileOutputStream(textFile);
                        fos.write(data.getBytes());
                        fos.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}

