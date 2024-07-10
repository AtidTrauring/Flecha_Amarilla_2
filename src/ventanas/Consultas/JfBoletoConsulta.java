package ventanas.Consultas;

import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import ventanas.Registros.JfRegistroPasajeros;

public final class JfBoletoConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultComboBoxModel listas;
    private final CCargaCombos queryCarga = new CCargaCombos();
    private final CBusquedas queryBusca = new CBusquedas();
    private ArrayList<String[]> datosBoletos = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private final Calendar fechaCalendario = Calendar.getInstance();

    public JfBoletoConsulta() {
        initComponents();
        cargaComboBox(JcmbxOrigenes, 1);
        cargaComboBox(JcmbxDestinos, 2);
        cargaComboBox(JcmbxMeses, 4);
    }

    public void limpiarSeleccion() {
        JcmbxDestinos.setSelectedIndex(0);
        JcmbxOrigenes.setSelectedIndex(0);
        JcmbxMeses.setSelectedIndex(0);
        JcmbxDias.setSelectedIndex(0);
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    datosListas = queryCarga.cargaComboCiudad();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboCiudad();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 3:
                    String[] dias = asignaDias(JcmbxMeses);
                    if (dias != null) {
                        for (String dia : dias) {
                            listas.addElement(dia);
                        }
                        dias = null;
                    }
                    break;

                case 4:
                    ArrayList<String[]> Meses = queryCarga.cargaComboMeses();
                    for (String[] Mes : Meses) {
                        listas.addElement(Mes[1]);
                    }
                    Meses.clear();
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

    private boolean validaCombos() {
        return JcmbxDestinos.getSelectedIndex() == 0
                || JcmbxOrigenes.getSelectedIndex() == 0
                || JcmbxMeses.getSelectedIndex() == 0
                || JcmbxDias.getSelectedIndex() == 0;
    }

    private String cantidadBoletos(int cantidadBoletos) {
        String numeroBoletos;
        switch (cantidadBoletos) {
            case 1:
                if (JOptionPane.showConfirmDialog(
                        null,
                        "Unicamente hay un boleto disponible...\n ¿Desea comprarlo?",
                        "Boletos Disponibles",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
                    numeroBoletos = "1";
                } else {
                    numeroBoletos = "0";
                }

                break;
            case 2:
                numeroBoletos = (String) JOptionPane.showInputDialog(
                        null,
                        "Existen " + cantidadBoletos + " boletos disponibles\n Seleccione una cantidad a comprar...",
                        "Boletos Disponibles",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, // null para icono defecto
                        new String[]{"1 boleto", "2 boletos"},
                        "1 boleto");
                break;
            case 3:
                numeroBoletos = (String) JOptionPane.showInputDialog(
                        null,
                        "Existen " + cantidadBoletos + " boletos disponibles\n Seleccione una cantidad a comprar...",
                        "Boletos Disponibles",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, // null para icono defecto
                        new String[]{"1 boleto", "2 boletos", "3 boletos"},
                        "1 boleto");
                break;
            case 4:
                numeroBoletos = (String) JOptionPane.showInputDialog(
                        null,
                        "Existen " + cantidadBoletos + " boletos disponibles\n Seleccione una cantidad a comprar...",
                        "Boletos Disponibles",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, // null para icono defecto
                        new String[]{"1 boleto", "2 boletos", "3 boletos", "4 boletos"},
                        "1 boleto");
                break;
            case 5:
            default:
                numeroBoletos = (String) JOptionPane.showInputDialog(
                        null,
                        "Existen " + cantidadBoletos + " boletos disponibles\n Seleccione una cantidad a comprar...",
                        "Boletos Disponibles",
                        JOptionPane.INFORMATION_MESSAGE,
                        null, // null para icono defecto
                        new String[]{"1 boleto", "2 boletos", "3 boletos", "4 boletos", "5 boletos"},
                        "1 boleto");
                break;

        }
        return numeroBoletos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JcmbxOrigenes = new javax.swing.JComboBox<>();
        JcmbxDestinos = new javax.swing.JComboBox<>();
        JcmbxDias = new javax.swing.JComboBox<>();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JbtnConsultar = new javax.swing.JButton();
        JlblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comprar");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JcmbxOrigenes.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JcmbxOrigenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un origen" }));
        JpnlLienzo.add(JcmbxOrigenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, -1));

        JcmbxDestinos.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JcmbxDestinos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un destino" }));
        JpnlLienzo.add(JcmbxDestinos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, -1));

        JcmbxDias.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un dia" }));
        JpnlLienzo.add(JcmbxDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 130, -1));

        JcmbxMeses.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un mes" }));
        JcmbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMesesItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 130, -1));

        JbtnConsultar.setText("Consultar");
        JbtnConsultar.setToolTipText("Verificar dispinibiliad de los boletos");
        JbtnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnConsultarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 90, -1));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoBoleto.jpg"))); // NOI18N
        JpnlLienzo.add(JlblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 300));

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

    private void JbtnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnConsultarActionPerformed
        if (validaCombos()) {
            CMensajes.msg_advertencia("Seleccione una opcion por lista", "Compra");
        } else {
            try {
                datosBoletos = queryBusca.buscaAsientos(JcmbxDestinos.getSelectedItem().toString(),
                        JcmbxOrigenes.getSelectedItem().toString(),
                        JcmbxMeses.getSelectedItem().toString(),
                        JcmbxDias.getSelectedItem().toString(),
                        String.valueOf(fechaCalendario.get(Calendar.YEAR))
                );
                if (datosBoletos.size() == 0) {
                    CMensajes.msg_advertencia("No contamos con asientos disponibles", "Compra");
                    limpiarSeleccion();
                } else {

                    String compraBoletos = cantidadBoletos(datosBoletos.size());
                    if (compraBoletos != null) {
                        int numeroBoletos = 0;
                        switch (compraBoletos) {
                            case "1 boleto":
                                numeroBoletos = 1;
                                break;
                            case "2 boletos":
                                numeroBoletos = 2;
                                break;
                            case "3 boletos":
                                numeroBoletos = 3;
                                break;
                            case "4 boletos":
                                numeroBoletos = 4;
                                break;
                            case "5 boletos":
                                numeroBoletos = 5;
                                break;
                        }
//                        limpiarSeleccion();
                        JfRegistroPasajeros rp = new JfRegistroPasajeros();
                        rp.asignaPasajeros(numeroBoletos);
                        rp.asignaAsientos(datosBoletos);
                        rp.setVisible(true);
                        rp.setLocationRelativeTo(null);
                        rp.setSize(445, 290);
                        rp.setResizable(false);
                        rp.setTitle("Registro Pasajeros");
                    } else {
                        CMensajes.msg("Entendido", "Escoge boletos");
                        limpiarSeleccion();
                    }
                }
            } catch (SQLException ex) {
                CMensajes.msg_error("Ocurrio un error al obtener los asientos", "Comprar");
            }
        }
    }//GEN-LAST:event_JbtnConsultarActionPerformed

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        if (JcmbxMeses.getSelectedIndex() != 0) {
            if (JcmbxDias.getItemCount() == 1) {
                cargaComboBox(JcmbxDias, 3);
            } else if (JcmbxDias.getItemCount() > 1) {
                while (JcmbxDias.getItemCount() > 1) {
                    JcmbxDias.removeItemAt(1);
                }
                cargaComboBox(JcmbxDias, 3);
            }
        } else {
            while (JcmbxDias.getItemCount() > 1) {
                JcmbxDias.removeItemAt(1);
            }
        }
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
            java.util.logging.Logger.getLogger(JfBoletoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfBoletoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfBoletoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfBoletoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfBoletoConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnConsultar;
    private javax.swing.JComboBox<String> JcmbxDestinos;
    private javax.swing.JComboBox<String> JcmbxDias;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JComboBox<String> JcmbxOrigenes;
    private javax.swing.JLabel JlblFondo;
    private javax.swing.JPanel JpnlLienzo;
    // End of variables declaration//GEN-END:variables
}
