package nodes.statements;

import nodes.exprs.Expr;
import nodes.exprs.Identifier;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class CallProcOp implements Visitable, Statement, Expr {

    private Identifier identifier;
    private ArrayList<Expr> exprs;
    private String nodeType;

    public CallProcOp(Identifier identifier, ArrayList<Expr> exprs) {
        this.identifier = identifier;
        this.exprs = exprs;
        nodeType = "da definire";
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public ArrayList<Expr> getExprs() {
        return exprs;
    }

    @Override
    public Element accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
