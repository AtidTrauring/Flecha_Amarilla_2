package ventanas.Consultas;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import crud.CCargaCombos;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class JfAutobusConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    // Variable para manipular el modelo de la tabla
    private DefaultTableModel modelo;
    // Variable para poder manipular el modelo de las listas
    private DefaultComboBoxModel listas;
    // Variable para poder agregar filtros
    private TableRowSorter tr;
    // Instancias de la clases que permite hacer las consultas "Transacciones"
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    // Creacion de lista, para la obtencion de valores de la tabla
    private ArrayList<String[]> datosAutobuses = new ArrayList<>();
    // Creacion de lista, para la obtencion de valores de las listas
    private ArrayList<String> datosListas = new ArrayList<>();
    private int idActualizar;
    private String[] valoresFila;
    private int idEliminar;

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

    //**************** METODOS ******************/
    // Metodo para limpiar la tabla
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        // Limpia los cuadro de texto
        JcmbxCapacidad.setSelectedIndex(0);
        JcmbxMarcas.setSelectedIndex(0);
        JcmbxModelos.setSelectedIndex(0);
        JcmbxOrdenar.setSelectedIndex(0);
    }

    public void limpiarFiltro() {
        // Si el objeto 'tr' tiene algun filtro
        if (tr != null) {
            // Elimina el filtro 
            tr.setRowFilter(null);
        }
    }

    // Metodo que permite ingresar los valores necesarios a la tabla
    public void cargarTabla() {
        // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        try {
            // Leer los datos
            datosAutobuses = queryBusca.buscaAutobusesCompletos();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosAutobus : datosAutobuses) {
                modelo.addRow(new Object[]{datosAutobus[1], datosAutobus[2], datosAutobus[3], datosAutobus[4], datosAutobus[5], datosAutobus[6]});
            }
            // Crea un TableRowSorter para permitir la ordenación de las filas de la tabla.
            tr = new TableRowSorter<>(modelo);

            // Establece el TableRowSorter en la tabla 'JtableConductores'.
            JtableAutobuses.setRowSorter(tr);
        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public void cargarTabl(int opcionConsulta) {
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        try {
            limpiarTabla();
            switch (opcionConsulta) {
                case 1:
                    datosAutobuses = queryBusca.buscaAutobusesCompletos();
                    break;
                case 2:
                    datosAutobuses = queryBusca.buscaAutobusesActivos();
                    break;
                case 3:
                    datosAutobuses = queryBusca.buscaAutobusesInactivos();
                    break;
            }
            for (String[] datos : datosAutobuses) {
                modelo.addRow(datos);
            }
        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }


    /* Metodo que permite cargar las opciones en las listas
     Recibe por parametro el JComboBox al que se agregaran items */
    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        //  Obtenemos el modelo del JComboBox
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    // Obtenemos los valores de la tabla
                    datosListas = queryCarga.cargaComboMarca();
                    // listas.addElement("Seleccione una opcion");
                    // Asiganamos los valores obtenidos al JComboBox
                    for (int i = 0; i < datosListas.size(); i++) {
                        // Añadimos items por string dentro de la lista
                        listas.addElement(datosListas.get(i));
                    }
                    // Limpiamos la lista para cargar los datos del siguiente JComboBox
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboModelo();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 3:
                    datosListas = queryCarga.cargaComboCapacidad();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;

            }

        } catch (SQLException e) {
        }

    }

    /*Metodo que permite crear una lista de filtros que se puedan aplicar de
    manera simultanea*/
    public void aplicaFiltros() {
        // Obtenemos el modelo de la tabla y lo guardamos en la variable 'modelo'
        modelo = (DefaultTableModel) JtableAutobuses.getModel();
        // Creamos un TableRowSorter para el modelo de la tabla y lo guardamos en la variable 'tr'
        tr = new TableRowSorter<>(modelo);
        // Asignamos el TableRowSorter a la tabla para que se pueda ordenar y filtrar
        JtableAutobuses.setRowSorter(tr);
        // Creamos una lista para almacenar los filtros que se van a aplicar
        ArrayList<RowFilter<Object, Object>> filtros = new ArrayList<>();
        // Si se selecciona una marca (índice diferente de 0), agregamos un filtro para esa marca
        if (JcmbxMarcas.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxMarcas.getSelectedItem().toString(), 0));
        }
        // Si se selecciona un modelo (índice diferente de 0), agregamos un filtro para ese modelo
        if (JcmbxModelos.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxModelos.getSelectedItem().toString(), 1));
        }
        // Si se selecciona una capacidad (índice diferente de 0), agregamos un filtro para esa capacidad
        if (JcmbxCapacidad.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxCapacidad.getSelectedItem().toString(), 2));
        }
        // Creamos un filtro combinando todos los filtros de la lista
        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);

        // Aplicamos el filtro combinado al TableRowSorter
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        String[] valores = new String[6];
        int filaSeleccionada = JtableAutobuses.getSelectedRow();
        if (filaSeleccionada != -1) {
            for (int i = 0; i < JtableAutobuses.getColumnCount(); i++) {
                valores[i] = (String) JtableAutobuses.getValueAt(filaSeleccionada, i);
            }
        } else {
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }
        return valores;
    }

    public int buscarId(String marca, String modelo, String capacidad, String dia, String mes, String anio) {
        for (String[] autobus : datosAutobuses) {
            if (autobus[1].equals(marca) && autobus[2].equals(modelo) && autobus[3].equals(capacidad) && autobus[4].equals(dia) && autobus[5].equals(mes) && autobus[6].equals(anio)) {
                return Integer.parseInt(autobus[0]);
            }
        }
        return -1;
    }

    public void eliminar(int id) {
        try {
            // Buscar la terminal por ID
            String idTerminal = queryBusca.buscarTerminales(id);

            if (idTerminal != null || !idTerminal.isEmpty()) {
                // Llamar al método que elimina todas las dependencias y la terminal
                if (queryElimina.eliminarAutobusConductor(id)) {
                    if (queryElimina.eliminarAutobusRuta(id)) {
                        if (queryElimina.eliminarAutobusBaja(id)) {
                            if (queryElimina.eliminarAutobusReembolsos(id)) {
                                if (queryElimina.eliminarAutobusBoletosCliente(id)) {
                                    if (queryElimina.eliminarAutobusBoletos(id)) {
                                        if (queryElimina.eliminarAutobusAsiento(id)) {
                                            if (queryElimina.eliminarAutobus(id)) {
                                                CMensajes.msg("Autobus eliminado y su informacion asociada", "Eliminar");
                                            } else {
                                                CMensajes.msg_error("Ocurrió un error al eliminar el autobus", "Eliminar");
                                            }
                                        } else {
                                            CMensajes.msg_error("Ocurrió un error al eliminar el asiento", "Eliminar");
                                        }
                                    } else {
                                        CMensajes.msg_error("Ocurrió un error al eliminar el boletoo", "Eliminar");
                                    }
                                } else {
                                    CMensajes.msg_error("Ocurrió un error al eliminar Boletos-cliente", "Eliminar");
                                }
                            } else {
                                CMensajes.msg_error("Ocurrió un error al eliminar el reembolso", "Eliminar");
                            }
                        } else {
                            CMensajes.msg_error("Ocurrió un error el autobus de baja", "Eliminar");
                        }
                    } else {
                        CMensajes.msg_error("Ocurrió un error al eliminar Autobus-Ruta", "Eliminar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrió un error al eliminar Autpbus-Conductor", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Autobus no encontrado", "Eliminar");
            }
        } catch (SQLException e) {
            CMensajes.msg_error("Error de SQL: " + e.getMessage(), "Eliminar");
        } finally {
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaAutobuses = new javax.swing.JScrollPane();
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

            },
            new String [] {
                "Marca", "Modelo", "Capacidad", "Dia", "Mes", "Anio"
            }
        ));
        JtableAutobuses.getTableHeader().setResizingAllowed(false);
        JtableAutobuses.getTableHeader().setReorderingAllowed(false);
        JtableAutobuses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableAutobusesMouseClicked(evt);
            }
        });
        JSPTablaAutobuses.setViewportView(JtableAutobuses);
        if (JtableAutobuses.getColumnModel().getColumnCount() > 0) {
            JtableAutobuses.getColumnModel().getColumn(0).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(1).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(2).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(3).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(3).setPreferredWidth(100);
            JtableAutobuses.getColumnModel().getColumn(4).setResizable(false);
            JtableAutobuses.getColumnModel().getColumn(5).setResizable(false);
        }

        JpnlLienzo.add(JSPTablaAutobuses, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 500, 320));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });
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

        JcmbxOrdenar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Activos", "Inactivos" }));
        JcmbxOrdenar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxOrdenarItemStateChanged(evt);
            }
        });
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
        // Llamada al metodo aplicafiltros
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMarcasItemStateChanged

    private void JcmbxModelosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxModelosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxModelosItemStateChanged

    private void JcmbxCapacidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxCapacidadItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxCapacidadItemStateChanged

    private void JcmbxOrdenarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxOrdenarItemStateChanged
        // Aun no esta programado
        if (JcmbxOrdenar.getSelectedIndex() == 0) {
            cargarTabl(1);
        } else if (JcmbxOrdenar.getSelectedIndex() == 1) {
            cargarTabl(2);
        } else if (JcmbxOrdenar.getSelectedIndex() == 2) {
            cargarTabl(3);
        }
    }//GEN-LAST:event_JcmbxOrdenarItemStateChanged

    private void JtableAutobusesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableAutobusesMouseClicked
        // TODO add your handling code here:
        valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5]);
            }
        }
    }//GEN-LAST:event_JtableAutobusesMouseClicked

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
        if (JtableAutobuses.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5]) != -1) {
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5]);
                    eliminar(idEliminar);
                }
            } else {
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new JfAutobusConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaAutobuses;
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
    // End of variables declaration//GEN-END:variables
}
