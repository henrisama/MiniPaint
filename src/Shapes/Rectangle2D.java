package Shapes;

import java.awt.*;

public class Rectangle2D extends Draw implements SetToDraw {
    private double width;
    private double heigth;

    public Rectangle2D(Point initPoint, Point finalPoint){
        setPoint(initPoint, finalPoint);
        setVariableToDraw();
    }

    public double getWidth() {
        return width;
    }

    private void setWidth(double width) {
        this.width = width;
    }

    public double getHeigth() {
        return heigth;
    }

    private void setHeigth(double heigth) {
        this.heigth = heigth;
    }


    @Override
    public void setVariableToDraw() {
        setWidth(getFinalPoint().x - getInitPoint().x);
        setHeigth(getFinalPoint().y - getInitPoint().y);
    }

    @Override
    public void setPoint(Point initPoint, Point finalPoint) {
        if(initPoint.x > finalPoint.x && initPoint.y > finalPoint.y){
            setInitPoint(finalPoint.getLocation());
            setFinalPoint(initPoint.getLocation());
        }
        else if(initPoint.x < finalPoint.x && initPoint.y < finalPoint.y){
            setInitPoint(initPoint.getLocation());
            setFinalPoint(finalPoint.getLocation());
        }
        else{
            Point point1 = new Point((int) initPoint.getX(), (int) finalPoint.getY());
            Point point2 = new Point((int) finalPoint.getX(), (int) initPoint.getY());
            if(initPoint.x < finalPoint.x && initPoint.y > finalPoint.y){
                setInitPoint(point1.getLocation());
                setFinalPoint(point2.getLocation());
            }
            else if(initPoint.x > finalPoint.x && initPoint.y < finalPoint.y){
                setInitPoint(point2.getLocation());
                setFinalPoint(point1.getLocation());
            }
        }
    }
}
