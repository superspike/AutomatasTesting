package mealyMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class MealyMachine {
	private Integer initialState=0;
	private ArrayList<State> stateList;
	//vamos a tener dos alfabetos, uno de entrada y uno de salida
	//cada alfabeto va a ser un HashMap, podemos mantener los elementos de
	//cada alfabeto únicos haciendo que sean los values y sus keys sean sus "índices"
	private int inAlphabetSize = 3;
	//private Set<String> inAlph;
	private HashMap<String, Integer> inAlph;
	private int outAlphabetSize = 3;
	//private Set<String> outAlph;
	private HashMap<String, Integer> outAlph;
	Scanner reader=new Scanner(System.in);
	Random r=new Random();
	
	//En el constructor en el que generamos la máquina de mealy aleatoriamente
	//le asignaremos a los elementos del set la string del entero de su índice (0-tamAlph-1)
	public MealyMachine(int ns, int inAlphSize, int outAlphSize, boolean rand){
		this.stateList=new ArrayList<State>();
		this.inAlph = new HashMap<String, Integer>();
		this.outAlph = new HashMap<String, Integer>();
		this.inAlphabetSize = inAlphSize;
		this.outAlphabetSize = outAlphSize;
		if(rand){
			int randOut;
			for(int i=0;i<ns;i++){
				this.stateList.add(new State(i));
				for(int j=0; j < inAlphSize; j++){
					//Checkear esto para ver si en transición se le asigna el output y tal
					//para que elija uno dentro del outAlphSize
					randOut = r.nextInt(outAlphSize);
					this.stateList.get(i).addTransition(new Transition(i,r.nextInt(ns), j, randOut));
					//Añadimos el input (j) y el output (el rand generado) de la transición a los alfabetos como strings
					//this.inAlph.add(Integer.toString(j));
					//this.outAlph.add(Integer.toString(randOut));
					this.inAlph.put(Integer.toString(j), j);
				}
				for(int j = 0; j < outAlphSize; j++) {
					this.outAlph.put(Integer.toString(j), j);
				}
			}
		}
		else{
			for(int i=0;i<ns;i++){
				this.stateList.add(new State(i));
			}
		}
	}
	
	//Al parsear las transiciones podemos ir generando el set llamando a un addToSet o algo así
	//porque en el archivo o lo que sea del que leamos los inputs/outputs pueden estar en otro formato que 
	//no sean ints, entcones nos renta que  medida que vayamos leyendo y encontrando inputs/outputs los
	//añadamos a sus correspondientes sets y que el orden en el que los parseemos se corresponda con el entero 
	//que le vamos a asignar internamente (0<->n-1)
	public MealyMachine(String s, int inAlphSize, int outAlphSize) throws Exception{
		this.stateList=new ArrayList<State>();
		this.inAlphabetSize = inAlphSize;
		this.outAlphabetSize = outAlphSize;
		//this.inAlph = new HashSet<String>();
		//this.outAlph = new HashSet<String>();
		this.inAlph = new HashMap<String, Integer>();
		this.outAlph = new HashMap<String, Integer>();
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
		//Entrar a la función parseTransition para desde ahí poder añadir los elementos a los input y output sets
		//usando getters
		while(words[i].charAt(words[i].length()-1)!='}'){
			this.addTransition(Transition.parseTransition(words[i], this.inAlph, this.outAlph));
			i++;
		}
	}
	
	public MealyMachine(int n, int inAlphSize, int outAlphSize){
		this.stateList=new ArrayList<State>();
		this.inAlphabetSize = inAlphSize;
		this.outAlphabetSize = outAlphSize;
		for(int i=0;i<n;i++){
			stateList.add(new State(i));
		}
		Integer or, dest;
		String in, out;
		int opcion=1;
		while(opcion!=0){
			System.out.println("0-End");
			System.out.println("1-Introduce Transition");
			opcion=reader.nextInt();
			if(opcion==1){
				//La transición funciona con enteros, pero el usuario va a introducir un String para in/out
				//Metemos esa string en el Set de alfabeto correspondiente, y el valor entero que le pasamos 
				//a addTransition es el índice de esas strings en sus correspondientes Sets
				or=reader.nextInt();
				dest=reader.nextInt();
				in=reader.nextLine();
				out=reader.nextLine();
				//Si el input introducido no está todavía en el mapa, si es el k-ésimo elemento
				//le vamos a asignar el índice (valor) k-1, que es el map.size() antes de añadirlo
				if(!this.inAlph.containsKey(in)) {
					this.inAlph.put(in, this.inAlph.size());
				}
				if(!this.outAlph.containsKey(out)) {
					this.outAlph.put(in, this.outAlph.size());
				}
				this.addTransition(new Transition(or, dest, this.inAlph.get(in), this.outAlph.get(out)));
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
		int randomInt3=r.nextInt(this.outAlphabetSize);
		//System.out.println(copia.stateList.get(randomInt1).getTransition(randomInt2).getOutput());
		//CREO QUE NO HACE FALTA QUE CAMBIEMOS NADA EN LA SGUIENTE PORQUE INTERNAMENTE SEGUIMOS TRATANDO LOS INPUTS/OUTS
		//COMO ENTEROS, SÓLO QUE TENEMOS QUE TENER ALGUNA MANERA DE TRADUCIRLOS, ASÍ QUE SETTEAR EL OUTPUT COMO INT
		//PUEDE QUE ESTÉ BIEN? TENGO QUE COMPROBARLO
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
		ret.inAlphabetSize=this.inAlphabetSize;
		ret.initialState=this.initialState;
		ret.stateList=new ArrayList<State>();
		ret.inAlph = new HashMap<String, Integer>();
		ret.outAlph = new HashMap<String, Integer>();
		for(int i=0;i<stateList.size();i++){
			ret.stateList.add(this.stateList.get(i).clone());
		}
		ret.inAlph.putAll(this.inAlph);
		ret.outAlph.putAll(this.outAlph);
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
	public Integer getInAlphSize(){
		return this.inAlphabetSize;
	}
	public Integer getOutAlphSize() {
		return this.outAlphabetSize;
	}
	public HashMap<String, Integer> getInAlph(){
		return this.inAlph;
	}
	public HashMap<String, Integer> getOutAlph(){
		return this.outAlph;
	}
	//Searches a given set for an element.
	//Returns its index if it's in the set or -1 if it isn't
	//Debería hacer una clase que implemente Set y que tenga este método aparte 
	//pero ya para otro momento
	public Integer getSetIndex(Set<String> set, String element) {
		int index = 0;
		for(String item:set){
			if(item.equals(element)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	public void addInput(String s) {
		this.inAlph.put(s, this.inAlph.size());
	}
	public void addOutput(String s) {
		this.outAlph.put(s, this.inAlph.size());
	}
}
