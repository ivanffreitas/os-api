package com.ivanilson.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanilson.os.domain.Pessoa;
import com.ivanilson.os.domain.Tecnico;
import com.ivanilson.os.dtos.TecnicoDTO;
import com.ivanilson.os.repositories.PessoaRepository;
import com.ivanilson.os.repositories.TecnicoRepository;
import com.ivanilson.os.services.exceptions.DataIntegratyViolationException;
import com.ivanilson.os.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	// retornar dados do banco do Tecnico
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
	}

	// retorna uma lista de tecnico com todos os tecnico
	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	// INSERIR UM NOVO TECNICO NO BANCO DE DADOS
	public Tecnico create(TecnicoDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	// ATUALIZAR O TECINO NA BASE DE DADOS
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Técnico possui Ordens de Serviço, não pode ser deletado");
		}
		repository.deleteById(id);
	}

	// VERIFICAR SE EXISTE JÁ EXISTE O CPF NA BASE DA DADOS E SE ELE É VALIDO
	private Pessoa findByCPF(TecnicoDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
