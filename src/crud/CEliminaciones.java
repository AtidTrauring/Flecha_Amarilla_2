package crud;

import java.sql.SQLException;

public class CEliminaciones {

    private CConsultas cnslt = new CConsultas();
    private String consulta;

    public boolean eliminaTelefono(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.telefono_persona WHERE telefono_persona.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaConductor(int id) throws SQLException {
        consulta = "DELETE FROM flecha_amarilla.conductor WHERE conductor.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaAutbousConductor(int idConductor) throws SQLException {
        consulta = "DELETE FROM `autobusconductor` WHERE autobusconductor.Id_conductor = " + idConductor;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaRutaConductor(int idConductor) throws SQLException {
        consulta = "DELETE FROM `rutaconductor` WHERE rutaconductor.Id_conductor = " + idConductor;
        return cnslt.elimina(consulta);
    }

    public boolean eliminaPersona(int id) throws SQLException {
        consulta = "DELETE FROM persona WHERE persona.Id_persona = " + id;
        return cnslt.elimina(consulta);
    }
}
