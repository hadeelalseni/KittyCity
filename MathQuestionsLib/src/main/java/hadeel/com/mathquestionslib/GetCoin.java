package hadeel.com.mathquestionslib;

import java.util.Random;

public class GetCoin {
    Random r = new Random();
    int low = 10;
    int high = 100;


    public int getNumberOne(){
        int number = r.nextInt(high-low) + low;
        return number;
    }

    public int getNumberTwo(){
        int number = r.nextInt(high-low) + low;
        return number;
    }

    public boolean checkAnswer(int answer, int numberOne, int numberTwo){
        int result = numberOne + numberTwo;
        if(result == answer){
            return true;
        }
        return false;

    }

}


