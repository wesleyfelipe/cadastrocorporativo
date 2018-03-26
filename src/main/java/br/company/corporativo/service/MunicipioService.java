package br.company.corporativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.entity.Municipio;
import br.company.corporativo.entity.UnidadeFederativa;
import br.company.corporativo.repository.MunicipioRepository;

@Stateless
public class MunicipioService {

	@Inject
	private MunicipioRepository municipioRepository;
	
	public List<Municipio> findAll(UnidadeFederativa uf){
		if(uf == null)
			return new ArrayList<>();
		
		return municipioRepository.findAll(uf);
	}
}
