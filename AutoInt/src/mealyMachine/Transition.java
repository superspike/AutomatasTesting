package mealyMachine;

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

	public static Transition parseTransition(String string) {
		Integer or, dest, in, out;
		String[] words=string.split(" ");
		or=Integer.parseInt(words[0].subSequence(1, words[0].length()-1).toString());
		dest=Integer.parseInt(words[2].subSequence(1, words[0].length()-1).toString());
		String[] inout1=words[3].split("\"");
		in=Integer.parseInt(inout1[1]);
		String[] inout2=words[5].split("\"");
		out=Integer.parseInt(inout2[0]);
		return new Transition(or, dest, in, out);
	}

}
