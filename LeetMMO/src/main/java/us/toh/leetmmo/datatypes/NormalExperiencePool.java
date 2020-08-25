package us.toh.leetmmo.datatypes;

public class NormalExperiencePool extends ExperiencePool {

    public NormalExperiencePool() {}

    public NormalExperiencePool(double points, double poolCap) {
        this.setPoints(points);
        this.setPoolCap(poolCap);
    }

}
