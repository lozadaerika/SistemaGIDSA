/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagidsa;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static sistemagidsa.ExperienciaProfesional.convertUtilDateToSqlDate;

/**
 *
 * @author Andrés
 */
public class FormacionAcademica extends javax.swing.JFrame {

    /**
     * Creates new form FormacionAcademica
     */
    public FormacionAcademica() {
        initComponents();
        titulos=new String[6];
        titulos[0]="Formacion";
        titulos[1]="Nombre de la Institucion";
        titulos[2]="Estado de formación";
        titulos[3]="Año";
        titulos[4]="Nivel";
        titulos[5]="Titulo";
        //inicio();
        tblDatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblDatos.getSelectedRow()!=-1){
                    int fila=tblDatos.getSelectedRow();
                    jcbNivel.setSelectedItem(String.valueOf(tblDatos.getValueAt(fila, 0)));
                    txtNombre_Ins.setText(String.valueOf(tblDatos.getValueAt(fila, 1)));
                    jcbEstado.setSelectedItem(String.valueOf(tblDatos.getValueAt(fila, 2)));
                    jdcAnio.setYear(Integer.valueOf(String.valueOf(tblDatos.getValueAt(fila, 3))));
//                    jspNivel.setValue(Integer.valueOf(String.valueOf(tblDatos.getValueAt(fila, 4))));
                    txtTitulo.setText(String.valueOf(tblDatos.getValueAt(fila, 5)));
                }
            }
        });
    }
    
    public void inicio(){
        txtNombre_Ins.setEnabled(false);
        txtTitulo.setEnabled(false);
        jcbEstado.setEnabled(false);
        jcbNivel.setEnabled(false);
        jdcAnio.setEnabled(false);
        jspNivel.setEnabled(false);
    }
    String datos[];
    DefaultTableModel modelo;
    String titulos[];
    public void cargarTabla(){
        String sql= "SELECT NIV_FOR,NOM_INS,FINALIZADO,ANO,NIVEL_ALC,TITULO FROM FORMACION_ACADEMICA "
                + "WHERE NUM_CED = '"+txtCed_emp.getText()+"'";
        conexion cc = new conexion();
        Connection cn=cc.conectar("ANDRES");
        datos=new String[6];
        modelo=new DefaultTableModel(null, titulos);
        try{
            PreparedStatement psd=cn.prepareStatement(sql);
            ResultSet rs=psd.executeQuery();
            modelo=new DefaultTableModel(null, titulos);
            while(rs.next()){
                datos[0]=rs.getString("NIV_FOR");
                datos[1]=rs.getString("NOM_INS");
                datos[2]=rs.getString("FINALIZADO");
                datos[3]=rs.getString("ANO");
                datos[4]=rs.getString("NIVEL_ALC");
                datos[5]=rs.getString("TITULO");
                modelo.addRow(datos);
            }
            tblDatos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se ha podido realizar el SELECT"+e);
        }
    }
    public void seleccionarUltimoKey(){
            try{
                conexion cc = new conexion();
             Connection cn=cc.conectar("ANDRES");
             String sentencia="SELECT MAX(COD_FOR_ACA) FROM FORMACION_ACADEMICA";
             Statement statement1=cn.createStatement();
             JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
             ResultSet rs=statement1.executeQuery(sentencia);
             String key="";
             if(rs!=null){
                 while(rs.next()){
                 key=rs.getString(1);
                 }
             int keyN=Integer.parseInt(key)+1;
                   lblCodigo.setText(lblCodigo.getText()+String.valueOf(keyN));
             }            
             
             JOptionPane.showMessageDialog(null, "Registro seleccionado! "+ key);
             }catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, "Error al seleccionar "+ex.getMessage());
             }
      
    }
    
public void Insertar(){
 try{
    conexion cc = new conexion();
 Connection cn=cc.conectar("ANDRES");
 String sentencia="insert into FORMACION_ACADEMICA(COD_FOR_ACA,NUM_CED,NIV_FOR,NOM_INS,FINALIZADO,ANO,NIVEL_ALC,TITULO) "
                                + "values(6"+",'"+txtCed_emp.getText()+"', '"
                                + jcbNivel.getSelectedItem().toString().substring(0,1)+"', '"
                                + txtNombre_Ins.getText()+"', '"
                                + jcbEstado.getSelectedItem().toString()+"', '"
                                + String.valueOf(jdcAnio.getYear())+"', '"
                                + String.valueOf(jspNivel.getValue())+"', '"
                                + txtTitulo.getText()+"')";
 Statement statement1=cn.createStatement();
 JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
                       statement1.executeUpdate(sentencia);
   cn.commit();
 JOptionPane.showMessageDialog(null, "Registro ingresado! ");
 }catch(SQLException ex){
     JOptionPane.showMessageDialog(null, "Error al insertar "+ex.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        lblCodigo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jspNivel = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jdcAnio = new com.toedter.calendar.JYearChooser();
        txtNombre_Ins = new javax.swing.JTextField();
        jcbEstado = new javax.swing.JComboBox<>();
        txtCed_emp = new javax.swing.JTextField();
        lblEmpleado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcbNivel = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null,  new Integer(0), null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Formacion", "Nombre de la Institucion", "Estado de formación", "Año", "Nivel", "Titulo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblDatosKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblDatos);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Datos de Formación Académica");

        lblCodigo.setText("Codigo: ");
        lblCodigo.setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Titulo");

        jLabel5.setText("Nivel Alcanzado");

        jLabel4.setText("Año de finalización");

        jLabel3.setText("Nivel de Formación Finalizado");

        jLabel9.setText("Empleado");

        jLabel2.setText("Nombre de la institucion");

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "N", "E" }));
        jcbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbEstadoItemStateChanged(evt);
            }
        });

        txtCed_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCed_empActionPerformed(evt);
            }
        });
        txtCed_emp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCed_empKeyReleased(evt);
            }
        });

        jLabel1.setText("Nivel de Formacion");

        jcbNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Primaria", "Secundaria", "Universidad", "Posgrado", "Doctorado", "PHD" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(44, 44, 44)
                        .addComponent(txtTitulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(60, 60, 60)
                                .addComponent(txtNombre_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCed_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lblEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jspNivel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcAnio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtCed_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jcbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre_Ins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jspNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");

        jButton4.setText("Ver Formacion Academica");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Modificar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCodigo)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Insertar();
        cargarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cargarTabla();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCed_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCed_empActionPerformed
        if (txtCed_emp.getText().length()==10)
            buscarEmpleado(txtCed_emp.getText());
        else
            lblEmpleado.setText("");
            
    }//GEN-LAST:event_txtCed_empActionPerformed

    private void jcbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbEstadoItemStateChanged
        if (jcbEstado.getSelectedItem()=="SI")
        {
            jdcAnio.setEnabled(true);
            jspNivel.setEnabled(true);
            txtTitulo.setEnabled(true);
        }
    }//GEN-LAST:event_jcbEstadoItemStateChanged

    private void tblDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDatosKeyReleased
    int fila=tblDatos.getSelectedRow();
    //modificarTabla(fila);
    }//GEN-LAST:event_tblDatosKeyReleased

    private void txtCed_empKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCed_empKeyReleased
        if (txtCed_emp.getText().length()==10)
        {
            
        }
    }//GEN-LAST:event_txtCed_empKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
//    public void modificarTabla(int fila)
//    {
//        conexion cc=new conexion();
//        Connection cn=(Connection) cc.conectar();
//        String sql="";
//        sql="UPDATE GIDSAS SET VIA_CAR_PLACA='"+String.valueOf(tblViajes.getValueAt(fila, 1)).toUpperCase() +"', "
//                            + "VIA_COD_PRO_SAL='"+tblViajes.getValueAt(fila, 2) +"', "
//                            + "VIA_COD_PRO_LLE='"+String.valueOf(tblViajes.getValueAt(fila, 3)).toUpperCase() +"', "
//                            + "VIA_FECSALIDA='"+String.valueOf(tblViajes.getValueAt(fila, 4)).toUpperCase() +"', "
//                            + "VIA_FECLLEGADA='"+String.valueOf(tblViajes.getValueAt(fila, 5)).toUpperCase() +"', "
//                            + "VIA_KILOMETRAJE='"+tblViajes.getValueAt(fila, 6) +"' ,"
//                            + "VIA_HORASALIDA='"+String.valueOf(tblViajes.getValueAt(fila, 7)).toUpperCase() +"' ,"
//                            + "VIA_HORALLEGADA='"+String.valueOf(tblViajes.getValueAt(fila, 8)).toUpperCase() +"' ,"
//                            + "VIA_OBSERVACION='"+String.valueOf(tblViajes.getValueAt(fila, 9)).toUpperCase() +"'"
//                +"WHERE VIA_CODIGO='"+tblViajes.getValueAt(fila, 0) +"'";    
//        try {
//            PreparedStatement psd=(PreparedStatement) cn.prepareStatement(sql);     
//            int n=psd.executeUpdate();
//            if(n>0){
//                System.out.println("Se actualizo correctamente");
//                cargarTabla(txtBuscarCodigo.getText());
//            }
//      }catch(Exception ex){
//            JOptionPane.showMessageDialog(null, ex); 
//      }
//    }
    public void buscarEmpleado(String cedula)
    {
        String sql= "SELECT (NOM_EM_1,NOM_EM_2,APE_EM_1,APE_EM_2) FROM EMPLEADOS"
                + "WHERE NUM_CED = '"+cedula+"'";
        conexion cc = new conexion();
        Connection cn=cc.conectar("ANDRES");
        try{
            PreparedStatement psd=cn.prepareStatement(sql);
            ResultSet rs=psd.executeQuery();
            modelo=new DefaultTableModel(null, titulos);
            if(rs.next()){
                lblEmpleado.setText(rs.getString("NOM_EM_1")+
                                    rs.getString("NOM_EM_2")+
                                    rs.getString("APE_EM_1")+
                                    rs.getString("APE_EM_2"));
            }
            else
            {
                lblEmpleado.setText("No existe dicho empleadp");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se ha podido realizar el SELECT"+e);
        }
    }
    public void limpiar()
    {
        txtCed_emp.setText("");
        txtNombre_Ins.setText("");
        txtTitulo.setText("");
        jspNivel.setValue(0);
        jdcAnio.setYear(2005);
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
            java.util.logging.Logger.getLogger(FormacionAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormacionAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormacionAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormacionAcademica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormacionAcademica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbEstado;
    private javax.swing.JComboBox<String> jcbNivel;
    private com.toedter.calendar.JYearChooser jdcAnio;
    private javax.swing.JSpinner jspNivel;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtCed_emp;
    private javax.swing.JTextField txtNombre_Ins;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
