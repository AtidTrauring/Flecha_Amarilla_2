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

public class JfRutasConsulta extends javax.swing.JFrame {
    
 private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CConsultas query = new CConsultas();
    private ArrayList<String[]> datosRutas = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();

    public JfRutasConsulta() {
        initComponents();
         JtableRutas.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxOrigenes, 1);
        cargaComboBox(JcmbxDestinos, 2);
        cargaComboBox(JcmbxDistancias, 3);
        cargaComboBox(JcmbxDuraciones, 4);
        cargaComboBox(JcmbxPrecios, 5);
        // Cargas los valores de la tabla
        cargarTabla();
    }

     public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    // Obtenemos los valores de la tabla
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
                    datosListas = query.cargaComboDistacia();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 4:
                    datosListas = query.cargaComboDuracion();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 5:
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

    public void filtrar(JComboBox lista, int columna) {
        modelo = (DefaultTableModel) JtableRutas.getModel();
        tr = new TableRowSorter(modelo);
        JtableRutas.setRowSorter(tr);
        if (lista.getSelectedIndex() != 0) {
            tr.setRowFilter(RowFilter.regexFilter("^" + lista.getSelectedItem().toString() + "$", columna));
        }
    }

    // Metodo para limpiar la tabla
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableRutas.getModel();
        // Por medio de un for, tomando en cuenta el numero de filas
        for (int i = (JtableRutas.getRowCount() - 1); i >= 0; i--) {
            modelo.removeRow(i);
        }
    }

    public void cargarTabla() {
        // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JtableRutas.getModel();
        try {
            // Leer los datos
            datosRutas = query.buscaRutas();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosRutas : datosRutas) {
                modelo.addRow(new Object[]{datosRutas[0], datosRutas[1], datosRutas[2], datosRutas[3],datosRutas[4], datosRutas[5], datosRutas[6], datosRutas[7]});
            }

        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaRutas = new javax.swing.JScrollPane();
        JtableRutas = new javax.swing.JTable();
        JbtnActualizar = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JlblOrigen = new javax.swing.JLabel();
        JcmbxOrigenes = new javax.swing.JComboBox<>();
        JlblDestino = new javax.swing.JLabel();
        JcmbxDestinos = new javax.swing.JComboBox<>();
        JlblDistancia = new javax.swing.JLabel();
        JcmbxDistancias = new javax.swing.JComboBox<>();
        JlblDuracion = new javax.swing.JLabel();
        JcmbxDuraciones = new javax.swing.JComboBox<>();
        JlblPrecio = new javax.swing.JLabel();
        JcmbxPrecios = new javax.swing.JComboBox<>();
        JlblNombreTerminal = new javax.swing.JLabel();
        JtxtNombreTerminal = new javax.swing.JTextField();
        JspNombreTerminal = new javax.swing.JSeparator();
        JbtnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rutas");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Origen", "Destino", "Distancia", "Salida", "Llegada", "Duracion", "Precio"
            }
        ));
        JSPTablaRutas.setViewportView(JtableRutas);

        JpnlLienzo.add(JSPTablaRutas, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 600, 182));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, -1, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Buscar");
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 82, -1));

        JlblOrigen.setText("Origen");
        JpnlLienzo.add(JlblOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        JcmbxOrigenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxOrigenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxOrigenesActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxOrigenes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        JlblDestino.setText("Destino");
        JpnlLienzo.add(JlblDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        JcmbxDestinos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDestinos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDestinosActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDestinos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        JlblDistancia.setText("Distancia");
        JpnlLienzo.add(JlblDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, -1, -1));

        JcmbxDistancias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDistancias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDistanciasActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDistancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, -1, -1));

        JlblDuracion.setText("Duracion");
        JpnlLienzo.add(JlblDuracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, -1, -1));

        JcmbxDuraciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxDuraciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDuracionesActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxDuraciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, -1));

        JlblPrecio.setText("Precio");
        JpnlLienzo.add(JlblPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        JcmbxPrecios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        JcmbxPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxPreciosActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JcmbxPrecios, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 220, -1, -1));

        JlblNombreTerminal.setText("Nombre de la terminal");
        JpnlLienzo.add(JlblNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, -1));

        JtxtNombreTerminal.setBorder(null);
        JpnlLienzo.add(JtxtNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 150, -1));
        JpnlLienzo.add(JspNombreTerminal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 293, 150, 10));

        JbtnBuscar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnBuscar.setText("Eliminar");
        JpnlLienzo.add(JbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 82, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxOrigenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxOrigenesActionPerformed
        // TODO add your handling code here:
        filtrar(JcmbxOrigenes, 1);
    }//GEN-LAST:event_JcmbxOrigenesActionPerformed

    private void JcmbxDestinosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDestinosActionPerformed
        // TODO add your handling code here:
        filtrar(JcmbxDestinos, 2);
    }//GEN-LAST:event_JcmbxDestinosActionPerformed

    private void JcmbxDistanciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDistanciasActionPerformed
        // TODO add your handling code here:
        filtrar(JcmbxDistancias, 3);
    }//GEN-LAST:event_JcmbxDistanciasActionPerformed

    private void JcmbxDuracionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDuracionesActionPerformed
        // TODO add your handling code here:
        filtrar(JcmbxDuraciones, 4);
    }//GEN-LAST:event_JcmbxDuracionesActionPerformed

    private void JcmbxPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxPreciosActionPerformed
        // TODO add your handling code here:
        filtrar(JcmbxPrecios, 5);
    }//GEN-LAST:event_JcmbxPreciosActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfRutasConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfRutasConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaRutas;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnBuscar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JComboBox<String> JcmbxDestinos;
    private javax.swing.JComboBox<String> JcmbxDistancias;
    private javax.swing.JComboBox<String> JcmbxDuraciones;
    private javax.swing.JComboBox<String> JcmbxOrigenes;
    private javax.swing.JComboBox<String> JcmbxPrecios;
    private javax.swing.JLabel JlblDestino;
    private javax.swing.JLabel JlblDistancia;
    private javax.swing.JLabel JlblDuracion;
    private javax.swing.JLabel JlblNombreTerminal;
    private javax.swing.JLabel JlblOrigen;
    private javax.swing.JLabel JlblPrecio;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspNombreTerminal;
    private javax.swing.JTable JtableRutas;
    private javax.swing.JTextField JtxtNombreTerminal;
    // End of variables declaration//GEN-END:variables
}
