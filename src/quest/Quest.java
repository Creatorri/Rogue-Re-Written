package quest;

import entity.item.Item;
import java.util.ArrayList;

/**
 *
 * @author Creatorri
 */
public class Quest {

    public boolean story;
    public int rewardRep;
    public Item[] reward;
    public double rewardXp;
    public static ArrayList<Boolean> done = new ArrayList<>();
    public final int ID;

    public Quest(int id) {
        ID = id;
    }
}
