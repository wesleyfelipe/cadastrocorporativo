package br.company.corporativo.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.company.corporativo.entity.DependenciaPessoa;
import br.company.corporativo.entity.PessoaFisica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.service.PessoaFisicaService;

@Named("pessoaFisicaController")
@ViewScoped
public class PessoaFisicaController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaFisicaService pessoaFisicaService;

	private PessoaFisica pessoaFisicaForm = new PessoaFisica();

	private DependenciaPessoa dependenteForm = new DependenciaPessoa();

	@PostConstruct
	public void init() {
		limparDependenteForm();
		limparForm();
	}

	public void limparDependenteForm() {
		this.dependenteForm = new DependenciaPessoa();
		this.dependenteForm.setDependente(new PessoaFisica());
	}

	public void limparForm() {
		this.pessoaFisicaForm = new PessoaFisica();
	}

	public void addDependente() {
		pessoaFisicaService.addDependente(this.pessoaFisicaForm, this.dependenteForm);
		hideDependenteModal();
	}
	
	public void hideDependenteModal() {
		super.hideModal("dependenteFormDialog");
	}
	
	public void removeDependente(DependenciaPessoa dependencia) {
		pessoaFisicaService.removeDependente(this.pessoaFisicaForm, dependencia);
	}
	
	public void editDependente(DependenciaPessoa dependencia) {
		this.dependenteForm = dependencia;
	}

	public void salvarPessoa() {
		pessoaFisicaService.save(this.pessoaFisicaForm);
		super.showInfoMessageRegistroSalvo();
		super.hideModal("pessoaFisicaFormDialog");
	}

	public void deletePessoa(PessoaVw pessoa) {
		pessoaFisicaService.delete(pessoa);
		super.showInfoMessageRegistroExcluido();
	}

	public void editPessoa(PessoaVw pessoa) {
		this.pessoaFisicaForm = pessoaFisicaService.findOne(pessoa);
		if (this.pessoaFisicaForm.getMunicipio() != null)
			this.pessoaFisicaForm.setUf(this.pessoaFisicaForm.getMunicipio().getUnidadeFederativa());
	}

	public void limparMunicipioForm() {
		this.pessoaFisicaForm.setMunicipio(null);
	}

	public PessoaFisica getPessoaFisicaForm() {
		return pessoaFisicaForm;
	}

	public void setPessoaFisicaForm(PessoaFisica pessoaFisicaForm) {
		this.pessoaFisicaForm = pessoaFisicaForm;
	}

	public DependenciaPessoa getDependenteForm() {
		return dependenteForm;
	}

	public void setDependenteForm(DependenciaPessoa dependenteForm) {
		this.dependenteForm = dependenteForm;
	}

}
