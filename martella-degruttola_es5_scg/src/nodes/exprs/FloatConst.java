package nodes.exprs;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class FloatConst implements Visitable, Expr {

    private String value;
    private String nodeType;

    public FloatConst(String value) {
        this.value = value;
        nodeType = "da definire";
    }

    public String getValue() {
        return value;
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
