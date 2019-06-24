package client;

import moominClasses.Moomin;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

class MoominCircle extends Ellipse2D {
    private Point2D point;

    private static final int SIZE = 80;

    private double width;
    private double height;
    private Color color;
    private Moomin moomin;

    public MoominCircle(Moomin moomin) {
        point = new Point(moomin.getX(), moomin.getY());
        this.width = SIZE;
        this.height = SIZE;
        this.color = new Color((moomin.getOwnerId() * 769) % 256,
                (moomin.getOwnerId() * 513) % 256,
                (moomin.getOwnerId() * 1397) % 256);
        this.moomin = moomin;
    }


    @Override
    public double getX() {
        return point.getX();
    }

    @Override
    public double getY() {
        return point.getY();
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        point.setLocation(x, y);
        this.width = w;
        this.height = h;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        double dx = x - (this.getX() + this.getWidth());
        double dy = y - (this.getY() + this.getHeight());
        double sqr = Math.sqrt(dx * dx + dy * dy);

        return sqr <= this.getWidth();
    }


    @Override
    public boolean contains(Point2D p) {
        return this.contains(p.getX(), p.getY());
    }

    public Color getColor() {
        return color;
    }

    public Moomin getMoomin() {
        return moomin;
    }
}

