package com.generation.projetointegradorfit.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "tb_exercicios")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do exercício é obrigatório!")
    @Size(min = 5, max = 100, message = "O nome do exercício deve conter entre 5 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O grupo muscular é obrigatório!")
    @Size(min = 5, max = 100, message = "O grupo muscular deve conter entre 5 e 100 caracteres")
    private String grupoMuscular;

    @NotNull(message = "O número de repetições é obrigatório!")
    @Min(value = 1, message = "O número de repetições deve ser maior que 0")
    private Integer repeticoes;

    @NotNull(message = "O número de séries é obrigatório!")
    @Min(value = 1, message = "O número de séries deve ser maior que 0")
    private Integer series;

    @ManyToOne
    @JsonIgnoreProperties("exercicio")
    private Treino treino;

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

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public Integer getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(Integer repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    // Métodos úteis

    @Override
    public String toString() {
        return "Exercicio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", grupoMuscular='" + grupoMuscular + '\'' +
                ", repeticoes=" + repeticoes +
                ", series=" + series +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercicio exercicio = (Exercicio) o;
        return id.equals(exercicio.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}