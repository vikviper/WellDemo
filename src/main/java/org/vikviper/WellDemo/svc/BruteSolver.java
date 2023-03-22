package org.vikviper.welldemo.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vikviper.welldemo.res.LineSegment;
import org.vikviper.welldemo.res.Point;
import org.vikviper.welldemo.res.Space;

import java.util.*;

@Service
public class BruteSolver {
    @Autowired
    Space space;

    public Set<LineSegment> getLinesWithNPoints(int minPoints) {
        int numElems = space.getPoints().size();
        Point[] points = space.getPoints().toArray(new Point[numElems]);

        // Mappa delle linee possibili (Chiave: "numeratore,denominatore"; Valore: LineSegment = insieme dei punti)
        HashMap<String, LineSegment> mappaSegmenti = new HashMap<>();

        for (int i1 = 0; i1 < numElems - 1; i1++) {// cicla per ciascuno dei punti dello space
            for (int i2 = i1 + 1; i2 < numElems; i2++) {// cicla a partire dal punto successivo
                System.out.print("Primo punto: (" + points[i1].getX() + "," + points[i1].getY() + ")");
                System.out.print(" - Secondo punto: (" + points[i2].getX() + "," + points[i2].getY() + ")");
                String retta;
                int yDiff = points[i2].getY() - points[i1].getY();
                int xDiff = points[i2].getX() - points[i1].getX();

                if (xDiff == 0) {
                    retta = "x = " + points[i2].getX();
                } else {
                    String coeffAng = frazRidotta(yDiff, xDiff);

                    int numeratore = points[i2].getX() * points[i1].getY() - points[i1].getX() * points[i2].getY();
                    int denominatore = points[i2].getX() - points[i1].getX();
                    String intercept = frazRidotta(numeratore, denominatore);

                    // Inserisce l'equazione della retta in una stringa, da usare come chiave
                    retta = "y = (" + coeffAng + ")x + (" + intercept + ")" ;
                    System.out.println(" segmento con equazione: " + retta);
                }

                if (!mappaSegmenti.containsKey(retta)) { // nuovo segmento
                    System.out.println("* nuova retta: " + retta);
                    mappaSegmenti.put(retta, new LineSegment(new HashSet<>()));
                    // aggiunge il primo punto alla linea col coefficiente angolare corrente nella mappa (se non già presente)
                    System.out.println("--> aggiunto il punto di partenza: (" + points[i1].getX() + "," + points[i1].getY() + ") al segmento: " + retta);
                    mappaSegmenti.get(retta).getPoints().add(points[i1]);
                }// if

                // aggiunge il secondo punto alla linea col coefficiente angolare corrente nella mappa (se non già presente)
                mappaSegmenti.get(retta).getPoints().add(points[i2]);
                System.out.println("--> aggiunto il punto (" + points[i2].getX() + "," + points[i2].getY() + ") al segmento: " + retta);
            }// ciclo interno
            System.out.println();
        }// ciclo esterno

        // trova i segmenti che passano per almeno minPoints punti
        Set<LineSegment> resultLines = new HashSet<>();
        mappaSegmenti.forEach((chiave, segmento) -> {
            System.out.print("--> Trovato segmento con equazione: " + chiave + " punti: ");
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

    private String frazRidotta(int numeratore, int denominatore){
        int mcd = MCD(numeratore, denominatore);
        numeratore = numeratore / mcd;
        denominatore = denominatore / mcd;
        return  numeratore + "/" + denominatore;
    }
}// end of class
