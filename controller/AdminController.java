package controller;

import dao.*;
import model.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminController {
    private PagamentoDAO pagamentoDAO;
    private UsuarioDAO usuarioDAO;
    private ConsultaDAO consultaDAO;
    private PacienteDAO pacienteDAO;
    private VeterinarioDAO veterinarioDAO;
    
    public AdminController() {
        this.pagamentoDAO = new PagamentoDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.consultaDAO = new ConsultaDAO();
        this.pacienteDAO = new PacienteDAO();
        this.veterinarioDAO = new VeterinarioDAO();
    }
    
    public Map<String, Object> gerarBalancoFinanceiro(LocalDate dataInicio, LocalDate dataFim) {
        Map<String, Object> balanco = new HashMap<>();
        
        try {
            List<Pagamento> pagamentos = pagamentoDAO.listarTodos();
            
            BigDecimal totalRecebido = BigDecimal.ZERO;
            BigDecimal totalPendente = BigDecimal.ZERO;
            int qtdPagamentosConfirmados = 0;
            int qtdPagamentosPendentes = 0;
            
            for (Pagamento p : pagamentos) {
                if (dataInicio != null && dataFim != null) {
                    if (p.getDataPagamento().isBefore(dataInicio) || 
                        p.getDataPagamento().isAfter(dataFim)) {
                        continue;
                    }
                }
                
                if (p.getStatus() == 'C') {
                    totalRecebido = totalRecebido.add(p.getValor());
                    qtdPagamentosConfirmados++;
                } else {
                    totalPendente = totalPendente.add(p.getValor());
                    qtdPagamentosPendentes++;
                }
            }
            
            balanco.put("totalRecebido", totalRecebido);
            balanco.put("totalPendente", totalPendente);
            balanco.put("qtdPagamentosConfirmados", qtdPagamentosConfirmados);
            balanco.put("qtdPagamentosPendentes", qtdPagamentosPendentes);
            balanco.put("saldoTotal", totalRecebido);
            
            System.out.println("Balanço financeiro gerado com sucesso!");
            return balanco;
            
        } catch (SQLException e) {
            System.err.println("Erro ao gerar balanço: " + e.getMessage());
            return null;
        }
    }
    
    public Map<String, Object> gerarDashboardGeral() {
        Map<String, Object> dashboard = new HashMap<>();
        
        try {
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            List<Paciente> pacientes = pacienteDAO.listarTodos();
            List<Consulta> consultas = consultaDAO.listarTodos();
            List<Veterinario> veterinarios = veterinarioDAO.listarTodos();
            List<Pagamento> pagamentos = pagamentoDAO.listarTodos();
            
            dashboard.put("totalUsuarios", usuarios.size());
            dashboard.put("totalPacientes", pacientes.size());
            dashboard.put("totalConsultas", consultas.size());
            dashboard.put("totalVeterinarios", veterinarios.size());
            
            int usuariosAtivos = 0;
            for (Usuario u : usuarios) {
                if (u.getIsActive() == 'S' || u.getIsActive() == '1') {
                    usuariosAtivos++;
                }
            }
            dashboard.put("usuariosAtivos", usuariosAtivos);
            
            BigDecimal receitaTotal = BigDecimal.ZERO;
            for (Pagamento p : pagamentos) {
                if (p.getStatus() == 'C') {
                    receitaTotal = receitaTotal.add(p.getValor());
                }
            }
            dashboard.put("receitaTotal", receitaTotal);
            
            System.out.println("Dashboard geral gerado com sucesso!");
            return dashboard;
            
        } catch (SQLException e) {
            System.err.println("Erro ao gerar dashboard: " + e.getMessage());
            return null;
        }
    }
    
    public List<Usuario> listarTodosUsuarios() {
        try {
            return usuarioDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            return null;
        }
    }
    
    public Map<String, Object> relatoriosGerais() {
        Map<String, Object> relatorios = new HashMap<>();
        
        try {
            List<Consulta> consultas = consultaDAO.listarTodos();
            List<Paciente> pacientes = pacienteDAO.listarTodos();
            List<Pagamento> pagamentos = pagamentoDAO.listarTodos();
            
            // Relatório de consultas por mês
            Map<Integer, Integer> consultasPorMes = new HashMap<>();
            for (Consulta c : consultas) {
                int mes = c.getTimestamp().getMonthValue();
                consultasPorMes.put(mes, consultasPorMes.getOrDefault(mes, 0) + 1);
            }
            
            // Relatório de animais mais atendidos
            Map<String, Integer> animaisMaisAtendidos = new HashMap<>();
            for (Paciente p : pacientes) {
                String animal = p.getAnimal();
                animaisMaisAtendidos.put(animal, animaisMaisAtendidos.getOrDefault(animal, 0) + 1);
            }
            
            // Relatório de formas de pagamento
            Map<String, Integer> formasPagamento = new HashMap<>();
            for (Pagamento p : pagamentos) {
                String forma = p.getFormaPagamento();
                formasPagamento.put(forma, formasPagamento.getOrDefault(forma, 0) + 1);
            }
            
            relatorios.put("consultasPorMes", consultasPorMes);
            relatorios.put("animaisMaisAtendidos", animaisMaisAtendidos);
            relatorios.put("formasPagamento", formasPagamento);
            
            System.out.println("Relatórios gerais gerados com sucesso!");
            return relatorios;
            
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatórios: " + e.getMessage());
            return null;
        }
    }
    
    public boolean desativarUsuario(Integer idUsuario) {
        try {
            Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
            if (usuario != null) {
                usuario.setIsActive('N');
                usuarioDAO.atualizar(usuario);
                System.out.println("Usuário desativado com sucesso!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao desativar usuário: " + e.getMessage());
            return false;
        }
    }
    
    public boolean ativarUsuario(Integer idUsuario) {
        try {
            Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
            if (usuario != null) {
                usuario.setIsActive('S');
                usuarioDAO.atualizar(usuario);
                System.out.println("Usuário ativado com sucesso!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao ativar usuário: " + e.getMessage());
            return false;
        }
    }
}