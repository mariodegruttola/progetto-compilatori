package nodes;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class VarDeclOp implements Visitable {

    private String type;
    private ArrayList<IdInitOp> idInitOps;
    private String nodeType;

    public VarDeclOp(String type, ArrayList<IdInitOp> idInitOps) {
        this.type = type;
        this.idInitOps = idInitOps;
        nodeType = "da definire";
    }

    public String getType() {
        return type;
    }

    public ArrayList<IdInitOp> getIdInitOps() {
        return idInitOps;
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
