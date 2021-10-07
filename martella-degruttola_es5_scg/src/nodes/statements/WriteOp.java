package nodes.statements;

import nodes.exprs.Expr;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class WriteOp implements Visitable, Statement {

    private ArrayList<Expr> exprs;
    private String nodeType;

    public WriteOp(ArrayList<Expr> exprs) {
        this.exprs = exprs;
        nodeType = "da definire";
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
