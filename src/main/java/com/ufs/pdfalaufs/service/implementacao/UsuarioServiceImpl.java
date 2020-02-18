package com.ufs.pdfalaufs.service.implementacao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ufs.pdfalaufs.model.entity.Usuario;
import com.ufs.pdfalaufs.model.repository.UsuarioRepository;
import com.ufs.pdfalaufs.service.UsuarioService;
import com.ufs.pdfalaufs.service.exception.ErroAutenticacao;
import com.ufs.pdfalaufs.service.exception.RegraNegocioException;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository repository;
	
	//alterado da video aula, TIRAR SE DA PEOBLEMA
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		//Verifica se o usario esta no sistema
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario nao encontrado para o email informado");
		}
		
		
		//verifica se a senha do usuario confere
		boolean autenticacao = BCrypt.checkpw(senha ,usuario.get().getSenha());
		
		if(autenticacao == true ) return usuario.get();
		else throw new ErroAutenticacao("Senha invalida");
	
		
	}

	@Override
	//criar uma transacao na base de dados executa o metodo de salvar o usuario e dps commita
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) throws RegraNegocioException {
		validarEmail(usuario.getEmail());
		verificaCpfValido(usuario.getCpf());
		validarCpf(usuario.getCpf());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) throws RegraNegocioException {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Ja existe um usuario cadastrado com este email");
		}
	}
	
	@Override
	public void validarCpf(String cpf) throws RegraNegocioException {
		boolean existe = repository.existsByCpf(cpf);
		if (existe) {
			throw new RegraNegocioException("Ja existe um usario cadastrado com este CPF");
		}
	}
	
	@Override
	public void verificaCpfValido(String cpf) throws RegraNegocioException {
		//CODIGO https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097 autor do artigo Romero.
			// considera-se erro CPF's formados por uma sequencia de numeros iguais
			if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
					|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
					|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
					|| cpf.equals("99999999999") || (cpf.length() != 11))
					throw new RegraNegocioException("CPF invalido");
				

			char dig10, dig11;
			int soma, verificador, transformador, num, peso;

			// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
			try {
				// Calculo do 1o. Digito Verificador
				soma = 0;
				peso = 10;
				for (verificador = 0; verificador < 9; verificador++) {
					// converte o i-esimo caractere do CPF em um numero:
					// por exemplo, transforma o caractere '0' no inteiro 0
					// (48 eh a posicao de '0' na tabela ASCII)
					num = (int) (cpf.charAt(verificador) - 48);
					soma = soma + (num * peso);
					peso -= 1;
				}

				transformador = 11 - (soma % 11);
				if ((transformador == 10) || (transformador == 11))
					dig10 = '0';
				else
					dig10 = (char) (transformador + 48); // converte no respectivo caractere numerico

				// Calculo do 2o. Digito Verificador
				soma = 0;
				peso = 11;
				for (verificador = 0; verificador < 10; verificador++) {
					num = (int) (cpf.charAt(verificador) - 48);
					soma = soma + (num * peso);
					peso = peso - 1;
				}

				transformador = 11 - (soma % 11);
				if ((transformador == 10) || (transformador == 11))
					dig11 = '0';
				else
					dig11 = (char) (transformador + 48);

				// Verifica se os digitos calculados conferem com os digitos informados.
				if ((dig10 != cpf.charAt(9)) && (dig11 != cpf.charAt(10)))
						throw new RegraNegocioException("CPF invalido");
			} catch (InputMismatchException erro) {
				throw new RegraNegocioException("CPF invalido");

			}

	}

	@Override
	public Optional<Usuario> obterPorId(UUID id) {
		return repository.findByIdUsuario(id);
	}

	@Override
	public Usuario obterProEmail(String email) {
		return repository.findByEmail(email).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return repository.findAll();
	}

}
