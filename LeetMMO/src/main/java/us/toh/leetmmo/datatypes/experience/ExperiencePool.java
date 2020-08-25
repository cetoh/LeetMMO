package us.toh.leetmmo.datatypes.experience;

public class ExperiencePool {

    private double points = 0;
    private double poolCap = 0;

    private double carry = 0;

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

        if (getPoints() >= getPoolCap()) {
            setCarry(getPoints() - getPoolCap());
        }
    }

    public double getPoolCap() {
        return poolCap;
    }

    public void setPoolCap(double poolCap) {
        this.poolCap = poolCap;
    }

    public double getCarry() {
        return carry;
    }

    public void setCarry(double carry) {
        this.carry = carry;
    }
}
