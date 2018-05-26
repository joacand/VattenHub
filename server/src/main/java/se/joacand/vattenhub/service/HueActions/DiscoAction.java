package se.joacand.vattenhub.service.HueActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.joacand.vattenhub.service.HueApiException;
import se.joacand.vattenhub.service.IHueService;

import java.util.Arrays;
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
    private final Random rand = new Random();
    private boolean active = false;

    public DiscoAction(IHueService hueService) {
        super("DiscoAction", hueService);
    }

    @Override
    public void execute(int[] lights) {
        if (lights.length == 0) {
            logger.info("No lights specified, returning from Disco action");
            return;
        }
        logger.info("Executing disco mode for lights " + Arrays.toString(lights));
        active = true;

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Void> future = executor.submit(() -> {
            startTask(lights);
            return null;
        });
    }

    @Override
    public void cancel() {
        logger.info("Trying to cancel disco mode");
        active = false;
    }

    private void startTask(int[] lights) {
        logger.info("Starting task for disco mode for lights " + Integer.toString(lights.length));

        // TODO: Move hardcoding of these times to configuration file or take as input
        final int timeBetween = 300;
        final int transitionTime = 2;
        final int bri = 254;

        HashMap<String, Object> jsonVals = new HashMap<>();
        jsonVals.put("bri", bri);
        jsonVals.put("transitiontime", transitionTime);

        while (active) {
            // Send a random color for each light - lights should not share colors
            for (int light : lights) {
                int hue = rand.nextInt(65535);
                int sat = rand.nextInt(254);
                jsonVals.put("hue", hue);
                jsonVals.put("sat", sat);
                logger.info("Setting hue to " + hue);
                try {
                    this.hueService.sendRaw(jsonVals, new int[]{light});
                } catch (HueApiException e) {
                    logger.info("Exception during hue request " + e.toString());
                }
            }
            try {
                Thread.sleep(timeBetween);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
