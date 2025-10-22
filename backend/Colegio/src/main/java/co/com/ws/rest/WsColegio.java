package co.com.ws.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import co.com.bd.BaseDatos;
import co.com.manager.MateriasManager;
import co.com.model.Estudiante;
import co.com.model.Materia;
import co.com.model.MateriaInput;

@Path("WsColegio")
public class WsColegio {
	
	@GET
    @Path("isNumeroParImpar")
    @Produces(MediaType.TEXT_PLAIN)
    public String isNumeroParImpar(@QueryParam("numero") Integer numero){
        String isParImpar = "";
        
        if (numero % 2 == 0) {
        	isParImpar = "Par";
        	
        } else {
        	isParImpar = "Impar";
        }
        
        return isParImpar;
        
    }
	
	@GET
    @Path("isNumeroParImparJSon")
    @Produces(MediaType.APPLICATION_JSON)
    public String isNumeroParImparJSon(@QueryParam("numero") Integer numero){
        boolean isPar = false;
        boolean isImpar = false;
        
        if (numero % 2 == 0) {
        	isPar = true;
        	
        } else {
        	isImpar = true;
        }
        
        
        JsonObject jsonRespuesta = new JsonObject();
        jsonRespuesta.addProperty("isPar", isPar);
        jsonRespuesta.addProperty("isImpar", isImpar);
        
        return jsonRespuesta.toString();
        
    }
	
	@GET
	@Path("/getMateriasSinBd")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasSinBd() {
		Gson gson = new Gson();
		List<Materia> materias = new ArrayList<Materia>();

		for (int i = 0; i < 3 ; i++) {
			
			Materia mat = new Materia();
			mat.setIdMateria(i);
			mat.setNombre("Materia " + i);
			
			materias.add(mat);
		}
	
		return gson.toJson(materias);
	}
	
	@GET
	@Path("/getMateriasSinBdManager")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasSinBdManager() {
		Gson gson = new Gson();
		MateriasManager materiasManager = new MateriasManager();
		List<Materia> listMaterias = materiasManager.getTodasLasMateriasSinBd();
	
		return gson.toJson(listMaterias);
		
	}
	
	@GET
	@Path("/getMaterias")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMaterias() {
		Gson gson = new Gson();
		List<Materia> materias = new ArrayList<Materia>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Materias");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setIdMateria(rs.getInt("idMateria"));
				mat.setNombre(rs.getString("nombre"));
				
				materias.add(mat);
			}
			
//			Materia mat = new Materia();
//			mat.setIdMateria(1);
//			mat.setNombre("Fisica");
//			
//			materias.add(mat);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(materias);
	}
	
	@POST
	@Path("/getMateriasById")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasById(String idMateria) {
		Gson gson = new Gson();
		List<Materia> materias = new ArrayList<Materia>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Materias WHERE idMateria = ?");
			ps.setInt(1, Integer.valueOf(idMateria));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setIdMateria(rs.getInt("idMateria"));
				mat.setNombre(rs.getString("nombre"));
				
				materias.add(mat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return gson.toJson(materias);
	}
	
	@POST
	@Path("/getMateriasByIdJson")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getMateriasByIdJson(String materia) {
		Gson gson = new Gson();
		MateriaInput materiaInput = gson.fromJson(materia, MateriaInput.class);
		List<Materia> materias = new ArrayList<Materia>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Materias WHERE idMateria = ?");
			ps.setInt(1, materiaInput.getIdMateria());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setIdMateria(rs.getInt("idMateria"));
				mat.setNombre(rs.getString("nombre"));
				
				materias.add(mat);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return gson.toJson(materias);
	}
	
	@GET
	@Path("/getEstudiantes")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String getEstudiantes() {
		Gson gson = new Gson();
		List<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		ResultSet rs;
		PreparedStatement ps;
		
		try {
			Connection conn = BaseDatos.getConnection();
			ps = conn.prepareStatement("SELECT  * from Estudiantes");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Estudiante est = new Estudiante();
				est.setNombre(rs.getString("nombres"));
				
				estudiantes.add(est);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(estudiantes);
	}
	@POST
	@Path("/insertEstudiante")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertEstudiante(
	    @FormParam("idEstudiante") int idEstudiante,
	    @FormParam("identificacion") String identificacion,
	    @FormParam("nombre") String nombre,
	    @FormParam("apellidos") String apellidos,
	    @FormParam("email") String email,
	    @FormParam("edad") int edad,
	    @FormParam("activo") String activo
	) {
	    Gson gson = new Gson();
	    Map<String, String> response = new HashMap<>();

	    try 
	        (Connection conn = BaseDatos.getConnection()) { conn.setAutoCommit(false);
	        String sql = "INSERT INTO Estudiantes (idEstudiante, identificacion, nombres, apellidos, edad, email, activo) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, idEstudiante);
	        ps.setString(2, identificacion);
	        ps.setString(3, nombre);
	        ps.setString(4, apellidos);
	        ps.setInt(5, edad);
	        ps.setString(6, email);
	        ps.setString(7, activo);        

	        int rows = ps.executeUpdate();
	        conn.commit();
	        conn.close();

	        if (rows > 0) {
	            response.put("status", "ok");
	            response.put("message", "Estudiante insertado correctamente");
	        } else {
	            response.put("status", "error");
	            response.put("message", "No se insertó el estudiante");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "error");
	        response.put("message", e.getMessage());
	    }

	    return gson.toJson(response);
	 }
	@PUT
	@Path("/updateEstudiante")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateEstudiante(
	    @FormParam("idEstudiante") int idEstudiante,
	    @FormParam("identificacion") String identificacion,
	    @FormParam("nombre") String nombre,
	    @FormParam("apellidos") String apellidos,
	    @FormParam("email") String email,
	    @FormParam("edad") int edad,
	    @FormParam("activo") String activo
	) {
	    Gson gson = new Gson();
	    Map<String, String> response = new HashMap<>();

	    try (Connection conn = BaseDatos.getConnection()) {
	        conn.setAutoCommit(false);
	        String sql = "UPDATE Estudiantes SET identificacion = ?, nombres = ?, apellidos = ?, edad = ?, email = ?, activo = ? WHERE idEstudiante = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, identificacion);
	        ps.setString(2, nombre);
	        ps.setString(3, apellidos);
	        ps.setInt(4, edad);
	        ps.setString(5, email);
	        ps.setString(6, activo);
	        ps.setInt(7, idEstudiante);

	        int rows = ps.executeUpdate();
	        conn.commit();
	        conn.close();

	        if (rows > 0) {
	            response.put("status", "ok");
	            response.put("message", "Estudiante actualizado correctamente");
	        } else {
	            response.put("status", "error");
	            response.put("message", "No se encontró el estudiante para actualizar");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("status", "error");
	        response.put("message", e.getMessage());
	    }

	    return gson.toJson(response);
	}
	@DELETE
	@Path("/deleteEstudiante")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteEstudiante(@QueryParam("idEstudiante") int idEstudiante) {
	    try (Connection conn = BaseDatos.getConnection()) {
	        conn.setAutoCommit(false);

	        PreparedStatement ps1 = conn.prepareStatement("DELETE FROM Promedios WHERE idEstudiante = ?");
	        ps1.setInt(1, idEstudiante);
	        ps1.executeUpdate();

	        PreparedStatement ps2 = conn.prepareStatement("DELETE FROM MateriasEstudiantes WHERE idEstudiante = ?");
	        ps2.setInt(1, idEstudiante);
	        ps2.executeUpdate();

	        PreparedStatement ps3 = conn.prepareStatement("DELETE FROM Estudiantes WHERE idEstudiante = ?");
	        ps3.setInt(1, idEstudiante);
	        int filas = ps3.executeUpdate();

	        if (filas > 0) {
	            conn.commit();
	            return "{\"mensaje\": \"Estudiante eliminado correctamente\"}";
	        } else {
	            conn.rollback();
	            return "{\"mensaje\": \"No se encontró el estudiante con ID: " + idEstudiante + "\"}";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "{\"error\": \"Error al eliminar estudiante: " + e.getMessage() + "\"}";
	    }
	}
	@POST
	@Path("/promedioEstudiante")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String promedioEstudiante(@FormParam("nombre") String nombre, @FormParam("identificacion") String identificacion) {
	    try (Connection conn = BaseDatos.getConnection()) {
	        String sqlEst = "SELECT idEstudiante FROM Estudiantes WHERE nombres = ? AND identificacion = ?";
	        PreparedStatement psEst = conn.prepareStatement(sqlEst);
	        psEst.setString(1, nombre);
	        psEst.setString(2, identificacion);
	        ResultSet rsEst = psEst.executeQuery();

	        if (!rsEst.next()) {
	            return "{\"mensaje\": \"No se encontró el estudiante con esos datos\"}";
	        }

	        int idEstudiante = rsEst.getInt("idEstudiante");

	        String sqlNotas = "SELECT m.nombre AS materia, me.nota FROM MateriasEstudiantes me JOIN Materias m ON me.idMateria = m.idMateria WHERE me.idEstudiante = ?";
	        PreparedStatement psNotas = conn.prepareStatement(sqlNotas);
	        psNotas.setInt(1, idEstudiante);
	        ResultSet rsNotas = psNotas.executeQuery();

	        double suma = 0;
	        int cantidad = 0;
	        double mejorNota = 0;
	        String mejorMateria = "";

	        while (rsNotas.next()) {
	            double nota = rsNotas.getDouble("nota");
	            String materia = rsNotas.getString("materia");
	            suma += nota;
	            cantidad++;

	            if (nota > mejorNota) {
	                mejorNota = nota;
	                mejorMateria = materia;
	            }
	        }

	        if (cantidad == 0) {
	            return "{\"mensaje\": \"El estudiante no tiene notas registradas\"}";
	        }

	        double promedio = suma / cantidad;

	        return "{\"nombre\": \"" + nombre + "\", \"identificacion\": \"" + identificacion + "\", \"promedio\": " + promedio + ", \"mejorMateria\": \"" + mejorMateria + "\", \"mejorNota\": " + mejorNota + "}";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "{\"error\": \"Error al calcular el promedio: " + e.getMessage() + "\"}";
	    }
	}
}




