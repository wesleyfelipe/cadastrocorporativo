package br.company.corporativo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.StreamedContent;

import br.company.corporativo.dto.FiltroPessoaDTO;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.enums.TipoPessoaEnum;
import br.company.corporativo.service.DependenciaPessoaService;
import br.company.corporativo.service.PessoaVwService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named
@ViewScoped
public class PessoaController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String CAMINHO_ARQUIVO_REPORT = File.separator + "reports" + File.separator + "pessoas.jrxml";
	private static final String CAMINHO_ARQUIVO_DEPENDENTE_REPORT = File.separator + "reports" + File.separator
			+ "pessoas_dependente.jasper";

	private static final String NOME_ARQUIVO_PDF_PESSOAS = "pessoas.pdf";

	@Inject
	private PessoaVwService pessoaVwService;

	@Inject
	private DependenciaPessoaService dependenciaPessoaService;

	private FiltroPessoaDTO filtroPessoa = new FiltroPessoaDTO();

	private LazyDataModel<PessoaVw> pessoas;

	@PostConstruct
	public void init() {
		limparFiltro();
		listarPessoas();
	}

	public void listarPessoas() {

		this.pessoas = new LazyDataModel<PessoaVw>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<PessoaVw> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {
				List<PessoaVw> result = pessoaVwService.findAll(getFiltroPessoa(), first, pageSize);
				pessoas.setRowCount(pessoaVwService.count(filtroPessoa));
				return result;
			}
		};
	}

	public StreamedContent buildRelatorio() throws FileNotFoundException, JRException {
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(prepararDadosParaRelatorio());
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("subreportdir",
				FacesContext.getCurrentInstance().getExternalContext().getRealPath(CAMINHO_ARQUIVO_DEPENDENTE_REPORT));

		InputStream inputStream = super.gerarRelatorioPdf(CAMINHO_ARQUIVO_REPORT, FacesContext.getCurrentInstance(),
				beanColDataSource, parameters);
		return new DefaultStreamedContent(inputStream, CONTENT_TYPE_PDF, NOME_ARQUIVO_PDF_PESSOAS);
	}

	private List<PessoaVw> prepararDadosParaRelatorio() {
		List<PessoaVw> pessoas = pessoaVwService.findAll(this.filtroPessoa);

		for (PessoaVw pessoaVw : pessoas) {
			pessoaVw.setDependentes(dependenciaPessoaService.findAllDependentes(pessoaVw));
		}

		return pessoas;
	}

	public TipoPessoaEnum[] getTiposPessoas() {
		return TipoPessoaEnum.values();
	}

	public void limparMunicipioFiltro() {
		this.filtroPessoa.setMunicipio(null);
	}

	public void limparFiltro() {
		this.filtroPessoa = new FiltroPessoaDTO();
	}

	public void buscar() {
		listarPessoas();
	}

	public FiltroPessoaDTO getFiltroPessoa() {
		return filtroPessoa;
	}

	public void setFiltroPessoa(FiltroPessoaDTO filtroPessoa) {
		this.filtroPessoa = filtroPessoa;
	}

	public LazyDataModel<PessoaVw> getPessoas() {
		return pessoas;
	}

	public void setPessoas(LazyDataModel<PessoaVw> pessoas) {
		this.pessoas = pessoas;
	}

}
