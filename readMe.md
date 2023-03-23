Nell'affrontare la soluzione di questo compito ho subito trovato la fonte di ispirazione dello stesso e visto alcuni algoritmi di soluzione.
Tuttavia nessuno di quelli che ho trovati era perfettamente aderente al quesito proposto.
Per questo motivo ho dovuto "inventarmene" uno io.
La soluzione a cui sono arrivato non è probabilmente la più efficiente possibile, e di sicuro non la più elegante nella forma, ma per quello che ho verificato funziona.
L'algoritmo può essere descritto così:

Ho definito nella configuration dell'applicazione space come spring-bean di tipo Space contenente un Set di Point (in particolare ho usato poi degli HashSet che mi garantiscono l'unicità degli elementi) in maniera tale che fosse istanza unica condivisa;
LineSegment sempre come Set di Point, da istanziare di volta in volta.
Point con 2 membri int (x e y) contenenti le coordinate del punto.

Parto col primo ciclo dal primo punto di space (fino al penultimo - l'ultimo non avrebbe altri punti con cui calcolare l'equazione della retta che li comprende),
il secondo ciclo prende tutti gli elementi di space rimanenti e per ciascuna coppia calcola l'equazione della retta che li comprende.
All'inizio mi sono limitato a calcolare il solo coefficiente angolare della retta, ma questo porta a risultati errati per punti su rette parallele.
Per evitare problemi con l'arrotondamento dei double (o dei float, per quello), ho preferito calcolare il coefficiente angolare (e l'intercetta) come frazioni ridotte usando il MCD (calcolato con l'algoritmo di Euclide).
Se l'equazione così trovata non è già presente nella Mappa dei segmenti possibili, viene aggiunto un elemento alla mappa con chiave uguale all'equazione della retta e al valore corrispondente (che è un Set di Point) il Point corrente del primo Ciclo.
Dopodiché viene aggiunto il Point corrente del secondo ciclo al Set di Point corrispondente all'elemento della mappa con chiave uguale all'equazione della retta attualmente calcolata.
Trattandosi di un HashSet, non vengono creati duplicati.
Concluso il ciclo nidificato, nella mappa abbiamo tutti i segmenti possibili completi di tutti i punti che loro appartengono.
Verificando quali di questi hanno un numero di punti >= al vincolo passato, posso restituire la soluzione.

Nel determinare l'equazione della retta, ho isolato il caso speciale delle rette parallele all'asse Y, che fanno eccezione alla formula standard per determinare il coefficiente e termine noto dell'equazione).
Volendo si poteva isolare anche il caso anche delle rette parallele all'asse X, ma queste possono rientrare nella formula "standard".
Nella scelta della chiave si poteva, ovviamente, "risparmiare" caratteri (ovvero longwords), ma ho preferito mantenerla leggibile da umani per scopi di debugging.

Ho aggiunto qualche test del metodo di risoluzione con JUnit e all'inizio del metodo stesso un check per escludere le aberrazioni per numero minimo di punti richiesto < 2 e > N.

Grazie della cortese attenzione,
VM
