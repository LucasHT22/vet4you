package model;

import java.time.LocalDateTime;

public class Consulta {
    private Integer idConsulta;
    private LocalDateTime timestamp;
    private String motivo;
    private String diagnostico;
    private Integer idPaciente;
    private Integer idVeterinario;
    private Paciente paciente;
    private Veterinario veterinario;

    public Consulta() {}

    public Consulta(LocalDateTime timestamp, String motivo, String diagnostico, Integer idPaciente, Integer idVeterinario) {
        this.timestamp = timestamp;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.idPaciente = idPaciente;
        this.idVeterinario = idVeterinario;
    }

    // Getters e Setters
    public Integer getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Integer idConsulta) { this.idConsulta = idConsulta; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    
    public Integer getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(Integer idVeterinario) { this.idVeterinario = idVeterinario; }
    
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    
    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
}