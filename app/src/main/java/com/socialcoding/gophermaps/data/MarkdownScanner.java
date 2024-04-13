package com.socialcoding.gophermaps.data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class MarkdownScanner {
    public static String parseMD(String fname){
        try {
            Scanner s = new Scanner(new File(fname));
            StringBuilder res = new StringBuilder();
            while(s.hasNextLine()){
                res.append(s.nextLine());
            }
            return res.toString();
        } catch(FileNotFoundException e) {
            System.out.println(e);
            return "Error finding file";
        }
    }
}
