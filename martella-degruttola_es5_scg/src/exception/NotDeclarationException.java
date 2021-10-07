package exception;

public class NotDeclarationException extends Exception {

    private static final long serialVersionUID = 1L;

    public NotDeclarationException(String whatIs, String nameId) {
        super(whatIs + nameId + "' non dichiarata!");
    }
}