package org.vikviper.welldemo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vikviper.welldemo.res.LineSegment;
import org.vikviper.welldemo.res.Point;
import org.vikviper.welldemo.res.Space;
import org.vikviper.welldemo.svc.BruteSolver;

import java.util.Set;

@RestController
public class DemoController {
    @Autowired
    BruteSolver bruteSolver;

    @Autowired
    Space space;


    @PostMapping("/point")
    public String addPoint(Point pt2Add){
        space.getPoints().add(pt2Add);
        return "OK";
    }

    @GetMapping("/space")
    public Set<Point> getAllPoints(){
       return space.getPoints();
    }

    @GetMapping("/lines/{n}")
    public Set<LineSegment> getLines(@PathVariable int n){
        return bruteSolver.getLinesWithNPoints(n);
    }

    @DeleteMapping("space")
    public String deletePoints(){
        space.getPoints().clear();
        return "OK";
    }

    @PostMapping("/points")
    public String addPoints(){
        space.getPoints().add(new Point(2,2));
        space.getPoints().add(new Point(1,1));
        space.getPoints().add(new Point(1,2));
        space.getPoints().add(new Point(1,3));
        space.getPoints().add(new Point(1,4));
        space.getPoints().add(new Point(0,0));
        space.getPoints().add(new Point(3,4));
        space.getPoints().add(new Point(0,5));
        space.getPoints().add(new Point(1,5));
        space.getPoints().add(new Point(2,5));
        space.getPoints().add(new Point(3,5));

        return "OK";
    }
}



