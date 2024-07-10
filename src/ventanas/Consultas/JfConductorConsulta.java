package ventanas.Consultas;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class JfConductorConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private TableRowSorter tr;
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String[]> datosConductores = new ArrayList<>();
    private int idActualizar;
    private int idEliminar;
    private String[] valoresFila;

    public JfConductorConsulta() {
        initComponents();
        JtableConductores.getTableHeader().setReorderingAllowed(false);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        // Obtiene el modelo de la tabla
        modelo = (DefaultTableModel) JtableConductores.getModel();
        // Establece el numero de filas del modelo a 0
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        // Limpia los cuadro de texto
        JtxtNombres.setText(null);
        JtxtApPaterno.setText(null);
        JtxtApMaterno.setText(null);
        JtxtTelefono.setText(null);
    }

    public void limpiarFiltro() {
        // Si el objeto 'tr' tiene algun filtro
        if (tr != null) {
            // Elimina el filtro 
            tr.setRowFilter(null);
        }
    }

    public void cargarTabla() {
        // Obtiene el modelo de la tabla 
        modelo = (DefaultTableModel) JtableConductores.getModel();
        try {
            // Realiza una consulta para obtener los datos completos de los conductores.
            datosConductores = queryBusca.buscarConductoresCompletos();
            // Limpia las filas actuales de la tabla.
            limpiarTabla();
            // Recorre los datos de los conductores obtenidos de la consulta.
            for (String[] datosConductor : datosConductores) {
                // Añade una nueva fila al modelo de la tabla con los datos del conductor, omitiendo el ID
                modelo.addRow(new Object[]{datosConductor[1], datosConductor[2], datosConductor[3], datosConductor[4]});
            }

            // Crea un TableRowSorter para permitir la ordenación de las filas de la tabla.
            tr = new TableRowSorter<>(modelo);

            // Establece el TableRowSorter en la tabla 'JtableConductores'.
            JtableConductores.setRowSorter(tr);
        } catch (SQLException e) {
            // Muestra un mensaje de error si no se pudieron cargar los conductores en la tabla.
            CMensajes.msg_error("No se pudo cargar la información en la tabla", "Cargando Tabla");
        }
    }

    public void aplicaFiltros() {
        // Obtiene el modelo de la tabla 
        modelo = (DefaultTableModel) JtableConductores.getModel();

        // Crea un TableRowSorter para permitir la ordenación y filtrado de las filas de la tabla.
        tr = new TableRowSorter<>(modelo);

        // Establece el TableRowSorter en la tabla 
        JtableConductores.setRowSorter(tr);

        // Crea una lista para almacenar los filtros de filas.
        ArrayList<RowFilter<Object, Object>> filtros = new ArrayList<>();

        // Si los campos obtenidos no estan vacios aplica el filtro dependiento de lo que tenga por texto
        if (!JtxtNombres.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtNombres.getText().trim() + "$", 0));
        }
        if (!JtxtApPaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtApPaterno.getText().trim() + "$", 1));
        }
        if (!JtxtApMaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtApMaterno.getText().trim() + "$", 2));
        }
        if (!JtxtTelefono.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("^" + JtxtTelefono.getText().trim() + "$", 3));
        }

        // Crea un filtro compuesto que combina todos los filtros individuales usando una operación AND.
        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);

        // Establece el filtro de filas en el TableRowSorter.
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        // Crea un array de strings para almacenar los valores de la fila seleccionada.
        String[] valores = new String[4];

        // Obtiene el índice de la fila seleccionada en la tabla 'JtableConductores'.
        int filaSeleccionada = JtableConductores.getSelectedRow();

        // Verifica si hay una fila seleccionada.
        if (filaSeleccionada != -1) {
            // Recorre las columnas de la fila seleccionada y almacena los valores en el array.
            for (int i = 0; i < JtableConductores.getColumnCount(); i++) {
                valores[i] = (String) JtableConductores.getValueAt(filaSeleccionada, i);
            }
        } else {
            // Muestra un mensaje de error si no hay ninguna fila seleccionada.
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }

        // Devuelve el array con los valores de la fila seleccionada.
        return valores;
    }

    public int buscarId(String nombre, String apPat, String apMat, String telefono) {
        // Recorre la lista de datos de los conductores.
        for (String[] conductor : datosConductores) {
            // Comprueba si los valores de los parámetros coinciden con los datos del conductor actual.
            if (conductor[1].equals(nombre) && conductor[2].equals(apPat) && conductor[3].equals(apMat) && conductor[4].equals(telefono)) {
                // Si coinciden, devuelve el ID del conductor convertido a entero.
                return Integer.parseInt(conductor[0]);
            }
        }
        // Si no se encuentra ninguna coincidencia, devuelve -1.
        return -1;
    }

    public void actualizar(int id) {
        String nombre = (String) JtableConductores.getValueAt(JtableConductores.getSelectedRow(), 0);
        String apPaterno = (String) JtableConductores.getValueAt(JtableConductores.getSelectedRow(), 1);
        String apMaterno = (String) JtableConductores.getValueAt(JtableConductores.getSelectedRow(), 2);
        String telefono = (String) JtableConductores.getValueAt(JtableConductores.getSelectedRow(), 3);
        try {
            String idConductor = queryBusca.buscarConductor(id);
            if (idConductor != null || idConductor.isEmpty()) {
                if (queryActualiza.actualizarPersona(nombre, apPaterno, apMaterno, id)) {
                    CMensajes.msg("Se actualizo la informacion del conductor", "Actualizar");
                    if (queryActualiza.actualizarTelefono(telefono, id)) {
                        CMensajes.msg("Se actualizo el telefonos del conductor", "Actualizar");
                    } else {
                        CMensajes.msg_error("Ocurrio un error al actualizar el telefono", "Actualizar");
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
            cargarTabla();
        }
    }

    public void eliminar(int id) {
        try {
            String idConductor = queryBusca.buscarConductor(id);
            if (idConductor != null || idConductor.isEmpty()) {
                // Eliminando
                if (queryElimina.eliminaTelefono(id)) {
                    // Eliminando telefonos de la tabla telefono_persona
                    // Eliminando relacion de autobus con conductor
                    if (queryElimina.eliminaAutbousConductor(Integer.parseInt(idConductor))) {
                        // Eliminando relacion de ruta con Conductor
                        if (queryElimina.eliminaRutaConductor(Integer.parseInt(idConductor))) {
                            // Eliminando relacion de la tabla Conductor
                            if (queryElimina.eliminaConductor(id)) {
                                // Eliminando la persona en la tabla persona
                                if (queryElimina.eliminaPersona(id)) {
                                    CMensajes.msg("Se elimino al conductor seleccionado y su informacion relacionada", "Eliminar");
                                } else {
                                    CMensajes.msg_error("Ocurrio un error al eliminar a la persona", "Eliminando");
                                }
                            } else {
                                CMensajes.msg_error("Ocurrio un error al eliminar al conductor", "Eliminar");
                            }
                        } else {
                            CMensajes.msg_error("Ocurrio un error al eliminar \nlas rutas asociadas al conductor", "Eliminar");
                        }
                    } else {
                        CMensajes.msg_error("Ocurrio un error al eliminar \nlos autobuses asociadas al conductor", "Eliminar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrio un error al eliminar el telefono asociado al conductor", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Usuario no encontrado", "Eliminar-Buscar");
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
        JSPTablaConductores = new javax.swing.JScrollPane();
        JtableConductores = new javax.swing.JTable();
        JbtnActualizar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JlblNombres = new javax.swing.JLabel();
        JtxtNombres = new javax.swing.JTextField();
        JspNombres = new javax.swing.JSeparator();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApMaterno = new javax.swing.JLabel();
        JtxtApMaterno = new javax.swing.JTextField();
        JspApMaterno = new javax.swing.JSeparator();
        JlblTelefono = new javax.swing.JLabel();
        JtxtTelefono = new javax.swing.JTextField();
        JspTelefono = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conductores");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableConductores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Telefono"
            }
        ));
        JtableConductores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JtableConductores.setOpaque(false);
        JtableConductores.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JtableConductores.setShowGrid(false);
        JtableConductores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableConductoresMouseClicked(evt);
            }
        });
        JSPTablaConductores.setViewportView(JtableConductores);

        JpnlLienzo.add(JSPTablaConductores, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 500, 210));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JbtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnActualizarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 160, -1, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 194, 82, -1));

        JlblNombres.setText("Nombre(s)");
        JlblNombres.setFocusCycleRoot(true);
        JpnlLienzo.add(JlblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 6, -1, -1));

        JtxtNombres.setBorder(null);
        JtxtNombres.setFocusCycleRoot(true);
        JtxtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombresKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 28, 190, -1));

        JspNombres.setFocusCycleRoot(true);
        JpnlLienzo.add(JspNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 50, 190, 10));

        JlblApPaterno.setText("Apellido Paterno");
        JlblApPaterno.setFocusCycleRoot(true);
        JpnlLienzo.add(JlblApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 66, -1, -1));

        JtxtApPaterno.setBorder(null);
        JtxtApPaterno.setFocusCycleRoot(true);
        JtxtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApPaternoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 88, 190, -1));

        JspApPaterno.setFocusCycleRoot(true);
        JpnlLienzo.add(JspApPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 110, 190, 10));

        JlblApMaterno.setText("Apellido Materno");
        JlblApMaterno.setFocusCycleRoot(true);
        JpnlLienzo.add(JlblApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, -1, -1));

        JtxtApMaterno.setBorder(null);
        JtxtApMaterno.setFocusCycleRoot(true);
        JtxtApMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApMaternoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 190, -1));

        JspApMaterno.setFocusCycleRoot(true);
        JpnlLienzo.add(JspApMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 190, 10));

        JlblTelefono.setText("Telefono");
        JlblTelefono.setFocusCycleRoot(true);
        JpnlLienzo.add(JlblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, -1, -1));

        JtxtTelefono.setBorder(null);
        JtxtTelefono.setFocusCycleRoot(true);
        JtxtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtTelefonoKeyReleased(evt);
            }
        });
        JpnlLienzo.add(JtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 190, -1));

        JspTelefono.setFocusCycleRoot(true);
        JpnlLienzo.add(JspTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 190, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JtxtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombresKeyReleased
        // Aplicamos los filtros
        aplicaFiltros();
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        // Aplicamos los filtros
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApMaternoKeyReleased
        // Aplicamos los filtros
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApMaternoKeyReleased

    private void JtxtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtTelefonoKeyReleased
        // Aplicamos los filtros
        aplicaFiltros();
    }//GEN-LAST:event_JtxtTelefonoKeyReleased

    private void JbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnActualizarActionPerformed
        // Si el usuario selecciona una fila...
        if (JtableConductores.getSelectedRow() != -1) {
            actualizar(idActualizar);
            // Si no esta seleccionada una fila
        } else {
            // Se solicita que seleccione un registro
            CMensajes.msg_error("Seleccione un registro", "Eliminar");
        }
    }//GEN-LAST:event_JbtnActualizarActionPerformed

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // Si el usuario selecciona una fila...
        if (JtableConductores.getSelectedRow() != -1) {
            // Preguntamos si quiere eliminar el regitro seleccionado
            // Si el usuario confirma.
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                // Obtenemos los valores de la fila seleccionada en el arreglo valoresFila
                valoresFila = obtenerValoresFilaTabla();
                // Se busca el ID en el arreglo que almacena los valores de la tabla.
                // Si se encuentra el ID.
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]) != -1) {
                    // Se asigna el ID encontrado a la variable idEliminar.
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]);
                    // Eliminamos el registro
                    eliminar(idEliminar);
                }
                // Si cancela o indica que no quiere eliminar el registro
            } else {
                // Se muestra un mensaje cancelando la accion
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
            // Sino esta seleccionada una fila
        } else {
            // Se solicita que seleccione un registro
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JtableConductoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableConductoresMouseClicked
        // Se obtienen los valores de la fila seleccionada en la tabla.
        valoresFila = obtenerValoresFilaTabla();
        // Si se obtienen valores.
        if (valoresFila != null) {
            // Se busca el ID en el arreglo que almacena los valores de la tabla.
            // Si se encuentra el ID .
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3]);
            }
        }

    }//GEN-LAST:event_JtableConductoresMouseClicked

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
            java.util.logging.Logger.getLogger(JfConductorConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfConductorConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfConductorConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfConductorConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfConductorConsulta().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaConductores;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JLabel JlblApMaterno;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JLabel JlblTelefono;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JSeparator JspTelefono;
    private javax.swing.JTable JtableConductores;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    private javax.swing.JTextField JtxtTelefono;
    // End of variables declaration//GEN-END:variables
}
