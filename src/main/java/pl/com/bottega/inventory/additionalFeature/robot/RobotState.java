package pl.com.bottega.inventory.additionalFeature.robot;

abstract class RobotState {

    Robot robot;

    RobotState(Robot robot) {
        this.robot = robot;
    }

    abstract void move();

    abstract void rotate();




}
