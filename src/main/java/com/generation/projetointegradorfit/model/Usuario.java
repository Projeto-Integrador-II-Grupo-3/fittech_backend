package com.generation.projetointegradorfit.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @NotBlank
    private String tipo;

   
    private Double peso;


    
    private Double altura;


    private Double imc;
    
    
        
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    @JsonIgnoreProperties({"usuario", "descricao"})
    private List<Treino> treino;
    
    
    


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

	public List<Treino> getTreino() {
		return treino;
	}


	public void setTreino(List<Treino> treino) {
		this.treino = treino;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	


	


	


  


}
