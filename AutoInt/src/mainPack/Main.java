package mainPack;

import java.util.ArrayList;
import java.util.Random;

import mealyMachine.MealyMachine;
import mealyMachine.Transition;
import testThings.Test;
import testThings.TestTester;

public class Main {
	public static void main(String args[]){
		Random r=new Random();
		int numStates=3;
		int alphSize=3;
		int numTests=10;
		int lonTest=12;
		int mut=10;
		MealyMachine m=new MealyMachine(numStates, alphSize);//3 estados, 1 2 3 como inputs
		m.addTransition(new Transition(0,r.nextInt(numStates),0,r.nextInt(alphSize)));
		m.addTransition(new Transition(0,r.nextInt(numStates),1,r.nextInt(alphSize)));
		m.addTransition(new Transition(0,r.nextInt(numStates),2,r.nextInt(alphSize)));
		m.addTransition(new Transition(1,r.nextInt(numStates),0,r.nextInt(alphSize)));
		m.addTransition(new Transition(1,r.nextInt(numStates),2,r.nextInt(alphSize)));
		m.addTransition(new Transition(1,r.nextInt(numStates),1,r.nextInt(alphSize)));
		m.addTransition(new Transition(2,r.nextInt(numStates),0,r.nextInt(alphSize)));
		m.addTransition(new Transition(2,r.nextInt(numStates),1,r.nextInt(alphSize)));
		m.addTransition(new Transition(2,r.nextInt(numStates),2,r.nextInt(alphSize)));
		ArrayList<Integer> inp=new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> tests=new ArrayList<ArrayList<Integer>>();

		for(int i=0;i<lonTest;i++){
			inp.add(r.nextInt(alphSize));//Llenamos el input inicial (el que se quiere testar).
		}

		Test t=new Test(inp, m);
		t.runTest();
		for(int i=0;i<numTests;i++){
			tests.add(new ArrayList<Integer>());
		}
		for(int i=0;i<numTests;i++){
			for(int j=0;j<lonTest;j++){
				tests.get(i).add(r.nextInt(alphSize));//Llenamos los inputs
			}
		}
		ArrayList<TestTester> ttt=new ArrayList<TestTester>();
		for(int i=0;i<numTests;i++){
			ttt.add(new TestTester(t, m, mut));
		}
		for(int i=0;i<numTests;i++){
			ttt.get(i).testTest(1);
			ttt.get(i).showRes();
		}
		//t.show();
		System.out.println(m.toDotString());
	}
}
