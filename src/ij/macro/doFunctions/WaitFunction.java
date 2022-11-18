package ij.macro.doFunctions;

import ij.IJ;
import ij.macro.Interpreter;
import ij.macro.Program;

import static ij.macro.MacroConstants.RUN;

public class WaitFunction implements Function{
    @Override
    public boolean support(int type) {
        return RUN == type;
    }

    @Override
    public void process(Interpreter interp, Program pgm) {
        doRun(interp, pgm);
    }

    void doRun(Interpreter interp, Program pgm) {

    }
}
