package org.youngmonkeys.ls.paint.v2;

import org.youngmonkeys.ls.paint.Position;
import org.youngmonkeys.ls.paint.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Shape {
    private Position position;
    private Size size;

    public boolean isValid() {
        return true;
    }
}
