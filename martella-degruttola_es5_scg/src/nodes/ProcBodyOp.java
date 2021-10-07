package nodes;

import nodes.exprs.Expr;
import nodes.statements.Statement;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class ProcBodyOp implements Visitable {

    private ArrayList<VarDeclOp> varDeclOps;
    private ArrayList<Statement> statements;
    private ArrayList<Expr> exprs;
    private String nodeType;

    public ProcBodyOp(ArrayList<VarDeclOp> varDeclOps, ArrayList<Statement> statements, ArrayList<Expr> exprs) {
        this.varDeclOps = varDeclOps;
        this.statements = statements;
        this.exprs = exprs;
        nodeType = "da definire";
    }

    public ArrayList<VarDeclOp> getVarDeclOps() {
        return varDeclOps;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public ArrayList<Expr> getExprs() {
        return exprs;
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
