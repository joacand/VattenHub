package se.joacand.vattenhub.service.HueActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.joacand.vattenhub.domain.LightState;
import se.joacand.vattenhub.service.IHueService;

/**
 * Changes the Hue light colors randomly in a disco fashion according to the
 * specified parameters
 */
public class DiscoAction extends BaseAction implements ILightAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DiscoAction(IHueService hueService) {
        super("DiscoAction", hueService);
    }

    @Override
    public void execute() {
        // TODO: Remove hardcoded light 3
        // TODO: Remove hardcoded numberOfTimes
        int numberOfTimes = 10;
        logger.info("Executing disco mode {0} times", numberOfTimes);

        LightState lsOn = new LightState();
        lsOn.setLight(3);
        lsOn.setOn(true);

        LightState lsOff = new LightState();
        lsOff.setLight(3);
        lsOff.setOn(false);

        for (int i = 0; i < numberOfTimes; i++) {
            try {
                this.hueService.changeState(lsOn);
                Thread.sleep(1000);
                this.hueService.changeState(lsOff);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
