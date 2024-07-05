package ventanas.Consultas;

import crud.CConsultas;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class JfPasajeroConsulta extends javax.swing.JFrame {

    private DefaultTableModel modelo;
    private TableRowSorter tr;
    private final CConsultas query = new CConsultas();
    private ArrayList<String[]> datosPasajeros = new ArrayList<>();

    public JfPasajeroConsulta() {
        initComponents();
        // Linea para impedir que sea posible mover los encabezados de cada tabla
        JtblPasajeros.getTableHeader().setReorderingAllowed(false);
        cargaTabla();

    }

    public void aplicaFiltros() {
        // Obtener el modelo de la tabla de pasajeros
        modelo = (DefaultTableModel) JtblPasajeros.getModel();

        // Crear un nuevo TableRowSorter utilizando el modelo de la tabla
        tr = new TableRowSorter<>(modelo);

        // Aplicar el TableRowSorter a la tabla JtblPasajeros
        JtblPasajeros.setRowSorter(tr);

        // Crear una lista para almacenar los filtros
        ArrayList<RowFilter<String, Integer>> filtros = new ArrayList<>();

        // Verificar si el campo de nombres no está vacío
        if (!JtxtNombres.getText().trim().isEmpty()) {
            // Agregar un filtro regex para filtrar por nombres (columna 0)
            filtros.add(RowFilter.regexFilter(JtxtNombres.getText().trim(), 0));
        }

        // Verificar si el campo de apellido paterno no está vacío
        if (!JtxtApPaterno.getText().trim().isEmpty()) {
            // Agregar un filtro regex para filtrar por apellido paterno (columna 1)
            filtros.add(RowFilter.regexFilter(JtxtApPaterno.getText().trim(), 1));
        }

        // Verificar si el campo de apellido materno no está vacío
        if (!JtxtApMaterno.getText().trim().isEmpty()) {
            // Agregar un filtro regex para filtrar por apellido materno (columna 2)
            filtros.add(RowFilter.regexFilter(JtxtApMaterno.getText().trim(), 2));
        }

        // Verificar si se ha seleccionado un tipo de pasajero en el JComboBox
        if (JcmbxTIpoPasajero.getSelectedIndex() != 0) {
            // Agregar un filtro regex para filtrar por tipo de pasajero (columna 3)
            filtros.add(RowFilter.regexFilter(JcmbxTIpoPasajero.getSelectedItem().toString(), 3));
        }

        // Crear un filtro compuesto AND que combina todos los filtros en la lista
        RowFilter<String, Integer> rf = RowFilter.andFilter(filtros);

        // Aplicar el filtro compuesto al TableRowSorter
        tr.setRowFilter(rf);
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtblPasajeros.getModel();
        for (int i = (JtblPasajeros.getRowCount() - 1); i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void cargaTabla() {
        // Obtener el modelo de la tabla de datos
        modelo = (DefaultTableModel) JtblPasajeros.getModel();
        try {
            // Leer los datos
            datosPasajeros = query.buscarPasajeros();
            // Limpiar la tabla
            limpiarTabla();
            // Asignar los datos en la tabla
            for (String[] datosPasajero : datosPasajeros) {
                /* Añadimos datos al modelo de la tabla Hacemos la seleccion con respecto al tipo de pasajero que sea /*
                    N -> Niño
                    A -> Adulto
                    D -> Docente
                    E -> Estudiante
                    AT -> INAPAM
                 */
                switch (datosPasajero[3]) {
                    case "N":
                        modelo.addRow(new Object[]{datosPasajero[0], datosPasajero[1], datosPasajero[2], "Niño", datosPasajero[4]});
                        break;
                    case "A":
                        modelo.addRow(new Object[]{datosPasajero[0], datosPasajero[1], datosPasajero[2], "Adulto", datosPasajero[4]});
                        break;
                    case "D":
                        modelo.addRow(new Object[]{datosPasajero[0], datosPasajero[1], datosPasajero[2], "Docente", datosPasajero[4]});
                        break;
                    case "E":
                        modelo.addRow(new Object[]{datosPasajero[0], datosPasajero[1], datosPasajero[2], "Estudiante", datosPasajero[4]});
                        break;
                    case "AT":
                        modelo.addRow(new Object[]{datosPasajero[0], datosPasajero[1], datosPasajero[2], "INAPAM", datosPasajero[4]});
                        break;
                }
            }

        } catch (SQLException e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaPasajeros = new javax.swing.JScrollPane();
        JtblPasajeros = new javax.swing.JTable();
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
        JbtnBuscar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JbtnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pasajeros");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtblPasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Tipo", "% de Descuento"
            }
        ));
        JtblPasajeros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JtblPasajeros.setEnabled(false);
        JtblPasajeros.setOpaque(false);
        JSPTablaPasajeros.setViewportView(JtblPasajeros);

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

        JbtnBuscar.setBackground(new java.awt.Color(160, 6, 70));
        JbtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnBuscar.setText("Buscar");
        JpnlLienzo.add(JbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 80, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 6, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(643, 290, 80, -1));

        JbtnActualizar.setBackground(new java.awt.Color(160, 6, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
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
    private javax.swing.JButton JbtnBuscar;
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
    private javax.swing.JTable JtblPasajeros;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables
}
