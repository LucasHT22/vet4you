package dao;

import model.Exame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExameDAO {
    
    public void inserir(Exame exame) throws SQLException {
        String sql = "INSERT INTO Exame (tipo, resultado, data, id_consulta) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, exame.getTipo());
            stmt.setString(2, exame.getResultado());
            stmt.setDate(3, Date.valueOf(exame.getData()));
            stmt.setInt(4, exame.getIdConsulta());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    exame.setIdExame(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Exame exame) throws SQLException {
        String sql = "UPDATE Exame SET tipo=?, resultado=?, data=?, id_consulta=? WHERE id_exame=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, exame.getTipo());
            stmt.setString(2, exame.getResultado());
            stmt.setDate(3, Date.valueOf(exame.getData()));
            stmt.setInt(4, exame.getIdConsulta());
            stmt.setInt(5, exame.getIdExame());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Exame WHERE id_exame=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Exame buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Exame WHERE id_exame=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearExame(rs);
                }
            }
        }
        return null;
    }
    
    public List<Exame> listarTodos() throws SQLException {
        List<Exame> exames = new ArrayList<>();
        String sql = "SELECT * FROM Exame";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                exames.add(mapearExame(rs));
            }
        }
        return exames;
    }
    
    public List<Exame> buscarPorConsulta(Integer idConsulta) throws SQLException {
        List<Exame> exames = new ArrayList<>();
        String sql = "SELECT * FROM Exame WHERE id_consulta=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idConsulta);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    exames.add(mapearExame(rs));
                }
            }
        }
        return exames;
    }
    
    private Exame mapearExame(ResultSet rs) throws SQLException {
        Exame exame = new Exame();
        exame.setIdExame(rs.getInt("id_exame"));
        exame.setTipo(rs.getString("tipo"));
        exame.setResultado(rs.getString("resultado"));
        exame.setData(rs.getDate("data").toLocalDate());
        exame.setIdConsulta(rs.getInt("id_consulta"));
        return exame;
    }
}