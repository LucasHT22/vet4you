package dao;

import model.Prescricao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescricaoDAO {
    
    public void inserir(Prescricao prescricao) throws SQLException {
        String sql = "INSERT INTO Prescricao (id_consulta, id_produto, quantidade, instrucoes) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, prescricao.getIdConsulta());
            stmt.setInt(2, prescricao.getIdProduto());
            stmt.setInt(3, prescricao.getQuantidade());
            stmt.setString(4, prescricao.getInstrucoes());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    prescricao.setIdPrescricao(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Prescricao prescricao) throws SQLException {
        String sql = "UPDATE Prescricao SET id_consulta=?, id_produto=?, quantidade=?, instrucoes=? WHERE id_prescricao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, prescricao.getIdConsulta());
            stmt.setInt(2, prescricao.getIdProduto());
            stmt.setInt(3, prescricao.getQuantidade());
            stmt.setString(4, prescricao.getInstrucoes());
            stmt.setInt(5, prescricao.getIdPrescricao());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Prescricao WHERE id_prescricao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Prescricao buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Prescricao WHERE id_prescricao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPrescricao(rs);
                }
            }
        }
        return null;
    }
    
    public List<Prescricao> listarTodos() throws SQLException {
        List<Prescricao> prescricoes = new ArrayList<>();
        String sql = "SELECT * FROM Prescricao";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                prescricoes.add(mapearPrescricao(rs));
            }
        }
        return prescricoes;
    }
    
    public List<Prescricao> buscarPorConsulta(Integer idConsulta) throws SQLException {
        List<Prescricao> prescricoes = new ArrayList<>();
        String sql = "SELECT * FROM Prescricao WHERE id_consulta=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idConsulta);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    prescricoes.add(mapearPrescricao(rs));
                }
            }
        }
        return prescricoes;
    }
    
    private Prescricao mapearPrescricao(ResultSet rs) throws SQLException {
        Prescricao prescricao = new Prescricao();
        prescricao.setIdPrescricao(rs.getInt("id_prescricao"));
        prescricao.setIdConsulta(rs.getInt("id_consulta"));
        prescricao.setIdProduto(rs.getInt("id_produto"));
        prescricao.setQuantidade(rs.getInt("quantidade"));
        prescricao.setInstrucoes(rs.getString("instrucoes"));
        return prescricao;
    }
}