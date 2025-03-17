package com.generation.projetointegradorfit.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Atributo NOME é Obrigatório!")
    private String nome;

    @NotNull(message = "O Atributo CPF é Obrigatório!")
    @Size(max = 11, message = "O Atributo CPF deve ser um CPF válido!")
    private String cpf;

    @NotNull(message = "O Atributo USUÁRIO é Obrigatório!")
    @Email(message = "O Atributo USUÁRIO deve ser um email válido!")
    private String usuario;

    @NotBlank(message = "O Atributo SENHA é Obrigatório!")
    @Size(min = 8, message = "A SENHA deve ter no mínimo 8 caracteres")
    private String senha;

    @NotBlank(message = "O Atributo TIPO é Obrigatório!")
    private String tipo;

    private Double peso;

    private Double altura;

    private Double imc;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuario") // Evita loop infinito ao serializar para JSON
    private List<Treino> treinos;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    // Métodos úteis

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", usuario='" + usuario + '\'' +
                ", tipo='" + tipo + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", imc=" + imc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

	public void adicionarTreino(Treino treino) {
	    if (this.treinos == null) {
	        this.treinos = new ArrayList<>();
	    }
	    this.treinos.add(treino);
	}
}