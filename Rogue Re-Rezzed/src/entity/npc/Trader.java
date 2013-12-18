
package entity.npc;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.item.Item;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import util.Direction;

/**
 *
 * @author Creatorri
 */
public class Trader extends RogueNPC{
    private final String[] dialogue = {"Hello Adventurer!","Looking for something?","Need a hand?","Hey dawg whaddup?","Hello Stranger!","Mortuus Trabajos owning you?","You saw the crystal? LOL its NYI"};
    public final int[] prices;
    public int[][] buttons = new int[3][2];
    public BufferedImage img = new BufferedImage(256,256,BufferedImage.TYPE_4BYTE_ABGR);
    public boolean trade=false;
    public Trader(Room r,Level l1) {
        super(r,l1);
        this.prices = new int[3];
        lvl=Rogue.numLevels;
        maxhealth=10000;
        health=maxhealth;
        inv=new Item[3];
        for(int i=0;i<inv.length;i++){
            inv[i]=new Item(rand.nextInt(Item.numid),this,rand.nextInt((lvl)*10-1)+1,l1);
            prices[i]=inv[i].id+inv[i].lvl;
        }
        int say = rand.nextInt(dialogue.length);
        img=la.createBufferedImage("Dialogue"+Level.renderlevel+".png", 256, 256);
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256/2-width/2, 40);
        width = g.getFontMetrics().stringWidth("Here is what I have:");
        g.drawString("Here is what I have:", 256/2-width/2, 60);
        for(int i=0;i<inv.length;i++){
            g.drawString(inv[i].name+" for "+prices[i]+" gold", 0, (i*30)+80);
            buttons[i][0]=2;
            buttons[i][1]=(i*30)+80;
        }
        name="Trader";
        g.dispose();
    }
    @Override
    public void action(){
        trade=true;
    }
    @Override
    public void turn(){
        Direction pdir;
        boolean b = rand.nextBoolean();
        int d = rand.nextInt(3);
        if(b){
            switch (d){
                case 0:
                    pdir=Direction.UP;
                    break;
                case 1:
                    pdir=Direction.DOWN;
                    break;
                case 2:
                    pdir=Direction.LEFT;
                    break;
                case 3:
                    pdir=Direction.RIGHT;
                    break;
                default:
                    pdir=Direction.STOP;
                    break;
            }
        }else{
            pdir=Direction.STOP;
        }
        if(distTo(l.getPlayer())>1){
            trade=false;
        }
        move(pdir);
    }
    public void refreshTrade(){
        int say = rand.nextInt(dialogue.length);
        img=la.createBufferedImage("Dialogue"+Level.renderlevel+".png", 256, 256);
        Graphics2D g = img.createGraphics();
        int width = g.getFontMetrics().stringWidth(dialogue[say]);
        g.setColor(Color.BLACK);
        g.drawString(dialogue[say], 256/2-width/2, 40);
        width = g.getFontMetrics().stringWidth("Here is what I have:");
        g.drawString("Here is what I have:", 256/2-width/2, 60);
        for(int i=0;i<inv.length;i++){
            prices[i]=inv[i].id+inv[i].lvl;
            g.drawString(inv[i].name+" for "+prices[i]+" gold", 0, (i*30)+80);
            buttons[i][0]=2;
            buttons[i][1]=(i*30)+80;
        }
        g.dispose();
    }
}
