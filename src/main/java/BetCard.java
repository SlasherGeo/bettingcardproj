import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedHashSet;
import java.util.Set;

public class BetCard {
    //spotsPlaying - the number of spots the player has chosen
    //spotArray - the numbers that the player has chosen in those spots
    //drawingsNum - the number of drawings that the player has chosen
    //spotRand - random generator that generates numbers between 1 - 80
    //noDups - the result set that will draw 20 numbers with no repeats
    //prizeMoney - total amount won in prize function
    //flagColor - flag var. for changeLook
    private int spotsPlaying;
    private Set<Integer> spotArray = new LinkedHashSet<>();
    private int drawingsNum;
    private Random spotRand = new Random();
    private Set<Integer> noDups = new LinkedHashSet<>();
    private int prizeMoney;
    private int flagColor;


    public BetCard(){
        this.spotsPlaying = 0;
        this.drawingsNum = 0;
    }
    public BetCard(int sP, int dN){
        this.spotsPlaying = sP;
        this.drawingsNum = dN;
        this.prizeMoney = 0;
    }


    public void setSpotsPlayed(int spots){
        this.spotsPlaying = spots;
    }
    public void setDrawings(int draws){this.drawingsNum = draws;}

    public void setFlagColor(int fC){
        this.flagColor = fC;
    }
    public int getFlagColor(){
        return this.flagColor;
    }

    //gets the number of spots played
    public int getSpotsPlaying(){
        return this.spotsPlaying;
    }
    public int getDrawingsNum(){
        return this.drawingsNum;
    }


    //returns the set of numbers the player has chosen
    public Set<Integer> getSpotArray(){
        return this.spotArray;
    }

    //picking random numbers for the user
    public void pickME(){
//        int randomness;
//        for(int i = 0; i < this.spotsPlaying; i++){
//            randomness = this.spotRand.nextInt(1, 81);
//            if(spotArray.contains(randomness)){
//                randomness = this.spotRand.nextInt(1, 81);
//            }
//            this.spotArray.add(randomness);
//        }
        while(this.spotArray.size() < this.spotsPlaying){
            this.spotArray.add(this.spotRand.nextInt(79)+1);
        }
        this.spotsPlaying = 0;
    }
    //adds a number to the user's selections
    public void add(int a){
        if(this.spotsPlaying < 0){
            return;
        }
        this.spotArray.add(a);
        this.spotsPlaying -= 1;
    }

    //takes the amount of spots the user has played (spotArray.size) and uses this filthy if else-if
    //tree to see how much money the player gets
    public int prize(int s){
        int prizeInst = 0;
        if(this.spotArray.size() == 1){
            if(s > 0){
                prizeInst += 2;
            }
        }else if(this.spotArray.size() == 4){
            if (s >= 4) {
                prizeInst += 75;
            } else if (s == 3) {
                prizeInst +=5;
            }else if (s == 2){
                prizeInst += 1;
            }
        }else if(this.spotArray.size() == 8){
            if(s >= 8){
                prizeInst += 10000;
            }else if(s == 7){
                prizeInst += 750;
            }else if(s == 6){
                prizeInst += 50;

            }else if(s == 5){
                prizeInst += 12;
            }else if (s == 4){
                prizeInst += 2;
            }
        } else if (this.spotArray.size() == 10) {
            if(s >= 10){
                prizeInst += 100000;
            }else if(s == 9){
                prizeInst += 4250;
            }else if(s == 8){
                prizeInst += 450;
            }else if(s == 7){
                prizeInst += 40;
            }else if(s == 6){
                prizeInst += 12;
            }else if(s == 5){
                prizeInst += 2;
            }else if(s == 0){
                prizeInst += 5;
            }

        }
        //store all the cash into this class variable and return the prize from this draw
        this.prizeMoney += prizeInst;
        return prizeInst;
    }
    public int getPrizeMoney(){
        return this.prizeMoney;
    }

    //generates 20 random numbers for the result
    //this should be called numerous times if the user's drawings are more than 1
    public Set<Integer> resultOfDraw(){
        while(this.noDups.size() < 20){
            this.noDups.add(this.spotRand.nextInt(79)+1);
        }
        return this.noDups;
    }

    //clears the result set
    public void clearDraw(){
        this.noDups.clear();
    }

    //clears the numbers that the user has chosen
    public void clearArr(){
        this.spotArray.clear();
    }
}
