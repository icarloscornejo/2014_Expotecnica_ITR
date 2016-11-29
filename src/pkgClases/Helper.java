/* Created by (user) */

package pkgClases;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;
import javax.xml.bind.DatatypeConverter;
import pkgFormularios.FrmMenuSuperAdmins;
import pkgFormularios.FrmMenuSuperAdmins;
import pkgFormularios.frmConfirmacion;
import pkgFormularios.frmConfirmacion;
import pkgFormularios.frmLogin;
import pkgFormularios.frmLogin;
import pkgFormularios.frmMenuAdm;
import pkgFormularios.frmMenuAdm;
import pkgFormularios.frmMenuEmpleado;
import pkgFormularios.frmMenuEmpleado;
import pkgFormularios.frmToast;
import pkgFormularios.frmToast;
import pkgFormularios.frmToastError;
import pkgFormularios.frmToastError;
import pkgFormularios.frmTransicion;
import pkgFormularios.frmTransicion;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Helper {
    public Statement stm;
    public PreparedStatement pstm;
    public CallableStatement cstm;
    public Connection Conexion = null;
    public ResultSet rs;
    public ResultSetMetaData rsmd;
    public static int id_usuario = -1, id_cliente = -1;
    public static String nom_usu, tip_persona, toast_msj, toasterror_msj, confirm_title, confirm_msj;
    public static boolean confirm_boolean = false, confirm_open = true;
    public static Paragraph P;
    public SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");
    public BufferedImage tempImage;    
    public Timer fade, fadenuevo, fadeactual, transicion, duracion;
    public static final String AlgorithmOne = "AES";
    public static final String AlgorithTwo = "RSA";
    public static final String PrivateRSAKey = "C:/keys/private.key";  
    public static final String PublicRSAKey = "C:/keys/public.key";
    public static final String DBLocal = "jdbc:sqlserver://localhost:1433;database = SCTB;user = sa;password = 123456";
    public static final String DBMaster = "jdbc:sqlserver://localhost:1433;database = master;user = sa;password = prototypeC1";
    public static final String DBAzure = "jdbc:sqlserver://rrd7wnibcf.database.windows.net:1433;database=SCTB;user=protokonstant@rrd7wnibcf;password={Expo2014};encrypt=true;trustServerCertificate=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    private static final byte[] AESkey = new byte[]{ 'A', 'S', 'e', 'c', 'u', 'r', 'e', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };
    
    public void ConnectforInitialize() throws SQLException{  //Necesario para construir la base si no se encuentra.
        try{            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String DBLocation = DBMaster;
        try{            
            Conexion = DriverManager.getConnection(DBLocation);
            if(Conexion != null){
                stm = Conexion.createStatement();        
            }
        }
        catch(SQLException ex){
            if(ex.getErrorCode() == 4060){
                this.GenerateDatabase();
                this.Restart();
            }else{
                this.showMessage(ex.toString(), "Error", "Error");
            }
        }     
        }
        catch(ClassNotFoundException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }

}
    
    public void ConnectDatabaseSTM() throws SQLException{ //Ocupado para simples consultas sin alta seguridad
        try{            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String DBLocation = DBLocal;
            try{            
                Conexion = DriverManager.getConnection(DBLocation);
                if(Conexion != null){
                    stm = Conexion.createStatement();        
                }
            }
            catch(SQLException ex){
                if(ex.getErrorCode() == 4060){                
                    this.GenerateDatabase();
                }else{
                    this.showMessage(ex.toString(), "Error", "Error");
                }
            }     
        }
        catch(ClassNotFoundException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }

}
    
    public PreparedStatement ConnectDatabasePSTM(String Consulta) throws SQLException{ //Ocupados mayormente para operaciones seguras.
        try{            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DBLocation = DBLocal;
            try{            
            Conexion = DriverManager.getConnection(DBLocation);        
                pstm = Conexion.prepareStatement(Consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);        
            }
            catch(SQLException ex){
                if(ex.getErrorCode() == 4060){                
                    this.GenerateDatabase();
                }else{
                 this.showMessage(ex.toString(), "Error", "Error");
                }
            }
        }
        catch(ClassNotFoundException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return pstm;
}
    
    public CallableStatement ConnectDatabaseCSTM(String Consulta) throws SQLException{ //Ocupados mayormente para procedimientos almacenados.
        try{            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DBLocation = DBLocal;
            try{            
            Conexion = DriverManager.getConnection(DBLocation);        
                cstm = Conexion.prepareCall(Consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);        
            }
            catch(SQLException ex){
                if(ex.getErrorCode() == 4060){                
                    this.GenerateDatabase();
                }else{
                 this.showMessage(ex.toString(), "Error", "Error");
                }
            }
        }
        catch(ClassNotFoundException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return cstm;
}
    
    public boolean ExecuteforInitialize(String Consulta) throws SQLException{ //Es el que se ocupa para construir la base de datos por primera vez
        this.ConnectforInitialize();
        try{
            stm.executeUpdate(Consulta);
            return true;
        }
        catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
            return false;
        }
    }
    
    public boolean Execute(String Consulta) throws SQLException{ //Metodo para agregar objetos a la base mediante concatenacion
        this.ConnectDatabaseSTM();
        boolean i = false;
        try{
            i = stm.executeUpdate(Consulta) > 0;
        }
        catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return i;
    }
    
    public ResultSet ExecuteQuery(String Consulta) throws SQLException{ //Metodo para hacer busquedas de registros
        this.ConnectDatabaseSTM();
        try{
            rs = stm.executeQuery(Consulta);
        }
        catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return rs;
    }
    
    public Object SearchOne(String Consulta) throws SQLException{ //Metodo para buscar una sola celda de registros
        this.ConnectDatabaseSTM();
        Object dato = null;
        try{
            rs = this.ExecuteQuery(Consulta);
            while(rs.next()){
                dato = rs.getObject(1);
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        return dato;
    }
    
    public Object SearchOnePSTM(String Consulta, Object[] Objetos) throws SQLException{ //Metodo para hacer busquedas mas seguras
        PreparedStatement PreparedSTM = ConnectDatabasePSTM(Consulta);
        Object dato = null;
        try{            
            for(int i = 0; i < Objetos.length;i++){
                PreparedSTM.setObject(i+1, Objetos[i]);
            }
            rs = PreparedSTM.executeQuery();
            while(rs.next()){
                dato = rs.getObject(1);
            }
        }catch(SQLException ex){
            this.Error(ex.toString());
        }finally{
            PreparedSTM.close();
        }
        return dato;
    }
    
    public Object[] ExecuteQueryPSTM(String Consulta, Object[] Objetos) throws SQLException{ //Metodo para hacer busquedas mas seguras
        PreparedStatement PreparedSTM = ConnectDatabasePSTM(Consulta);
        Object[] datos = null;
        try{            
            for(int i = 0; i < Objetos.length;i++){
                PreparedSTM.setObject(i+1, Objetos[i]);
            }
            rs = PreparedSTM.executeQuery();
            rsmd = rs.getMetaData();
            datos = new Object[rsmd.getColumnCount()];
            while(rs.next()){
                for(int i = 0; i < rsmd.getColumnCount();i++){
                    datos[i] = rs.getObject(i+1);
                }
            }
        }catch(SQLException ex){
            this.Error(ex.toString());
        }finally{
            PreparedSTM.close();
        }
        return datos;
    }
    
    public boolean ExecutePSTM(String Consulta, String[] Objetos) throws SQLException{ //Metodo automatizado para setear objetos y agregarlos a la base.
        PreparedStatement PreparedSTM = ConnectDatabasePSTM(Consulta);
        boolean o = false;
        try{            
            for(int i = 0; i < Objetos.length;i++){
                PreparedSTM.setObject(i+1, Objetos[i]);
            }
            o = PreparedSTM.executeUpdate() > 0;
        }catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }finally{
            PreparedSTM.close();
        }
        return o;
    }
    
    public boolean ExecuteCSTM(String Consulta, String[] data) throws SQLException{
        CallableStatement CallableSTM = ConnectDatabaseCSTM(Consulta);
        boolean res = false;
        try{            
            for(int i = 0; i < data.length;i++){
                CallableSTM.setObject(i+1, data[i]);
            }
            res = CallableSTM.executeUpdate() > 0;
        }catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }finally{
            CallableSTM.close();
        }
        return res;
    }
    
    public Object[] ExecuteCSTMWithOUTPUT(String Consulta, String[] data, int ObjetosaDevolver) throws SQLException{
        CallableStatement CallableSTM = ConnectDatabaseCSTM(Consulta);
        Object[] res = new Object[ObjetosaDevolver];
        try{            
            for(int i = 0; i < data.length;i++){
                CallableSTM.setObject(i+1, data[i]);
            }
            for(int x = 0; x < ObjetosaDevolver;x++){
                CallableSTM.registerOutParameter(x+1+data.length, java.sql.Types.OTHER);
            }
            if(CallableSTM.executeUpdate() > 0){
                for(int x = 0; x < ObjetosaDevolver;x++){
                    res[x] = CallableSTM.getObject(x+1+data.length);
                }
            }
        }catch(SQLException ex){
            this.Error(ex.toString());
        }finally{
            CallableSTM.close();
        }
        return res;
    }
    
    public boolean notExists(String consulta, String dataSinEncriptar, int uno_paraencriptadoXXXdos_sinencriptado) throws SQLException{
        boolean res = true;
        if(uno_paraencriptadoXXXdos_sinencriptado == 2){
        res = this.SearchOnePSTM(consulta, new Object[]{dataSinEncriptar}) == null;
        }else if(uno_paraencriptadoXXXdos_sinencriptado == 1){
            try(PreparedStatement notExists = ConnectDatabasePSTM(consulta)) {   
                rs = notExists.executeQuery();
                while(rs.next()){
                    res = !this.RSAdesenc(rs.getString(1)).equals(this.AESenc(dataSinEncriptar));
                }
            }catch(SQLException ex){
                this.Error(ex.toString());
            }
        }
        return res;
    }
    
    public String LoginSCTB(JTextComponent usu, JPasswordField con) throws SQLException{
        String usuario = usu.getText(), contraseña = String.valueOf(con.getPassword());
        PreparedStatement Login = ConnectDatabasePSTM("SELECT usu_usuario, contra_usuario, id_usuario, nom_tippersona FROM viw.LoginV");         
        String res = "null";
        try{   
            rs = Login.executeQuery();
            while(rs.next()){
                String RSAuser, RSApassword, AESuser, AESpassword, user, password;
                RSAuser = rs.getString(1);
                RSApassword = rs.getString(2);
                AESuser = this.RSAdesenc(RSAuser);
                AESpassword = this.RSAdesenc(RSApassword);
                user = this.AESenc(usuario);
                password = this.AESenc(contraseña);
                if(user.equals(AESuser)){
                    if(password.equals(AESpassword)){
                        this.id_usuario = rs.getInt(3);
                        res = rs.getString(4);
                    }
                }
            }
        }catch(SQLException ex){
            this.Error(ex.toString());
        } catch (Exception ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Login.close();
        }
        return res;
    }
    
    public void tblClicked(JTable tbl, Object[] txts){
        try{
            for(int i = 0; i < txts.length; i++){
                JTextComponent txt = (JTextComponent) txts[i];
                txt.setText(String.valueOf(tbl.getValueAt(tbl.getSelectedRow(), i)));
                txt.setForeground(Color.black);
            }
        } catch (Exception ex) {
            this.Error(ex.toString());
        }
    }
    
    public void Cleantbl(JTable tbl){ //Metodo para limpiar tablas.
        DefaultTableModel modeltbl = (DefaultTableModel) tbl.getModel();
        int filas = modeltbl.getRowCount()-1;
        for( int x=filas; x>=0; x--){
        modeltbl.removeRow(x);
        }   
        tbl.getColumnModel().removeColumn(tbl.getColumnModel().getColumn(0));
        modeltbl.setColumnCount(0);
        modeltbl.setRowCount(0);
        tbl.setModel(modeltbl);
    }
    
    public void ValidarLetrayNumeros(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c) || (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) || Character.isDigit(c) ){
            
        }        
        else{
            evt.consume();
        }
    }
    
    public void ValidarLetras(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if(Character.isLetter(c) || Character.isSpaceChar(c) || (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
            
        }        
        else{
            evt.consume();
        }
    }
    
    public void ValidarNumeros(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();
        if(Character.isDigit(c) || (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)){
            
        }        
        else{
            evt.consume();
        }
    }
    
    public boolean ValidarEmail(String email){
        String expresion;
            expresion = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(expresion);
    }
    
    public DefaultTableModel CreateTableModel(int columnas){ //Metodo que crea un modelo de tabla para imagenes y texto
        final boolean[] canEdit = new boolean[columnas];
        for(int i = 0; i < columnas; i++){
            canEdit[i] = false;
        }
        DefaultTableModel modelo = new DefaultTableModel(){
        @Override
        public Class getColumnClass(int indice){
            return getValueAt(0, indice).getClass();
        };
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
        };
        return modelo;
    }
    
    public void CreateComboBoxModel(JComboBox cmb, String Consulta){ //MEtodo para crear un modelo de un cmb mediante consulta
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        try{            
            rs = this.ExecuteQuery(Consulta);
            while(rs.next()){
                model.addElement(rs.getString(1));
            }
        }catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        cmb.setModel(model);
    }   

    private static void generateRSAKey() {
    try {
      final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(AlgorithTwo);
      keyGen.initialize(1024);
      final KeyPair key = keyGen.generateKeyPair();
      File privateKeyFile = new File(PrivateRSAKey);
      File publicKeyFile = new File(PublicRSAKey);
      if (privateKeyFile.getParentFile() != null) {
        privateKeyFile.getParentFile().mkdirs();
      }
      privateKeyFile.createNewFile();
      if (publicKeyFile.getParentFile() != null) {
        publicKeyFile.getParentFile().mkdirs();
      }
      publicKeyFile.createNewFile();
        try (ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                new FileOutputStream(publicKeyFile))) {
            publicKeyOS.writeObject(key.getPublic());
        }
        try (ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                new FileOutputStream(privateKeyFile))) {
            privateKeyOS.writeObject(key.getPrivate());
        }
    } catch (IOException | NoSuchAlgorithmException e) {
    }
  }
    private static Key generateAESKey() throws Exception{
                  Key key = new SecretKeySpec(AESkey, AlgorithmOne);
                  return key;
          }
    public static boolean areKeysPresent() {
      File privateKey = new File(PrivateRSAKey);
      File publicKey = new File(PublicRSAKey);
      return privateKey.exists() && publicKey.exists();
    }
    public byte[] encrypt(String text, PublicKey key) {
      byte[] cipherText = null;
      try {
        // get an RSA cipher object and print the provider
        final Cipher cipher = Cipher.getInstance(AlgorithTwo);
        // encrypt the plain text using the public key
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipherText = cipher.doFinal(text.getBytes());
      } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
          this.showMessage(ex.toString(), "Error", "Error");
      }
      return cipherText;
    }
    public String decrypt(byte[] text, PrivateKey key) {
      byte[] dectyptedText = null;
      try {
        // get an RSA cipher object and print the provider
        final Cipher cipher = Cipher.getInstance(AlgorithTwo);

        // decrypt the text using the private key
        cipher.init(Cipher.DECRYPT_MODE, key);
        dectyptedText = cipher.doFinal(text);

      } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
          this.showMessage(ex.toString(), "Error", "Error");         
      }
      return new String(dectyptedText);
    }

    public String RSAenc(String data){
        String res = null;
        try {
            if (!areKeysPresent()) {
                generateRSAKey();
            }
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(PublicRSAKey));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            res = HexBin.encode(encrypt(data, publicKey));
            
        } catch (IOException | ClassNotFoundException ex) {
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return res;
    }

    public String RSAdesenc(String data){
        String res = null;
        try {
            if (!areKeysPresent()) {
                generateRSAKey();
            }
            ObjectInputStream inputStream;
            inputStream = new ObjectInputStream(new FileInputStream(PrivateRSAKey));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            res = decrypt(DatatypeConverter.parseHexBinary(data), privateKey);
        } catch (IOException | ClassNotFoundException ex) {
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return res;
    }

    public String AESenc(String data){
        String res = null;
        try{
            Key key = generateAESKey();
            Cipher chiper = Cipher.getInstance(AlgorithmOne);
            chiper.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = chiper.doFinal(data.getBytes());
            res = new BASE64Encoder().encode(encVal);
        }catch(Exception ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return res;
    }

    public String AESdesenc(String data){
        String res = null;
        try{
            Key key = generateAESKey();
            Cipher chiper = Cipher.getInstance(AlgorithmOne);
            chiper.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(data);
            byte[] decValue = chiper.doFinal(decordedValue);
            res = new String(decValue);
        }catch(Exception ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return res;
    }

    public String Desencriptar(String data) throws Exception { //Metodo de desencriptado
        return this.AESdesenc(this.RSAdesenc(data));
}

    public String Encriptar(String data) throws Exception { //Metodo de encriptado
        return this.RSAenc(this.AESenc(data));        
}

    public boolean ValidarTelefono(String num){
        return num.startsWith("2") || num.startsWith("7") || num.startsWith("6");
    }
    
    public boolean ValidarDUI(String DUI){
    int uno, dos, tres, cuatro, cinco, seis, siete, ocho, verificador;
    
    uno = Integer.parseInt(String.valueOf(DUI.charAt(0)));
    dos = Integer.parseInt(String.valueOf(DUI.charAt(1)));
    tres = Integer.parseInt(String.valueOf(DUI.charAt(2)));
    cuatro = Integer.parseInt(String.valueOf(DUI.charAt(3)));
    cinco = Integer.parseInt(String.valueOf(DUI.charAt(4)));
    seis = Integer.parseInt(String.valueOf(DUI.charAt(5)));
    siete = Integer.parseInt(String.valueOf(DUI.charAt(6)));
    ocho = Integer.parseInt(String.valueOf(DUI.charAt(7)));
    verificador = Integer.parseInt(String.valueOf(DUI.charAt(9)));
    
    int res = (9*uno)+(8*dos)+(7*tres)+(6*cuatro)+(5*cinco)+(4*seis)+(3*siete)+(2*ocho);
    int div = res % 10;
    int resta = 10 - div;
    if(resta == 0){ //Si es 0 es valido
        return true;
    }else return resta == verificador; //Si es igual al numero verificador tambien es valido
}

    public boolean ValidarNIT(String NIT){ //Metodo para validacion de NIT
        int part,i,n,f,cal = 0;
        part = Integer.parseInt(NIT.substring(12, 15));
        if (part <= 100) {
            n = 1;
            for (i = 0; i <= 14; i++) {
                if (!(i == 4 || i == 11)) {
                    cal = cal + Integer.parseInt(String.valueOf(NIT.charAt(i))) * (15 - n);
                    n++;
                }
            }
            cal = cal % 11;
            if (cal == 10){
                cal = 0;
            }
        } else {
            n = 1;
            for (i = 0; i <= 14; i++) {
                if (!(i == 4 || i == 11)) {
                    f = (int) ((3 + 6 * Math.floor(Math.abs((n + 4) / 6))) - n);
                    cal = cal + (Integer.parseInt(String.valueOf(NIT.charAt(i))) * f);
                    n++;
                }
            }
            cal = cal % 11;
            if (cal > 1){
                cal = 11 - cal;
            }else{
                cal = 0;
            }
        }
        int a = Integer.parseInt(String.valueOf(NIT.charAt(16)));
        return a == cal;
    }

    public void showMessage(String msj, String titulo, String tipo){
    int typemessage = 1;
    switch(tipo.toLowerCase()){
        case "informacion":
            typemessage = JOptionPane.INFORMATION_MESSAGE;
            break;
        case "advertencia":
            typemessage = JOptionPane.WARNING_MESSAGE;
            break;
        case "error":
            typemessage = JOptionPane.ERROR_MESSAGE;
            break;
    }    
    JOptionPane.showMessageDialog(null, msj, titulo, typemessage);
}
    
    public void toastMessage(String msj){
        toast_msj = msj;
        final frmToast frm = new frmToast(null, false);
        frm.show();
    }
    
    public void toastErrorMessage(String msj){
        toasterror_msj = msj;
        final frmToastError frm = new frmToastError(null, false);
        frm.show();
    }
    
    public void Message(String msj){
        toastMessage(msj);
    }
    public void Warning(String msj){
        toastMessage(msj);
    }
    public void Error(String ex){
        toastErrorMessage(ex);
    }
    
    public boolean Confirm(String msj){
        this.confirm_msj = msj;
        this.confirm_title = "Confirmacion.";
        final frmConfirmacion frm = new frmConfirmacion(null, true);
        frm.show();
        return confirm_boolean;
    }
    
    public void Menu(Frame frm){
        showWithTransition(frm, new FrmMenuSuperAdmins());
    }

    public void cerrarSesion(Frame frm){
        id_usuario = -1;
        nom_usu = null;
        showWithTransition(frm, new frmLogin());
    }
    public void show(final Frame actual, final Frame nuevo){ //Metodo para expresar nuevos frames con fade
    fade = new Timer (1, new ActionListener () { 
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
           float f = 0.0f;
        while(f < 1.0f){
           nuevo.setOpacity(f);
            f += 0.00001f;            
        }
        actual.dispose();
        fade.stop();
     } 
        });
    nuevo.show();
    nuevo.setOpacity(0.0f);
    nuevo.toFront();
    fade.start();
}
    
    public boolean VerificarCampos(Object[] txts){ //Metodo para verificar que los campos esten llenos
            boolean llenos = true;        
            for (Object txt1 : txts){
                JTextComponent txt = (JTextComponent) txt1;
                if(txt.getForeground() != Color.black){
                    llenos = false;
                }else if("".equals(txt.getText())){
                    llenos = false;
                }
            }        
            return llenos;
        }
    
    public void LimpiarCampos(Object[] txts){ //Metodo para limpiar los campos
        for (Object txt1 : txts){
            JTextComponent txt = (JTextComponent) txt1;
            txt.setText(null);
        }    
    }

    public void unmaskPassword(JPasswordField txt){
        txt.setEchoChar((char) 0);
    }
    
    public void maskPassword(JPasswordField txt){
        txt.setEchoChar((char) '*');
    }
    public void fillTable(JTable tbl, String consulta,int[] TipodeObjeto){ //Metodo para llenado de tabla
        DefaultTableModel defaultmodel = this.CreateTableModel(1);
        defaultmodel.addColumn("defaultcolumn");
        tbl.setModel(defaultmodel);
        this.Cleantbl(tbl);
        DefaultTableModel modelo;
        Object[] fila;
        try{        
            rs = this.ExecuteQuery(consulta);
            rsmd = rs.getMetaData();
            modelo = this.CreateTableModel(rsmd.getColumnCount());
            fila = new Object[rsmd.getColumnCount()];
            for(int i = 0; i < rsmd.getColumnCount(); i++){
                modelo.addColumn(rsmd.getColumnLabel(i+1));
            }
            while(rs.next()){         
                for(int i = 0; i < rsmd.getColumnCount();i++){
                    if(TipodeObjeto[i] == 2){                    
                        ImageIcon image = new ImageIcon(new ImageIcon(rs.getString(i+1)).getImage().getScaledInstance(62, 62, 0));      
                        fila[i] = image;
                    }else if(TipodeObjeto[i] == 1){
                        fila[i] = this.Desencriptar(String.valueOf(rs.getObject(i+1)));
                    }else{
                    fila[i] = rs.getObject(i+1);
                    }
                }
                modelo.addRow(fila);
            }
            tbl.setModel(modelo);
            tbl.setFont(new java.awt.Font("Ubuntu Light", Font.NORMAL, 14));
            tbl.setForeground(Color.BLACK);
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer(){
                java.awt.Font f = new java.awt.Font("Ubuntu", Font.NORMAL, 16);
                
                @Override
                public void setOpaque(boolean isOpaque) {
                    super.setOpaque(true); //To change body of generated methods, choose Tools | Templates.
                }                
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setFont(f);
                    return this;
                }
            };
            headerRenderer.setBackground(new Color(85, 86, 87));
            headerRenderer.setForeground(Color.WHITE);
            for (int i = 0; i < tbl.getModel().getColumnCount(); i++) {
                tbl.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }
        }catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }catch (Exception ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
    }
    
//    public String getDate(JCalendarCombo calendario){ //Metodo para extraer la fecha.
//        int dia, mes, año;
//        Calendar c = calendario.getCalendar();
//        c.add(Calendar.DAY_OF_MONTH, 2);
//        Date d = c.getTime();
//        return formato_fecha.format(d);
//    }
    
    public Date setDate(String fecha){ //Metodo para setear la fecha
        Date d = null;
        try {        
            d = formato_fecha.parse(fecha);
        } catch (ParseException ex) {
            this.Error(ex.toString());
        }
        return d;
    }
    
    public boolean ValidarFecha(Date fecha){ 
        Date start = null, end = null;
        int año = Calendar.getInstance().getTime().getYear() + 1900;
        int mes = Calendar.getInstance().getTime().getMonth() + 1;
        int dia = Calendar.getInstance().getTime().getDate();
        try{            
            start = formato_fecha.parse(""+(año-85)+"-"+mes+"-"+dia+"");
            end = formato_fecha.parse(""+(año-18)+"-"+mes+"-"+dia+"");
        } catch (ParseException ex) {
            this.Error(ex.toString());
        }
        return fecha.before(end) && fecha.after(start);
    }

    public void setFotoInicial(JLabel lbl){
            try{
                File archivo = new File("archives/images/not_found.png");
                tempImage = ImageIO.read(archivo.toURL());
                String ruta = archivo.getPath();
                ImageIcon imagen = new ImageIcon(ruta);
                Image img = imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
                Icon icono = new ImageIcon(img);    
                lbl.setIcon(icono);
            }catch(IOException ex){
                this.Error(ex.toString());
            }
    }

    public void setFoto(JLabel lbl){
            try {
                JFileChooser Buscador = new JFileChooser();
                Buscador.showOpenDialog(null);
                File archivo = Buscador.getSelectedFile();
                tempImage = ImageIO.read(Buscador.getSelectedFile().toURL());
                String ruta = archivo.getPath();
                ImageIcon imagen = new ImageIcon(ruta);
                Image img = imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH);
                Icon icono = new ImageIcon(img);    
                lbl.setIcon(icono);
            }catch(IOException ex){
                this.Error(ex.toString());
            }
        }

    public String GuardarFoto(String nombrefoto){
        String url_foto = "";
        try{
            File archivoaguardar = new File("archives/images/"+nombrefoto+".png");
            url_foto = archivoaguardar.getPath();
            ImageIO.write(tempImage, "png", archivoaguardar);
        }catch(IOException ex){
            this.Error(ex.toString());
        }
        return url_foto;
    }

    public String ModificarFoto(String nombrefoto, String rutaantiguafoto){
        String url_foto = "";
        try{
            File archivoaborrar = new File(rutaantiguafoto);
            archivoaborrar.delete();
            File archivoaguardar = new File("archives/images/"+nombrefoto+".png");
            url_foto = archivoaguardar.getPath();
            ImageIO.write(tempImage, "png", archivoaguardar);
        }catch(IOException ex){
            this.Error(ex.toString());
        }
        return url_foto;
    }

    public void EliminarFoto(String ruta){
        File f = new File(ruta);
        f.delete();
    }

    public void getIdioma(){ //Metodo para extraer el idioma guardado
//        try{
//        rs = this.ExecuteQuery("SELECT idioma_conf FROM Configuracion");
//        while(rs.next()){
//            this.idioma = rs.getInt(1);
//        }
//        }catch(SQLException ex){
//            this.showMessage(ex.toString(), "Error", "Error");
//        }
    }

    public boolean setIdioma(int idioma) throws SQLException{ //Metodo para actualizar el idioma
//        this.idioma = idioma;
//        return this.Execute("UPDATE Configuracion SET idioma_conf = "+idioma+"");
        return false;
    }
    
    public void focusOn(JTextComponent txt){
        if(txt.getForeground() != Color.black){
            txt.setForeground(Color.black);
            txt.setText(null);
        }
    }
    
    public void focusOff(JTextComponent txt,String texto){
        if("".equals(txt.getText())){
            txt.setForeground(Color.gray);
            txt.setText(texto);
        }
    }
    
    public void showWithTransition(final Frame actual, final Frame nuevo){ //Metodo para menu    
        final frmTransicion t = new frmTransicion();
        fadenuevo = new Timer (1, new ActionListener () { 
        @Override
        public void actionPerformed(ActionEvent e) 
        { 
               float f = 0.0f;
            while(f < 1.0f){
               nuevo.setOpacity(f);
                f += 0.00001f;            
            }
            t.setAlwaysOnTop(false);
            nuevo.toFront();
            t.dispose();
            fadenuevo.stop();
         } 
            });
        fadeactual = new Timer (1, new ActionListener () { 
        @Override
        public void actionPerformed(ActionEvent e) 
        { 
               float f = 0.0f;

            while(f < 1.0f){
            t.setOpacity(f);
            f += 0.0000020f;
            }
            actual.dispose();
            transicion.start();
            fadeactual.stop();
         } 
    });
        transicion = new Timer (100, new ActionListener () { 
        @Override
        public void actionPerformed(ActionEvent e) 
        { 
            nuevo.show();
        nuevo.setOpacity(0.0f);
        fadenuevo.start();    
        nuevo.setAlwaysOnTop(false);   
            transicion.stop();
         } 
            });

        float f = 0.0f;
        t.show();
        t.setOpacity(f);
        fadeactual.start();
    }

    public void GenerateDatabase(){ //Metodo para generacion de la base
        
    }

    public void Restart() { //Metodo para reiniciar el programa
            try {
                Runtime.getRuntime().exec("java -jar Reinicializador.jar");
                Runtime.getRuntime().exec("java -jar dist/Reinicializador.jar");
                System.exit(0);
            } catch (IOException ex) {            
                this.showMessage(ex.toString(), "Error", "Error");
            }
    }

    public boolean ValidarTarjeta(String tarjeta){
        int i, sumatotal, digito;
        String reverse_tarjeta, cadenatotal;
        
        // Primero, pasamos al reves el numero de tarjeta y quitamos todos los caracteres no numericos.
        
        reverse_tarjeta = "";
        for(i = 0; i < tarjeta.length(); i++){
            digito = Integer.parseInt(String.valueOf(tarjeta.charAt(i)), 10);
            if (digito >= 0 && digito <= 9){
                reverse_tarjeta = digito + reverse_tarjeta;
            }
        }        
        
        // Validamos que sea una tarjeta de credito o debito.
        
        if (reverse_tarjeta.length() <= 1){
            return false;
        }
        
        // Ahora todos los numeros que esten en posicion de multiplos pares se multiplican por 2
        // y los resultados se añaden a la cadena sustituyendo al anterior numero, formando una
        // cadena un poco mas larga.
        
        cadenatotal = "";
        for(i = 0; i < reverse_tarjeta.length(); i++){
            digito = Integer.parseInt(String.valueOf(reverse_tarjeta.charAt(i)), 10);
            if (i % 2 != 0){
                digito *= 2;
            }
            cadenatotal = cadenatotal + digito;
        }
        
        // Finalmente, se junta toda la cadena para sumar y validar mediante el algoritmo de Luhn.
        
        sumatotal = 0;
        for(i = 0; i < cadenatotal.length(); i++){
            digito = Integer.parseInt(String.valueOf(cadenatotal.charAt(i)), 10);
            sumatotal = sumatotal + digito;
        }
        return sumatotal != 0 && sumatotal % 10 == 0;
    }
    
    public String GenerarTarjeta(int tipo_tarjeta){
        String tar = "", tarjeta;
	int tipo = 0, largo = 0, sum = 0, digito_final, t, len_offset;
	int[] str = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        
	if(tipo_tarjeta == 1){        //Tarjetas de Debito
            str[0] = 5;
            t = (int) (Math.floor(Math.random() * 5) % 5);
            str[1] = (int) (1 + t);	  // Desde 1 hasta 5.
            tipo = 2;
            largo = 16;
	}else if(tipo_tarjeta == 2){  //Tarjetas de Credito
            str[0] = 6;
            str[1] =0;
            str[2] = 1;
            str[3] = 1;
            tipo = 4;
            largo = 16;
        }

	// Llena todos los numeros excepto el numero final que es el verificador.

	while(tipo < largo - 1){
            str[tipo++] = (int) (Math.floor(Math.random() * 10) % 10);
        }

	// Construye el numero de la tarjeta mediante el algoritmo de Luhn.

	len_offset = (largo + 1) % 2;
	for(tipo = 0; tipo < largo - 1; tipo++){
            if((tipo + len_offset) % 2 > 0){
                t = str[tipo] * 2;
                if(t > 9){
                    t -= 9;
                }
                sum += t;
            }else{
                sum += str[tipo];
            }
	}

	// Calcula el ultimo digito que sera el numero de verificacion de la tarjeta.

	digito_final = (10 - (sum % 10)) % 10;
	str[largo - 1] = digito_final;
	for(int i = 0; i < str.length; i++){
            tar += str[i];
        }
	tarjeta = tar.substring(0, largo);
        return tarjeta;
    }
    
    public void crearReporte(String tituloreporte, String consulta, int[] TipodeObjeto,Document documento){
        try{
            FileOutputStream FacturaPdf;
            FacturaPdf = new FileOutputStream("archives\\Report.pdf");
            PdfWriter writer = PdfWriter.getInstance((com.itextpdf.text.Document) documento, FacturaPdf);
            com.itextpdf.text.Rectangle rct = new com.itextpdf.text.Rectangle(36, 54, 559, 788);
            writer.setBoxSize("art", rct);
            HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);
            Calendar fecha = Calendar.getInstance();
            documento.open();
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance("archives\\images\\SCTB.png");
            imagen.setAbsolutePosition(0f, 0f);
            documento.add(imagen);
            Paragraph AV = new Paragraph("Sistema de Control Total Bancario", FontFactory.getFont("Segoe UI", 22, Font.NORMAL, BaseColor.BLACK));
            AV.setAlignment(Element.ALIGN_RIGHT);
            documento.add(AV);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));           
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(tituloreporte));
            documento.add(new Paragraph("Fecha de Reporte: "+fecha.get(Calendar.DAY_OF_MONTH)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR)+"  "+fecha.get(Calendar.HOUR_OF_DAY)+":"+fecha.get(Calendar.MINUTE)));
            documento.add(new Paragraph(" "));           
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));           
            documento.add(new Paragraph(" "));
            
            
            rs = this.ExecuteQuery(consulta);
            rsmd = rs.getMetaData();
            PdfPTable tabla = new PdfPTable(rsmd.getColumnCount());
            for(int i = 0; i < rsmd.getColumnCount(); i++){
                tabla.addCell(rsmd.getColumnLabel(i+1));
            }
            while(rs.next()){         
                for(int i = 0; i < rsmd.getColumnCount();i++){
                    if(TipodeObjeto[i] == 1){
                        tabla.addCell(this.Desencriptar(String.valueOf(rs.getObject(i+1))));
                    }else{
                        tabla.addCell((String) rs.getObject(i+1));
                    }
                }
            }
            documento.add(tabla);
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" ")); 
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));    
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(P);
            documento.close();
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"archives\\Report.pdf");
        }catch(Exception ex){
            this.Error(ex.toString());
        }
         
    }
    static class HeaderFooter extends PdfPageEventHelper {
        private final String encabezado = "Sistema de Control Total Bancario";
        PdfTemplate total;      
    
        @Override
            public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
            String Pagina = String.format("Pagina "+writer.getPageNumber());
            P = new Paragraph(Pagina);
            P.setAlignment(Element.ALIGN_RIGHT);
    }
    }
    
    public String capitalizarTexto(String textoSinFormato){
        String []palabras = textoSinFormato.split("\\s+");
        StringBuilder textoFormateado = new StringBuilder();
        
        for(String palabra : palabras){
            textoFormateado.append(palabra.substring(0,1).toUpperCase()
        	    	.concat( palabra.substring(1,palabra.length())
        		.toLowerCase()).concat(" "));
        }
        
        return textoFormateado.toString();        
    }
    
    public void Hora(final JLabel lbl){
        Timer timer = new Timer (1000, new ActionListener () { 
    @Override
    public void actionPerformed(ActionEvent e) 
    { 
        
      Date fecha = new Date();
      SimpleDateFormat dia = new SimpleDateFormat("E");      
      SimpleDateFormat m = new SimpleDateFormat("M");
      SimpleDateFormat hora = new SimpleDateFormat("dd',' hh:mm a");
      
      String mes = "";
      
      switch(Integer.parseInt(m.format(fecha))){
          case 1:
              mes = "Ene";
              break;
                  case 2:
                      mes = "Feb";
              break;
                      case 3:
                          mes = "Mar";
              break;
                          case 4:
                              mes = "Abr";
              break;
                              case 5:
                                  mes = "May";
              break;
                                  case 6:
                                      mes = "Jun";
              break;
                                      case 7:
                                          mes = "Jul";
              break;
                                          case 8:
                                              mes = "Ago";
              break;
                                              case 9:
                                                  mes = "Sept";
              break;
                                                  case 10:
                                                      mes = "Oct";
              break;
                                                      case 11:
                                                          mes = "Nov";
              break;
                                                          case 12:
                                                              mes = "Dic";
              break;
      }
      
      lbl.setText(capitalizarTexto(dia.format(fecha))+" "+mes+" "+hora.format(fecha));
      
     } 
});
        timer.start();
    }
}
