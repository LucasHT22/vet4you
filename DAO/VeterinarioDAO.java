package dao;

import model.Veterinario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
    
    public void inserir(Veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO Veterinario (nome, especialidade, crmvet) VALUES (?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getEspecialidade());
            stmt.setInt(3, veterinario.getCrmvet());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veterinario.setIdVeterinario(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Veterinario veterinario) throws SQLException {
        String sql = "UPDATE Veterinario SET nome=?, especialidade=?, crmvet=? WHERE id_veterinario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getEspecialidade());
            stmt.setInt(3, veterinario.getCrmvet());
            stmt.setInt(4, veterinario.getIdVeterinario());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Veterinario WHERE id_veterinario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Veterinario buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Veterinario WHERE id_veterinario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearVeterinario(rs);
                }
            }
        }
        return null;
    }
    
    public List<Veterinario> listarTodos() throws SQLException {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM Veterinario";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                veterinarios.add(mapearVeterinario(rs));
            }
        }
        return veterinarios;
    }
    
    private Veterinario mapearVeterinario(ResultSet rs) throws SQLException {
        Veterinario vet = new Veterinario();
        vet.setIdVeterinario(rs.getInt("id_veterinario"));
        vet.setNome(rs.getString("nome"));
        vet.setEspecialidade(rs.getString("especialidade"));
        vet.setCrmvet(rs.getInt("crmvet"));
        return vet;
    }
}