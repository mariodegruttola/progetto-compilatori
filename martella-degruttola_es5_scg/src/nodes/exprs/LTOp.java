package nodes.exprs;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class LTOp implements Visitable, Expr {

    private Expr expr1;
    private Expr expr2;
    private String nodeType;

    public LTOp(Expr expr1, Expr expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        nodeType = "da definire";
    }

    public Expr getExpr1() {
        return expr1;
    }

    public Expr getExpr2() {
        return expr2;
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
