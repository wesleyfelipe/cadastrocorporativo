package br.company.corporativo.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.company.corporativo.entity.Municipio;
import br.company.corporativo.entity.UnidadeFederativa;
import br.company.corporativo.service.MunicipioService;

@Named
@ViewScoped
public class MunicipioController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MunicipioService municipioService;

	public List<Municipio> findAll(UnidadeFederativa uf) {
		return municipioService.findAll(uf);
	}

}
