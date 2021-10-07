package visitors;// utilizzo element e non object per non fare il casting ogni volta
import org.w3c.dom.Element;

public interface Visitable {

    public Element accept(Visitor visitor);

}
