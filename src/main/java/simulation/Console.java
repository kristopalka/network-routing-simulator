package simulation;

public class Console extends javax.swing.JFrame {

    public Console(String routerName) {
        initComponents();
        this.setTitle(routerName);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outputPane = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        inputTextField = new javax.swing.JTextField();
        enterButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        closeItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        copyOutputItem = new javax.swing.JMenuItem();
        increaseFontItem = new javax.swing.JMenuItem();
        decreaseFontItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        showItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Router");
        setMinimumSize(new java.awt.Dimension(320, 240));
        setPreferredSize(new java.awt.Dimension(640, 480));
        setSize(new java.awt.Dimension(640, 480));

        jPanel1.setLayout(new java.awt.BorderLayout());

        outputPane.setEditable(false);
        jScrollPane1.setViewportView(outputPane);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 50));

        enterButton.setText("Enter");
        enterButton.setToolTipText("Enter");
        enterButton.setMaximumSize(new java.awt.Dimension(50, 35));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(inputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputTextField)
                    .addComponent(enterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("File");

        closeItem.setText("Close");
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        jMenu1.add(closeItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        copyOutputItem.setText("Copy output");
        copyOutputItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyOutputItemActionPerformed(evt);
            }
        });
        jMenu2.add(copyOutputItem);

        increaseFontItem.setText("Increase font size");
        increaseFontItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseFontItemActionPerformed(evt);
            }
        });
        jMenu2.add(increaseFontItem);

        decreaseFontItem.setText("Decrease font size");
        jMenu2.add(decreaseFontItem);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Info");

        showItem.setText("Show status");
        showItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showItemActionPerformed(evt);
            }
        });
        jMenu3.add(showItem);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void copyOutputItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyOutputItemActionPerformed
        
    }//GEN-LAST:event_copyOutputItemActionPerformed

    private void showItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showItemActionPerformed

    private void increaseFontItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseFontItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseFontItemActionPerformed

    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeItemActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_closeItemActionPerformed

//    public static void main(String args[]) {
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Console().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem closeItem;
    private javax.swing.JMenuItem copyOutputItem;
    private javax.swing.JMenuItem decreaseFontItem;
    private javax.swing.JButton enterButton;
    private javax.swing.JMenuItem increaseFontItem;
    private javax.swing.JTextField inputTextField;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane outputPane;
    private javax.swing.JMenuItem showItem;
    // End of variables declaration//GEN-END:variables
}
