package controller;

import dao.PacienteDAO;
import model.Paciente;
import java.sql.SQLException;
import java.util.List;

public class PacienteController {
    private PacienteDAO pacienteDAO;
    
    public PacienteController() {
        this.pacienteDAO = new PacienteDAO();
    }
    
    public boolean cadastrarPaciente(Paciente paciente) {
        try {
            if (paciente.getNome() == null || paciente.getIdUsuario() == null) {
                System.out.println("Nome e ID do usuário são obrigatórios!");
                return false;
            }
            
            pacienteDAO.inserir(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar paciente: " + e.getMessage());
            return false;
        }
    }
    
    public List<Paciente> listarPacientes() {
        try {
            return pacienteDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes: " + e.getMessage());
            return null;
        }
    }
    
    public List<Paciente> listarPacientesPorUsuario(Integer idUsuario) {
        try {
            return pacienteDAO.buscarPorUsuario(idUsuario);
        } catch (SQLException e) {
            System.err.println("Erro ao listar pacientes do usuário: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarPaciente(Paciente paciente) {
        try {
            pacienteDAO.atualizar(paciente);
            System.out.println("Paciente atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar paciente: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerPaciente(Integer id) {
        try {
            pacienteDAO.deletar(id);
            System.out.println("Paciente removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover paciente: " + e.getMessage());
            return false;
        }
    }
    
    public Paciente buscarPacientePorId(Integer id) {
        try {
            return pacienteDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar paciente: " + e.getMessage());
            return null;
        }
    }
}