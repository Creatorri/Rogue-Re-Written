
package input;

import core.Rogue;
import dungeon.Level;
import entity.item.Item;
import entity.player.Player;
import javax.swing.JPanel;
import ui.GamePlay;

/**
 * listener for MButtons
 * @author Torri
 */
public class MButtonInput {
    public static boolean backtogame;
    public void clicked(String command){
        if(command.equalsIgnoreCase("New Game")){
            Player.pinv = null;
            Player.xplevels = 1;
            Player.xp = 0;
            Player.kills = 0;
            Level.numLevels=0;
            Rogue.setLevel(new Level(1));
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(false);
            Rogue.mm.gp.setVisible(true);
            Rogue.mm.gp.update();
        }
        if(command.equalsIgnoreCase("Options")){
            Rogue.mm.mmp.setVisible(false);
            Rogue.mm.omp.setVisible(true);
            backtogame=false;
        }
        if(command.equalsIgnoreCase("Quit")){
            Rogue.mm.dispose();
        }
        if(command.equalsIgnoreCase("pick up")){
            if(GamePlay.pickup!=null){
                GamePlay.pickup.setParent(Rogue.getLevel().getPlayer());
                Player.pinv[Player.currinv]=GamePlay.pickup;
                GamePlay.pickup.death();
                Rogue.mm.gp.update();
            }
            refresh(Rogue.mm.gp);
        }
        if(command.equalsIgnoreCase("leave it")){
            GamePlay.pickup=null;
            Rogue.mm.gp.update();
            refresh(Rogue.mm.gp);
        }
        if(command.equalsIgnoreCase("Return")){
            Rogue.mm.mmp.setVisible(true);
            Rogue.mm.sm.setVisible(false);
        }
        for(int i=0;i<Rogue.mm.gp.equip.length;i++){
            if(command.equalsIgnoreCase("Drop"+i)){
                Player.pinv[i].drop();
                Player.pinv[i]=new Item(0,Rogue.getLevel().getPlayer(),0,Rogue.getLevel());
                Rogue.getLevel().getPlayer().updateStats();
                Rogue.mm.gp.update();
            }
            if(command.equalsIgnoreCase("Equip"+i)){
                Player.pinv[i].equip=true;
                Rogue.getLevel().getPlayer().updateStats();
                Rogue.mm.gp.update();
            }
        }
    }
    private void refresh(JPanel p){
        p.setVisible(false);
        p.setVisible(true);
    }
}