package dao;

import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    
    public void inserir(Paciente paciente) throws SQLException {
        String sql = "INSERT INTO Paciente (nome, raca, animal, idade, sexo, id_usuario, plano) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getRaca());
            stmt.setString(3, paciente.getAnimal());
            stmt.setInt(4, paciente.getIdade());
            stmt.setString(5, String.valueOf(paciente.getSexo()));
            stmt.setInt(6, paciente.getIdUsuario());
            
            if (paciente.getPlano() != null) {
                stmt.setInt(7, paciente.getPlano());
            } else {
                stmt.setNull(7, Types.INTEGER);
            }
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setIdPaciente(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Paciente paciente) throws SQLException {
        String sql = "UPDATE Paciente SET nome=?, raca=?, animal=?, idade=?, sexo=?, id_usuario=?, plano=? WHERE id_paciente=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getRaca());
            stmt.setString(3, paciente.getAnimal());
            stmt.setInt(4, paciente.getIdade());
            stmt.setString(5, String.valueOf(paciente.getSexo()));
            stmt.setInt(6, paciente.getIdUsuario());
            
            if (paciente.getPlano() != null) {
                stmt.setInt(7, paciente.getPlano());
            } else {
                stmt.setNull(7, Types.INTEGER);
            }
            
            stmt.setInt(8, paciente.getIdPaciente());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Paciente WHERE id_paciente=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Paciente buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Paciente WHERE id_paciente=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPaciente(rs);
                }
            }
        }
        return null;
    }
    
    public List<Paciente> listarTodos() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                pacientes.add(mapearPaciente(rs));
            }
        }
        return pacientes;
    }
    
    public List<Paciente> buscarPorUsuario(Integer idUsuario) throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente WHERE id_usuario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pacientes.add(mapearPaciente(rs));
                }
            }
        }
        return pacientes;
    }
    
    private Paciente mapearPaciente(ResultSet rs) throws SQLException {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(rs.getInt("id_paciente"));
        paciente.setNome(rs.getString("nome"));
        paciente.setRaca(rs.getString("raca"));
        paciente.setAnimal(rs.getString("animal"));
        paciente.setIdade(rs.getInt("idade"));
        paciente.setSexo(rs.getString("sexo").charAt(0));
        paciente.setIdUsuario(rs.getInt("id_usuario"));
        
        Integer plano = rs.getInt("plano");
        if (!rs.wasNull()) {
            paciente.setPlano(plano);
        }
        
        return paciente;
    }
}