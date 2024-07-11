package crud;

import java.sql.SQLException;

public class CInserciones {

    private final CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean insertaPersona(int id, String nombres, String apPaterno, String apMaterno) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.persona (`Id_persona`, `nombre`, `ApPat`, `ApMat`) VALUES('" + id + "','" + nombres + "','" + apPaterno + "','" + apMaterno + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaTelefonos(int idTelefono, String telefono, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.telefono_persona (`Id_telefonoPersona`, `telefono`, `Id_persona`) VALUES (" + idTelefono + ",'" + telefono + "'," + idPersona + ")";
        return cnslt.inserta(consulta);
    }

    public boolean insertaClientes(int idCliente, String correo, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.cliente (`Id_cliente`, `correo`, `Id_persona`) VALUES ('" + idCliente + "','" + correo + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaPasajeros(int idPasajero, String tipoPasajero, int descuento, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.pasajero (`Id_pasajero`, `tipoPasajero`, `descuento`, `Id_persona`) VALUES ('" + idPasajero + "','" + tipoPasajero + "','" + descuento + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaFecha(int idFecha, int dia, int mes, int anio) throws SQLException {
        consulta = "INSERT INTO `fecha`(`Id_fecha`, `dia`, `Id_mes`, `Id_anio`) VALUES ('" + idFecha + "','" + dia + "','" + mes + "','" + anio + "');";
        return cnslt.inserta(consulta);
    }

    public boolean insertaAnio(int Id_anio, int anio) throws SQLException {
        consulta = "INSERT INTO `anio`(`Id_anio`, `anio`) VALUES ('" + Id_anio + "','" + anio + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaTarjeta(int IdTarjeta, String numCuenta, int cvv, int IdAnio, int IdMes, int IdMetodo, int IdCliente, int tipo) throws SQLException {
        consulta = "INSERT INTO `tarjeta`(`Id_tarjeta`, `numeroCuenta`, `CVV`, `Id_anio`, `Id_mes`, `Id_metodo`, `Id_cliente`, `Id_tipo_tarjeta`) VALUES"
                + " ('" + IdTarjeta
                + "','" + numCuenta
                + "','" + cvv
                + "','" + IdAnio
                + "','" + IdMes
                + "','" + IdMetodo
                + "','" + IdCliente
                + "','" + tipo
                + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaMetodo(int IdMetodo, int idFecha) throws SQLException {
        consulta = "INSERT INTO `metodo_pago`(`Id_metodo`, `Id_fecha`) VALUES ('" + IdMetodo + "','" + idFecha + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaEfectivo(int IdEfectivo, int idMetodo) throws SQLException {
        consulta = "INSERT INTO `efectivo`(`Id_efectivo`, `Id_metodo`) VALUES ('" + IdEfectivo + "','" + idMetodo + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaBoleto(int idBoleto, String tipoBoleto, float precioDescuento, int idMetodo, int idRuta, int idFecha, int idPasajero, int idAsiento) throws SQLException {
        consulta = "INSERT INTO `boleto`(`Id_boleto`, `tipo_boleto`, `precioDescuento`, `Id_metodo`, `Id_ruta`, `Id_fecha`, `Id_pasajero`, `Id_asiento`) VALUES"
                + " ('" + idBoleto
                + "','" + tipoBoleto
                + "','" + precioDescuento
                + "','" + idMetodo
                + "','" + idRuta
                + "','" + idFecha
                + "','" + idPasajero
                + "','" + idAsiento
                + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaCompra(int idCompra, float total, int idBoleto, int idCliente) throws SQLException {
        consulta = "INSERT INTO `boletocliente`(`Id_BolClie`, `total`, `Id_boleto`, `Id_cliente`) VALUES ('" + idCompra + "','" + total + "','" + idBoleto + "','" + idCliente + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaReembolso(int idReembolso, float cantidad, int idBoleto, int idFecha) throws SQLException {
        consulta = "INSERT INTO `reembolso`(`Id_reembolso`, `cantidad`, `Id_boleto`, `Id_fecha`) VALUES ('"+idReembolso+"','"+cantidad+"','"+idBoleto+"','"+idFecha+"')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaConductores(int idConductor, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.conductor (`Id_conductor`, `Id_persona`) VALUES ('" + idConductor + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaRuta(int idRuta, String nombre, String duración, String horaSalida, String horaLegada, float precio, String distancia, int idOrigen, int idDestino) throws SQLException {
        consulta = "INSERT INTO `ruta`(`Id_ruta`, `nombre`, `duracion_ruta`, `hora_salida`, `hora_llegada`, `precio`, `distancia`, `Id_origen`, `Id_destino`)"
                + " VALUES (" + idRuta + ",'" + nombre + "','" + duración + "','" + horaSalida + "','" + horaLegada + "'," + precio + ",'" + distancia + "','" + idOrigen + "','" + idDestino + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaParada(int idParada, int idRuta, int idTerminal) throws SQLException {
        consulta = "INSERT INTO `rutaterminal`(`Id_RutTer`, `Id_ruta`, `Id_terminal`) "
                + "VALUES ('" + idParada + "','" + idRuta + "','" + idTerminal + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaAutobusConductor(int Id_AutCon, int Id_conductor, int Id_autobus) throws SQLException {
        consulta = "INSERT INTO `autobusconductor`(`Id_AutCon`, `Id_conductor`, `Id_autobus`) "
                + "VALUES ('" + Id_AutCon + "','" + Id_conductor + "','" + Id_autobus + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaRutaConductor(int Id_RutCon, int Id_ruta, int Id_conductor) throws SQLException {
        consulta = "INSERT INTO `rutaconductor`(`Id_RutCon`, `Id_ruta`, `Id_conductor`)"
                + " VALUES ('" + Id_RutCon + "','" + Id_ruta + "','" + Id_conductor + "');";
        return cnslt.inserta(consulta);
    }

    public boolean insertaRutaAutobus(int Id_RutAut, int Id_ruta, int Id_autobus, int Id_fecha) throws SQLException {
        consulta = "INSERT INTO `rutaautobus`(`Id_RutAut`, `Id_ruta`, `Id_autobus`, `Id_fecha`) "
                + "VALUES ('" + Id_RutAut + "','" + Id_ruta + "','" + Id_autobus + "','" + Id_fecha + "');";
        return cnslt.inserta(consulta);
    }

    public boolean insertaMarca(int Id_marca, String nombre) throws SQLException {
        consulta = "INSERT INTO `marca`(`Id_marca`, `nombre`) VALUES ('" + Id_marca + "','" + nombre + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaMadelo(int Id_modelo, String nombre, int Id_marca) throws SQLException {
        consulta = "INSERT INTO `modelo`(`Id_modelo`, `nombre`, `Id_marca`) VALUES ('" + Id_modelo + "','" + nombre + "','" + Id_marca + "');";
        return cnslt.inserta(consulta);
    }

    public boolean insertaAutobus(int Id_autobus, int capacidad, String num_economico, String placa, int Id_anio, int Id_fecha, int Id_modelo) throws SQLException {
        consulta = "INSERT INTO `autobus`(`Id_autobus`, `capacidad`, `num_economico`, `placa`, `Id_anio`, `Id_fecha`, `Id_modelo`) VALUES"
                + " ('" + Id_autobus + "','" + capacidad + "','" + num_economico + "','" + placa + "','" + Id_anio + "','" + Id_fecha + "','" + Id_modelo + "');";
        return cnslt.inserta(consulta);
    }

    public boolean insertaCodigoPostal(int Id_CP, int codigo_postal) throws SQLException {
        consulta = "INSERT INTO `codigo_postal`(`Id_CP`, `codigo_postal`) VALUES (" + Id_CP + "," + codigo_postal + ");";
        return cnslt.inserta(consulta);
    }

    public boolean insertaColonia(int Id_colonia, String colonia) throws SQLException {
        consulta = "INSERT INTO `colonia`(`Id_colonia`, `colonia`) VALUES (" + Id_colonia + ",'" + colonia + "')";
        return cnslt.inserta(consulta);
    }

    public boolean insertaDireccion(int Id_direccion, String nombre_calle, int numero, int Id_ciudad, int Id_colonia, int Id_CP) throws SQLException {
        consulta = "INSERT INTO `direccion`(`Id_direccion`, `nombre_calle`, `numero`, `Id_ciudad`, `Id_colonia`, `Id_CP`) VALUES"
                + " (" + Id_direccion + ",'" + nombre_calle + "'," + numero + "," + Id_ciudad + "," + Id_colonia + "," + Id_CP + ");";
        return cnslt.inserta(consulta);
    }

    public boolean insertaTerminal(int Id_terminal, String nombre, int Id_direccion) throws SQLException {
        consulta = "INSERT INTO `terminal`(`Id_terminal`, `nombre`, `Id_direccion`) VALUES (" + Id_terminal + ",'" + nombre + "'," + Id_direccion + ")";
        return cnslt.inserta(consulta);
    }

    public boolean insertaCiudad(int Id_ciudad, String nombre, int Id_estado) throws SQLException {
        consulta = "INSERT INTO `ciudad`(`Id_ciudad`, `nombre`, `Id_estado`) VALUES (" + Id_ciudad + ",'" + nombre + "'," + Id_estado + ");";
        return cnslt.inserta(consulta);
    }
}
