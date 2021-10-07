package visitors;

import org.w3c.dom.Element;

public interface Visitor {

    public Element visit(Visitable v);

}
