package org.vikviper.welldemo.svc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.vikviper.welldemo.res.LineSegment;
import org.vikviper.welldemo.res.Point;
import org.vikviper.welldemo.res.Space;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
class BruteSolverTest {

    @Autowired
    Space testSpace;

    @Autowired
    BruteSolver bruteSolver;

    @BeforeEach
    void clearSpace(){
        testSpace.getPoints().clear();
    }

    @Test
    void getLinesWithNPoints_3_rette_minimo_3_4_5_punti() {
        Point pointA = new Point(2,2);
        testSpace.getPoints().add(pointA);
        Point pointB = new Point(1,1);
        testSpace.getPoints().add(pointB);
        Point pointC = new Point(1,2);
        testSpace.getPoints().add(pointC);
        Point pointD = new Point(1,3);
        testSpace.getPoints().add(pointD);
        Point pointE = new Point(1,4);
        testSpace.getPoints().add(pointE);
        Point pointF = new Point(0,0);
        testSpace.getPoints().add(pointF);
        Point pointG = new Point(3,4);
        testSpace.getPoints().add(pointG);
        Point pointH = new Point(0,5);
        testSpace.getPoints().add(pointH);
        Point pointI = new Point(1,5);
        testSpace.getPoints().add(pointI);
        Point pointJ = new Point(2,5);
        testSpace.getPoints().add(pointJ);
        Point pointK = new Point(3,5);
        testSpace.getPoints().add(pointK);

        LineSegment expectedLS1 = new LineSegment(new HashSet<>());
        expectedLS1.getPoints().add(pointA);
        expectedLS1.getPoints().add(pointB);
        expectedLS1.getPoints().add(pointF);

        LineSegment expectedLS2 = new LineSegment(new HashSet<>());
        expectedLS2.getPoints().add(pointH);
        expectedLS2.getPoints().add(pointI);
        expectedLS2.getPoints().add(pointJ);
        expectedLS2.getPoints().add(pointK);

        LineSegment expectedLS3 = new LineSegment(new HashSet<>());
        expectedLS3.getPoints().add(pointB);
        expectedLS3.getPoints().add(pointC);
        expectedLS3.getPoints().add(pointD);
        expectedLS3.getPoints().add(pointE);
        expectedLS3.getPoints().add(pointI);

        Set<LineSegment> result3 = bruteSolver.getLinesWithNPoints(3);
        assertEquals(3, result3.size());
        assertTrue(result3.contains(expectedLS1));
        assertTrue(result3.contains(expectedLS2));
        assertTrue(result3.contains(expectedLS3));

        Set<LineSegment> result4 = bruteSolver.getLinesWithNPoints(4);
        assertEquals(2, result4.size());
        assertTrue(result4.contains(expectedLS2));
        assertTrue(result4.contains(expectedLS3));

        Set<LineSegment> result5 = bruteSolver.getLinesWithNPoints(5);
        assertEquals(1, result5.size());
        assertTrue(result5.contains(expectedLS3));
    }

    @Test
    void getLinesWithNPoints_1_retta_min_3_punti() {
        Point pointA = new Point(-2,-1);
        testSpace.getPoints().add(pointA);
        Point pointB = new Point(2,1);
        testSpace.getPoints().add(pointB);
        Point pointC = new Point(3,3);
        testSpace.getPoints().add(pointC);
        Point pointD = new Point(4,2);
        testSpace.getPoints().add(pointD);

        LineSegment expectedLS = new LineSegment(new HashSet<>());
        expectedLS.getPoints().add(pointA);
        expectedLS.getPoints().add(pointB);
        expectedLS.getPoints().add(pointD);

        Set<LineSegment> result = bruteSolver.getLinesWithNPoints(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(expectedLS));
    }

    @Test
    void getLinesWithNPoints_1_retta_verticale() {
        Point pointA = new Point(-2,-1);
        testSpace.getPoints().add(pointA);
        Point pointB = new Point(5,1);
        testSpace.getPoints().add(pointB);
        Point pointC = new Point(5,3);
        testSpace.getPoints().add(pointC);
        Point pointD = new Point(5,-2);
        testSpace.getPoints().add(pointD);

        LineSegment expectedLS = new LineSegment(new HashSet<>());
        expectedLS.getPoints().add(pointC);
        expectedLS.getPoints().add(pointB);
        expectedLS.getPoints().add(pointD);

        Set<LineSegment> result = bruteSolver.getLinesWithNPoints(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(expectedLS));
    }

    @Test
    void getLinesWithNPoints_1_retta_orizzontale() {
        Point pointA = new Point(-2,3);
        testSpace.getPoints().add(pointA);
        Point pointB = new Point(5,1);
        testSpace.getPoints().add(pointB);
        Point pointC = new Point(5,3);
        testSpace.getPoints().add(pointC);
        Point pointD = new Point(0,3);
        testSpace.getPoints().add(pointD);

        LineSegment expectedLS = new LineSegment(new HashSet<>());
        expectedLS.getPoints().add(pointC);
        expectedLS.getPoints().add(pointA);
        expectedLS.getPoints().add(pointD);

        Set<LineSegment> result = bruteSolver.getLinesWithNPoints(3);
        assertEquals(1, result.size());
        assertTrue(result.contains(expectedLS));
    }

}