package visitors;

import java.util.ArrayList;
import java.util.Stack;

import exception.*;
import nodes.exprs.*;
import nodes.statements.*;
import org.w3c.dom.Element;

import nodes.*;
import util.SymbolTable;

public class SemanticVisitor implements Visitor {

    private Stack<SymbolTable> stack;
    private String lastFunction;

    public SemanticVisitor() {
        stack = new Stack<>();
        lastFunction = "da definire";
    }

    @Override
    public Element visit(Visitable visitable) {

        String className = visitable.getClass().getName();
        //System.out.println("class name " + className);

        switch (className) {

            case "nodes.IdInitOp":
                try {
                    semantic_IdInitOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.ParDeclOp":
                try {
                    semantic_ParDeclOp(visitable);
                } catch (MultipleDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.ProcBodyOp":
                semantic_ProcBodyOp(visitable);
                break;

            case "nodes.ProcOp":
                try {
                    semantic_ProcOp(visitable);
                } catch (MultipleDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (NotDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.ProgramOp":
                try {
                    semantic_ProgramOp(visitable);
                } catch (NotDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.VarDeclOp":
                try {
                    semantic_VarDeclOp(visitable);
                } catch (MultipleDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

                //statements
            case "nodes.statements.AssignOp":
                try {
                    semantic_AssignOp(visitable);
                } catch (NotDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.BodyOp":
                semantic_BodyOp(visitable);
                break;

            case "nodes.statements.CallProcOp":
                try {
                    semantic_CallProcOp(visitable);
                } catch (NotDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.ElifOp":
                try {
                    semantic_ElifOp(visitable);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                }
                break;

            case "nodes.statements.IfOp":
                try {
                    semantic_IfOp(visitable);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.ReadOp":
                semantic_ReadOp(visitable);
                break;

            case "nodes.statements.WhileOp":
                try {
                    semantic_WhileOp(visitable);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.WriteOp":
                semantic_WriteOp(visitable);
                break;

                // expr
            case "nodes.exprs.AddOp":
                try {
                    semantic_AddOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.AndOp":
                try {
                    semantic_AndOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.BooleanConst":
                semantic_BooleanConst(visitable);
                break;

            case "nodes.exprs.DiffOp":
                try {
                    semantic_DiffOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.DivOp":
                try {
                    semantic_DivOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.EQOp":
                try {
                    semantic_EQOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.FloatConst":
                semantic_FloatConst(visitable);
                break;

            case "nodes.exprs.GEOp":
                try {
                    semantic_GEOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.GTOp":
                try {
                    semantic_GTOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.Identifier":
                try {
                    semantic_Identifier(visitable);
                } catch (NotDeclarationException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.IntConst":
                semantic_IntConst(visitable);
                break;

            case "nodes.exprs.LEOp":
                try {
                    semantic_LEOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.LTOp":
                try {
                    semantic_LTOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.MulOp":
                try {
                    semantic_MulOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.NEOp":
                try {
                    semantic_NEOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.NotOp":
                try {
                    semantic_NotOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.NullConst":
                semantic_NullConst(visitable);
                break;

            case "nodes.exprs.OrOp":
                try {
                    semantic_OrOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.StringConst":
                semantic_StringConst(visitable);
                break;

            case "nodes.exprs.UminusOp":
                try {
                    semantic_UminusOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                }
                break;

            default:
                try {
                    throw new FatalError("La classe '" + className + "' non è stata trovata all'interno del SemanticVisitor");
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                }
                break;
        }

        return null;
    }

    private void semantic_IdInitOp(Visitable visitable) throws TypeMismatchException {
        IdInitOp idInitOp = (IdInitOp) visitable;

        // non eseguiamo l'accept dell'identificatore in quanto questo è stato marcato col suo tipo già dal VarDeclOp che è
        // colui che chiama questo metodo

        Expr expr = idInitOp.getExpr();
        if(expr != null){
            expr.accept(this);
            TypeChecker.typeCheckBinaryOp(TypeChecker.AssignOp, idInitOp.getIdentifier().getNodeType(), expr.getNodeType());
        }
        idInitOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_ParDeclOp(Visitable visitable) throws MultipleDeclarationException {
        ParDeclOp parDeclOp = (ParDeclOp) visitable;

        for(Identifier identifier : parDeclOp.getIdentifiers()){
            identifier.setNodeType(parDeclOp.getType());
            stack.peek().insertId(identifier);

            //non effettuiamo l'accept dell'id  perchè non stiamo utilizzando la variabile, ma solo inserendola nella symbolTable
        }
        parDeclOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_ProcBodyOp(Visitable visitable) {
        ProcBodyOp procBodyOp = (ProcBodyOp) visitable;

        for(VarDeclOp varDeclOp : procBodyOp.getVarDeclOps()){
            varDeclOp.accept(this);
        }

        for(Statement statement : procBodyOp.getStatements()){
            statement.accept(this);
        }

        for(Expr expr : procBodyOp.getExprs()){
            expr.accept(this);
        }
        procBodyOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_ProcOp(Visitable visitable) throws MultipleDeclarationException, NotDeclarationException, FatalError, TypeMismatchException {
        // In questo nodo bisogna vedere se i tipi dei valori di ritorno siano uguali a quelli dichiarati nella firma
        // Lo facciamo qui, invece che in ProcBodyOp, perché è qui che abbiamo la firma della funzione e i tipi che le
        // variabili di ritorno devono avere

        ProcOp procOp = (ProcOp) visitable;

        IdentifierFunction identifierFunction = new IdentifierFunction(procOp.getIdentifier().getName(), procOp.getParDeclOps(), procOp.getResultTypeList());
        stack.peek().insertId(identifierFunction);
        stack.push(new SymbolTable(identifierFunction.getName()));

        for (ParDeclOp parDeclOp : identifierFunction.getParDeclOps()) {
            parDeclOp.accept(this);
        }

        procOp.getProcBodyOp().accept(this);

        ArrayList<String> returnFirmTypes = identifierFunction.getResultTypes();
        ArrayList<String> returnOutputType = new ArrayList<>();

        // L'accept delle expr non viene effettuato perchè viene fatto quando facciamo l'accept del procBodyOp (3 istruzioni sopra)
        for(Expr expr : procOp.getProcBodyOp().getExprs()){
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
                //Verifichiamo che la funzione sia stata dichiarate precedentemente, e quindi presente nello scoop
                Identifier identifier = ((CallProcOp) expr).getIdentifier();
                identifier.setIsFuntion();
                IdentifierFunction idFunction = (IdentifierFunction) findInTypeEnvironment(identifier);
                returnOutputType.addAll(idFunction.getResultTypes());
            } else {
                returnOutputType.add(expr.getNodeType());
            }
        }

        TypeChecker.typeCheckAssignOp(returnFirmTypes, returnOutputType);

        lastFunction = procOp.getIdentifier().getName();
        procOp.setNodeType(TypeChecker.VOID);
        stack.pop();
    }

    private ProgramOp semantic_ProgramOp(Visitable visitable) throws NotDeclarationException, FatalError {

        ProgramOp programOp = (ProgramOp) visitable;
        stack.push(new SymbolTable("GLOBAL"));

        for (VarDeclOp varDeclOp : programOp.getVarDeclOps()) {
            varDeclOp.accept(this);
        }

        for (ProcOp procOp : programOp.getProcOps()) {
            procOp.accept(this);
        }

        if(stack.peek().containsKey("main()")){
            if(!lastFunction.equals("main")){
                throw new FatalError("Funzione main non presente nel programma");
            }
        } else {
            throw new NotDeclarationException("Funzione", " main()");
        }

        IdentifierFunction identifierFunction = (IdentifierFunction) stack.peek().get("main()");
        ArrayList<String> resultType = identifierFunction.getResultTypes();
        if((resultType.size() > 0)){
            throw new FatalError("Il main deve restituire unicamente void");
        }

        if(identifierFunction.getParDeclOps().size() != 0){
            throw new FatalError("Il main non deve prendere in input alcun paramentro");
        }

        stack.pop();
        programOp.setNodeType(TypeChecker.VOID);
        return programOp;
    }

    private void semantic_VarDeclOp(Visitable visitable) throws MultipleDeclarationException {

        VarDeclOp varDeclOp = (VarDeclOp) visitable;

        for(IdInitOp idInitOp : varDeclOp.getIdInitOps()){
            idInitOp.getIdentifier().setNodeType(varDeclOp.getType());
            idInitOp.accept(this);
            stack.peek().insertId(idInitOp.getIdentifier());
        }
        varDeclOp.setNodeType(TypeChecker.VOID);
    }

    // STATEMENTS
    private void semantic_AssignOp(Visitable visitable) throws NotDeclarationException, FatalError, TypeMismatchException {
        // Per il checking dell'assegnazione prepariamo due liste, con i tipi degli id e delle espressioni da assegnare a
        // questi id, passandole poi al typechecker
        AssignOp assignOp = (AssignOp) visitable;
        ArrayList<String> identifiersType = new ArrayList<>();
        ArrayList<String> exprsType = new ArrayList<>();

        for(Identifier identifier : assignOp.getIdentifiers()){
            identifier.accept(this);
            identifiersType.add(identifier.getNodeType());
        }

        // Controlliamo il typo delle espressioni e inseriamole nella lista da passare al TypeChecker
        // Se l'espressione è una funzione (CallProcOp) bisogna vedere quanti valori di ritorno questa restituisce;
        // dato che le funzioni devono essere dichiarate prima di essere usate siamo sicuri che la firma della funzione
        // è gia stata inserita nella SymbolTable (ed in particolare in quella globale). Quindi andiamo a prendere la
        // firma (l'IdentifierFunction) nella symbolTable e aggiungiamo alla lista delle espressioni da valutare
        // per la parte destra dell'assegnamento (l'AssignOp) i tipi dei valori di ritorno di questa funzione.
        for(Expr expr : assignOp.getExprs()){
            expr.accept(this);
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
                //Per prendere i tipi di ritorno devo andare comunque a prendere l'IdentifierFunction nella SymbolTable
                Identifier identifier = ((CallProcOp) expr).getIdentifier();
                identifier.setIsFuntion();
                IdentifierFunction identifierFunction = (IdentifierFunction) findInTypeEnvironment(identifier);
                if(identifierFunction.getResultTypes().size() == 0){
                    throw new FatalError("Non è possibile assegnare una funzione void");
                }
                exprsType.addAll(identifierFunction.getResultTypes());
            } else {
                exprsType.add(expr.getNodeType());
            }
        }
        TypeChecker.typeCheckAssignOp(identifiersType, exprsType);
        assignOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_BodyOp(Visitable visitable){
        BodyOp bodyOp = (BodyOp) visitable;
        for(Statement statement : bodyOp.getStatements()){
            statement.accept(this);
        }
        bodyOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_CallProcOp(Visitable visitable) throws NotDeclarationException, FatalError, TypeMismatchException {
        CallProcOp callProcOp = (CallProcOp) visitable;

        // Quando effettuiamo una chiamata a funzione dobbiamo verificare che il tipo degli argomenti passati a questa
        // corrispondano a quelli dei paramentri nella sua firma

        // Tipo dei parametri nella FIRMA della funzione chiamata
        ArrayList<String> paramsDefFuncType = new ArrayList<>();
        // Tipo degli argomenti che diamo in input alla funzione
        ArrayList<String> paramsInputType = new ArrayList<>();

        Identifier identifier = callProcOp.getIdentifier();
        identifier.setIsFuntion();
        IdentifierFunction identifierFunction = (IdentifierFunction) findInTypeEnvironment(identifier);

        // Prepariamo l'array che contiene i tipi dei parametri
        for(ParDeclOp parDeclOp : identifierFunction.getParDeclOps()){
            for(Identifier identifier1 : parDeclOp.getIdentifiers()){
                paramsDefFuncType.add(identifier1.getNodeType());
            }
        }

        // Bisogna stare attenti in quanto alla funzione possiamo passare i valori di ritorno di una funzione
        // passando direttamente la funzione nella chiamata e che questa funzione passata può ritornare più valori
        // Tutti i controlli sono identici alla preparazione dell'array dei tipi della parte destra (le espressioni) di un
        // assegnamento (vedi in AssignOp)
        for(Expr expr : callProcOp.getExprs()){
            expr.accept(this);
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
                Identifier id = ((CallProcOp) expr).getIdentifier();
                id.setIsFuntion();
                IdentifierFunction idFunction = (IdentifierFunction) findInTypeEnvironment(id);
                if(idFunction.getResultTypes().size() == 0) {
                    throw new FatalError("Non è possibile passare ad una funzione un'altra funzione void");
                }
                paramsInputType.addAll(idFunction.getResultTypes());
            } else {
                paramsInputType.add(expr.getNodeType());
            }
        }
        TypeChecker.typeCheckAssignOp(paramsDefFuncType, paramsInputType);

        // Se la funzione ritorna un solo valore, allora il tipo da settare al nodo sarà uguale a quello dell'unico
        // valore restituito, altrimenti sarà functionMultiReturn
        if(identifierFunction.getResultTypes().size() == 1){
            callProcOp.setNodeType(identifierFunction.getResultTypes().get(0));
        } else {
            callProcOp.setNodeType(TypeChecker.FUNCTION);
        }
    }

    private void semantic_ElifOp(Visitable visitable) throws FatalError, TypeMismatchException {
        ElifOp elifOp = (ElifOp) visitable;
        Expr expr = elifOp.getExpr();
        expr.accept(this);
        TypeChecker.typeCheckUnaryOp(TypeChecker.Conditional, expr.getNodeType());

        BodyOp bodyOp = elifOp.getBodyOp();
        bodyOp.accept(this);
        elifOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_IfOp(Visitable visitable) throws FatalError, TypeMismatchException {
        IfOp ifOp = (IfOp) visitable;
        Expr expr = ifOp.getExpr();
        expr.accept(this);
        TypeChecker.typeCheckUnaryOp(TypeChecker.Conditional, expr.getNodeType());

        BodyOp bodyOp = ifOp.getBodyOp();
        bodyOp.accept(this);
        for(ElifOp elifOp : ifOp.getElifOps()){
            elifOp.accept(this);
        }
        BodyOp bodyOpElse = ifOp.getBodyOpElse();
        if(bodyOpElse != null){
            bodyOpElse.accept(this);
        }
        ifOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_ReadOp(Visitable visitable) {
        ReadOp readOp = (ReadOp) visitable;

        for(Identifier identifier : readOp.getIdentifiers()){
            identifier.accept(this);
        }
        readOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_WhileOp(Visitable visitable) throws FatalError, TypeMismatchException {
        WhileOp whileOp = (WhileOp) visitable;

        BodyOp bodyOp1 = whileOp.getBodyOp1();
        if(bodyOp1 != null){
            bodyOp1.accept(this);
        }

        Expr expr = whileOp.getExpr();
        expr.accept(this);
        TypeChecker.typeCheckUnaryOp(TypeChecker.Conditional, expr.getNodeType());

        BodyOp bodyOp2 = whileOp.getBodyOp2();
        bodyOp2.accept(this);

        whileOp.setNodeType(TypeChecker.VOID);
    }

    private void semantic_WriteOp(Visitable visitable) {
        WriteOp writeOp = (WriteOp) visitable;

        for (Expr expr : writeOp.getExprs()) {
            expr.accept(this);
        }
        writeOp.setNodeType(TypeChecker.VOID);
    }

    // EXPRS
    private void semantic_AddOp(Visitable visitable) throws TypeMismatchException {
        AddOp expr = (AddOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.AddOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_AndOp(Visitable visitable) throws TypeMismatchException {
        AndOp expr = (AndOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.AndOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_BooleanConst(Visitable visitable) {
        BooleanConst booleanConst = (BooleanConst) visitable;
        booleanConst.setNodeType(TypeChecker.BOOL);
    }

    private void semantic_DiffOp(Visitable visitable) throws TypeMismatchException {
        DiffOp expr = (DiffOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);
        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.DiffOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_DivOp(Visitable visitable) throws TypeMismatchException {
        DivOp expr = (DivOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);
        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.DivOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_EQOp(Visitable visitable) throws TypeMismatchException {
        EQOp expr = (EQOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_FloatConst(Visitable visitable) {
        FloatConst doubleConst = (FloatConst) visitable;
        doubleConst.setNodeType(TypeChecker.FLOAT);
    }

    private void semantic_GEOp(Visitable visitable) throws TypeMismatchException {
        GEOp expr = (GEOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_GTOp(Visitable visitable) throws TypeMismatchException {
        GTOp expr = (GTOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_Identifier(Visitable visitable) throws NotDeclarationException {
        Identifier identifier = (Identifier) visitable;
        // Verifica che la variabile sia dichiarata nello scoop
        Identifier idInScoop = findInTypeEnvironment(identifier);
        identifier.setNodeType(idInScoop.getNodeType());
    }

    private void semantic_IntConst(Visitable visitable) {
        IntConst intConst = (IntConst) visitable;
        intConst.setNodeType(TypeChecker.INT);
    }

    private void semantic_LEOp(Visitable visitable) throws TypeMismatchException {
        LEOp expr = (LEOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_LTOp(Visitable visitable) throws TypeMismatchException {
        LTOp expr = (LTOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_MulOp(Visitable visitable) throws TypeMismatchException {
        MulOp expr = (MulOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);
        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.MulOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());

        expr.setNodeType(resultType);
    }

    private void semantic_NEOp(Visitable visitable) throws TypeMismatchException {
        NEOp expr = (NEOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);
        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());
        expr.setNodeType(resultType);
    }

    private void semantic_NotOp(Visitable visitable) throws FatalError, TypeMismatchException {
        NotOp expr = (NotOp) visitable;
        String resultType = TypeChecker.typeCheckUnaryOp(TypeChecker.NotOp, expr.getNodeType());
        expr.setNodeType(resultType);
    }

    private void semantic_NullConst(Visitable visitable) {
        NullConst expr = (NullConst) visitable;
        expr.setNodeType(TypeChecker.NULL);
    }

    private void semantic_OrOp(Visitable visitable) throws TypeMismatchException {
        OrOp expr = (OrOp) visitable;
        expr.getExpr1().accept(this);
        expr.getExpr2().accept(this);
        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.OrOp, expr.getExpr1().getNodeType(),
                expr.getExpr2().getNodeType());
        expr.setNodeType(resultType);
    }

    private void semantic_StringConst(Visitable visitable){
        StringConst stringConst = (StringConst) visitable;
        stringConst.setNodeType(TypeChecker.STRING);
    }

    private void semantic_UminusOp(Visitable visitable) throws FatalError, TypeMismatchException {
        UminusOp expr = (UminusOp) visitable;
        String resultType = TypeChecker.typeCheckUnaryOp(TypeChecker.UminusOp, expr.getNodeType());
        expr.setNodeType(resultType);
    }



    private Identifier findInTypeEnvironment(Identifier identifier) throws NotDeclarationException {
        String nameId = identifier.getName();
        String whatIs = "Variabile '";
        if (identifier.getIsFunction()){
            nameId += "()";
            whatIs = "Funzione '";
        }

        // Controllo se la variabile/funzione è nell'ultima symbolTable inserita nello stack e se falso se è presente
        // nella symbolTable globale, che ovviamente è la prima inserita nello stack e quindi si trova in posizione zero.
        // Andiamo a vedere direttamente nella globale perchè il nostro scoop avrà sempre al massimo due livelli (quello
        // della funzione in cui mi trovo e quello globale)
        if(!stack.peek().containsKey(nameId)){
            if(!stack.get(0).containsKey(nameId)){
                throw new NotDeclarationException(whatIs, nameId);
            } else {
                return stack.get(0).get(nameId);
            }
        } else {
            return stack.peek().get(nameId);
        }
    }
}