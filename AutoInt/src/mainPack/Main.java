package mainPack;

import java.util.ArrayList;
import java.util.Random;

import mealyMachine.MealyMachine;
import mealyMachine.Transition;
import testThings.Test;
import testThings.TestComparer;
import testThings.TestTester;

public class Main {
	public static void main(String args[]){
		//VIEW->TESTSCREEN LÍNEA 75 QUITAR COMENTARIO
		//TENGO QUE MIRAR A VER QUE LAS MUTACIONES SEAN CONSISTENTES PARA CADA FILA
		//Y QUE LOS TESTS SEAN CONSISTENTES PARA CADA COLUMNA
		//SIMPLEMENTE ES HACER PRINTS Y SHOWS Y TAL EN UN BUCLE PARA COMPROBAR QUE
		//LOS TESTS Y LAS MÁQUINAS Y ESAS MIERDAS SON LAS QUE DEBERÍAN SER
		Random r=new Random();
		int numStates=4;
		int inAlphSize=3;
		int outAlphSize=4;
		int numTests=10;
		int testLength=12;
		int mut=10;
		MealyMachine m=new MealyMachine(numStates, inAlphSize, outAlphSize, true);//4 estados, 1 2 3 como inputs 1 2 3 4 como outputs
		TestComparer tcomp = new TestComparer(m, 0, mut);

		
		ArrayList<Integer> inp=new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> tests = new ArrayList<ArrayList<Integer>>();

		for(int i=0;i<testLength;i++){
			inp.add(r.nextInt(inAlphSize));//Llenamos el input inicial (el que se quiere testar).
		}

		Test t=new Test(inp, m);
		t.runTest();
		/*
		for(int i=0;i<numTests;i++){
			tests.add(new ArrayList<Integer>());
		}
		for(int i=0;i<numTests;i++){
			for(int j=0;j<lonTest;j++){
				tests.get(i).add(r.nextInt(alphSize));//Llenamos los inputs
			}
		}
		*/
		//Creamos numTests aleatorios y los añadimos al TestComparer
		for(int i = 0; i < numTests; i++) {
			Test tempT = new Test(m, testLength);//Constructor que crea un test random con longitud testLength
			tcomp.addTest(tempT);
			//System.out.println(tempT.toString());
		}
		
		tcomp.runAll();
		tcomp.showMatrix();
		System.out.println("");
		System.out.println("");
		System.out.println("");
		/*
		ArrayList<TestTester> ttt=new ArrayList<TestTester>();
		for(int i=0;i<numTests;i++){
			ttt.add(new TestTester(t, m, mut));
		}
		for(int i=0;i<numTests;i++){
			ttt.get(i).testTest(1);
			ttt.get(i).showRes();
		}
		*/
		//t.show();
		
		System.out.println(m.toDotString());
		
		//VIEW->TESTSCREEN LÍNEA 75 QUITAR COMENTARIO
		//VIEW->TESTSCREEN LÍNEA 75 QUITAR COMENTARIO
		//VIEW->TESTSCREEN LÍNEA 75 QUITAR COMENTARIO
		//VIEW->TESTSCREEN LÍNEA 75 QUITAR COMENTARIO
	}
}
