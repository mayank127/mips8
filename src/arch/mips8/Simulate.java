package arch.mips8;

import arch.mips8.instruction.Instruction;

public class Simulate {
	Stage IF, ID, IS, RF, EX, DF, DS, TC, WB;
	boolean done;
	Globals globals;
	Simulate(Globals globals) {
		this.globals = globals;
		done = false;
		int instrIndex = 0;
		while (!done) {
			if (!WB.free()) {
				WB.execute(8);
			}
			if (!TC.free()) {
				TC.execute(7);
			}
			if (!DS.free()) {
				DS.execute(6);
			}
			if (!DF.free()) {
				DF.execute(5);
			}
			if (!EX.free()) {
				EX.execute(4);
			}
			if (!ID.free()) {
				ID.execute(3);
			}
			if (!IS.free()) {
				IS.execute(2);
			}
			if (!IF.free()) {
				IF.execute(1);
			}
			if ((WB.free()) && (TC.free())) {
				Instruction i = TC.getInstruction();
				WB.addInstruction(i);
			}
			if ((TC.free()) && (DS.free())) {
				Instruction i = DS.getInstruction();
				TC.addInstruction(i);
			}
			if ((DS.free()) && (DF.free())) {
				Instruction i = DF.getInstruction();
				DS.addInstruction(i);
			}
			if ((DF.free()) && (EX.free())) {
				Instruction i = EX.getInstruction();
				DF.addInstruction(i);
			}
			if ((EX.free()) && (ID.free())) {
				Instruction i = ID.getInstruction();
				EX.addInstruction(i);
			}
			if ((ID.free()) && (IS.free())) {
				Instruction i = IS.getInstruction();
				ID.addInstruction(i);
			}
			if (IS.free() && IF.free()) {
				Instruction i = IF.getInstruction();
				IS.addInstruction(i);
			}
			if (instrIndex < globals.instructions.size() && IF.free()) {
				IS.addInstruction(globals.instructions.get(instrIndex));
			}
			if (WB.free()) {
				Instruction i = WB.getInstruction();
				if (i.equals(globals.instructions.get(instrIndex))) {
					done = true;
				}
			}
			instrIndex = (int) globals.getRegister("pc").content;
		}
	}
}