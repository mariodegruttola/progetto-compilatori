package nodes.statements;

import nodes.exprs.Expr;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class ElifOp implements Visitable, Statement {

    private Expr expr;
    private BodyOp bodyOp;
    private String nodeType;

    public ElifOp(Expr expr, BodyOp bodyOp) {
        this.expr = expr;
        this.bodyOp = bodyOp;
        nodeType = "da definire";
    }

    public Expr getExpr() {
        return expr;
    }

    public BodyOp getBodyOp() {
        return bodyOp;
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
