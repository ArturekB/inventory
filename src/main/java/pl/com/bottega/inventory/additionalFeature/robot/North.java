package pl.com.bottega.inventory.additionalFeature.robot;

class North extends RobotState {

    North(Robot robot) {
        super(robot);
    }

    @Override
    void move() {
        robot.modifyPosition(0,1);
    }

    @Override
    void rotate() {
        robot.modifyState(new East(robot));
    }
}
