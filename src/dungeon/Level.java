package dungeon;

import entity.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * The world
 *
 * @author Torri
 */
public class Level {

    /**
     * Player List
     */
    public ArrayList<Player> p;
    /**
     * Size of the level (should be multiple of Chunk.SIZE)
     */
    public static int SIZE = 10 * Chunk.SIZE;
    /**
     * Sprite size of this level 0=text, 16=16x16, 32=32x32, 48=48x48
     */
    public int RENDERLEVEL = 0;
    /**
     * Defines the number of rooms in the level
     */
    public static int NUMROOMS = 200;
    /**
     * length(x)/width(y) of rooms
     */
    public static double ROOMRATIO = 1 / 2;
    /**
     * Size of the room
     */
    public static int ROOMSIZE = 10;
    /**
     * list of chunks
     */
    private final ArrayList<Chunk> chunks = new ArrayList<>();
    /**
     * Allows for random number generation
     */
    public final Random rand = new Random();
    /**
     * Level Number
     */
    public static int NUMLEVELS = 1;

    /**
     * Creates new level
     *
     * @param turn
     */
    public Level(boolean turn) {
        NUMLEVELS++;
        RENDERLEVEL = Math.round(NUMLEVELS / 5) * 16 <= 48 ? Math.round(NUMLEVELS / 5) * 16 : 48;
        generateLevel(NUMLEVELS);
    }

    /**
     * Creates a level
     *
     * @param turn
     * @param render render level
     */
    public Level(boolean turn, int render) {
        NUMLEVELS++;
        RENDERLEVEL = render;
        generateLevel(NUMLEVELS);
    }

    /**
     * VERY IMPORTANT TO MAKE THE GAME REPLAYABLE
     */
    public static void reset() {
        NUMLEVELS = 0;
        NUMROOMS = 200;
        SIZE = 160;
        ROOMRATIO = 1 / 2;
    }

    /**
     * Builds level chunk by chunk Chunks added like so: x+y*width
     *
     * @param levelnum
     * @param endless
     */
    private void generateLevel(int levelnum) {
        for (int i = 0; i < SIZE; i += Chunk.SIZE) {
            for (int j = 0; j < SIZE; j += Chunk.SIZE) {
                chunks.add(new Chunk(i, j));
            }
        }
        buildRooms();

    }

    /**
     * Creates rooms and adds them to the chunks
     */
    private void buildRooms() {
        int x, y, sx, sy;
        for (int i = 0; i < NUMROOMS; i++) {
            sx = rand.nextInt(ROOMSIZE);
            sy = (int) (sx * ROOMRATIO);
            x = rand.nextInt(SIZE - sx - 2) + 1;
            y = rand.nextInt(SIZE - sy - 2) + 1;
            for (int xx = x; xx < sx + x; xx++) {
                for (int yy = y; yy < sy + y; y++) {
                    getChunk(xx, yy).chunk[xx % Chunk.SIZE][yy % Chunk.SIZE] = true;
                }
            }
        }
    }

    /**
     * Populates the world
     *
     * @param lvl
     */
    public void populate(int lvl) {

    }

    /**
     * Gets Chunk containing x and y
     *
     * @param x
     * @param y
     * @return Containing Chunk
     */
    public Chunk getChunk(double x, double y) {
        int index = (int) (((x - (x % Chunk.SIZE)) / SIZE) + ((y - (y % Chunk.SIZE)) / SIZE));
        return chunks.get(index);
    }

    /**
     * Checks if a point is solid
     *
     * @param x
     * @param y
     * @return solid ? true : false
     */
    public boolean solid(double x, double y) {
        int cx = (int) (x % Chunk.SIZE), cy = (int) (y % Chunk.SIZE);
        return getChunk(x, y).chunk[cx][cy];
    }

    /**
     *
     */
    public Player getPlayer(int id) {
        return p.get(id);
    }
}
