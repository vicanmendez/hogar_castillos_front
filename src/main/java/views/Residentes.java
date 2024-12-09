/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CrudApi;

/**
 *
 * @author vicmn
 */
public class Residentes extends javax.swing.JFrame {
    public static Residentes instancia = null;
    private String token = "";
    private CrudApi servicios = new CrudApi();
    /**
     * Creates new form Residentes
     */
    public Residentes() {
        initComponents();
        this.setBounds(0, 0, 900, 600);
        

    }
    
    public void loadTable() {
        //Definimos el token para acceder a la API
        servicios.setToken(token);
        //Lista
         String[] columnNames = {"N°", "CI", "Nombre", "Nacimiento", "Ingreso", "Familiar referente", "Teléfono Familiar"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setBounds(panelLista.getBounds());
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true); // Para que la tabla sea scrollable
        try {
                String response = servicios.listResidents();
                JSONArray jsonArray = new JSONArray(response);

                // Limpiar datos anteriores
                model.setRowCount(0);

                // Procesar JSON y añadir a la tabla
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Object[] row = {
                            jsonObject.getInt("id"),
                            jsonObject.getInt("ci"),
                            jsonObject.getString("name"),
                            jsonObject.getString("birth_date"),
                            jsonObject.getString("entry_date"),
                            jsonObject.getString("family_name"),
                            jsonObject.getString("family_phone"),

                    };
                    model.addRow(row);
                }
                table.setModel(model);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al consultar residentes: " + ex.getMessage());
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        panelNuevo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        fechaNacimiento = new com.toedter.calendar.JDateChooser();
        fechaIngreso = new com.toedter.calendar.JDateChooser();
        txtFamiliar = new javax.swing.JTextField();
        txtTelefonoFamiliar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de residentes");
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(java.awt.SystemColor.activeCaption);
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(600, 600));

        panelLista.setBackground(java.awt.SystemColor.controlHighlight);

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table);

        jButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jButton1.setText("Actualizar datos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setText("Eliminar residente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaLayout = new javax.swing.GroupLayout(panelLista);
        panelLista.setLayout(panelListaLayout);
        panelListaLayout.setHorizontalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelListaLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista", panelLista);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Documento de Identidad");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Fecha de nacimiento");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Fecha de Ingreso al centro");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Familiar o familiares de referencia");

        jLabel6.setText("Teléfono(s) de contacto de familiar");

        jButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Blue"));
        jButton3.setText("Ingresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(txtCedula)
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(fechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFamiliar)
                    .addComponent(txtTelefonoFamiliar))
                .addContainerGap(335, Short.MAX_VALUE))
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(fechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefonoFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(46, 46, 46))
        );

        jTabbedPane1.addTab("+ Agregar nuevo", panelNuevo);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 500));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Lista");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int fila = table.getSelectedRow();
        int idResidente = (int) table.getValueAt(fila, 0);
        String nombreResidente = table.getValueAt(fila,2).toString();
        int decision = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea eliminar el residente N° " + idResidente + " - Nombre: "+nombreResidente+"? \n ESTA ACCIÓN ES IRREVERSIBLE", "PRECAUCIÓN", JOptionPane.YES_NO_CANCEL_OPTION);
        switch(decision){
                case 0: 
            {
                try {
                    servicios.deleteResident(idResidente);
                    JOptionPane.showMessageDialog(null, "Residente eliminado");
                    loadTable();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Hubo un error al eliminar el residente");
                    Logger.getLogger(Residentes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    break;

                case 1: 
                    JOptionPane.showMessageDialog(null, "No se eliminará al residente");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Operación cancelada");
                    break;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         int fila = table.getSelectedRow();
         if(fila < 0) {
                      JOptionPane.showMessageDialog(null, "No se está seleccionando ningún registro a modificar");
         } else {
                String idResidente = table.getValueAt(fila, 0).toString();
                String ciResidente = table.getValueAt(fila, 1).toString();
                String nombreResidente = table.getValueAt(fila,2).toString();
                String fechaNacimiento = table.getValueAt(fila, 3).toString();
                String fechaIngreso = table.getValueAt(fila, 4).toString();
                String nombreFamiliar = table.getValueAt(fila, 5).toString();
                String telefonoFamiliar = table.getValueAt(fila, 6).toString();
                int decision = JOptionPane.showConfirmDialog(rootPane, "¿Seguro que desea MODIFICAR el residente N° " + idResidente + " - Nombre: "+nombreResidente+"? \n ESTA ACCIÓN ES IRREVERSIBLE \n"
                        + "Nuevos datos: \n"
                        + "ID: "+idResidente+"\n"
                        + "Nombre: "+nombreResidente+"\n"
                        + "CI: "+ciResidente+"\n"
                        + "Fecha de nacimiento: " +fechaNacimiento+"\n"
                        + "Fecha de ingreso: "+fechaIngreso+"\n"
                        + "Familiar de referencia: "+nombreFamiliar+"\n"
                        + "Teléfono de referencia: "+telefonoFamiliar+"\n"
                        , "PRECAUCIÓN", JOptionPane.YES_NO_CANCEL_OPTION);
                switch(decision){
                        case 0: 
                    {
                        try {
                            servicios.updateResident(idResidente, nombreResidente, ciResidente, fechaNacimiento, fechaIngreso, nombreFamiliar, telefonoFamiliar);
                            JOptionPane.showMessageDialog(null, "Residente modificado");
                            loadTable();

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Hubo un error al modificar el residente");
                            Logger.getLogger(Residentes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                            break;

                        case 1: 
                            JOptionPane.showMessageDialog(null, "No se modificará al residente");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Operación cancelada");
                            break;
                }
         }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
         Date fechaNac = fechaNacimiento.getDate();
         Date fechaIng = fechaIngreso.getDate();
        String nacimiento = fechaNac != null ? new SimpleDateFormat("yyyy-MM-dd").format(fechaNac) : "No especificada";
        String ingreso = fechaIng != null ? new SimpleDateFormat("yyyy-MM-dd").format(fechaIng) : "No especificada";
        String familiar = txtFamiliar.getText();
        String telefonoFamiliar = txtTelefonoFamiliar.getText();
        
         String mensaje = String.format("""
        ¿Desea ingresar al residente con los siguientes datos?

        Nombre: %s
        Cédula: %s
        Fecha de Nacimiento: %s
        Fecha de Ingreso: %s
        Familiar Responsable: %s
        Teléfono del Familiar: %s
        """, nombre, cedula, nacimiento, ingreso, familiar, telefonoFamiliar);
        
         int opcion = JOptionPane.showConfirmDialog(
        null, mensaje, "Confirmar Ingreso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        // Responder según la opción seleccionada
        if (opcion == JOptionPane.YES_OPTION) {
            try{
                
                
                JOptionPane.showMessageDialog(null, "Residente ingresado exitosamente");
                servicios.createResident(nombre, cedula, nacimiento, ingreso, familiar, telefonoFamiliar);
                txtNombre.setText("");
                txtCedula.setText("");
                fechaNacimiento.setDate(null);
                fechaIngreso.setDate(null);
                txtFamiliar.setText("");
                txtTelefonoFamiliar.setText("");
                loadTable();
                
            } catch (Exception e ) {
                JOptionPane.showMessageDialog(null, "Hubo un error al ingresar al residente. \n Código del error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ingresará al residente");
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Residentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Residentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Residentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Residentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Residentes().setVisible(true);
            }
        });
    }
    
    public static Residentes getInstancia(String token) {
        if (instancia == null) {
            instancia = new Residentes();
            instancia.setToken(token);
            return instancia;
        }
        return instancia;
    }
    
    public void setToken(String t) {
        token = t;
        loadTable();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fechaIngreso;
    private com.toedter.calendar.JDateChooser fechaNacimiento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelLista;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtFamiliar;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefonoFamiliar;
    // End of variables declaration//GEN-END:variables
}
