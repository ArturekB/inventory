package pl.com.bottega.inventory.additionalFeature.robot;

class West extends RobotState {

    West(Robot robot) {
        super(robot);
    }

    @Override
    void move() {
        robot.modifyPosition(-1,0);
    }

    @Override
    void rotate() {
        robot.modifyState(new North(robot));
    }
}
