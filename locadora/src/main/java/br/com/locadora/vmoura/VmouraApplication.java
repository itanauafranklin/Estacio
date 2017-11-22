package br.com.locadora.vmoura;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Endereco;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.entidade.Veiculo;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.TipoItemAdicionalRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.TipoVeiculoRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.VeiculoRepositorio;

@SpringBootApplication
public class VmouraApplication {
	
	@Autowired
	private TipoVeiculoRepositorio tipoVeiculoRepositorio;
	@Autowired
	private VeiculoRepositorio veiculoRepositorio;
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private TipoItemAdicionalRepositorio tipoItemAdicionalRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(VmouraApplication.class, args);
	}
	
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.jsf");
    }
	
	@PostConstruct
	public void init(){
		// init code goes here
		TipoVeiculo tipo = new TipoVeiculo();
		tipo.setNome("Econômico sem ar");
		tipo.setDescricao("Descrição");
		tipo.setValorDiario(150.0);
		tipo.setQuantidadeTotal(10);
		tipo.setDataHoraAtualizacao(new Date());
		tipoVeiculoRepositorio.save(tipo);
		
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca("PUW7782");
		veiculo.setAnoFabricacao(2014);
		veiculo.setAnoModelo("2014/2014");
		veiculo.setChassi("EQWE123231");
		veiculo.setFabricante("VW");
		veiculo.setModelo("FOX");
		veiculo.setCor("Preto");
		veiculo.setTipoCombustivel("Flex");
		veiculo.setTipoVeiculo(tipo);
		veiculo.setDataHoraAtualizacao(new Date());
		veiculoRepositorio.save(veiculo);
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro("Rua do espinheiro");
		endereco.setNumero("201");
		endereco.setComplemento("apto 202");
		endereco.setCep("50710-190");
		endereco.setBairro("Graças");
		endereco.setCidade("Recife");
		endereco.setEstado("PE");
		endereco.setDataHoraAtualizacao(new Date());
		Cliente cliente = new Cliente();
		cliente.setCpf("000.000.000-00");
		cliente.setTelefone("(81)99999-9999");
		cliente.setRg("0000000");
		cliente.setNome("Fulano da Silva");
		cliente.setEmail("fulano@gmail.com");
		cliente.setDataNascimento(new Date());
		cliente.setDataHoraAtualizacao(new Date());
		cliente.setEndereco(endereco);
		clienteRepositorio.save(cliente);
		
		TipoItemAdicional tipo1 = new TipoItemAdicional();
		tipo1.setNome("Cadeira de bebê");
		tipo1.setDescricao("Cadeira de bebê");
		tipo1.setQuantidadeTotal(5);
		tipo1.setValorDiario(15.0);
		tipo1.setDataHoraAtualizacao(new Date());
		tipoItemAdicionalRepositorio.save(tipo1);
		
		TipoItemAdicional tipo2 = new TipoItemAdicional();
		tipo2.setNome("GPS");
		tipo2.setDescricao("GPS");
		tipo2.setQuantidadeTotal(5);
		tipo2.setValorDiario(15.0);
		tipo2.setDataHoraAtualizacao(new Date());
		tipoItemAdicionalRepositorio.save(tipo2);
	}
	
}
