package ventanas.Consultas;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class JfPasajeroConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private TableRowSorter tr;
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String[]> datosPasajeros = new ArrayList<>();
    private int idActualizar;
    private int idEliminar;
    private String[] valoresFila;

    public JfPasajeroConsulta() {
        initComponents();
        JtablePasajeros.getTableHeader().setReorderingAllowed(false);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtablePasajeros.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        // Limpia los cuadro de texto
        JtxtNombres.setText(null);
        JtxtApPaterno.setText(null);
        JtxtApMaterno.setText(null);
        JcmbxTIpoPasajero.setSelectedIndex(0);
    }

    public void limpiarFiltro() {
        if (tr != null) {
            tr.setRowFilter(null);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtablePasajeros.getModel();
        try {
            datosPasajeros = queryBusca.buscarPasajerosCompletos();
            limpiarTabla();
            for (String[] datosPasajero : datosPasajeros) {
                /* Añadimos datos al modelo de la tabla Hacemos la seleccion con respecto al tipo de pasajero que sea /*
                    N -> Niño
                    A -> Adulto
                    D -> Docente
                    E -> Estudiante
                    AT -> INAPAM
                 */
                switch (datosPasajero[4]) {
                    case "N":
                        modelo.addRow(new Object[]{datosPasajero[1], datosPasajero[2], datosPasajero[3], "Niño", datosPasajero[5]});
                        break;
                    case "A":
                        modelo.addRow(new Object[]{datosPasajero[1], datosPasajero[2], datosPasajero[3], "Adulto", datosPasajero[5]});
                        break;
                    case "D":
                        modelo.addRow(new Object[]{datosPasajero[1], datosPasajero[2], datosPasajero[3], "Docente", datosPasajero[5]});
                        break;
                    case "E":
                        modelo.addRow(new Object[]{datosPasajero[1], datosPasajero[2], datosPasajero[3], "Estudiante", datosPasajero[5]});
                        break;
                    case "AT":
                        modelo.addRow(new Object[]{datosPasajero[1], datosPasajero[2], datosPasajero[3], "INAPAM", datosPasajero[5]});
                        break;
                }
            }
            tr = new TableRowSorter<>(modelo);
            JtablePasajeros.setRowSorter(tr);
        } catch (SQLException e) {
        }
    }

    public void aplicaFiltros() {
        modelo = (DefaultTableModel) JtablePasajeros.getModel();
        tr = new TableRowSorter<>(modelo);
        JtablePasajeros.setRowSorter(tr);
        ArrayList<RowFilter<Object, Object>> filtros = new ArrayList<>();
        if (!JtxtNombres.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtNombres.getText().trim() + "$", 0));
        }
        if (!JtxtApPaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtApPaterno.getText().trim() + "$", 1));
        }
        if (!JtxtApMaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtApMaterno.getText().trim() + "$", 2));
        }
        if (JcmbxTIpoPasajero.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxTIpoPasajero.getSelectedItem().toString(), 3));
        }
        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        String[] valores = new String[5];
        int filaSeleccionada = JtablePasajeros.getSelectedRow();
        if (filaSeleccionada != -1) {
            for (int i = 0; i < JtablePasajeros.getColumnCount(); i++) {
                valores[i] = (String) JtablePasajeros.getValueAt(filaSeleccionada, i);
            }
        } else {
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }
        return valores;
    }

    public int buscarId(String nombre, String apPat, String apMat, String tipo, String descuento) {
        for (String[] pasajeros : datosPasajeros) {
            if (pasajeros[1].equals(nombre) && pasajeros[2].equals(apPat) && pasajeros[3].equals(apMat) && pasajeros[4].equals(tipo) && pasajeros[5].equals(descuento)) {
                return Integer.parseInt(pasajeros[0]);
            }
        }
        return -1;
    }

    public void eliminar(int id) {
        try {
            String idPasajero = queryBusca.buscaPasajero(id);
            if (idPasajero != null && !idPasajero.isEmpty()) {
                if (queryElimina.eliminarPasajeroReembolso(id)) {
                    if (queryElimina.eliminarPasajeroBoletoCliente(id)) {
                        if (queryElimina.eliminarPasajeroBoleto(id)) {
                            if (queryElimina.eliminarPasajero(id)) {
                                
                            } else {
                                CMensajes.msg_error("Ocurrió un error al eliminar al pasajero", "Eliminar");
                            }
                        } else {
                            CMensajes.msg_error("Ocurrió un error al eliminar el boleto", "Eliminar");
                        }
                    } else {
                        CMensajes.msg_error("Ocurrió un error al eliminar Boleto-Cliente", "Eliminar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrió un error al eliminar el reembolso", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Pasajero no encontrado", "Eliminar-Buscar");
            }
        } catch (SQLException e) {
            CMensajes.msg_error("Error al eliminar el pasajero: " + e.getMessage(), "Error");
        } finally {
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
    }

    public String regresaTipo(String tipoTabla) {
        String tipoPasajero = "";
        switch (tipoTabla) {
            case "Niño":
                tipoPasajero = "N";
                break;
            case "Adulto":
                tipoPasajero = "A";
                break;
            case "Estudiante":
                tipoPasajero = "E";
                break;
            case "Docente":
                tipoPasajero = "D";
                break;
            case "INAPAM":
                tipoPasajero = "AT";
                break;
        }
        return tipoPasajero;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaPasajeros = new javax.swing.JScrollPane();
        JtablePasajeros = new javax.swing.JTable();
        JlblTipoPasajero = new javax.swing.JLabel();
        JcmbxTIpoPasajero = new javax.swing.JComboBox<>();
        JlblNombres = new javax.swing.JLabel();
        JtxtNombres = new javax.swing.JTextField();
        JspNombres = new javax.swing.JSeparator();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApMaterno = new javax.swing.JLabel();
        JtxtApMaterno = new javax.swing.JTextField();
        JspApMaterno = new javax.swing.JSeparator();
        JbtnEliminar = new javax.swing.JButton();
        JbtnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pasajeros");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtablePasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Tipo", "% de Descuento"
            }
        ));
        JtablePasajeros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JtablePasajeros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtablePasajerosMouseClicked(evt);
            }
        });
        JSPTablaPasajeros.setViewportView(JtablePasajeros);

        JpnlLienzo.add(JSPTablaPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, 390));

        JlblTipoPasajero.setText("Tipo de pasajero");
        JpnlLienzo.add(JlblTipoPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        JcmbxTIpoPasajero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Niño", "Adulto", "Estudiante", "Docente", "INAPAM" }));
        JcmbxTIpoPasajero.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxTIpoPasajeroItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxTIpoPasajero, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 170, -1));

        JlblNombres.setText("Nombre(s)");
        JpnlLienzo.add(JlblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

        JtxtNombres.setBorder(null);
        JtxtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombresKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, 170, -1));
        JpnlLienzo.add(JspNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 133, 170, 10));

        JlblApPaterno.setText("Apellido Paterno");
        JpnlLienzo.add(JlblApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        JtxtApPaterno.setBorder(null);
        JtxtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApPaternoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 170, -1));
        JpnlLienzo.add(JspApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, 170, 10));

        JlblApMaterno.setText("Apellido Materno");
        JpnlLienzo.add(JlblApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, -1, -1));

        JtxtApMaterno.setBorder(null);
        JtxtApMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApMaternoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 170, -1));
        JpnlLienzo.add(JspApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, 170, 10));

        JbtnEliminar.setBackground(new java.awt.Color(160, 6, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 290, 80, -1));

        JbtnActualizar.setBackground(new java.awt.Color(160, 6, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnActualizarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxTIpoPasajeroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxTIpoPasajeroItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxTIpoPasajeroItemStateChanged

    private void JtxtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombresKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApMaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApMaternoKeyReleased

    private void JbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JbtnActualizarActionPerformed

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
        if (JtablePasajeros.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], regresaTipo(valoresFila[3]), valoresFila[4]) != -1) {
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], regresaTipo(valoresFila[3]), valoresFila[4]);
                    eliminar(idEliminar);
                }
            } else {
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JtablePasajerosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtablePasajerosMouseClicked
        // TODO add your handling code here:
        valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4]) != -1) {
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4]);
            }
        }
    }//GEN-LAST:event_JtablePasajerosMouseClicked

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
            java.util.logging.Logger.getLogger(JfPasajeroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfPasajeroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfPasajeroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfPasajeroConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfPasajeroConsulta().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaPasajeros;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxTIpoPasajero;
    private javax.swing.JLabel JlblApMaterno;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JLabel JlblTipoPasajero;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JTable JtablePasajeros;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables
}
