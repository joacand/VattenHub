package se.joacand.vattenhub.service.HueActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.joacand.vattenhub.service.IHueService;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Changes the Hue light colors randomly in a disco fashion according to the
 * specified parameters
 */
public class DiscoAction extends BaseAction implements ILightAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Random rand = new Random();
    private boolean active = false;

    public DiscoAction(IHueService hueService) {
        super("DiscoAction", hueService);
    }

    @Override
    public void execute() {
        logger.info("Executing disco mode");
        active = true;

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Void> future = executor.submit(() -> {
            startTask();
            return null;
        });
    }

    @Override
    public void cancel() {
        logger.info("Trying to cancel disco mode");
        active = false;
    }

    public void startTask() {
        logger.info("Starting task for disco mode");

        // TODO: Remove hardcoding of these times
        int timeBetween = 500;
        int transitionTime = 5;
        int bri = 254;
        int sat = 254;

        HashMap<String, Object> jsonVals = new HashMap<>();
        jsonVals.put("bri", bri);
        jsonVals.put("sat", sat);
        jsonVals.put("transitiontime", transitionTime);

        // TODO: Remove hardcoded light 3
        int[] lights = new int[]{3};

        while (active) {
            try {
                int hue = rand.nextInt(65535);
                jsonVals.put("hue", hue);
                logger.info("Setting hue to " + hue);
                this.hueService.sendRaw(jsonVals, lights);
                Thread.sleep(timeBetween);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
