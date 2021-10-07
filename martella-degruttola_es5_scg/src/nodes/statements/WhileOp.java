package nodes.statements;

import nodes.exprs.Expr;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class WhileOp implements Visitable, Statement {

    private BodyOp bodyOp1;
    private Expr expr;
    private BodyOp bodyOp2;
    private String nodeType;

    public WhileOp(BodyOp bodyOp1, Expr expr, BodyOp bodyOp2) {
        this.bodyOp1 = bodyOp1;
        this.expr = expr;
        this.bodyOp2 = bodyOp2;
        nodeType = "da definire";
    }

    public BodyOp getBodyOp1() {
        return bodyOp1;
    }

    public Expr getExpr() {
        return expr;
    }

    public BodyOp getBodyOp2() {
        return bodyOp2;
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
