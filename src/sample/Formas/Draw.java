package sample.Formas;

import java.awt.*;

abstract class Draw {
    private Point initPoint = null;
    private Point finalPoint = null;

    public Point getInitPoint() {
        return initPoint;
    }

    private void setInitPoint(Point initPoint) {
        this.initPoint = initPoint;
    }


    public Point getFinalPoint() {
        return finalPoint;
    }

    private void setFinalPoint(Point finalPoint) {
        this.finalPoint = finalPoint;
    }


    void setPonto(Point initPoint, Point finalPoint){
        if(initPoint.x > finalPoint.x && initPoint.y > finalPoint.y){
            setInitPoint(finalPoint.getLocation());
            setFinalPoint(initPoint.getLocation());
        }
        else if(initPoint.x < finalPoint.x && initPoint.y < finalPoint.y){
            setInitPoint(initPoint.getLocation());
            setFinalPoint(finalPoint.getLocation());
        }
        else{
            Point ponto1 = new Point((int) initPoint.getX(), (int) finalPoint.getY());
            Point ponto2 = new Point((int) finalPoint.getX(), (int) initPoint.getY());
            if(initPoint.x < finalPoint.x && initPoint.y > finalPoint.y){
                setInitPoint(ponto1.getLocation());
                setFinalPoint(ponto2.getLocation());
            }
            else if(initPoint.x > finalPoint.x && initPoint.y < finalPoint.y){
                setInitPoint(ponto2.getLocation());
                setFinalPoint(ponto1.getLocation());
            }
        }
    }
}
