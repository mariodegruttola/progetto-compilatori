package visitors;

import exception.FatalError;
import nodes.*;
import nodes.exprs.*;
import nodes.statements.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;


public class XMLVisitor implements Visitor {

    private Document doc;

    public XMLVisitor() throws Exception {
        initDocument();
    }

    @Override
    public Element visit(Visitable visitable) {

        String className = visitable.getClass().getName();

        switch (className) {

            case "nodes.IdInitOp":
                return xml_IdInitOp(visitable);

            case "nodes.ParDeclOp":
                return xml_ParDeclOp(visitable);

            case "nodes.ProcOp":
                return xml_ProcOp(visitable);

            case "nodes.ProgramOp":
                return xml_ProgramOp(visitable);

            case "nodes.VarDeclOp":
                return xml_VarDeclOp(visitable);

            case "nodes.ProcBodyOp":
                return xml_ProcBodyOp(visitable);

                //Statements
            case "nodes.statements.BodyOp":
                return xml_BodyOp(visitable);

            case "nodes.statements.ReadOp":
                return xml_ReadOp(visitable);

            case "nodes.statements.WriteOp":
                return xml_WriteOp(visitable);

            case "nodes.statements.AssignOp":
                return xml_AssignOp(visitable);

            case "nodes.statements.WhileOp":
                return xml_WhileOp(visitable);

            case "nodes.statements.IfOp":
                return xml_IfOp(visitable);

            case "nodes.statements.ElifOp":
                return xml_ElifOp(visitable);

            case "nodes.statements.CallProcOp":
                return xml_CallProcOp(visitable);

                //Exprs

            case "nodes.exprs.AddOp":
                return xml_AddOp(visitable);

            case "nodes.exprs.AndOp":
                return xml_AndOp(visitable);

            case "nodes.exprs.BooleanConst":
                return xml_BooleanConst(visitable);

            case "nodes.exprs.DiffOp":
                return xml_DiffOp(visitable);

            case "nodes.exprs.DivOp":
                return xml_DivOp(visitable);

            case "nodes.exprs.EQOp":
                return xml_EQOp(visitable);

            case "nodes.exprs.FloatConst":
                return xml_FloatConst(visitable);

            case "nodes.exprs.GEOp":
                return xml_GEOp(visitable);

            case "nodes.exprs.GTOp":
                return xml_GTOp(visitable);

            case "nodes.exprs.Identifier":
                return xml_Identifier(visitable);

            case "nodes.exprs.IntConst":
                return xml_IntConst(visitable);

            case "nodes.exprs.LEOp":
                return xml_LEOp(visitable);

            case "nodes.exprs.LTOp":
                return xml_LTOp(visitable);

            case "nodes.exprs.MulOp":
                return xml_MulOp(visitable);

            case "nodes.exprs.NEOp":
                return xml_NEOp(visitable);

            case "nodes.exprs.NotOp":
                return xml_NotOp(visitable);

            case "nodes.exprs.NullConst":
                return xml_NullConst(visitable);

            case "nodes.exprs.OrOp":
                return xml_OrOp(visitable);

            case "nodes.exprs.StringConst":
                return xml_StringConst(visitable);

            case "nodes.exprs.UminusOp":
                return xml_UminusOp(visitable);

            default:
                try {
                    throw new FatalError("La classe '" + className + "' non è stata trovata all'interno del XMLVisitor");
                } catch (FatalError fatalError) {
                    fatalError.printStackTrace();
                    System.exit(1);
                }
                return null;
        }
    }

    private Element xml_ProgramOp(Visitable visitable){

        ProgramOp prog = (ProgramOp) visitable;
        Element e = doc.createElement("ProgramOP");

        for(VarDeclOp varDeclOp : prog.getVarDeclOps()){
            e.appendChild(varDeclOp.accept(this));

        }
        for(ProcOp procOp : prog.getProcOps()){
            e.appendChild(procOp.accept(this));
        }

        doc.appendChild(e);

        return null;
    }

    private Element xml_VarDeclOp(Visitable visitable) {

        VarDeclOp varDeclOp = (VarDeclOp) visitable;
        Element e = doc.createElement("VarDeclOp");
        Element eT = doc.createElement("Type");
        eT.appendChild(doc.createTextNode(varDeclOp.getType()));
        e.appendChild(eT);

        for(IdInitOp idInitOp : varDeclOp.getIdInitOps()){
            e.appendChild(idInitOp.accept(this));
        }
        return e;
    }

    private Element xml_IdInitOp(Visitable visitable) {
        IdInitOp idInitOp = (IdInitOp) visitable;
        Element e = doc.createElement("IdInitOp");

        e.appendChild(idInitOp.getIdentifier().accept(this));

        Expr expr = idInitOp.getExpr();
        if(expr != null){
            e.appendChild(expr.accept(this));
        }

        return e;
    }

    private Element xml_ProcOp(Visitable visitable) {
        ProcOp procOp = (ProcOp) visitable;
        Element e = doc.createElement("ProcOp");

        e.appendChild(procOp.getIdentifier().accept(this));

        ArrayList<ParDeclOp> parDeclOps = procOp.getParDeclOps();
        for(ParDeclOp parDeclOp : procOp.getParDeclOps()){
            e.appendChild(parDeclOp.accept(this));
        }

        ArrayList<String> resultTypes = procOp.getResultTypeList();
        if(!resultTypes.isEmpty()){
            for(String type : resultTypes){
                Element eT = doc.createElement("ResultType");
                eT.appendChild(doc.createTextNode(type));
                e.appendChild(eT);
            }
        }

        e.appendChild(procOp.getProcBodyOp().accept(this));

        return e;
    }

    private Element xml_ParDeclOp(Visitable visitable) {
        ParDeclOp parDeclOp = (ParDeclOp) visitable;
        Element e = doc.createElement("ParDeclOp");

        Element eT = doc.createElement("Type");
        eT.appendChild(doc.createTextNode(parDeclOp.getType()));
        e.appendChild(eT);

        for(Identifier identifier : parDeclOp.getIdentifiers()){
            e.appendChild(identifier.accept(this));
        }
        return e;
    }

    private Element xml_ProcBodyOp(Visitable visitable) {
        ProcBodyOp procBodyOp = (ProcBodyOp) visitable;
        Element e = doc.createElement("ProcBodyOp");

        for(VarDeclOp varDeclOp : procBodyOp.getVarDeclOps()){
            e.appendChild(varDeclOp.accept(this));
        }

        for(Statement statement : procBodyOp.getStatements()){
            e.appendChild(statement.accept(this));
        }

        for(Expr expr : procBodyOp.getExprs()){
            e.appendChild(expr.accept(this));
        }
        return e;
    }

    //------------STATEMENTS
    private Element xml_BodyOp(Visitable visitable) {
        BodyOp bodyOp = (BodyOp) visitable;
        Element e = doc.createElement("BodyOp");

        ArrayList<Statement> statements = bodyOp.getStatements();
        for(Statement statement : statements){
            e.appendChild(statement.accept(this));
        }

        return e;
    }

    private Element xml_ReadOp(Visitable visitable) {
        ReadOp readOp = (ReadOp) visitable;
        Element e = doc.createElement("ReadOp");
        for(Identifier identifier : readOp.getIdentifiers()){
            e.appendChild(identifier.accept(this));
        }
        return e;
    }

    private Element xml_WriteOp(Visitable visitable) {
        WriteOp writeOp = (WriteOp) visitable;
        Element e = doc.createElement("WriteOp");

        for(Expr expr : writeOp.getExprs()) {
            e.appendChild(expr.accept(this));
        }

        return e;
    }

    private Element xml_AssignOp(Visitable visitable){
        AssignOp assignOp = (AssignOp) visitable;
        Element e_AssignOp = doc.createElement("AssignOp");

        Element e_Identifiers = doc.createElement("Identifiers");

        for(Identifier identifier : assignOp.getIdentifiers()){
            e_Identifiers.appendChild(identifier.accept(this));
        }

        e_AssignOp.appendChild(e_Identifiers);

        Element e_Exrps = doc.createElement("Exprs");


        for(Expr expr : assignOp.getExprs()){
            e_Exrps.appendChild(expr.accept(this));
        }
        e_AssignOp.appendChild(e_Exrps);

        return e_AssignOp;
    }


    private Element xml_WhileOp(Visitable visitable) {
        WhileOp whileOp = (WhileOp) visitable;
        Element e = doc.createElement("WhileOp");

        BodyOp bodyOp1 = whileOp.getBodyOp1();
        if(bodyOp1 != null){
            e.appendChild(bodyOp1.accept(this));
        }

        e.appendChild(whileOp.getExpr().accept(this));
        e.appendChild(whileOp.getBodyOp2().accept(this));

        return e;
    }

    private Element xml_IfOp(Visitable visitable) {
        IfOp ifOp = (IfOp) visitable;
        Element e = doc.createElement("IfOp");

        e.appendChild(ifOp.getExpr().accept(this));
        e.appendChild(ifOp.getBodyOp().accept(this));

        ArrayList<ElifOp> elifOps = ifOp.getElifOps();
        for(ElifOp elifOp : elifOps){
            e.appendChild(elifOp.accept(this));
        }

        BodyOp bodyOpElse = ifOp.getBodyOpElse();
        if(bodyOpElse != null){
            e.appendChild(bodyOpElse.accept(this));
        }

        return e;
    }

    private Element xml_ElifOp(Visitable visitable) {
        ElifOp elifOp = (ElifOp) visitable;
        Element e = doc.createElement("ElifOp");

        e.appendChild(elifOp.getExpr().accept(this));
        e.appendChild(elifOp.getBodyOp().accept(this));

        return e;
    }

    private Element xml_CallProcOp(Visitable visitable) {
        CallProcOp callProcOp = (CallProcOp) visitable;
        Element e = doc.createElement("CallProcOp");

        e.appendChild(callProcOp.getIdentifier().accept(this));

        for(Expr expr : callProcOp.getExprs()) {
            e.appendChild(expr.accept(this));
        }

        return e;
    }

    //------------EXPRS

    private Element xml_Identifier(Visitable visitable){
        Identifier identifier = (Identifier) visitable;
        Element e = doc.createElement("Identifier");
        e.appendChild(doc.createTextNode(identifier.getName()));
        return e;
    }

    private Element xml_BooleanConst(Visitable visitable){
        BooleanConst booleanConst = (BooleanConst) visitable;
        Element e = doc.createElement("BooleanConst");

        e.appendChild(doc.createTextNode(booleanConst.getValue()));

        return e;
    }

    private Element xml_FloatConst(Visitable visitable){
        FloatConst floatConst = (FloatConst) visitable;
        Element e = doc.createElement("FloatConst");

        e.appendChild(doc.createTextNode(floatConst.getValue()));

        return e;
    }

    private Element xml_IntConst(Visitable visitable){
        IntConst intConst = (IntConst) visitable;

        Element e = doc.createElement("IntConst");
        e.appendChild(doc.createTextNode(intConst.getValue()));

        return e;
    }

    private Element xml_NullConst(Visitable visitable){
        NullConst nullConst = (NullConst) visitable;
        Element e = doc.createElement("NullConst");

        e.appendChild(doc.createTextNode(nullConst.getValue()));

        return e;
    }

    private Element xml_StringConst(Visitable visitable){
        StringConst stringConst = (StringConst) visitable;
        Element e = doc.createElement("StringConst");

        e.appendChild(doc.createTextNode(stringConst.getValue()));

        return e;
    }

    private Element xml_AddOp(Visitable visitable){
        AddOp addOp = (AddOp) visitable;
        Element e = doc.createElement("AddOp");

        e.appendChild(addOp.getExpr1().accept(this));
        e.appendChild(addOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_AndOp(Visitable visitable){
        AndOp andOp = (AndOp) visitable;
        Element e = doc.createElement("AndOp");

        e.appendChild(andOp.getExpr1().accept(this));
        e.appendChild(andOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_DiffOp(Visitable visitable){
        DiffOp diffOp = (DiffOp) visitable;
        Element e = doc.createElement("DiffOp");

        e.appendChild(diffOp.getExpr1().accept(this));
        e.appendChild(diffOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_DivOp(Visitable visitable){
        DivOp divOp = (DivOp) visitable;
        Element e = doc.createElement("DivOp");

        e.appendChild(divOp.getExpr1().accept(this));
        e.appendChild(divOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_EQOp(Visitable visitable){
        EQOp eqOp = (EQOp) visitable;
        Element e = doc.createElement("EQOp");

        e.appendChild(eqOp.getExpr1().accept(this));
        e.appendChild(eqOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_GEOp(Visitable visitable){
        GEOp geOp = (GEOp) visitable;
        Element e = doc.createElement("GEOp");

        e.appendChild(geOp.getExpr1().accept(this));
        e.appendChild(geOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_GTOp(Visitable visitable){
        GTOp gtOp = (GTOp) visitable;
        Element e = doc.createElement("GTOp");

        e.appendChild(gtOp.getExpr1().accept(this));
        e.appendChild(gtOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_LEOp(Visitable visitable){
        LEOp leOp = (LEOp) visitable;
        Element e = doc.createElement("LEOp");

        e.appendChild(leOp.getExpr1().accept(this));
        e.appendChild(leOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_LTOp(Visitable visitable){
        LTOp ltOp = (LTOp) visitable;
        Element e = doc.createElement("LTOp");

        e.appendChild(ltOp.getExpr1().accept(this));
        e.appendChild(ltOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_MulOp(Visitable visitable){
        MulOp mulOp = (MulOp) visitable;
        Element e = doc.createElement("MulOp");

        e.appendChild(mulOp.getExpr1().accept(this));
        e.appendChild(mulOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_NEOp(Visitable visitable){
        NEOp neOp = (NEOp) visitable;
        Element e = doc.createElement("NEOp");

        e.appendChild(neOp.getExpr1().accept(this));
        e.appendChild(neOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_NotOp(Visitable visitable){
        NotOp notOp = (NotOp) visitable;
        Element e = doc.createElement("NotOp");

        e.appendChild(notOp.getExpr().accept(this));

        return e;
    }

    private Element xml_OrOp(Visitable visitable){
        OrOp orOp = (OrOp) visitable;
        Element e = doc.createElement("OrOp");

        e.appendChild(orOp.getExpr1().accept(this));
        e.appendChild(orOp.getExpr2().accept(this));

        return e;
    }

    private Element xml_UminusOp(Visitable visitable){
        UminusOp uminusOp = (UminusOp) visitable;
        Element e = doc.createElement("UminusOp");

        e.appendChild(uminusOp.getExpr().accept(this));

        return e;
    }

    //--------------------------------------------------------------

    private void initDocument() throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.newDocument();
    }

    public void exportDocument(File file) throws Exception {

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);

    }
}
