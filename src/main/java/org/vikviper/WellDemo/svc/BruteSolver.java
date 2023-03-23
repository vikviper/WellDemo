package org.vikviper.welldemo.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vikviper.welldemo.res.LineSegment;
import org.vikviper.welldemo.res.Point;
import org.vikviper.welldemo.res.Space;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class BruteSolver {
    @Autowired
    Space space;

    public Set<LineSegment> getLinesWithNPoints(int minPoints) {
        Set<LineSegment> resultLines = new HashSet<>();
        int numElems = space.getPoints().size();

        // controllo sul numero di elementi minimo richiesto
        if (minPoints < 2 || minPoints > numElems)
            throw new RuntimeException("Il numero minimo di punti richiesti (" + minPoints + ") è troppo piccolo o troppo grande.");

        // converte l'insieme dei punti in un array
        Point[] points = space.getPoints().toArray(new Point[numElems]);

        // Mappa delle linee possibili (Chiave: equazione della retta; Valore: LineSegment = insieme dei punti)
        HashMap<String, LineSegment> mappaSegmenti = new HashMap<>();

        for (int i1 = 0; i1 < numElems - 1; i1++) {// cicla per ciascuno dei punti dello space tranne l'ultimo
            for (int i2 = i1 + 1; i2 < numElems; i2++) {// cicla a partire dal punto successivo fino all'ultimo
                String eqRetta;
                int yDiff = points[i2].getY() - points[i1].getY();
                int xDiff = points[i2].getX() - points[i1].getX();

                if (xDiff == 0) {// retta parallela all'asse Y
                    eqRetta = "x = " + points[i2].getX();
                } else {
                    String coeffAng = frazRidotta(yDiff, xDiff);

                    int numeratore = points[i2].getX() * points[i1].getY() - points[i1].getX() * points[i2].getY();
                    int denominatore = points[i2].getX() - points[i1].getX();
                    String intercetta = frazRidotta(numeratore, denominatore);

                    // Inserisce l'equazione della retta in una stringa, da usare come chiave
                    eqRetta = "y = (" + coeffAng + ")x + (" + intercetta + ")";
                }

                if (!mappaSegmenti.containsKey(eqRetta)) { // nuovo segmento
                    mappaSegmenti.put(eqRetta, new LineSegment(new HashSet<>()));
                    // aggiunge il primo punto al segmento con equazione uguale a quella corrente nella mappa (se non già presente)
                    mappaSegmenti.get(eqRetta).getPoints().add(points[i1]);
                }// if

                // aggiunge il secondo punto al segmento con equazione uguale a quella corrente nella mappa (se non già presente)
                mappaSegmenti.get(eqRetta).getPoints().add(points[i2]);
            }// ciclo interno
        }// ciclo esterno

        // trova i segmenti che passano per almeno <minPoints> punti
        mappaSegmenti.forEach((eqRetta, segmento) -> {
            System.out.print("--> Trovato segmento lungo " + segmento.getPoints().size() +
                    " con equazione: " + eqRetta + " passante per i punti: ");
            segmento.getPoints().forEach(point -> System.out.print("(" + point.getX() + "," + point.getY() + ") "));
            System.out.println();
            if (segmento.getPoints().size() >= minPoints) {
                resultLines.add(segmento);
            }// if
        });

        return resultLines;
    }// getLinesWithNPoints()

    /**
     * Calcolo dell'MCD con l'algoritmo di Euclide
     */
    private int MCD(int m, int n) {
        if (n == 0) return m;
        return MCD(n, m % n);
    }// MCD()

    private String frazRidotta(int numeratore, int denominatore) {
        int mcd = MCD(numeratore, denominatore);
        numeratore = numeratore / mcd;
        denominatore = denominatore / mcd;
        return numeratore + "/" + denominatore;
    }
}// end of class
