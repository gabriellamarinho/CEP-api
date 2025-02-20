package minegroup.example.CEP_api;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.Scanner;

public class EnderecoController {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Olá, gostaria de verificar um endereço com base no cep? (sim/não): ");
        String receiveCep = scan.next().trim();

        if (receiveCep.equalsIgnoreCase("sim")) {
            System.out.print("Por favor, informe o CEP sem o traço: ");
            String cep = scan.next().trim();

            // Verifica se o CEP tem exatamente 8 dígitos numéricos
            if (!cep.matches("\\d{8}")) {
                System.out.println("Erro: O CEP deve conter exatamente 8 dígitos numéricos.");
                return;
            }

            // Chama a API para obter o endereço
            RestTemplate restTemplate = new RestTemplate();
            String url = VIA_CEP_URL.replace("{cep}", cep);

            try {
                // Fazendo a requisição e mapeando o retorno diretamente para o objeto Endereco
                ResponseEntity<Endereco> response = restTemplate.getForEntity(url, Endereco.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    Endereco endereco = response.getBody();
                    if (endereco != null && endereco.getErro() == null) {
                        System.out.println("Endereço encontrado:");
                        System.out.println("Logradouro: " + endereco.getLogradouro());
                        System.out.println("Bairro: " + endereco.getBairro());
                        System.out.println("Cidade: " + endereco.getLocalidade());
                        System.out.println("Estado: "+endereco.getEstado());
                        System.out.println("Região: "+endereco.getRegiao());
                        System.out.println("DDD: "+endereco.getDdd());
                        System.out.println("UF: " + endereco.getUf());
                    } else {
                        System.out.println("Erro: Endereço não encontrado para o CEP informado.");
                    }
                } else {
                    System.out.println("Erro ao buscar o endereço.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao chamar a API: " + e.getMessage());
            }
        } else {
            System.out.println("Ok, até logo!");
        }

        scan.close();
    }
}
