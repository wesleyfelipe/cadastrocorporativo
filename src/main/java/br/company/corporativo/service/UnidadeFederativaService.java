package br.company.corporativo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.entity.UnidadeFederativa;
import br.company.corporativo.repository.UnidadeFederativaRepository;

@Stateless
public class UnidadeFederativaService {

	@Inject
	private UnidadeFederativaRepository unidadeFederativaRepository;
	
	public List<UnidadeFederativa> findAll(){
		return unidadeFederativaRepository.findAll();
	}
}
