package testThings;

import java.util.ArrayList;

import mealyMachine.MealyMachine;

public class TestComparer {
	private int mutType;
	private MealyMachine m;
	private int numMuts;
	private ArrayList<TestTester> ttList;
	public TestComparer(MealyMachine m, int mutType, int numMuts){
		this.m=m;
		this.mutType=mutType;
		this.numMuts=numMuts;
		ttList=new ArrayList<TestTester>();
	}
	
	public void addTestTester(Test t){
		this.ttList.add(new TestTester(t, m, numMuts));
	}
	
	public void runAll(){
		for(int i=0;i<ttList.size();i++){
			ttList.get(i).testTest(mutType);
		}
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();

		for(TestTester tt: ttList){
			sb.append(tt.toString());
		}
		
		sb.append('\n');
		
		return sb.toString();
	}
	
	public String getMatrix(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.ttList.size();i++){
			sb.append(this.ttList.get(i).getRes());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void showMatrix(){
		for(int i=0;i<this.ttList.size();i++){
			this.ttList.get(i).showRes();
		}
	}
	public int getNumStates(){
		return this.m.getNumStates();
	}
	public int getAlphSize(){
		return this.getAlphSize();
	}
	public MealyMachine getMealy(){
		return this.m;
	}
	public int size(){
		return this.ttList.size();
	}
	public String getOutput(int i){
		return this.ttList.get(i).getOutput();
	}
}
