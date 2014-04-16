package graphics;

/**
 *
 * @author Creatorri
 */
public class Screen extends Render {

    public Render draw;

    public Screen(int width, int height) {
        super(width, height);
    }

    public void render(int offx, int offy) {
        draw(draw, offx, offy);
    }
}
