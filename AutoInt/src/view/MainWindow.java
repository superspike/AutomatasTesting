package view;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {

	private Container c;
	private Stage st;
	private Scene sc;
	//Dialog d=new Dialog();
	
	public static void main(String args[]){
		launch();
	}
	
	@Override
	public void start(Stage s) throws Exception {
		// TODO Auto-generated method stub
		c=new Container();
		this.st=s;
		c.setFs(new FirstScreen(st,c));
		this.sc=new Scene(c.getFs(), 800,600);
		st.setScene(sc);
		st.setTitle("Main Window");
		st.show();
	}

	/*private void changeGroup(Group g, String tit){
		this.sc=new Scene(g,800, 600);
		this.gr=g;
		this.st.setScene(sc);
		st.setTitle(tit);
	}*/
	
}
