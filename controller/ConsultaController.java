package controller;

import dao.ConsultaDAO;
import model.Consulta;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaController {
    private ConsultaDAO consultaDAO;
    
    public ConsultaController() {
        this.consultaDAO = new ConsultaDAO();
    }
    
    public boolean marcarConsulta(Consulta consulta) {
        try {
            if (consulta.getIdPaciente() == null || consulta.getIdVeterinario() == null) {
                System.out.println("Paciente e Veterinário são obrigatórios!");
                return false;
            }
            
            if (consulta.getTimestamp() == null) {
                consulta.setTimestamp(LocalDateTime.now());
            }
            
            consultaDAO.inserir(consulta);
            System.out.println("Consulta marcada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao marcar consulta: " + e.getMessage());
            return false;
        }
    }
    
    public List<Consulta> listarConsultas() {
        try {
            return consultaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas: " + e.getMessage());
            return null;
        }
    }
    
    public List<Consulta> listarConsultasPorPaciente(Integer idPaciente) {
        try {
            return consultaDAO.buscarPorPaciente(idPaciente);
        } catch (SQLException e) {
            System.err.println("Erro ao listar consultas do paciente: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarConsulta(Consulta consulta) {
        try {
            consultaDAO.atualizar(consulta);
            System.out.println("Consulta atualizada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar consulta: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerConsulta(Integer id) {
        try {
            consultaDAO.deletar(id);
            System.out.println("Consulta removida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover consulta: " + e.getMessage());
            return false;
        }
    }
    
    public Consulta buscarConsultaPorId(Integer id) {
        try {
            return consultaDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar consulta: " + e.getMessage());
            return null;
        }
    }
}