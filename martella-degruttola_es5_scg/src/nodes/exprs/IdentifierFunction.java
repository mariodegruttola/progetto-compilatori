package nodes.exprs;

import nodes.ParDeclOp;

import java.util.ArrayList;

public class IdentifierFunction extends Identifier {

    private ArrayList<ParDeclOp> parDeclOps;
    private ArrayList<String> resultTypes;

    public IdentifierFunction(String name, ArrayList<ParDeclOp> parDeclOps, ArrayList<String> resultTypes) {
        super(name);
        setIsFuntion();
        this.parDeclOps = parDeclOps;
        this.resultTypes = resultTypes;
    }

    public ArrayList<ParDeclOp> getParDeclOps() {
        return parDeclOps;
    }

    public ArrayList<String> getResultTypes() {
        return resultTypes;
    }
}
