/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import views.*;
import views.*;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CrudApi;
import models.Cliente;
import models.Recibo;
/**
 *
 * @author vicmn
 */
public class Recibos extends javax.swing.JFrame {
    public static Recibos instancia = null;
    private String token = "";
    private CrudApi servicios = new CrudApi();
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Recibo> recibos = new ArrayList<Recibo>();
    /**
     * Creates new form Residentes
     */
    public Recibos() {
        initComponents();
        this.setBounds(0, 0, 900, 600);
        try {
            loadClientes();
        } catch (IOException ex) {
            Logger.getLogger(Recibos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
    
    public void loadClientes() throws IOException {
        String clients = servicios.listClients();
        if(clientes.size() > 0) { 
                    clientes.clear();
        }
        JSONArray jsonArray = new JSONArray(clients);
        for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Object[] row = {
                            jsonObject.getInt("id"),
                            jsonObject.getString("ci"),
                            jsonObject.getString("name"),
                            jsonObject.getString("phone"),
                            jsonObject.getString("email"),

                    };
                    String id = row[0].toString();
                    String ci = row[1].toString();
                    String name = row[2].toString();
                    String phone = row[3].toString();
                    String email = row[4].toString();
                    Cliente c = new Cliente(id, ci, name, phone, email);
                    clientes.add(c);
                }
        
    }
    
    public Cliente getClientbyId(String id) { 
        for (int i=0; i<clientes.size(); i++) {
            if(id.equals(clientes.get(i).getId())) {
               return clientes.get(i);
        }
        }
        
        return null;
    }
    
    public Recibo getReceiptById(String id) { 
        for (int i=0; i<recibos.size(); i++) {
            if(id.equals(recibos.get(i).getId())) {
               return recibos.get(i);
        }
        }
        
        return null;
    }
    
    public void loadTable() {
        //Definimos el token para acceder a la API
        servicios.setToken(token);
         if(clientes.size() > 0) { 
                    clientes.clear();
        }
         if (recibos.size() > 0) {
             recibos.clear();
         }
        //Lista
         String[] columnNames = {"N°", "Contribuyente", "Concepto", "Monto", "Momento"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setBounds(panelLista.getBounds());
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true); // Para que la tabla sea scrollable
        try {
                String response = servicios.listReceipts();
                loadClientes();
                JSONArray jsonArray = new JSONArray(response);

                // Limpiar datos anteriores
                model.setRowCount(0);

                // Procesar JSON y añadir a la tabla
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Cliente c = getClientbyId(jsonObject.getString("client_id"));
                    Recibo r = new Recibo();
                    Object[] row = {
                            jsonObject.getString("id"),
                            c.getNombre(),
                            jsonObject.getString("concept"),
                            jsonObject.getString("amount"),
                            jsonObject.getString("date_time"),


                    };
                    model.addRow(row);
                    r.setId(row[0].toString());
                    r.setCodigo(jsonObject.getString("receipt_code"));
                    r.setCliente("Nombre: " + c.getNombre() + " - Email: " + c.getEmail());
                    r.setDescripcion(row[2].toString());
                    r.setMonto(row[3].toString());
                    r.setMomento(row[4].toString());
                    recibos.add(r);
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
        panelNuevo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de socios o clientes");
        setMinimumSize(new java.awt.Dimension(800, 500));
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
        jButton1.setText("Ver Detalles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                    .addComponent(jButton1))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        panelListaLayout.setVerticalGroup(
            panelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lista", panelLista);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Documento de Identidad");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Teléfono");

        jButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jButton3.setText("Crear recibo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("E-Mail");

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                            .addComponent(txtCedula))
                        .addContainerGap(335, Short.MAX_VALUE))
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(60, 60, 60))
        );

        jTabbedPane1.addTab("+ Agregar nuevo", panelNuevo);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 500));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Lista");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id = table.getValueAt(table.getSelectedRow(), 0).toString();
        Recibo r = getReceiptById(id);
        DetallesRecibo detalles = DetallesRecibo.getInstancia(r);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombre.getText();
        String cedula = txtCedula.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        
         String mensaje = String.format("""
        ¿Desea ingresar al socio con los siguientes datos?

        Nombre: %s
        Cédula: %s
        Teléfono: %s
        E-Mail: %s
        """, nombre, cedula, telefono, email);
        
         int opcion = JOptionPane.showConfirmDialog(
        null, mensaje, "Confirmar Ingreso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         
        // Responder según la opción seleccionada
        if (opcion == 0) {
            JOptionPane.showMessageDialog(null, "Ingresamos al cliente");
            
            try{
                
                
                JOptionPane.showMessageDialog(null, "Residente ingresado exitosamente");
                servicios.createClient(nombre, cedula, telefono, email);
                txtNombre.setText("");
                txtCedula.setText("");
                txtEmail.setText("");
                txtTelefono.setText("");
                loadTable();
                
            } catch (Exception e ) {
                JOptionPane.showMessageDialog(null, "Hubo un error al ingresar al socio o cliente. \n Código del error: " + e.getMessage());
            }
            
            
        } else {
            JOptionPane.showMessageDialog(null, "No se ingresará al cliente / socio");
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
            java.util.logging.Logger.getLogger(Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recibos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recibos().setVisible(true);
            }
        });
    }
    
    public static Recibos getInstancia(String token) {
        if (instancia == null) {
            instancia = new Recibos();
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelLista;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
