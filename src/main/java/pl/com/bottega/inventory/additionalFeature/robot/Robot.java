package pl.com.bottega.inventory.additionalFeature.robot;

public class Robot {
    private Position position;
    private RobotState state;

    public Robot() {
        this.position = new Position(0,0);
        state = new North(this);
    }

    void move(){
        state.move();
    }

    void rotate(){
        state.rotate();
    }


    public void modifyPosition(int x, int y) {
        position.change(x,y);
    }

    public void modifyState(RobotState newState) {
        state = newState;
    }

    private class Position {
        private int xCoordinate;
        private int yCoordinate;

        Position(int xCoordinate, int yCoordinate) {
            this.xCoordinate = xCoordinate;
            this.yCoordinate = yCoordinate;
        }

        void change(int x, int y) {
            xCoordinate+=x;
            yCoordinate+=y;
        }
    }
}
