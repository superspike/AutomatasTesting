package view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mealyMachine.MealyMachine;
import mealyMachine.Transition;

public class CreateManualAutomatonScreen extends MealyCreatorGroup {

	private Container c;
	private Stage st;
	private Button b1;
	private int numStates;
	private int alphabetSize;
	private GridPane gp;
	private ArrayList<Spinner<Integer>> outputsList;
	private ArrayList<Spinner<Integer>> destiniesList;
	
	
	public CreateManualAutomatonScreen(int n, int m, Stage sta, Container co){
		this.st=sta;
		this.numStates=n;
		this.alphabetSize=m;
		this.c=co;
		b1=new Button("Generate automaton");
		//b2=new Button("Begin test");
		this.gp=new GridPane();
		this.outputsList=new ArrayList<Spinner<Integer>>();
		this.destiniesList=new ArrayList<Spinner<Integer>>();
		for(int i=0;i<numStates;i++){
			for(int j=0;j<alphabetSize;j++){
				this.gp.add(new Text("From state "+i),0,i*alphabetSize+j);//estado origen
				this.gp.add(new Text("To state"),1,i*alphabetSize+j);//estadodestino
				this.destiniesList.add(new Spinner<Integer>(new SpinnerValueFactory.//se añade estado destino a lista spinners
						IntegerSpinnerValueFactory(1, this.numStates, 1)));//se añade estado destino a grid
				this.gp.add(destiniesList.get(destiniesList.size()-1),2,i*alphabetSize+j);
				this.gp.add(new Text("Input "+j),3,i*alphabetSize+j);//input
				this.gp.add(new Text("Output "),4,i*alphabetSize+j);//ouput
				this.outputsList.add(new Spinner<Integer>(new SpinnerValueFactory.//se añade estado destino a lista spinners
						IntegerSpinnerValueFactory(1, this.alphabetSize, 1)));//se añade estado destino a grid
				this.gp.add(this.outputsList.get(outputsList.size()-1),5,i*alphabetSize+j);//se añade a grid
			}
		}
		if(this.outputsList.size()!=this.destiniesList.size())System.out.println("Error");
		b1.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                	//MIRAR PORQUE TIENEN QUE SER DIFERENTES TAMAÑOS DE ALFABETOS
	                	//TENGO PUESTO ESE DOS VECES PARA QUE NO DE ERROR Y COMPILE
	                		MealyMachine mod=new MealyMachine(numStates,alphabetSize, alphabetSize, false);
	                		for(int i=0;i<numStates;i++){
	                			for(int j=0;j<alphabetSize;j++){
	                				mod.addTransition(new Transition(
	                						i,destiniesList.get(i*alphabetSize+j).getValue(),
	                						j, outputsList.get(i*alphabetSize+j).getValue()));
	                			}
	                		}
	                		c.setMm(mod);
	                		c.setFs(new FirstScreen(sta,c));
	                		Scene sc=new Scene(c.getFs(),800,600);
	                		st.setScene(sc);
	                		c.changeText();
	                    }
	            });
		gp.add(b1, 7, 0);
		gp.setVgap(10);
		gp.setHgap(10);
		this.getChildren().add(gp);
	}
	
	public MealyMachine getMealyMachine(){
		return c.getMm();
	}
}
