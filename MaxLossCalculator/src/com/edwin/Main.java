package com.edwin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code
        CreateReadFile create = new CreateReadFile("result.txt");
        CreateReadFile read1 = new CreateReadFile();
        CreateReadFile read2 = new CreateReadFile();
        read1.readFile("C:/Users/kamsh/Documents/Git.mine/Data-Analysis/blackjackJavaEdition/number1.txt");
        read2.readFile("C:/Users/kamsh/Documents/Git.mine/Data-Analysis/blackjackJavaEdition/number2.txt");
        int maxmoney=0;
        int win=0,gap=0;
        while(read1.hasNextLine()&&read2.hasNextLine()) {
            win=(Integer.parseInt(read1.nextLine())+Integer.parseInt(read2.nextLine()));
            create.record(win);
            create.recordString("\n");
            if ( win> maxmoney) //if current net win > peak
            {
                maxmoney = win; //set new peak
            } else {
                if (maxmoney - win > gap) {
                    gap = maxmoney - win;
                }
            }
        }
        System.out.println(gap);
        create.close();


    }
}
class CreateReadFile{
    private Formatter x;
    CreateReadFile(String filename){openFile(filename);}
    CreateReadFile(){}
    public void openFile(String filename){//create file
        try{
            x= new Formatter(filename);
        }catch(Exception e){
            System.err.println("You have an error!");
            System.exit(0);

        }
    }
    public  void record(int number){
        x.format(Integer.toString(number));
    }
    public  void recordString(String string){
        x.format(string);
    }
    public void close(){x.close();}
    private Scanner s;
    private File file = null;
    public void readFile(String location){
        try {
            file = new File(location);  //location need to be something like C:/user/number.txt
            s = new Scanner(file);
        }catch (FileNotFoundException e){
            System.err.println("File not found");
            System.exit(0);
        }
    }
    public String nextLine(){
        return s.nextLine();
    }
    public boolean hasNextLine(){
        return s.hasNextLine();
    }
    public int nextInt(){
        return s.nextInt();
    }
    public String next(){
        return s.next();
    }

}