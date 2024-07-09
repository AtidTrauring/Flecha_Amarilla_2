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

public final class JfParadasConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String[]> datosParadas = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private int idActualizar;
    private int idEliminar;
    private String[] valoresFila;

    public JfParadasConsulta() {
        initComponents();
        JtableParadas.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxRutas, 1);
        cargaComboBox(JcmbxTerminales, 2);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableParadas.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        JcmbxRutas.setSelectedIndex(0);
        JcmbxTerminales.setSelectedIndex(0);
    }

    public void limpiarFiltro() {
        if (tr != null) {
            tr.setRowFilter(null);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableParadas.getModel();
        try {
            datosParadas = queryBusca.buscaParadasCompletas();
            limpiarTabla();
            for (String[] datosParadas : datosParadas) {
                modelo.addRow(new Object[]{datosParadas[1], datosParadas[2]});
            }
            tr = new TableRowSorter<>(modelo);
            JtableParadas.setRowSorter(tr);
        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaComboRutas();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboTerminales();
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
        modelo = (DefaultTableModel) JtableParadas.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableParadas.setRowSorter(tr);
        ArrayList<RowFilter<Object, Object>> filtros = new ArrayList<>();
        if (JcmbxRutas.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxRutas.getSelectedItem().toString(), 0));
        }
        if (JcmbxTerminales.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxTerminales.getSelectedItem().toString(), 1));
        }
        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        String[] valores = new String[2];
        int filaSeleccionada = JtableParadas.getSelectedRow();
        if (filaSeleccionada != -1) {
            for (int i = 0; i < JtableParadas.getColumnCount(); i++) {
                valores[i] = (String) JtableParadas.getValueAt(filaSeleccionada, i);
            }
        } else {
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }
        return valores;
    }

    public int buscarId(String ruta, String terminal) {
        for (String[] paradas : datosParadas) {
            if (paradas[1].equals(ruta) && paradas[2].equals(terminal)) {
                return Integer.parseInt(paradas[0]);
            }
        }
        // Si no se encuentra ninguna coincidencia, devuelve -1.
        return -1;
    }

    public void actualizar(int id) {
        String ruta = (String) JtableParadas.getValueAt(JtableParadas.getSelectedRow(), 0);
        String terminal = (String) JtableParadas.getValueAt(JtableParadas.getSelectedRow(), 1);
        try {
            String idParada = queryBusca.buscarParadas(id);
            if (idParada != null || idParada.isEmpty()) {
                if (queryActualiza.actualizarRuta(ruta, id)) {
                    CMensajes.msg("Se actualizo la informacion de la ruta", "Actualizar");
                    if (queryActualiza.actualizarTerminal(terminal, id)) {
                        CMensajes.msg("Se actualizo la terminal", "Actualizar");
                    } else {
                        CMensajes.msg_error("Ocurrio un error al actualizar la terminal", "Actualizar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrio un error al actualizar la ruta", "Actualizar");
                }

            } else {
                CMensajes.msg_error("Usuario no encontrado", "Actualizar-Buscar");
            }
        } catch (SQLException e) {
        } finally {
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
    }

    public void eliminar(int id) {
        try {
            String idParada = queryBusca.buscarParadas(id);
            if (idParada != null || idParada.isEmpty()) {
                if (queryElimina.eliminaRutaTerminal(id)) {
                    CMensajes.msg("Se elimino la parada", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Parada no encontrada ", "Eliminar-Buscar");
            }
        } catch (SQLException e) {
        } finally {
//            datosConductores.clear();
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaParadas = new javax.swing.JScrollPane();
        JtableParadas = new javax.swing.JTable();
        JlblRuta = new javax.swing.JLabel();
        JlblTerminal = new javax.swing.JLabel();
        JbtnActualizar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JcmbxRutas = new javax.swing.JComboBox<>();
        JcmbxTerminales = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Paradas");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JtableParadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Ruta", "Terminal (Parada)"
            }
        ));
        JtableParadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableParadasMouseClicked(evt);
            }
        });
        JSPTablaParadas.setViewportView(JtableParadas);

        JlblRuta.setText("Rutas");

        JlblTerminal.setText("Terminales");

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnActualizarActionPerformed(evt);
            }
        });

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });

        JcmbxRutas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxRutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxRutasActionPerformed(evt);
            }
        });

        JcmbxTerminales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxTerminales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxTerminalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSPTablaParadas, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlblRuta)
                    .addComponent(JlblTerminal)
                    .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JcmbxTerminales, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JcmbxRutas, javax.swing.GroupLayout.Alignment.LEADING, 0, 187, Short.MAX_VALUE))
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JbtnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JbtnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(JlblRuta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JcmbxRutas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(JlblTerminal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JcmbxTerminales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(JbtnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JbtnEliminar))
                    .addComponent(JSPTablaParadas, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

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

    private void JcmbxRutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxRutasActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxRutasActionPerformed

    private void JcmbxTerminalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxTerminalesActionPerformed
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxTerminalesActionPerformed

    private void JbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnActualizarActionPerformed
        // TODO add your handling code here:
        if (JtableParadas.getSelectedRow() != -1) {
            actualizar(idActualizar);
        } else {
            CMensajes.msg_error("Seleccione un registro", "Actualizar");
        }
    }//GEN-LAST:event_JbtnActualizarActionPerformed

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
        if (JtableParadas.getSelectedRow() != -1) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar la parada seleccionada?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (respuesta == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                int idEncontrado = buscarId(valoresFila[0], valoresFila[1]);
                if (idEncontrado != -1) {
                    idEliminar = idEncontrado;
                    eliminar(idEliminar);
                } else {
                    CMensajes.msg_error("Parada no encontrada", "Eliminar-Buscar");
                }
            } else {
                CMensajes.msg("Acción cancelada", "Eliminación");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");
        }

    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JtableParadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableParadasMouseClicked
        // TODO add your handling code here:
                 valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1]);
            }
        }
    }//GEN-LAST:event_JtableParadasMouseClicked

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
            java.util.logging.Logger.getLogger(JfParadasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfParadasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfParadasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfParadasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfParadasConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaParadas;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxRutas;
    private javax.swing.JComboBox<String> JcmbxTerminales;
    private javax.swing.JLabel JlblRuta;
    private javax.swing.JLabel JlblTerminal;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JTable JtableParadas;
    // End of variables declaration//GEN-END:variables
}
