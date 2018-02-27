package pl.com.bottega.inventory.additionalFeature.robot;

class South extends RobotState {

    South(Robot robot) {
        super(robot);
    }

    @Override
    void move() {
        robot.modifyPosition(0,-1);
    }

    @Override
    void rotate() {
        robot.modifyState(new West(robot));
    }
}
