package framework;

public class PathUtils {

    public static void applySleep(int sec)
    {
        try {
            Thread.sleep(sec*1000);
        }

        catch (InterruptedException e) {

        }
    }
}
