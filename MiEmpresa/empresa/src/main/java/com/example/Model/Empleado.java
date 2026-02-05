package com.example.Model;

public class Empleado {
    private int id;
    private String nombre;
    private int departamento;
    private int salario;
    private String fechaAlta;
    private String oficio;
    private int comision = 0;

    public Empleado(int id,String nombre,int departamento,int salario,String fechaAlta){
        this.id=id;
        this.nombre=nombre;
        this.departamento=departamento;
        this.salario=salario;
        this.fechaAlta=fechaAlta;
    }
    public Empleado(int id,String nombre,int departamento,int salario,String fechaAlta,String oficio){
        this.id=id;
        this.nombre=nombre;
        this.departamento=departamento;
        this.salario=salario;
        this.fechaAlta=fechaAlta;
        this.oficio=oficio;
    }
    public Empleado(int id,String nombre,int departamento,int salario,String fechaAlta,String oficio,int comision){
        this.id=id;
        this.nombre=nombre;
        this.departamento=departamento;
        this.salario=salario;
        this.fechaAlta=fechaAlta;
        this.oficio=oficio;
        this.comision=comision;
    }

    public int getID() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getDepartamento() {
        return departamento;
    }
    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
    public int getSalario() {
        return salario;
    }
    public void setSalario(int salario) {
        this.salario = salario;
    }
    public String getFechaAlta() {
        return fechaAlta;
    }
    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    public String getOficio() {
        return oficio;
    }
    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
    public int getComision() {
        return comision;
    }
    public void setComision(int comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Empleado [nombre=" + nombre + ", departamento=" + departamento + ", salario=" + salario + ", fechaAlta="
                + fechaAlta + ", oficio=" + oficio + ", comision=" + comision + "]";
    }
    
}
