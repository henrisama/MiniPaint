package Shapes;

import java.awt.*;

abstract class Draw {
    private Point initPoint = null;
    private Point finalPoint = null;

    public Point getInitPoint() {
        return initPoint;
    }

    protected void setInitPoint(Point initPoint) {
        this.initPoint = initPoint;
    }

    public Point getFinalPoint() {
        return finalPoint;
    }

    protected void setFinalPoint(Point finalPoint) {
        this.finalPoint = finalPoint;
    }


    void setPoint(Point initPoint, Point finalPoint){
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

    abstract void setVariableToDraw();
}
