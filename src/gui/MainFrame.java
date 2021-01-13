package gui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import tools.ImageIconGetter;
import tools.OnScreenLocation;

public class MainFrame extends javax.swing.JFrame {
    
    private ImageIconGetter iig;
    
    // actualMode:  0-def, 1-add4SR, 2-add3SR, 3-addPC, 4-addLink, 5-del
    private int actualMode;
    
    private HashMap<OnScreenLocation, JDeviceLabel> screenMap;

    public MainFrame() {
        
        this.setTitle("Project Simulation");
        this.setMinimumSize(new Dimension(640, 480));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        iig = new ImageIconGetter();
        screenMap = new HashMap<>();
        initComponents();
        
    }
    
    private JDeviceLabel getJDeviceLabel(OnScreenLocation location) {
        return switch (this.actualMode) {
            case 1 -> new JDeviceLabel("4SR", iig.getIcon("router4.png", 54), location);
            case 2 -> new JDeviceLabel("3SR", iig.getIcon("router3.png", 54), location);
            default -> new JDeviceLabel("PC", iig.getIcon("pc.png", 54), location);
        };
    }
    
    private void setActualMode(int i) {
        if(actualMode != i) {
            this.actualMode = i;
        } else {
            this.actualMode = 0;
            this.simulationScroll.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        String text;
        switch(this.actualMode) {
            case 1 -> {
                text = "Click on an empty space to place the 4-socket router";
                this.simulationScroll.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
            case 2 -> {
                text = "Click on an empty space to place the 3-socket router";
                this.simulationScroll.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
            case 3 -> {
                text = "Click on an empty space to place the PC";
                this.simulationScroll.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
            case 4 -> text = "Click on routers to link them";
            case 5 -> text = "Click on a device to remove it";
            default -> {
                text = "Add devices and links using left bar";
                this.simulationScroll.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
        this.statusLabel.setText(text);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addingPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        simulationScroll = new javax.swing.JScrollPane();
        simulationPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(960, 720));
        setPreferredSize(new java.awt.Dimension(960, 720));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        addingPanel.setBackground(new java.awt.Color(200, 200, 200));
        addingPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addingPanel.setMinimumSize(new java.awt.Dimension(256, 512));
        addingPanel.setPreferredSize(new java.awt.Dimension(192, 512));
        addingPanel.setLayout(new java.awt.GridLayout(4, 1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(iig.getIcon("router4.png", 120));
        jLabel1.setToolTipText("Add 4-socket router");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "4-socket router", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        addingPanel.add(jLabel1);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(iig.getIcon("router3.png", 120));
        jLabel3.setToolTipText("Add 3-socket router");
        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3-socket router", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM));
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });
        addingPanel.add(jLabel3);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(iig.getIcon("pc.png", 120));
        jLabel4.setToolTipText("Add PC");
        jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "PC", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM));
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });
        addingPanel.add(jLabel4);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(iig.getIcon("link.png", 120));
        jLabel5.setToolTipText("Add link");
        jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add link", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM));
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        addingPanel.add(jLabel5);

        getContentPane().add(addingPanel, java.awt.BorderLayout.LINE_START);

        simulationScroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        simulationScroll.setAutoscrolls(true);

        simulationPanel.setBackground(new java.awt.Color(255, 255, 255));
        simulationPanel.setMaximumSize(new java.awt.Dimension(2000, 1000));
        simulationPanel.setPreferredSize(new java.awt.Dimension(2000, 1000));
        simulationPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                simulationPanelMouseClicked(evt);
            }
        });
        simulationPanel.setLayout(null);
        simulationScroll.setViewportView(simulationPanel);

        getContentPane().add(simulationScroll, java.awt.BorderLayout.CENTER);

        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        statusLabel.setText("Add devices and links using left bar");
        statusLabel.setToolTipText("Status bar");
        statusLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        statusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        statusLabel.setMaximumSize(new java.awt.Dimension(304, 28));
        statusLabel.setMinimumSize(new java.awt.Dimension(304, 28));
        statusLabel.setOpaque(true);
        statusLabel.setPreferredSize(new java.awt.Dimension(304, 28));
        getContentPane().add(statusLabel, java.awt.BorderLayout.PAGE_END);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(166, 40));

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("New");
        jMenuItem1.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Open");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator4);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Save");
        jMenuItem3.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenu1.add(jMenuItem3);
        jMenu1.add(jSeparator3);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem5.setText("Exit");
        jMenuItem5.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Edit");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem10.setText("Add 4-socket router");
        jMenuItem10.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem11.setText("Add 3-socket router");
        jMenuItem11.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem11);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem15.setText("Add PC");
        jMenuItem15.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem16.setText("Add link");
        jMenuItem16.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);
        jMenu3.add(jSeparator2);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem12.setText("Delete");
        jMenuItem12.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Info");

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem14.setText("Credits");
        jMenuItem14.setPreferredSize(new java.awt.Dimension(220, 30));
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // add link
    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        this.setActualMode(4);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    // adding 4-socket router
    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        this.setActualMode(1);
    }//GEN-LAST:event_jLabel1MousePressed
    
    // adding device on screen
    private void simulationPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simulationPanelMouseClicked
        if(this.actualMode != 0) {
            if(this.actualMode < 4) {
                OnScreenLocation osl = new OnScreenLocation(evt.getX(), evt.getY());
                if(!this.screenMap.containsKey(osl)) {
                    JDeviceLabel jdl = this.getJDeviceLabel(osl);
                    this.screenMap.put(osl, jdl);
                    this.simulationPanel.add(jdl);
                    this.simulationPanel.revalidate();
                    this.simulationPanel.repaint();
                }
            }
            
            this.simulationScroll.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            this.setActualMode(0);
        }
    }//GEN-LAST:event_simulationPanelMouseClicked
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setActualMode(0);
        }
    }//GEN-LAST:event_formKeyPressed

    // adding 4-socket router
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        this.setActualMode(1);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    // adding 3-socket router
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        this.setActualMode(2);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    // adding PC
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        this.setActualMode(3);
    }//GEN-LAST:event_jMenuItem15ActionPerformed
    
    // delete
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        this.setActualMode(5);
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    
    // add 3SR
    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        this.setActualMode(2);
    }//GEN-LAST:event_jLabel3MousePressed
    
    // add PC
    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
        this.setActualMode(3);
    }//GEN-LAST:event_jLabel4MousePressed

    // add link
    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        this.setActualMode(4);
    }//GEN-LAST:event_jLabel5MousePressed
//
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addingPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPanel simulationPanel;
    private javax.swing.JScrollPane simulationScroll;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
