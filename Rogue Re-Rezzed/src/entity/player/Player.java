package entity.player;

import core.Rogue;
import dungeon.Level;
import dungeon.Room;
import entity.RogueEntity;
import entity.item.Gold;
import entity.item.Item;
import entity.mob.RogueHostileEntity;
import entity.npc.RogueNPC;
import render.Sprite;
import ui.GamePlay;
import util.Direction;

/**
 * Hello! This is you!
 * @author Torri
 */
public class Player extends RogueEntity{
    public boolean dead=false;
    public int maxMana=100;//mana=magic points
    public double mana=50;
    public int kills=0;
    boolean attack=false;
    public int currinv=0;
    public double xp=0;
    public int rep=0;
    public int gold=0;
    public int roomnum;
    public Player(Level l1){
        super(l1);
        lvl=1;
        if(inv==null){
            inv = new Item[10];
            for(int i=0;i<inv.length;i++){
                inv[i]=new Item(0,this,0,l);
            }
        }
        for(int k=0;k<inv.length;k++){
            if(inv[k].name.equalsIgnoreCase("empty")){
                currinv=k;
                break;
            }
        }
        name="Player";
        maxhealth=100;
        updateStats();
        mana=maxMana/2;
        health=maxhealth;
        sp = new Sprite("Player");
        roomnum = rand.nextInt(l1.getRooms().length);
        Room r = l1.getRoom(roomnum);
        spawn(r);
    }
    @Override
    public void death(){
        dead=true;
    }
    @Override
    public void turn(){
        l=Rogue.getCurrentLevel();
        if(health<=0){
            dead=true;
        }else if(health<maxhealth){
            health+=0.1*lvl;
        }
        if(mana<maxMana){
            mana+=0.1*lvl;
        }
        if(lvl==0) lvl = 1;
        if(xp/(10*lvl)==1 && xp>1){
            updateStats();
            xp=0;
            lvl++;
            health=maxhealth;
            mana=maxMana;
        }
        for(int i=0;i<inv.length;i++){
            if(inv[i].name.equalsIgnoreCase("Empty")){
                currinv=i;
                break;
            }
        }
        for (int j=0;j<l.getItems().size();j++) {
            Item i = l.getItems().get(j);
            if(i.x==this.x && i.y==this.y && i.id!=0 && inv[currinv]!=null){
                if(i instanceof Gold){
                    gold+=i.id;
                    i.death();
                    continue;
                }
                GamePlay.pickup = i;
            }
        }
        attack=false;
    }
    public void updateStats(){
        maxAtt=2*lvl;
        maxMana=lvl;
        maxhealth=100+2*(lvl-1);
        maxDefence=lvl;
        boolean curr=false;
        for (int i=0;i<inv.length;i++) {
            if(inv[i]!=null){
                inv[i].setParent(this);
                if(inv[i].name.equalsIgnoreCase("EMPTY") && !curr){
                    currinv=i;
                    curr=true;
                }else if(inv[i].equip==true){
                    maxhealth+=inv[i].stats[3];
                    maxMana+=inv[i].stats[2];
                    maxDefence+=inv[i].stats[1];
                    maxAtt+=inv[i].stats[0];
                }
            }
        }
    }
    @Override
    public void move(Direction d){
        int dx=0;
        int dy=0;
        switch(d){
            case UP:
                dy--;
                break;
            case DOWN:
                dy++;
                break;
            case LEFT:
                dx--;
                break;
            case RIGHT:
                dx++;
                break;
            default:
                return;
        }
        for(int i=0;i<l.getEntities().size();i++){
            RogueEntity re = l.getEntities().get(i);
            if(re instanceof RogueNPC  && re.x==this.x+dx && re.y==this.y+dy){
                RogueNPC t = (RogueNPC) re;
                t.action();
            }
            if(re instanceof RogueHostileEntity && re.x==this.x+dx && re.y==this.y+dy){
                re.damage(this);
                if(re.health<=0){
                    kills++;
                    xp+=(re.lvl/lvl);
                    re.death();
                }
                attack=true;
            }
        }
        if(!attack){
            for (int j=0;j<l.getItems().size();j++) {
                Item i = l.getItems().get(j);
                if(i.x==this.x+dx && i.y==this.y+dy && !inv[currinv].equals(i) && inv[currinv]!=null){
                    GamePlay.pickup = i;
                }
            }
        }
        for(RogueEntity re:l.getEntities()){
            if(re != null){
                if(re.x==x+dx && re.y==y+dy){
                    return;
                }
            }
        }
        if(dx+x>=0 && dy+y>=0){
            if(l.board[dx+x][dy+y]==true){
                x+=dx;
                y+=dy;
            }
        }
    }
}
