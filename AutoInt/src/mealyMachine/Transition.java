package mealyMachine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Transition {
	private Integer dest;
	private Integer orig;
	private Integer input;
	private Integer output;
	
	public Transition(Integer orig, Integer dest, Integer inp, Integer outp){
		this.orig=orig;
		this.dest=dest;
		this.input=inp;
		this.output=outp;
	}
	
	public Transition() {}

	public Integer getDest() {
		return dest;
	}

	public void setDest(Integer dest) {
		this.dest = dest;
	}
	public Integer getInput() {
		return input;
	}
	public void setInput(Integer input) {
		this.input = input;
	}
	public Integer getOutput() {
		return output;
	}
	public void setOutput(Integer output) {
		this.output = output;
	}
	public Integer getOrig() {
		return orig;
	}
	public void setOrig(Integer orig) {
		this.orig = orig;
	}
	
	public String toString(){
		return this.toDotString();
	}
	
	public String toDotString(){
		StringBuilder sb=new StringBuilder();
		sb.append("s");
		sb.append(this.orig);
		sb.append(" -> ");
		sb.append("s");
		sb.append(this.dest);
		sb.append(" [label=\"");
		sb.append(this.input);
		sb.append(" / ");
		sb.append(this.output);
		sb.append("\"];\n");
		return sb.toString();
	}
	
	
	public Transition clone(){
		Integer or, de, inp, outp;
		or=new Integer(this.orig);
		de=new Integer(this.dest);
		inp=new Integer(this.input);
		outp=new Integer(this.output);
		Transition copia=new Transition(or, de, inp, outp);
		return copia;
	}
	
	//Dados ambos sets, añadimos las strings del input y el output a éstos y creamos la transición con sus índices
	public static Transition parseTransition(String string, HashMap<String, Integer> inMap, HashMap<String, Integer> outMap){
		Integer or, dest;
		String in, out;
		String[] words=string.split(" ");
		or=Integer.parseInt(words[0].subSequence(1, words[0].length()-1).toString());
		dest=Integer.parseInt(words[2].subSequence(1, words[0].length()-1).toString());
		String[] inout1=words[3].split("\"");
		//La string "leída" la añadimos a inputSet (el alfabeto de inputs que hemos pasado como argumento)
		//in es el input de la transición que estamos parseando, si no está en el hashmap la añadimos
		in=inout1[1];
		if(!inMap.containsKey(in)) {
			//como asignamos los valores como índices enteros de un array, el k-ésimo elemento
			//tendra el value k-1, que es el .size() del mapa antes de añadirlo
			inMap.put(in, inMap.size());
		}
		//inputSet.add(in);
		String[] inout2=words[5].split("\"");
		//Lo mismo que con in=inout[1]
		out=inout2[0];
		if(!outMap.containsKey(out)) {
			outMap.put(in, outMap.size());
		}
		//outputSet.add(out);
		//Creamos la transición con los índices de in y out que obtenemos con getSetIndex y la returneamos
		return new Transition(or, dest, inMap.get(in), outMap.get(out));
	}
	
	//Searches a given set for an element.
	//Returns its index if it's in the set or -1 if it isn't
	//Debería hacer una clase que implemente Set y que tenga este método aparte 
	//pero ya para otro momento
	public static Integer getSetIndex(Set<String> set, String element) {
		int index = 0;
		for(String item:set){
			if(item.equals(element)) {
				return index;
			}
			index++;
		}
		return -1;
	}

}
