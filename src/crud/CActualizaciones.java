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
        consulta = "UPDATE flecha_amarilla.ruta SET `nombre`= '" + nombreRuta + "' ,`duracion_ruta`= '" + duracion + "' ,`hora_salida`= '" +
                salida+ "' ,`hora_llegada`= '" + llegada + "' ,`precio`= '" + precio + "' ,`distancia`= '" + 
                distancia + "' WHERE `Id_ruta`=" + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizarCodigoPostal(int id, int codigoPostal) throws SQLException{
        consulta = "UPDATE flecha_amarilla.codigo_postal SET `codigo_postal`= " + codigoPostal+ " WHERE `Id_CP`= " + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizarColonia(int id, String colonia) throws SQLException{
        consulta = "UPDATE flecha_amarilla.colonia SET `colonia`='" + colonia + "' WHERE `Id_colonia`=" + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizaEstado(int id, String estado) throws SQLException{
        consulta = "UPDATE flecha_amarilla.estado SET `nombre`='" + estado + "' WHERE `Id_estado`=" + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizaCiudad(int id, String ciudad) throws SQLException{
        consulta = "UPDATE flecha_amarilla.ciudad SET `nombre`='" + ciudad  +"' WHERE `Id_ciudad`=" + id;
        return cnslt.actualiza(consulta);
    }
    
    public boolean actualizarDireccion(int id, String nombreCalle, int numero) throws SQLException{
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
    public boolean actualizarMes(String mes, int id) throws SQLException {
        consulta = "UPDATE flecha_amarilla.mes SET `mes`='" + mes + "' WHERE Id_mes = " + id;
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
}
