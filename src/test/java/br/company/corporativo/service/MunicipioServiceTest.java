package br.company.corporativo.service;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.company.corporativo.entity.UnidadeFederativa;
import br.company.corporativo.repository.MunicipioRepository;

@RunWith(MockitoJUnitRunner.class)
public class MunicipioServiceTest {

	@Spy
	private MunicipioRepository municipioRepository;

	@InjectMocks
	private MunicipioService municipioService;

	@Test
	public void findAll_ufNull() {
		municipioService.findAll(null);

		Mockito.verify(municipioRepository, Mockito.times(0)).findAll(null);
	}
	
	@Test
	public void findAll() {
		UnidadeFederativa uf = new UnidadeFederativa();
		
		Mockito.doReturn(new ArrayList<>()).when(municipioRepository).findAll(uf);
		
		municipioService.findAll(uf);

		Mockito.verify(municipioRepository, Mockito.times(1)).findAll(uf);
	}

}
