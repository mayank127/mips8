package arch.mips8.instruction.twoRoneI;

import arch.mips8.Globals;
import arch.mips8.Register;

public class SrlInstruction extends TwoRoneIInstruction {

	public SrlInstruction(Register r1, Register r2, long immd) {
		super(r1, r2, immd);
		super.name = "srl";
	}

	public SrlInstruction(SrlInstruction srlInstruction) {
		super(srlInstruction);
		super.name = "srl";
	}

	@Override
	public boolean executeEX() {
		super.executeEX();
		super.r1Val = (long) ((int) super.r2Val >> (int) super.immd);
		if (r2.contentAvailable(id)) {

		} else if (Globals.forwardingEnable && r2.forwardAvailable()) {
			r2.setForwardTo(id, 4);
		}
		if(Globals.forwardingEnable){
			r1.setForward(r1Val, id, 4);
		}
		return true;
	}

	@Override
	public SrlInstruction copy() {
		return new SrlInstruction(this);
	}

}
