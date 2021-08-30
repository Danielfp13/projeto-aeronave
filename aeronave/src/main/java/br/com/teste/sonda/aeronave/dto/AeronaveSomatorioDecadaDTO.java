package br.com.teste.sonda.aeronave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AeronaveSomatorioDecadaDTO {

	private Long decada;
	private Long quantidade;
}

