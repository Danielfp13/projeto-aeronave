package br.com.teste.sonda.aeronave.controller.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
}
