package br.company.corporativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.company.corporativo.entity.UnidadeFederativa;
import br.company.corporativo.service.UnidadeFederativaService;

@Named
@ViewScoped
public class UnidadeFederativaController extends AbstractController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeFederativaService unidadeFederativaService;
	
	private List<UnidadeFederativa> unidadesFederativas;
	
	@PostConstruct
	public void init() {
		this.unidadesFederativas = unidadeFederativaService.findAll();
	}
	
	public List<UnidadeFederativa> getUnidadesFederativas() {
		return unidadesFederativas;
	}
	
	public void setUnidadesFederativas(List<UnidadeFederativa> unidadesFederativas) {
		this.unidadesFederativas = unidadesFederativas;
	}

}
