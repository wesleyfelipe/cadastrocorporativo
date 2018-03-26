package br.company.corporativo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.dto.FiltroPessoaDTO;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.enums.SortOrder;
import br.company.corporativo.repository.PessoaVwRepository;

@Stateless
public class PessoaVwService {

	@Inject
	private PessoaVwRepository pessoaVwRepository;
	
	public List<PessoaVw> findAll(FiltroPessoaDTO filtro, Integer first, Integer pageSize){
			
		return pessoaVwRepository.findAll(filtro, SortOrder.ASC, "nome", first, pageSize);
	}
	
	public List<PessoaVw> findAll(FiltroPessoaDTO filtro){
		
		return pessoaVwRepository.findAll(filtro, SortOrder.ASC, "nome");
	}
	
	public int count(FiltroPessoaDTO filtro) {
		return pessoaVwRepository.count(filtro);
	}
}
