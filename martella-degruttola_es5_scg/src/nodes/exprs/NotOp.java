package nodes.exprs;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class NotOp implements Visitable, Expr {

    private Expr expr;
    private String nodeType;

    public NotOp(Expr expr) {
        this.expr = expr;
        nodeType = "da definire";
    }

    public Expr getExpr() {
        return expr;
    }

    @Override
    public String getNodeType() {
        return nodeType;
    }

    @Override
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public Element accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
