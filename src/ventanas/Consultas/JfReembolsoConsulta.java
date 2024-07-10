package ventanas.Consultas;

import crud.CActualizaciones;
import crud.CBusquedas;
import crud.CCargaCombos;
import crud.CEliminaciones;
import crud.CInserciones;
import crud.CMensajes;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public final class JfReembolsoConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String[]> datosReembolso = new ArrayList<>();
    private int idActualizar;
    private String[] valoresFila;
    private int idEliminar;
    
    public JfReembolsoConsulta() {
        initComponents();
        JtableReembolsos.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxMeses, 1);
        cargaComboBox(JcmbxAnios, 2);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        // Limpia los cuadro de texto
        JcmbxDias.setSelectedIndex(0);
        JcmbxMeses.setSelectedIndex(0);
        JcmbxAnios.setSelectedIndex(0);
        JtxtNombres.setText(null);
        JtxtApPaterno.setText(null);
        JtxtApMaterno.setText(null);
    }

    public void limpiarFiltro() {
        if (tr != null) {
            tr.setRowFilter(null);
        }
    }

    public void cargaComboBox(JComboBox combo, int metodoCarga) {
        listas = (DefaultComboBoxModel) combo.getModel();
        try {
            switch (metodoCarga) {
                case 1:
                    ArrayList<String[]> Meses = queryCarga.cargaComboMeses();
                    for (String[] Mes : Meses) {
                        listas.addElement(Mes[1]);
                    }
                    Meses.clear();
                    break;
                case 2:
                    ArrayList<String[]> Anios = queryCarga.cargaComboAnios();
                    for (String[] Anio : Anios) {
                        listas.addElement(Anio[1]);
                    }
                    Anios.clear();
                    break;
                case 3:
                    String[] dias = asignaDias(JcmbxMeses, JcmbxAnios);
                    if (dias != null) {
                        for (String dia : dias) {
                            listas.addElement(dia);
                        }
                    }
                    dias = null;
                    break;
            }

        } catch (SQLException e) {
        }

    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        try {
            datosReembolso = queryBusca.buscarReembolsoCompleto();
            limpiarTabla();
            for (String[] datosRee : datosReembolso) {
                modelo.addRow(new Object[]{datosRee[1], datosRee[2], datosRee[3], datosRee[4], datosRee[5], datosRee[6], datosRee[7]});
            }
        } catch (SQLException e) {
            CMensajes.msg_error("No se pudo cargar la informacion en la tabla", "Cargando Tabla");
        }
    }

    public String[] asignaDias(JComboBox mes, JComboBox anio) {
        String[] Dias = null;
        int anios = 0;
        if (anio.getSelectedIndex() != 0) {
            anios = Integer.parseInt(anio.getSelectedItem().toString());
        }
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
                if (anios != 0) {
                    if ((anios % 4 == 0 && anios % 100 != 0) || (anios % 400 == 0)) {
                        Dias = new String[29];
                        for (int i = 0; i < 29; i++) {
                            Dias[i] = String.valueOf(i + 1);
                        }
                    } else {
                        Dias = new String[28];
                        for (int i = 0; i < 28; i++) {
                            Dias[i] = String.valueOf(i + 1);
                        }
                    }
                } else {
                    Dias = new String[28];
                    for (int i = 0; i < 28; i++) {
                        Dias[i] = String.valueOf(i + 1);
                    }
                }
                break;
        }
        return Dias;
    }

    public void cargaComboDias(int opcion) {
        switch (opcion) {
            case 1:
                if (JcmbxAnios.getSelectedIndex() != 0 && JcmbxMeses.getSelectedIndex() != 0) {
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
                break;
            case 2:
                if (JcmbxAnios.getSelectedIndex() != 0 || JcmbxMeses.getSelectedIndex() != 0) {
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
                break;
        }
    }

    public void aplicaFiltros() {
        modelo = (DefaultTableModel) JtableReembolsos.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableReembolsos.setRowSorter(tr);
        ArrayList<RowFilter<String, Integer>> filtros = new ArrayList<>();
        if (!JtxtNombres.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtNombres.getText().trim(), 0));
        }
        if (!JtxtApPaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtApPaterno.getText().trim(), 1));
        }
        if (!JtxtApMaterno.getText().trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter(JtxtApMaterno.getText().trim(), 2));
        }
        if (JcmbxDias.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxDias.getSelectedItem().toString(), 4));
        }
        if (JcmbxMeses.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxMeses.getSelectedItem().toString(), 5));
        }
        if (JcmbxAnios.getSelectedIndex() != 0) {
            filtros.add(RowFilter.regexFilter(JcmbxAnios.getSelectedItem().toString(), 6));
        }
        RowFilter<String, Integer> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
        String[] valores = new String[7];
        int filaSeleccionada = JtableReembolsos.getSelectedRow();
        if (filaSeleccionada != -1) {
            for (int i = 0; i < JtableReembolsos.getColumnCount(); i++) {
                valores[i] = (String) JtableReembolsos.getValueAt(filaSeleccionada, i);
            }
        } else {
            CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
            return null;
        }
        return valores;
    }

    public int buscarId(String nombre, String apPat, String apMat, String cantidad, String dia, String mes, String anio) {
        for (String[] ree : datosReembolso) {
            if (ree[1].equals(nombre) && ree[2].equals(apPat) && ree[3].equals(apMat) && ree[4].equals(cantidad) && ree[5].equals(dia) && ree[6].equals(mes) && ree[7].equals(anio)) {
                return Integer.parseInt(ree[0]);
            }
        }
        return -1;
    }
    
     public void eliminar(int id) {
        try {
            String idRee = queryBusca.buscarReembolso(id);
            if (idRee != null || idRee.isEmpty()) {
                if (queryElimina.eliminarReembolso(id)) {
                    CMensajes.msg("Se elimino el reembolso", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Reembolso no encontrado ", "Eliminar-Buscar");
            }
        } catch (SQLException e) {
        } finally {
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JpnlLienzo = new javax.swing.JPanel();
        JSPTablaReembolsos = new javax.swing.JScrollPane();
        JtableReembolsos = new javax.swing.JTable();
        JpnlDatosCliente = new javax.swing.JPanel();
        JlblApPaterno = new javax.swing.JLabel();
        JtxtApPaterno = new javax.swing.JTextField();
        JspApPaterno = new javax.swing.JSeparator();
        JlblApMaterno = new javax.swing.JLabel();
        JtxtApMaterno = new javax.swing.JTextField();
        JspApMaterno = new javax.swing.JSeparator();
        JspNombres = new javax.swing.JSeparator();
        JtxtNombres = new javax.swing.JTextField();
        JlblNombres = new javax.swing.JLabel();
        JlblDia = new javax.swing.JLabel();
        JcmbxDias = new javax.swing.JComboBox<>();
        JlblMes = new javax.swing.JLabel();
        JcmbxMeses = new javax.swing.JComboBox<>();
        JlblAnio = new javax.swing.JLabel();
        JcmbxAnios = new javax.swing.JComboBox<>();
        JbtnTotal = new javax.swing.JButton();
        JbtnEliminar = new javax.swing.JButton();
        JbtnActualizar = new javax.swing.JButton();
        JlblTotal = new javax.swing.JLabel();
        JlblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reembolsos");
        setResizable(false);

        JpnlLienzo.setBackground(new java.awt.Color(255, 255, 255));
        JpnlLienzo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtableReembolsos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre(s)", "Apellido Paterno", "Apellido Materno", "Cantidad", "Dia", "Mes", "Año"
            }
        ));
        JtableReembolsos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableReembolsosMouseClicked(evt);
            }
        });
        JSPTablaReembolsos.setViewportView(JtableReembolsos);

        JpnlLienzo.add(JSPTablaReembolsos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 630, 147));

        JpnlDatosCliente.setBackground(new java.awt.Color(255, 255, 255));
        JpnlDatosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Datos del Cliente"));

        JlblApPaterno.setText("Apellido Paterno");

        JtxtApPaterno.setBorder(null);
        JtxtApPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApPaternoKeyReleased(evt);
            }
        });

        JlblApMaterno.setText("Apellido Materno");

        JtxtApMaterno.setBorder(null);
        JtxtApMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtApMaternoKeyReleased(evt);
            }
        });

        JtxtNombres.setBorder(null);
        JtxtNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JtxtNombresKeyReleased(evt);
            }
        });

        JlblNombres.setText("Nombre(s)");

        javax.swing.GroupLayout JpnlDatosClienteLayout = new javax.swing.GroupLayout(JpnlDatosCliente);
        JpnlDatosCliente.setLayout(JpnlDatosClienteLayout);
        JpnlDatosClienteLayout.setHorizontalGroup(
            JpnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlDatosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JlblNombres)
                    .addComponent(JtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblApPaterno)
                    .addComponent(JtxtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JlblApMaterno)
                    .addComponent(JtxtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JspApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpnlDatosClienteLayout.setVerticalGroup(
            JpnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpnlDatosClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JlblNombres)
                .addGap(4, 4, 4)
                .addComponent(JtxtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(JspNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JlblApPaterno)
                .addGap(4, 4, 4)
                .addComponent(JtxtApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(JspApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(JlblApMaterno)
                .addGap(4, 4, 4)
                .addComponent(JtxtApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(JspApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JpnlLienzo.add(JpnlDatosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 160, 190));

        JlblDia.setText("Dia");
        JpnlLienzo.add(JlblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un mes" }));
        JcmbxDias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxDiasItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxDias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        JlblMes.setText("Mes");
        JpnlLienzo.add(JlblMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        JcmbxMeses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxMesesItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        JlblAnio.setText("Año");
        JpnlLienzo.add(JlblAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        JcmbxAnios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion" }));
        JcmbxAnios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcmbxAniosItemStateChanged(evt);
            }
        });
        JpnlLienzo.add(JcmbxAnios, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        JbtnTotal.setBackground(new java.awt.Color(160, 16, 70));
        JbtnTotal.setForeground(new java.awt.Color(255, 255, 255));
        JbtnTotal.setText("Total");
        JbtnTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JbtnTotalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JbtnTotalMouseExited(evt);
            }
        });
        JbtnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnTotalActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 90, -1));

        JbtnEliminar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnEliminar.setText("Eliminar");
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });
        JpnlLienzo.add(JbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 90, -1));

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");
        JpnlLienzo.add(JbtnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 90, -1));

        JlblTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        JpnlLienzo.add(JlblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 130, 40));

        JlblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FondoReembolso.png"))); // NOI18N
        JpnlLienzo.add(JlblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 210, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcmbxDiasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxDiasItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxDiasItemStateChanged

    private void JtxtNombresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtNombresKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtNombresKeyReleased

    private void JtxtApPaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApPaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApPaternoKeyReleased

    private void JtxtApMaternoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JtxtApMaternoKeyReleased
        aplicaFiltros();
    }//GEN-LAST:event_JtxtApMaternoKeyReleased

    private void JcmbxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxMesesItemStateChanged
        cargaComboDias(2);
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

    private void JcmbxAniosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxAniosItemStateChanged
        cargaComboDias(1);
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxAniosItemStateChanged

    private void JbtnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnTotalActionPerformed
        Double[] precios = new Double[JtableReembolsos.getRowCount()];
        for (int i = 0; i < JtableReembolsos.getRowCount(); i++) {
            precios[i] = Double.parseDouble(String.valueOf(JtableReembolsos.getValueAt(i, 3)));
        }

        double total = 0;
        for (int i = 0; i < precios.length; i++) {
            total = total + precios[i];
//            total += precios[i];
        }
        JlblTotal.setText("El total es de " + total);
    }//GEN-LAST:event_JbtnTotalActionPerformed

    private void JbtnTotalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbtnTotalMouseEntered
        if (JtableReembolsos.getRowCount() != 0) {
            JbtnTotal.setEnabled(true);
        } else {
            JbtnTotal.setEnabled(false);
            JlblTotal.setText("");
        }
    }//GEN-LAST:event_JbtnTotalMouseEntered

    private void JbtnTotalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbtnTotalMouseExited
        JbtnTotal.setEnabled(true);
    }//GEN-LAST:event_JbtnTotalMouseExited

    private void JtableReembolsosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableReembolsosMouseClicked
        // TODO add your handling code here:
         valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6]);
            }
        }
    }//GEN-LAST:event_JtableReembolsosMouseClicked

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
        if (JtableReembolsos.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6]) != -1) {
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3], valoresFila[4], valoresFila[5], valoresFila[6]);
                    eliminar(idEliminar);
                }
            } else {
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JfReembolsoConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JfReembolsoConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JSPTablaReembolsos;
    private javax.swing.JButton JbtnActualizar;
    private javax.swing.JButton JbtnEliminar;
    private javax.swing.JButton JbtnTotal;
    private javax.swing.JComboBox<String> JcmbxAnios;
    private javax.swing.JComboBox<String> JcmbxDias;
    private javax.swing.JComboBox<String> JcmbxMeses;
    private javax.swing.JLabel JlblAnio;
    private javax.swing.JLabel JlblApMaterno;
    private javax.swing.JLabel JlblApPaterno;
    private javax.swing.JLabel JlblDia;
    private javax.swing.JLabel JlblFondo;
    private javax.swing.JLabel JlblMes;
    private javax.swing.JLabel JlblNombres;
    private javax.swing.JLabel JlblTotal;
    private javax.swing.JPanel JpnlDatosCliente;
    private javax.swing.JPanel JpnlLienzo;
    private javax.swing.JSeparator JspApMaterno;
    private javax.swing.JSeparator JspApPaterno;
    private javax.swing.JSeparator JspNombres;
    private javax.swing.JTable JtableReembolsos;
    private javax.swing.JTextField JtxtApMaterno;
    private javax.swing.JTextField JtxtApPaterno;
    private javax.swing.JTextField JtxtNombres;
    // End of variables declaration//GEN-END:variables
}
