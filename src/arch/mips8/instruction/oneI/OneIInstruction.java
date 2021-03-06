package arch.mips8.instruction.oneI;

import arch.mips8.Globals;
import arch.mips8.Register;
import arch.mips8.instruction.Instruction;

public class OneIInstruction implements Instruction {

	int id;
	public long immd;
	String name;
	long current_pc;

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructionName() {
		return this.name + " " + this.immd;
	}

	public OneIInstruction(long immd) {
		this.immd = immd;
	}

	public OneIInstruction(OneIInstruction instruction) {
		this.name = instruction.name;
		this.immd = instruction.immd;
	}

	@Override
	public boolean executeIF() {
		Register reg = Globals.getRegister("pc");
		current_pc = reg.getContent();
		reg.setContent(current_pc + 1);
		return true;
	}

	@Override
	public boolean executeIS() {
		return true;
	}

	@Override
	public boolean executeID() {
		return true;
	}

	@Override
	public boolean executeEX() {
		Register reg = Globals.getRegister("pc");
		long current_pc = reg.getContent();
		reg.setContent(current_pc - 1 + ((int) immd / 4));
		return true;
	}

	@Override
	public boolean executeDF() {
		return true;
	}

	@Override
	public boolean executeDS() {
		return true;
	}

	@Override
	public boolean executeTC() {
		return true;
	}

	@Override
	public boolean executeWB() {
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public OneIInstruction copy() {
		return new OneIInstruction(this);
	}

}