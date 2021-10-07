package nodes;

import nodes.exprs.Identifier;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class ProcOp implements Visitable {

    private Identifier identifier;
    private ArrayList<ParDeclOp> parDeclOps;
    private ArrayList<String> resultTypeList;
    private ProcBodyOp procBodyOp;
    private String nodeType;

    public ProcOp(Identifier identifier, ArrayList<ParDeclOp> parDeclOps, ArrayList<String> resultTypeList, ProcBodyOp procBodyOp) {
        this.identifier = identifier;
        this.parDeclOps = parDeclOps;
        this.resultTypeList = resultTypeList;
        this.procBodyOp = procBodyOp;
        nodeType = "da definire";
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ArrayList<ParDeclOp> getParDeclOps() {
        return parDeclOps;
    }

    public ArrayList<String> getResultTypeList() {
        return resultTypeList;
    }

    public ProcBodyOp getProcBodyOp() {
        return procBodyOp;
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
