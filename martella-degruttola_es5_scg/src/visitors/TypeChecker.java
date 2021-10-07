package visitors;

import exception.FatalError;
import exception.TypeMismatchException;

import java.util.ArrayList;

public class TypeChecker {

    //tipi con cui definiamo i nodi
    public static final String INT = "int";
    public static final String FLOAT = "float";
    public static final String STRING = "string";
    public static final String BOOL = "bool";
    public static final String VOID = "void";
    public static final String FUNCTION = "functionMultiReturn";
    public static final String NULL = "null";

    //Operazioni da controllare con il checker
    public static final String AddOp= "AddOp";
    public static final String MulOp = "MulOp" ;
    public static final String DiffOp = "DiffOp";
    public static final String DivOp = "DivOp";
    public static final String RelOp = "RelOp";
    public static final String AssignOp = "AssignOp";
    public static final String UminusOp = "UminusOp";
    public static final String NotOp = "NotOp";
    public static final String OrOp = "OrOp";
    public static final String AndOp = "AndOp";
    public static final String Conditional = "Conditional";


    public static String typeCheckUnaryOp(String op, String type) throws TypeMismatchException, FatalError {
        if (op.equals(UminusOp)) {
            return typeCheckUminusOp(type);

        } else if (op.equals(NotOp)) {
            return typeCheckNotOp(type);

        } else if (op.equals(Conditional)) {
            return typeCheckConditionalOp(type);
        } else {
            throw new FatalError("Operazione " + op + " non verificabile nel checker unario");
        }
    }

    public static String typeCheckBinaryOp(String op, String type1, String type2) throws TypeMismatchException {
        if (op.equals(AddOp) || op.equals(MulOp) || op.equals(DiffOp) || op.equals(DivOp)) {
            return typeCheckAritmeticOp(type1, type2);

        } else if (op.equals(RelOp)) {
            return typeCheckRelationalOp(type1, type2);

        } else if (op.equals(AssignOp)) {
            return typeCheckAssignOp(type1, type2);

        } else if (op.equals(OrOp) || op.equals(AndOp)) {
            return typeCheckOrAndOp(type1, type2);

        } else {
            throw new TypeMismatchException("Operazione " + op + " non verificabile nel checker binario");
        }
    }

    public static void typeCheckAssignOp(ArrayList<String> identifiersType, ArrayList<String> exprsType) throws TypeMismatchException, FatalError {
        int index = exprsType.size();
        if (identifiersType.size() == index){
            for(int i = 0; i < index; i+=1){
                typeCheckAssignOp(identifiersType.get(i), exprsType.get(i));
            }
        } else {
            throw new FatalError("Liste dei parametri da controllare non hanno la stessa dimensione: verifica del TypeChecking non possibile");
        }
    }

    private static String typeCheckAritmeticOp(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return INT;
        } else if (type1.equals(INT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return FLOAT;
        } else if (type1.equals(FLOAT) && type2.equals(INT)) {
            return FLOAT;
        }
        throw new TypeMismatchException("Operazioni aritmentiche eseguibili solo tra espressioni di tipo int e float. Valori forniti: " + type1 + " - " + type2);
    }

    private static String typeCheckRelationalOp(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return BOOL;
        } else if (type1.equals(INT) && type2.equals(FLOAT)) {
            return BOOL;
        } else if (type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return BOOL;
        } else if (type1.equals(FLOAT) && type2.equals(INT)) {
            return BOOL;
        } else if (type1.equals(STRING) && type2.equals(STRING)) {
            return BOOL;
        } else if (type1.equals(BOOL) && type2.equals(BOOL)) {
            return BOOL;
        } else {
            throw new TypeMismatchException("Non possiamo relazionare " + type2 + " e " + type1);
        }
    }

    private static String typeCheckOrAndOp(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(BOOL) && type2.equals(BOOL)) {
            return BOOL;
        } else {
            throw new TypeMismatchException("Per l'operazione di and, o or, sono richieste due espressioni di tipo bool. Fornite: " + type1 + " - " + type2);
        }
    }

    private static String typeCheckConditionalOp(String type) throws TypeMismatchException {
        if (type.equals(BOOL)) {
            return VOID;
        } else {
            throw new TypeMismatchException("L'espressione nella condizione di un costrutto if, elif e while deve essere di tipo bool. Fornita: " + type);
        }
    }

    private static String typeCheckAssignOp(String type1, String type2) throws TypeMismatchException {
        if (type1.equals(INT) && type2.equals(INT)) {
            return VOID;
        } else if(type1.equals(FLOAT) && type2.equals(FLOAT)) {
            return VOID;
        } else if(type1.equals(STRING) && type2.equals(STRING)) {
            return VOID;
        } else if(type1.equals(BOOL) && type2.equals(BOOL)) {
            return VOID;
        }
        throw new TypeMismatchException("Non è possibile assegnare " + type2 + " a " + type1);
    }

    private static String typeCheckUminusOp(String type) throws TypeMismatchException {
        if(type.equals(INT)) {
            return INT;
        }else if(type.equals(FLOAT)) {
            return FLOAT;
        } else{
            throw new TypeMismatchException("Per l'operazione 'uminus' è richiesta una espressione di tipo int o float. Fornita: " + type);
        }
    }

    private static String typeCheckNotOp(String type) throws TypeMismatchException {
        if (type.equals(BOOL)) {
            return BOOL;
        } else {
            throw new TypeMismatchException("Per l'operazione 'not' è richiesta una espressione di tipo bool. Fornita: " + type);
        }

    }
}