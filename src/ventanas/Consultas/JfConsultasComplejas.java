package ventanas.Consultas;

import crud.CBusquedas;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class JfConsultasComplejas extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private final CBusquedas queryBusca = new CBusquedas();
    private DefaultTableModel modelo;
    private ArrayList<String[]> datosConsultas;

    public JfConsultasComplejas() {

        initComponents();
    }

    //**************** METODOS ******************/
    // Metodo para limpiar la tabla
    // Método para limpiar la tabla
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableConsultas.getModel();
        modelo.setRowCount(0);
        modelo.setColumnCount(0);
    }

    public void cargarTabla(String[] columnas, int numColumnas, int opcionConsulta) {
        modelo = (DefaultTableModel) JtableConsultas.getModel();
        try {
            limpiarTabla();
            switch (opcionConsulta) {
                case 1:
                    datosConsultas = queryBusca.consulta8();
                    break;
                case 2:
                    datosConsultas = queryBusca.consulta12();
                    break;
                case 3:
                    datosConsultas = queryBusca.consulta13();
                    break;
                case 4:
                    datosConsultas = queryBusca.consulta14();
                    break;
            }

            for (int i = 0; i < numColumnas; i++) {
                modelo.addColumn(columnas[i]);
            }
            for (String[] datos : datosConsultas) {
                modelo.addRow(datos);
            }
            JtableConsultas.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException e) {
            // Asegúrate de que CMensajes.msg_error() esté definido y accesible para manejar errores
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public void eleccionCarga() {
        switch (JcmbxConsultas.getSelectedIndex()) {
            case 1:
                cargarTabla(new String[]{"Pasajero", "Conductor", "Ruta", "Fecha"}, 4, 1);
                break;
            case 2:
                cargarTabla(new String[]{"Chofer", "Ruta", "Origen", "Destino", "Parada", "Cliente"}, 6, 2);
                break;
            case 3:
                cargarTabla(new String[]{"Origen", "Destino"}, 2, 3);
                break;
            case 4:
                cargarTabla(new String[]{"Asiento", "Marca", "Modelo"}, 3, 4);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaConsultas = new javax.swing.JScrollPane();
        JtableConsultas = new javax.swing.JTable();
        JcmbxConsultas = new javax.swing.JComboBox<>();
        JlblConsultas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultas Complejas");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));

        JtableConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        JSPTablaConsultas.setViewportView(JtableConsultas);

        JcmbxConsultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Pasajeros y Conductores que viajaron el día 16 de Junio de 2024.", "Información de los pasajeros con viaje “Ciudad de México - Cancún” de los choferes y de las paradas de dicho viaje.", "Ciudades de origen y destino con 3 choferes.", "Asientos disponibles del camión correspondiente a la ruta Ciudad de México – Morelia el 20 de junio 2024." }));
        JcmbxConsultas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxConsultasItemStateChanged(evt);
            }
        });

        JlblConsultas.setText("Consultas complejas");

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JcmbxConsultas, 0, 737, Short.MAX_VALUE)
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(JlblConsultas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(JSPTablaConsultas))
                .addContainerGap())
        );
        JpnlLienzoLayout.setVerticalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JlblConsultas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JcmbxConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JSPTablaConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxConsultasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxConsultasItemStateChanged
        if (JcmbxConsultas.getSelectedIndex() == 0) {
            limpiarTabla();
            datosConsultas.clear();
        } else {
            eleccionCarga();
        }
    }//GEN-LAST:event_JcmbxConsultasItemStateChanged
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
            java.util.logging.Logger.getLogger(JfConsultasComplejas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfConsultasComplejas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfConsultasComplejas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfConsultasComplejas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfConsultasComplejas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaConsultas;
    private javax.swing.JComboBox<String> JcmbxConsultas;
    private javax.swing.JLabel JlblConsultas;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JTable JtableConsultas;
    // End of variables declaration//GEN-END:variables
}
