/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas.Consultas;

import crud.CConsultas;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class JfReembolsoConsulta extends javax.swing.JFrame {

    /**
     * Creates new form JfReembolsoConsulta
     */
    public JfReembolsoConsulta() {
        initComponents();
        cargaComboBox(JcmbxAnios, 2);
        cargaComboBox(JcmbxMeses, 1);
        
        cargarTabla();
         // Linea para impedir que sea posible mover los encabezados de cada tabla
     JtableReembolsos.getTableHeader().setReorderingAllowed(false);
    }

   
    
    
    
     
    
    
    //**************   ATRIBUTOS  *******************/
     // Variable para manipular el modelo de la tabla
    private DefaultTableModel modelo;
    // Variable para poder manipular el modelo de las listas
    private DefaultComboBoxModel listas;
    // Variable para poder agregar filtros
    private TableRowSorter tr;
    // Instancia de la clase que permite hacer las consultas "Transacciones"
    private final CConsultas query = new CConsultas();
    // Creacion de lista, para la obtencion de valores de la tabla
    private ArrayList<String[]> datosReembol = new ArrayList<>();
    // Creacion de lista, para la obtencion de valores de las listas
    private ArrayList<String> datosListas = new ArrayList<>();
    
     // Linea para impedir que sea posible mover los encabezados de cada tabla
      // JtableConducen.getTableHeader().setReorderingAllowed(false);
       
    
    
    //**************** METODOS ******************/
       // Metodo que permite cargar las opciones en las listas
    // Recibe por parametro el JComboBox al que se agregaran items
    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        //  Obtenemos el modelo del JComboBox
//        listas = new DefaultComboBoxModel();

        listas = (DefaultComboBoxModel) combo.getModel();
        // combo.setModel(listas);
        try {
            switch (metodoCarga) {
                case 1:
                    // Obtenemos los valores de la tabla
                    datosListas = query.cargaComboMeses();
                    // listas.addElement("Seleccione una opcion");
                    // Asiganamos los valores obtenidos al JComboBox
                    for (int i = 1; i < datosListas.size(); i++) {
                        // Añadimos items por string dentro de la lista
                        listas.addElement(datosListas.get(i));
                    }
                    // Limpiamos la lista para cargar los datos del siguiente JComboBox
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = query.cargaComboAnios();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                    
             

            }

        } catch (SQLException e) {
        }

    }
    
    
    
    public void cargarTabla() {
        // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        try {
            // Leer los datos
            datosReembol = query.buscarReembolso();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosRee : datosReembol) {
                modelo.addRow(new Object[]{datosRee[0], datosRee[1], datosRee[2], datosRee[3], datosRee[4], datosRee[5], datosRee[6]});
            }

        } catch (Exception e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }
    
       // Metodo para limpiar la tabla
    private void limpiarTabla() {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        // Por medio de un for, tomando en cuenta el numero de filas
        for (int i = (JtableReembolsos.getRowCount() - 1); i >= 0; i--) {
            // Eliminaremos las filas hasta que el valor del iterador sea mayor o igual a 0
            modelo.removeRow(i);
        }
    }
    
    
    // Metodo que permite filtrar los valores dentro de la tabla
    /* Recibe por parametro el JComboBox de donde tomaremos los valores para
       filtrar, asi como el numero de la columna donde buscaremos las coincidencias*/
    public void filtrar(JComboBox lista, int columna) {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        // Nuestro Filtro recibe el modelo de la tabla a filtrar
        tr = new TableRowSorter(modelo);
        // Le indicamos a la tabla el filtro se usara 
        JtableReembolsos.setRowSorter(tr);
        // Si la opcion seleccionada no es 'Seleccione una opcion'
        if (lista.getSelectedIndex() != 0) {
            // Aplicamos el filtro para hacerlo coincidir con el item seleccionadao en la columna indicada
            // tr.setRowFilter(RowFilter.regexFilter(lista.getSelectedItem().toString(), columna));
            tr.setRowFilter(RowFilter.regexFilter("^" + lista.getSelectedItem().toString() + "$", columna));

            // En caso de serlo, no queremos que aplique el filtro proporcionado
        }
    }
    
    
    public void filtraCuadrOTexto(String valor, int columna){
               // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        tr=new TableRowSorter(modelo);
        JtableReembolsos.setRowSorter(tr);
        if (valor != null) {
            tr.setRowFilter(RowFilter.regexFilter(valor, columna));
            
        }
    
    }
    
    
    
    
    
    

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaReembolsos = new javax.swing.JScrollPane();
        JtableReembolsos = new javax.swing.JTable();
        JlblDia = new javax.swing.JLabel();
        JlblMes = new javax.swing.JLabel();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JlblAnio = new javax.swing.JLabel();
        JcmbxAnios = new javax.swing.JComboBox<>();
        JbtnBuscar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JlblFondo = new javax.swing.JLabel();
        JbtnActualizar = new javax.swing.JButton();
        JpnlDatosCliente = new javax.swing.JPanel();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApPaterno1 = new javax.swing.JLabel();
        JtxtApmMaterno = new javax.swing.JTextField();
        JspApPaterno1 = new javax.swing.JSeparator();
        JspNombres = new javax.swing.JSeparator();
        JtxtNombres = new javax.swing.JTextField();
        JlblNombres = new javax.swing.JLabel();
        JcmbxDias = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reembolsos");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableReembolsos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Cantidad", "Dia", "Mes", "Año"
            }
        ));
        JSPTablaReembolsos.setViewportView(JtableReembolsos);

        JpnlLienzo.add(JSPTablaReembolsos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 630, 147));

        JlblDia.setText("Dia");
        JpnlLienzo.add(JlblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        JlblMes.setText("Mes");
        JpnlLienzo.add(JlblMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMesesItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        JlblAnio.setText("Año");
        JpnlLienzo.add(JlblAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        JcmbxAnios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxAnios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxAniosItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxAnios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        JbtnBuscar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnBuscar.setText("Buscar");
        JpnlLienzo.add(JbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 90, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 90, -1));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoReembolso.png"))); // NOI18N
        JpnlLienzo.add(JlblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 210, 190));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 90, -1));

        JpnlDatosCliente.setBackground(new java.awt.Color(255, 255, 255));
        JpnlDatosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Datos del Cliente"));

        JlblApPaterno.setText("Apellido Paterno");

        JtxtApPaterno.setBorder(null);
        JtxtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApPaternoKeyReleased(evt);
            }
        });

        JlblApPaterno1.setText("Apellido Materno");

        JtxtApmMaterno.setBorder(null);
        JtxtApmMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApmMaternoKeyReleased(evt);
            }
        });

        JtxtNombres.setBorder(null);
        JtxtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombresKeyReleased(evt);
            }
        });

        JlblNombres.setText("Nombre(s)");

        javax.swing.GroupLayout JpnlDatosClienteLayout = new javax.swing.GroupLayout(JpnlDatosCliente);
        JpnlDatosCliente.setLayout(JpnlDatosClienteLayout);
        JpnlDatosClienteLayout.setHorizontalGroup(
            JpnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlDatosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlblNombres)
                    .addComponent(JtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblApPaterno)
                    .addComponent(JtxtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblApPaterno1)
                    .addComponent(JtxtApmMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspApPaterno1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpnlDatosClienteLayout.setVerticalGroup(
            JpnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlDatosClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JlblNombres)
                .addGap(4, 4, 4)
                .addComponent(JtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(JspNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JlblApPaterno)
                .addGap(4, 4, 4)
                .addComponent(JtxtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(JspApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JlblApPaterno1)
                .addGap(4, 4, 4)
                .addComponent(JtxtApmMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(JspApPaterno1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JpnlLienzo.add(JpnlDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 160, 190));

        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        JcmbxDias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDiasItemStateChanged(evt);
            }
        });
        JcmbxDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDiasActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDiasItemStateChanged
        filtrar(JcmbxDias, 4);
    }//GEN-LAST:event_JcmbxDiasItemStateChanged

    private void JcmbxDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcmbxDiasActionPerformed

    private void JtxtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombresKeyReleased
        // TODO add your handling code here:
         filtraCuadrOTexto(JtxtNombres.getText(), 0);
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        // TODO add your handling code here:
        filtraCuadrOTexto(JtxtApPaterno.getText(), 1);
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApmMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApmMaternoKeyReleased
        // TODO add your handling code here:
             filtraCuadrOTexto(JtxtApmMaterno.getText(), 2);
    }//GEN-LAST:event_JtxtApmMaternoKeyReleased

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        // TODO add your handling code here:
        filtrar(JcmbxMeses, 5);
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

    private void JcmbxAniosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxAniosItemStateChanged
        // TODO add your handling code here:
        filtrar(JcmbxDias, NORMAL);
    }//GEN-LAST:event_JcmbxAniosItemStateChanged

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
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfReembolsoConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaReembolsos;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnBuscar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxAnios;
    private javax.swing.JComboBox<String> JcmbxDias;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JLabel JlblAnio;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblApPaterno1;
    private javax.swing.JLabel JlblDia;
    private javax.swing.JLabel JlblFondo;
    private javax.swing.JLabel JlblMes;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JPanel JpnlDatosCliente;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspApPaterno1;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JTable JtableReembolsos;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtApmMaterno;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables
}
