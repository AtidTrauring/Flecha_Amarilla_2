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

public class JfConduceConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CConsultas query = new CConsultas();
    private ArrayList<String[]> datosConduce = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();

    public JfConduceConsulta() {
        initComponents();
        JtableConducen.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxMarcas, 1);
        cargaComboBox(JcmbxModelos, 2);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableConducen.getModel();
        for (int i = (JtableConducen.getRowCount() - 1); i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableConducen.getModel();
        try {
            datosConduce = query.buscaConduce();
            limpiarTabla();
            for (String[] datosCon : datosConduce) {
                modelo.addRow(new Object[]{datosCon[0], datosCon[1], datosCon[2], datosCon[3], datosCon[4], datosCon[5]});
            }
        } catch (Exception e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = query.cargaComboMarca();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = query.cargaComboModelo();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
            }
        } catch (SQLException e) {
        }
    }

    public void aplicaFiltros() {
        modelo = (DefaultTableModel) JtableConducen.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableConducen.setRowSorter(tr);
        ArrayList<RowFilter<String, Integer>> filtros = new ArrayList<>();
        if (!JtxtNombres.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtNombres.getText(), 0));
        }
        if (!JtxtApPaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtApPaterno.getText(), 1));
        }
        if (!JtxtApMaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtApMaterno.getText(), 2));
        }
        if (!JtxtPlaca.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtPlaca.getText(), 3));
        }
        if (JcmbxMarcas.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxMarcas.getSelectedItem().toString(), 4));
        }
        if (JcmbxModelos.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxModelos.getSelectedItem().toString(), 5));
        }

        RowFilter<String, Integer> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaConducen = new javax.swing.JScrollPane();
        JtableConducen = new javax.swing.JTable();
        JlblNombres = new javax.swing.JLabel();
        JtxtNombres = new javax.swing.JTextField();
        JspNombres = new javax.swing.JSeparator();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApMaterno = new javax.swing.JLabel();
        JtxtApMaterno = new javax.swing.JTextField();
        JspApMaterno = new javax.swing.JSeparator();
        JlblMarca = new javax.swing.JLabel();
        JcmbxMarcas = new javax.swing.JComboBox<>();
        JcmbxModelos = new javax.swing.JComboBox<>();
        JlblModelo = new javax.swing.JLabel();
        JlblPlaca = new javax.swing.JLabel();
        JtxtPlaca = new javax.swing.JTextField();
        JspPlaca = new javax.swing.JSeparator();
        JbtnActualizar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JbtnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conductores asignados");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JtableConducen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Placa", "Marca", "Modelo"
            }
        ));
        JSPTablaConducen.setViewportView(JtableConducen);

        JlblNombres.setText("Nombre(s)");

        JtxtNombres.setBorder(null);
        JtxtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombresKeyReleased(evt);
            }
        });

        JlblApPaterno.setText("Apellido Paterno");

        JtxtApPaterno.setBorder(null);
        JtxtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApPaternoKeyReleased(evt);
            }
        });

        JlblApMaterno.setText("Apelldio Materno");

        JtxtApMaterno.setBorder(null);
        JtxtApMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApMaternoKeyReleased(evt);
            }
        });

        JlblMarca.setText("Marca");

        JcmbxMarcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxMarcas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMarcasItemStateChanged(evt);
            }
        });

        JcmbxModelos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxModelos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxModelosItemStateChanged(evt);
            }
        });

        JlblModelo.setText("Modelo");

        JlblPlaca.setText("Placa");

        JtxtPlaca.setBorder(null);
        JtxtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtPlacaKeyReleased(evt);
            }
        });

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");

        JbtnBuscar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnBuscar.setText("Buscar");

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSPTablaConducen)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(JlblNombres, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JspNombres, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JtxtNombres, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JlblApPaterno)
                                    .addComponent(JspApPaterno)
                                    .addComponent(JtxtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JlblApMaterno)
                                    .addComponent(JspApMaterno)
                                    .addComponent(JtxtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JlblMarca)
                                    .addComponent(JcmbxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JlblModelo)
                                    .addComponent(JcmbxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JlblPlaca)
                                    .addComponent(JspPlaca)
                                    .addComponent(JtxtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JbtnActualizar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JbtnEliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JbtnBuscar, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        JpnlLienzoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JbtnActualizar, JbtnBuscar, JbtnEliminar});

        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpnlLienzoLayout.createSequentialGroup()
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addComponent(JlblNombres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JspNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addComponent(JlblApPaterno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JtxtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JtxtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JspApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JspApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addComponent(JlblMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JcmbxMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addComponent(JlblModelo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JcmbxModelos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addComponent(JlblPlaca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JtxtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JspPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpnlLienzoLayout.createSequentialGroup()
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlblApMaterno)
                            .addComponent(JbtnActualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JbtnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JbtnBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(JSPTablaConducen, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void JtxtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombresKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApMaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApMaternoKeyReleased

    private void JtxtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtPlacaKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtPlacaKeyReleased

    private void JcmbxMarcasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMarcasItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMarcasItemStateChanged

    private void JcmbxModelosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxModelosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxModelosItemStateChanged

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
            java.util.logging.Logger.getLogger(JfConduceConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfConduceConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfConduceConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfConduceConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfConduceConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaConducen;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnBuscar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxMarcas;
    private javax.swing.JComboBox<String> JcmbxModelos;
    private javax.swing.JLabel JlblApMaterno;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblMarca;
    private javax.swing.JLabel JlblModelo;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JLabel JlblPlaca;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JSeparator JspPlaca;
    private javax.swing.JTable JtableConducen;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    private javax.swing.JTextField JtxtPlaca;
    // End of variables declaration//GEN-END:variables
}
