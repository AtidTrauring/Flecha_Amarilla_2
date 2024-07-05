package ventanas.Consultas;

import crud.CConsultas;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JfTerminalesConsulta extends javax.swing.JFrame {

    // Variable del modelo
    private DefaultTableModel modelo;
    // Variable del moelo e las listas
    private DefaultComboBoxModel listas;
    // Variable para poder agregar filtros
    private TableRowSorter tr;
    // Instancia de la clase que permite hacer las consultas "Transacciones"
    private final CConsultas query = new CConsultas();
    // Creacion de lista, para la obtencion de valores de la tabla
    private ArrayList<String[]> datosTerminales = new ArrayList<>();
    // Creacion de lista, para la obtencion de valores de las listas
    private ArrayList<String> datosListas = new ArrayList<>();

    public JfTerminalesConsulta() throws SQLException {
        initComponents();
        JtableTerminales.getTableHeader().setReorderingAllowed(false);
        //CArgamos valores del ComboBox
        cargaComboBox(JcmbxEstados, 1);
        cargaComboBox(JcmbxCiudades, 2);
        //Cargamos la tabla
        cargarTabla();
    }
    
    /**
     * ********* Metodos **********
     */
    private void limpiarTabla() {
        // Obtenemos el modelo
        modelo = (DefaultTableModel) JtableTerminales.getModel();
        // Por medio de un for, tomando en cuenta el numero de filas
        for (int i = (JtableTerminales.getRowCount() - 1); i >= 0; i--) {
            // Eliminaremos las filas hasta que el valor del iterador sea mayor o igual a 0
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JtableTerminales.getModel();
        try {
            // Leer los datos
            datosTerminales = query.buscaTerminales();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosTerminal : datosTerminales) {
                modelo.addRow(new Object[]{datosTerminal[0], datosTerminal[1], datosTerminal[2], datosTerminal[3], datosTerminal[4], datosTerminal[5],
                    datosTerminal[6], datosTerminal[7]});
            }
        }catch(SQLException ex) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) throws SQLException {
        //  Obtenemos el modelo del JComboBox
        listas = (DefaultComboBoxModel) combo.getModel();
        switch (metodoCarga) {
            case 1:
                // Obtenemos los valores de la tabla
                datosListas = query.cargaComboEstado();
                for (int i = 1; i < datosListas.size(); i++) {
                    // Añadimos items por string dentro de la lista
                    listas.addElement(datosListas.get(i));
                }
                // Limpiamos la lista para cargar los datos del siguiente JComboBox
                datosListas.clear();
                break;
            case 2:
                datosListas = query.cargaComboCiudad();
                for (int i = 1; i < datosListas.size(); i++) {
                    listas.addElement(datosListas.get(i));
                }
                datosListas.clear();
                break;
        }
    }

    public void filtrar(JComboBox lista, int columna) {
        modelo = (DefaultTableModel) JtableTerminales.getModel();
        tr = new TableRowSorter(modelo);
        // Le indicamos a la tabla el filtro se usara 
        JtableTerminales.setRowSorter(tr);
        // Si la opcion seleccionada no es 'Seleccione una opcion'
        if (lista.getSelectedIndex() != 0) {
            // Aplicamos el filtro para hacerlo coincidir con el item seleccionadao en la columna indicada
            tr.setRowFilter(RowFilter.regexFilter("^" + lista.getSelectedItem().toString() + "$", columna));
        }
    }

    public void filtrarTextField(String valor, int columna) {
        modelo = (DefaultTableModel) JtableTerminales.getModel();
        // Nuestro Filtro recibe el modelo de la tabla a filtrar
        tr = new TableRowSorter(modelo);
        // Le indicamos a la tabla el filtro se usara 
        JtableTerminales.setRowSorter(tr);
        // Si la opcion seleccionada no es 'Seleccione una opcion'
        if (valor != null) {
            // Aplicamos el filtro para hacerlo coincidir con el item seleccionadao en la columna indicada
            tr.setRowFilter(RowFilter.regexFilter(valor, columna));
            // En caso de serlo, no queremos que aplique el filtro proporcionado
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPtablaTerminales = new javax.swing.JScrollPane();
        JtableTerminales = new javax.swing.JTable();
        JlblEstado = new javax.swing.JLabel();
        JcmbxEstados = new javax.swing.JComboBox<>();
        JlblCiudad = new javax.swing.JLabel();
        JcmbxCiudades = new javax.swing.JComboBox<>();
        JlblNombreTerminal = new javax.swing.JLabel();
        JtxtNombreTerminal = new javax.swing.JTextField();
        JbtnActualizar = new javax.swing.JButton();
        JbtnBuscar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JspNombreTerminal = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Terminales");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableTerminales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Ciudad", "Vialidad", "Numero", "Colonia", "Codigo  Postal", "Telefono"
            }
        ));
        JSPtablaTerminales.setViewportView(JtableTerminales);

        JpnlLienzo.add(JSPtablaTerminales, new org.netbeans.lib.awtextra.AbsoluteConstraints(294, 6, 700, 288));

        JlblEstado.setText("Estado");
        JpnlLienzo.add(JlblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        JcmbxEstados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxEstados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxEstadosItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxEstados, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 181, -1));

        JlblCiudad.setText("Ciudad");
        JpnlLienzo.add(JlblCiudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        JcmbxCiudades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxCiudades.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxCiudadesItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxCiudades, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 181, -1));

        JlblNombreTerminal.setText("Nombre de la terminal");
        JpnlLienzo.add(JlblNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        JtxtNombreTerminal.setBorder(null);
        JtxtNombreTerminal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombreTerminalKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 181, -1));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 28, 84, -1));

        JbtnBuscar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnBuscar.setText("Buscar");
        JpnlLienzo.add(JbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 56, 84, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 84, 84, -1));
        JpnlLienzo.add(JspNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 181, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxEstadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxEstadosItemStateChanged
        filtrar(JcmbxEstados, 1);
    }//GEN-LAST:event_JcmbxEstadosItemStateChanged

    private void JcmbxCiudadesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxCiudadesItemStateChanged
        filtrar(JcmbxCiudades, 2);
    }//GEN-LAST:event_JcmbxCiudadesItemStateChanged

    private void JtxtNombreTerminalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombreTerminalKeyReleased
        filtrarTextField(JtxtNombreTerminal.getText(), 0);
    }//GEN-LAST:event_JtxtNombreTerminalKeyReleased

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
            java.util.logging.Logger.getLogger(JfTerminalesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfTerminalesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfTerminalesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfTerminalesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JfTerminalesConsulta().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JfTerminalesConsulta.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPtablaTerminales;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnBuscar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxCiudades;
    private javax.swing.JComboBox<String> JcmbxEstados;
    private javax.swing.JLabel JlblCiudad;
    private javax.swing.JLabel JlblEstado;
    private javax.swing.JLabel JlblNombreTerminal;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspNombreTerminal;
    private javax.swing.JTable JtableTerminales;
    private javax.swing.JTextField JtxtNombreTerminal;
    // End of variables declaration//GEN-END:variables
}
