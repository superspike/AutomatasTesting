package inp;
import java.util.Scanner;

import mealyMachine.Transition;

public class In {
	private static Scanner input;
	private In(){
		input=new Scanner(System.in);
	}

	public Integer readIn(){
		return In.input.nextInt();
	}
	public String readStr(){
		return In.input.next();
	}
	public static Transition readTrans(){
		Integer or, dest, in, out;
		or=In.input.nextInt();
		dest=In.input.nextInt();
		in=In.input.nextInt();
		out=In.input.nextInt();
		return new Transition(or, dest, in, out);
	}
	
	
}
