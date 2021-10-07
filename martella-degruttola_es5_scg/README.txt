                    CUP
Mac OS
java -jar ../CUP/java-cup-11b.jar -dump -destdir src srcjflexcup/toy.cup 2> dumpCup.txt
Windows
java -jar C:\CUP\java-cup-11b.jar -dump -destdir src srcjflexcup\toy.cup 2> dumpCup.txt

                    FLEX
Mac OS
../jflex/bin/jflex -d src srcjflexcup/toy.flex
Windows
C:\JFLEX\bin\jflex.bat -d src srcjflexcup\toy.flex

-------------------------------------------------------------------------------------------------------------------------------------

Funzionamento del XMLVisitor

Dopo aver generato l'AST tramite il parser, lo visitamo tramite questo XMLVisitor per creare un file che ci mostri come
quest'albero sia composto. Partiamo visitando la radice (ProgramOp) e per ogni nodo incontrato creiamo un tag xml per
quel nodo. Inoltre visitiamo i suoi nodi figli la medesima operazione.

-------------------------------------------------------------------------------------------------------------------------------------

Funzionamento del SemanticVisitor:

Tramite questo visitor andiamo ancora una volta a espolare i nodi dell'AST per effetuare l'analisi semantica.
Nell'analisi semantica bisogna gestire lo scooping e il type checking.


------------------------------------------------------------------------------------------------------------------------

SCOOPING:
Per la gestione dello scooping andiamo a inserire nella tabella dello scoop corrente gli identificatori di variabili e
funzioni rispettivamente quando andiamo ad esplorare i nodi VarDeclOp e ProcOp.
Un'altro compito di scooping è quello di verificare se gli identificatori (di variabili e funzioni) che troviamo
durante l'esplorazione dell'AST siano stati dichiarati in precedenza e che siano visibili nello scoop del constesto
corrente. Queste verifiche le facciamo ogni volta che ritroviamo identificatori (chiamando il metodo identfier.accept()).
Ovviamente quest'operazione è inutile quando ci ritroviamo all'interno del nodo VarDeclOp, perchè in quel caso noi
inseriamo l'identificatore nella tabella dei simboli, ed è inutile farla anche nel nodo ProcOp con l'identificatore della
funzione perchè anche in questo caso stiamo inserendo quell'identificatore all'interno della tabella dei simboli.

------------------------------------------------------------------------------------------------------------------------

TYPE CHECKING:
Le classiche operazioni di Type Checking vengono effettuate su tutte i nodi expr. Ci soffermiamo su AssignOp, ProcOp e
CallProcOp.

Nel nodo AssignOp andiamo a costruire due ArryList<String> contenti uno i tipi delle variabili del lato sinistro dell'assegnazione
e uno i tipi dei valori che vogliamo assegnare a tali variabili. Costruiti i due array invochiamo il TypeChecker per
verificare che PRIMO le size siano identiche (per verificare che il numero delle variabili di sinistra sia identico al
numero dei valori che vogliamo assegnare) e SECONDO che i tipi tra variabili siano identici.
Non è possibile avere nel lato sinistro funzioni con non restituiscono niente (void).

Nel nodo ProcOp invece andiamo a verificare che il tipo dei valori di ritorno della funzione siano matchabili con i tipi
di ritorno indicati nella sua firma. La strategia è la stessa dell'AssagnOp andando a creare le due liste e passarle al
TypeChecker.

Nel nodo CallProc andiamo invece a verificare che i tipi dei valori che forniamo in input alla funzione invocata siano
identici ai tipi dei paramentri in ingresso indicati nella firma della funzione. La strategia è sempre la stessa
(come indicato nell'AssignOp).
Non è possibile passare come paramentro una funione che non restituisca niente (void).

Per tutti e tre i controlli bisogna stare attenti alle espressioni che possiamo inserire:
in una parte destra di una assegnazione (AssignOp);
come valore di ritorno in una funzione (ProcOp);
come parametro di input in una chiamata di funzione (CallProc).
Se l'espressione è una chiamata a funzione, questa potrebbe non restuire alcun valore (void) o
restituire più valori (functionMultiReturn).

Ogni nodo ha una variabile nodeType, che viene settata con un determinato tipo. In particolare, se il nodo è un
espressione, il suo tipo è dato andando passare gli argomenti (o l'argomento) dell'espressione è il tipo di operazione
al TypeChecker e invocando il metodo typeCheckBinaryOp (o typeCheckUnaryOp).
Per quanti riguarda il tipo del nodo CallProcOp, nodo che può essere sia uno statement che un expr, se la funzione
restituisce un solo valore viene settato con tale valore, altrimenti sarà functionMultiReturn (anche nel caso che non
restituisca niente).

------------------------------------------------------------------------------------------------------------------------

*Attenzione! Allo stato attuale possiamo dichiarare funzioni con nome 'readln' e 'write', ma non possiamo utilizzarle
poi come statement (CallProcOp di tipo statement) in quanto ci sono già le 'readln' e 'write' come statement. Verranno
utilizzate solo quando quelle due funzioni dichiarate verranno tradotte come 'expr'.

------------------------------------------------------------------------------------------------------------------------

CLangVisitor

La scrittura della traduzione nel file 'C' di destiazione avviene sempre alla fine di uno statement.
Ogni volta che entriamo in un nuovo nodo statement viene inserito all'interno di uno stack globale la stringa che
conterrà il pezzo di codice della traduzione in 'C'.
Quando invece andiamo a visitare le expr, queste verranno appesse al codice dello statement di cui fanno parte,
prendendo tale codice dallo stack gloabale dei codici.

*Gestione traduzione funzioni da toy a C
Una criticità da superare è il fatto che in toy abbiamo funzioni che possono ritornare più valori.
Per risolvere questo costrutto in C ci avvaliamo di variabili temporanee che andranno a sostituire i valori di ritorno.
Nella funzione chiamante verranno dunque dichiarate tante variabili temporanee quanti sono i valori di ritorno della
funzione chiamata. Queste variabili verranno passate (i loro indirizzi), in aggiunta ai paramentri attuali, alla
funzione è nel momento del ritorno questa funzione andrà a scrivere i risultati che deve ritornare al chiamante in
queste apposite variabili temporanee. Le funzioni in C dunque sarano tutte di tipo "void" data questa gestione per i
ritorni.

Gestendo in questo modo i ritorni delle funzioni, è necessario "preparare" le funzioni prima che queste siano chiamate in
qualche statement e dare allo statement, che fa uso dei ritorni delle funzioni, le variabili temporanee generate facendoci
posticipare la scrittura dello statement nel file "C" di destinazione.
(Verdere es-piu-ritorni.toy)
Com'è possibile vedere nell'esempio, a due variabili vengono assegnati i valori di ritorno della funzione "duplica".
    a, b := duplica(x);
Per la traduzione in C bisogna prima "preparare" la funzione duplica:
    int varTemp0;
    int varTemp1;
    duplica(x, &varTemp0, &varTemp1);
e successivamente assegnare le variabili temporanee in cui si è salvato il valore di ritorno ad 'a' e 'b':
    a = varTemp0;
    b = varTemp1;

*HashMap procNumReturn
Nel gestire le funzioni in tale modo è indispensabile sapere in determinati momenti i tipi e il numero di variabili
di ritorno di una funzione, in quando andremo a generare delle variabili temporanee con quei tipi. Tali informazioni
vengono apprese e memorizzate in una hashMap globale nel momento della visita del nodo ProcOp. In particolare
nell'hashMap memorizziamo l'identificatore della funzione e l'array di stringhe che indica i suoi valori di ritorno.
(si veda nella visita del nodo CallProcOp).

*Variabile tempCount
Variabile contatore utilizzata per dare sempre un nome diverso alle variabili temporanee. Utilizzata anche quando abbiamo
la necessità di assegnare i valori di ritorno delle funzioni tradotte in C.

*Utilizzo TypeChecker e tabella dei simboli(per sapere il valore delle variabili (identifier)) nel CLangVisitor
Nelle expr settiamo il valore delle espressioni in quanto se ci troviamo nello statement "write" è fondamentale sapere
il valore delle expr e convertire la 'write' nella 'printf' di "C".

*Visita espressioni Add, Diff, Mul, Div
Quando ci ritroviamo all'interno di uno di questi nodi, dobbiamo visitare i due operandi che compongono l'operazione.
Nella visita del primo, così come nel secondo, se l'operando (che ricoridamo essere anche esso essere un expr) è una
chiamata di funzione, dobbiamo prima "preparare" la funzione e dare come operando la variabile temporanea utilizzata come
variabile di ritorno della funzione.
(ricordiamo che abbiamo imposto che una funzione può essere utilizzata in una espressione aritmetica solo se questa
restituisce un solo valore di ritorno. Il controllo è fatto nell'analisi semantica)

*Visita espressioni Identifier, IntConst, FloatConst, BooleanConst, StringConst
Stampo vicino all'ultimo pezzo di codice che sto scrivendo il loro valore.