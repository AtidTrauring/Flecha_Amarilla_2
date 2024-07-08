package crud;

import java.sql.SQLException;

public class CInserciones {

    private CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean insertaTelefonos(int idTelefono, String telefono, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.telefono_persona (`Id_telefonoPersona`, `telefono`, `Id_persona`) VALUES (" + idTelefono + ",'" + telefono + "'," + idPersona + ")";
        return cnslt.inserta(consulta);
    }

    public boolean insertaPersona(int id, String nombres, String apPaterno, String apMaterno) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.persona (`Id_persona`, `nombre`, `ApPat`, `ApMat`) VALUES('" + id + "','" + nombres + "','" + apPaterno + "','" + apMaterno + "')";
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

    public boolean insertaConductores(int idConductor, int idPersona) throws SQLException {
        consulta = "INSERT INTO flecha_amarilla.conductor (`Id_conductor`, `Id_persona`) VALUES ('" + idConductor + "','" + idPersona + "')";
        return cnslt.inserta(consulta);
    }
}
