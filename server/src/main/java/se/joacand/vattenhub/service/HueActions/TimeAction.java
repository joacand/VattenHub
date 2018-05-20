package se.joacand.vattenhub.service.HueActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.joacand.vattenhub.service.IHueService;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Changes the Hue light colors according to the current time
 */
public class TimeAction extends BaseAction implements ILightAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private boolean active = false;

    public TimeAction(IHueService hueService) {
        super("TimeAction", hueService);
    }

    @Override
    public void execute(int[] lights) {
        if (lights.length == 0) {
            logger.info("No lights specified, returning from Time action");
            return;
        }
        logger.info("Executing time action");
        active = true;

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Void> future = executor.submit(() -> {
            startTask(lights);
            return null;
        });
    }

    @Override
    public void cancel() {
        logger.info("Trying to cancel time action");
        active = false;
    }

    public void startTask(int[] lights) {
        logger.info("Starting task for time action");

        // TODO: Move hardcoding of these times to configuration file or take as input
        final int timeBetween = 1000;
        final int transitionTime = 10;
        final int bri = 254;

        HashMap<String, Object> jsonVals = new HashMap<>();
        jsonVals.put("bri", bri);
        jsonVals.put("transitiontime", transitionTime);

        logger.info("Starting while loop");

        while (active) {
            try {
                int hue = calculateHueFromTime();
                int sat = calculateHueFromTime();
                jsonVals.put("hue", hue);
                jsonVals.put("sat", sat);
                logger.info("Setting hue: " + hue);
                this.hueService.sendRaw(jsonVals, lights);
                Thread.sleep(timeBetween);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int calculateHueFromTime() {
        String timeStr = String.valueOf(System.currentTimeMillis());
        int lastSix = Integer.parseInt(timeStr.substring(timeStr.length() - 5));
        double percentage = lastSix / 99999.0;
        int hue = (int) Math.round(percentage * 65535);
        return hue;
    }

}
