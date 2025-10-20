package dao;

import model.Consulta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {
    
    public void inserir(Consulta consulta) throws SQLException {
        String sql = "INSERT INTO Consulta (timestamp, motivo, diagnostico, id_paciente, id_veterinario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setTimestamp(1, Timestamp.valueOf(consulta.getTimestamp()));
            stmt.setString(2, consulta.getMotivo());
            stmt.setString(3, consulta.getDiagnostico());
            stmt.setInt(4, consulta.getIdPaciente());
            stmt.setInt(5, consulta.getIdVeterinario());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setIdConsulta(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Consulta consulta) throws SQLException {
        String sql = "UPDATE Consulta SET timestamp=?, motivo=?, diagnostico=?, id_paciente=?, id_veterinario=? WHERE id_consulta=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTimestamp(1, Timestamp.valueOf(consulta.getTimestamp()));
            stmt.setString(2, consulta.getMotivo());
            stmt.setString(3, consulta.getDiagnostico());
            stmt.setInt(4, consulta.getIdPaciente());
            stmt.setInt(5, consulta.getIdVeterinario());
            stmt.setInt(6, consulta.getIdConsulta());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Consulta WHERE id_consulta=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Consulta buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Consulta WHERE id_consulta=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearConsulta(rs);
                }
            }
        }
        return null;
    }
    
    public List<Consulta> listarTodos() throws SQLException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                consultas.add(mapearConsulta(rs));
            }
        }
        return consultas;
    }
    
    public List<Consulta> buscarPorPaciente(Integer idPaciente) throws SQLException {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE id_paciente=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapearConsulta(rs));
                }
            }
        }
        return consultas;
    }
    
    private Consulta mapearConsulta(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(rs.getInt("id_consulta"));
        consulta.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
        consulta.setMotivo(rs.getString("motivo"));
        consulta.setDiagnostico(rs.getString("diagnostico"));
        consulta.setIdPaciente(rs.getInt("id_paciente"));
        consulta.setIdVeterinario(rs.getInt("id_veterinario"));
        return consulta;
    }
}