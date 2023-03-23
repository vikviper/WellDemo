package org.vikviper.welldemo.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Data
@AllArgsConstructor
@Builder
public class Space {
    HashSet<Point> points;
}


