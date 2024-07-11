package ventanas.Consultas;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class JfRutasConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
  private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String[]> datosRutas = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private int idActualizar;
    private String[] valoresFila;
    private int idEliminar;

    public JfRutasConsulta() {
        initComponents();
        JtableRutas.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxOrigenes, 1);
        cargaComboBox(JcmbxDestinos, 2);
        cargaComboBox(JcmbxDistancias, 3);
        cargaComboBox(JcmbxDuraciones, 4);
        cargaComboBox(JcmbxPrecios, 5);
        cargarTabla();
    }

    //**************** METODOS ******************/  
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableRutas.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        // Limpia los cuadro de texto
        JtxtNombreTerminal.setText(null);
        JcmbxOrigenes.setSelectedIndex(0);
        JcmbxDestinos.setSelectedIndex(0);
        JcmbxDistancias.setSelectedIndex(0);
        JcmbxDuraciones.setSelectedIndex(0);
        JcmbxPrecios.setSelectedIndex(0);
    }

    public void limpiarFiltro() {
        if (tr != null) {
            tr.setRowFilter(null);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableRutas.getModel();
        try {
            datosRutas = queryBusca.buscaRutasCompletas();
            limpiarTabla();
            for (String[] ruta : datosRutas) {
                modelo.addRow(new Object[]{ruta[1], ruta[2], ruta[3], ruta[4], ruta[5], ruta[6], ruta[7], ruta[8]});
            }

        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla.");
        }
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaComboOrigenes();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboDestinos();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 3:
                    datosListas = queryCarga.cargaComboDistacia();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 4:
                    datosListas = queryCarga.cargaComboDuracion();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 5:
                    datosListas = queryCarga.cargaComboPrecioRuta();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }

        } catch (SQLException e) {
        }

    }

    public void aplicaFiltros() {
        modelo = (DefaultTableModel) JtableRutas.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableRutas.setRowSorter(tr);
        ArrayList<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (!JtxtNombreTerminal.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtNombreTerminal.getText().trim() + "$", 0));
        }
        if (JcmbxOrigenes.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxOrigenes.getSelectedItem().toString(), 1));
        }
        if (JcmbxDestinos.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDestinos.getSelectedItem().toString(), 2));
        }
        if (JcmbxDistancias.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDistancias.getSelectedItem().toString(), 3));
        }
        if (JcmbxDuraciones.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDuraciones.getSelectedItem().toString(), 6));
        }
        if (JcmbxPrecios.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxPrecios.getSelectedItem().toString(), 7));
        }
        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        String[] valores = new String[8];
        int filaSeleccionada = JtableRutas.getSelectedRow();
        if (filaSeleccionada != -1) {
            for (int i = 0; i < JtableRutas.getColumnCount(); i++) {
                valores[i] = (String) JtableRutas.getValueAt(filaSeleccionada, i);
            }
        } else {
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }
        return valores;
    }

    public int buscarId(String nombre, String origen, String destino, String distancia, String salida, String llegada, String duracion, String precio) {
        for (String[] ruta : datosRutas) {
            if (ruta[1].equals(nombre) && ruta[2].equals(origen) && ruta[3].equals(destino) && ruta[4].equals(distancia) && ruta[5].equals(salida)
                    && ruta[6].equals(llegada) && ruta[7].equals(duracion) && ruta[8].equals(precio)) {
                return Integer.parseInt(ruta[0]);
            }
        }
        return -1;
    }
    
    public void actualizar(int id){
        String nombre = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 0);
        String origen = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 1);
        String destino = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 2);
        String distancia = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 3);
        String salida = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 4);
        String llegada = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 5);
        String duracion = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 6);
        String precio = (String) JtableRutas.getValueAt(JtableRutas.getSelectedRow(), 7);
        float precioFlotante = Float.parseFloat(precio);
        try {
            String idRuta = queryBusca.buscarRutas(id);
            if (idRuta != null || idRuta.isEmpty()) {
                if (queryActualiza.actualizarTerminal(origen, id)) {
                    if (queryActualiza.actualizarTerminal(destino, id)) {
                        if (queryActualiza.actualizarRuta2(id, nombre, duracion, salida, llegada, precioFlotante, distancia)) {
                            CMensajes.msg("Se actualizo la informacion de la ruta.", "Actualizar");
                        } else {
                            CMensajes.msg_error("Error al actualizar la ruta.", "Actualizar");
                        }
                    } else {
                        CMensajes.msg_error("Error al actualizar la terminal de destino.", "Actualizar");
                    }
                } else {
                    CMensajes.msg_error("Error al actualizar la terminal de origen.", "Actualizar");
                }
            } else {
                CMensajes.msg_error("Ocurrio un error al actualizar.", "Actualizar");
            }
        } catch (Exception e) {
        } finally {
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
    }

  public void eliminar(int id) {
    try {
        String idRuta = queryBusca.buscarRutas(id);
        
        if (idRuta != null || idRuta.isEmpty()) {
            // Llamar al método que elimina todas las dependencias y la ruta
            if (queryElimina.eliminarRutaCompleta(Integer.parseInt(idRuta))) {
                CMensajes.msg("Ruta eliminada correctamente", "Eliminar");
            } else {
                CMensajes.msg_error("Ocurrió un error al eliminar la ruta", "Eliminar");
            }
        } else {
            CMensajes.msg_error("Ruta no encontrada", "Eliminar");
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
        JSPTablaRutas = new javax.swing.JScrollPane();
        JtableRutas = new javax.swing.JTable();
        JbtnActualizar = new javax.swing.JButton();
        JlblOrigen = new javax.swing.JLabel();
        JcmbxOrigenes = new javax.swing.JComboBox<>();
        JlblDestino = new javax.swing.JLabel();
        JcmbxDestinos = new javax.swing.JComboBox<>();
        JlblDistancia = new javax.swing.JLabel();
        JcmbxDistancias = new javax.swing.JComboBox<>();
        JlblDuracion = new javax.swing.JLabel();
        JcmbxDuraciones = new javax.swing.JComboBox<>();
        JlblPrecio = new javax.swing.JLabel();
        JcmbxPrecios = new javax.swing.JComboBox<>();
        JlblNombreTerminal = new javax.swing.JLabel();
        JtxtNombreTerminal = new javax.swing.JTextField();
        JspNombreTerminal = new javax.swing.JSeparator();
        JbtnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rutas");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Origen", "Destino", "Distancia", "Salida", "Llegada", "Duracion", "Precio"
            }
        ));
        JtableRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableRutasMouseClicked(evt);
            }
        });
        JSPTablaRutas.setViewportView(JtableRutas);

        JpnlLienzo.add(JSPTablaRutas, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 600, 182));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnActualizarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        JlblOrigen.setText("Origen");
        JpnlLienzo.add(JlblOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        JcmbxOrigenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxOrigenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxOrigenesActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxOrigenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        JlblDestino.setText("Destino");
        JpnlLienzo.add(JlblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        JcmbxDestinos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDestinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDestinosActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDestinos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        JlblDistancia.setText("Distancia");
        JpnlLienzo.add(JlblDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        JcmbxDistancias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDistancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDistanciasActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDistancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        JlblDuracion.setText("Duracion");
        JpnlLienzo.add(JlblDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        JcmbxDuraciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDuraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDuracionesActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDuraciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, -1));

        JlblPrecio.setText("Precio");
        JpnlLienzo.add(JlblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        JcmbxPrecios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxPreciosActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        JlblNombreTerminal.setText("Nombre de la terminal");
        JpnlLienzo.add(JlblNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        JtxtNombreTerminal.setBorder(null);
        JpnlLienzo.add(JtxtNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 150, -1));
        JpnlLienzo.add(JspNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 293, 150, 10));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 82, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxOrigenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxOrigenesActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxOrigenesActionPerformed

    private void JcmbxDestinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDestinosActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDestinosActionPerformed

    private void JcmbxDistanciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDistanciasActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDistanciasActionPerformed

    private void JcmbxDuracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDuracionesActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDuracionesActionPerformed

    private void JcmbxPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxPreciosActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxPreciosActionPerformed

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
        if (JtableRutas.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]) != -1) {
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]);
                    eliminar(idEliminar);
                }
            } else {
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JtableRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableRutasMouseClicked
        // TODO add your handling code here:
        valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]);
            }
        }
    }//GEN-LAST:event_JtableRutasMouseClicked

    private void JbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnActualizarActionPerformed
        if (JtableRutas.getSelectedRow() != -1) {
            actualizar(idActualizar);
        } else {
            CMensajes.msg_error("Seleccione un registro para actualizar.", "Actualizar");
        }
    }//GEN-LAST:event_JbtnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRutasConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaRutas;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxDestinos;
    private javax.swing.JComboBox<String> JcmbxDistancias;
    private javax.swing.JComboBox<String> JcmbxDuraciones;
    private javax.swing.JComboBox<String> JcmbxOrigenes;
    private javax.swing.JComboBox<String> JcmbxPrecios;
    private javax.swing.JLabel JlblDestino;
    private javax.swing.JLabel JlblDistancia;
    private javax.swing.JLabel JlblDuracion;
    private javax.swing.JLabel JlblNombreTerminal;
    private javax.swing.JLabel JlblOrigen;
    private javax.swing.JLabel JlblPrecio;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspNombreTerminal;
    private javax.swing.JTable JtableRutas;
    private javax.swing.JTextField JtxtNombreTerminal;
    // End of variables declaration//GEN-END:variables
}
