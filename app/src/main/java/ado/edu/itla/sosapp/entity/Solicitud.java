package ado.edu.itla.sosapp.entity;


import java.util.Date;

public class Solicitud {

    private int id;
    private String title;
    private String description;
    private AreaAfin areaAfin;
    private String state;
    private Date onCreate;
    private int usuarioSolicitante;
    private int usuarioAsignado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AreaAfin getAreaAfin() {
        return areaAfin;
    }

    public void setAreaAfin(AreaAfin areaAfin) {
        this.areaAfin = areaAfin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getOnCreate() {
        return onCreate;
    }

    public void setOnCreate(Date onCreate) {
        this.onCreate = onCreate;
    }

    public int getUsuarioSolicitante() {
        return usuarioSolicitante;
    }

    public void setUsuarioSolicitante(int usuarioSolicitante) {
        this.usuarioSolicitante = usuarioSolicitante;
    }

    public int getUsuarioAsignado() {
        return usuarioAsignado;
    }

    public void setUsuarioAsignado(int usuarioAsignado) {
        this.usuarioAsignado = usuarioAsignado;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", areaAfin=" + areaAfin +
                ", state=" + state +
                ", onCreate=" + onCreate +
                ", usuarioSolicitante=" + usuarioSolicitante +
                ", usuarioAsignado=" + usuarioAsignado +
                '}';
    }
}
