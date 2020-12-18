//
//  main.cpp
//  Red Pocket Game
//
//  Created by Edwin Kam on 12/18/20.
//

#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;
int num_player=10;
int dist(int skip, double[]);
double pocket=10;

int main() {
    cout << "Enter how many player: ";
    cin >>  num_player;
    double player[num_player];
    for(int i=0; i<num_player;i++){
        player[i]=100; //give everyone 100
    }
    srand((unsigned int)time(NULL));
    dist(rand()%num_player,player);
    return 0;
    
    
}
int dist(int skip, double* arr){ //return who get the least amount
    cout<< "first pay playetrr:" << skip<<endl;
    srand((unsigned int)time(NULL));
    int dist_first = rand()%num_player;
    
    while(dist_first==skip){
        dist_first = rand()%num_player; //loop unti first != skip
    }
    cout << "first dist player"<< dist_first<<endl;
    int curr=dist_first;
    double min=(int)pocket*100+1;
    double pool=pocket;
    int smallest_person=dist_first;
    
    do{
        if (curr==num_player){
            curr=0;
        }
        else if (curr!=skip){
            double curr_dist=(rand()%((int)pool*100))/100;
            if(curr_dist<min){
//                cout<<"min curr: "<<curr_dist<<" min: "<<min<<endl;
                min=curr_dist;
                smallest_person=curr;
            }
            cout<<"curr: "<<curr<<" got: "<<curr_dist<<endl;
            arr[curr]+=curr_dist;
            pool-=curr_dist;
            curr++;
        }
        else{
            curr++;
        }
    }while(curr!=dist_first);
    cout<<"smmalest personm"<<smallest_person<<endl;
    return smallest_person;
}
