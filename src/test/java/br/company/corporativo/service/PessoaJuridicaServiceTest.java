package br.company.corporativo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.company.corporativo.entity.PessoaJuridica;
import br.company.corporativo.entity.vw.PessoaVw;
import br.company.corporativo.repository.PessoaJuridicaRepository;

@RunWith(MockitoJUnitRunner.class)
public class PessoaJuridicaServiceTest {
	
	@Spy
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@InjectMocks
	private PessoaJuridicaService pessoaJuridicaService;

	@Test
	public void delete_ok() {
		PessoaVw pessoa = new PessoaVw();
		pessoa.setId(1L);
		PessoaJuridica pj = new PessoaJuridica();
		
		Mockito.doReturn(pj).when(pessoaJuridicaRepository).findOne(pessoa.getId());
		Mockito.doNothing().when(pessoaJuridicaRepository).delete(pj);
		
		pessoaJuridicaService.delete(pessoa);
		
		Mockito.verify(pessoaJuridicaRepository, Mockito.times(1)).delete(pj);
	}
	
	@Test
	public void delete_entidadeNaoEncontrada() {
		PessoaVw pessoa = new PessoaVw();
		pessoa.setId(1L);
		PessoaJuridica pj = null;
		
		Mockito.doReturn(pj).when(pessoaJuridicaRepository).findOne(pessoa.getId());
		Mockito.doNothing().when(pessoaJuridicaRepository).delete(pj);
		
		pessoaJuridicaService.delete(pessoa);
		
		Mockito.verify(pessoaJuridicaRepository, Mockito.times(0)).delete(pj);
	}

}
