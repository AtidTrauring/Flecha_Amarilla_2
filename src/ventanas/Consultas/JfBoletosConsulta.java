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

public final class JfBoletosConsulta extends javax.swing.JFrame {

    //**************   ATRIBUTOS  *******************/
    private DefaultTableModel modelo;
    private DefaultComboBoxModel listas;
    private TableRowSorter tr;
    private final CInserciones queryInserta = new CInserciones();
    private final CBusquedas queryBusca = new CBusquedas();
    private final CEliminaciones queryElimina = new CEliminaciones();
    private final CActualizaciones queryActualiza = new CActualizaciones();
    private final CCargaCombos queryCarga = new CCargaCombos();
    private ArrayList<String[]> datosBoletos = new ArrayList<>();
    private ArrayList<String> datosListas = new ArrayList<>();
    private int idActualizar;
    private String[] valoresFila;
    private int idEliminar;
    
    public JfBoletosConsulta() {
        initComponents();
        JtableBoletos.getTableHeader().setReorderingAllowed(false);
        cargaComboBox(JcmbxOrigenes, 1);
        cargaComboBox(JcmbxDestinos, 2);
        cargaComboBox(JcmbxMeses, 4);
        cargaComboBox(JcmbxAnios, 5);
        cargaComboBox(JcmbxPrecios, 6);
        cargarTabla();
    }

    //**************** METODOS ******************/
    private void limpiarTabla() {
        modelo = (DefaultTableModel) JtableBoletos.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarBuscadores() {
        JcmbxOrigenes.setSelectedIndex(0);
        JcmbxDestinos.setSelectedIndex(0);
        JcmbxMeses.setSelectedIndex(0);
        JcmbxDias.setSelectedIndex(0);
        JcmbxAnios.setSelectedIndex(0);
        JcmbxPrecios.setSelectedIndex(0);
        JcmbxTiposBoletos.setSelectedIndex(0);
    }

    public void limpiarFiltro() {
        if (tr != null) {
            tr.setRowFilter(null);
        }
    }

    public void cargarTabla() {
        modelo = (DefaultTableModel) JtableBoletos.getModel();
        try {
            datosBoletos = queryBusca.buscaBoletosCompletos();
            limpiarTabla();
            for (String[] datosBoleto : datosBoletos) {
                switch (datosBoleto[7]) {
                    case "PP":
                        modelo.addRow(new Object[]{datosBoleto[1], datosBoleto[2], datosBoleto[3], datosBoleto[4], datosBoleto[5], datosBoleto[6],
                            "Primera Plus", datosBoleto[8]});
                        break;
                    case "C":
                        modelo.addRow(new Object[]{datosBoleto[1], datosBoleto[2], datosBoleto[3], datosBoleto[4], datosBoleto[5], datosBoleto[6],
                            "Comercial", datosBoleto[8]});
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
                    datosListas = queryCarga.cargaComboOrigenes();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 2:
                    datosListas = queryCarga.cargaComboDestinos();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
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

                case 4:
                    ArrayList<String[]> Meses = queryCarga.cargaComboMeses();
                    for (String[] Mes : Meses) {
                        listas.addElement(Mes[1]);
                    }
                    Meses.clear();
                    break;
                case 5:
                    ArrayList<String[]> Anios = queryCarga.cargaComboAnios();
                    for (String[] Anio : Anios) {
                        listas.addElement(Anio[1]);
                    }
                    Anios.clear();
                    break;
                case 6:
                    datosListas = queryCarga.cargaComboPrecio();
                    for (int i = 0; i < datosListas.size(); i++) {
                        listas.addElement(datosListas.get(i));
                    }
                    datosListas.clear();
                    break;
                case 7:

            }

        } catch (SQLException e) {
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
        modelo = (DefaultTableModel) JtableBoletos.getModel();
        tr = new TableRowSorter<>(modelo);
        JtableBoletos.setRowSorter(tr);
        ArrayList<RowFilter<Object, Object>> filtros = new ArrayList<>();
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
        RowFilter<Object, Object> rf = RowFilter.andFilter(filtros);
        tr.setRowFilter(rf);
    }

    private String[] obtenerValoresFilaTabla() {
    String[] valores = new String[8]; 
    int filaSeleccionada = JtableBoletos.getSelectedRow();
    if (filaSeleccionada != -1) {
        for (int i = 0; i < JtableBoletos.getColumnCount(); i++) {
            valores[i] = (String) JtableBoletos.getValueAt(filaSeleccionada, i);
        }
    } else {
        CMensajes.msg_error("No hay fila seleccionada", "Obteniendo datos fila");
        return null;
    }
    return valores;
}

    public int buscarId(String asiento, String origen, String destino, String dia, String mes, String anio, String tipo, String precio) {
    for (String[] boletos : datosBoletos) {
        if (boletos[1].equals(asiento) && boletos[2].equals(origen) && boletos[3].equals(destino) && boletos[4].equals(dia) && boletos[5].equals(mes)
                && boletos[6].equals(anio) && boletos[7].equals(tipo) && boletos[8].equals(precio)) {
            return Integer.parseInt(boletos[0]);
        }
    }
    return -1;
}

    public void eliminar(int id) {
        try {
            String idBoleto = queryBusca.buscarBoletos(id);
            if (idBoleto != null || idBoleto.isEmpty()) {
                if (queryElimina.eliminaBoleto(id)) {
                    CMensajes.msg("Se elimino el telefono correspondiente", "Eliminar");
                    if (queryElimina.eliminaClienteBoleto(Integer.parseInt(idBoleto))) {
                        CMensajes.msg("Se eliminaron las relaciones del cliente \ncon los boletos correspondientes", "Eliminar");
                    } else {
                        CMensajes.msg_error("Ocurrio un error al eliminar \nlas compras asociadas al cliente", "Eliminar");
                    }
                } else {
                    CMensajes.msg_error("Ocurrio un error al eliminar el Boleto", "Eliminar");
                }
            } else {
                CMensajes.msg_error("Usuario no encontrado", "Eliminar-Buscar");
            }
        } catch (SQLException e) {
        } finally {
//            datosConductores.clear();
            limpiarBuscadores();
            limpiarFiltro();
            cargarTabla();
        }
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
        JbtnActualizar = new javax.swing.JButton();

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
        JtableBoletos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JtableBoletosMouseClicked(evt);
            }
        });
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

        JcmbxDias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un mes" }));
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
        JbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JbtnEliminarActionPerformed(evt);
            }
        });

        JbtnActualizar.setBackground(new java.awt.Color(160, 16, 70));
        JbtnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        JbtnActualizar.setText("Actualizar");

        javax.swing.GroupLayout JpnlLienzoLayout = new javax.swing.GroupLayout(JpnlLienzo);
        JpnlLienzo.setLayout(JpnlLienzoLayout);
        JpnlLienzoLayout.setHorizontalGroup(
            JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpnlLienzoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpnlLienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(JbtnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(JbtnActualizar))
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addComponent(JSPTablaBoletos)
                        .addGap(21, 21, 21))))
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
                            .addComponent(JbtnEliminar)
                            .addComponent(JbtnActualizar)))
                    .addGroup(JpnlLienzoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(JlblTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JcmbxTiposBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(JSPTablaBoletos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpnlLienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        cargaComboDias(2);
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxMesesItemStateChanged

    private void JcmbxAniosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxAniosItemStateChanged
        cargaComboDias(1);
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxAniosItemStateChanged

    private void JcmbxTiposBoletosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxTiposBoletosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxTiposBoletosItemStateChanged

    private void JcmbxPreciosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcmbxPreciosItemStateChanged
        aplicaFiltros();
    }//GEN-LAST:event_JcmbxPreciosItemStateChanged

    private void JbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JbtnEliminarActionPerformed
        // TODO add your handling code here:
         if (JtableBoletos.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro seleccionado?", "Confimacion", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                valoresFila = obtenerValoresFilaTabla();
                if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3],valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]) != -1) {
                    idEliminar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3],valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]);
                    eliminar(idEliminar);
                }
            } else {
                CMensajes.msg("Accion cancelada", "Eliminacion");
            }
        } else {
            CMensajes.msg_error("Seleccione un registro", "Eliminar");

        }
    }//GEN-LAST:event_JbtnEliminarActionPerformed

    private void JtableBoletosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JtableBoletosMouseClicked
        // TODO add your handling code here:
         valoresFila = obtenerValoresFilaTabla();
        if (valoresFila != null) {
            if (buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3],valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]) != -1) {
                // Se asigna el ID encontrado a la variable idActualizar.
                idActualizar = buscarId(valoresFila[0], valoresFila[1], valoresFila[2], valoresFila[3],valoresFila[4], valoresFila[5], valoresFila[6], valoresFila[7]);
            }
        }
    }//GEN-LAST:event_JtableBoletosMouseClicked

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
    private javax.swing.JButton JbtnActualizar;
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
