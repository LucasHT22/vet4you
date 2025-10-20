package controller;

import dao.CirurgiaDAO;
import model.Cirurgia;
import java.sql.SQLException;
import java.util.List;

public class CirurgiaController {
    private CirurgiaDAO cirurgiaDAO;
    
    public CirurgiaController() {
        this.cirurgiaDAO = new CirurgiaDAO();
    }
    
    public boolean registrarCirurgia(Cirurgia cirurgia) {
        try {
            if (cirurgia.getIdPaciente() == null || cirurgia.getIdVeterinario() == null) {
                System.out.println("Paciente e Veterinário são obrigatórios!");
                return false;
            }
            
            cirurgiaDAO.inserir(cirurgia);
            System.out.println("Cirurgia registrada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar cirurgia: " + e.getMessage());
            return false;
        }
    }
    
    public List<Cirurgia> listarCirurgias() {
        try {
            return cirurgiaDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar cirurgias: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarCirurgia(Cirurgia cirurgia) {
        try {
            cirurgiaDAO.atualizar(cirurgia);
            System.out.println("Cirurgia atualizada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cirurgia: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerCirurgia(Integer id) {
        try {
            cirurgiaDAO.deletar(id);
            System.out.println("Cirurgia removida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover cirurgia: " + e.getMessage());
            return false;
        }
    }
    
    public Cirurgia buscarCirurgiaPorId(Integer id) {
        try {
            return cirurgiaDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cirurgia: " + e.getMessage());
            return null;
        }
    }
}