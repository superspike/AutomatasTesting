package testThings;

import java.util.ArrayList;

import mealyMachine.MealyMachine;

public class Test {
	private ArrayList<Integer> inputs;
	private ArrayList<Integer> outputs;
	private final MealyMachine m;
	public Test(MealyMachine m){
		inputs=new ArrayList<Integer>();
		outputs=new ArrayList<Integer>();
		this.m=m;
	}
	public Test(ArrayList<Integer> h, MealyMachine m){
		inputs=new ArrayList<Integer>(h);
		outputs=new ArrayList<Integer>();
		this.m=m;
	}
	public ArrayList<Integer> getInputs() {
		return inputs;
	}
	public void setInputs(ArrayList<Integer> inputs) {
		this.inputs = inputs;
	}
	public ArrayList<Integer> getOutputs() {
		return outputs;
	}
	public void setOutputs(ArrayList<Integer> outputs) {
		this.outputs = outputs;
	}
	
	public void show(){
		for(int i=0;i<this.outputs.size();i++){
			System.out.print(this.outputs.get(i));
			System.out.print(' ');
		}
		System.out.print('\n');
	}
	
	public void runTest(){
		int st=0;
		for(int i=0;i<inputs.size();i++){
			outputs.add(m.step(st, inputs.get(i)).getOutput());
			st=m.step(st, inputs.get(i)).getDest();
		}
	}
	public int size(){
		return this.outputs.size();
	}
}
