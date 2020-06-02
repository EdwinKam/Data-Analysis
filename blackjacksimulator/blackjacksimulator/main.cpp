//
//  main.cpp
//  blackjacksimulator
//
//  Created by Edwin Kam on 6/1/20.
//  Copyright © 2020 Catherine. All rights reserved.
//

//6/1/2020 version
#include<iostream>
#include<ctime>
#include<string>
#include<stack>
#include<random>
#include<algorithm>


using namespace std;
//here
const int numset = 4; //how many set of card will be use
double percent = 0.65;//how many cards used before before shuffling
int gamenum = 100000; //how many games you want
//function
//function
//function
//function
//function
void shuffle(int*, size_t); //shuffle card function
void create(int*, const int);//poker card generator
void print(int*, const int);//function that print the the poker deck in order
void printf(string*, const int);//string function that print the the poker deck in order
int aceaction(int);//when player or dealer get ace
int splitaction(int);//when player can split
int paction(int);//player action
int intdis();//distributing card to player and dealer
void play(int);//play function
void dealeraction();//dealer action function
string display(int);
int value(int);//determine the value of the card
int sum(int*,int);//calculate the sum of hands
void coutcard(int*, string,int);//display players hand and sum
bool checkace(int*,int);//see if anyone has ace
void acevalue(int*,int);//determine ace value
void result();//result function
void dbust(int*);//check if dealer >21 player win count ++
void pbust(int*);//chck if player bomb >21 dealer win count ++
void intialhands(int*,int);//initialize player hands
bool blackjack(int*, string,int);//determine if anyone gets blackjack
void start();
void setbet(int);
//function
//function
//function
//string pokerName[1 + totalcard];
//int pokerValue[1 + totalcard];
int cardcount = 1;//distrube card should be always 1
const int totalcard = numset * 52;
int poker[1 + totalcard];//number of card in play
int shuffnum[totalcard];
int dealer[10];
int player[1000];
double bet[10];
int handscount=0;
int psize = 1000;
int dsize = sizeof(dealer) / sizeof(dealer[0]);
int psum, dsum,dealerflag; //dealer sum player sum
int playerhandcount = 1;
int dealerhandcount = 2;
double playercount = 0;
double dealercount = 0;
double basebet=1;//bet value
int gamecount, positive,checkdouble;//count in intdis()
double playerwin, dealerwin, tiegame, split, doublerate;
int gg=0;
int lastgame = 0;
int maxmoney=0;
int leastmoney=0;

int main()
{
    create(poker, numset);
    //print(poker, numset);
    while (gamecount < gamenum)
    {//loop until desired game amount played
        shuffle(poker, numset);
        print(poker, numset);//print the shuffled deck
        cardcount = 1;
        positive = 0;//initial posivity
        do {
            cout << "===================New Game==================\n";
            cout << "Game " << gamecount + 1 << endl;
            intialhands(player,psize);
            intialhands(dealer,dsize);
            
            if (positive <= 5)//if current poaitive less than this number, then set bet to ...
            {
                setbet(1);
                cout << "Current positivity: " << positive << ".  Last game bet was $" << lastgame << endl;
                cout << "$$$$$$set bet to $" << bet[0] << endl;
            }
            else //if greater than that positive
            {
                if (lastgame == 1)
                {
                    setbet(2);
                    cout << "Current positivity: " << positive << ".  Last game bet was $" << lastgame << endl;
                    cout << "$$$$$$$$$set bet to $" << bet[0] << endl;
                }
                else if (lastgame >= 2)
                {
                    setbet(4);
                    cout << "Current positivity: " << positive << ".  Last game bet was $" << lastgame << endl;
                    cout << "$$$$$$$$$increased bet to $" << bet[0] << endl;
                }
                else
                {
                    cout << "error1";
                    cin >> gg;
                }
            }
            
            lastgame = bet[0];
            player[handscount*10 + 0] = intdis();//disturbiting cards
            player[handscount*10 + 1] = intdis();
            //int temp = value(player[0]) + value(player[1]);//sum of player hands
            coutcard(player, "Player",handscount);//cout player hand
            dealer[0] = intdis();//disturbiting cards
            dealer[1] = intdis();
            cout << "Dealer has " << display(dealer[0]) << endl;//dealer exposed card
            coutcard(dealer, "Dealer",0);
            checkdouble = 0;//initialize double flag//blackjack(player, "Player", handscount);//check if player has blackjack
            int temp = value(player[handscount * 10 + 0]) + value(player[handscount * 10 + 1]);//sum of player hands
            //coutcard(player, "Player",handscount);//cout player hand
            
            
            if (blackjack(dealer, "Dealer", 0) && blackjack(player, "Player", handscount))
            {
                
                cout << "PUSH!!!!!!\n";
            }
            else if (blackjack(dealer, "Dealer", 0) && !blackjack(player, "Player", handscount))//dealer has bj but player dont
            {
                
                cout << "SORRY!!\n";
            }
            else if (blackjack(player, "Player", handscount) && !blackjack(dealer, "Dealer", 0))//player has blackjack
            {
                gg = gamecount + 1;
                cout << "Dealer no blackjack\n";
                bet[handscount] = bet[handscount] * 1.5;
                cout << "Player gets $" << bet[handscount] << endl;
            }
            else if (!blackjack(dealer, "Dealer", 0)&&!blackjack(player, "Player", handscount))//if dealer no blackjack
            {
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
            
            
            cout << "Player win: $ " << playercount << "\tDealer win: $ " << dealercount << endl;
            cout << "Player net winning: $" << playercount - dealercount << endl;
            cout << "Postivity: " << positive << endl;
            if (playercount - dealercount>maxmoney)
            {
                maxmoney = playercount - dealercount;
            }
            if (playercount - dealercount<leastmoney)
            {
                leastmoney = playercount - dealercount;
            }
            gamecount++;
        } while (cardcount < totalcard * percent);//how much of the card
        cout << "gamecount: " << gamecount << "    This set Used card count: " << cardcount << endl;

    }
    cout << "===============================================================================\n";
    cout << "Playerwin game: " << playerwin << "\t Playerwin rate: " << playerwin / gamecount << endl;
    cout << "Dealerwin game: " << dealerwin << "\t Dealerwin rate: " << dealerwin / gamecount << endl;
    cout << "TIE game: " << tiegame << "\tTie rate: " << tiegame / gamecount << endl;
    cout << "Double game: " << doublerate << "\t Double rate: " << doublerate / gamecount << endl;
    cout << "Split game : " << split << "\tSplit rate: " << split / gamecount << endl;
    cout << "Player win: $ " << playercount << "\tDealer win: $ " << dealercount << endl;
    cout << "Player net winning: $" << playercount - dealercount << endl;
    cout << "Player max win $" <<maxmoney<<"\t Player max lose: $" <<leastmoney<<endl;

}
void start()
{
    checkdouble = 0;//initialize double flag
    //blackjack(player, "Player", handscount);//check if player has blackjack
    int temp = value(player[handscount*10 + 0]) + value(player[handscount*10 + 1]);//sum of player hands
    //coutcard(player, "Player",handscount);//cout player hand
    if (blackjack(player, "Player", handscount) && !blackjack(dealer, "Dealer",0))
    {
        cout << "Dealer no blackjack\n";
        bet[handscount] = bet[handscount] * 1.5;
        cout << "Player gets $" << bet[handscount] << endl;
    }
    else if (blackjack(dealer, "Dealer",0) && !blackjack(player, "Player", handscount))
    {
        cout << "SORRY!!\n";
    }
    else if (blackjack(dealer, "Dealer", 0) && blackjack(player, "Player", handscount))
    {
        cout << "PUSH!!\n";
    }
    else if (!blackjack(dealer, "Dealer",0))//if dealer no blackjack
    {
        if (player[handscount*10 + 0] == player[handscount * 10 + 1] && player[handscount * 10 + 0] < 10)//pairs except 10
        {
            cout << "!!!!!!!!!!!!!!!!!!";
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

void shuffle(int* arr, size_t numset)
{
    srand(time(0));
    for(int i=1; i<=numset*52; i++)
    {
        int a = rand() % 52*numset+1;
        swap(arr[a], arr[i]);
       // cout<<" arr[i]"<<arr[i]<<" arr[a]"<<arr[a];
        //cout <<a << " "<<b<<" ";

    }
    cout << "****Shuffling******";
}

void create(int* poker, const int numset)
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

void print(int* arr, const int numset)
{
    cout << "  " << numset << " set of card in play. Shuffled deck: ";
    int totalcard = numset * 52;
    for (int i = 1; i <= totalcard; i++)
    {
        assert (arr[i]!=0);
        cout <<display(arr[i]) << " ";

        }
    
    cout << endl;
}

void printf(string* arr, const int numset)
{
    int totalcard = numset * 52;
    cout << "Shuffled deck: ";
    for (int i = 1; i <= totalcard; i++)
    {
        cout << arr[i] << " ";
    }
}

int intdis()
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

int paction(int choice)//without Ace
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
    break;
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

        break;
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


        break;
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

        break;
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

        break;
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

        break;
    case 17:
    case 18:
    case 19:
    case 20:
    case 21:
    {
        //cout << "*******player called stand" << endl;
        return 3;
    }


    break;
    default:
            cout << "error2 got input of case "<< choice;
        cin >> gg;
            return 0;
        break;
    }
}

int aceaction(int hand)
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
        break;
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
        break;
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
        break;
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
        break;
    case 8:
    case 9:
    case 10: //cout << "*******player called stand" << endl;
        return 3;
        break;
    default:
        cout << "error3 got input of case "<< hand;
        cin >> gg;
            return 0;
        break;
    }
}

int splitaction(int hand)
{
    switch (hand)
    {
    case 1:
        //cout << "*******player called split" << endl;
        return 4;
        break;
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
        break;
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
        break;
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
        break;
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
        break;
    case 8:
        //cout << "*******player called split" << endl;
        return 4;
        break;

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
        break;
    default:
            cout << "error4 got input of case "<< hand;
        cin >> gg;
            return 0;
        break;
    }
}

void play(int action)//1hit 2double 3stand 4split 5splithit
{

    switch (action)
    {
    case 1: //hit
        cout << "*******" << handscount + 1 << "player called hit" << endl;
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
            pbust(player);//player busts
        break;
    case 2: //double
        doublerate++;
        checkdouble = 1;//double flag
        bet[handscount] = bet[handscount] * 2;//double the bet
        cout << "*******" << handscount + 1 << "player called double" << endl;
        cout << "$$bet of $" << bet[handscount] << endl;
        player[handscount * 10 + 2] = intdis();
        acevalue(player, handscount);//determine ace value
        coutcard(player, "Player", handscount);
        pbust(player);//check player busts
        break;
    case 3://stand
        cout << "*******" << handscount + 1 << "player called stand" << endl;
        acevalue(player, handscount);
        //coutcard(player, "Player",handscount);

        break;
    case 4:{ //split
        cout << "((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((" << endl;
        cout << "*******" << handscount + 1 << "player called split" << endl;
        int i = 0;
        split++;
        while (player[i * 10] != 0)
        {
            i++;
            //find the next empty stack
        }
        player[handscount * 10 + 1] = intdis();//assign second card to first hand
        coutcard(player, "Player", handscount);//display 1player
        //handscount++;//next hand
        if (player[0]==11){///split the caard the second hand
        player[i * 10] = 1;//however, if play[0] has ace but value of 11 could lead error
        }//so just assign it to one
        else
        {
            player[i * 10] =player[0];//otherwise assign second player the first player[o]
        }
        player[i * 10 + 1] = intdis();//assign second card to second hand
        coutcard(player, "Player", i);//display second hand
        //handscount--;//next hand
        start();//finish first hand
        handscount = i;//next hand
        cout << handscount + 1 << "Player turn---" << endl;
        coutcard(player, "Player", handscount);//display second hand
        //cout << "count" << handscount << endl;
        playerhandcount = 1 + i * 10;//make sure disturebute to next stack
        //cout << "playerhandcount" << playerhandcount<<endl;

        start();//finish second hand
        handscount++;
        break;
    }
    default:
            cout << "error5 got input of case "<<action;
        cin>> gg;
        break;
    }
}
void dealeraction()
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
                cout << "--------------\nDealer turn\n";
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
                cout << "Dealer stand\n";
                
            }
            else if (checkace(dealer, 0) && sum(dealer, 0) > 7 && sum(dealer, 0) < 12)//dealer has ace and dealer sum-1 is 7-10
            {
                cout << "++++++++++++++++++++++++++++++++++++Dealer has Ace, check here\n";
                acevalue(dealer, 0);
                //coutcard(dealer, "Dealer", 0);//display
                cout << "Dealer stand\n";
                
            }
            else if (checkace(dealer, 0) && (sum(dealer, 0) - 1) == 6) //soft 17, ace and 6
            {
                cout << "Dealer soft 17!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" << endl;
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

string display(int value)
{
    switch (value)
    {
    case 1: return "A";
        break;
    case 2: return "2";
        break;
    case 3: return "3";
        break;
    case 4: return "4";
        break;
    case 5: return "5";
        break;
    case 6: return "6";
        break;
    case 7: return "7";
        break;
    case 8: return "8";
        break;
    case 9: return "9";
        break;
    case 10: return "10";
        break;
    case 11: return"A";
        break;
    case 12: return "J";
        break;
    case 13: return "Q";
        break;
    case 14: {return "K";
        break; }

    default:
            cout << "error6 got input of case "<<value;
            cout<< value;
        cin >> gg;
            return 0;
            
        break;


    }

}

int value(int value)//return card value
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

int sum(int* arr, int hand)
{
    int startpoint = hand*10;
    int sum = 0;
    for (int i = startpoint; i < startpoint+10; i++)
    {
        sum += value(arr[i]);
    }
    return sum;
}

void coutcard(int* arr, string name,int hand)
{
    int startpoint = hand * 10;
    cout << hand +1<< name << " card: ";
    for (int i = startpoint; i < startpoint +10; i++)
    {
        if (arr[i] != 0) {
            cout << display(arr[i]) << ", ";
        }
    }
    cout << "\t" << name << " sum: " << sum(arr, hand) << endl;
}

bool checkace(int* arr,int hand)
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

void acevalue(int* arr, int hand)
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

void result()
{
    int trystack = 0;//try from array[0]
    while (player[trystack*10] != 0) {
        if (sum(player, trystack) < 22 && sum(dealer, 0) < 22) {
            if (sum(player, trystack) > sum(dealer, 0))
            {
                cout << "========" << trystack  + 1 << "Player won!!!!!" << endl;
                playerwin++;
                cout << "$win: " <<bet[trystack] << endl;
                playercount += bet[trystack];//player get the bet
            }
            else     if (sum(player, trystack) < sum(dealer, 0))
            {
                cout << "========"<< trystack + 1<<"Dealer won!!!!!" << endl;
                dealerwin++;
                cout << "$lose: " << bet[trystack ] << endl;
                dealercount += bet[trystack];//dealer gets the bet
            }
            else
            {
                cout << "====" << trystack  + 1 << " TIE Game======" << endl;
                tiegame++;
            }

        }
        trystack ++;//inc
    }
}


void dbust(int* arr)
{
    if (sum(arr, 0) > 21)
    {
        cout << "==== Dealerbusts >21" << endl;
        cout << "=========Player won!!!!!" << endl;
        playerwin++;
        for (int i = 0; i < handscount+1; i++) {
            playercount += bet[i];//in case if player doubles
            cout << i + 1 << "player wins: $" << bet[i]<<endl;
        }
    }

}

void pbust(int* arr)
{
    if (sum(arr, handscount) > 21)
    {
        cout << "== Playerbusts >21" << endl;
        cout << "==========Dealer won!!!!!" << endl;
        dealercount += bet[handscount];//in case if player doubles
        cout << handscount + 1 << "player loses: $" << bet[handscount] << endl;//display before set the bet to 0
        bet[handscount] = 0;
        dealerwin++;
        
    }

}
void intialhands(int *arr,int size)
{

    for (int i = 0; i < size; i++)
    {
        arr[i] = 0;
    }
    dealerhandcount = 2;
    playerhandcount = 1;
    handscount = 0;

}

bool blackjack(int* arr, string name,int hand)
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
        cout << "========" << name << " has blackjack!!!\n";
        return true;
    }
    else if (arr[2] == 0&& sum(arr, hand) == 21)//if third card not yet disturbuted
    {
        return true;
    }
    else return false;
}

void setbet(int mult)
{
    for (int i = 0; i < 10; i++)
    {
        bet[i] = basebet * mult;
    }
}
