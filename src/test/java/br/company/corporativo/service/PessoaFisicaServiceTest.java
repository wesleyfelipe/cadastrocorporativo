package br.company.corporativo.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.company.corporativo.entity.DependenciaPessoa;
import br.company.corporativo.entity.PessoaFisica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.repository.PessoaFisicaRepository;

@RunWith(MockitoJUnitRunner.class)
public class PessoaFisicaServiceTest {
	
	@Spy
	private PessoaFisicaRepository pessoaFisicaRepository;

	@InjectMocks
	private PessoaFisicaService pessoaFisicaService;

	@Test
	public void delete_ok() {
		PessoaVw pessoa = new PessoaVw();
		pessoa.setId(1L);
		PessoaFisica pf = new PessoaFisica();
		
		Mockito.doReturn(pf).when(pessoaFisicaRepository).findOne(pessoa.getId());
		Mockito.doNothing().when(pessoaFisicaRepository).delete(pf);
		
		pessoaFisicaService.delete(pessoa);
		
		Mockito.verify(pessoaFisicaRepository, Mockito.times(1)).delete(pf);
	}
	
	@Test
	public void delete_entidadeNaoEncontrada() {
		PessoaVw pessoa = new PessoaVw();
		pessoa.setId(1L);
		PessoaFisica pf = null;
		
		Mockito.doReturn(pf).when(pessoaFisicaRepository).findOne(pessoa.getId());
		Mockito.doNothing().when(pessoaFisicaRepository).delete(pf);
		
		pessoaFisicaService.delete(pessoa);
		
		Mockito.verify(pessoaFisicaRepository, Mockito.times(0)).delete(pf);
	}
	
	@Test
	public void addDependente() {
		
		PessoaFisica pf = new PessoaFisica();
		DependenciaPessoa dep = new DependenciaPessoa();
		
		pessoaFisicaService.addDependente(pf, dep);
		
		Assert.assertEquals(pf.getDependentes().size(), 1);
	}
	
	@Test
	public void removeDependente() {
		PessoaFisica pf = new PessoaFisica();
		DependenciaPessoa dep = new DependenciaPessoa();
		
		pf.getDependentes().add(dep);
		
		pessoaFisicaService.removeDependente(pf, dep);
		
		Assert.assertEquals(pf.getDependentes().size(), 0);
	}

}
