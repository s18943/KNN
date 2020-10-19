package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String csvTrainSet = args[1];
        String csvTestSet = args[2];
        ArrayList<InelVector> trainSet = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(csvTrainSet));
            String str;
            String[] fields;
            while((str = bfr.readLine()) != null){
                fields = str.split(",");
                trainSet.add(new InelVector(fields));
            }
            kNN proc = new kNN(k,trainSet);
            bfr = new BufferedReader(new FileReader(csvTestSet));
            while((str = bfr.readLine()) != null){
                fields = str.split(",");
                proc.determ(new InelVector(fields));
            }
            Scanner in = new Scanner(System.in);
            str="";
            while(!str.equals("e")){
                System.out.println("input - i");
                System.out.println("exit - e");
                str = in.nextLine();
                switch (str) {
                    case"i":
                        System.out.println("input 4 parameters or more in format 0.0 /n anything else to stop");
                        ArrayList<String> tmp = new ArrayList<String>();
                        str=in.nextLine();
                        while(str.matches("\\d+\\.\\d+")){
                            tmp.add(str);
                            str=in.nextLine();
                        }
                        tmp.add("none");
                        String []arr = new String[tmp.size()];
                    proc.determ(new InelVector(tmp.toArray(arr)));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
