package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import exception.MultipleDeclarationException;
import nodes.exprs.Identifier;

public class SymbolTable extends HashMap<String, Identifier> {

    private String name;

    public SymbolTable(String n) {
        super();
        name = n;
    }

    public Identifier insertId(Identifier id) throws MultipleDeclarationException{

        if(id.getIsFunction()){
            //Inseriamo le parentesi, se è una funzione, così che non ci sarà un conflitto di chiavi con una variabile se questa
            //è stata dichiarata con lo stesso nome della funzione. Questo quindi ci permette di dichiarare una variabile con
            //lo stesso nome di una funzione, e viceversa.
            if(this.containsKey(id.getName() + "()")){
                throw new MultipleDeclarationException("dichiarazione multipla della funzione '"+id.getName()+"()' nello scope di " + name);
            }
            else{
                this.put(id.getName() + "()", id);
                return id;
            }

        } else {
            if(this.containsKey(id.getName())){
                throw new MultipleDeclarationException("dichiarazione multipla della variabile '"+id.getName()+"' nello scope di " + name);
            }
            else{
                this.put(id.getName(), id);
                return id;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printAllIdentifierName() {
        Iterator<Entry<String, Identifier>> entries = this.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Identifier> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().getName());
        }
    }
}