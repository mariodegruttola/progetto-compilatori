package nodes;

import nodes.exprs.Expr;
import nodes.exprs.Identifier;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class IdInitOp implements Visitable {

    private Identifier identifier;
    private Expr expr;
    private String nodeType;

    public IdInitOp(Identifier identifier, Expr expr) {
        this.identifier = identifier;
        this.expr = expr;
        nodeType = "da definire";
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Expr getExpr() {
        return expr;
    }

    @Override
    public Element accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
