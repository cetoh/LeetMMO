package us.toh.leetmmo.datatypes;

public class SkillPointPool {
    private int numPoints = 0;

    public SkillPointPool() {

    }

    public SkillPointPool(int numPoints) {
        this.numPoints = numPoints;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }
}
