package nodes.statements;

import org.w3c.dom.Element;
import visitors.Visitable;
import visitors.Visitor;

import java.util.ArrayList;

public class BodyOp implements Visitable {

    private ArrayList<Statement> statements;
    private String nodeType;

    public BodyOp(ArrayList<Statement> statements){
        this.statements = statements;
        nodeType = "da definite";
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    public void addToArray(Statement statement){
        statements.add(statement);
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
