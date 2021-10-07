import nodes.ProgramOp;
import visitors.*;

import java.io.File;
import java.io.FileReader;

public class MainTester {

    public static void main(String[] args) throws Exception {

/*
        FileReader fr = new FileReader(new File(args[0]));
        Yylex yy = new Yylex(fr, true);

        System.out.println("****************** Analisi Lessicale *********************");


        for (Symbol symbol = yy.next_token(); symbol.sym != sym.EOF ; symbol = yy.next_token()) {

            if (symbol.value == null){
                System.out.println("< " + sym.terminalNames[symbol.sym] + " >\n");
            }else {
                System.out.println("< " + sym.terminalNames[symbol.sym] + ", \""+ symbol.value.toString()+"\" >\n");
            }
        }

        System.out.println("****************** Stampa symbol table dell'analisi lessicale.... *********************");

        HashMap<String, Symbol> symTable = yy.getSymbolTable();

        for (String name: symTable.keySet()){

            String key =name.toString();
            Symbol value = symTable.get(name);

            System.out.println(key + " (ID,"+value.value+")");
        }
*/
        String inPathFile = args[0];

        //controllo sul file in input: vedere se il file è ".toy"
        if (!(inPathFile.endsWith(".toy"))){
            System.err.println("Dare in input un file .toy");
            System.exit(1);
        }

        String outPathFile = inPathFile.replaceAll(".toy", ".c");

        parser p = new parser(new Yylex(new FileReader(new File(inPathFile)), true));

        ProgramOp programOp = (ProgramOp) p.parse().value;
        //ProgramOp programOp = (ProgramOp) p.debug_parse().value;

        XMLVisitor xmlVisitor = new XMLVisitor();
        programOp.accept(xmlVisitor);
        xmlVisitor.exportDocument(new File(inPathFile.replaceAll(".toy", "_AST.txt")));

        SemanticVisitor semanticVisitor = new SemanticVisitor();
        programOp.accept(semanticVisitor);

        CLangVisitor cLangVisit = new CLangVisitor(new File(outPathFile));
        programOp.accept(cLangVisit);
    }
}