package Shapes;

import java.awt.*;

abstract class Draw {
    private Point initPoint = null;
    private Point finalPoint = null;

    public Point getInitPoint() {
        return initPoint;
    }

    protected    void setInitPoint(Point initPoint) {
        this.initPoint = initPoint;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    protected void setFinalPoint(Point finalPoint) {
        this.finalPoint = finalPoint;
    }



}
