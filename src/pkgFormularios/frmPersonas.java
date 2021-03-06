/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgFormularios;

import java.awt.Color;
import java.sql.SQLException;
import pkgClases.Helper;

/**
 *
 * @author Juan Carlos Cuenca
 */
public class frmPersonas extends javax.swing.JFrame {

    /**
     * Creates new form frmPersonas
     */
    public frmPersonas() {
        initComponents();
        txts = new Object[]{txtNombres, txtApellidos, txtCorreo};
        if(nom_persona != null){
            txtNombres.setText(nom_persona);
            txtApellidos.setText(ape_persona);
            txtCorreo.setText(correo_persona);
            txtNombres.setForeground(Color.black);
            txtApellidos.setForeground(Color.black);
            txtCorreo.setForeground(Color.black);
        }
        jTextArea1.setBackground(new Color(0,0,0,0.0f));
        txtNombres.setFont(new java.awt.Font("Ubuntu Light", 0, 24));
        txtApellidos.setFont(new java.awt.Font("Ubuntu Light", 0, 24));
        txtCorreo.setFont(new java.awt.Font("Ubuntu Light", 0, 24));
        jTextArea1.setFont(new java.awt.Font("Ubuntu Light", 0, 24));
        btnAceptar.setFont(new java.awt.Font("Ubuntu Light", 0, 24));
        btnAtras.setFont(new java.awt.Font("Ubuntu Light", 0, 24));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H.Menu(frmPersonas.this);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                H.ValidarLetras(evt);
            }
        });
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                H.ValidarLetras(evt);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombres.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(153, 153, 153));
        txtNombres.setText("Nombres");
        txtNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombresFocusLost(evt);
            }
        });
        getContentPane().add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 540, 50));

        txtApellidos.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(153, 153, 153));
        txtApellidos.setText("Apellidos");
        txtApellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApellidosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidosFocusLost(evt);
            }
        });
        getContentPane().add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 540, 50));

        txtCorreo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(153, 153, 153));
        txtCorreo.setText("Correo electronico");
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 540, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Bancaria Center.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 60));

        btnAceptar.setBackground(new java.awt.Color(255, 255, 255));
        btnAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(null);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 610, 160, 60));

        btnAtras.setBackground(new java.awt.Color(255, 255, 255));
        btnAtras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.setBorder(null);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        getContentPane().add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 590, 180, 70));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new Color(0,0,0,0f));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Bienvenido al Sistema de Control Total Bancario.\n\nA continuacion se le pedira que rellene una serie de datos,\nporfavor rellene todos los datos correctamente antes de \navanzar al siguiente paso.\nRecuerde siempre poner datos validos de la persona a \nregistrar.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(null);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 650, 420));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Navegation.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 730, 1370, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Fondo Menu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    Helper H = new Helper();
    public static String nom_persona, ape_persona, correo_persona;
    Object[] txts;
    
    public void getStrings(){
        nom_persona = txtNombres.getText();
        ape_persona = txtApellidos.getText();
        correo_persona = txtCorreo.getText();
    }
    
    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            getStrings();
            if(H.VerificarCampos(txts)){
                if(H.ValidarEmail(correo_persona)){
                    if(H.notExists("SELECT id_persona FROM tbl.Personas WHERE correo_persona = ?", correo_persona, 2)){
                        H.showWithTransition(this, new frmDatosdePersona());
                    }
                }else{
                    H.Warning("Correo electronico invalido.");
                }
            }else{
                H.Warning("Complete los campos.");
            } 
        }catch(SQLException ex){
            H.Error(ex.toString());
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtNombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusGained
        H.focusOn(txtNombres);
    }//GEN-LAST:event_txtNombresFocusGained

    private void txtApellidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidosFocusGained
        H.focusOn(txtApellidos);
    }//GEN-LAST:event_txtApellidosFocusGained

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained
        H.focusOn(txtCorreo);
    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusLost
        H.focusOff(txtNombres, "Nombres");
    }//GEN-LAST:event_txtNombresFocusLost

    private void txtApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidosFocusLost
        H.focusOff(txtApellidos, "Apellidos");
    }//GEN-LAST:event_txtApellidosFocusLost

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        H.focusOff(txtCorreo, "Correo electronico");
    }//GEN-LAST:event_txtCorreoFocusLost

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
            java.util.logging.Logger.getLogger(frmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPersonas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPersonas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    // End of variables declaration//GEN-END:variables
}
