package com.edwin;

import java.util.Scanner;

public class Main {
    public static int count=0;
    public static int []month = new int[20];
    public static int []day = new int[20];
    public static int []year = new int[20];
    public static String whole;
    public static String [] save = new String [20];
    public static void main(String[] args) {
	// write your code here
        System.out.print("Enter 9999/ to exit");
        input();

        System.out.println(month);
        System.out.println(day);
        System.out.println(year);

        //int month = Integer.parseInt(s.next());
        //System.out.println(month);}
    }
    public static void input(){
        try {
            Scanner s = new Scanner(System.in);
            while(count<20){
                System.out.printf("%dInput", count+1);
            save[count]=s.nextLine();
            whole = save[count];
            Scanner w = new Scanner(whole);
            w.useDelimiter("/+|\\n");
             //   System.out.println("in");
            month[count] = Integer.parseInt(w.next());
            day[count] = Integer.parseInt(w.next());
            year[count] = Integer.parseInt(w.next());
            count++;}
            result();
        }catch (Exception e ){
            if(save[count].equals("9999/")){
                result();
            }
            else{
                System.out.println(save[count]);
            }
            System.out.println("Invalid input, please input again: ");
            input();
        }
    }
    public static void result(){
        System.out.printf("%-15s%s","user input","|output\n");
        for(int i=0; i<count; i++)
        System.out.printf("%-15s|%s %d, %d\n", save[i], displayMonth(month[i]), day[i], year[i]);
        System. exit(1);
    }
    public static String displayMonth(int month){
        switch(month){
            case 1: return "Jan";
            case 2: return "Feb";
            case 3: return "Mar";
            case 4: return "Apr";
            case 5: return "May";
            case 6: return "Jun";
            case 7: return "Jul";
            case 8: return "Aug";
            case 9: return "Sep";
            case 10: return "Oct";
            case 11: return "Nov";
            case 12: return "Dec";
            default: return "Jan";
        }
    }
}
