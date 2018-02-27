package pl.com.bottega.inventory.additionalFeature.robot;

class East extends RobotState {

    East(Robot robot) {
        super(robot);
    }

    @Override
    void move() {
        robot.modifyPosition(1,0);
    }

    @Override
    void rotate() {
        robot.modifyState(new South(robot));
    }
}
