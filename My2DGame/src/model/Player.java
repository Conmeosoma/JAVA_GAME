package model;

public class Player {
    private int positionX;
    private int positionY;

    public Player() {
    }

    public Player(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void shoot(){
        System.out.println("The player is shooting!");
    }

    @Override
    public String toString() {
        return "Player{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
