package controller;

import dao.ExameDAO;
import model.Exame;
import java.sql.SQLException;
import java.util.List;

public class ExameController {
    private ExameDAO exameDAO;
    
    public ExameController() {
        this.exameDAO = new ExameDAO();
    }
    
    public boolean registrarExame(Exame exame) {
        try {
            if (exame.getTipo() == null || exame.getIdConsulta() == null) {
                System.out.println("Tipo e ID da consulta são obrigatórios!");
                return false;
            }
            
            exameDAO.inserir(exame);
            System.out.println("Exame registrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar exame: " + e.getMessage());
            return false;
        }
    }
    
    public List<Exame> listarExames() {
        try {
            return exameDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar exames: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarExame(Exame exame) {
        try {
            exameDAO.atualizar(exame);
            System.out.println("Exame atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar exame: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerExame(Integer id) {
        try {
            exameDAO.deletar(id);
            System.out.println("Exame removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover exame: " + e.getMessage());
            return false;
        }
    }
    
    public Exame buscarExamePorId(Integer id) {
        try {
            return exameDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar exame: " + e.getMessage());
            return null;
        }
    }
}