package mealyMachine;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MealyMachine {
	private Integer initialState=0;
	private ArrayList<State> stateList;
	private int alphabetSize=3;
	Scanner reader=new Scanner(System.in);
	Random r=new Random();
	
	public MealyMachine(int ns, int tamAlph, boolean rand){
		this.stateList=new ArrayList<State>();
		this.alphabetSize=tamAlph;
		if(rand){
			for(int i=0;i<ns;i++){
				this.stateList.add(new State(i));
				for(int j=0; j<tamAlph; j++){
					this.stateList.get(i).addTransition(new Transition(i,r.nextInt(ns),
						j, r.nextInt(tamAlph)));
				}
			}
		}
		else{
			for(int i=0;i<ns;i++){
				this.stateList.add(new State(i));
			}
		}
	}
	
	public MealyMachine(String s, int tamAlph) throws Exception{
		this.stateList=new ArrayList<State>();
		this.alphabetSize=tamAlph;
		String[] words=s.split("\n");
		String[] intro=words[0].split(" ");
		if(!intro[0].equalsIgnoreCase("digraph"))throw new Exception();
		if(!intro[2].equals("{"))throw new Exception();
		if(!words[1].equalsIgnoreCase("__start0 [label=\"\" shape=\"none\"];"))throw new Exception();
		if(!words[2].equalsIgnoreCase("        __start0 -> s0;"))throw new Exception();
		int i=3;
		while(words[i].length()<4){
			stateList.add(new State(i-3));
			i++;
		}
		while(words[i].charAt(words[i].length()-1)!='}'){
			this.addTransition(Transition.parseTransition(words[i]));
			i++;
		}
	}
	
	public MealyMachine(int n, int alphSize){
		this.stateList=new ArrayList<State>();
		this.alphabetSize=alphSize;
		for(int i=0;i<n;i++){
			stateList.add(new State(i));
		}
		int opcion=1;
		while(opcion!=0){
			System.out.println("0-End");
			System.out.println("1-Introduce Transition");
			opcion=reader.nextInt();
			if(opcion==1){
				Integer or, dest, in, out;
				or=reader.nextInt();
				dest=reader.nextInt();
				in=reader.nextInt();
				out=reader.nextInt();
				this.addTransition(new Transition(or, dest, in, out));
			}
		}
		this.complete();
	}

	private void complete(){
		System.out.println("¿Hace falta completar el automata?Es decir");
		System.out.println("poner estado sumidero");
	}
	
	public MealyMachine() {}


	/**
	 * set type to 0 to mutate outputs or 1 to mutate destinies
	 * @param type 
	 * @return 
	 */
	public MealyMachine generateMutant(int type){
		MealyMachine ret=this.clone();
		if(type==0){
				ret.mutateOutput();
			}
		else if(type==1){
				ret.mutateDestiny();
			}
		else{
			System.out.println("Error, exected 0 or 1");
		}
		return ret;
	}
	
	public void mutateOutput(){
		Random r=new Random();
		int randomInt1=r.nextInt(this.stateList.size());//Seleccionamos un estado aleatorio
		int randomInt2=r.nextInt(this.stateList.get(randomInt1).getSize());//Seleccionamos una transicion aleatoria dentro de ese estado aleatorio
		int randomInt3=r.nextInt(this.alphabetSize);
		//System.out.println(copia.stateList.get(randomInt1).getTransition(randomInt2).getOutput());
		this.stateList.get(randomInt1).getTransition(randomInt2).setOutput(randomInt3);
		//System.out.println(copia.stateList.get(randomInt1).getTransition(randomInt2).getOutput());
		//System.out.println(randomInt3);
		//System.out.println();
	}
	public void mutateDestiny(){
		int randomInt1=r.nextInt(this.stateList.size());//Seleccionamos un estado aleatorio
		int randomInt2=r.nextInt(this.stateList.get(randomInt1).getSize());//Seleccionamos una transicion aleatoria dentro de ese estado aleatorio
		int randomInt3=r.nextInt(this.stateList.size());//Seleccionamos un estado aleatorio como destino de la transicion elegida
		//System.out.println(copia.stateList.get(randomInt1).getTransition(randomInt2).getDest());
		this.stateList.get(randomInt1).getTransition(randomInt2).setDest(randomInt3);
		//System.out.println(randomInt3);
		//System.out.println(copia.stateList.get(randomInt1).getTransition(randomInt2).getDest());
		//System.out.println();
	}
	
	public Transition step(int cur, Integer input){
		return stateList.get(cur).step(input);
	}
	
	//private void generateMealy(String s) {}
	
	
	public void addTransition(Transition t){
		this.stateList.get(t.getOrig()).addTransition(t);
	}
	
	public MealyMachine clone(){
		MealyMachine ret=new MealyMachine();
		ret.alphabetSize=this.alphabetSize;
		ret.initialState=this.initialState;
		ret.stateList=new ArrayList<State>();
		for(int i=0;i<stateList.size();i++){
			ret.stateList.add(this.stateList.get(i).clone());
		}
		return ret;
	}
	
	public String toDotString(){
		StringBuilder sb=new StringBuilder();
		sb.append("digraph g {\n");
		sb.append(" __start0 [label=\"\" shape=\"none\"]\n");
        sb.append("__start0 -> \"s0\";\n");
		for(int i=0;i<this.stateList.size();i++){
			sb.append("s");
			sb.append(i);
			sb.append("[shape=\"circle\" label=\"");
			sb.append("s");
			sb.append(i);
			sb.append("\"];");
			sb.append("\n");
		}
		for(int i=0;i<this.stateList.size();i++){
			sb.append(this.stateList.get(i).toDotString());
		}
		sb.append("}\n");
		return sb.toString();
	}
	public String toString(){
		return this.toDotString();
	}
	public Integer getNumStates(){
		return this.stateList.size();
	}
	public Integer getAlphSize(){
		return this.alphabetSize;
	}
}
