package crud;

import java.sql.SQLException;

public class CActualizaciones {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean actualizarPersona(String nombre, String ApPat, String ApMat, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.persona SET `nombre`='" + nombre + "',`ApPat`='" + ApPat + "',`ApMat`='" + ApMat + "' WHERE persona.Id_persona = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarTelefono(String tel, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.telefono_persona SET `telefono`='" + tel + "' WHERE telefono_persona.Id_persona = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarRuta(String ruta, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.ruta SET nombre = '" + ruta + "' WHERE ruta.Id_ruta = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarRuta2(int id, String nombreRuta, String duracion, String salida, String llegada, float precio, String distancia) throws SQLException {
        consulta = "UPDATE flecha_amarilla.ruta SET `nombre`= '" + nombreRuta + "' ,`duracion_ruta`= '" + duracion + "' ,`hora_salida`= '"
                + salida + "' ,`hora_llegada`= '" + llegada + "' ,`precio`= '" + precio + "' ,`distancia`= '"
                + distancia + "' WHERE `Id_ruta`=" + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarCodigoPostal(int id, int codigoPostal) throws SQLException {
        consulta = "UPDATE flecha_amarilla.codigo_postal SET `codigo_postal`= " + codigoPostal + " WHERE `Id_CP`= " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarColonia(int id, String colonia) throws SQLException {
        consulta = "UPDATE flecha_amarilla.colonia SET `colonia`='" + colonia + "' WHERE `Id_colonia`=" + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizaEstado(int id, String estado) throws SQLException {
        consulta = "UPDATE flecha_amarilla.estado SET `nombre`='" + estado + "' WHERE `Id_estado`=" + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizaCiudad(int id, String ciudad) throws SQLException {
        consulta = "UPDATE flecha_amarilla.ciudad SET `nombre`='" + ciudad + "' WHERE `Id_ciudad`=" + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarDireccion(int id, String nombreCalle, int numero) throws SQLException {
        consulta = "UPDATE flecha_amarilla.direccion SET `nombre_calle`='" + nombreCalle + "',`numero`=" + numero + " WHERE `Id_direccion`=" + id;;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarTerminal(String terminal, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.terminal SET nombre='" + terminal + "' WHERE terminal.Id_terminal = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarCorreo(String correo, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.cliente SET `correo`='" + correo + "' WHERE cliente.Id_persona = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarMarca(String marca, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.marca SET `marca`='" + marca + "' WHERE Id_marca = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarModelo(String modelo, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.modelo SET `modelo`='" + modelo + "' WHERE Id_modelo = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarCapacidad(int capacidad, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.autobus SET `capacidad`='" + capacidad + "' WHERE Id_autobus = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarMes(String idmes, String mes) throws SQLException {
        consulta = "UPDATE `mes` SET `mes`='" + mes + "' WHERE Id_mes=" + idmes + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarAnioA(String idAnio, String anio) throws SQLException {
        consulta = "UPDATE `anio` SET `anio`='" + anio + "' WHERE Id_anio= " + idAnio;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarAnio(int anio, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.anio SET `anio`='" + anio + "' WHERE Id_anio = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarFecha(int dia, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.fecha SET `dia`='" + dia + "' WHERE Id_fecha = " + id;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarAutobus(String id, String placa) throws SQLException {
        consulta = "UPDATE `autobus` SET `placa`='" + placa + "' WHERE Id_autobus= " + id + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarAutobusCapacidad(String id, String capacidad) throws SQLException {
        consulta = "UPDATE `autobus` SET `capacidad`='" + capacidad + "' WHERE Id_autobus= " + id + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizaModelo(String id, String modelo) throws SQLException {
        consulta = "UPDATE `modelo` SET `nombre`='" + modelo + "' WHERE Id_modelo = " + id + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarMarca(String idmarca, String marca) throws SQLException {
        consulta = "UPDATE `marca` SET `nombre`='" + marca + "' WHERE Id_marca=" + idmarca + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarFecha(String idFecha, String dia) throws SQLException {
        consulta = "UPDATE `fecha` SET `dia`='" + dia + "' WHERE Id_fecha= " + idFecha + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizaTerminalOrigen(String idOrigenTerminal, String origen) throws SQLException {
        consulta = "UPDATE `terminal` SET `nombre`='" + origen + "' WHERE Id_terminal = " + idOrigenTerminal + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizaTerminalDestino(String idTerminalDestino, String destino) throws SQLException {
        consulta = "UPDATE `terminal` SET `nombre`='" + destino + "' WHERE Id_terminal = " + idTerminalDestino + ";";
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarPrecioBoleto(String idBoleto, String precio) throws SQLException {
        consulta = "UPDATE boleto SET precio = " + precio + " WHERE Id_boleto = " + idBoleto;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarTipoBoleto(String idBoleto, String tipoBoleto) throws SQLException {
        consulta = "UPDATE boleto SET tipo_boleto = '" + tipoBoleto + "' WHERE Id_boleto = " + idBoleto;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarDiaFecha(String idFecha, String dia) throws SQLException {
        consulta = "UPDATE fecha SET dia = " + dia + " WHERE Id_fecha = " + idFecha;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarMesFecha(String idMes, String mes) throws SQLException {
        consulta = "UPDATE fecha SET mes = '" + mes + "' WHERE Id_mes = " + idMes;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarAnioFecha(String idAnio, String anio) throws SQLException {
        consulta = "UPDATE fecha SET anio = " + anio + " WHERE Id_anio = " + idAnio;
        return cnslt.actualiza(consulta);
    }

    public boolean actualizarTerminal(String idTerminal, String terminal, String tipo) throws SQLException {
        String campo = tipo.equals("origen") ? "origen" : "destino";
        consulta = "UPDATE " + campo + " SET nombre = '" + terminal + "' WHERE Id_terminal = " + idTerminal;
        return cnslt.actualiza(consulta);
    }

}
