package com.ivanilson.os.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivanilson.os.domain.Cliente;
import com.ivanilson.os.domain.Pessoa;
import com.ivanilson.os.dtos.ClienteDTO;
import com.ivanilson.os.repositories.ClienteRepository;
import com.ivanilson.os.repositories.PessoaRepository;
import com.ivanilson.os.services.exceptions.DataIntegratyViolationException;
import com.ivanilson.os.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	// RETORNAR DADOS DO BANCO - CLIENTE
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	// RETORNA UMA LISTA DE CLIENTES
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// INSERIR UM NOVO CLIENTE NO BANCO DE DADOS
	public Cliente create(ClienteDTO objDTO) {
		if (findByCPF(objDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
	}

	// ATUALIZAR O CLIENTE NA BASE DADOS
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldObj = findById(id);

		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}

		oldObj.setNome(objDTO.getNome());
		oldObj.setCpf(objDTO.getCpf());
		oldObj.setTelefone(objDTO.getTelefone());

		return repository.save(oldObj);
	}

	// DELETAR CLIENTE DA BASE DE DADOS
	public void delete(Integer id) {
		Cliente obj = findById(id);

		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Ordens de Servico, não pode ser deletado");
		}
		repository.deleteById(id);
	}

	// VERIFICAR SE EXISTE JÁ EXISTE O CPF NA BASE DA DADOS E SE ELE É VALIDO
	private Pessoa findByCPF(ClienteDTO objDTO) {
		Pessoa obj = pessoaRepository.findByCPF(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}

}
