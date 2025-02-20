package minegroup.example.CEP_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    System.out.println("Endereço encontrado: " + response.getBody());
                } else {
                    System.out.println("Erro: Endereço não encontrado para o CEP informado.");
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
