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
        read1.readFile("C:/Users/kamsh/Documents/Git.mine/Data-Analysis/Stock Market/number1.txt");//market price
        read2.readFile("C:/Users/kamsh/Documents/Git.mine/Data-Analysis/Stock Market/number2.txt");//MACD
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

    public static double averageDayChange(CreateReadFile file) {
        double lastPrice=(Double.parseDouble(file.nextLine()));
        double current =0;
        double index=1; //needs to be double because we need to divide that
        double sum=0;
        while (file.hasNextLine()) {
            try {
                current = (Double.parseDouble(file.nextLine()));//change string in the file to double
            } catch (Exception e) {
                System.err.println("Something not number in entered in the file. Please check.");
                System.exit(0);
            }
            sum+=(current-lastPrice);
            lastPrice=current;
        }
        return sum/index;
    }
    public static double lowestAverage(CreateReadFile file) {
        double[] lowest = new double[10000];
        double price = 0;
        int index = 0;
        while (file.hasNextLine()) {
            try {
                price = (Double.parseDouble(file.nextLine()));//change string in the file to double
            } catch (Exception e) {
                System.err.println("Something not number in entered in the file. Please check.");
                System.exit(0);
            }
            try {
                if (lowest[index] == 0) { //if nothing in the index, we just plug in the number first
                    lowest[index] = price;
                } else {
                    if (lowest[index] > price) {//if current < lowest
                        lowest[index] = price;
                    } else {
                        index++;//last index was a lowest because the price starting to fall
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                double[] temp = new double[2 * lowest.length];
                for (int i = 0; i < lowest.length; i++) {
                    temp[i] = lowest[i];
                }

                lowest = temp;

            }

        }
        double sum=0;
        double count=0.0;
        for(int i=0; i<lowest.length;i++){
            if(lowest[i]==0){
                break;
            }
            count++;
            sum+=lowest[i];
        }
        return sum/count;

    }

    public static double peakAverage(CreateReadFile file) {
        double[] peak = new double[10000];
        double price = 0;
        int index = 0;
        while (file.hasNextLine()) {
            try {
                price = (Double.parseDouble(file.nextLine()));//change string in the file to double
            } catch (Exception e) {
                System.err.println("Something not number in entered in the file. Please check.");
                System.exit(0);
            }
            try {
                if (peak[index] == 0) { //if nothing in the index, we just plug in the number first
                    peak[index] = price;
                } else {
                    if (peak[index] < price) {
                        peak[index] = price;
                    } else {
                        index++;//last index was a peak because the price starting to fall
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                double[] temp = new double[2 * peak.length];
                for (int i = 0; i < peak.length; i++) {
                    temp[i] = peak[i];
                }

                peak = temp;

            }

        }
        double sum=0;
        double count=0.0;
        for(int i=0; i<peak.length;i++){
            if(peak[i]==0){
                break;
            }
            count++;
            sum+=peak[i];
        }
        return sum/count;


    }
    public static double maxLoss(CreateReadFile file) {
        double peak=0;
        double win=0,gap=0;
        while(file.hasNextLine()) {
            try {
                win = (Double.parseDouble(file.nextLine()));//change string in the file to double
            }catch (Exception e){
                System.err.println("Something not number in entered in the file. Please check.");
                System.exit(0);
            }
            if ( win> peak) //if current net win > peak
            {
                peak = win; //set new peak
            } else {
                if (peak - win > gap) {
                    gap = peak - win;
                }
            }
        }
        return gap;
    }
    public static double maxProfit(CreateReadFile file) {
        double peak =0;
        double lowest = Double.parseDouble(file.nextLine());
        double max=0;
        double price=0;
        while(file.hasNextLine()) {
            try {
                price = (Double.parseDouble(file.nextLine()));//change string in the file to double
            }catch (Exception e){
                System.err.println("Something not number in entered in the file. Please check.");
                System.exit(0);
            }
            if(price<lowest){
                lowest=price;
                peak =0;//make sure peak happens after this lowest
            }else if(price>peak){
                peak =price;
                if(peak-lowest>max){
                    max=peak-lowest;
                }
            }
        }
        return max;
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