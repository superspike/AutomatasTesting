package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import testThings.Test;
import testThings.TestComparer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TestScreen extends Group {

	
	private FileChooser fc;
	private TextArea inputs;
	private TextArea outputs;
	private Spinner<Integer> mutants;
	private Spinner<Integer> typeOfMutation;
	private TextArea matrix;
	private GridPane gp;
	private Button b1;
	private Button b2;
	private Text t1;
	private Text t2;
	private Text t3;
	private Text t4;
	private Container c;
	private Stage st;
	
	public TestScreen(Stage sta,Container co){
		this.st=sta;
		this.c=co;
		this.fc=new FileChooser();
		this.t1=new Text("Introduce inputs here");
		this.t2=new Text("Outputs displayed here");
		this.t3=new Text("Number of mutants");
		this.t4=new Text("0-mutate outputs, 1 destinies");
		this.inputs=new TextArea();
		this.outputs=new TextArea();
		this.mutants=new Spinner<Integer>(new SpinnerValueFactory.
				IntegerSpinnerValueFactory(1, 50, 10));
		this.typeOfMutation=new Spinner<Integer>(new SpinnerValueFactory.
				IntegerSpinnerValueFactory(0, 1, 0));
		this.matrix=new TextArea();
		this.b1=new Button("Realizar comparacion");
		this.b2=new Button("Select file");
		this.b1.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) {
	                		TestComparer comp=new TestComparer(c.getMm(),typeOfMutation.getValue(), mutants.getValue());
	                		String[] inpList=inputs.getText().split("\n");
	                		String[] inp;
	                		ArrayList<Integer> caso;
	                		Test te;
	                		for(int i=0;i<inpList.length;i++){
	                			caso=new ArrayList<Integer>();
	                			inp=inpList[i].split(" ");
	                			for(int j=0;j<inp.length;j++){
	                				caso.add(Integer.parseInt(inp[j]));
	                			}
	                			te=new Test(caso,c.getMm());
	                			te.runTest();
	                			//comp.addTestTester(te);
	                		}
	                		c.setTc(comp);
	                		c.getTc().runAll();
	                		StringBuilder sb=new StringBuilder();
	                		for(int i=0;i<c.getTc().size();i++){
	                			sb.append(c.getTc().getOutput(i));
	                			sb.append("\n");
	                		}
	                		outputs.setText(sb.toString());
	                		matrix.setText(c.getTc().getMatrix());
	                }
	            });
		this.b2.setOnAction(
	            new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(final ActionEvent e) { 
	                	String texto="";
	                	File file = fc.showOpenDialog(st);
	                	if (file != null) {
                        //openFile(file);
	                		texto=readFile(file);
	                	}
	                	inputs.setText(texto);
	                }
	            });
		this.gp=new GridPane();
		gp.add(t1, 0, 0);
		gp.add(inputs, 1, 0);
		gp.add(mutants, 0, 1);
		gp.add(t3, 1, 1);
		gp.add(typeOfMutation, 0, 2);
		gp.add(t4, 1, 2);
		gp.add(b1, 0, 3);
		gp.add(t2, 0, 4);
		gp.add(outputs, 1, 4);
		gp.add(new Text("Matrix"), 0, 5);
		gp.add(this.matrix, 1, 5);
		this.getChildren().add(gp);
	}
	
	private String readFile(File file){
		  StringBuilder sb=new StringBuilder();
		  try {
			  BufferedReader br = new BufferedReader(new FileReader(file)); 
			  String str; 
			while ((str = br.readLine()) != null) {
			   sb.append(str); 
			  }
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
}
