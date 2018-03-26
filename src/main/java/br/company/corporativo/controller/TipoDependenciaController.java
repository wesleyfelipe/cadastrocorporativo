package br.company.corporativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.company.corporativo.entity.TipoDependencia;
import br.company.corporativo.service.TipoDependenciaService;

@Named
@ViewScoped
public class TipoDependenciaController extends AbstractController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoDependenciaService tipoDependenciaService;
	
	private List<TipoDependencia> tiposDependencia;
	
	@PostConstruct
	public void init() {
		this.tiposDependencia = tipoDependenciaService.findAll();
	}
	
	public List<TipoDependencia> getTiposDependencia() {
		return tiposDependencia;
	}
	
	public void setTiposDependencia(List<TipoDependencia> tiposDependencia) {
		this.tiposDependencia = tiposDependencia;
	}

}
