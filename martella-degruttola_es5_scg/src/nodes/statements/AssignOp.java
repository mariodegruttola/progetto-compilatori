package nodes.statements;

import nodes.exprs.Expr;
import nodes.exprs.Identifier;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class AssignOp implements Visitable, Statement {

    private ArrayList<Identifier> identifiers;
    private ArrayList<Expr> exprs;
    private String nodeType;


    public AssignOp(ArrayList<Identifier> identifiers, ArrayList<Expr> exprs) {

        this.identifiers = identifiers;
        this.exprs = exprs;
        nodeType = "da definire";
    }

    public ArrayList<Identifier> getIdentifiers() {
        return identifiers;
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
