/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

/**
 *
 * @author Razim
 */
public class DebugMenu extends javax.swing.JFrame {

    /**
     * Creates new form DebugMenu
     */
    public DebugMenu() {
        this.setVisible(true);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        chooseEntitySpawn = new javax.swing.JComboBox();
        chooseLvl = new javax.swing.JTextField();
        chooseLvlBtn = new javax.swing.JButton();
        chooseEntitySpawnBtn = new javax.swing.JButton();
        chooseItemSpawn = new javax.swing.JComboBox();
        chooseItemSpawnBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Hobo Std", 0, 48)); // NOI18N
        jLabel2.setText("Debug Menu");

        chooseEntitySpawn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Snake", "Bandit", "Fish", "Mortuus Trabajos", "Quatopularslith", "Bag", "Stairway" }));

        chooseLvl.setText("Insert lvl #");

        chooseLvlBtn.setText("Generate");

        chooseEntitySpawnBtn.setText("Spawn Entity");

        chooseItemSpawn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Broken Stick", "Axe", "Magical Axe", "Pickaxe", "Magical Pickaxe" }));

        chooseItemSpawnBtn.setText("Spawn Item");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(268, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chooseEntitySpawnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseEntitySpawn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooseItemSpawn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseItemSpawnBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chooseLvl)
                    .addComponent(chooseLvlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addContainerGap(180, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseEntitySpawn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseLvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseItemSpawn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseEntitySpawnBtn)
                    .addComponent(chooseLvlBtn)
                    .addComponent(chooseItemSpawnBtn))
                .addContainerGap(291, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox chooseEntitySpawn;
    public javax.swing.JButton chooseEntitySpawnBtn;
    public javax.swing.JComboBox chooseItemSpawn;
    public javax.swing.JButton chooseItemSpawnBtn;
    public javax.swing.JTextField chooseLvl;
    public javax.swing.JButton chooseLvlBtn;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}