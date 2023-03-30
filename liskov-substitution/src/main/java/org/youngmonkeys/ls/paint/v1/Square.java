package org.youngmonkeys.ls.paint.v1;

import org.youngmonkeys.ls.paint.Position;
import org.youngmonkeys.ls.paint.Size;

public class Square extends Shape {
    public Square(Position position, Size size) {
        super(position, size);
        if (size.getWidth() != size.getHeight()) {
            throw new IllegalArgumentException(
                "with must be equal height"
            );
        }
    }
}
