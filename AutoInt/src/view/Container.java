package view;

import testThings.TestComparer;
import mealyMachine.MealyMachine;

public class Container {
	public FirstScreen getFs() {
		return fs;
	}

	public void setFs(FirstScreen fs) {
		this.fs = fs;
	}

	public CreateManualAutomatonScreen getCma() {
		return cma;
	}

	public void setCma(CreateManualAutomatonScreen cma) {
		this.cma = cma;
	}

	private MealyMachine mm;
	private FirstScreen fs;
	private CreateManualAutomatonScreen cma;
	private TestScreen ts;
	private TestComparer tc;
	
	public MealyMachine getMm() {
		return mm;
	}

	public void setMm(MealyMachine mm) {
		this.mm = mm;
	}
	public void changeText(){
		this.fs.writeAuto();
	}

	public TestScreen getTs() {
		return ts;
	}

	public void setTs(TestScreen ts) {
		this.ts = ts;
	}

	public TestComparer getTc() {
		return tc;
	}

	public void setTc(TestComparer tc) {
		this.tc = tc;
	}
}
