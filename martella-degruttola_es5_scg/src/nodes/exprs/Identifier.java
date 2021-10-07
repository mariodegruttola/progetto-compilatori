package nodes.exprs;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

public class Identifier implements Expr, Visitable {

    private String name;
    private boolean isFunction;
    private String nodeType;

    public Identifier(String name){
        this.name = name;
        isFunction = false;
        nodeType = "da definire";
    }

    public String getName() { return name; }

    public boolean getIsFunction() {
        return isFunction;
    }

    public void setIsFuntion(){
        isFunction = true;
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