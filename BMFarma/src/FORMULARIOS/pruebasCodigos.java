/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIOS;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import PROTOTIPOS.ManejadorFechas;
import java.sql.Date;
import GENERICOS.LimpiarTextosPanel;
import GENERICOS.ValidarVacios;

/**
 *
 * @author blasm
 */
public class pruebasCodigos extends javax.swing.JDialog {
    
private ManejadorFechas manFecha;
private LimpiarTextosPanel limPanel;
private ValidarVacios vacioPanel;
    /**
     * Creates new form pruebasCodigos
     */
    public pruebasCodigos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        asinar();
        limPanel =new LimpiarTextosPanel();
        vacioPanel =new ValidarVacios();

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtgetFechaActual = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtgetHoraActual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtrestarFechasDias = new javax.swing.JTextField();
        txtfNumeros = new javax.swing.JFormattedTextField();
        pnlTextos = new javax.swing.JPanel();
        txt1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPasswd = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtFormat = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnVerVacio = new javax.swing.JButton();
        txtPrueba3 = new javax.swing.JTextField();
        txtPrueba2 = new javax.swing.JTextField();
        txtPrueba = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtfFechaHora = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("SOLO NUMEROS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 85, -1, -1));

        jLabel2.setText("N° Y SIGNOS DE OPERACION");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 51, -1, -1));

        jLabel3.setText("SOLO LETRAS");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 108, -1, -1));

        jLabel4.setText("getFechaActual");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 112, -1, -1));

        txtgetFechaActual.setText("jTextField1");
        getContentPane().add(txtgetFechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 106, 140, -1));

        jLabel5.setText("FechaHora");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, 10));

        txtgetHoraActual.setText("jTextField1");
        getContentPane().add(txtgetHoraActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 268, 140, 20));

        jLabel6.setText("restarFechasDias 10");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 135, -1, -1));

        txtrestarFechasDias.setText("jTextField1");
        getContentPane().add(txtrestarFechasDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 132, 140, -1));

        txtfNumeros.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        getContentPane().add(txtfNumeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 82, 110, -1));

        pnlTextos.setBorder(javax.swing.BorderFactory.createTitledBorder("LIMPIAR POR PANEL"));

        txt1.setText("jTextField1");

        jLabel7.setText("txt");

        txtPasswd.setText("jPasswordField1");

        jLabel8.setText("pssw");

        txtFormat.setText("jFormattedTextField1");

        jLabel9.setText("txt format");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnVerVacio.setText("Ver Vacios?");
        btnVerVacio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerVacioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTextosLayout = new javax.swing.GroupLayout(pnlTextos);
        pnlTextos.setLayout(pnlTextosLayout);
        pnlTextosLayout.setHorizontalGroup(
            pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextosLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextosLayout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlTextosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTextosLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTextosLayout.createSequentialGroup()
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVerVacio)
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlTextosLayout.setVerticalGroup(
            pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTextosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTextosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar)
                    .addComponent(btnVerVacio)))
        );

        getContentPane().add(pnlTextos, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 143, 195, -1));

        txtPrueba3.setText("jTextField1");
        txtPrueba3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrueba3KeyTyped(evt);
            }
        });
        getContentPane().add(txtPrueba3, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 68, 140, -1));

        txtPrueba2.setText("jTextField1");
        txtPrueba2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrueba2KeyTyped(evt);
            }
        });
        getContentPane().add(txtPrueba2, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 42, 140, -1));

        txtPrueba.setText("jTextField1");
        txtPrueba.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPruebaKeyTyped(evt);
            }
        });
        getContentPane().add(txtPrueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(261, 11, 140, -1));

        jLabel10.setText("getHoraActual");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 278, -1, 10));

        txtfFechaHora.setText("jFormattedTextField1");
        getContentPane().add(txtfFechaHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 230, 170, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPruebaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPruebaKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 97 && k <= 122 || k >= 65 && k <= 90) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!" + k, "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 241 || k == 209) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            JOptionPane.showMessageDialog(null, "No puede ingresar letras!!!" + k, "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
        }
        if (k == 10) {
            txtPrueba.transferFocus();
        }
    }//GEN-LAST:event_txtPruebaKeyTyped

    private void txtPrueba2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrueba2KeyTyped
        char c;
//capturar el caracter digitado 
        c = evt.getKeyChar();
        if ((c < '0' || c > '9' || c == 10) && (c<',' || c>'.')){
            evt.consume();//ignora el caracter digitado 
        }
    }//GEN-LAST:event_txtPrueba2KeyTyped

    private void txtPrueba3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrueba3KeyTyped
           
             char c=evt.getKeyChar(); 
      
          if(Character.isDigit(c)) { 
              getToolkit().beep();               
              evt.consume(); 
              
              JOptionPane.showMessageDialog(null, "Ingresa Solo Letras" , "Ventana Error Datos", JOptionPane.ERROR_MESSAGE);
               
          }  
    }//GEN-LAST:event_txtPrueba3KeyTyped

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limPanel.limpiarCampos(pnlTextos);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnVerVacioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerVacioActionPerformed
         Object[] datos = {txt1.getText(), txtPasswd.getText(), txtFormat.getText()};
         vacioPanel.validar(datos);
         
    }//GEN-LAST:event_btnVerVacioActionPerformed

    private void asinar(){
    txtgetFechaActual.setText(manFecha.getFechaActual().toString());
    txtgetHoraActual.setText(manFecha.getHoraActual().toString());
    txtfFechaHora.setText(manFecha.getFechaHoraActual().toString());
    

    
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(pruebasCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pruebasCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pruebasCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pruebasCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                pruebasCodigos dialog = new pruebasCodigos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnVerVacio;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel pnlTextos;
    private javax.swing.JTextField txt1;
    private javax.swing.JFormattedTextField txtFormat;
    private javax.swing.JPasswordField txtPasswd;
    public javax.swing.JTextField txtPrueba;
    public javax.swing.JTextField txtPrueba2;
    public javax.swing.JTextField txtPrueba3;
    public javax.swing.JFormattedTextField txtfFechaHora;
    public javax.swing.JFormattedTextField txtfNumeros;
    public javax.swing.JTextField txtgetFechaActual;
    public javax.swing.JTextField txtgetHoraActual;
    public javax.swing.JTextField txtrestarFechasDias;
    // End of variables declaration//GEN-END:variables
}
