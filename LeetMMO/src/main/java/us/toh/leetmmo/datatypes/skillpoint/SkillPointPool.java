package us.toh.leetmmo.datatypes.skillpoint;

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

    public void addNumPoints(int points) { this.numPoints += points; }

    public void subtractNumPoints(int points) { this.numPoints -= points; }
}
