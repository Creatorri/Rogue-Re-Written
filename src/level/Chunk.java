package level;

import entity.Entity;
import java.util.ArrayList;

/**
 * Part of the world to split it all up
 *
 * @author Creatorri
 */
public class Chunk {

    /**
     * Size of each chunk
     */
    public static final int SIZE = 16;
    /**
     * x and y of the top left corner
     */
    public int x, y;
    /**
     * boolean representing what the world looks like
     */
    public boolean[][] chunk = new boolean[SIZE][SIZE];
    /**
     * Entities in this chunk
     */
    public ArrayList<Entity> entities = new ArrayList<>();

    /**
     * Creates new Chunk
     *
     * @param x
     * @param y
     */
    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates everything in the chunk
     */
    public void update() {
        entities.stream().forEach((e) -> {
            e.update();
        });
    }
}
