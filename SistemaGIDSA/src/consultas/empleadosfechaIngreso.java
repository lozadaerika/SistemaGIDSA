/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas;
import conexion.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ALEX JAVIER
 */
public class empleadosfechaIngreso extends javax.swing.JFrame {
  private DefaultTableModel model;

    /**
     * Creates new form discapaciddes
     */
    public empleadosfechaIngreso() {
        initComponents();
        
    }

    void cargarTabla(String dia, String mes , String anio){
        
        
        conexion cc = new conexion();
        Connection cn = cc.conectar("");
        
        String titulos []= {"Cedula","Nombre","Apellidos","EstadoCivil","Genero","Direccion","Ubicacion Fisica","Foto","Telefono","Celular"};
        
        model= new DefaultTableModel(null,titulos);
        String registro[] = new String [11]; 
        String sql="";
        sql = "Select Num_Ced, Nom_Em_1, Nom_Em_2, Ape_Em_1, Ape_Em_2, ESTADO_CI_EM, GENERO, DIR_EM, UBI_FIS, FOTO, TEL_EM, CEL_EM, FEC_ING_EM_EM from Empleados "
                + "WHERE datepart(mm,FEC_ING_EM_EM )="+mes+" and datepart(yyyy,FEC_ING_EM_EM ) = "+anio+" and datepart(dd,FEC_ING_EM_EM )="+dia+"";
        try{
        Statement psd = cn.createStatement();
        ResultSet rs = psd.executeQuery(sql);
        while(rs.next()){
            // , , , , , , , 
            registro[0]=rs.getString("Num_Ced");
            registro[1]=rs.getString("Nom_Em_1")+" "+rs.getString("Nom_Em_2");
            registro[2]=rs.getString("Ape_Em_1")+" "+rs.getString("Ape_Em_2");
            registro[3]=rs.getString("ESTADO_CI_EM");
            registro[4]=rs.getString("GENERO");
             registro[5]=rs.getString("DIR_EM");
              registro[6]=rs.getString("UBI_FIS");
            registro[7]=rs.getString("FOTO");
            registro[8]=rs.getString("TEL_EM");
            registro[9]=rs.getString("CEL_EM");
            registro[10]=rs.getString("FEC_ING_EM_EM");
            model.addRow(registro);
        }
          tblDiscapacidades.setModel(model);
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDiscapacidades = new javax.swing.JTable();
        fecha = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Empleados");

        jLabel2.setText("Fecha");

        tblDiscapacidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblDiscapacidades);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnBuscar)))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String dia=new SimpleDateFormat("dd").format(fecha.getDate());
        String mes=new SimpleDateFormat("MM").format(fecha.getDate());
        String anio=new SimpleDateFormat("yyyy").format(fecha.getDate());
        cargarTabla(dia, mes , anio);
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(empleadosfechaIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(empleadosfechaIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(empleadosfechaIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(empleadosfechaIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new empleadosfechaIngreso().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDiscapacidades;
    // End of variables declaration//GEN-END:variables
}
