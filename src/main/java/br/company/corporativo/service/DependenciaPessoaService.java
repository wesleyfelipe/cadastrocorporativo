package br.company.corporativo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.entity.DependenciaPessoa;
import br.company.corporativo.entity.PessoaFisica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.repository.DependenciaPessoaRepository;
import br.company.corporativo.repository.PessoaFisicaRepository;

@Stateless
public class DependenciaPessoaService {

	@Inject
	private DependenciaPessoaRepository dependenciaPessoaRepository;
	
	@Inject
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	public List<DependenciaPessoa> findAllDependentes(PessoaVw pessoa){
		if(pessoa == null || !pessoa.isPessoaFisica() || pessoa.getId() == null)
			return new ArrayList<>();
		
		PessoaFisica pf = pessoaFisicaRepository.findOne(pessoa.getId());
		
		return dependenciaPessoaRepository.findAllDependentes(pf);
	}
}
