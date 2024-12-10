import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

class MyTest {

	BetCard testBC = new BetCard(4, 9);


	@Test
	void constructorTTTESST() {
		assertEquals(4, testBC.getSpotsPlaying());
		assertEquals(9, testBC.getDrawingsNum());
	}

	@Test
	void constructorTest2(){
		BetCard defaultS = new BetCard();
		assertEquals(defaultS.getSpotsPlaying(), 0);
		assertEquals(defaultS.getDrawingsNum(), 0);
		assertEquals(defaultS.getPrizeMoney(), 0);
	}

	@Test
	//testing random generation
	void randomGenTest1(){
		Set<Integer>  testSet = new LinkedHashSet<>();
		//generate a random set of numbers
		testBC.pickME();
		for(int a : testBC.getSpotArray()){
			testSet.add(a);
		}
		testBC.clearArr();
		testBC.pickME();
		assertNotEquals(testSet, testBC.getSpotArray());
	}

	@Test
	void randomGenTest2(){
		Set<Integer> testSet2 = new LinkedHashSet<>();
		Set<Integer> getSet = testBC.resultOfDraw();

		for(int b : getSet){
			testSet2.add(b);
		}
		testBC.clearDraw();
		getSet= testBC.resultOfDraw();

		assertNotEquals(testSet2, getSet);
	}


	@Test
	void clearSpotArrayTest(){
		testBC.pickME();
		assertNotEquals(testBC.getSpotArray().size(), 0);
		testBC.clearArr();
		assertEquals(testBC.getSpotArray().size(), 0);
	}

	@Test
	void clearDrawingsSetTest(){
		Set<Integer> drawSet = testBC.resultOfDraw();
		assertNotEquals(drawSet.size(), 0);
		testBC.clearDraw();
		assertEquals(drawSet.size(), 0);
	}

	@Test
	void getSpots(){
		assertEquals(4, testBC.getSpotsPlaying());
	}

	@Test
	void getDraws(){
		assertEquals(9, testBC.getDrawingsNum());
	}

	@Test
	void prizeTest(){
		int[] testArray = {4};
		//to see if prize works
		testBC.pickME();

		for(int i = 0; i < testArray.length; i++){
			testBC.prize(testArray[i]);
		}
		assertEquals(testBC.getPrizeMoney(), 75);
	}

	@Test
	void prizeTest2(){
		BetCard maxPrize = new BetCard(10, 4);
		int matched = 10;
		maxPrize.pickME();
		int mP = maxPrize.prize(matched);
		assertEquals(mP, 100000);
	}

	@Test
	void prizeTest3(){
		BetCard minPrize = new BetCard(1, 4);
		int matched = 1;
		minPrize.pickME();
		int Mp = minPrize.prize(matched);
		assertEquals(Mp, 2);
	}

	@Test
	void biggestPrize(){
		BetCard bP = new BetCard(10, 4);
		int matched = 10;
		bP.pickME();
		int prizeMons = 0;

		while(true){
			if(bP.getDrawingsNum() > 0){
				prizeMons = bP.prize(matched);
				bP.setDrawings(bP.getDrawingsNum()-1);
			}else{
				break;
			}
		}
		assertEquals(bP.getPrizeMoney(), 400000);
	}
	@Test
	void setSpots(){
		testBC.setSpotsPlayed(8);
		assertEquals(testBC.getSpotsPlaying(), 8);
	}

	@Test
	void setDraws(){
		testBC.setDrawings(4);
		assertEquals(testBC.getDrawingsNum(), 4);
	}

	@Test
	void getSpotArray(){

		Set<Integer> getSet = testBC.getSpotArray();
		testBC.add(5);
		testBC.add(3);
		testBC.add(9);
		testBC.add(1);
		Set<Integer> tester = new LinkedHashSet<>(getSet);
		assertEquals(getSet, tester);
		assertEquals(getSet.size(), 4);

	}

	@Test
	void getDrawingTest(){
		Set<Integer> set = testBC.resultOfDraw();
		assertEquals(set, testBC.resultOfDraw());

	}

	@Test
	void betCardSim1(){
		//4 spots three drawings
		BetCard sim1 = new BetCard(4, 3);
		int prizeM = 0;
		//they decide to have the computer to pick for themselves
		sim1.pickME();
		//make sure it isn't empty
		assertNotEquals(sim1.getSpotArray().size(), 0);
		Set<Integer> result = sim1.resultOfDraw();
		assertNotEquals(result.size(), 0);
		Set<Integer> intersect = new LinkedHashSet<>(sim1.getSpotArray());
		int count = 0;
		while(sim1.getDrawingsNum() > 0){
			//get how many numbers match the result
			intersect.retainAll(result);
			prizeM = sim1.prize(intersect.size());
			sim1.setDrawings(sim1.getDrawingsNum()-1);
			sim1.clearDraw();
			result = sim1.resultOfDraw();
			count++;
		}
		//making sure the count went three times (The first draw technically counts as one
		assertEquals(count, 3);
		if(prizeM == 0){
			assertEquals(prizeM, 0);
		}else{
			assertNotEquals(prizeM, 0);
		}
		System.out.println("User won: $" + sim1.getPrizeMoney());
	}

	@Test
	void prizeTestSetNumbers(){
		BetCard prizeRig = new BetCard(4, 4);
		prizeRig.add(5);
		prizeRig.add(10);
		prizeRig.add(23);
		prizeRig.add(80);
		assertEquals(prizeRig.getSpotArray().size(), 4);

		Set<Integer> spot = new LinkedHashSet<>(prizeRig.getSpotArray());
		Set<Integer> res = new LinkedHashSet<>(spot);
		assertEquals(spot, res);

		int count = 0;
		int prize = 0;
		Set<Integer> intersector = new LinkedHashSet<>(spot);
		while(prizeRig.getDrawingsNum() > 0){
			intersector.retainAll(res);
			prize = prizeRig.prize(intersector.size());
			prizeRig.setDrawings(prizeRig.getDrawingsNum()-1);
		}
		//since all the numbers match, the max prize for all 4 spots is $75
		//the drawing then multiplies it
		int riggedMoney = 75 * 4;
		assertEquals(prizeRig.getPrizeMoney(), riggedMoney);

	}

	@Test
	void maxSpotsChosenTest(){
		BetCard chosenSpots = new BetCard(4, 1);
		chosenSpots.add(19);
		chosenSpots.add(42);
		chosenSpots.add(10);
		chosenSpots.add(21);
		chosenSpots.add(42);
		chosenSpots.add(9);
		assertEquals(chosenSpots.getSpotArray().size(), 4);
	}

}
