package nodes;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class ProgramOp implements Visitable {

    private ArrayList<VarDeclOp> varDeclOps;
    private ArrayList<ProcOp> procOps;
    private String nodeType;

    public ProgramOp(ArrayList<VarDeclOp> varDeclOps, ArrayList<ProcOp> procOps){
        this.varDeclOps = varDeclOps;
        this.procOps = procOps;
        nodeType = "da definire";
    }

    public ArrayList<VarDeclOp> getVarDeclOps() {
        return varDeclOps;
    }

    public ArrayList<ProcOp> getProcOps() {
        return procOps;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public Element accept(Visitor visitor) {
        return visitor.visit(this);
    }
}