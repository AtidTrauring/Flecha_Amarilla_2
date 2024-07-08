package crud;

import java.sql.SQLException;
import java.util.ArrayList;

public class CCargaCombos {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    public ArrayList<String> cargaComboDias() throws SQLException {
        consulta = "SELECT DISTINCT fecha.dia FROM fecha ORDER BY fecha.dia ASC;";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboPlaca() throws SQLException {
        consulta = "SELECT autobus.placa FROM autobus;";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String[]> cargaComboMeses() throws SQLException {
        consulta = "SELECT mes.Id_mes, mes.mes FROM `mes` ORDER BY mes.Id_mes ASC";
        return cnslt.buscarCon2(consulta);
    }

    public ArrayList<String[]> cargaComboAnios() throws SQLException {
        consulta = "SELECT anio.Id_anio, anio.anio FROM anio ORDER BY anio.Id_anio ASC";
        return cnslt.buscarCon2(consulta);
    }

    public ArrayList<String> cargaComboCantidad() throws SQLException {
        consulta = "SELECT anio.anio FROM `anio`";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboMarca() throws SQLException {
        consulta = "SELECT marca.nombre from marca";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboRutas() throws SQLException {
        consulta = "SELECT ruta.nombre from ruta";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboTerminales() throws SQLException {
        consulta = "SELECT terminal.nombre from terminal";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboModelo() throws SQLException {
        consulta = "SELECT DISTINCT modelo.nombre FROM modelo;";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboCapacidad() throws SQLException {
        consulta = "SELECT DISTINCT capacidad FROM autobus ORDER BY capacidad ASC;";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboDuracion() throws SQLException {
        consulta = "SELECT DISTINCT duracion_ruta FROM ruta ORDER BY CAST(SUBSTRING_INDEX(duracion_ruta, ' ', 1) AS UNSIGNED) ASC";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboDistacia() throws SQLException {
        consulta = "SELECT DISTINCT distancia FROM ruta ORDER BY CAST(SUBSTRING_INDEX(distancia, ' ', 1) AS DECIMAL(10, 2)) ASC";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboOrigenes() throws SQLException {
        consulta = "SELECT DISTINCT terminal.nombre \n"
                + "FROM origen \n"
                + "INNER JOIN terminal ON terminal.Id_terminal = origen.Id_terminal\n"
                + "ORDER BY nombre ASC";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboDestinos() throws SQLException {
        consulta = "SELECT DISTINCT terminal.nombre \n"
                + "FROM destino \n"
                + "INNER JOIN terminal ON terminal.Id_terminal = destino.Id_terminal\n"
                + "ORDER BY nombre ASC";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboTipoBoletos() throws SQLException {
        consulta = "SELECT DISTINCT boleto.tipo_boleto FROM boleto";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboPrecio() throws SQLException {
        consulta = "SELECT DISTINCT boleto.precioDescuento FROM boleto ORDER BY boleto.precioDescuento ASC;";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboPrecioRuta() throws SQLException {
        consulta = "SELECT DISTINCT ruta.precio FROM ruta ORDER BY ruta.precio ASC";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboCiudad() throws SQLException {
        consulta = "SELECT ciudad.nombre FROM ciudad";
        return cnslt.buscarCon1(consulta);
    }

    public ArrayList<String> cargaComboEstado() throws SQLException {
        consulta = "SELECT estado.nombre FROM estado";
        return cnslt.buscarCon1(consulta);
    }
}
