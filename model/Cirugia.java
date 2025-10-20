package model;

import java.time.LocalDate;

public class Cirurgia {
    private Integer idCirurgia;
    private String tipo;
    private LocalDate data;
    private String observacoes;
    private Integer idPaciente;
    private Integer idVeterinario;

    public Cirurgia() {}

    public Cirurgia(String tipo, LocalDate data, String observacoes, Integer idPaciente, Integer idVeterinario) {
        this.tipo = tipo;
        this.data = data;
        this.observacoes = observacoes;
        this.idPaciente = idPaciente;
        this.idVeterinario = idVeterinario;
    }

    // Getters e Setters
    public Integer getIdCirurgia() { return idCirurgia; }
    public void setIdCirurgia(Integer idCirurgia) { this.idCirurgia = idCirurgia; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    
    public Integer getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(Integer idVeterinario) { this.idVeterinario = idVeterinario; }
}