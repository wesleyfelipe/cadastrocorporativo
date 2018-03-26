package br.company.corporativo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.entity.TipoDependencia;
import br.company.corporativo.repository.TipoDependenciaRepository;

@Stateless
public class TipoDependenciaService {

	@Inject
	private TipoDependenciaRepository tipoDependenciaRepository;
	
	public List<TipoDependencia> findAll(){
		return tipoDependenciaRepository.findAll();
	}
}
