package com.generation.projetointegradorfit.model;


import java.util.List;

//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "tb_treino")
public class Treino {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Treino() {}

    @NotBlank(message = "O nome do treino é Obrigatório!")
    @Size(min = 2, max = 50, message = "O atributo nome do treino deve conter no mínimo 02 e no máximo 100 caracteres")
    private String treino;


    @NotBlank(message = "O atributo descrição é Obrigatório!")
    @Size(min = 5, max = 1000, message = "O atributo descrição deve conter no mínimo 05 e no máximo 1000 caracteres")
    private String descricao;


    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "treino", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("treino")
    private List<Exercicio> exercicio;
    
    @ManyToOne 
   	@JsonIgnoreProperties({"treino", "cpf", "usuario", "senha"}) // anotação para não deixar uma resposta em loop no Json
   	private Usuario usuario;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    
    public String getTreino() {
        return treino;
    }


    public void setTreino(String treino) {
        this.treino = treino;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


	public List<Exercicio> getExercicio() {
		return exercicio;
	}


	public void setExercicio(List<Exercicio> exercicio) {
		this.exercicio = exercicio;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	

	


	


   
}
