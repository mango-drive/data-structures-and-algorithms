#include <iostream>

using namespace std;

// My naive

int numberOfSteps(int num) {
    int steps = 0;

    while ( num != 0 )
    {
        if (num % 2 == 0)
        {
            ++steps;
            num /= 2;
        } else {
            // steps incremented in either case,
            // so may as well increment it outside
            // of if
            ++steps;
            num -= 1;
        }
    }

    return steps;
}

// Better naive

int numberOfSteps2 (int num) {
    int count = 0;

    while(num){
        if (num % 2 == 0) num /= 2;
        else num -= 1;
        count++;
    }

    return count;
}


// Optimal

int numberOfSteps (int num) {
    if (num == 0) return 0;
    int count = 0;

    while(num) {
        // this is not immediately obvious to me.
        // See below for what I think is the explanation
        if (num % 2) count += 2;
        else count++;
        num /= 2;
    }

    return count - 1;
}

// Say the number is a power of 2
// There will only be 
 
// Using Bitwise operators:

int numberOfSteps (int num) {
    if(num == 0) return 0;
    int count = 0;
    for(int p = 1; p <= num; p *= 2){
        // So ... is this a better modulo?
        if((p & num) != 0) count += 2;
        else count++;
    }
    return count - 1;
}

int numberOfSteps (int num) {
    if(num == 0) return 0;
    int steps = 0;
    while(num){
        (num&1)? steps+=2: steps+=1; //if bit is set then increment by 2 steps, else by 1 step
        num >>= 1;
    }
    return steps-1; //last bit is always set and requires only one step
}


int main(void)
{
    cout << numberOfSteps(14);
    return 0;
}