package com.edwin;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.*;
//  Created by Edwin Kam on 7/24/20.
//  Copyright © 2020 Edwin. All rights reserved.
//8/6/2020 java version
public class Main {
    public static final int numset = 2; //how many set of card will be use
    public static double percent = 0.65;//how many cards used before before shuffling
    public static int gamenum = 30000; //how many games you want
    
    //function
    //function
    //function
    //string pokerName[1 + totalcard];
    //int pokerValue[1 + totalcard];
    public static int cardcount = 1;//distrube card should be always 1
    public static final int totalcard = numset * 52;
    public static int []poker = new int [1 + totalcard];//number of card in play
    public static int []shuffnum= new int [totalcard];
    public static int []dealer =new int [10];
    public static int []player= new int[1000];
    public static double []bet= new double[10];
    public static int handscount=0;
    public static int psize = 1000;
    public static int dsize = 10;
    public static int psum, dsum,dealerflag; //dealer sum player sum
    public static int playerhandcount = 1;
    public static int dealerhandcount = 2;
    public static double playercount = 0;
    public static double dealercount = 0;
    public static double basebet=1;//bet value
    public static int gamecount, positive,checkdouble;//count in intdis()
    public static double playerwin, dealerwin, tiegame, split, doublerate,lastp,lastd;
    public static int gg=0;
    public static int lastgame = 0;
    public static int maxmoney=0,userinput;
    public static int leastmoney=0, gap=0;
    public static int pan= 0;
    public static int save[]= new int[100];
    public static  boolean back=false;
    public static Scanner s = new Scanner(System.in);



    public static void main(String[] args) {
        CreateFile file = new CreateFile("number.txt");
        System.out.println("Enter 1 = 124, 2 = 1246 old version, 3 = 1246 new verison");

        while(userinput<1||userinput>3) {
            System.out.print("Your choice (1-3): ");
            userinput = s.nextInt();
        }
        create(poker, numset);
        //print(poker, numset);
        while (gamecount < gamenum)
        {//loop until desired game amount played
            shuffle(poker, numset);
            print(poker, numset);//print the shuffled deck
            cardcount = 1;
            positive = 0;//initial posivity
            do {
                System.out.println("====================New Game===================");
                System.out.printf("Game %d\n",gamecount + 1);
                intialhands(player,psize);
                intialhands(dealer,dsize);
                switch (userinput) {
                    case 1: //124
                        if (positive>4)
                        {
                            setbet(4);
                            System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                            System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                        }
                        else if (positive>2)
                        {
                            setbet(2);
                            System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                            System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                        }
                        else
                        {
                            setbet(1);
                            System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                            System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                        }
                    case 2: //1246 old version
                        //only change things in case 1
                        if (positive <= 3)//if current poaitive less than this number, then set bet to ...
                        {
                            setbet(1);
                            System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                            System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                        }
                        else //if greater than that positive
                        {
                            if (lastgame == 1) {
                                setbet(2);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                            } else if (lastgame >= 4) {
                                setbet(6);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                            } else if (lastgame >= 2) {
                                //cout<<"6666666";
                                setbet(4);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                            } else {
                                System.out.println("error1");
                                System.exit(0);
                            }
                        }
                            // dont change anything below
                            break;
                    case 3: //1246
                        if (back) {//bet(6)
                            if (positive > 3) {
                                back = true;
                                setbet(6);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d\n", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                                System.out.println("Last game auto bet6. Next game will also be bet of $6");
                            } else {//less than 4 positive
                                back = false;
                                setbet(6);System.out.printf("Current positivity: %d.\tLast game bet was: %d\n", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);System.out.println("Since last game bet was $6");
                            }
                        } else if (positive <= 3)//if current poaitive less than this number, then set bet to ...
                            {
                                setbet(1);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d\n", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                            } else //if greater than that positive
                        {
                            if (lastgame == 1) {
                                setbet(2);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d\n", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                            } else if (lastgame >= 4) {
                                back = true;
                                setbet(6);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d\n", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                                System.out.println("Next game will also be bet of $6");
                            } else if (lastgame >= 2) {
                                        //cout<<"6666666";
                                setbet(4);
                                System.out.printf("Current positivity: %d.\tLast game bet was: %d\n", positive, lastgame);
                                System.out.printf("$$$$$$set bet to $ %.1f.\n", bet[0]);
                            } else {
                                System.out.println("error1");
                                System.exit(0);
                                    }
                                }
                                break;
                            default:
                                System.err.println("User input error");
                                System.exit(0);
                        }



                lastgame = (int)bet[0];
                player[handscount*10 + 0] = intdis();//disturbiting cards
                player[handscount*10 + 1] = intdis();
                //int temp = value(player[0]) + value(player[1]);//sum of player hands
                coutcard(player, "Player",handscount);//cout player hand
                dealer[0] = intdis();//disturbiting cards
                dealer[1] = intdis();
                System.out.printf("Dealer has %s\n", display(dealer[0]));
                coutcard(dealer, "Dealer",0);
                checkdouble = 0;//initialize double flag//blackjack(player, "Player", handscount);//check if player has blackjack
                int temp = value(player[handscount * 10 + 0]) + value(player[handscount * 10 + 1]);//sum of player hands
                //coutcard(player, "Player",handscount);//cout player hand


                if (blackjack(dealer, "Dealer", 0) && blackjack(player, "Player", handscount))
                {

                    //cout << "PUSH!!!!!!\n";
                    System.out.println("PUSH!!!");
                }
                else if (blackjack(dealer, "Dealer", 0) && !blackjack(player, "Player", handscount))//dealer has bj but player dont
                {

                    //cout << "SORRY!!\n";
                    System.out.println("SORRY!!");
                }
                else if (blackjack(player, "Player", handscount) && !blackjack(dealer, "Dealer", 0))//player has blackjack
                {
                    //gg = gamecount + 1;
                    //cout << "Dealer no blackjack\n";
                    System.out.println("Dealer no blackjack");
                    bet[handscount] = bet[handscount] * 1.5;
                    System.out.printf("Player gets $ %.1f \n", bet[handscount]);
                }
                else if (!blackjack(dealer, "Dealer", 0)&&!blackjack(player, "Player", handscount))//if dealer no blackjack and player no blackjack
                {
                    //cout << "-----------------Call section-----------------\n";
                    System.out.println("-----------------Call section-----------------");
                    if (player[handscount * 10 + 0] == player[handscount * 10 + 1] && player[handscount * 10 + 0] < 10)//pairs except 10
                    {
                        //cout << "!!!!!!!!!!!!!!!!!!";
                        play(splitaction(player[handscount * 10 + 0]));//go to splite with that card
                    }
                    else if (checkace(player, handscount)) //if player has an ace
                    {
                        play(aceaction(temp - 1));
                    }
                    else
                    {
                        play(paction(temp));//just keep playing
                    }
                    dealerflag = 1;//make sure only display dealer's turns once
                    dealeraction();//dealeraction
                }

                result();
                System.out.printf("Player won: $ %.1f\tDealer won: $ %.1f\n",lastp,lastd );
                System.out.printf("Player win: $ %.1f\tDealer win: $ %.1f\n",playercount,dealercount );
                lastp=playercount;
                lastd=dealercount;
                System.out.printf("Player net winning: $%.1f\n", playercount - dealercount);
                file.record((int)playercount - (int)dealercount);
                file.recordString("\n");
                System.out.printf("Positive: %d\n", positive);

                if (playercount - dealercount>maxmoney) //if current net win > peak
                {
                    maxmoney = (int)playercount - (int)dealercount; //set new peak
                }
                else
                {
                    if (maxmoney-(playercount - dealercount)>gap)
                    {
                        gap=(int)maxmoney-((int)playercount - (int)dealercount);
                    }
                }
                gamecount++;
            } while (cardcount < totalcard * percent);//how much of the card
            //cout << "gamecount: " << gamecount << "    This set Used card count: " << cardcount << endl;
            System.out.printf("Gamecount: %d\tThis set used card count: %d",gamecount,cardcount);

        }
        System.out.println();
        System.out.println("===============================================================================");
        System.out.format("%-16s:%7.0f    %-10s:%5.2f\n", "Player win game", playerwin,"Playerwin rate",playerwin / gamecount);
        System.out.format("%-16s:%7.0f    %-10s:%5.2f\n", "Dealer win game", dealerwin,"Dealerwin rate",dealerwin / gamecount);
        System.out.format("%-16s:%7.0f    %-10s:%5.2f\n", "TIE game", tiegame,"Tie rate",tiegame / gamecount);
        System.out.format("%-16s:%7.0f    %-10s:%5.2f\n", "Double win game", doublerate,"Double win rate",doublerate / gamecount);
        System.out.format("%-16s:%7.0f    %-10s:%5.2f\n", "Split win game", split,"Split win rate",split / gamecount);
        System.out.format("%-16s: $%.1f    %s: $%.1f\n", "Player wins", playercount,"Dealer wins",dealercount);
        System.out.format("%-16s: $%.0f\n","Player net winning", playercount-dealercount);
        System.out.format("%-16s: $%d    %-10s:$%d\n", "Player max win", maxmoney,"Player max lose",gap);
        System.out.print("Split game: ");
        file.close();
        int i=pan-1;
        while(i>pan-10)
        {
           // System.out.printf("%d ", save[i]);
            i--;
        }
        System.out.printf("\nAA split game: %d\n", gg+1);


    }
    public static void start()
    {
        checkdouble = 0;//initialize double flag
        //blackjack(player, "Player", handscount);//check if player has blackjack
        int temp = value(player[handscount*10 + 0]) + value(player[handscount*10 + 1]);//sum of player hands
        //coutcard(player, "Player",handscount);//cout player hand
        if (blackjack(player, "Player", handscount) && !blackjack(dealer, "Dealer",0))
        {
            System.out.println("Dealer no blackjack");
            //bet[handscount] = bet[handscount] * 1.5;
            System.out.printf("Player gets: $ %.1f\n", bet[handscount]);
        }
        else if (blackjack(dealer, "Dealer",0) && !blackjack(player, "Player", handscount))
        {
            System.out.println("SORRY!!");
        }
        else if (blackjack(dealer, "Dealer", 0) && blackjack(player, "Player", handscount))
        {
            System.out.println("PUSH!!!");
        }
        else if (!blackjack(dealer, "Dealer",0))//if dealer no blackjack
        {
            if (player[handscount*10 + 0] == player[handscount * 10 + 1] && player[handscount * 10 + 0] < 10)//pairs except 10
            {
                System.out.println("!!!!!!!!!!!!!!");
                play(splitaction(player[handscount * 10 + 0]));//go to splite with that card
            }
            else if (checkace(player, handscount)) //if player has an ace
            {
                play(aceaction(temp - 1));
            }
            else
            {
                play(paction(temp));//just keep playing
            }

        }
    }

    public static void shuffle(int[] arr, int numset)
    {
        Random rand = new Random();
        back =false;
        lastgame=0;
        for (int i = 1; i < arr.length; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length-1)+1;
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
           // System.out.printf("%d ", i);
        }
        System.out.println("\n****Shuffling******");
    }

    public static void create(int[] poker, final int numset)
    {
        poker[0] = 0;
        for (int i = 0; i < 4 * numset; i++)//new set of card
        {
            poker[1 + i * 13] = 1;
            poker[2 + i * 13] = 2;
            poker[3 + i * 13] = 3;
            poker[4 + i * 13] = 4;
            poker[5 + i * 13] = 5;
            poker[6 + i * 13] = 6;
            poker[7 + i * 13] = 7;
            poker[8 + i * 13] = 8;
            poker[9 + i * 13] = 9;
            poker[10 + i * 13] = 10;
            poker[11 + i * 13] = 12;
            poker[12 + i * 13] = 13;
            poker[13 + i * 13] = 14;

        }//for
    }

    public static void print(int[] arr, final int numset)
    {
        //cout << "  " << numset << " set of card in play. Shuffled deck: ";
        System.out.printf("   %d set of card in play. Shuffled deck: ", numset);
        int totalcard = numset * 52;
        for (int i = 1; i <= totalcard; i++)
        {
            if (arr[i]==0)
            {
                System.err.println("error8");
                System. exit(0);
            }
            System.out.printf("%s ", display(arr[i]));;

        }
        System.out.println();
    }

    public static void printf(String[] arr, final int numset)
    {
        int totalcard = numset * 52;
        System.out.print("Suffled deck: ");
        for (int i = 1; i <= totalcard; i++)
        {
            //cout << arr[i] << " ";
            System.out.printf("%s ", arr[i]);;
        }
    }

    public static int intdis()
    {
        if (poker[cardcount] <= 7 && poker[cardcount] >= 2)//2-6 positive
        {
            positive++;
        }
        else if (poker[cardcount] > 9 || poker[cardcount] == 1)//10 and A negetive
        {
            positive--;
        }
        return poker[cardcount++];//distribute the next card

    }

    public static int paction(int choice)//without Ace
    {
        //1 = hit;
        //2 = double
        //3 = stand
        //4 = split
        switch (choice)
        {
            case 5:
            case 6:
            case 7:
            case 8:
            {
                //cout << "*******player called hit" << endl;
                return 1;
            }
            //break;
            case 9:
                if (value(dealer[0]) <= 6 && value(dealer[0]) > 2)
                {
                    //cout << "*******player called double" << endl;
                    return 2;

                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }

              //  break;
            case 10:
                if (value(dealer[0]) <= 9 && value(dealer[0]) > 1)
                {
                    //cout << "*******player called double" << endl;
                    return 2;

                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }


                //break;
            case 11:
                if (blackjack(player, "Player", handscount))
                    return 3;
                else if (value(dealer[0]) > 1)
                {
                    //cout << "*******player called double" << endl;
                    return 2;
                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }

               // break;
            case 12:
                if (value(dealer[0]) >= 4 && value(dealer[0]) <= 6)
                {
                    //cout << "*******player called stand" << endl;
                    return 3;

                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }

               // break;
            case 13:
            case 14:
            case 15:
            case 16:
                if (value(dealer[0]) <= 6 && value(dealer[0]) > 1)
                {
                    //cout << "*******player called stand" << endl;
                    return 3;

                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }

              //  break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            {
                //cout << "*******player called stand" << endl;
                return 3;
            }


          //  break;
            default:

                System.err.printf("error2 got input of case %d", choice);
                System. exit(0);
                return 0;
          //  break;
        }
    }

    public static int aceaction(int hand)
    {
        switch (hand)
        {
            case 2:
            case 3:
                if (value(dealer[0]) <= 4 || value(dealer[0]) >= 7)//1-4, 7-10
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
                else
                {
                    //cout << "*******player called double" << endl;
                    return 2;
                }
             //   break;
            case 4:
            case 5:
                if (value(dealer[0]) <= 3 || value(dealer[0]) >= 7)//1-3,7-10
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
                else
                {
                    //cout << "*******player called double" << endl;
                    return 2;
                }
               // break;
            case 6:
                if (value(dealer[0]) >= 3 && value(dealer[0]) <= 6)//3-6
                {
                    //cout << "*******player called double" << endl;
                    return 2;
                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
               // break;
            case 7:
                if (value(dealer[0]) == 9 || value(dealer[0]) == 10 || value(dealer[0]) == 1)//9, 10 , 1
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
                else if (value(dealer[0]) == 2 || value(dealer[0]) == 7 || value(dealer[0]) == 8)//2, 7 , 8
                {
                    //cout << "*******player called stand" << endl;
                    return 3;
                }
                else
                {
                    //cout << "*******player called double" << endl;
                    return 2;
                }
               // break;
            case 8:
            case 9:
            case 10: //cout << "*******player called stand" << endl;
                return 3;
            //break;
            default:
                //cout << "error3 got input of case "<< hand;
                System.err.printf("error3 got input of case %d", hand);
                System. exit(0);
                return 0;
           // break;
        }
    }

    public static int splitaction(int hand)
    {
        switch (hand)
        {
            case 1:
                //cout << "*******player called split" << endl;
                return 5;
           // break;
            case 2:
            case 3:
                if (value(dealer[0]) >= 4 && value(dealer[0]) <= 7)
                {
                    //cout << "*******player called split" << endl;
                    return 4;
                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
               // break;
            case 4:
                if (value(dealer[0]) >= 5 && value(dealer[0]) <= 6)
                {
                    //cout << "*******player called split" << endl;
                    return 4;
                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
            case 5:
                if (value(dealer[0]) == 1 || value(dealer[0]) == 10)
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
                else
                {
                    //cout << "*******player called double" << endl;
                    return 2;
                }
               // break;
            case 6:
                if (value(dealer[0]) >= 2 && value(dealer[0]) <= 6)
                {
                    //cout << "*******player called split" << endl;
                    return 4;
                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
               // break;
            case 7:
                if (value(dealer[0]) >= 2 && value(dealer[0]) <= 7)
                {
                    //cout << "*******player called split" << endl;
                    return 4;
                }
                else
                {
                    //cout << "*******player called hit" << endl;
                    return 1;
                }
               // break;
            case 8:
                //cout << "*******player called split" << endl;
                return 4;
            //break;

            case 9:
                if (value(dealer[0]) == 7 || value(dealer[0]) == 10 || value(dealer[0]) == 1)
                {
                    //    cout << "*******player called stand" << endl;
                    return 3;
                }
                else
                {
                    //cout << "*******player called split" << endl;
                    return 4;
                }
              //  break;
            default:
                //cout << "error4 got input of case "<< hand;
                System.err.printf("error4 got input of case %d", hand);
                System. exit(0);
                return 0;
           // break;
        }
    }

    public static void play(int action)//1hit 2double 3stand 4split 5splithit
    {

        switch (action)
        {
            case 1: //hit
               //cout  << handscount + 1 << "player called hit" << endl;
                System.out.printf("%dplayer called hit\n", handscount+1);
                player[++playerhandcount] = intdis();//distribute from play[2]
                psum = sum(player, handscount);//player sum
                coutcard(player, "Player", handscount);//display card;

                if (sum(player, handscount) < 12 && checkace(player, handscount))//check whether stand with ace
                {
                    if (aceaction(sum(player, handscount) - 1) == 1 || aceaction(sum(player, handscount) - 1) == 1)//if supposed hit or double//both changed to 1 hit
                    {
                        play(1);//play(hit)
                    }
                    else
                    {
                        play(3);//play(stand)

                    }
                }
                else if (sum(player, handscount) < 22)//without ace but <22
                {
                    if (paction(sum(player, handscount)) == 1 || paction(sum(player, handscount)) == 2)//if supposed hit or double
                    {
                        play(1);//play(hit)
                    }
                    else
                    {
                        play(3);//play(stand)

                    }
                }
                else
                    pbust(player, handscount);//player busts
                break;
            case 2: //double
                doublerate++;
                checkdouble = 1;//double flag
                bet[handscount] = bet[handscount] * 2;//double the bet
               // cout << handscount + 1 << "player called double" << endl;
                System.out.printf("%dplayer called double\n", handscount+1);
                System.out.printf("$$bet of $ %.1f \n", bet[handscount]);
                player[handscount * 10 + 2] = intdis();
                acevalue(player, handscount);//determine ace value
                coutcard(player, "Player", handscount);
                pbust(player, handscount);//check player busts
                break;
            case 3://stand
                acevalue(player, handscount);
                //cout << handscount + 1 << "player called stand\t\t\tFinal: " <<handscount +1<< "Player sum: " << sum(player, handscount) << endl;
                System.out.printf("%dplayer called stand\n", handscount+1);
                //coutcard(player, "Player",handscount);

                break;
            case 4: //split

                System.out.println("(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((");
                System.out.printf("%dplayer called split\n", handscount+1);
                int i = 0;
                if(pan>80){
                    pan=0;
                }
                save[pan++] = gamecount + 1;//save split game

                split++;
                while (player[i * 10] != 0)
                {
                    i++;
                    //find the next empty stack
                }
                if(i==2){
                   // gg=gamecount;
                }
                player[handscount * 10 + 1] = intdis();//assign second card to first hand
                coutcard(player, "Player", handscount);//display 1player
                //handscount++;//next hand
                if (player[0]==11){///split the card the second hand
                    player[i * 10] = 1;//however, if play[0] has ace but value of 11 could lead error
                }
                //so just assign it to one
                else
                {
                    player[i * 10] =player[0];//otherwise assign second player the first player[o]
                }
                player[i * 10 + 1] = intdis();//assign second card to second hand
                coutcard(player, "Player", i);//display second hand
                //handscount--;//next hand
                System.out.printf("----------------%dPlayer turn-----------------", handscount+1);
                start();//finish first hand
                handscount = i;//next hand
                System.out.printf("----------------%dPlayer turn-----------------", handscount+1);
                coutcard(player, "Player", handscount);//display second hand
                //cout << "count" << handscount << endl;
                playerhandcount = 1 + i * 10;//make sure disturebute to next stack
                //cout << "playerhandcount" << playerhandcount<<endl;

                start();//finish second hand
                //handscount++;
                break;

            case 5://ace split
                save[pan++]=gamecount;//save split gamecount
                split++;
                gg=gamecount;
                player[1]=intdis();//distribute first hand second card
                player[10]=player[0];//distribute second hand first card
                //coutcard(player, "player",handscount);//display first hand
                acevalue(player, handscount);//determine ace value
                coutcard(player, "Player", handscount);
                pbust(player, handscount);//check player busts
                handscount++;

                player[11]=intdis();
                //coutcard(player, "player",handscount);
                acevalue(player, handscount);//determine ace value
                coutcard(player, "Player", handscount);
                pbust(player, handscount);//check player busts
                break;

            default:
               // cout << "error5 got input of case "<<action;
                System.err.printf("error5 got input of case %d",action);
                System. exit(0);
                break;
        }
    }
    public static void dealeraction()
    {
        int checkbust = 0;//to see if dealer needs action
        int trystack = 0;//try from array[0]
        while (player[trystack*10] != 0) {
            if (sum(player, trystack) < 22) {//if player sum < 21 then dealer needs action
                checkbust = 1;
            }trystack ++;//inc
        }

        if (checkbust == 1)//dealer needs action
        {
            if (dealerflag == 1) {
                //cout << "----------------Dealer turn-------------------\n";
                System.out.println("----------------Dealer turn-------------------");
                coutcard(dealer, "Dealer", 0);
                dealerflag = 0;//set dealer flag to 0 so it wouldnt display again
            }
            playerhandcount = 1;//initial playerhand
            if (sum(dealer, 0) > 21)
            {
                dbust(dealer);//cehck dealer busts
            }
            else if (!checkace(dealer, 0) && sum(dealer, 0) > 16)//dealer sum >17
            {
                //coutcard(dealer, "Dealer", 0);//dealer stand and display
                //cout << "Dealer stand\t\t\tDealer Sum: "<< sum(dealer,0 )<<endl;
                System.out.printf("Dealer stand \t\t\tdealer sum: %d\n", sum(dealer,0));


            }
            else if (checkace(dealer, 0) && sum(dealer, 0) > 7 && sum(dealer, 0) < 12)//dealer has ace and dealer sum-1 is 7-10
            {
                acevalue(dealer, 0);
                //coutcard(dealer, "Dealer", 0);//display
                //cout << "Dealer stand\t\t\t Dealer Sum: "<< sum(dealer,0 )<<endl;
                System.out.printf("Dealer stand \t\t\tdealer sum: %d\n", sum(dealer,0));


            }
            else if (checkace(dealer, 0) && (sum(dealer, 0) - 1) == 6) //soft 17, ace and 6
            {
               // cout << "Dealer soft 17!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" << endl;
                System.out.println("Dealer soft 17!!!!!!!!!!!!!!!!!!!!!");
                dealer[dealerhandcount++] = intdis();
                acevalue(dealer, 0);
                coutcard(dealer, "Dealer", 0);
                dbust(dealer);//check dealer busts
                if (sum(dealer, 0) < 17)//if still <17
                {
                    dealer[dealerhandcount] = intdis();//distribute third card
                    dealerhandcount++;
                    coutcard(dealer, "Dealer", 0);
                    dealeraction();//do dealer action again
                }
            }
            else if (sum(dealer, 0) < 17)//if still <17
            {
                dealer[dealerhandcount] = intdis();//distribute third card
                dealerhandcount++;
                coutcard(dealer, "Dealer", 0);
                dealeraction();//do dealer action again
            }
        }






    }

    public static String display(int value)
    {
        switch (value)
        {
            case 1: return "A";
            //break;
            case 2: return "2";
            //break;
            case 3: return "3";
           // break;
            case 4: return "4";
           // break;
            case 5: return "5";
           // break;
            case 6: return "6";
            //break;
            case 7: return "7";
           // break;
            case 8: return "8";
           // break;
            case 9: return "9";
           // break;
            case 10: return "10";
           // break;
            case 11: return"A";
           // break;
            case 12: return "J";
           // break;
            case 13: return "Q";
          //  break;
            case 14: return "K";
            //    break;


            default:
                System.err.printf("error6 got input of case%d", value);
                System. exit(0);
                return "error";
           // break;


        }

    }

    public static int value(int value)//return card value
    {
        if (value < 12 || value == 19)
        {
            return value;
        }
        else//for 12, 13, 14
        {
            return 10;
        }
    }

    public static int sum(int[] arr, int hand)
    {
        int startpoint = hand*10;
        int sum = 0;
        for (int i = startpoint; i < startpoint+10; i++)
        {
            sum += value(arr[i]);
        }
        return sum;
    }

    public static void coutcard(int[] arr, String name,int hand)
    {
        int startpoint = hand * 10;
        //cout << hand +1<< name << " card: ";
        System.out.format("%d%s card:%-20s%d%s sum: %-4dP: %d\n", hand+1, name, tostring(arr,startpoint),hand+1,name,sum(arr, hand), positive);


       // System.out.printf("%d%s sum: %d   P: %d\n",hand+1,name,sum(arr, hand), positive);

    }

    public static String tostring(int[] arr, int startpoint){
        String output="";
        for (int i = startpoint; i < startpoint +10; i++)
        {
            if (arr[i] != 0) {
                //cout << display(arr[i]) << ", ";
                //System.out.printf("%s, ", display(arr[i]));
                output = output+display((arr[i]))+", ";
            }
        }
        return output;
    }

    public static boolean checkace(int[] arr,int hand)
    {
        int startpoint = hand * 10;
        for (int i = startpoint; i < startpoint+10; i++)
        {
            if (arr[i] == 1||arr[i]==11) {
                return true;

            }
        }
        return false;
    }

    public static void acevalue(int[] arr, int hand)
    {
        int startpoint = hand * 10;
        for (int j = startpoint; j < startpoint+10; j++)
        {
            if (arr[j] == 1 && sum(arr, startpoint) < 12)//if after stand gets an ace No explod if a=11
            {
                arr[j] = 11;
                //cout << "in ace stand" << endl;
                //coutcard(player, "Player",handscount);//display card;
            }

        }
    }

    public static void result()
    {
        int itrystack = 0;//try from array[0]
        while (player[itrystack*10] != 0) {
            System.out.print("[FINAL]");
            coutcard(player, "Player", itrystack);
            itrystack++;
        }
        System.out.print("[FINAL]");
        coutcard(dealer, "Dealer", 0);
        int trystack = 0;//try from array[0]
        System.out.println();
        while (player[trystack*10] != 0) {
            if (sum(player, trystack) < 22 && sum(dealer, 0) < 22) {
                if (blackjack(player, "Player", trystack))
                {
                    System.out.printf("======%dPlayer win!!!!!\n",trystack  + 1 );
                    playerwin++;
                    System.out.printf("%dPlayer win: $ %.1f\n",trystack  + 1,bet[trystack]);
                    playercount += bet[trystack];//player get the bet
                }
                else if (sum(player, trystack) > sum(dealer, 0))
                {
                    System.out.printf("======%dPlayer win!!!!!\n",trystack  + 1 );
                    playerwin++;
                    System.out.printf("%dPlayer win: $ %.1f\n",trystack  + 1,bet[trystack]);
                    playercount += bet[trystack];//player get the bet
                }
                else if (sum(player, trystack) < sum(dealer, 0))
                {
                    System.out.printf("======%dDealer win!!!!!\n",trystack  + 1 );
                    dealerwin++;
                    System.out.printf("%dPlayer lose: $ %.1f\n",trystack  + 1,bet[trystack]);
                    dealercount += bet[trystack];//dealer gets the bet
                }
                else
                {
                    System.out.printf("======%dTIE game\n",trystack  + 1 );
                    tiegame++;
                }

            }
            else //one of them busted
            //no dealer or player++ because counted at pbust()
            //display only money counted at pbust or dbust()
            {
                if (sum(player, trystack) >21)//if player bust
                {
                    System.out.printf("======%dPlayer busted!!!!!\n",trystack  + 1 );
                    System.out.printf("%dPlayer lose: $ %.1f\n",trystack  + 1,bet[trystack]);
                    dealercount += bet[trystack];//dealer gets the bet
                }
                else if (sum(dealer, 0)>21)//dealer busted
                {
                    System.out.printf("======%dDealer busted!!!!!\n",trystack  + 1 );
                    System.out.printf("%dPlayer get: $ %.1f\n",trystack  + 1,bet[trystack]);
                    playercount += bet[trystack];//player get the bet
                }
                else{//catch error
                    System.err.println("error7");
                    System. exit(0);
                }
            }
            trystack ++;//inc
        }
        System.out.println("----------------------------------------------");
    }


    public static void dbust(int[] arr)
    {
        if (sum(arr, 0) > 21)
        {
            System.out.println("Dealerbusts >21");
            //cout << "=========Player won!!!!!" << endl;
            playerwin++;
            if (handscount==2)
            {
                // handscount--;//becasue I inc handscount in split
                //gg=gamecount;

                System.out.println("hi");
            }
            for (int i = 0; i < handscount+1; i++)
            {
                //playercount += bet[i];//in case if player doubles
                //cout << i + 1 << "player wins: $" << bet[i]<<endl;
                System.out.printf("%dPlayer wins: $%.1f\n", i+1, bet[i]);
                if(i==1)
                {
                    //gg= gamecount;
                }
            }
        }

    }

    public static void pbust(int[] arr,  int hand)
    {
        if (sum(arr, hand) > 21)
        {
            System.out.println("Playerbusts >21");
            //cout << "==========Dealer won!!!!!" << endl;
            //dealercount += bet[hand];//in case if player doubles
           // cout << handscount + 1 << "player loses: $" << bet[hand] << endl;//display before set the bet to 0
            //bet[handscount] = 0;
            System.out.printf("%dPlayer loses: $ %.1f\n",handscount + 1, bet[hand]);
            dealerwin++;

        }

    }
    public static void intialhands(int []arr,int size)
    {

        for (int i = 0; i < size; i++)
        {
            arr[i] = 0;
        }
        dealerhandcount = 2;
        playerhandcount = 1;
        handscount = 0;

    }

    public static boolean blackjack(int[] arr, String name,int hand)
    {
        //if (sum(arr, startpoint) == 21)
        //{
        //    return true;
//    }
        int startpoint = hand * 10;
        if (checkace(arr,hand) && sum(arr, hand) == 11)
        {
            for (int j = 0; j < 2; j++)
            {
                if (arr[j] == 1)//Ace and 10
                {
                    arr[j] = 11;//set ace to 11

                }

            }
           // cout << "========" << name << " has blackjack!!!\n";
            System.out.printf("=====%s has blackjack!!!!\n",name);
            return true;
        }
        else if (arr[2] == 0&& sum(arr, hand) == 21)//if third card not yet disturbuted
        {
            return true;
        }
        else return false;
    }

    public static void setbet(int mult)
    {
        for (int i = 0; i < 10; i++)
        {
            bet[i] = basebet * mult;
        }
    }

    public static final <T> void swap (T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


}
class CreateFile {
    private Formatter x;
    CreateFile(String filename){//open file in constructor
        openFile(filename);
    }

    public void openFile(String filename){//create file
        try{
            x= new Formatter(filename);
        }catch(Exception e){
            System.out.println("You have an error!");
            System.exit(0);
        }
    }
    public  void record(int number){
        x.format(Integer.toString(number));
    }
    public  void recordString(String string){
        x.format(string);
    }
    public void close(){
        x.close();
    }

}

