package arch.mips8.instruction.twoR;

import arch.mips8.Globals;
import arch.mips8.Register;

public class DivInstruction extends TwoRInstruction {

	private int divCounter = 4;

	public DivInstruction(Register r1, Register r2) {
		super(r1, r2);
		super.name = "div";
	}

	public DivInstruction(DivInstruction divInstruction) {
		super(divInstruction);
		super.name = "div";
	}

	@Override
	public boolean executeEX() {
		super.executeEX();
		loVal = (long) ((int) super.r1Val / (int) super.r2Val);
		hiVal = (long) ((int) super.r1Val % (int) super.r2Val);
		if (divCounter > 1) {
			divCounter--;
			return false;
		} else {
			if (r1.contentAvailable(id) && r2.contentAvailable(id)) {

			} else if (Globals.forwardingEnable && r1.forwardAvailable()
					&& r1.forwardAvailable()) {
				r1.setForwardTo(id, 4);
				r2.setForwardTo(id, 4);
			} else if (Globals.forwardingEnable && r1.forwardAvailable()
					&& r2.contentAvailable(id)) {
				r1.setForwardTo(id, 4);
			} else if (Globals.forwardingEnable && r2.forwardAvailable()
					&& r1.contentAvailable(id)) {
				r1.setForwardTo(id, 4);
			}
			if(Globals.forwardingEnable){
				lo.setForward(loVal, id, 4);
				hi.setForward(hiVal, id, 4);
			}
		}
		return true;
	}

	@Override
	public DivInstruction copy() {
		return new DivInstruction(this);
	}

}
