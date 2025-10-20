package model;

import java.time.LocalDate;

public class Internacao {
    private Integer idInternacao;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String observacoes;
    private Integer idVeterinario;
    private Integer idPaciente;

    public Internacao() {}

    public Internacao(LocalDate dataEntrada, LocalDate dataSaida, String observacoes, Integer idVeterinario, Integer idPaciente) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.observacoes = observacoes;
        this.idVeterinario = idVeterinario;
        this.idPaciente = idPaciente;
    }

    // Getters e Setters
    public Integer getIdInternacao() { return idInternacao; }
    public void setIdInternacao(Integer idInternacao) { this.idInternacao = idInternacao; }
    
    public LocalDate getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }
    
    public LocalDate getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    public Integer getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(Integer idVeterinario) { this.idVeterinario = idVeterinario; }
    
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
}