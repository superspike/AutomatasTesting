package testThings;

import java.util.ArrayList;

import mealyMachine.MealyMachine;



public class TestTester {
	private Test test;
	private MealyMachine m;
	private ArrayList<Test> tests;
	private Integer numMuts;
	private ArrayList<Integer> comp;
	public TestTester(Test test, MealyMachine m, int n){
		this.test=test;
		this.m=m;
		this.tests=new ArrayList<Test>();
		this.numMuts=n;
		this.comp=new ArrayList<Integer>();
	}
	
	public void testTest(int type){
		for(int i=0;i<numMuts;i++){
			this.tests.add(new Test(test.getInputs(), m.generateMutant(type)));
		}
		for(int i=0;i<numMuts;i++){
			tests.get(i).runTest();	
		}

		for(int i=0;i<numMuts;i++){
			this.comp.add(compare(this.test, this.tests.get(i)));
		}
	}
	
	public Integer compare(Test t1, Test t2){
		for(int i=0;i<t1.size()&&i<t2.size();i++){
			if(!t1.getOutputs().get(i).equals(t2.getOutputs().get(i)))return i+1;
		}
		return 0;
	}
	
	public String getRes(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<numMuts;i++){
			sb.append(this.comp.get(i).toString());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public void showRes(){
		this.test.show();
		for(int i=0;i<numMuts;i++){
			System.out.print(this.comp.get(i));
			System.out.print(' ');
		}
		System.out.print('\n');
	}
	
	public void showOutputs(){
		for(int i=0;i<numMuts;i++){
			this.tests.get(i).show();
		}
	}
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<numMuts;i++){
			sb.append(this.comp.get(i));
			sb.append(' ');
		}
		sb.append('\n');
		return sb.toString();
	}
	public String getOutput(){
		return this.test.getStringOutputs();
	}
}
	

