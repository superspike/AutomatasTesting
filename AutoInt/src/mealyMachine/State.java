package mealyMachine;

import java.util.ArrayList;


public class State {
	private Integer id;
	private ArrayList<Transition> list;
	
	public Transition step(Integer input){
		//Integer o=list.get(0).getOutput();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getInput().equals(input))
				return list.get(i);
		}
		return null;
	}
	
	public Transition getTransition(int pos){
		return this.list.get(pos);
	}
	
	public State(int id){
		this.id=id;
		this.list=new ArrayList<Transition>();
	}
	public State() {
		list=new ArrayList<Transition>();
	}
	
	public int getSize(){
		return list.size();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addTransition(Transition t){
		this.list.add(t);
	}
	public State clone(){
		State copia=new State();
		copia.id=new Integer(this.id.intValue());
		for(int i=0;i<this.list.size();i++){
			copia.list.add(this.list.get(i).clone());
		}
		return copia;
	}
	public String toDotString(){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<this.getSize();i++){
			sb.append(this.list.get(i).toDotString());
		}
		return sb.toString();
	}
}
