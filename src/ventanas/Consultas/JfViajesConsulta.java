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

public final class JfViajesConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CConsultas query = new CConsultas();
    private ArrayList<String[]> datosViaje = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();

    public JfViajesConsulta() {
        initComponents();
        cargaComboBox(JcmbxMarca, 1);
        cargaComboBox(JcmbxModelo, 2);
        cargaComboBox(JcmbxPlaca, 3);
        cargaComboBox(JcmbxMeses, 4);
        cargaComboBox(JcmbxOrigenes, 5);
        cargaComboBox(JcmbxDestinos, 6);
        cargarTabla();
        JtableViajes.getTableHeader().setReorderingAllowed(false);
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableViajes.getModel();
        for (int i = (JtableViajes.getRowCount() - 1); i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableViajes.getModel();
        try {
            datosViaje = query.buscaViaje();
            limpiarTabla();
            for (String[] datosVia : datosViaje) {
                modelo.addRow(new Object[]{datosVia[0], datosVia[1], datosVia[2], datosVia[3], datosVia[4], datosVia[5], datosVia[6]});
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
                case 3:
                    datosListas = query.cargaComboPlaca();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 4:
                    ArrayList<String[]> Meses = query.cargaComboMeses();
                    for (String[] Mes : Meses) {
                        listas.addElement(Mes[1]);
                    }
                    Meses.clear();
                    break;
                case 5:
                    datosListas = query.cargaComboOrigenes();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 6:
                    datosListas = query.cargaComboDestinos();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 7:
                    String[] dias = asignaDias(JcmbxMeses);
                    if (dias != null) {
                        for (String dia : dias) {
                            listas.addElement(dia);
                        }
                        dias = null;
                    }
                    break;
            }
        } catch (SQLException e) {
        }

    }

    public String[] asignaDias(JComboBox mes) {
        String[] Dias = null;
        String mesSeleccionado = mes.getSelectedItem().toString();
        switch (mesSeleccionado) {
            case "Enero":
            case "Marzo":
            case "Mayo":
            case "Julio":
            case "Agosto":
            case "Octubre":
            case "Diciembre":
                Dias = new String[31];
                for (int i = 0; i < 31; i++) {
                    Dias[i] = String.valueOf(i + 1);
                }
                break;
            case "Abril":
            case "Junio":
            case "Septiembre":
            case "Noviembre":
                Dias = new String[30];
                for (int i = 0; i < 30; i++) {
                    Dias[i] = String.valueOf(i + 1);
                }
                break;
            case "Febrero":
                Dias = new String[29];
                for (int i = 0; i < 29; i++) {
                    Dias[i] = String.valueOf(i + 1);
                }
                break;
        }
        return Dias;
    }

    public void aplicaFiltros() {
        modelo = (DefaultTableModel) JtableViajes.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableViajes.setRowSorter(tr);
        ArrayList<RowFilter<String, Integer>> filtros = new ArrayList<>();
        if (JcmbxOrigenes.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxOrigenes.getSelectedItem().toString(), 0));
        }
        if (JcmbxDestinos.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDestinos.getSelectedItem().toString(), 1));
        }
        if (JcmbxPlaca.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxPlaca.getSelectedItem().toString(), 2));
        }
        if (JcmbxModelo.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxModelo.getSelectedItem().toString(), 3));
        }
        if (JcmbxMarca.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxMarca.getSelectedItem().toString(), 4));
        }
        if (JcmbxDias.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDias.getSelectedItem().toString(), 5));
        }
        if (JcmbxMeses.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxMeses.getSelectedItem().toString(), 6));
        }

        RowFilter<String, Integer> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaViajes = new javax.swing.JScrollPane();
        JtableViajes = new javax.swing.JTable();
        JlblDia = new javax.swing.JLabel();
        JlblMes = new javax.swing.JLabel();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JlblMarca = new javax.swing.JLabel();
        JcmbxMarca = new javax.swing.JComboBox<>();
        JlblModelo = new javax.swing.JLabel();
        JcmbxModelo = new javax.swing.JComboBox<>();
        JlblOrigen = new javax.swing.JLabel();
        JcmbxOrigenes = new javax.swing.JComboBox<>();
        JlblDestino = new javax.swing.JLabel();
        JcmbxDestinos = new javax.swing.JComboBox<>();
        JlblMes1 = new javax.swing.JLabel();
        JcmbxPlaca = new javax.swing.JComboBox<>();
        JcmbxDias = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Viajes");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JtableViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Origen", "Destino", "Placa", "Modelo", "Marca", "Dia", "Mes"
            }
        ));
        JSPTablaViajes.setViewportView(JtableViajes);

        JlblDia.setText("Dia");

        JlblMes.setText("Mes");

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMesesItemStateChanged(evt);
            }
        });

        JlblMarca.setText("Marca");

        JcmbxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMarcaItemStateChanged(evt);
            }
        });

        JlblModelo.setText("Modelo");

        JcmbxModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxModelo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxModeloItemStateChanged(evt);
            }
        });

        JlblOrigen.setText("Origen");

        JcmbxOrigenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxOrigenes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxOrigenesItemStateChanged(evt);
            }
        });

        JlblDestino.setText("Destino");

        JcmbxDestinos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDestinos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDestinosItemStateChanged(evt);
            }
        });

        JlblMes1.setText("Placa");

        JcmbxPlaca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxPlaca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxPlacaItemStateChanged(evt);
            }
        });

        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un mes" }));
        JcmbxDias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDiasItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JSPTablaViajes)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JlblDia)
                            .addComponent(JlblMes)
                            .addComponent(JcmbxMeses, 0, 160, Short.MAX_VALUE)
                            .addComponent(JcmbxDias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JlblMarca)
                            .addComponent(JcmbxMarca, 0, 160, Short.MAX_VALUE)
                            .addComponent(JlblModelo)
                            .addComponent(JcmbxModelo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JcmbxOrigenes, 0, 160, Short.MAX_VALUE)
                            .addComponent(JlblOrigen)
                            .addComponent(JlblDestino)
                            .addComponent(JcmbxDestinos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(JlblMes1)
                    .addComponent(JcmbxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JlblDia)
                    .addComponent(JlblMarca)
                    .addComponent(JlblOrigen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcmbxMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JcmbxOrigenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JcmbxDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JlblMes)
                    .addComponent(JlblModelo)
                    .addComponent(JlblDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcmbxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JcmbxModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JcmbxDestinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JlblMes1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JcmbxPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JSPTablaViajes, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void JcmbxPlacaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxPlacaItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxPlacaItemStateChanged

    private void JcmbxDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDiasItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDiasItemStateChanged

    private void JcmbxOrigenesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxOrigenesItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxOrigenesItemStateChanged

    private void JcmbxDestinosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDestinosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDestinosItemStateChanged

    private void JcmbxModeloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxModeloItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxModeloItemStateChanged

    private void JcmbxMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMarcaItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMarcaItemStateChanged

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        if (JcmbxMeses.getSelectedIndex() != 0) {
            if (JcmbxDias.getItemCount() == 1) {
                
                cargaComboBox(JcmbxDias, 7);
            } else if (JcmbxDias.getItemCount() > 1) {
                while (JcmbxDias.getItemCount() > 1) {
                    JcmbxDias.removeItemAt(1);
                }
                cargaComboBox(JcmbxDias, 7);
            }
        } else {
            while (JcmbxDias.getItemCount() > 1) {
                JcmbxDias.removeItemAt(1);
            }
        }
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

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
            java.util.logging.Logger.getLogger(JfViajesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfViajesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfViajesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfViajesConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfViajesConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaViajes;
    private javax.swing.JComboBox<String> JcmbxDestinos;
    private javax.swing.JComboBox<String> JcmbxDias;
    private javax.swing.JComboBox<String> JcmbxMarca;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JComboBox<String> JcmbxModelo;
    private javax.swing.JComboBox<String> JcmbxOrigenes;
    private javax.swing.JComboBox<String> JcmbxPlaca;
    private javax.swing.JLabel JlblDestino;
    private javax.swing.JLabel JlblDia;
    private javax.swing.JLabel JlblMarca;
    private javax.swing.JLabel JlblMes;
    private javax.swing.JLabel JlblMes1;
    private javax.swing.JLabel JlblModelo;
    private javax.swing.JLabel JlblOrigen;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JTable JtableViajes;
    // End of variables declaration//GEN-END:variables
}
