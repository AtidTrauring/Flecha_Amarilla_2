package ventanas.Consultas;

import crud.CConsultas;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class JfUsuarioConsulta extends javax.swing.JFrame {

    public JfUsuarioConsulta() {
        initComponents();
        // Linea para impedir que sea posible mover los encabezados de cada tabla
        JtblPasajeros.getTableHeader().setReorderingAllowed(false);
        cargaTabla();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        JpnlLienzo = new javax.swing.JPanel();
        JspTablaPasajeros = new javax.swing.JScrollPane();
        JtblPasajeros = new javax.swing.JTable();
        JlblTiposPasajeros = new javax.swing.JLabel();
        JcmbxUsuarios = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios General");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtblPasajeros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno"
            }
        ));
        JtblPasajeros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        JtblPasajeros.setEnabled(false);
        JtblPasajeros.setOpaque(false);
        JspTablaPasajeros.setViewportView(JtblPasajeros);

        JpnlLienzo.add(JspTablaPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 370, 320));

        JlblTiposPasajeros.setText("Tipo de usuario");
        JpnlLienzo.add(JlblTiposPasajeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        JcmbxUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JpnlLienzo.add(JcmbxUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JpnlLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private DefaultTableModel modelo;
    CConsultas query = new CConsultas();
    ArrayList<String[]> datosPasajeros = new ArrayList<>();

    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtblPasajeros.getModel();
        for (int i = (JtblPasajeros.getRowCount() - 1); i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void cargaTabla() {
        // Obtener el modelo de la tabla de datos
        modelo = (DefaultTableModel) JtblPasajeros.getModel();
        // Agregamos las columnas necesarias a mostrar.
        modelo.addColumn("Nombre(s)");
        modelo.addColumn("Apellido Paterno");
        modelo.addColumn("Apellido Materno");
        try {
            // Leer los datos
            datosPasajeros = query.buscaUsuarios();
            // Limpiar la tabla
            limpiarTabla();
            // Asignar los datos en la tabla
            for (String[] datosPasajero : datosPasajeros) {
                /* Añadimos datos al modelo de la tabla */
                modelo.addRow(new Object[]{datosPasajero[0], datosPasajero[1], datosPasajero[2]});
            }

        } catch (SQLException e) {
        }
    }

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
            java.util.logging.Logger.getLogger(JfUsuarioConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfUsuarioConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfUsuarioConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfUsuarioConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfUsuarioConsulta().setVisible(true);

            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JcmbxUsuarios;
    private javax.swing.JLabel JlblTiposPasajeros;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JScrollPane JspTablaPasajeros;
    private javax.swing.JTable JtblPasajeros;
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables
}
