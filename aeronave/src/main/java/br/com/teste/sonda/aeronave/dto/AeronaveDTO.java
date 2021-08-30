package br.com.teste.sonda.aeronave.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AeronaveDTO {
	
	@NotEmpty(message = "O campo nome é obrigatório.")
	private String nome;
	
	@NotEmpty(message = "O campo marca é obrigatório.")
	private String marca;
	
	@NotNull(message = "O campo ano é obrigatório.")
	private Long ano;
	
	private String descricao;
	
	@NotNull(message = "O campo nome é obrigatório.")
	private Boolean vendido;
}
