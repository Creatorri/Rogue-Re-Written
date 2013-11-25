
package render;

import art.LoadArt;
import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Item;
import entity.player.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Torri
 */
public class RenderPanel extends JPanel{
    private Level l = Rogue.getLevel();
    private int offx=0,offy=0;
    LoadArt la = new LoadArt();
    Room[] room = l.getRooms();
    public static Sprite fsp = new Sprite("DungeonFloor1");
    private List<RogueEntity> current = l.getEntities();
    public RenderPanel(){
        l=Rogue.getLevel();
    }
    /**
     * The core updater
     */
    public void update(){
        l=Rogue.getLevel();
        l.getStairWay().turn();
        l.getPlayer().turn();
        for(int i=0;i<l.getEntities().size()-1;i++){
            if(l.getEntities().get(i) != null){
                if(l.getEntities().get(i).health<=0){
                    l.getEntities().get(i).death();
                }else{
                    l.getEntities().get(i).turn();
                }
            }
        }
        Rogue.mm.d.invp.update();
        Rogue.mm.d.s.update();
        Rogue.mm.d.mapp.update();
        repaint();
        if(Player.dead){
            Rogue.mm.mmp.setVisible(true);
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.d.setVisible(false);
            Level.numLevels=0;
        }
    }
    void setReletiveTo(RogueEntity e){
        offx=(getWidth()/2)-e.x*64;
        offy=(getHeight()/2)-e.y*64;
    }
    /**
     * Paints the world
     * @param g 
     */
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        setReletiveTo(l.getPlayer());
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        room = l.getRooms();
        current = l.getEntities();
        g2.drawImage(l.getStairWay().sp.i, l.getStairWay().x*64+offx,l.getStairWay().y*64+offy, this);
        for (Room r1 : room) {
            for (int[][] area0 : r1.area) {
                for (int[] area1 : area0) {
                    g2.drawImage(fsp.i, area1[0]*64+offx,area1[1]*64+offy, this);
                }
            }
        }
        for(Item i:l.getItems()){
            g2.drawImage(i.sp.i, i.x*64+offx+i.ofx,i.y*64+offy+i.ofy, this);
        }
        for (int i=0;i<current.size();i++) {
            if(current.get(i)!=null){
                g2.setColor(Color.RED);
                g2.fillRect(current.get(i).x*64+offx+2, current.get(i).y*64+offy-10, (int) ((current.get(i).health/current.get(i).maxhealth)*61), 20);
                g2.setColor(Color.BLACK);
                g2.drawString("Health:"+(int)current.get(i).health, current.get(i).x*64+offx+3, current.get(i).y*64+offy+4);
                g2.drawImage(current.get(i).sp.i, current.get(i).x*64+offx, current.get(i).y*64+offy, this);
            }
        }
        g2.setColor(Color.RED);
        g2.fillRect(l.getPlayer().x*64+offx+2, l.getPlayer().y*64+offy-10, (int) ((l.getPlayer().health/l.getPlayer().maxhealth)*61), 20);
        g2.setColor(Color.BLACK);
        g2.drawString("Health:"+(int)l.getPlayer().health, l.getPlayer().x*64+offx+3, l.getPlayer().y*64+offy+4);
        g2.drawImage(l.getPlayer().sp.i,l.getPlayer().x*64+offx,l.getPlayer().y*64+offy,this);
    }
}
