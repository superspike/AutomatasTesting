package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import mealyMachine.MealyMachine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FirstScreen extends MealyCreatorGroup {

	private FileChooser fc;
	private Stage sta;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Spinner<Integer> s1;
	private Spinner<Integer> s2;
	private SpinnerValueFactory<Integer> vf1;
	private SpinnerValueFactory<Integer> vf2;
	private Text t1;
	private Text t2;
	private Text t3;
	private TextArea autoDot;
	private Container c;
	private GridPane gp;
	private boolean created=false;
	
	public FirstScreen(Stage st, Container co){
		this.c=co;
		this.sta=st;
		fc=new FileChooser();
		b1=new Button("Create Random automaton");
		b2=new Button("Create Manual automaton");
		b3=new Button("Begin test");
		b4=new Button("Select file");
		s1=new Spinner<Integer>();
		s2=new Spinner<Integer>();
		vf1=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 3);
		vf2=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 3);
		t1=new Text("Number of states");
		t2=new Text("Size of alphabet");
		t3=new Text("Dot automata");
		autoDot=new TextArea();
		s1.setValueFactory(vf1);
		s2.setValueFactory(vf2);
		gp=new GridPane();
		b1.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                		//c.setMm(new MealyMachine(
	                			//	s1.getValue(), s2.getValue(), true));
	                		autoDot.setText(c.getMm().toDotString());
	                		created =true;
	                    }
	            });
		b2.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                		Scene sc=new Scene(new CreateManualAutomatonScreen
	                				(s1.getValue(),s2.getValue(), sta,c), 800,600);
	                		sta.setScene(sc);
	                		created=true;
	                		sta.show();
	                    }
	            });
		b3.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                		if(c.getMm()!=null){
	                			Scene sc=new Scene(new TestScreen
	                				(sta,c),800,600);
	                			sta.setScene(sc);
	                			sta.show();
	                		}
	                		else System.out.println("sin crear");
	                	}
	            });
		this.b4.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	String texto="";
	                	File file = fc.showOpenDialog(sta);
	                	if (file != null) {
                        //openFile(file);
	                		texto=readFile(file);
	                	}
	                	autoDot.setText(texto);
	                }
	            });
		gp.add(s1, 1, 0);
		gp.add(t1, 0, 0);
		gp.add(s2, 1, 1);
		gp.add(t2, 0, 1);
		gp.add(b1, 0, 2);
		gp.add(b2, 1, 2);
		gp.add(t3, 0, 3);
		gp.add(autoDot, 0, 4);
		gp.add(b3, 0, 5);
		gp.add(b4, 0, 6);
		gp.setVgap(10);
		gp.setHgap(10);
		this.getChildren().add(gp);
	}

	private String readFile(File file){
		  StringBuilder sb=new StringBuilder();
		  try {
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  String str; 
			while ((str = br.readLine()) != null) {
			   sb.append(str); 
			   sb.append("\n");
			  }
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public void writeAuto(){
		this.autoDot.setText(this.c.getMm().toDotString());
	}
	
	@Override
	public MealyMachine getMealyMachine() {
		return c.getMm();
	}
	
}
/*
 * TENGO QUE ARREGLAR LAS COSAS DE VIEW AHORA QUE HE CAMBIADO LA ESTRUCTURA DEL CÓGIDO CON LOS SETS Y DEMÁS
 * L 64-65 FirstScreen
 * L 101 FirstScreen
 * L 58 CreateManualAutomatonScreen
 * 
 * TENGO QUE MIRAR LAS FUNCIONES QUE CREAN LAS STRINGS QUE DESCRIBEN LAS MÁQUINAS Y LAS QUE LAS PARSEAN
 * PARA ADAPTARLAS A QUE USEN LOS SETS
 * 
 * La maquina no tiene porque ser completa
 * Alfabeto de input y de output
 * Testear con maquina y sin maquina(solo con test), generar tests(Tener formato interno input-ouput).,
 * Problema del mutante quivalente 
 * Conformance
 * Generar test de longitud determinada
 * Generar test de forma aleatoria con n inputs EN TOTAL, evitar test redundantes
 * 
*/