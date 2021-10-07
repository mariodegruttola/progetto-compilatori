package visitors;

import exception.FatalError;
import exception.TypeMismatchException;
import nodes.*;
import nodes.statements.*;
import nodes.exprs.*;

import org.w3c.dom.Element;
import util.StackForCode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CLangVisitor implements Visitor {

    private FileWriter outputC;
    private HashMap<String, ArrayList<String>> procNumReturns;
    private int tempCount = 0;
    private StackForCode codeStack = new StackForCode();
    private Stack<String> stackElif = new Stack<>();
    private final String returnTempName = "returnTemp";
    private final String varTempName = "varTemp";

    public CLangVisitor(File out) throws Exception {
        outputC = new FileWriter(out);
        procNumReturns = new HashMap<>();
    }

    @Override
    public Element visit(Visitable visitable) {

        String className = visitable.getClass().getName();

        switch (className) {

            case "nodes.IdInitOp":
                try {
                    c_IdInitOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.ParDeclOp":
                try {
                    c_ParDeclOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.ProcBodyOp":
                try {
                    c_ProcBodyOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;
            case "nodes.ProcOp":
                try {
                    c_ProcOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.ProgramOp":
                try {
                    c_ProgramOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.VarDeclOp":
                try {
                    c_VarDeclOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;


            //Statements
            case "nodes.statements.AssignOp":
                try {
                    c_AssignOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.BodyOp":
                c_BodyOp(visitable);
                break;

            case "nodes.statements.CallProcOp":
                try {
                    c_CallProcOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.ElifOp":
                try {
                    c_ElifOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.IfOp":
                try {
                    c_IfOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.ReadOp":
                try {
                    c_ReadOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.WhileOp":
                try {
                    c_WhileOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.statements.WriteOp":
                try {
                    c_WriteOp(visitable);
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            //Exprs
            case "nodes.exprs.AddOp":
                try {
                    c_AddOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.AndOp":
                try {
                    c_AndOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.BooleanConst":
                c_BooleanConst(visitable);
                break;

            case "nodes.exprs.DiffOp":
                try {
                    c_DiffOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.DivOp":
                try {
                    c_DivOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.EQOp":
                try {
                    c_EQOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.FloatConst":
                c_FloatConst(visitable);
                break;

            case "nodes.exprs.GEOp":
                try {
                    c_GEOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;


            case "nodes.exprs.GTOp":
                try {
                    c_GTOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.Identifier":
                c_Identifier(visitable);
                break;

            case "nodes.exprs.IntConst":
                c_IntConst(visitable);
                break;

            case "nodes.exprs.LEOp":
                try {
                    c_LEOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.LTOp":
                try {
                    c_LTOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.MulOp":
                try {
                    c_MulOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.NEOp":
                try {
                    c_NEOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.NotOp":
                try {
                    c_NotOp(visitable);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.NullConst":
                c_NullConst(visitable);
                break;

            case "nodes.exprs.OrOp":
                try {
                    c_OrOp(visitable);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            case "nodes.exprs.StringConst":
                c_StringConst(visitable);
                break;

            case "nodes.exprs.UminusOp":
                try {
                    c_UminusOp(visitable);
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                } catch (TypeMismatchException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;

            default:
                try {
                    throw new FatalError("La classe '" + className + "' non è stata trovata all'interno del CLangVisitor");
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                }
                break;
        }
        return null;
    }

    private void c_IdInitOp(Visitable visitable) throws IOException {
        IdInitOp idInitOp = (IdInitOp) visitable;

        idInitOp.getIdentifier().accept(this);

        Expr expr = idInitOp.getExpr();
        if(expr != null) {
            codeStack.insertInLast(" = ");
            if (expr.getClass().getName().equals("nodes.statements.CallProcOp")) {
                // Nella dichiarazione di una variabile possiamo assegnare a questa il risultato di una funzione, che
                // ovviamente dovrà restituire un solo valore. Dato questa certezza, vediamo che se appunto l'expr è una
                // funzione, aggiungiuamo direttamente il valore della variabile temporanea al codice.
                // int x := fun1(somma(5, 5)); anche con funzioni innestate, sappiamo che il risultato di fun1 verrà
                // inserito nella prossima variabile temporanea generata con numero 'tempCount'.
                codeStack.insertInLast(varTempName + (tempCount));
            }
            expr.accept(this);
        }
    }

    private void c_ParDeclOp(Visitable visitable) throws IOException {
        ParDeclOp parDeclOp = (ParDeclOp) visitable;

        //non creiamo una nuova stringa nello stack perchè stampiamo direttamente quando siamo nella firma della funzione

        int count = 0;
        int size = parDeclOp.getIdentifiers().size();
        for(Identifier identifier : parDeclOp.getIdentifiers()){
            count++;
            if(parDeclOp.getType().equals("string")){
                outputC.write("char* " + identifier.getName());
            } else {
                outputC.write(parDeclOp.getType() + " " + identifier.getName());
            }
            if(count != size){
                outputC.write(", ");
            }
        }
    }

    private void c_ProcBodyOp(Visitable visitable) throws IOException {
        ProcBodyOp procBodyOp = (ProcBodyOp) visitable;

        codeStack.push("");

        for(VarDeclOp varDeclOp : procBodyOp.getVarDeclOps()){
            varDeclOp.accept(this);
        }

        for(Statement statement : procBodyOp.getStatements()){
            statement.accept(this);
        }

        int count = 0;
        for(Expr expr : procBodyOp.getExprs()){
            codeStack.insertInLast("*" + returnTempName + count + " = ");
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
                codeStack.insertInLast(varTempName + tempCount);
            }
            expr.accept(this);
            codeStack.insertInLast(";\n");
            count++;
        }
        outputC.write(codeStack.pop());
    }

    private void c_ProcOp(Visitable visitable) throws IOException {
        ProcOp procOp = (ProcOp) visitable;

        //La firma della funzione la stampiamo direttamente senza utilizzare lo stack, per cui anche in ParDeclOp facciamo così

        outputC.write("");

        Identifier identifier = procOp.getIdentifier();

        if(identifier.getName().equals("main")){
            outputC.write("int main(void) {\n");
        } else {
            outputC.write("void " + identifier.getName() + "(");

            ArrayList<ParDeclOp> parDeclOps = procOp.getParDeclOps();
            for(int i = 0; i < parDeclOps.size(); i++) {
                parDeclOps.get(i).accept(this);
                if(i < (parDeclOps.size() - 1)) {
                    outputC.write(", ");
                }
            }
            if(!procOp.getResultTypeList().isEmpty() && !procOp.getParDeclOps().isEmpty()){
                outputC.write(", ");
            }

            int count = 0;

            ArrayList<String> resultTypes = procOp.getResultTypeList();
            int resultTypesSize = procOp.getResultTypeList().size();
            for(int i = 0; i < resultTypesSize; i++){
                if(resultTypes.get(i).equals("string")) {
                    outputC.write( "char **" + returnTempName + count);

                } else {
                    outputC.write(resultTypes.get(i) + "* " + returnTempName + count);

                }
                if(i < resultTypesSize - 1){
                    outputC.write(", ");
                }
                count++;
            }
            outputC.write(") {\n");
        }

        //Istruzione prima dell'accept perchè nel caso di ricorsione ci serve già sapere i tipi dei return
        procNumReturns.put(identifier.getName(), procOp.getResultTypeList());

        procOp.getProcBodyOp().accept(this);

        if(identifier.getName().equals("main")) {
            outputC.write("return 0;\n}\n");
        } else {
            outputC.write("}\n");
        }
    }

    private void c_ProgramOp(Visitable visitable) throws IOException {
        ProgramOp programOp = (ProgramOp) visitable;

        outputC.write("#include <stdio.h>\n");
        outputC.write("#include <stdlib.h>\n");
        outputC.write("#include <string.h>\n");
        outputC.write("#include <stdbool.h>\n\n");

        for(VarDeclOp varDeclOp : programOp.getVarDeclOps()){
            varDeclOp.accept(this);

        }

        for(ProcOp procOp : programOp.getProcOps()){
            procOp.accept(this);
        }

        outputC.close();
    }

    private void c_VarDeclOp(Visitable visitable) throws IOException {
        VarDeclOp varDeclOp = (VarDeclOp) visitable;

        //Inserimento stringa per il codice di questa varDeclOp
        codeStack.push("");

        if (varDeclOp.getType().equals(TypeChecker.STRING)){
            codeStack.insertInLast("char ");
        }
        else {
            codeStack.insertInLast(varDeclOp.getType() + " ");
        }

        int count = 0;
        int size = varDeclOp.getIdInitOps().size();
        for(IdInitOp idInitOp : varDeclOp.getIdInitOps()){
            count++;
            if (varDeclOp.getType().equals(TypeChecker.STRING)){
                codeStack.insertInLast("*");
            }
            idInitOp.accept(this);
            if(count == size){
                codeStack.insertInLast(";\n");
            } else {
                codeStack.insertInLast(", ");
            }
        }
        outputC.write(codeStack.pop());
    }

    //STATEMENTS
    private void c_AssignOp(Visitable visitable) throws IOException {
        AssignOp assignOp = (AssignOp) visitable;

        ArrayList<Identifier> identifiers = assignOp.getIdentifiers();
        ArrayList<Expr> exprs = assignOp.getExprs();

        int countId = 0;
        for(int i = 0; i < exprs.size(); i++) {
            Expr expr = exprs.get(i);
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){

                //posizione della prima variabile temporanea che
                int posFirstTemReturn = tempCount;
                expr.accept(this);
                CallProcOp callProcOp = (CallProcOp) expr;
                int nReturn = procNumReturns.get(callProcOp.getIdentifier().getName()).size();
                for(int x = countId; x < (countId + nReturn); x++){
                    outputC.write(identifiers.get(x).getName() + " = " + varTempName + (posFirstTemReturn) + ";\n");
                    posFirstTemReturn++;
                }
                countId += (nReturn - 1);

            } else {
                codeStack.push(identifiers.get(countId).getName() + " = " );
                expr.accept(this);
                outputC.write(codeStack.pop() + ";\n");
            }
            countId++;
        }
    }

    private void c_BodyOp(Visitable visitable) {
        BodyOp bodyOp = (BodyOp) visitable;

        for(Statement statement : bodyOp.getStatements()){
            statement.accept(this);
        }
    }

    private void c_CallProcOp(Visitable visitable) throws IOException {
        CallProcOp callProcOp = (CallProcOp) visitable;

        // Per la stampa di una chiamata a funzione, se questa ritorna dei valori, questi vengono messi in variabili
        // temporanee.
        // Es. traduzione in C di somma(a, b) che dà come valore di ritorno un int:
        // int t0;
        // somma(a, b, &t0);

        // Preparazione variabili temporanee passate per riferimento per utilizzarle come variabili per i valori di ritorno
        int startTempCount = tempCount;
        ArrayList<String> resultType = procNumReturns.get(callProcOp.getIdentifier().getName());
        for(String type : resultType){
            if(type.equals("string")){
                outputC.write("char *" + varTempName + tempCount + ";\n");
            } else {
                outputC.write(type + " " + varTempName + tempCount + ";\n");
            }
            tempCount++;
        }


        int nReturn = procNumReturns.get(callProcOp.getIdentifier().getName()).size();

        codeStack.push(callProcOp.getIdentifier().getName() + "(");
        ArrayList<Expr> exprs = callProcOp.getExprs();
        int c = exprs.size();
        for(int i = 0; i < c; i++){
            Expr expr = exprs.get(i);
            int inParamsFromProxProc = tempCount;
            expr.accept(this);
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
                // Se entro qui significa che alla funzione che sto analizzando è stato dato come paramentro in
                // ingresso un'altra funzione. Dovrò prendere in input dunque le variabili temporanee che questa
                // genererà è utilizzarà come "valori di ritorno" e darli in input alla funzione che sto analizzando.
                // Salverò allora il numero di variabili temporanee che ho generato fin'ora (inParamsFromProxProc) e ne
                // darò in input alla funzione corrente tante quante sono quelle generate dalla funzione che passo
                // come argomento (ovvero quanto il numero di valori di ritorno della funzione).
                // Il salvataggio del contatore avviene perchè tempCount è globale e potendo avere chiamate di funzioni
                // a cascata passate come argomenti, queste potrebbero fare aumentare il contatore facendoci perdere in
                // questo contesto il valore del contatore.
                CallProcOp callProcOp1 = (CallProcOp) expr;
                int nReturnCp1 = procNumReturns.get(callProcOp1.getIdentifier().getName()).size();

                for(int ii = inParamsFromProxProc; ii < (inParamsFromProxProc + nReturnCp1); ii++){
                    String code = varTempName + (ii);
                    if(ii < (inParamsFromProxProc + nReturnCp1) - 1) {
                        code += ", ";
                    }
                    codeStack.insertInLast(code);
                }
            }
            if(i < c - 1) {
                codeStack.insertInLast(", ");
            }
        }


        // Nel caso la funzione ritorni dei valori e prende in input degli argomenti allora stampiamo la virgola
        // *Questa virgola "delimita gli argomenti in input da quelli che utiliziamo come valore di ritorno"
        if(nReturn > 0 && !exprs.isEmpty()){
            codeStack.insertInLast(", ");
        }

        // Stampa delle variabili temporanee che fungono da valori di ritorno
        String code = "";
        for(int i = startTempCount; i < (startTempCount + nReturn); i++){
            code += "&" + varTempName + i;
            if(i < (startTempCount + nReturn) - 1){
                code += ", ";
            }
        }
        outputC.write(codeStack.pop() + code + ");\n");

        // Se la funzione ritorna un solo valore, allora il tipo da settare al nodo sarà uguale a quello dell'unico
        // valore restituito, altrimenti sarà functionMultiReturn
        if(resultType.size() == 1){
            callProcOp.setNodeType(resultType.get(0));
        } else {
            callProcOp.setNodeType(TypeChecker.FUNCTION);
        }
    }

    private void c_ElifOp(Visitable visitable) throws IOException {
        ElifOp elifOp = (ElifOp) visitable;

        outputC.write(" else if (" + stackElif.pop() + ") {\n");
        elifOp.getBodyOp().accept(this);
        outputC.write("}\n");
    }

    private void c_IfOp(Visitable visitable) throws IOException {
        IfOp ifOp = (IfOp) visitable;

//      Calcoliamo prima le condizioni dell'elifOp perchè nel caso che in esse ci siano delle funzioni, queste devono
//      essere "preparate" prima dell'if. Una volta calcolate le inseriamo in uno stack per non avere problemi nel
//      caso in cui degli altri if con elifop siano inseriti nel body dell'if che stiamo traducendo.
//  if x > 1 then
//      if x = 2 then
//          write("2");
//      elif x = 3 then     (elif di un if) in un if
//          write("3");
//      fi;
//  elif x < 1 then
//      write("0");
//  fi;
//      Le condizioni le inseriamo al contrario in questo stack per stamparle in ordine quando entriamo nel nodo
//      ElifOp, altrimenti, dato l'utilizzo dello stack, queste verrebbero estratte dall'ultima alla prima.
        ArrayList<ElifOp> elifOps = ifOp.getElifOps();
        for(int i = (elifOps.size() - 1); i >=0; i--){
            ElifOp elifOp = elifOps.get(i);
            codeStack.push("");
            elifOp.getExpr().accept(this);
            stackElif.push(codeStack.pop());
        }

        codeStack.push("");

        Expr expr = ifOp.getExpr();
        if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr.accept(this);

        outputC.write("if (" + codeStack.pop() + ") {\n");

        ifOp.getBodyOp().accept(this);

        outputC.write("}\n");


        for(ElifOp elifOp : ifOp.getElifOps()){
            elifOp.accept(this);
        }

        BodyOp bodyOpElse = ifOp.getBodyOpElse();
        if(bodyOpElse != null) {
            outputC.write("else {\n");
            bodyOpElse.accept(this);
            outputC.write("}\n");

        }
    }

    private void c_ReadOp(Visitable visitable) throws IOException {
        ReadOp readOp = (ReadOp) visitable;

        codeStack.push("");
        String formati = "";

        for(Identifier identifier : readOp.getIdentifiers()){
            //preparo la parte destra della scanf, quella dove inserisco i valori
            codeStack.insertInLast(", &");

            identifier.accept(this);

            //preparo la parte sinistra della scanf, quella dove inserisco i formati
            switch(identifier.getNodeType()){
                case "int":
                    formati += "%d";
                    break;
                case "float":
                    formati += "%f";
                    break;
                case "string":
                    formati += "%s";
                    break;
                default:
                    formati += "";
                    break;
            }
        }
        outputC.write("scanf(\"" + formati + "\"" + codeStack.pop() + ");\n");
    }

    private void c_WhileOp(Visitable visitable) throws IOException {
        WhileOp whileOp = (WhileOp) visitable;

        BodyOp bodyOp1 = whileOp.getBodyOp1();

        // Nel caso del while con statements prima della condizione, in C questi vengono eseguiti prima di entrare nel
        // while (li inseriamo nel file di destinazione con la seguente accept()) e alla fine di ogni ciclo prima di
        // rivalutare la condizione. (Ecco perchè facciamo l'accept() dell bodyOp1 qui e dopo)
        if(bodyOp1 != null){
            bodyOp1.accept(this);
        }

        codeStack.push("");

        Expr expr = whileOp.getExpr();
        if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr.accept(this);

        outputC.write("while (" + codeStack.pop() + ") {\n");
        whileOp.getBodyOp2().accept(this);

        if(bodyOp1 != null){
            bodyOp1.accept(this);
        }

        outputC.write("}\n");
    }

    private void c_WriteOp(Visitable visitable) throws IOException {
        WriteOp writeOp = (WriteOp) visitable;

        codeStack.push("");
        String formati = "";

        for(Expr expr : writeOp.getExprs()){
            // Preparo la parte destra della printf, quella dove inserisco i valori
            codeStack.insertInLast(", ");
            int lastTemp = tempCount;
            expr.accept(this);
            if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
                codeStack.insertInLast(varTempName + lastTemp);
            }

            // Preparo la parte sinistra della printf, quella dove inserisco i formati
            switch(expr.getNodeType()){
                case "int":
                    formati += "%d";
                    break;
                case "float":
                    formati += "%f";
                    break;
                case "string":
                    formati += "%s";
                    break;
                default:
                    formati += "";
                    break;
            }
        }
        outputC.write("printf(\"" + formati + "\"" + codeStack.pop() + ");\n");
    }

    //EXPR
    private void c_AddOp(Visitable visitable) throws TypeMismatchException {
        AddOp addOp = (AddOp) visitable;

        Expr expr1 = addOp.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" + ");

        Expr expr2 = addOp.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.AddOp, expr1.getNodeType(),
                expr2.getNodeType());
        addOp.setNodeType(resultType);
    }

    private void c_AndOp(Visitable visitable) throws TypeMismatchException {
        AndOp expr = (AndOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" && ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.AndOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_BooleanConst(Visitable visitable) {
        BooleanConst booleanConst = (BooleanConst) visitable;
        codeStack.insertInLast(booleanConst.getValue());
        booleanConst.setNodeType(TypeChecker.BOOL);
    }

    private void c_DiffOp(Visitable visitable) throws TypeMismatchException {
        DiffOp expr = (DiffOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" - ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.DiffOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_DivOp(Visitable visitable) throws TypeMismatchException {
        DivOp expr = (DivOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" / ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.DivOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_EQOp(Visitable visitable) throws TypeMismatchException {
        EQOp eqOp = (EQOp) visitable;

        Expr expr1 = eqOp.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" == ");

        Expr expr2 = eqOp.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr1.getNodeType(),
                expr2.getNodeType());
        eqOp.setNodeType(resultType);
    }

    private void c_FloatConst(Visitable visitable) {
        FloatConst floatConst = (FloatConst) visitable;
        codeStack.insertInLast(floatConst.getValue());
        floatConst.setNodeType(TypeChecker.FLOAT);
    }

    private void c_GEOp(Visitable visitable) throws TypeMismatchException {
        GEOp expr = (GEOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" >= ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_GTOp(Visitable visitable) throws TypeMismatchException {
        GTOp expr = (GTOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" > ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_Identifier(Visitable visitable) {
        Identifier identifier = (Identifier) visitable;
        codeStack.insertInLast(identifier.getName());
    }

    private void c_IntConst(Visitable visitable) {
        IntConst intConst = (IntConst) visitable;
        codeStack.insertInLast(intConst.getValue());
        intConst.setNodeType(TypeChecker.INT);
    }

    private void c_LEOp(Visitable visitable) throws TypeMismatchException {
        LEOp expr = (LEOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" <= ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_LTOp(Visitable visitable) throws TypeMismatchException {
        LTOp expr = (LTOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" < ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_MulOp(Visitable visitable) throws TypeMismatchException {
        MulOp expr = (MulOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" * ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.MulOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_NEOp(Visitable visitable) throws TypeMismatchException {
        NEOp expr = (NEOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" != ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.RelOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_NotOp(Visitable visitable) throws FatalError, TypeMismatchException {
        NotOp notOp = (NotOp) visitable;

        codeStack.insertInLast("!");
        Expr expr = notOp.getExpr();
        if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr.accept(this);

        String resultType = TypeChecker.typeCheckUnaryOp(TypeChecker.NotOp, expr.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_NullConst(Visitable visitable) {
        NullConst nullConst = (NullConst) visitable;
        codeStack.insertInLast(nullConst.getValue());
        nullConst.setNodeType(TypeChecker.NULL);
    }

    private void c_OrOp(Visitable visitable) throws TypeMismatchException {
        OrOp expr = (OrOp) visitable;

        Expr expr1 = expr.getExpr1();
        if(expr1.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr1.accept(this);

        codeStack.insertInLast(" || ");

        Expr expr2 = expr.getExpr2();
        if(expr2.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr2.accept(this);

        String resultType = TypeChecker.typeCheckBinaryOp(TypeChecker.OrOp, expr1.getNodeType(),
                expr2.getNodeType());
        expr.setNodeType(resultType);
    }

    private void c_StringConst(Visitable visitable) {
        StringConst stringConst = (StringConst) visitable;
        codeStack.insertInLast(stringConst.getValue());
        stringConst.setNodeType(TypeChecker.STRING);
    }

    private void c_UminusOp(Visitable visitable) throws FatalError, TypeMismatchException {
        UminusOp uminusOp = (UminusOp) visitable;

        codeStack.insertInLast("-");
        Expr expr = uminusOp.getExpr();
        if(expr.getClass().getName().equals("nodes.statements.CallProcOp")){
            codeStack.insertInLast(varTempName + tempCount);
        }
        expr.accept(this);
        String resultType = TypeChecker.typeCheckUnaryOp(TypeChecker.UminusOp, expr.getNodeType());
        expr.setNodeType(resultType);
    }
}
