package nodes.statements;

import org.w3c.dom.Element;
import visitors.Visitor;

public interface Statement {

    public Element accept(Visitor visitor);
    public String getNodeType();
    public void setNodeType(String nodeType);
}