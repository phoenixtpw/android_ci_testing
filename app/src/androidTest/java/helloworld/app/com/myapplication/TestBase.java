package helloworld.app.com.myapplication;

import android.app.Activity;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.view.WindowManager;

import org.junit.Before;

/**
 * Serves as a base test that will make sure that the screen is unlocked.
 *
 * @author Greyson Parrelli (keybase.io/greyson)
 */
public abstract class TestBase {

    @Before
    public void setup() {
        // Unlock the screen if it's locked
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        try {
            device.wakeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Set the flags on our activity so it'll appear regardless of lock screen state
        final Activity activity = getActivityRule().getActivity();
        Runnable wakeUpDevice = new Runnable() {
            public void run() {
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                        WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
        activity.runOnUiThread(wakeUpDevice);
    }

    abstract ActivityTestRule getActivityRule();
}
