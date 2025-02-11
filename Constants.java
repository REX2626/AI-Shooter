import java.util.List;
import java.util.ArrayList;

public class Constants {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 200;

    public static final int FPS = 60;

    public static final int NUM_SHOOTERS = 40;
    public static final int NUM_GENERATIONS = 500;
    public static int GENERATION;

    public static List<Shooter> shooters = new ArrayList<Shooter>();
    public static List<Bullet> bullets = new ArrayList<Bullet>();
}
