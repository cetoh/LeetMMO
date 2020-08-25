package us.toh.leetmmo.datatypes;

public class ExperiencePool {

    private double points = 0;
    private double poolCap = 0;

    public ExperiencePool() {}

    public ExperiencePool(double points, double poolCap) {
        setPoints(points);
        setPoolCap(poolCap);
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void addPoints(double points) {
        this.points += points;
    }

    public double getPoolCap() {
        return poolCap;
    }

    public void setPoolCap(double poolCap) {
        this.poolCap = poolCap;
    }





}
