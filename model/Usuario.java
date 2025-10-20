package model;

import java.util.List;

public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String telefone;
    private String email;
    private String username;
    private String password;
    private Character isAdmin;
    private String cpf;
    private Character isActive;
    private List<Paciente> pacientes;
    private List<Endereco> enderecos;

    public Usuario() {}

    public Usuario(String nome, String telefone, String email, String username, String password, Character isAdmin, String cpf, Character isActive) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.cpf = cpf;
        this.isActive = isActive;
    }

    // Getters e Setters
    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public Character getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Character isAdmin) { this.isAdmin = isAdmin; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public Character getIsActive() { return isActive; }
    public void setIsActive(Character isActive) { this.isActive = isActive; }
    
    public List<Paciente> getPacientes() { return pacientes; }
    public void setPacientes(List<Paciente> pacientes) { this.pacientes = pacientes; }
    
    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
}