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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
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
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.mail.internet.MimeUtility;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import org.freixas.jcalendar.JCalendarCombo;
import pkgFormularios.frmLogin;
import pkgFormularios.frmMenu;
import pkgFormularios.frmTransicion;

public class Helper {
    public Statement stm;
    public PreparedStatement pstm;
    public Connection Conexion = null;
    public ResultSet rs;
    public ResultSetMetaData rsmd;
    public static int idioma = -1, nivel_usuario = -1;
    public float trans2 = 1.0f;
    public static String nom_usu = "";
    public static Paragraph P;
    public SimpleDateFormat formato_fecha = new SimpleDateFormat("yyyy-MM-dd");
    private final static String keyBuffer = "56af65d2";
    public BufferedImage tempImage;    
    public Timer fade, fadenuevo, fadeactual, transicion;
    
    public void ConnectforInitialize() throws SQLException{  //Necesario para construir la base si no se encuentra.
        try{            
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String DBLocation = "jdbc:sqlserver://localhost:1433;database = master;user = sa;password = prototypeC1";
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
        String DBLocation = "jdbc:sqlserver://localhost:1433;database = SCTB;user = sa;password = prototypeC1";
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
            String DBLocation = "jdbc:sqlserver://localhost:1433;database = SCTB;user = sa;password = prototypeC1";
            try{            
            Conexion = DriverManager.getConnection(DBLocation);        
                pstm = Conexion.prepareStatement(Consulta, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);        
            }
            catch(SQLException ex){
                 this.showMessage(ex.toString(), "Error", "Error");
            }
        }
        catch(ClassNotFoundException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
        return pstm;

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
            this.Error(""+ex.getErrorCode());
        }finally{
            PreparedSTM.close();
        }
        return dato;
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

    public static byte[] encode(byte[] b) throws Exception { //Usado para la encriptacion
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (OutputStream b64os = MimeUtility.encode(baos, "base64")) {
            b64os.write(b);
        }
    return baos.toByteArray();
}

    public static byte[] decode(byte[] b) throws Exception { //Usado para la desencriptacion
    ByteArrayInputStream bais = new ByteArrayInputStream(b);
    InputStream b64is = MimeUtility.decode(bais, "base64");
    byte[] tmp = new byte[b.length];
    int n = b64is.read(tmp);
    byte[] res = new byte[n];
    System.arraycopy(tmp, 0, res, 0, n);
    return res;
}

    private static SecretKeySpec getKey() { //Llave unica de encriptacion
    SecretKeySpec key = new SecretKeySpec(keyBuffer.getBytes(), "DES");
    return key;    
}

    public static String Desencriptar(String s) throws Exception { //Metodo de desencriptado
    String s1;
    if (s.indexOf("{DES}") != -1) {
        String s2 = s.substring("{DES}".length());
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(2, getKey());
        byte abyte0[] = cipher.doFinal(decode(s2.getBytes()));
        s1 = new String(abyte0);
    }else{
        s1 = s;
    }
    return s1;

}

    public static String Encriptar(String s) throws Exception { //Metodo de encriptado
    //DES (Data Encryption Standard) cipher in Electronic Codebook mode, with PKCS #5-style padding CREATED.
    byte abyte0[];   
    SecureRandom securerandom = new SecureRandom();
    securerandom.nextBytes(keyBuffer.getBytes());
    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    cipher.init(1, getKey());
    abyte0 = encode(cipher.doFinal(s.getBytes()));
    String data = "{DES}" + new String(abyte0);
    return data.trim();
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
    public void Message(String msj, String titulo){
        this.showMessage(msj, titulo, "Informacion");
    }
    public void Warning(String msj){
        this.showMessage(msj, "Warning", "Advertencia");
    }
    public void Error(String ex){
        this.showMessage(ex, "Error", "Error");
    }
    
    public boolean Confirm(String msj){
        return JOptionPane.showConfirmDialog(null, msj, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public void show(final JFrame actual, final JFrame nuevo){ //Metodo para expresar nuevos frames con fade
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
                JTextField txt = (JTextField) txt1;
                if("".equals(txt.getText())){
                    llenos = false;
                }
            }        
            return llenos;
        }
    
    public void LimpiarCampos(Object[] txts){ //Metodo para limpiar los campos
        for (Object txt1 : txts){
            JTextField txt = (JTextField) txt1;
            txt.setText(null);
        }    
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
        }catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }catch (Exception ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
    }
    
    public String getDate(JCalendarCombo calendario){ //Metodo para extraer la fecha.
        int dia, mes, año;
        Calendar c = calendario.getCalendar();
        c.add(Calendar.DAY_OF_MONTH, 2);
        Date d = c.getTime();
        return formato_fecha.format(d);
    }
    
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
        try{            
            start = formato_fecha.parse("1914-01-01");
            end = formato_fecha.parse("1995-01-01");
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
        try{
        rs = this.ExecuteQuery("SELECT idioma_conf FROM Configuracion");
        while(rs.next()){
            this.idioma = rs.getInt(1);
        }
        }catch(SQLException ex){
            this.showMessage(ex.toString(), "Error", "Error");
        }
    }

    public boolean setIdioma(int idioma) throws SQLException{ //Metodo para actualizar el idioma
        this.idioma = idioma;
        return this.Execute("UPDATE Configuracion SET idioma_conf = "+idioma+"");
    }

    public void showLogin(JFrame Actual){ //Muestra el login
        this.nom_usu = "";
        frmLogin login = new frmLogin();
        this.show(Actual, login);
    }

    public void showMenu(final JFrame actual){ //Metodo para menu
        final frmMenu nuevo = new frmMenu();    
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
        String Create, Idioma, SA1, SA2, SA3, SA4, 
               tblAdministradores, tblClientes, tblConfiguracion, 
               tblCreditos, tblCuentas, tblEmpleados, tblEstadosdeCuentas, 
               tblGerentes, tblMonedas, tblQuioskos, tblRemesas, 
               tblServiciosRealizados, tblSucursales, tblSuperAdministradores, 
               tblTarjetasdeCredito, tblTarjetasdeDebito, tblTiposdeCreditos, 
               tblTiposdeCuentas, tblTiposdeServicios, tblTiposdeTarjetas;
            Create = "create database SCTB2";
            tblSucursales = "create table Sucursales( "
                    + "id_sucursal int not null identity primary key, "
                    + "nom_sucursal varchar(30), "
                    + "ubicacion_sucursal varchar(100)"
                    + ") ";
                    tblGerentes = "create table Gerentes( " +
                    "id_gerente int not null identity primary key, " +
                    "id_sucursal int not null, " +
                    "foreign key(id_sucursal) references Sucursales(id_sucursal) on delete cascade on update cascade, " +
                    "dui_gerente varchar(9), " +
                    "nit_gerente varchar(17), " +
                    "nom_gerente varchar(30), " +
                    "ape_gerente varchar(30), " +
                    "fecnac_gerente datetime, " +
                    "correo_gerente varchar(30), " +
                    "tel1_gerente varchar(8), " +
                    "tel2_gerente varchar(8), " +
                    "usu_gerente varchar(100), " +
                    "contra_gerente varchar(100) " +
                    ")" ;
                    tblEmpleados = "create table Empleados(\n" +
                    "id_empleado int not null identity primary key,\n" +
                    "id_sucursal int not null,\n" +
                    "foreign key(id_sucursal) references Sucursales(id_sucursal) on delete cascade on update cascade,\n" +
                    "dui_empleado varchar(9),\n" +
                    "nit_empleado varchar(17),\n" +
                    "nom_empleado varchar(30),\n" +
                    "ape_empleado varchar(30),\n" +
                    "fecnac_empleado datetime,\n" +
                    "correo_empleado varchar(30),\n" +
                    "tel1_empleado varchar(8),\n" +
                    "tel2_empleado varchar(8),\n" +
                    "usu_empleado varchar(100),\n" +
                    "contra_empleado varchar(100)\n" +
                    ")" ;
                    tblRemesas = "create table Remesas(\n" +
                    "id_remesa int not null identity primary key,\n" +
                    "fecha_remesa datetime,\n" +
                    "cantidad_remesa money,\n" +
                    "clave_remesa varchar(30)\n" +
                    ")" ;
                    tblMonedas = "create table Monedas(\n" +
                    "id_moneda int not null identity primary key,\n" +
                    "nom_moneda varchar(15),\n" +
                    "val_moneda money,\n" +
                    "continente_moneda varchar(30)\n" +
                    ")" ;
                    tblClientes = "create table Clientes(\n" +
                    "id_cliente int not null identity primary key,\n" +
                    "id_cuenta int not null,\n" +
                    "foreign key(id_cuenta) references Cuentas(id_cuenta) on delete cascade on update cascade,\n" +
                    "dui_cliente varchar(9),\n" +
                    "nit_cliente varchar(17),\n" +
                    "nom_cliente varchar(30),\n" +
                    "ape_cliente varchar(30),\n" +
                    "fecnac_cliente datetime,\n" +
                    "correo_cliente varchar(30),\n" +
                    "tel1_cliente varchar(8),\n" +
                    "tel2_cliente varchar(8),\n" +
                    "dir_cliente varchar(100)\n" +
                    ")";
                    tblQuioskos = "create table Quioskos(\n" +
                    "id_quiosko int not null identity primary key,\n" +
                    "nom_quiosko varchar(30),\n" +
                    "ubicacion_quiosko varchar(100)\n" +
                    ")" ;
                    tblTiposdeServicios = "create table TiposdeServicios(\n" +
                    "id_tipservicio int not null identity primary key,\n" +
                    "nom_tipservicio varchar(30),\n" +
                    "desc_tipservicio varchar(100)\n" +
                    ")";
                    tblServiciosRealizados = "create table ServiciosRealizados(\n" +
                    "id_servicio int not null identity primary key,\n" +
                    "id_tipservicio int not null,\n" +
                    "foreign key(id_tipservicio) references TiposdeServicios(id_tipservicio) on delete cascade on update cascade,\n" +
                    "id_cuenta int not null,\n" +
                    "foreign key(id_cuenta) references Cuentas(id_cuenta) on delete cascade on update cascade,\n" +
                    "id_quiosko int not null,\n" +
                    "foreign key(id_quiosko) references Quioskos(id_quiosko) on delete cascade on update cascade\n" +
                    ")";
                    tblTiposdeCreditos = "create table TiposdeCreditos(\n" +
                    "id_tipcredito int not null identity primary key,\n" +
                    "nom_tipcredito varchar(30),\n" +
                    "desc_tipcredito varchar(100),\n" +
                    "montomax_tipcredito money\n" +
                    ")";
                    tblCreditos = "create table Creditos(\n" +
                    "id_credito int not null identity primary key,\n" +
                    "id_tipcredito int not null,\n" +
                    "foreign key(id_tipcredito) references TiposdeCreditos(id_tipcredito) on delete cascade on update cascade\n" +
                    ")";
                    tblEstadosdeCuentas = "create table EstadosdeCuentas(\n" +
                    "id_estcuenta int not null identity primary key,\n" +
                    "nom_estcuenta varchar(15),\n" +
                    "desc_estcuenta varchar(15)\n" +
                    ")";
                    tblTiposdeTarjetas = "create table TiposdeTarjetas(\n" +
                    "id_tiptarjeta int not null primary key,\n" +
                    "nom_tiptarjeta varchar(30),\n" +
                    "desc_tiptarjeta varchar(100)\n" +
                    ")";
                    tblTarjetasdeCredito = "create table TarjetasdeCredito(\n" +
                    "id_tarcredito int not null identity primary key,\n" +
                    "id_tiptarjeta int not null,\n" +
                    "foreign key(id_tiptarjeta) references TiposdeTarjetas(id_tiptarjeta) on delete cascade on update cascade,\n" +
                    "usuario_tarcredito varchar(100),\n" +
                    "pin_tarcredito varchar(4)\n" +
                    ")";
                    tblTarjetasdeDebito = "create table TarjetasdeDebito(\n" +
                    "id_tardebito int not null identity primary key,\n" +
                    "id_tiptarjeta int not null,\n" +
                    "foreign key(id_tiptarjeta) references TiposdeTarjetas(id_tiptarjeta) on delete cascade on update cascade,\n" +
                    "usuario_tardebito varchar(100),\n" +
                    "pin_tardebito varchar(4)\n" +
                    ")";
                    tblTiposdeCuentas = "create table TiposdeCuentas(\n" +
                    "id_tipcuenta int not null identity primary key,\n" +
                    "nom_tipcuenta varchar(30),\n" +
                    "desc_tipcuenta varchar(100)\n" +
                    ")";
                    tblCuentas = "create table Cuentas(\n" +
                    "id_cuenta int not null identity primary key,\n" +
                    "saldo_cuenta money,\n" +
                    "id_credito int,\n" +
                    "id_tarcredito int,\n" +
                    "id_tardebito int,\n" +
                    "id_tipcuenta int,\n" +
                    "foreign key (id_credito) references Creditos(id_credito) on delete cascade on update cascade,\n" +
                    "foreign key (id_tarcredito) references TarjetasdeCredito(id_tarcredito) on delete cascade on update cascade,\n" +
                    "foreign key (id_tardebito) references TarjetasdeDebito(id_tardebito)," +
                    "foreign key (id_tipcuenta) references TiposdeCuentas(id_tipcuenta) on delete cascade on update cascade\n" +
                    ")";
                    tblAdministradores = "create table Administradores(\n" +
                    "id_adm int not null identity primary key,\n" +
                    "nom_adm varchar(30),\n" +
                    "ape_adm varchar(30),\n" +
                    "usu_adm varchar(100),\n" +
                    "contra_adm varchar(100)\n" +
                    ")";
                    tblSuperAdministradores = "create table SuperAdministradores(\n" +
                    "id_superadm int not null identity primary key,\n" +
                    "usu_superadm varchar(100),\n" +
                    "contra_superadm varchar(100)\n" +
                    ")";
                    tblConfiguracion = "create table Configuracion(\n" +
                    "idioma_conf int\n" +
                    ")";
             Idioma = "INSERT INTO Configuracion(idioma_conf) VALUES(0)";
                SA1 = "INSERT INTO SuperAdministradores(usu_superadm, contra_superadm) VALUES('{DES}QGoqz7AvB9TDorTzvZTqCQ==','{DES}0Z3P8UMUJCJK7h1N4McdBA==')";
                SA2 = "INSERT INTO SuperAdministradores(usu_superadm, contra_superadm) VALUES('{DES}Q11h2N35d7+lPePxOz4FUg==','{DES}HMmJzdSCrfg=')";
                SA3 = "INSERT INTO SuperAdministradores(usu_superadm, contra_superadm) VALUES('{DES}obBFEZoUgw8=','{DES}A/vYXjrfgNdVGxfZutyDDw==')";
                SA4 = "INSERT INTO SuperAdministradores(usu_superadm, contra_superadm) VALUES('{DES}KzKTzYgUR3Q=','{DES}ohRdZ6h2LxY=')";
            try{

                if(this.ExecuteforInitialize(Create) == true){
    //                this.showMessage("Creada SCTB2.", "Informacion", "Informacion");               
                        this.Execute(tblSucursales);
                        this.Execute(tblGerentes);
                        this.Execute(tblEmpleados);
                        this.Execute(tblRemesas);
                        this.Execute(tblMonedas);
                        this.Execute(tblQuioskos);
                        this.Execute(tblTiposdeServicios);
                        this.Execute(tblTiposdeCreditos);
                        this.Execute(tblCreditos);
                        this.Execute(tblEstadosdeCuentas);
                        this.Execute(tblTiposdeTarjetas);
                        this.Execute(tblTarjetasdeCredito);
                        this.Execute(tblTarjetasdeDebito);
                        this.Execute(tblTiposdeCuentas);
                        this.Execute(tblCuentas);                    
                        this.Execute(tblClientes);
                        this.Execute(tblServiciosRealizados);
                        this.Execute(tblAdministradores);
                        this.Execute(tblSuperAdministradores);
                        this.Execute(tblConfiguracion);
    //                    this.showMessage("Base de Datos creada satisfactoriamente.", "Informacion", "Informacion");                    
                        this.Execute(Idioma);
                        this.Execute(SA1);
                        this.Execute(SA2);
                        this.Execute(SA3);
                        this.Execute(SA4);
    //                    this.showMessage("Proceso finalizado exitosamente.", "Informacion", "Informacion");                   
                }else{
                    this.showMessage("Error: Helper/GenerateDatabase/ExecuteCreate", "Error", "Error");
                }

            }catch(SQLException ex){
                this.showMessage(ex.toString(), "Error", "Error");
            }
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
        
        // Primero, pasamos al revez el numero de tarjeta y quitamos todos los caracteres no numericos.
        
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
            str[1] = 0;
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
}
