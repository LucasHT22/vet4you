package dao;

import model.Internacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InternacaoDAO {
    
    public void inserir(Internacao internacao) throws SQLException {
        String sql = "INSERT INTO Internacao (data_entrada, data_saida, motivo, id_paciente) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDate(1, Date.valueOf(internacao.getDataEntrada()));
            
            if (internacao.getDataSaida() != null) {
                stmt.setDate(2, Date.valueOf(internacao.getDataSaida()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            
            stmt.setString(3, internacao.getMotivo());
            stmt.setInt(4, internacao.getIdPaciente());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    internacao.setIdInternacao(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Internacao internacao) throws SQLException {
        String sql = "UPDATE Internacao SET data_entrada=?, data_saida=?, motivo=?, id_paciente=? WHERE id_internacao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(internacao.getDataEntrada()));
            
            if (internacao.getDataSaida() != null) {
                stmt.setDate(2, Date.valueOf(internacao.getDataSaida()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            
            stmt.setString(3, internacao.getMotivo());
            stmt.setInt(4, internacao.getIdPaciente());
            stmt.setInt(5, internacao.getIdInternacao());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Internacao WHERE id_internacao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Internacao buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Internacao WHERE id_internacao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearInternacao(rs);
                }
            }
        }
        return null;
    }
    
    public List<Internacao> listarTodos() throws SQLException {
        List<Internacao> internacoes = new ArrayList<>();
        String sql = "SELECT * FROM Internacao";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                internacoes.add(mapearInternacao(rs));
            }
        }
        return internacoes;
    }
    
    public List<Internacao> buscarPorPaciente(Integer idPaciente) throws SQLException {
        List<Internacao> internacoes = new ArrayList<>();
        String sql = "SELECT * FROM Internacao WHERE id_paciente=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    internacoes.add(mapearInternacao(rs));
                }
            }
        }
        return internacoes;
    }
    
    private Internacao mapearInternacao(ResultSet rs) throws SQLException {
        Internacao internacao = new Internacao();
        internacao.setIdInternacao(rs.getInt("id_internacao"));
        internacao.setDataEntrada(rs.getDate("data_entrada").toLocalDate());
        
        Date dataSaida = rs.getDate("data_saida");
        if (dataSaida != null) {
            internacao.setDataSaida(dataSaida.toLocalDate());
        }
        
        internacao.setMotivo(rs.getString("motivo"));
        internacao.setIdPaciente(rs.getInt("id_paciente"));
        return internacao;
    }
}