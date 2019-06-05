package Shapes;

import java.awt.*;

public class Circle2D extends Draw implements SetToDraw {
    private double radius;
    private double xCentral;
    private double yCentral;

    public Circle2D(Point initPoint, Point finalPoint){
        if(initPoint != finalPoint){
            setPoint(initPoint, finalPoint);
            setVariableToDraw();
        }
    }

    public double getxCentral() {
        return xCentral;
    }

    private void setxCentral(double xCentral) {
        this.xCentral = xCentral;
    }

    public double getyCentral() {
        return yCentral;
    }

    private void setyCentral(double yCentral) {
        this.yCentral = yCentral;
    }

    public double getRadius() {
        return radius;
    }

    private void setRadius(double radius) {
        this.radius = radius;
    }


    @Override
    public void setVariableToDraw() {
        if(getFinalPoint().x - getInitPoint().x < getFinalPoint().y - getInitPoint().y){
            setRadius((getFinalPoint().x - getInitPoint().x)/2);
        }
        else {
            setRadius((getFinalPoint().y - getInitPoint().y)/2);
        }
        setxCentral(getInitPoint().x + ((getFinalPoint().x - getInitPoint().x)/2));
        setyCentral(getInitPoint().y + ((getFinalPoint().y - getInitPoint().y)/2));
    }
}
