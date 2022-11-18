package ij.macro.doFunctions;

import ij.IJ;
import ij.macro.Interpreter;
import ij.macro.Program;

import static ij.macro.MacroConstants.RUN;

public class RunFunction implements Function{
    @Override
    public boolean support(int type) {
        return RUN == type;
    }

    @Override
    public void process(Interpreter interp, Program pgm) {
        doRun(interp, pgm);
    }

    void doRun(Interpreter interp, Program pgm) {
        interp.getLeftParen();
        String arg1 = getString(interp);
        interp.getToken();
        if (!(interp.token==')' || interp.token==','))
            interp.error("',' or ')'  expected");
        String arg2 = null;
        if (interp.token==',') {
            arg2 = getString(interp);
            interp.getRightParen();
        }
        IJ.run(interp, arg1, arg2);
        IJ.setKeyUp(IJ.ALL_KEYS);
    }

    final String getString(Interpreter interp) {
        String str = interp.getStringTerm();
        while (true) {
            interp.getToken();
            if (interp.token=='+')
                str += interp.getStringTerm();
            else {
                interp.putTokenBack();
                break;
            }
        };
        return str;
    }
}
