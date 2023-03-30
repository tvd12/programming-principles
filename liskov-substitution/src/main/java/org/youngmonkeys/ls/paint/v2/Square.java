package org.youngmonkeys.ls.paint.v2;

import org.youngmonkeys.ls.paint.Position;
import org.youngmonkeys.ls.paint.Size;

public class Square extends Shape {
    public Square(Position position, Size size) {
        super(position, size);
    }

    @Override
    public boolean isValid() {
        return getSize().getWidth() ==
               getSize().getHeight();
    }
}
