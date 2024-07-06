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

public class JfBoletosConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CConsultas query = new CConsultas();
    private ArrayList<String[]> datosBoletos = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();

    public JfBoletosConsulta() {
        initComponents();
        JtableBoletos.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxOrigenes, 1);
        cargaComboBox(JcmbxDestinos, 2);
        cargaComboBox(JcmbxDias, 3);
        cargaComboBox(JcmbxMeses, 4);
        cargaComboBox(JcmbxAnios, 5);
        cargaComboBox(JcmbxPrecios, 6);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableBoletos.getModel();
        for (int i = (JtableBoletos.getRowCount() - 1); i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableBoletos.getModel();
        try {
            datosBoletos = query.buscaBoletos();
            limpiarTabla();
            for (String[] datosBoleto : datosBoletos) {
                switch (datosBoleto[6]) {
                    case "PP":
                        modelo.addRow(new Object[]{datosBoleto[0], datosBoleto[1], datosBoleto[2], datosBoleto[3], datosBoleto[4], datosBoleto[5],
                            "Primera Plus", datosBoleto[7]});
                        break;
                    case "C":
                        modelo.addRow(new Object[]{datosBoleto[0], datosBoleto[1], datosBoleto[2], datosBoleto[3], datosBoleto[4], datosBoleto[5],
                            "Comercial", datosBoleto[7]});
                        break;
                }
            }
        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = query.cargaComboOrigenes();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = query.cargaComboDestinos();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 3:
                    datosListas = query.cargaComboDias();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 4:
                    datosListas = query.cargaComboMeses();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 5:
                    datosListas = query.cargaComboAnios();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 6:
                    datosListas = query.cargaComboPrecio();
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
        modelo = (DefaultTableModel) JtableBoletos.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableBoletos.setRowSorter(tr);
        ArrayList<RowFilter<String, Integer>> filtros = new ArrayList<>();
        if (JcmbxOrigenes.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxOrigenes.getSelectedItem().toString(), 1));
        }
        if (JcmbxDestinos.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDestinos.getSelectedItem().toString(), 2));
        }
        if (JcmbxDias.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDias.getSelectedItem().toString(), 3));
        }
        if (JcmbxMeses.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxMeses.getSelectedItem().toString(), 4));
        }
        if (JcmbxAnios.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxAnios.getSelectedItem().toString(), 5));
        }
        if (JcmbxTiposBoletos.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxTiposBoletos.getSelectedItem().toString(), 6));
        }
        if (JcmbxPrecios.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxPrecios.getSelectedItem().toString(), 7));
        }
        RowFilter<String, Integer> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaBoletos = new javax.swing.JScrollPane();
        JtableBoletos = new javax.swing.JTable();
        JlblOrigen = new javax.swing.JLabel();
        JcmbxOrigenes = new javax.swing.JComboBox<>();
        JlblDestino = new javax.swing.JLabel();
        JcmbxDestinos = new javax.swing.JComboBox<>();
        JlblDia = new javax.swing.JLabel();
        JcmbxDias = new javax.swing.JComboBox<>();
        JlblMes = new javax.swing.JLabel();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JlblTipo = new javax.swing.JLabel();
        JcmbxTiposBoletos = new javax.swing.JComboBox<>();
        JlblPrecio = new javax.swing.JLabel();
        JcmbxPrecios = new javax.swing.JComboBox<>();
        JlblAnios = new javax.swing.JLabel();
        JcmbxAnios = new javax.swing.JComboBox<>();
        JbtnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Boletos");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JtableBoletos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Asiento", "Origen", "Destino", "Dia", "Mes", "Año", "Tipo de boleto", "Precio"
            }
        ));
        JSPTablaBoletos.setViewportView(JtableBoletos);

        JlblOrigen.setText("Origen");

        JcmbxOrigenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxOrigenes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxOrigenesItemStateChanged(evt);
            }
        });

        JlblDestino.setText("Destino");

        JcmbxDestinos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxDestinos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDestinosItemStateChanged(evt);
            }
        });

        JlblDia.setText("Dia");

        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxDias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDiasItemStateChanged(evt);
            }
        });

        JlblMes.setText("Mes");

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMesesItemStateChanged(evt);
            }
        });

        JlblTipo.setText("Tipo de Boleto");

        JcmbxTiposBoletos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Primera Plus", "Comercial" }));
        JcmbxTiposBoletos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxTiposBoletosItemStateChanged(evt);
            }
        });

        JlblPrecio.setText("Precio");

        JcmbxPrecios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxPrecios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxPreciosItemStateChanged(evt);
            }
        });

        JlblAnios.setText("Año");

        JcmbxAnios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxAnios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxAniosItemStateChanged(evt);
            }
        });

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSPTablaBoletos)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlblOrigen)
                            .addComponent(JcmbxOrigenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JlblTipo)
                            .addComponent(JcmbxTiposBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JlblPrecio)
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addComponent(JcmbxPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JbtnEliminar))
                            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                        .addComponent(JlblDestino)
                                        .addGap(133, 133, 133)
                                        .addComponent(JlblDia)
                                        .addGap(150, 150, 150)
                                        .addComponent(JlblMes))
                                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                        .addComponent(JcmbxDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(JcmbxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JcmbxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(JlblAnios))
                                    .addComponent(JcmbxAnios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JlblOrigen)
                            .addComponent(JlblDestino)
                            .addComponent(JlblDia)
                            .addComponent(JlblMes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JcmbxOrigenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JcmbxDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JcmbxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JcmbxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(JlblAnios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JcmbxAnios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(JlblPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JcmbxPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JbtnEliminar)))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JlblTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JcmbxTiposBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(JSPTablaBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void JcmbxOrigenesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxOrigenesItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxOrigenesItemStateChanged

    private void JcmbxDestinosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDestinosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDestinosItemStateChanged

    private void JcmbxDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDiasItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDiasItemStateChanged

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

    private void JcmbxAniosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxAniosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxAniosItemStateChanged

    private void JcmbxTiposBoletosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxTiposBoletosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxTiposBoletosItemStateChanged

    private void JcmbxPreciosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxPreciosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxPreciosItemStateChanged

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
            java.util.logging.Logger.getLogger(JfBoletosConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfBoletosConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfBoletosConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfBoletosConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfBoletosConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaBoletos;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxAnios;
    private javax.swing.JComboBox<String> JcmbxDestinos;
    private javax.swing.JComboBox<String> JcmbxDias;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JComboBox<String> JcmbxOrigenes;
    private javax.swing.JComboBox<String> JcmbxPrecios;
    private javax.swing.JComboBox<String> JcmbxTiposBoletos;
    private javax.swing.JLabel JlblAnios;
    private javax.swing.JLabel JlblDestino;
    private javax.swing.JLabel JlblDia;
    private javax.swing.JLabel JlblMes;
    private javax.swing.JLabel JlblOrigen;
    private javax.swing.JLabel JlblPrecio;
    private javax.swing.JLabel JlblTipo;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JTable JtableBoletos;
    // End of variables declaration//GEN-END:variables
}
