package arch.mips8.instruction.branch;

import arch.mips8.Globals;
import arch.mips8.Register;

public class BneInstruction extends BranchInstruction {
	long prev_pc = 0;

	public BneInstruction(Register r1, Register r2, long immd) {
		super(r1, r2, immd);
		super.name = "bne";
	}

	public BneInstruction(BneInstruction bneInstruction) {
		super(bneInstruction);
		super.name = "bne";
	}

	@Override
	public boolean executeEX() {
		Register reg = Globals.getRegister("pc");
		if ((int) super.r1Val != (int) super.r2Val) {
			reg.setContent(prev_pc + (int) super.immd);
			super.branchTaken = true;
		}
		return true;
	}

	@Override
	public boolean executeIF() {
		Register reg = Globals.getRegister("pc");
		long current_pc = reg.getContent();
		prev_pc = current_pc;
		reg.setContent(current_pc + 1);
		r1.lockRegister(id);
		return true;
	}

	@Override
	public BneInstruction copy() {
		return new BneInstruction(this);
	}

}
