package us.toh.leetmmo.datatypes.level;

public class Level {

    private int currentLevel = 0;

    public Level() {

    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void increaseLevel() {
        increaseLevel(1);
    }

    public void increaseLevel(int amount) {
        this.currentLevel += amount;
    }

    public void decreaseLevel() {
        decreaseLevel(1);
    }

    public void decreaseLevel(int amount) {
        this.currentLevel -= amount;
    }
}
