/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgFormularios;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.Timer;
import pkgClases.Helper;


public class frmLogin extends javax.swing.JFrame {

    /**
     * Creates new form frmLogin
     */
    public frmLogin() {
        initComponents();
        getRootPane().setDefaultButton(btnAceptar);
        fade.start();
        p1.setForeground(Color.white);
        i1.setVisible(true);
        txtdescripcion.setText("Bancaria esta desarrollado en Java, por lo cual es altamente actualizado y seguro.");
        i2.setVisible(false);
        i3.setVisible(false);
        transiciones.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblBottom = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        lblContraseña = new javax.swing.JLabel();
        btnRecuperarContraseña = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        pgbTransicion = new javax.swing.JProgressBar();
        p1 = new javax.swing.JLabel();
        p2 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        i3 = new javax.swing.JLabel();
        i2 = new javax.swing.JLabel();
        i1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        lblFondo2 = new javax.swing.JLabel();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBottom.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblBottom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Bancaria Center.png"))); // NOI18N
        getContentPane().add(lblBottom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, -1, -1));

        txtContraseña.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 180, -1));

        lblContraseña.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(255, 255, 255));
        lblContraseña.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblContraseña.setText("Contraseña:");
        getContentPane().add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 90, 20));

        btnRecuperarContraseña.setBackground(new Color(0,0,0,0.0f));
        btnRecuperarContraseña.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        btnRecuperarContraseña.setForeground(new java.awt.Color(255, 255, 255));
        btnRecuperarContraseña.setText("Recuperar contraseña");
        btnRecuperarContraseña.setBorder(null);
        btnRecuperarContraseña.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRecuperarContraseñaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRecuperarContraseñaMouseExited(evt);
            }
        });
        btnRecuperarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecuperarContraseñaActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecuperarContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 40, 140, 30));

        lblUsuario.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsuario.setText("Usuario:");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 80, 20));

        txtUsuario.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 180, -1));

        btnAceptar.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 140, -1));
        getContentPane().add(pgbTransicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 236, 515, 1));

        p1.setFont(new java.awt.Font("Ubuntu", 0, 105)); // NOI18N
        p1.setText(".");
        getContentPane().add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 410, -1, 90));

        p2.setFont(new java.awt.Font("Ubuntu", 0, 105)); // NOI18N
        p2.setText(".");
        getContentPane().add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 410, -1, 90));

        p3.setFont(new java.awt.Font("Ubuntu", 0, 105)); // NOI18N
        p3.setText(".");
        getContentPane().add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 410, -1, 90));

        i3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/3.png"))); // NOI18N
        getContentPane().add(i3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        i2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/2.png"))); // NOI18N
        getContentPane().add(i2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        i1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/1.png"))); // NOI18N
        getContentPane().add(i1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        jScrollPane1.setRequestFocusEnabled(false);

        txtdescripcion.setEditable(false);
        txtdescripcion.setColumns(20);
        txtdescripcion.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        txtdescripcion.setForeground(new java.awt.Color(0, 153, 204));
        txtdescripcion.setLineWrap(true);
        txtdescripcion.setRows(5);
        txtdescripcion.setText("12211222122123probando");
        txtdescripcion.setWrapStyleWord(true);
        txtdescripcion.setBorder(null);
        txtdescripcion.setFocusable(false);
        txtdescripcion.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(txtdescripcion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 490, 60));

        lblFondo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/log.png"))); // NOI18N
        getContentPane().add(lblFondo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/login.png"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    Timer fade = new Timer (1, new ActionListener () { 
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
           float f = 0.0f;
        while(f < 1.0f){
            frmLogin.this.setOpacity(f);
            f += 0.00001f;            
        }
        fade.stop();
     } 
        });
    
    int pgb = 1;
    Timer panel = new Timer (45, new ActionListener () { 
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
        pgbTransicion.setValue(pgb);
        pgb+=1;
        if(pgb == 100){
            panel.stop();
            pgb = 1;
        }
     } 
        });
    
    int p = 2;
    Timer transiciones = new Timer (5000, new ActionListener () { 
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
        i1.setVisible(false);
        i2.setVisible(false);
        i3.setVisible(false);
        p1.setForeground(Color.black);
        p2.setForeground(Color.black);
        p3.setForeground(Color.black);
        switch(p){
            case 1:
                i1.setVisible(true);
                txtdescripcion.setText("Bancaria esta desarrollado en Java, por lo cual es altamente actualizado y seguro.");
                p1.setForeground(Color.white);
                p = 2;
                panel.start();
                break;
            case 2:
                i2.setVisible(true);
                txtdescripcion.setText("Ofrecemos tarjetas de credito y debito al usuario final, con distintos niveles de socios.");
                p2.setForeground(Color.white);
                p = 3;
                panel.start();
                break;
            case 3:
                i3.setVisible(true);
                txtdescripcion.setText("Bancaria posee un alto nivel de seguridad gracias a sus encriptaciones AES y RSA incorporadas.");
                p3.setForeground(Color.white);
                p = 1;
                panel.start();
                break;
        }
     } 
        });
    
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
//        try {
//            H.tip_persona = H.LoginSCTB(txtUsuario, txtContraseña);
//            switch(H.tip_persona){
//                case "Empleado":
//                H.nom_usu = txtUsuario.getText();
//                H.Message("Bienvenido/a "+H.nom_usu+".");
//                H.showWithTransition(this, new frmMenuEmpleado());
//                break;
//                case "Gerente":
//                H.nom_usu = txtUsuario.getText();
//                H.Message("Bienvenido/a "+H.nom_usu+".");
//                H.showWithTransition(this, new frmMenuEmpleado());
//                break;
//                case "Administrador":
//                H.nom_usu = txtUsuario.getText();
//                H.Message("Bienvenido/a "+H.nom_usu+".");
//                H.showWithTransition(this, new frmMenuAdm());
//                break;
//                case "Superadministrador":
//                H.nom_usu = txtUsuario.getText();
//                H.Message("Bienvenido/a "+H.nom_usu+".");
//                H.showWithTransition(this, new FrmMenuSuperAdmins());
//                break;
//                case "null":
//                Limpiar();
//                txtUsuario.requestFocus();
//                H.Message("Usuario inexistente.");
//                break;
//            }
//        } catch (SQLException ex) {
//            H.Error(ex.toString());
//        }
        H.nom_usu = txtUsuario.getText();
                H.Message("Bienvenido/a "+H.nom_usu+".");
                H.showWithTransition(this, new FrmMenuSuperAdmins());
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnRecuperarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecuperarContraseñaActionPerformed
        new frmRecuperarContraseña(this, true).show();
    }//GEN-LAST:event_btnRecuperarContraseñaActionPerformed

    private void btnRecuperarContraseñaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecuperarContraseñaMouseEntered
        btnRecuperarContraseña.setForeground(Color.blue);
    }//GEN-LAST:event_btnRecuperarContraseñaMouseEntered

    private void btnRecuperarContraseñaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRecuperarContraseñaMouseExited
        btnRecuperarContraseña.setForeground(Color.white);
    }//GEN-LAST:event_btnRecuperarContraseñaMouseExited

    pkgClases.Helper H = new Helper();
    String usu, contra, msj;
    boolean exists = true;
    
    public void English(){
        lblUsuario.setText("Username:");
        lblContraseña.setText("Password:");
        btnAceptar.setText("Accept");
    }
    
    public void Español(){
        lblUsuario.setText("Usuario:");
        lblContraseña.setText("Contraseña:");
        btnAceptar.setText("Aceptar");
    }
    
    public void Limpiar(){
        txtUsuario.setText(null);
        txtContraseña.setText(null);
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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnRecuperarContraseña;
    private javax.swing.JLabel i1;
    private javax.swing.JLabel i2;
    private javax.swing.JLabel i3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBottom;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblFondo2;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JProgressBar pgbTransicion;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextArea txtdescripcion;
    // End of variables declaration//GEN-END:variables
}
