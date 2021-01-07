package simulation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Console extends javax.swing.JFrame {
    
    private String ROUTER_NAME;
    private String ACTUAL_POSITION;
    private Queue<String> inputQueue;

    public Console(String routerName) {
        this.inputQueue = new LinkedList<>();
        initComponents();
        this.ROUTER_NAME = routerName;
        this.setTitle(this.ROUTER_NAME);
        this.ACTUAL_POSITION = "";
        this.printInfo(routerName + " has started.\n");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
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

        scrollPane.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollPaneMouseWheelMoved(evt);
            }
        });

        outputPane.setEditable(false);
        outputPane.setFont(new java.awt.Font("Noto Mono", 0, 15)); // NOI18N
        scrollPane.setViewportView(outputPane);

        jPanel1.add(scrollPane, java.awt.BorderLayout.CENTER);

        jPanel3.setMinimumSize(new java.awt.Dimension(100, 50));
        jPanel3.setName(""); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 50));

        inputTextField.setFont(new java.awt.Font("Noto Mono", 0, 18)); // NOI18N
        inputTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inputTextFieldKeyPressed(evt);
            }
        });

        enterButton.setText("Enter");
        enterButton.setToolTipText("Enter");
        enterButton.setMaximumSize(new java.awt.Dimension(50, 35));
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(inputTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
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

        jMenuBar1.setPreferredSize(new java.awt.Dimension(123, 40));

        jMenu1.setText("File");
        jMenu1.setPreferredSize(new java.awt.Dimension(40, 30));

        closeItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        closeItem.setText("Hide console");
        closeItem.setPreferredSize(new java.awt.Dimension(220, 30));
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        jMenu1.add(closeItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setPreferredSize(new java.awt.Dimension(40, 30));

        copyOutputItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        copyOutputItem.setText("Copy output");
        copyOutputItem.setPreferredSize(new java.awt.Dimension(220, 30));
        copyOutputItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyOutputItemActionPerformed(evt);
            }
        });
        jMenu2.add(copyOutputItem);

        increaseFontItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_EQUALS, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        increaseFontItem.setText("Increase font size");
        increaseFontItem.setPreferredSize(new java.awt.Dimension(220, 30));
        increaseFontItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseFontItemActionPerformed(evt);
            }
        });
        jMenu2.add(increaseFontItem);

        decreaseFontItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_MINUS, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        decreaseFontItem.setText("Decrease font size");
        decreaseFontItem.setPreferredSize(new java.awt.Dimension(220, 30));
        decreaseFontItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseFontItemActionPerformed(evt);
            }
        });
        jMenu2.add(decreaseFontItem);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Info");
        jMenu3.setPreferredSize(new java.awt.Dimension(40, 30));

        showItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        showItem.setText("Show info");
        showItem.setPreferredSize(new java.awt.Dimension(220, 30));
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void copyOutputItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyOutputItemActionPerformed
        Toolkit.getDefaultToolkit()
        .getSystemClipboard()
        .setContents(
                new StringSelection(this.outputPane.getText()),
                null
        );
    }//GEN-LAST:event_copyOutputItemActionPerformed

    private void showItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showItemActionPerformed
        // TO DO
    }//GEN-LAST:event_showItemActionPerformed

    private void increaseFontItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseFontItemActionPerformed
        this.increaseFont();
    }//GEN-LAST:event_increaseFontItemActionPerformed

    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeItemActionPerformed

    private void decreaseFontItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseFontItemActionPerformed
        this.decreaseFont();
    }//GEN-LAST:event_decreaseFontItemActionPerformed

    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        this.enterInput();
    }//GEN-LAST:event_enterButtonActionPerformed

    private void inputTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTextFieldKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.enterInput();
        }
    }//GEN-LAST:event_inputTextFieldKeyPressed

    private void scrollPaneMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollPaneMouseWheelMoved
        if(evt.isControlDown()) {
            if(evt.getWheelRotation() > 0) {
            this.decreaseFont();
            }
            else {
                this.increaseFont();
            }
        }
    }//GEN-LAST:event_scrollPaneMouseWheelMoved

    public String getInputAction() throws NullPointerException {
        return this.inputQueue.remove();
    }
    
    private void enterInput() {
        String inputText = this.inputTextField.getText();
        this.printInput(inputText);
        this.inputTextField.setText("");
        this.inputQueue.add(inputText);
    }
    
    private void printInfo(String text) {
        SimpleAttributeSet infoSAS = new SimpleAttributeSet(); 
        StyleConstants.setForeground(infoSAS, Color.MAGENTA);
        
        String output1 = "\n--- INFO ---\n" + text;
        try {
            this.outputPane.getDocument().insertString(this.outputPane.getDocument().getLength(), output1, infoSAS);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void printWarning(String text) {
        SimpleAttributeSet warningSAS = new SimpleAttributeSet(); 
        StyleConstants.setForeground(warningSAS, Color.cyan);
        
        String output1 = "\n*** WARNING ***\n" + text;
        try {
            this.outputPane.getDocument().insertString(this.outputPane.getDocument().getLength(), output1, warningSAS);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void printError(String text) {
        SimpleAttributeSet errorSAS = new SimpleAttributeSet(); 
        StyleConstants.setForeground(errorSAS, Color.red);
        
        String output1 = "\n!!! ERROR !!!\n" + text;
        try {
            this.outputPane.getDocument().insertString(this.outputPane.getDocument().getLength(), output1, errorSAS);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void printInput(String text) {
        SimpleAttributeSet inputSAS = new SimpleAttributeSet(); 
        StyleConstants.setBold(inputSAS, true);
        
        String output1 = "\n" + this.ROUTER_NAME + "# ";
        try {
            this.outputPane.getDocument().insertString(this.outputPane.getDocument().getLength(), output1, inputSAS);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        try {
            this.outputPane.getDocument().insertString(this.outputPane.getDocument().getLength(), text, null);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void printLine(String line) {
        try {
            this.outputPane.getDocument().insertString(this.outputPane.getDocument().getLength(),"\n" + line, null);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void increaseFont() {
        Font f = this.outputPane.getFont();
        if(f.getSize() + 2 < 30) {
            this.outputPane.setFont(new Font(f.getFontName(), f.getStyle(), f.getSize() + 2));
        }
    }
    
    private void decreaseFont() {
        Font f = this.outputPane.getFont();
        if(f.getSize() - 2 > 6) {
            this.outputPane.setFont(new Font(f.getFontName(), f.getStyle(), f.getSize() - 2));
        }
    }

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
    private javax.swing.JTextPane outputPane;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JMenuItem showItem;
    // End of variables declaration//GEN-END:variables
}
