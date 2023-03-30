package org.youngmonkeys.ls.paint.v1;

import org.youngmonkeys.ls.paint.Position;
import org.youngmonkeys.ls.paint.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Shape {
    private Position position;
    private Size size;
}
