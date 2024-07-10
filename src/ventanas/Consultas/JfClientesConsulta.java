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

public final class JfClientesConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private TableRowSorter tr;
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private ArrayList<String[]> datosClientes = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private int idActualizar;
    private String[] valoresFila;
    private int idEliminar;

    public JfClientesConsulta() {
        initComponents();
        JtableClientes.getTableHeader().setReorderingAllowed(false);
        cargaTabla();

    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableClientes.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        // Limpia los cuadro de texto
        JtxtNombres.setText(null);
        JtxtApPaterno.setText(null);
        JtxtApMaterno.setText(null);
        JtxtCorreo.setText(null);
    }

    public void limpiarFiltro() {
        // Si el objeto 'tr' tiene algun filtro
        if (tr != null) {
            // Elimina el filtro 
            tr.setRowFilter(null);
        }
    }

    public void cargaTabla() {
        modelo = (DefaultTableModel) JtableClientes.getModel();
        try {
            datosClientes = queryBusca.buscarClientesCompletos();
            limpiarTabla();
            for (String[] datosCliente : datosClientes) {
                modelo.addRow(new Object[]{datosCliente[1], datosCliente[2], datosCliente[3], datosCliente[4]});
            }
            tr = new TableRowSorter<>(modelo);
            JtableClientes.setRowSorter(tr);
        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la información en la tabla", "Cargando Tabla");
        }
    }

    public void aplicaFiltros() {
        modelo = (DefaultTableModel) JtableClientes.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableClientes.setRowSorter(tr);

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
        if (!JtxtCorreo.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtCorreo.getText().trim() + "$", 3));
        }

        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        String[] valores = new String[4];
        int filaSeleccionada = JtableClientes.getSelectedRow();
        if (filaSeleccionada != -1) {
            for (int i = 0; i < JtableClientes.getColumnCount(); i++) {
                valores[i] = (String) JtableClientes.getValueAt(filaSeleccionada, i);
            }
        } else {
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }
        return valores;
    }

    public int buscarId(String nombre, String apPat, String apMat, String correo) {
        for (String[] cliente : datosClientes) {
            if (cliente[1].equals(nombre) && cliente[2].equals(apPat) && cliente[3].equals(apMat) && cliente[4].equals(correo)) {
                return Integer.parseInt(cliente[0]);
            }
        }
        return -1;
    }

    public void actualizar(int id) {
        String nombre = (String) JtableClientes.getValueAt(JtableClientes.getSelectedRow(), 0);
        String apPaterno = (String) JtableClientes.getValueAt(JtableClientes.getSelectedRow(), 1);
        String apMaterno = (String) JtableClientes.getValueAt(JtableClientes.getSelectedRow(), 2);
        String correo = (String) JtableClientes.getValueAt(JtableClientes.getSelectedRow(), 3);
        try {
            String idCliente = queryBusca.buscarClientes(id);
            if (idCliente != null || idCliente.isEmpty()) {
                if (queryActualiza.actualizarPersona(nombre, apPaterno, apMaterno, id)) {
                    CMensajes.msg("Se actualizo la informacion del cliente", "Actualizar");
                    if (queryActualiza.actualizarCorreo(correo, id)) {
                        CMensajes.msg("Se actualizo el correo del cliente", "Actualizar");
                    } else {
                        CMensajes.msg_error("Ocurrio un error al actualizar el correo", "Actualizar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrio un error al actualizar", "Actualizar");
                }

            } else {
                CMensajes.msg_error("Usuario no encontrado", "Actualizar-Buscar");
            }
        } catch (SQLException e) {
        } finally {
//            datosConductores.clear();
            limpiarBuscadores();
            limpiarFiltro();
            cargaTabla();
        }
    }

    public void eliminar(int id) {
        try {
            String idCliente = queryBusca.buscarClientes(id);
            if (idCliente != null || idCliente.isEmpty()) {
                if (queryElimina.eliminaTelefono(id)) {
                    CMensajes.msg("Se elimino el telefono correspondiente", "Eliminar");
                if (queryElimina.eliminaBoletoCliente(Integer.parseInt(idCliente))) {
                    CMensajes.msg("Se eliminaron las relaciones del cliente \ncon los boletos correspondientes", "Eliminar");
                    if (queryElimina.eliminaTarjetaCliente(Integer.parseInt(idCliente))) {
                        CMensajes.msg("Se elimino al cliente seleccionado", "Eliminar");
                        if (queryElimina.eliminaCliente(id)) {
                            CMensajes.msg("Se elimino el cliente", "Eliminar");
                            if (queryElimina.eliminaPersona(id)) {
                                CMensajes.msg("Se elimino al cliente seleccionado", "Eliminar");
                            } else {
                                CMensajes.msg_error("Ocurrio un error al eliminar a la persona", "Eliminando");
                            }
                        } else {
                            CMensajes.msg_error("Ocurrio un error al eliminar al cliente", "Eliminando");
                        }
                    } else {
                        CMensajes.msg_error("Ocurrio un error al eliminar \n el cliente en tarjeta", "Eliminar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrio un error al eliminar \nlas compras asociadas al cliente", "Eliminar");
                }
                } else {
                    CMensajes.msg_error("Ocurrio un error al eliminar el telefono asociado del cliente", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Usuario no encontrado", "Eliminar-Buscar");
            }
        } catch (SQLException e) {
        } finally {
//            datosConductores.clear();
            limpiarBuscadores();
            limpiarFiltro();
            cargaTabla();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaClientes = new javax.swing.JScrollPane();
        JtableClientes = new javax.swing.JTable();
        JlblNombres = new javax.swing.JLabel();
        JtxtNombres = new javax.swing.JTextField();
        JspNombres = new javax.swing.JSeparator();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApMaterno = new javax.swing.JLabel();
        JtxtApMaterno = new javax.swing.JTextField();
        JspApMaterno = new javax.swing.JSeparator();
        JlblCorreo = new javax.swing.JLabel();
        JtxtCorreo = new javax.swing.JTextField();
        JspCorreo = new javax.swing.JSeparator();
        JbtnActualizar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Correo"
            }
        ));
        JtableClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JtableClientes.getTableHeader().setReorderingAllowed(false);
        JtableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableClientesMouseClicked(evt);
            }
        });
        JSPTablaClientes.setViewportView(JtableClientes);

        JpnlLienzo.add(JSPTablaClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 760, 320));

        JlblNombres.setText("Nombre(s)");
        JpnlLienzo.add(JlblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        JtxtNombres.setBorder(null);
        JtxtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombresKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 28, 160, -1));
        JpnlLienzo.add(JspNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 50, 160, 10));

        JlblApPaterno.setText("Apellido Paterno");
        JpnlLienzo.add(JlblApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        JtxtApPaterno.setBorder(null);
        JtxtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApPaternoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 160, -1));
        JpnlLienzo.add(JspApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 160, 10));

        JlblApMaterno.setText("Apellido Materno");
        JpnlLienzo.add(JlblApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        JtxtApMaterno.setBorder(null);
        JtxtApMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApMaternoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 160, -1));
        JpnlLienzo.add(JspApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 160, 10));

        JlblCorreo.setText("Correo");
        JpnlLienzo.add(JlblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        JtxtCorreo.setBorder(null);
        JtxtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtCorreoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 160, -1));
        JpnlLienzo.add(JspCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 160, 10));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnActualizarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 82, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JtxtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombresKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApMaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApMaternoKeyReleased

    private void JtxtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtCorreoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtCorreoKeyReleased

    private void JbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnActualizarActionPerformed
        // TODO add your handling code here:
        if (JtableClientes.getSelectedRow() != -1) {
            actualizar(idActualizar);
        } else {
            CMensajes.msg_error("Seleccione un registro", "Actualizar");
        }
    }//GEN-LAST:event_JbtnActualizarActionPerformed

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
         if (JtableClientes.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]) != -1) {
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]);
                    eliminar(idEliminar);
                }
            } else {
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JtableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableClientesMouseClicked
        // TODO add your handling code here:
        valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]);
            }
        }
    }//GEN-LAST:event_JtableClientesMouseClicked

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
            java.util.logging.Logger.getLogger(JfClientesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfClientesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfClientesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfClientesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfClientesConsulta().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaClientes;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JLabel JlblApMaterno;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblCorreo;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspCorreo;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JTable JtableClientes;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtCorreo;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables
}
