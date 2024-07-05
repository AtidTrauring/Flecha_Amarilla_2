package ventanas.Consultas;

import crud.CConsultas;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JfConductorConsulta extends javax.swing.JFrame {

    // Variable para manipular el modelo de la tabla
    private DefaultTableModel modelo;
    // Variable para poder agregar filtros
    private TableRowSorter tr;
    // Instancia de la clase que permite hacer las consultas "Transacciones"
    private final CConsultas query = new CConsultas();
    // Creacion de lista, para la obtencion de valores de la tabla
    private ArrayList<String[]> datosConductores = new ArrayList<>();
    private String nombre, apPat, apMat, telefono;
    private int idActualizar;

    public JfConductorConsulta() {
        initComponents();
        // Linea para impedir que sea posible mover los encabezados de cada tabla
//        JtableConductore&s.getTableHeader().setReorderingAllowed(false);
        cargarTabla();

    }

    public void filtrar(String valor, int columna) {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableConductores.getModel();
        // Nuestro Filtro recibe el modelo de la tabla a filtrar
        tr = new TableRowSorter(modelo);
        // Le indicamos a la tabla el filtro se usara 
        JtableConductores.setRowSorter(tr);
        // Si la opcion seleccionada no es 'Seleccione una opcion'
        if (valor != null) {
            // Aplicamos el filtro para hacerlo coincidir con el item seleccionadao en la columna indicada
            // tr.setRowFilter(RowFilter.regexFilter(lista.getSelectedItem().toString(), columna));
//            tr.setRowFilter(RowFilter.regexFilter("^" + valor + "$", columna));
            tr.setRowFilter(RowFilter.regexFilter(valor, columna));

            // En caso de serlo, no queremos que aplique el filtro proporcionado
        } else {
            JtableConductores.setRowSorter(tr);
        }
    }

    // Metodo para limpiar la tabla
    private void limpiarTabla() {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JtableConductores.getModel();
        // Por medio de un for, tomando en cuenta el numero de filas
        for (int i = (JtableConductores.getRowCount() - 1); i >= 0; i--) {
            // Eliminaremos las filas hasta que el valor del iterador sea mayor o igual a 0
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JtableConductores.getModel();
        try {
            // Leer los datos
            datosConductores = query.buscarConductoresCompletos();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosConductor : datosConductores) {
//                modelo.addRow(new Object[]{datosConductor[0], datosConductor[1], datosConductor[2], datosConductor[3]});
                modelo.addRow(new Object[]{datosConductor[1], datosConductor[2], datosConductor[3], datosConductor[4]});
            }

        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    private String[] valoresFinales() {
        String[] valoresSeleccionados = new String[4];
        DefaultTableModel modelTabla = (DefaultTableModel) JtableConductores.getModel();
        if (modelTabla.getRowCount() != 0) { //Tabla con filas
            if (JtableConductores.getSelectedRow() != -1) {
                valoresSeleccionados[0] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 0);
                valoresSeleccionados[1] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 1);
                valoresSeleccionados[2] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 2);
                valoresSeleccionados[3] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 3);
                return valoresSeleccionados;
            }
        } else {//Tabla sin filas
            CMensajes.msg_error("No hay valores para obtener", "Obteniendo datos fila");
        }
        return valoresSeleccionados;
    }

    private String[] valoresIniciales() {
        String[] valores = new String[4];
        DefaultTableModel modelTabla = (DefaultTableModel) JtableConductores.getModel();
        if (modelTabla.getRowCount() != 0) { //Tabla con filas
            if (JtableConductores.getSelectedRow() != -1) {
                valores[0] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 0);
                valores[1] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 1);
                valores[2] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 2);
                valores[3] = (String) modelTabla.getValueAt(JtableConductores.getSelectedRow(), 3);
                return valores;
            }
        } else {//Tabla sin filas
            CMensajes.msg_error("No hay valores para obtener", "Obteniendo datos fila");
        }
        return valores;
    }

    public int buscarId(String nombre, String apPat, String apMat, String telefono) {
        int Id = -1;
        for (String[] conductor : datosConductores) {
            if (conductor[1].equals(nombre) && conductor[2].equals(apPat) && conductor[3].equals(apMat) && conductor[4].equals(telefono)) {
                return Integer.parseInt(conductor[0]);
            } else {
                Id = 0;
            }
        }
        return Id;
    }

    public void actualizar(int id, String[] valoresActualizar) {
        boolean comprueba = true;
        try {
            comprueba = query.buscarConductor(id);
            if (comprueba) {
                // Puedes actualizar
                if (query.actualizarPersona(valoresActualizar[0], valoresActualizar[1], valoresActualizar[2], id)) {
                    CMensajes.msg("Se actualizo la informacion del conductor", "Actualizar");
                    if (query.actualizarTelefono(valoresActualizar[3], id)) {
                        CMensajes.msg("Se actualizo el telefonos del conductor", "Actualizar");
                    } else {
                        CMensajes.msg_error("Ocurrio un error al actualizar el telefono", "Actualizar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrio un error al actualizar", "Actualizar");
                }

            } else {
                // Mensaje de error
                CMensajes.msg_error("Usuario no encontrado", "Actualizar-Buscar");
            }
        } catch (Exception e) {
        } finally {
            cargarTabla();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JtableConductoresMousePressed(evt);
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
        filtrar(JtxtNombres.getText(), 0);
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        filtrar(JtxtApPaterno.getText(), 1);
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApMaternoKeyReleased
        filtrar(JtxtApMaterno.getText(), 2);
    }//GEN-LAST:event_JtxtApMaternoKeyReleased

    private void JtxtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtTelefonoKeyReleased
        filtrar(JtxtTelefono.getText(), 3);
    }//GEN-LAST:event_JtxtTelefonoKeyReleased

    private void JbtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnActualizarActionPerformed
        String[] valoresIniciales = valoresIniciales();
        System.out.println("Los valores actualizados son: " + valoresIniciales[0] + valoresIniciales[1] + valoresIniciales[2] + valoresIniciales[3]);
        System.out.println("El id a actualizar es " + idActualizar);
        actualizar(idActualizar, valoresIniciales);
    }//GEN-LAST:event_JbtnActualizarActionPerformed

    private void JtableConductoresMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableConductoresMousePressed
        String[] valoresIniciales = valoresIniciales();
        System.out.println("Los valores obtenidos par buscar son: " + valoresIniciales[0] + valoresIniciales[1] + valoresIniciales[2] + valoresIniciales[3]);
        idActualizar = buscarId(valoresIniciales[0], valoresIniciales[1], valoresIniciales[2], valoresIniciales[3]);
    }//GEN-LAST:event_JtableConductoresMousePressed

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
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables
}
