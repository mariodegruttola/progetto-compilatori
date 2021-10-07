package nodes.statements;

import nodes.exprs.Expr;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class IfOp implements Visitable, Statement {

    private Expr expr;
    private BodyOp bodyOp;
    private ArrayList<ElifOp> elifOps;
    private BodyOp bodyOpElse;
    private String nodeType;

    public IfOp(Expr expr, BodyOp bodyOp, ArrayList<ElifOp> elifOps, BodyOp bodyOpElse) {
        this.expr = expr;
        this.bodyOp = bodyOp;
        this.elifOps = elifOps;
        this.bodyOpElse = bodyOpElse;
        nodeType = "da definire";
    }

    public Expr getExpr() {
        return expr;
    }

    public BodyOp getBodyOp() {
        return bodyOp;
    }

    public ArrayList<ElifOp> getElifOps() {
        return elifOps;
    }

    public BodyOp getBodyOpElse() {
        return bodyOpElse;
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
