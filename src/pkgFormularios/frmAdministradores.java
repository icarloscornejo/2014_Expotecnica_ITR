/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgFormularios;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgClases.Helper;

/**
 *
 * @author Dennys Campos
 */
public class frmAdministradores extends javax.swing.JFrame {

    /**
     * Creates new form frmAdministradores
     */
    public frmAdministradores() {
        initComponents();
        txts = new Object[]{txtNombres, txtApellidos, txtCorreo, txtUsuario, txtContraseña};
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H.cerrarSesion(frmAdministradores.this);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                H.Menu(frmAdministradores.this);
            }
        });
        Cargartbl();
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
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        tblAdministradores = new javax.swing.JScrollPane();
        tblAdministrador = new javax.swing.JTable();
        btnCerrarSesion = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        lblHora = new javax.swing.JLabel();
        lblNombredeUsuario = new javax.swing.JLabel();
        lblBanner = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblNavegador = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombres.setFont(new java.awt.Font("Ubuntu Light", 0, 18)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(153, 153, 153));
        txtNombres.setText("Nombres");
        txtNombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombresMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtNombresMouseExited(evt);
            }
        });
        txtNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNombresFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNombresFocusLost(evt);
            }
        });
        txtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombresKeyTyped(evt);
            }
        });
        getContentPane().add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 530, -1));

        txtApellidos.setFont(new java.awt.Font("Ubuntu Light", 0, 18)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(153, 153, 153));
        txtApellidos.setText("Apellidos");
        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });
        txtApellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtApellidosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtApellidosFocusLost(evt);
            }
        });
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidosKeyTyped(evt);
            }
        });
        getContentPane().add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 530, -1));

        txtCorreo.setFont(new java.awt.Font("Ubuntu Light", 0, 18)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(153, 153, 153));
        txtCorreo.setText("Correo eletronico");
        txtCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCorreoFocusLost(evt);
            }
        });
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 530, -1));

        btnAgregar.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 290, 38));

        btnModificar.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, 290, 40));

        btnEliminar.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 140, 320, 40));

        btnConsultar.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 210, 320, 40));

        tblAdministrador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAdministrador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdministradorMouseClicked(evt);
            }
        });
        tblAdministradores.setViewportView(tblAdministrador);

        getContentPane().add(tblAdministradores, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 1290, 320));

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/CerrarSesion.png"))); // NOI18N
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setContentAreaFilled(false);
        btnCerrarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSesion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCerrarSesion.setIconTextGap(-3);
        btnCerrarSesion.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnCerrarSesion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSesionMouseExited(evt);
            }
        });
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 730, 40, -1));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Home.png"))); // NOI18N
        btnHome.setBorder(null);
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHome.setIconTextGap(-3);
        btnHome.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnHome.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        getContentPane().add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 730, 40, -1));

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Atras.png"))); // NOI18N
        btnAtras.setBorder(null);
        btnAtras.setBorderPainted(false);
        btnAtras.setContentAreaFilled(false);
        btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAtras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAtras.setIconTextGap(-3);
        btnAtras.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnAtras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasMouseExited(evt);
            }
        });
        getContentPane().add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 730, 40, -1));

        lblHora.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 255, 255));
        lblHora.setText("00:00 AM");
        getContentPane().add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(1268, 740, 90, -1));

        lblNombredeUsuario.setFont(new java.awt.Font("Gisha", 0, 20)); // NOI18N
        lblNombredeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombredeUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombredeUsuario.setText("Nombre de Usuario");
        getContentPane().add(lblNombredeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 30, 340, -1));

        lblBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Bancaria Top.png"))); // NOI18N
        getContentPane().add(lblBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administradores.");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Ubuntu Light", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(153, 153, 153));
        txtUsuario.setText("Usuario");
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 530, -1));

        lblNavegador.setBackground(new java.awt.Color(255, 255, 255));
        lblNavegador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Navegation.png"))); // NOI18N
        lblNavegador.setOpaque(true);
        getContentPane().add(lblNavegador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 728, -1, -1));

        txtContraseña.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtContraseña.setForeground(new java.awt.Color(153, 153, 153));
        txtContraseña.setText("Contraseña");
        txtContraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusLost(evt);
            }
        });
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 530, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Fondo Menu.png"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-130, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    Helper H = new Helper();
    Object[] txts;
    String[] data;
    String id, nom_adm, ape_adm, correo_adm, usu_adm, contra_adm;
    
    public void getStrings(){
        nom_adm = txtNombres.getText();
        ape_adm = txtApellidos.getText();
        correo_adm = txtCorreo.getText();
        usu_adm = txtUsuario.getText();
        contra_adm = String.valueOf(txtContraseña.getPassword());
    }
    
    public void Cargartbl(){
        H.fillTable(tblAdministrador, "SELECT p.nom_persona AS Nombre, p.ape_persona AS Apellido, p.correo_persona AS Correo, u.usu_usuario AS Usuario FROM tbl.Personas as p, tbl.Usuarios as u, tbl.TiposdePersonas as tp WHERE p.id_tippersona = tp.id_tippersona AND p.id_tippersona = 2 AND p.id_persona = u.id_persona", new int[]{0,0,0,1});
    }
    
    public void setFocusOff(){
        H.focusOff(txtNombres, "Nombres");                                       
        H.focusOff(txtApellidos, "Apellidos");                                    
        H.focusOff(txtCorreo, "Correo electronico");                                     
        H.focusOff(txtUsuario, "Usuario");                                        
        H.focusOff(txtContraseña, "Contraseña");
    }
    
    private void btnCerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseEntered
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/MouseOverCerrarSesion.png")));
    }//GEN-LAST:event_btnCerrarSesionMouseEntered

    private void btnCerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSesionMouseExited
        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/CerrarSesion.png")));
    }//GEN-LAST:event_btnCerrarSesionMouseExited

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/MouseOverHome.png")));
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Home.png")));
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/MouseOverAtras.png")));
    }//GEN-LAST:event_btnAtrasMouseEntered

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pkgImagenes/Atras.png")));
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        Cargartbl();
        H.LimpiarCampos(txts);
        setFocusOff();
        txtContraseña.setEnabled(true);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try{
            getStrings();
            if(H.VerificarCampos(txts)){
                if(H.ValidarEmail(correo_adm)){
                    if(H.notExists("SELECT id_persona FROM tbl.Personas WHERE correo_persona = ?", correo_adm, 2)){
                        if(H.notExists("SELECT usu_usuario FROM tbl.Usuarios", usu_adm, 1)){
                            if(H.ExecuteCSTM("EXEC pcd.AgregarAdministrador ?, ?, ?, ?, ?, ?", new String[]{nom_adm, ape_adm, correo_adm, H.Encriptar(usu_adm), H.Encriptar(contra_adm), String.valueOf(H.id_usuario)})){
                                H.LimpiarCampos(txts);
                                setFocusOff();
                                Cargartbl();
                                H.Message("Administrador agregado.");
                            }
                        }else{
                            H.Warning("Usuario ya existente.");
                        }
                    }else{
                        H.Warning("Persona ya existente.");
                    }
                }else{
                    H.Warning("Introduzca un correo valido.");
                }
            }else{
                H.Warning("No deje campos vacios.");
            }
        }catch(SQLException ex){
            H.Error(ex.toString());
        } catch (Exception ex) {
            H.Error(ex.toString());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresKeyTyped
    }//GEN-LAST:event_txtNombresKeyTyped

    private void tblAdministradorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdministradorMouseClicked
        try {
            H.tblClicked(tblAdministrador, new Object[]{txtNombres, txtApellidos, txtCorreo, txtUsuario});
            getStrings();
            id = String.valueOf(H.SearchOnePSTM("SELECT id_persona FROM tbl.Personas WHERE correo_persona = ?", new String[]{correo_adm}));
            txtContraseña.setEnabled(false);
            txtContraseña.setForeground(Color.black);
        } catch (SQLException ex) {
            Logger.getLogger(frmAdministradores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblAdministradorMouseClicked

    private void txtApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyTyped
    }//GEN-LAST:event_txtApellidosKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       try{
           if(H.Confirm("¿Realmente desea modificar este administrador?")){
                getStrings();
                if(H.VerificarCampos(txts)){
                    if(H.ValidarEmail(correo_adm)){
                        if(H.ExecuteCSTM("EXEC pcd.ModificarAdministrador ?, ?, ?, ?, ?", new String[]{id, nom_adm, ape_adm, correo_adm, H.Encriptar(usu_adm)})){
                            H.LimpiarCampos(txts);
                            setFocusOff();
                            Cargartbl();
                            txtContraseña.setEnabled(true);
                            H.Message("Administrador modificado.");
                        }else{
                            H.Error("Ha ocurrido un error en el proceso.");
                        }
                    }else{
                        H.Warning("Introduzca un correo valido.");
                    }
                }else{
                    H.Warning("No deje campos vacios.");
                }
           }else{
               Cargartbl();
               H.LimpiarCampos(txts);
               setFocusOff();
               txtContraseña.setEnabled(true);
           }
        }catch(SQLException ex){
            H.Error(ex.toString());
        } catch (Exception ex) {
            H.Error(ex.toString());
        }        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       try{
           if(H.Confirm("¿Realmente desea eliminar este administrador?")){
               if(H.ExecuteCSTM("EXEC pcd.EliminarAdministrador ?", new String[]{correo_adm})){
                   H.LimpiarCampos(txts);
                   setFocusOff();
                   Cargartbl();
                   txtContraseña.setEnabled(true);
                   H.Message("Administrador eliminado.");
               }
           }else{
               Cargartbl();
               H.LimpiarCampos(txts);
               setFocusOff();
               txtContraseña.setEnabled(true);
           }
       }catch(SQLException ex){
           H.Error(ex.toString());
       }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtNombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombresMouseClicked
        
    }//GEN-LAST:event_txtNombresMouseClicked

    private void txtNombresMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombresMouseExited
        
    }//GEN-LAST:event_txtNombresMouseExited

    private void txtNombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusGained
        H.focusOn(txtNombres);
    }//GEN-LAST:event_txtNombresFocusGained

    private void txtNombresFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNombresFocusLost
        H.focusOff(txtNombres, "Nombres");
    }//GEN-LAST:event_txtNombresFocusLost

    private void txtApellidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidosFocusGained
        H.focusOn(txtApellidos);
    }//GEN-LAST:event_txtApellidosFocusGained

    private void txtApellidosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtApellidosFocusLost
        H.focusOff(txtApellidos, "Apellidos");
    }//GEN-LAST:event_txtApellidosFocusLost

    private void txtCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusGained
        H.focusOn(txtCorreo);
    }//GEN-LAST:event_txtCorreoFocusGained

    private void txtCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorreoFocusLost
        H.focusOff(txtCorreo, "Correo electronico");
    }//GEN-LAST:event_txtCorreoFocusLost

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        H.focusOn(txtUsuario);
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        H.focusOff(txtUsuario, "Usuario");
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void txtContraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraseñaFocusGained
        H.focusOn(txtContraseña);
    }//GEN-LAST:event_txtContraseñaFocusGained

    private void txtContraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraseñaFocusLost
        H.focusOff(txtContraseña, "Contraseña");
    }//GEN-LAST:event_txtContraseñaFocusLost
    String r;
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
            java.util.logging.Logger.getLogger(frmAdministradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAdministradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAdministradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAdministradores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new frmAdministradores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblBanner;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNavegador;
    private javax.swing.JLabel lblNombredeUsuario;
    private javax.swing.JTable tblAdministrador;
    private javax.swing.JScrollPane tblAdministradores;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
