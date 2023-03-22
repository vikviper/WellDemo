package org.vikviper.welldemo.res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
@Data
@AllArgsConstructor
public class LineSegment {
    HashSet<Point> points;
}
