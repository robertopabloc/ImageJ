package ij.macro.doFunctions;


import ij.macro.Interpreter;
import ij.macro.Program;

public interface Function {
    public boolean support(int type);

    public void process(Interpreter interp, Program pgm);
}
