/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagidsa;

import conexion.conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Adrian
 */
public class DatosPersonales extends javax.swing.JFrame {

    /**
     * Creates new form DatosPersonales
     * Datos Personales
     */
    public DatosPersonales() {
        initComponents();
        jcbNombreDis.setEnabled(false);
        cargarDiscapacidades();
    }
    public java.lang.String foto;
    public java.lang.String nomDis;
    
    public void Limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
    }
    public void cargarDiscapacidades(){
        conexion cc = new conexion();
        Connection cn=cc.conectar("ADRIAN\\DARIO");
        String sql="";
        sql="select * from discapacidades";
        try{
        Statement psd= cn.createStatement();
        ResultSet rs = psd.executeQuery(sql);
        while(rs.next()){
          String NOM_DIS=rs.getString("NOM_DIS");
          jcbNombreDis.addItem(NOM_DIS);
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error Al Cargar Las Discapacidades");
        }
    }
    public void bajarFoto() throws SQLException, IOException{
        ResultSet rsFotos = null;
                    ByteArrayOutputStream ouput=new ByteArrayOutputStream();
                    InputStream isdatos = null;
                        isdatos = rsFotos.getBinaryStream(8);
                    int temp=isdatos.read();
                    while(temp>=0)
                    {
                       ouput.write((char)temp);
                       temp=isdatos.read();

                    }
        Image imagen=Toolkit.getDefaultToolkit().createImage(ouput.toByteArray());
        imagen=imagen.getScaledInstance(110, 120, 1);
//        jlbFoto.setIcon(new ImageIcon(imagen));
    }
    public String subirfoto(){
        File fichero=null;
        int longitudBytes;
        JFileChooser se = new JFileChooser();
        FileInputStream fisfoto;
        se.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int estado = se.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            fichero = se.getSelectedFile();
            try {
                fisfoto = new FileInputStream(se.getSelectedFile());
                longitudBytes = (int) se.getSelectedFile().length();
                Image icono = ImageIO.read(se.getSelectedFile()).getScaledInstance(130, 165, Image.SCALE_DEFAULT);
                jlbFoto.setIcon(new ImageIcon(icono));
                jlbFoto.updateUI();
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return fichero.toString();
    }
    public void copy(String origen1){
        String archi =  origen1.toString();
            int a=archi.length(),con=0;
            for(int i=0;i<a;i++){
                char aux=archi.charAt(i);
                if(aux=='\\'){
                    con=i;                
                }
            }
        String fin=archi.substring(con+1),destino1;
        destino1="C:/Fotos/"+fin;
        File in = new File(archi);
        File out = new File(destino1);
        FileChannel inChannel, outChannel;
        try {
            inChannel = new FileInputStream(in).getChannel();
            outChannel = new FileOutputStream(out).getChannel();
            //txtUrl.setText(destino1);
            inChannel.transferTo(0, inChannel.size(), outChannel);
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+ e);
        }
    }
    public void guardar(){
        if(txtNombre.getText().isEmpty()){
            //JOptionPane.showMessageDialog(null, "Debe ingresar la placa");
//            txtApellido.requestFocus();
//        }else  if(txtApellido.getText().isEmpty()){
//                    //JOptionPane.showMessageDialog(null, "Debe ingresar la marca");
//                       jdcFechaNacimiento.requestFocus();
//                         }else if(jdcFechaNacimiento.getDate()==null){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la color");
//                            txtCedula.requestFocus();
//                            }else if(txtCedula.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la Modelo");
//                             jcbEstadoCivil.requestFocus();
//                            }else if(jcbEstadoCivil.getSelectedItem().toString().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la Año");
//                            txtTelefono.requestFocus();
//                            }else if(txtTelefono.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la Capacidad");
//                            txtCorreo.requestFocus();
//                            }else if(txtCorreo.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtCiudad.requestFocus();
//                            }else if(txtCiudad.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            jcbProvincia.requestFocus();
//                            }else if(jcbProvincia.getSelectedItem().toString().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            jcbDiscapacidad.requestFocus();
//                            }else if(jcbDiscapacidad.getSelectedItem().toString().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtDiscapacidad.requestFocus();
//                            }else if(txtDiscapacidad.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtCelular.requestFocus();
//                            }else if(txtCelular.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtNacionalidad.requestFocus();
//                            }else if(txtNacionalidad.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtDireccion.requestFocus();
//                            }else if(txtDireccion.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtFolio.requestFocus();
//                            }else if(txtFolio.getText().isEmpty()){
//                            //JOptionPane.showMessageDialog(null, "Debe ingresar la observacion");
//                            txtMotivo.requestFocus();
        }else{
        conexion cc = new conexion();
        Connection cn=cc.conectar("ADRIAN\\DARIO");
        String sql="";  int control=0;
        String numCed,nomEm1,nomEm2,apeEm1,apeEm2,fecNacEm,estadoCiEm,telEm,celEm,corrEm,perRefEm,relPerEm
                ,telPerRefEm,perDis,nacOrEm,ciuEm,dirEm,genero,activo,ubiFis,fecIngEmEm,fecSalEmEm,motSalEmEm,nomPro;
        nomEm1=txtNombre.getText(); nomEm2=txtNombre.getText(); apeEm1=txtApellido.getText(); apeEm2=txtApellido.getText();
        fecNacEm = new SimpleDateFormat("yyyy-MM-dd").format(jdcFechaNacimiento.getDate()); numCed=txtCedula.getText();
        estadoCiEm=jcbEstadoCivil.getSelectedItem().toString().substring(0, 2); telEm=txtTelefono.getText(); corrEm=txtCorreo.getText();
        ciuEm=txtCiudad.getText(); nomPro=jcbProvincia.getSelectedItem().toString(); perDis=jcbDiscapacidad.getSelectedItem().toString().substring(0, 1);
        celEm=txtCelular.getText(); nacOrEm=txtNacionalidad.getText(); dirEm = txtDireccion.getText();
        ubiFis=txtFolio.getText(); motSalEmEm=txtMotivo.getText(); perRefEm= txtNombreReferencia.getText(); 
        relPerEm= jcbRelacion.getSelectedItem().toString(); telPerRefEm=txtTelefonoReferencia.getText();
        genero=jcbGenero.getSelectedItem().toString().substring(0, 1); 
        fecIngEmEm=new SimpleDateFormat("yyyy-MM-dd").format(jdcFechaIngreso.getDate());
        control=0;
        if(jcbDiscapacidad.getSelectedItem()=="Seleccionar"||jcbDiscapacidad.getSelectedItem()=="No")
            nomDis="Ninguna";
        else
            nomDis=jcbNombreDis.getSelectedItem().toString();
        if(jcbDiscapacidad.getSelectedItem()=="Seleccionar")
            control++;
        if(jcbGenero.getSelectedItem()=="Seleccionar")
            control++;
        if(jcbRelacion.getSelectedItem()=="Seleccionar")
            control++;
        if(jcbProvincia.getSelectedItem()=="Seleccionar")
            control++;
        if(jcbEstadoCivil.getSelectedItem()=="Seleccionar")
            control++;
        if(control<=0){
        if(jdcFechaSalida.getDate()==null)
            fecSalEmEm="";
        else
            fecSalEmEm=new SimpleDateFormat("yyyy-MM-dd").format(jdcFechaSalida.getDate());
        sql="insert into EMPLEADOS (NUM_CED,NOM_EM_1,NOM_EM_2,APE_EM_1,APE_EM_2,FEC_NAC_EM,ESTADO_CI_EM,TEL_EM,CEL_EM,CORR_EM,PER_REF_EM"
                + ",REL_PER_EM,TEL_PER_REF_EM,PER_DIS,NAC_OR_EM,CIU_EM,DIR_EM,GENERO,ACTIVO,FOTO,UBI_FIS,FEC_ING_EM_EM,FEC_SAL_EM_EM,MOT_SAL_EM_EM"
                + ",NOM_DIS,NOM_PRO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            System.out.println("fe nac "+fecNacEm);
            System.out.println("fe in "+fecIngEmEm);
            System.out.println("fe sal "+fecSalEmEm);
            System.out.println("nom "+nomEm1+ " nom2 "+nomEm2+ " ced "+numCed + " ape "+apeEm1+ " ape2 "+nomEm2+ " genero "+genero
            + " dis "+perDis+ " nomDis "+nomDis+ " estad "+estadoCiEm);
            PreparedStatement psd = cn.prepareStatement(sql);
            psd.setString(1, numCed);       psd.setString(2, nomEm1);       psd.setString(3, nomEm2);
            psd.setString(4, apeEm1);       psd.setString(5, apeEm2);       psd.setString(6, fecNacEm);
            psd.setString(7, estadoCiEm);   psd.setString(8, telEm);        psd.setString(9, celEm);
            psd.setString(10, corrEm);      psd.setString(11, perRefEm);    psd.setString(12, relPerEm);
            psd.setString(13, telPerRefEm); psd.setString(14, perDis);      psd.setString(15, nacOrEm);
            psd.setString(16, ciuEm);       psd.setString(17, dirEm);       psd.setString(18, genero);
            psd.setString(19, "S");         psd.setString(20, foto);        psd.setString(21, ubiFis); 
            psd.setString(22, fecIngEmEm);  psd.setString(23, fecSalEmEm);  psd.setString(24, motSalEmEm); 
            psd.setString(25, nomDis);      psd.setString(26, nomPro);     
            int n = psd.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Se inserto correctamente");
    //            limpiar();
    //            desbloquear();
    //            botonesIniciales();
    //            cargarTablaAuto1();
            }
        }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex);
                }
        }else{
            JOptionPane.showMessageDialog(null,"Error asegurese que todos los campos esten llenos");
            control=0;
        }
        control=0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lpnDatosPersonales = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jcbEstadoCivil = new javax.swing.JComboBox<>();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtNacionalidad = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jcbGenero = new javax.swing.JComboBox<>();
        txtFolio = new javax.swing.JTextField();
        txtMotivo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jcbDiscapacidad = new javax.swing.JComboBox<>();
        jbtFoto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jlbFoto = new javax.swing.JLabel();
        jdcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jdcFechaIngreso = new com.toedter.calendar.JDateChooser();
        jdcFechaSalida = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        jcbProvincia = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jcbNombreDis = new javax.swing.JComboBox<>();
        jpnReferencia = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNombreReferencia = new javax.swing.JTextField();
        txtTelefonoReferencia = new javax.swing.JTextField();
        jcbRelacion = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jbtGuardar = new javax.swing.JButton();
        jbtCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Apellido");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Fecha de Nacimiento");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Cedula");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Estado Civil");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Telefono");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Celular");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Correo");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Nacionalidad");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("Ciudad");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setText("Dirección");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setText("Genero");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("Foto");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("Folio");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setText("Fecha de Ingreso");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setText("Fecha de Salida");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setText("Motivo de la Salida");

        jcbEstadoCivil.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcbEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Casado", "Soltero", "Viudo", "Divorciado", "Union Libre" }));

        jcbGenero.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino", "Otros" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Tiene alguna discapacidad");

        jcbDiscapacidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcbDiscapacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Si", "No" }));
        jcbDiscapacidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbDiscapacidadItemStateChanged(evt);
            }
        });

        jbtFoto.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jbtFoto.setText("Seleccionar");
        jbtFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFotoActionPerformed(evt);
            }
        });

        jlbFoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setText("Provincia");

        jcbProvincia.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcbProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Azuay", "Bolivar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi", "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja", "Los Rios", "Manabi", "Morona Santiago", "Napo", "Orellana", "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas", "Sucumbios", "Tungurahua", "Zamora Chinchipe" }));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setText("Nombre Discapacidad");

        javax.swing.GroupLayout lpnDatosPersonalesLayout = new javax.swing.GroupLayout(lpnDatosPersonales);
        lpnDatosPersonales.setLayout(lpnDatosPersonalesLayout);
        lpnDatosPersonalesLayout.setHorizontalGroup(
            lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnDatosPersonalesLayout.createSequentialGroup()
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel19)
                            .addComponent(jLabel7))
                        .addGap(55, 55, 55))
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(104, 104, 104))
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jdcFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbGenero, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbDiscapacidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbEstadoCivil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jcbNombreDis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel17)
                            .addComponent(jbtFoto))
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(txtCelular))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        lpnDatosPersonalesLayout.setVerticalGroup(
            lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lpnDatosPersonalesLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jcbEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbtFoto)))
                            .addComponent(jdcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(jcbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbDiscapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jcbNombreDis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jdcFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(lpnDatosPersonalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jdcFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Nombre de Referenca");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Relación");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setText("Telefono");

        jcbRelacion.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jcbRelacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Padre", "Madre", "Hermano", "Hermana", "Hijo", "Hija", "Primo", "Prima", "Abuelo", "Abuela", "Pareja" }));

        javax.swing.GroupLayout jpnReferenciaLayout = new javax.swing.GroupLayout(jpnReferencia);
        jpnReferencia.setLayout(jpnReferenciaLayout);
        jpnReferenciaLayout.setHorizontalGroup(
            jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnReferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(78, 78, 78)
                .addGroup(jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreReferencia)
                    .addComponent(jcbRelacion, 0, 209, Short.MAX_VALUE)
                    .addComponent(txtTelefonoReferencia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnReferenciaLayout.setVerticalGroup(
            jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnReferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtNombreReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jcbRelacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnReferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTelefonoReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel22.setText("Información de Empleados");

        jbtGuardar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jbtGuardar.setText("Guardar");
        jbtGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtGuardarActionPerformed(evt);
            }
        });

        jbtCancelar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jbtCancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lpnDatosPersonales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtGuardar)
                        .addGap(44, 44, 44)
                        .addComponent(jbtCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(258, 258, 258))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGap(39, 39, 39)
                .addComponent(lpnDatosPersonales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtGuardar)
                    .addComponent(jbtCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFotoActionPerformed
        // TODO add your handling code here:
        //bajarFoto();
        foto=subirfoto();
    }//GEN-LAST:event_jbtFotoActionPerformed

    private void jbtGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtGuardarActionPerformed
        // TODO add your handling code here:
        guardar();
    }//GEN-LAST:event_jbtGuardarActionPerformed

    private void jcbDiscapacidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbDiscapacidadItemStateChanged
        // TODO add your handling code here:
        if(jcbDiscapacidad.getSelectedItem()=="Si")
            jcbNombreDis.setEnabled(true);
        else{
            jcbNombreDis.setEnabled(false);
            nomDis="";
        }
    }//GEN-LAST:event_jcbDiscapacidadItemStateChanged

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
            java.util.logging.Logger.getLogger(DatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatosPersonales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatosPersonales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtCancelar;
    private javax.swing.JButton jbtFoto;
    private javax.swing.JButton jbtGuardar;
    private javax.swing.JComboBox<String> jcbDiscapacidad;
    private javax.swing.JComboBox<String> jcbEstadoCivil;
    private javax.swing.JComboBox<String> jcbGenero;
    private javax.swing.JComboBox<String> jcbNombreDis;
    private javax.swing.JComboBox<String> jcbProvincia;
    private javax.swing.JComboBox<String> jcbRelacion;
    private com.toedter.calendar.JDateChooser jdcFechaIngreso;
    private com.toedter.calendar.JDateChooser jdcFechaNacimiento;
    private com.toedter.calendar.JDateChooser jdcFechaSalida;
    private javax.swing.JLabel jlbFoto;
    private javax.swing.JPanel jpnReferencia;
    private javax.swing.JPanel lpnDatosPersonales;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreReferencia;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoReferencia;
    // End of variables declaration//GEN-END:variables
}
