package br.company.corporativo.service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.entity.PessoaJuridica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.repository.PessoaJuridicaRepository;

@Stateless
public class PessoaJuridicaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	public PessoaJuridica save(PessoaJuridica pessoa) {
		return pessoaJuridicaRepository.save(pessoa);
	}

	public PessoaJuridica findOne(PessoaVw pessoa) {
		return pessoaJuridicaRepository.findOne(pessoa.getId());
	}

	public void delete(PessoaVw pessoa) {
		PessoaJuridica pj = pessoaJuridicaRepository.findOne(pessoa.getId());
		if (pj == null)
			return;

		pessoaJuridicaRepository.delete(pj);
	}

}
