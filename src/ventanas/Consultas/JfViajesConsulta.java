/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas.Consultas;

import crud.CConsultas;
import crud.CMensajes;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author gelog
 */
public class JfViajesConsulta extends javax.swing.JFrame {

    /**
     * Creates new form JfViajesConsulta
     */
    public JfViajesConsulta() {
        initComponents();
           cargaComboBox(JcmbxMarca, 1);
        cargaComboBox(JcmbxModelo, 2);
        cargaComboBox(JcmbxPlaca, 3);
        cargaComboBox(JcmbxMeses, 4);
        cargaComboBox(JcmbxOrigenes, 5);
        cargaComboBox(JcmbxDestinos, 6);
        cargarTabla();
          // Linea para impedir que sea posible mover los encabezados de cada tabla
     JTableViajes.getTableHeader().setReorderingAllowed(false);
    }

    
      //**************   ATRIBUTOS  *******************/
     // Variable para manipular el modelo de la tabla
    private DefaultTableModel modelo;
    // Variable para poder manipular el modelo de las listas
    private DefaultComboBoxModel listas;
    // Variable para poder agregar filtros
    private TableRowSorter tr;
    // Instancia de la clase que permite hacer las consultas "Transacciones"
    private final CConsultas query = new CConsultas();
    // Creacion de lista, para la obtencion de valores de la tabla
    private ArrayList<String[]> datosViajeConsulta = new ArrayList<>();
    // Creacion de lista, para la obtencion de valores de las listas
    private ArrayList<String> datosListas = new ArrayList<>();
    

     //**************** METODOS ******************/
       // Metodo que permite cargar las opciones en las listas
    // Recibe por parametro el JComboBox al que se agregaran items
    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        //  Obtenemos el modelo del JComboBox
//        listas = new DefaultComboBoxModel();

        listas = (DefaultComboBoxModel) combo.getModel();
        // combo.setModel(listas);
        try {
            switch (metodoCarga) {
                case 1:
                    // Obtenemos los valores de la tabla
                    datosListas = query.cargaComboMarca();
                    // listas.addElement("Seleccione una opcion");
                    // Asiganamos los valores obtenidos al JComboBox
                    for (int i = 1; i < datosListas.size(); i++) {
                        // Añadimos items por string dentro de la lista
                        listas.addElement(datosListas.get(i));
                    }
                    // Limpiamos la lista para cargar los datos del siguiente JComboBox
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
                    datosListas = query.cargaComboMeses();
                    for (int i = 1; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
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
             

            }

        } catch (Exception e) {
        }

    }
    
         public void cargarTabla() {
             // Obtenemos el modelo para poder manipularlo
        modelo = (DefaultTableModel) JTableViajes.getModel();
        try {
            // Leer los datos
            datosViajeConsulta = query.buscaViaje();
            // Limpiamos la tabla
            limpiarTabla();
            // Asignamos los valores obtenidos en la tabla
            for (String[] datosVia : datosViajeConsulta) {
                modelo.addRow(new Object[]{datosVia[0], datosVia[1], datosVia[2], datosVia[3], datosVia[4], datosVia[5], datosVia[6]});
            }

        } catch (Exception e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }
      
           // Metodo para limpiar la tabla
    private void limpiarTabla() {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JTableViajes.getModel();
        // Por medio de un for, tomando en cuenta el numero de filas
        for (int i = (JTableViajes.getRowCount() - 1); i >= 0; i--) {
            // Eliminaremos las filas hasta que el valor del iterador sea mayor o igual a 0
            modelo.removeRow(i);
        }
    }
    
    
    
        
    // Metodo que permite filtrar los valores dentro de la tabla
    /* Recibe por parametro el JComboBox de donde tomaremos los valores para
       filtrar, asi como el numero de la columna donde buscaremos las coincidencias*/
    public void filtrar(JComboBox lista, int columna) {
        // Obtenemos el modelo de la tabla para poder manipularlo
        modelo = (DefaultTableModel) JTableViajes.getModel();
        // Nuestro Filtro recibe el modelo de la tabla a filtrar
        tr = new TableRowSorter(modelo);
        // Le indicamos a la tabla el filtro se usara 
        JTableViajes.setRowSorter(tr);
        // Si la opcion seleccionada no es 'Seleccione una opcion'
        if (lista.getSelectedIndex() != 0) {
            // Aplicamos el filtro para hacerlo coincidir con el item seleccionadao en la columna indicada
            // tr.setRowFilter(RowFilter.regexFilter(lista.getSelectedItem().toString(), columna));
            tr.setRowFilter(RowFilter.regexFilter("^" + lista.getSelectedItem().toString() + "$", columna));

            // En caso de serlo, no queremos que aplique el filtro proporcionado
        }
    }
    
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableViajes = new javax.swing.JTable();
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

        JTableViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Origen", "Destino", "Placa", "Modelo", "Marca", "Dia", "Mes"
            }
        ));
        jScrollPane1.setViewportView(JTableViajes);

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

        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        JcmbxDias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDiasItemStateChanged(evt);
            }
        });
        JcmbxDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcmbxDiasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        // TODO add your handling code here:
        filtrar(JcmbxPlaca, 2);
       
    }//GEN-LAST:event_JcmbxPlacaItemStateChanged

    private void JcmbxDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDiasItemStateChanged
        filtrar(JcmbxDias, 5);
    }//GEN-LAST:event_JcmbxDiasItemStateChanged

    private void JcmbxDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcmbxDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcmbxDiasActionPerformed

    private void JcmbxOrigenesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxOrigenesItemStateChanged
        // TODO add your handling code here:
           filtrar(JcmbxOrigenes, 0);
    }//GEN-LAST:event_JcmbxOrigenesItemStateChanged

    private void JcmbxDestinosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDestinosItemStateChanged
        // TODO add your handling code here:
        filtrar(JcmbxDestinos, 1);
        
    }//GEN-LAST:event_JcmbxDestinosItemStateChanged

    private void JcmbxModeloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxModeloItemStateChanged
        // TODO add your handling code here:
         filtrar(JcmbxModelo, 3);
    }//GEN-LAST:event_JcmbxModeloItemStateChanged

    private void JcmbxMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMarcaItemStateChanged
        // TODO add your handling code here:
        filtrar(JcmbxMarca, 4);
    }//GEN-LAST:event_JcmbxMarcaItemStateChanged

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        // TODO add your handling code here:
           filtrar(JcmbxMeses, 6);
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

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
    private javax.swing.JTable JTableViajes;
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
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
