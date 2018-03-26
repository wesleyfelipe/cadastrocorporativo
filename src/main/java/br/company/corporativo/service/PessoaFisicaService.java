package br.company.corporativo.service;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.company.corporativo.entity.DependenciaPessoa;
import br.company.corporativo.entity.PessoaFisica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.repository.PessoaFisicaRepository;

@Stateless
public class PessoaFisicaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaFisicaRepository pessoaFisicaRepository;

	public PessoaFisica save(PessoaFisica pessoa) {
		return pessoaFisicaRepository.save(pessoa);
	}

	public PessoaFisica findOne(PessoaVw pessoa) {
		return pessoaFisicaRepository.findOne(pessoa.getId());
	}

	public void delete(PessoaVw pessoa) {
		PessoaFisica pf = pessoaFisicaRepository.findOne(pessoa.getId());
		if (pf == null)
			return;

		pessoaFisicaRepository.delete(pf);
	}

	public void addDependente(PessoaFisica pessoaFisicaForm, DependenciaPessoa dependenteForm) {
		dependenteForm.setDependenteDe(pessoaFisicaForm);
		pessoaFisicaForm.getDependentes().add(dependenteForm);
	}

	public void removeDependente(PessoaFisica pessoaFisicaForm, DependenciaPessoa dependenteForm) {
		pessoaFisicaForm.getDependentes().remove(dependenteForm);
	}

}
