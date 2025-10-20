package dao;

import model.Cirurgia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CirurgiaDAO {
    
    public void inserir(Cirurgia cirurgia) throws SQLException {
        String sql = "INSERT INTO Cirurgia (tipo, data, observacoes, id_paciente, id_veterinario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, cirurgia.getTipo());
            stmt.setTimestamp(2, Timestamp.valueOf(cirurgia.getData()));
            stmt.setString(3, cirurgia.getObservacoes());
            stmt.setInt(4, cirurgia.getIdPaciente());
            stmt.setInt(5, cirurgia.getIdVeterinario());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cirurgia.setIdCirurgia(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Cirurgia cirurgia) throws SQLException {
        String sql = "UPDATE Cirurgia SET tipo=?, data=?, observacoes=?, id_paciente=?, id_veterinario=? WHERE id_cirurgia=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cirurgia.getTipo());
            stmt.setTimestamp(2, Timestamp.valueOf(cirurgia.getData()));
            stmt.setString(3, cirurgia.getObservacoes());
            stmt.setInt(4, cirurgia.getIdPaciente());
            stmt.setInt(5, cirurgia.getIdVeterinario());
            stmt.setInt(6, cirurgia.getIdCirurgia());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Cirurgia WHERE id_cirurgia=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Cirurgia buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Cirurgia WHERE id_cirurgia=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCirurgia(rs);
                }
            }
        }
        return null;
    }
    
    public List<Cirurgia> listarTodos() throws SQLException {
        List<Cirurgia> cirurgias = new ArrayList<>();
        String sql = "SELECT * FROM Cirurgia";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                cirurgias.add(mapearCirurgia(rs));
            }
        }
        return cirurgias;
    }
    
    public List<Cirurgia> buscarPorPaciente(Integer idPaciente) throws SQLException {
        List<Cirurgia> cirurgias = new ArrayList<>();
        String sql = "SELECT * FROM Cirurgia WHERE id_paciente=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cirurgias.add(mapearCirurgia(rs));
                }
            }
        }
        return cirurgias;
    }
    
    private Cirurgia mapearCirurgia(ResultSet rs) throws SQLException {
        Cirurgia cirurgia = new Cirurgia();
        cirurgia.setIdCirurgia(rs.getInt("id_cirurgia"));
        cirurgia.setTipo(rs.getString("tipo"));
        cirurgia.setData(rs.getTimestamp("data").toLocalDateTime());
        cirurgia.setObservacoes(rs.getString("observacoes"));
        cirurgia.setIdPaciente(rs.getInt("id_paciente"));
        cirurgia.setIdVeterinario(rs.getInt("id_veterinario"));
        return cirurgia;
    }
}