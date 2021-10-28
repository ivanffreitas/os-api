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

		Tecnico t1 = new Tecnico(null, "Ivanilson", "847.165.370-21", "(81)99461-3027");
		Tecnico t2 = new Tecnico(null, "Amanda", "520.803.140-09", "(81)99461-3400");
		Tecnico t3 = new Tecnico(null, "Arthur", "626.506.870-96", "(81)99461-3400");
		Tecnico t4 = new Tecnico(null, "Igor", "276.100.940-13", "(81)99001-3427");
		Tecnico t5 = new Tecnico(null, "João", "518.274.490-04", "(81)99461-3400");

		Cliente c1 = new Cliente(null, "Amanda Natally", "609.292.430-72", "(81)99000-6141");
		Cliente c2 = new Cliente(null, "João Eduardo", "810.497.930-21", "(81)99001-6141");
		Cliente c3 = new Cliente(null, "Carlos Ferreira", "334.811.540-03", "(81)99141-6141");
		Cliente c4 = new Cliente(null, "Maria Vitoria", "164.690.710-82", "(81)90000-6001");
		Cliente c5 = new Cliente(null, "Perez Martins", "498.213.780-30", "(81)90000-6141");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS 1", Status.ANDAMENTO, t1, c1);
		OS os2 = new OS(null, Prioridade.BAIXA, "Teste create OS 2", Status.ANDAMENTO, t2, c2);
		OS os3 = new OS(null, Prioridade.MEDIA, "Teste create OS 3", Status.ANDAMENTO, t3, c3);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));
		clienteRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		osRepository.saveAll(Arrays.asList(os1, os2, os3));

	}

}
