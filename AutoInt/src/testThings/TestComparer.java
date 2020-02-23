package testThings;

import java.util.ArrayList;

import mealyMachine.MealyMachine;

//Vamos a meter toda la funcionalidad de TestTester en TestComparer, es decir, la de testear
//el/los tests con las mutaciones de la máquina y compararlos.
//vamos a necesitar tener aquí un arrayList de mutaciones y uno de tests en vez del arrayList de testTesters
public class TestComparer {
	private int mutType;
	private MealyMachine m;
	private int numMuts;
	//private ArrayList<TestTester> ttList;
	private ArrayList<MealyMachine> mutList;
	private ArrayList<Test> testList;
	private ArrayList<ArrayList<Integer>> compMatrix;
	/*
	 * Cada test de testList es uno de los múltiples tests que tenemos y queremos ver cuáles son mejores etc
	 * Tenemos que probar cada uno de ellos en cada una de las mutaciones y comparar cómo ha resultado el test
	 * en la mutación vs en la máquina original
	 */
	
	public TestComparer(MealyMachine m, int mutType, int numMuts){
		this.m=m;
		this.mutType=mutType;
		this.numMuts=numMuts;
		//ttList=new ArrayList<TestTester>();
		mutList = new ArrayList<MealyMachine>();
		testList = new ArrayList<Test>();
		compMatrix = new ArrayList<ArrayList<Integer>>();
		createMutations();
	}
	
	//Creamos las mutaciones de la máquina
	public void createMutations() {
		for(int i = 0; i < this.numMuts; i++) {
			this.mutList.add(m.generateMutant(this.mutType));
		}
	}
	
	public void addTest(Test t) {
		this.testList.add(t);
	}
	
	//Runneamos todos los tests en todas las mutaciones y construimos compMatrix al hacerlo
	public void runAll(){
		//runneamos cada test para tener los outputs y esas mierdas
		for(int i = 0; i < testList.size(); i++) {
			ArrayList<Integer> tempComps = new ArrayList<Integer>();
			testList.get(i).runTest();
			for(int j = 0; j < mutList.size(); j++) {
				tempComps.add(compare(testList.get(i), this.m, mutList.get(j)));
			}
			//Al salir del bucle tempComps es un arrayList con los valores de las comparaciones de testList(i)
			//runneado en la máquina original vs en cada mutación
			
			//Ahora añadimos tempComps a compMatrix y pasamos al siguiente test
			compMatrix.add(tempComps);
		}
	}
	
	//Runneamos el mismo test en dos máquinas obteniendo la secuencia 
	//de outputs para ambas y comparamos éstas devolviendo la primera 
	//posición en la que difieren o 0 si no lo hacen.
	public Integer compare(Test t, MealyMachine m1, MealyMachine mutation){
		ArrayList<Integer> m1Out = t.runTestMachined(m1);
		ArrayList<Integer> mutOut = t.runTestMachined(mutation);
		for(int i = 0; i< m1Out.size() && i < mutOut.size(); i++) {
			if(!m1Out.get(i).equals(mutOut.get(i))) {
				return i + 1;
			}
		}
		return 0;
	}
	
	/*
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
	
	public void showRes(){
		this.test.show();
		for(int i=0;i<numMuts;i++){
			System.out.print(this.comp.get(i));
			System.out.print(' ');
		}
		System.out.print('\n');
	}
	*/
	
	//Para cada test (fila de la matriz) llamamos a getRow
	public String getMatrix() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.compMatrix.size(); i++) {
			sb.append(getRow(this.compMatrix.get(i)));
		}
		return sb.toString();
	}
	
	public void showMatrix() {
		for(int i = 0; i < this.compMatrix.size(); i++) {
			//System.out.println(this.compMatrix.get(i).size());
			showRow(this.compMatrix.get(i));
		}
	}
	
	public String getRow(ArrayList<Integer> list){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i).toString());
			sb.append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	//Cada fila son los datos de las comparaciones de un test con todas las mutaciones
	public void showRow(ArrayList<Integer> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			System.out.print(' ');
		}
		System.out.print('\n');
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
		return this.testList.size();
	}
	public String getOutput(int i){
		return this.testList.get(i).getStringOutputs();
	}
}
