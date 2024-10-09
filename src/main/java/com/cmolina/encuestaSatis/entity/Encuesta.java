package com.cmolina.encuestaSatis.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity //Indica que esta clase es una entidad
@Table(name="encuestas") //Indica que la tabla de la BBDD relacionada con esta entidad se llama productos
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El campo Nombre no puede estar en blanco")
    @Size(min=2,message="El nombre debe tener 2 caracteres")
    private String nombre;
    @NotBlank(message = "El campo Apellidos no puede estar en blanco")
    @Size(min=2,message="El apellido debe tener 2 caracteres")
    private String apellidos;
    @NotBlank(message = "El campo Correo electrónico no puede estar en blanco")
    @Email(message="El correo no esta en un formato valido ****@****.***")
    private String mail;
    @NotNull(message = "El campo Edad no puede estar en blanco")
    @Min(value = 18,message = "No puedes ser menor de 18")
    private Integer edad;
    @NotBlank(message = "El campo Telefono no puede estar en blanco")
    private String telefono;
    @NotNull(message = "El campo Fecha de Entrada no puede estar en blanco")
    @PastOrPresent(message = "La fecha de entrada tiene que ser la actual o pasada")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaEnt;
    @NotBlank(message = "El campo Motivo no puede estar en blanco")
    private String motivo;
    private Boolean restaurante;
    private Boolean gym;
    private Boolean spa;
    private Boolean piscina;
    private Boolean room;
    @NotBlank(message = "Debes elegir un nivel de satisfacción")
    private String satisfaccion;
    private String comment;

    public Encuesta(Long id, String nombre, String apellidos, String mail, Integer edad, String telefono, LocalDate fechaEnt, String motivo, Boolean restaurante, Boolean gym, Boolean spa, Boolean piscina, Boolean room, String satisfaccion, String comment) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
        this.edad = edad;
        this.telefono = telefono;
        this.fechaEnt = fechaEnt;
        this.motivo = motivo;
        this.restaurante = restaurante;
        this.gym = gym;
        this.spa = spa;
        this.piscina = piscina;
        this.room = room;
        this.satisfaccion = satisfaccion;
        this.comment = comment;
    }

    public Encuesta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaEnt() {
        return fechaEnt;
    }

    public void setFechaEnt(LocalDate fechaEnt) {
        this.fechaEnt = fechaEnt;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Boolean getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Boolean restaurante) {
        this.restaurante = restaurante;
    }

    public Boolean getGym() {
        return gym;
    }

    public void setGym(Boolean gym) {
        this.gym = gym;
    }

    public Boolean getSpa() {
        return spa;
    }

    public void setSpa(Boolean spa) {
        this.spa = spa;
    }

    public Boolean getPiscina() {
        return piscina;
    }

    public void setPiscina(Boolean piscina) {
        this.piscina = piscina;
    }

    public Boolean getRoom() {
        return room;
    }

    public void setRoom(Boolean room) {
        this.room = room;
    }

    public String getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(String satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", mail='" + mail + '\'' +
                ", edad=" + edad +
                ", fechaEnt=" + fechaEnt +
                ", motivo='" + motivo + '\'' +
                ", restaurante=" + restaurante +
                ", gym=" + gym +
                ", spa=" + spa +
                ", piscina=" + piscina +
                ", room=" + room +
                ", satisfaccion='" + satisfaccion + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
