package org.youngmonkeys.ls.paint.v1;

import org.youngmonkeys.ls.paint.Position;
import org.youngmonkeys.ls.paint.Size;

public class Paint {

    public void pain(Shape shape) {
        System.out.println("paint: " + shape);
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.pain(
            new Rectangle(
                new Position(1, 2),
                new Size(3, 4)
            )
        );

        paint.pain(
            new Square(
                new Position(1, 2),
                new Size(3, 4)
            )
        );
    }
}
