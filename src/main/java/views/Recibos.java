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
import models.Residente;
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
    private ArrayList<Residente> residentes = new ArrayList<Residente>();
    /**
     * Creates new form Residentes
     */
    public Recibos() {
        initComponents();
        this.setBounds(0, 0, 900, 600);
       
        
    }
    
    
    public void cargarDatos() {
         try {
            loadClientes();
            loadResidentes();
            updateComboClients();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Advertencia: Hubo un error en la carga de clientes o recibos. Revisar conexión y reintentar");
            Logger.getLogger(Recibos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateComboClients() {
        comboClientes.removeAllItems();
        for(Cliente cliente : clientes) {
            comboClientes.addItem(cliente.getNombre());
        }
    }
    
    public Cliente selectClientByName(String name) {
        for(Cliente cliente: clientes) {
            if(name.equals(cliente.getNombre())) {
                return cliente;
            }
        }
        return null;
    }
    
     public Residente selectResidentByName(String name) {
        for(Residente residente: residentes) {
            if(name.equals(residente.getNombre())) {
                return residente;
            }
        }
        return null;
    }
     
     public String selectClientIdByName(String name) {
        for(Cliente cliente: clientes) {
            if(name.equals(cliente.getNombre())) {
                return cliente.getId();
            }
        }
        return null;
    }
     
     public String selectResidentIdByName(String name) {
        for(Residente residente: residentes) {
            if(name.equals(residente.getNombre())) {
                return residente.getId();
            }
        }
        return "";
    }
    
    public void updateComboResidents() {
        try {
            comboResidentes.removeAllItems();
            loadResidentes();
            for(Residente residente : residentes) {
                comboResidentes.addItem(residente.getNombre());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Se produjo un error al cargar los residentes");
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
    
     public void loadResidentes() throws IOException {
        String residents = servicios.listResidents();
        if(residentes.size() > 0) { 
                    residentes.clear();
        }
        JSONArray jsonArray = new JSONArray(residents);
        for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Object[] row = {
                            jsonObject.getInt("id"),
                            jsonObject.getString("ci"),
                            jsonObject.getString("name"),
                            jsonObject.getString("birth_date"),
                            jsonObject.getString("entry_date"),
                            jsonObject.getString("family_name"),
                            jsonObject.getString("family_phone"),

                    };
                    String id = row[0].toString();
                    String ci = row[1].toString();
                    String name = row[2].toString();
                    String birthDate = row[3].toString();
                    String entryDate = row[4].toString();
                    String familyName = row[5].toString();
                    String familyPhone = row[6].toString();
                    Residente r = new Residente(id, ci, name, familyName, familyPhone, entryDate, birthDate);
                    residentes.add(r);
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
                    r.setCliente(c.getNombre() + " - Email: " + c.getEmail());
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
        txtMonto = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        comboConcepto = new javax.swing.JComboBox<>();
        panelDescripcion = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        comboResidentes = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de recibos");
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
        jLabel1.setText("Nombre del contribuyente");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Concepto");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Monto ($)");

        jButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jButton3.setText("Crear recibo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Corresponde al residente:");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));

        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        comboConcepto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sociedad", "Mensualidad", "Otro" }));
        comboConcepto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboConceptoItemStateChanged(evt);
            }
        });

        panelDescripcion.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Descripción del recibo:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        javax.swing.GroupLayout panelDescripcionLayout = new javax.swing.GroupLayout(panelDescripcion);
        panelDescripcion.setLayout(panelDescripcionLayout);
        panelDescripcionLayout.setHorizontalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelDescripcionLayout.setVerticalGroup(
            panelDescripcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDescripcionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(37, 37, 37))
            .addGroup(panelDescripcionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        comboResidentes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        comboResidentes.setEnabled(false);

        javax.swing.GroupLayout panelNuevoLayout = new javax.swing.GroupLayout(panelNuevo);
        panelNuevo.setLayout(panelNuevoLayout);
        panelNuevoLayout.setHorizontalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(335, Short.MAX_VALUE))
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboConcepto, javax.swing.GroupLayout.Alignment.LEADING, 0, 333, Short.MAX_VALUE)
                            .addComponent(comboClientes, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(41, 41, 41)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelNuevoLayout.createSequentialGroup()
                        .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboResidentes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelNuevoLayout.setVerticalGroup(
            panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNuevoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboResidentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addComponent(panelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
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
        try {
            String cliente = comboClientes.getSelectedItem().toString();
            String idCliente = selectClientIdByName(cliente);
            String concepto = comboConcepto.getSelectedItem().toString();
            Double monto = Double.parseDouble(txtMonto.getText());
            String strMonto = monto.toString();
            String descripcion = "";
            String idResidente = "";
            if(concepto.equals("Mensualidad")) { 
                descripcion = concepto + " correspondiente a/la abuelo/a " + comboResidentes.getSelectedItem().toString();
                idResidente = selectResidentIdByName(comboResidentes.getSelectedItem().toString());
            } else if (concepto.equals("Otro")) {
                descripcion = txtDescripcion.getText();
            } else { 
                descripcion = concepto;
            }
            String mensaje = String.format("""
           ¿Desea ingresar el recibo con la siguiente información? \n
                                                      

           Contribuyente: %s
           Concepto: %s
           Monto: %s
                                           
           \n
           IMPORTANTE: La fecha y hora del recibo serán generadas automáticamente por el sistema. \n
           RECUERDE QUE UN RECIBO UNA VEZ GENERADO, NO SE PODRÁ ELIMINAR. SE NOTIFICARÁ AL CONTRIBUYENTE VÍA E-MAIL (si se cuenta con el mismo) \n
                                                                                    
           """, cliente, descripcion, monto);

            int opcion = JOptionPane.showConfirmDialog(
           null, mensaje, "Confirmar Ingreso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

           // Responder según la opción seleccionada
           if (opcion == 0) {
               try{
                   if(!idResidente.equals("")) {
                        servicios.createReceipt(idCliente, idResidente, strMonto, descripcion);
                   } else {
                       servicios.createReceipt(idCliente, strMonto, descripcion);
                   }
                 JOptionPane.showMessageDialog(null, "Se ha creado el recibo y notificado al contribuyente por correo electrónico");
                 loadTable();

               } catch (Exception e ) {
                   JOptionPane.showMessageDialog(null, "Hubo un error al ingresar al socio o cliente. \n Código del error: " + e.getMessage());
               }


           } else {
               JOptionPane.showMessageDialog(null, "No se ingresará al cliente / socio");
           }

            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR: El monto debe ser un valor numérico");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error al enviar el recibo \n. Código de error: \n " +e.getMessage());
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Socios s = Socios.getInstancia(token);
        s.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void comboConceptoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboConceptoItemStateChanged
        // TODO add your handling code here:
        if(comboConcepto.getSelectedItem().equals("Mensualidad")) {
            comboResidentes.setEnabled(true);
            panelDescripcion.setVisible(true);
            updateComboResidents();
            System.out.println("");
        } else if(comboConcepto.getSelectedItem().equals("Sociedad")) {
            comboResidentes.setEnabled(false);
            panelDescripcion.setVisible(false);
        } else {
            panelDescripcion.setVisible(true);
            comboResidentes.setEnabled(false);
        }
        
    }//GEN-LAST:event_comboConceptoItemStateChanged

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
            instancia.cargarDatos();
            return instancia;
        }
        return instancia;
    }
    
    public void setToken(String t) {
        token = t;
        loadTable();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JComboBox<String> comboConcepto;
    private javax.swing.JComboBox<String> comboResidentes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panelDescripcion;
    private javax.swing.JPanel panelLista;
    private javax.swing.JPanel panelNuevo;
    private javax.swing.JTable table;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
