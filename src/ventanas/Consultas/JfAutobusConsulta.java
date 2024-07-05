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

public class JfAutobusConsulta extends javax.swing.JFrame {

    // Variable para manipular el modelo de la tabla
    private DefaultTableModel modelo;
    // Variable para poder manipular el modelo de las listas
    private DefaultComboBoxModel listas;
    // Variable para poder agregar filtros
    private TableRowSorter tr;
    // Instancia de la clase que permite hacer las consultas "Transacciones"
    private final CConsultas query = new CConsultas();
    // Creacion de lista, para la obtencion de valores de la tabla
    private ArrayList<String[]> datosAutobuses = new ArrayList<>();
    // Creacion de lista, para la obtencion de valores de las listas
    private ArrayList<String> datosListas = new ArrayList<>();

    public JfAutobusConsulta() {
        initComponents();
        // Linea para impedir que sea posible mover los encabezados de cada tabla
        JtableAutobuses.getTableHeader().setReorderingAllowed(false);
        // Cargar los valores de JComboBox
        cargaComboBox(JcmbxMarcas, 1);
        cargaComboBox(JcmbxModelos, 2);
        cargaComboBox(JcmbxCapacidad, 3);
        // Cargas los valores de la tabla
        cargarTabla();
    }

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
                    datosListas = query.cargaComboMarca();
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
                    datosListas = query.cargaComboModelo();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 3:
                    datosListas = query.cargaComboCapacidad();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;

            }

        } catch (SQLException e) {
        }

    }

    // Metodo que permite filtrar los valores dentro de la tabla
    /* Recibe por parametro el JComboBox de donde tomaremos los valores para
       filtrar, asi como el numero de la columna donde buscaremos las coincidencias*/
    public void filtrar(JComboBox lista, int columna) {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        // Nuestro Filtro recibe el modelo de la tabla a filtrar
        tr = new TableRowSorter(modelo);
        // Le indicamos a la tabla el filtro se usara 
        JtableAutobuses.setRowSorter(tr);
        // Si la opcion seleccionada no es 'Seleccione una opcion'
        if (lista.getSelectedIndex() != 0) {
            // Aplicamos el filtro para hacerlo coincidir con el item seleccionadao en la columna indicada
            // tr.setRowFilter(RowFilter.regexFilter(lista.getSelectedItem().toString(), columna));
            tr.setRowFilter(RowFilter.regexFilter("^" + lista.getSelectedItem().toString() + "$", columna));

            // En caso de serlo, no queremos que aplique el filtro proporcionado
        }
    }

    // Metodo para limpiar la tabla
    private void limpiarTabla() {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        // Por medio de un for, tomando en cuenta el numero de filas
        for (int i = (JtableAutobuses.getRowCount() - 1); i >= 0; i--) {
            // Eliminaremos las filas hasta que el valor del iterador sea mayor o igual a 0
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        try {
            // Leer los datos
            datosAutobuses = query.buscaAutobuses();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosAutobus : datosAutobuses) {
                modelo.addRow(new Object[]{datosAutobus[0], datosAutobus[1], datosAutobus[2], datosAutobus[3]});
            }

        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JtableAutobuses = new javax.swing.JTable();
        JbtnEliminar = new javax.swing.JButton();
        JlblMarcas = new javax.swing.JLabel();
        JcmbxMarcas = new javax.swing.JComboBox<>();
        JlblModelos = new javax.swing.JLabel();
        JcmbxModelos = new javax.swing.JComboBox<>();
        JlblCapacidad = new javax.swing.JLabel();
        JcmbxCapacidad = new javax.swing.JComboBox<>();
        JlblOrdenar = new javax.swing.JLabel();
        JcmbxOrdenar = new javax.swing.JComboBox<>();
        JbtnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Autobuses");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableAutobuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Capacidad", "Fecha de Registro"
            }
        ));
        JtableAutobuses.getTableHeader().setResizingAllowed(false);
        JtableAutobuses.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(JtableAutobuses);
        if (JtableAutobuses.getColumnModel().getColumnCount() > 0) {
            JtableAutobuses.getColumnModel().getColumn(0).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(1).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(2).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(3).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        JpnlLienzo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 320));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 220, 90, -1));

        JlblMarcas.setText("Marca");
        JpnlLienzo.add(JlblMarcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, -1));

        JcmbxMarcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxMarcas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMarcasItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxMarcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 160, -1));

        JlblModelos.setText("Modelo");
        JpnlLienzo.add(JlblModelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));

        JcmbxModelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxModelos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxModelosItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxModelos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 160, -1));

        JlblCapacidad.setText("Capacidad");
        JpnlLienzo.add(JlblCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        JcmbxCapacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxCapacidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxCapacidadItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 160, -1));

        JlblOrdenar.setText("Ordenar");
        JpnlLienzo.add(JlblOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, -1, -1));

        JcmbxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JpnlLienzo.add(JcmbxOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 160, -1));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxMarcasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMarcasItemStateChanged
        filtrar(JcmbxMarcas, 0);
    }//GEN-LAST:event_JcmbxMarcasItemStateChanged

    private void JcmbxModelosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxModelosItemStateChanged
        filtrar(JcmbxModelos, 1);
    }//GEN-LAST:event_JcmbxModelosItemStateChanged

    private void JcmbxCapacidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxCapacidadItemStateChanged
        filtrar(JcmbxCapacidad, 2);
    }//GEN-LAST:event_JcmbxCapacidadItemStateChanged

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
            java.util.logging.Logger.getLogger(JfAutobusConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfAutobusConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfAutobusConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfAutobusConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfAutobusConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxCapacidad;
    private javax.swing.JComboBox<String> JcmbxMarcas;
    private javax.swing.JComboBox<String> JcmbxModelos;
    private javax.swing.JComboBox<String> JcmbxOrdenar;
    private javax.swing.JLabel JlblCapacidad;
    private javax.swing.JLabel JlblMarcas;
    private javax.swing.JLabel JlblModelos;
    private javax.swing.JLabel JlblOrdenar;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JTable JtableAutobuses;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
