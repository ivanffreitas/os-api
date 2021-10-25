package com.ivanilson.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanilson.os.domain.Cliente;
import com.ivanilson.os.domain.OS;
import com.ivanilson.os.domain.Tecnico;
import com.ivanilson.os.domain.enuns.Prioridade;
import com.ivanilson.os.domain.enuns.Status;
import com.ivanilson.os.repositories.ClienteRepository;
import com.ivanilson.os.repositories.OSRepository;
import com.ivanilson.os.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;
	
	public void instaciaDB() {
		
		Tecnico t1 = new Tecnico(null,"Ivanilson","101.867.804-27","(81)99461-3227");
		Tecnico t2 = new Tecnico(null,"Arthur","520.803.140-09","(81)99461-3427");
		Cliente c1 = new Cliente(null, "Amanda Natally", "420.322.810-74", "(81)99141-6141");
		
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);		
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
		
	}
	
}
