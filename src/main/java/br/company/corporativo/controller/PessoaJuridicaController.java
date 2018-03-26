package br.company.corporativo.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.company.corporativo.entity.PessoaJuridica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.service.PessoaJuridicaService;

@Named("pessoaJuridicaController")
@ViewScoped
public class PessoaJuridicaController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaJuridicaService pessoaJuridicaService;

	private PessoaJuridica pessoaJuridicaForm = new PessoaJuridica();

	public void limparForm() {
		this.pessoaJuridicaForm = new PessoaJuridica();
	}

	public void salvarPessoa() {
		pessoaJuridicaService.save(this.pessoaJuridicaForm);
		super.showInfoMessageRegistroSalvo();
		super.hideModal("pessoaJuridicaFormDialog");
	}

	public void deletePessoa(PessoaVw pessoa) {
		pessoaJuridicaService.delete(pessoa);
		super.showInfoMessageRegistroExcluido();
	}

	public void editPessoa(PessoaVw pessoa) {
		this.pessoaJuridicaForm = pessoaJuridicaService.findOne(pessoa);
		if (this.pessoaJuridicaForm.getMunicipio() != null)
			this.pessoaJuridicaForm.setUf(this.pessoaJuridicaForm.getMunicipio().getUnidadeFederativa());
	}

	public void limparMunicipioForm() {
		this.pessoaJuridicaForm.setMunicipio(null);
	}

	public PessoaJuridica getPessoaJuridicaForm() {
		return pessoaJuridicaForm;
	}

	public void setPessoaJuridicaForm(PessoaJuridica pessoaJuridicaForm) {
		this.pessoaJuridicaForm = pessoaJuridicaForm;
	}

}
