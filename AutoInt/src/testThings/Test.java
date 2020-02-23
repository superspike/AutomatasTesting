package testThings;

import java.util.ArrayList;
import java.util.Random;

import mealyMachine.MealyMachine;

public class Test {
	private ArrayList<Integer> inputs;
	private ArrayList<Integer> outputs;
	private final MealyMachine m;
	
	//Constructor that sets the machine an leaves inputs and outputs empty
	public Test(MealyMachine m){
		inputs=new ArrayList<Integer>();
		outputs=new ArrayList<Integer>();
		this.m=m;
	}
	//Constructor that sets the machine and the inputs
	public Test(ArrayList<Integer> h, MealyMachine m){
		inputs=new ArrayList<Integer>(h);
		outputs=new ArrayList<Integer>();
		this.m=m;
	}
	//Constructor that sets the machine and creates random inputs
	public Test(MealyMachine m, Integer length) {
		this.m = m;
		this.inputs = new ArrayList<Integer>();
		Random r = new Random();
		for(int i = 0; i < length; i++) {
			this.inputs.add(r.nextInt(m.getInAlphSize()));
		}
		outputs = new ArrayList<Integer>();
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
	

	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("input: ");
		for(Integer i:inputs){
			sb.append(i.toString());
			sb.append(" ");
		}
		sb.append("\noutput: ");
		for(Integer o:outputs){
			sb.append(o.toString());
			sb.append(" ");
		}
		return sb.toString();
	}
	
	//Empezando desde el estado 0, recorremos todos los inputs generando y 
	//guardando los outputs que obtenemos con step
	public void runTest(){
		int st=0;
		for(int i=0;i<inputs.size();i++){
			outputs.add(m.step(st, inputs.get(i)).getOutput());
			st=m.step(st, inputs.get(i)).getDest();
		}
	}
	
	//Dada una máquina runnea el test sobre ella y nos devuelve los outputs
	public ArrayList<Integer> runTestMachined(MealyMachine mach) {
		int st=0;
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i=0;i<inputs.size();i++){
			out.add(mach.step(st, inputs.get(i)).getOutput());
			st=mach.step(st, inputs.get(i)).getDest();
		}
		return out;
	}
	
	public int size(){
		return this.outputs.size();
	}
	public String getStringOutputs(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<outputs.size();i++){
			sb.append(outputs.get(i).toString());
			sb.append(" ");
		}
		return sb.toString();
	}
}
