package ij.macro.doFunctions;

import ij.IJ;
import ij.macro.Interpreter;
import ij.macro.Program;
import ij.measure.ResultsTable;

import static ij.macro.MacroConstants.RUN;

public class SelectFunction implements Function{
    @Override
    public boolean support(int type) {
        return RUN == type;
    }

    @Override
    public void process(Interpreter interp, Program pgm) {
        selectWindow(interp, pgm);
    }

    private void selectWindow(Interpreter interp, Program pgm) {
        String title = getStringArg(interp);
        if ( "Results".equals(title)) {
            ResultsTable rt = ResultsTable.getResultsTable();
            if (rt!=null && rt.size()>0)
                rt.show("Results");
        }
        IJ.selectWindow(title);
    }

    String getStringArg(Interpreter interp) {
        interp.getLeftParen();
        String arg = getString(interp);
        interp.getRightParen();
        return arg;
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
