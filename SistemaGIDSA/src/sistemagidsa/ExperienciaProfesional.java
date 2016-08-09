/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemagidsa;
import java.sql.SQLData;
import conexion.conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import interfaces.ingreso;
/**
 *
 * @author Anita
 */
public class ExperienciaProfesional extends javax.swing.JFrame {

    /**
     * Creates new form ExperienciaProfesional
     */
    public ExperienciaProfesional() {
        initComponents();
        paneCargo.setVisible(false);
        jComboBox1.setVisible(false);
       // calIngreso1.setVisible(false);
        //calSalida1.setVisible(false);
        seleccionarUltimoKey();
    }
    public void seleccionarUltimoKey(){
            try{
                conexion cc = new conexion();
             Connection cn=cc.conectar("TOSHIBA");
             String sentencia="SELECT MAX(COD_EXP_PRO) FROM EXPERIENCIA_PROFESIONAL;";
             Statement statement1=cn.createStatement();
             JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
             ResultSet rs=statement1.executeQuery(sentencia);
             String key="";
             int keyN=1;
             if(rs!=null){
                 
                 while(rs.next()){
                 key=rs.getString(1);
                 JOptionPane.showMessageDialog(null, "key "+ key+" keyN "+keyN);
                 if(key!=null){
                 keyN=Integer.parseInt(key)+1;
                 }
                 
                 }             
                   nEP.setText(String.valueOf(keyN));
             }            
             
             JOptionPane.showMessageDialog(null, "Registro seleccionado! "+ key);
             }catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, "Error al seleccionar "+ex.getMessage());
             }
      
    }
public void Insertar(){
 try{
    conexion cc = new conexion();
 Connection cn=cc.conectar("TOSHIBA");
 java.sql.Date fecha1=convertUtilDateToSqlDate(dcIngreso1.getDate());
 java.sql.Date fecha2=convertUtilDateToSqlDate(dcSalida1.getDate());
 
 String sentencia="insert into EXPERIENCIA_PROFESIONAL(NUM_CED,NOM_EMP,"
                               + "ACT_EMP,FEC_ING_EMP,FEC_SAL_EMP,CIU_EMP,MOT_SAL_EM_EX,TEL_EMP) values('"+txtCedula.getText()+"', '"+
                               txtNombre.getText()+"','"+txtActividad.getText()+"','"+fecha1+"','"+fecha2+"','"+
                               txtCiudad.getText()+"','"+txtMotivoSalida.getText()+"','"+txtTelefono.getText()+"')";
  sentencia="insert into EXPERIENCIA_PROFESIONAL(NUM_CED,NOM_EMP,"
                               + "ACT_EMP,CIU_EMP,MOT_SAL_EM_EX,TEL_EMP) values('"+txtCedula.getText()+"', '"+
                               txtNombre.getText()+"','"+txtActividad.getText()+"','"+
                               txtCiudad.getText()+"','"+txtMotivoSalida.getText()+"','"+txtTelefono.getText()+"')";
 Statement statement1=cn.createStatement();
 JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
                       statement1.executeUpdate(sentencia);
   cn.commit();
 JOptionPane.showMessageDialog(null, "Registro ingresado! ");
 }catch(SQLException ex){
     JOptionPane.showMessageDialog(null, "Error al insertar en EXPERIENCIA_PROFESIONAL "+ex.getMessage());
 }
 
}
public void insertCARGO(){
 try{
    conexion cc = new conexion();
    Connection cn=cc.conectar("TOSHIBA");
    
    String sentencia="insert into CARGO(NOM_CAR) values('"+txtCargoNombre.getText()+"')";
    Statement statement1=cn.createStatement();
    JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
                          statement1.executeUpdate(sentencia);
      cn.commit();
    JOptionPane.showMessageDialog(null, "Registro ingresado! ");
    }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Error al insertar "+ex.getMessage());
    }
}
public void insertDETALLE_CARGO(){
 try{
    conexion cc = new conexion();
    Connection cn=cc.conectar("TOSHIBA");
    java.sql.Date fecha1=convertUtilDateToSqlDate(dcIngreso2.getDate());
    java.sql.Date fecha2=convertUtilDateToSqlDate(dcSalida2.getDate());
    String sentencia="insert into DETALLE_CARGO(COD_CAR,COD_EXP_PRO,FEC_ING_C,FEC_SAL_C) values('"+seleccionarUltimoCodCar()+"','"+nEP.getText()+"','"+fecha1+"','"+fecha2+"')";
    sentencia="insert into DETALLE_CARGO(COD_CAR,COD_EXP_PRO) values('"+seleccionarUltimoCodCar()+"','"+nEP.getText()+"')";
    Statement statement1=cn.createStatement();
    JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
                          statement1.executeUpdate(sentencia);
      cn.commit();
    JOptionPane.showMessageDialog(null, "Registro ingresado! ");
    }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, "Error al insertar en insertDETALLE_CARGO"+ex.getMessage());
    }
}
public int seleccionarUltimoCodCar(){
            try{
                conexion cc = new conexion();
             Connection cn=cc.conectar("TOSHIBA");
             String sentencia="SELECT MAX(COD_CAR) FROM CARGO;";
             Statement statement1=cn.createStatement();
             JOptionPane.showMessageDialog(null, "ANTES STATEMENT /n"+sentencia);
             ResultSet rs=statement1.executeQuery(sentencia);
             String key="";
             if(rs!=null){
                 while(rs.next()){
                 key=rs.getString(1);
                 }
             int keyN=Integer.parseInt(key);
                   //nEP.setText(String.valueOf(keyN));
                   return keyN;
             }            
             
             JOptionPane.showMessageDialog(null, "Registro seleccionado! "+ key);
             }catch(SQLException ex){
                 JOptionPane.showMessageDialog(null, "Error al seleccionar "+ex.getMessage());
             }
      return 0;
    }
public static java.sql.Date convertUtilDateToSqlDate(java.util.Date date){
    if(date != null) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }
    return null;
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtActividad = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnIngresarOtra = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        paneCargo = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtCargoNombre = new javax.swing.JTextField();
        dcIngreso2 = new com.toedter.calendar.JDateChooser();
        dcSalida2 = new com.toedter.calendar.JDateChooser();
        btnCancelarTodo = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtMotivoSalida = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        dcIngreso1 = new com.toedter.calendar.JDateChooser();
        dcSalida1 = new com.toedter.calendar.JDateChooser();
        nEP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Experiencia Profesional");
        jLabel1.setToolTipText("");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Actividad:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Sobre la Empresa.");

        jLabel5.setText("Fecha de Ingreso:");

        jLabel6.setText("Fecha de Salida:");

        jLabel7.setText("Ciudad:");

        jLabel8.setText("Motivo de la Salida:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 102));
        jLabel9.setText("Otros.");

        jLabel10.setText("Teléfono:");

        btnIngresarOtra.setText("Ingresar Otra");
        btnIngresarOtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarOtraActionPerformed(evt);
            }
        });

        jLabel11.setText("Cargo:");

        jButton2.setText("Seleccionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        paneCargo.setName(""); // NOI18N

        jLabel12.setText("Fecha de Ingreso:");

        jLabel13.setText("Fecha de Salida:");

        jLabel14.setText("Nombre Cargo:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneCargoLayout = new javax.swing.GroupLayout(paneCargo);
        paneCargo.setLayout(paneCargoLayout);
        paneCargoLayout.setHorizontalGroup(
            paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCargoLayout.createSequentialGroup()
                .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCargoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCargoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dcIngreso2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dcSalida2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneCargoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)))
                .addContainerGap())
        );
        paneCargoLayout.setVerticalGroup(
            paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCargoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtCargoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(dcIngreso2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(dcSalida2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(paneCargoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        btnCancelarTodo.setText("Cancelar");
        btnCancelarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarTodoActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cargos" }));

        dcIngreso1.setBorder(new javax.swing.border.MatteBorder(null));

        dcSalida1.setBorder(new javax.swing.border.MatteBorder(null));

        nEP.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(70, 70, 70)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(dcIngreso1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcSalida1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(79, 79, 79)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnCancelarTodo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(23, 23, 23)
                                .addComponent(txtMotivoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(13, 13, 13))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(96, 96, 96)
                                    .addComponent(jLabel6))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                    .addComponent(txtActividad))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIngresarOtra)
                        .addGap(218, 218, 218))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nEP, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(paneCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(33, 33, 33))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jButton2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paneCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dcIngreso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcSalida1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(txtMotivoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(24, 24, 24))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIngresarOtra)
                    .addComponent(btnCancelarTodo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          paneCargo.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        insertCARGO();
        insertDETALLE_CARGO();
          paneCargo.setVisible(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
          paneCargo.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarTodoActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarTodoActionPerformed

    private void btnIngresarOtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarOtraActionPerformed
Insertar();        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnIngresarOtraActionPerformed

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
            java.util.logging.Logger.getLogger(ExperienciaProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExperienciaProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExperienciaProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExperienciaProfesional.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExperienciaProfesional().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarTodo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnIngresarOtra;
    private com.toedter.calendar.JDateChooser dcIngreso1;
    private com.toedter.calendar.JDateChooser dcIngreso2;
    private com.toedter.calendar.JDateChooser dcSalida1;
    private com.toedter.calendar.JDateChooser dcSalida2;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nEP;
    private javax.swing.JPanel paneCargo;
    private javax.swing.JTextField txtActividad;
    private javax.swing.JTextField txtCargoNombre;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtMotivoSalida;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
