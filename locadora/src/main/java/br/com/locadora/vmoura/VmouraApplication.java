package br.com.locadora.vmoura;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.webapp.FacesServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.locadora.vmoura.dominio.entidade.Cliente;
import br.com.locadora.vmoura.dominio.entidade.Endereco;
import br.com.locadora.vmoura.dominio.entidade.ItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoItemAdicional;
import br.com.locadora.vmoura.dominio.entidade.TipoVeiculo;
import br.com.locadora.vmoura.dominio.entidade.Veiculo;
import br.com.locadora.vmoura.dominio.repositorio.ClienteRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.ItemAdicionalRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.TipoItemAdicionalRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.TipoVeiculoRepositorio;
import br.com.locadora.vmoura.dominio.repositorio.VeiculoRepositorio;
import br.com.locadora.vmoura.util.DataUtil;

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
	@Autowired
	private ItemAdicionalRepositorio itemAdicionalRepositorio;

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
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		criarDadosCliente("Itanauã Franklin", "6189824", "05518587457", "10/03/1985", 
				"itanaua@gmail.com", "(81)998959800", "Rua Parnamimrim", "304", "Apt 603", 
				"Artur Lundgren", "Paulista", "PE", "53417-470");
		
		criarDadosCliente("Maria José", "9856734", "76412364945", "20/11/1988", 
				"maria.jose@gmail.com", "(81)993567688", "Rua Ceará", "120", null, 
				"Paratibe", "Paulista", "PE", "53320-340");
		
		criarDadosCliente("Paulo da Silva", "8374692", "63398723478", "12/04/1980", 
				"paulo.silva@gmail.com", "(81)993567620", "Rua Piaui", "20", null, 
				"Jardim Brasil", "Olinda", "PE", "53290-100");
		
		TipoVeiculo economicoAr = criarTipoVeiculo("Ecômico com AR", 85.00, 15, 
				"4 portas, Manual, Ar condicionado, Direção hidraulica, vidro e trava elétrica");
		
		criarTipoVeiculo("Intermediário automático", 120.00, 12, 
				"4 portas, Automático, Direção hidraulica, vidro e trava elétrica.");
		
		TipoVeiculo suv = criarTipoVeiculo("SUV", 190.00, 8, 
				"4 portas, Automático, Direção elética, vidro e trava elétrica, 3 malas grandes");
		
		criarTipoVeiculo("Minivan", 170.00, 5, 
				"4 portas, Manual, Direção hidraulica, vidro e trava elétrica, 7 pessoas");
		
		TipoItemAdicional tipoItemBebeConforto = 
				criarTipoItemAdicional("Bebê conforto", 15.0, 5, "Crianças de 0 a 1 ano.");
		
		TipoItemAdicional tipoItemGPS = 
				criarTipoItemAdicional("GPS", 20.0, 10, 
						"GPS para traçar sua rota para chegar ao ​destino de forma mais rápida e segura.​");
		
		criarItemAdicional("Galzerano Transbaby", "Preto", "Até 25kg", tipoItemBebeConforto, 
						"Possui protetor de ombro e proteção lateral, capota retrátil, "
						+ "possui 3 posições de declínio e 3 regulagens de altura do "
						+ "cinto de segurança, além de 5 pontos de segurança.");
		
		criarItemAdicional("Multilaser", "Cinza", "7 polegadas", tipoItemGPS,
				"GPS automotivo Trckeer III, touch screen tv digital, USB.");
		
		criarVeiculo("KME5810", 2012, "2013", "DK3DJ8", 
				"Fiat", "Pálio", "Branco", "Gasolina", economicoAr);
		
		criarVeiculo("NXU9217", 2014, "2014", "TY7SL9", 
				"Hyundai", "Tucson", "Prata", "Flex", suv);
		
	}

	private ItemAdicional criarItemAdicional(String marca, String cor, String tamanho, 
			TipoItemAdicional tipoItemAdicional, String especificacao) {
		ItemAdicional item = new ItemAdicional();
		item.setMarca(marca);
		item.setCor(cor);
		item.setTamanho(tamanho);
		item.setEspecificacao(especificacao);
		item.setTipoItemAdicional(tipoItemAdicional);
		item.setDataHoraAtualizacao(new Date());
		item.setExcluido(false);
		itemAdicionalRepositorio.save(item);
		return item;
	}

	private TipoItemAdicional criarTipoItemAdicional(String nome, 
			Double valorDiario, Integer qtdeTotal, String descricao) {
		TipoItemAdicional tipo = new TipoItemAdicional();
		tipo.setNome(nome);
		tipo.setDescricao(descricao);
		tipo.setValorDiario(valorDiario);
		tipo.setQuantidadeTotal(qtdeTotal);
		tipo.setDataHoraAtualizacao(new Date());
		tipo.setExcluido(false);
		tipoItemAdicionalRepositorio.save(tipo);
		return tipo;
	}

	private Veiculo criarVeiculo(String placa, Integer anoFabricacao, String anoModelo, String chassi, 
			String fabricante, String modelo, String cor, String tipoCombustivel, TipoVeiculo tipoVeiculo) {
		Veiculo veiculo = new Veiculo();
		veiculo.setPlaca(placa);
		veiculo.setAnoFabricacao(anoFabricacao);
		veiculo.setAnoModelo(anoModelo);
		veiculo.setChassi(chassi);
		veiculo.setFabricante(fabricante);
		veiculo.setModelo(modelo);
		veiculo.setCor(cor);
		veiculo.setTipoCombustivel(tipoCombustivel);
		veiculo.setTipoVeiculo(tipoVeiculo);
		veiculo.setDataHoraAtualizacao(new Date());
		veiculo.setExcluido(false);
		veiculoRepositorio.save(veiculo);
		return veiculo;
	}

	private TipoVeiculo criarTipoVeiculo(String nome, Double valorDiario, Integer qdteTotal, String descricao) {
		TipoVeiculo tipo = new TipoVeiculo();
		tipo.setNome(nome);
		tipo.setDescricao(descricao);
		tipo.setValorDiario(valorDiario);
		tipo.setQuantidadeTotal(qdteTotal);
		tipo.setDataHoraAtualizacao(new Date());
		tipo.setExcluido(false);
		tipoVeiculoRepositorio.save(tipo);
		return tipo;
	}

	private Cliente criarDadosCliente(String nome, String rg, String cpf, String dataNascimento, String email,
			String telefone, String logradouro, String numero, String complemento, String bairro, String cidade, 
			String estado, String cep) {
		Endereco endereco = new Endereco();
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setComplemento(complemento);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setCep(cep);
		endereco.setDataHoraAtualizacao(new Date());
		endereco.setExcluido(false);
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setRg(rg);
		cliente.setCpf(cpf);
		cliente.setDataNascimento(DataUtil.formataData(dataNascimento));
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setDataHoraAtualizacao(new Date());
		cliente.setEndereco(endereco);
		cliente.setExcluido(false);
		clienteRepositorio.save(cliente);
		return cliente;
	}
	
}
