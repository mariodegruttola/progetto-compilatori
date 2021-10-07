package nodes;

import nodes.exprs.Identifier;
import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class ParDeclOp implements Visitable {

    private String type;
    private ArrayList<Identifier> identifiers;
    private String nodeType;

    public ParDeclOp(String type, ArrayList<Identifier> identifiers) {
        this.type = type;
        this.identifiers = identifiers;
        nodeType = "da definire";
    }

    public String getType() {
        return type;
    }

    public ArrayList<Identifier> getIdentifiers() {
        return identifiers;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public Element accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
