import java.nio.charset.StandardCharsets;
import java.util.LinkedList;


//Crie um sistema, em qualquer linguagem de programação, que seja capaz de fazer as seguintes operações:
//    1 - Dado um endereço de rede, calcular o primeiro e o último endereço de IP.
//    2 - Dado um endereço de rede e um valor N, indicar todas as faixas de IP para todas as subredes necessárias para endereçar N subredes.
//    3 - Dado um endereço IP e um endereço de rede, indicar se o endereço pertence à rede indicada.
//    4 - Considerando uma lista de endereços de rede e dado um endereço IP,
//        indicar qual endereço de rede contido na lista é 'dono' do endereço IP recebido. A resposta deve ser a subrede mais específica.


public class Main {
    public static void main(String[] args) {

        // Variaveis para a 1° questao
        String enderecoRede = "192.168.0.0/24";


        // Variaveis para a 2° questao
        int numbersOfSubnet = 5;

        // Variaveis para a 3° questao
        String givenIpAddress = "192.168.0.94";

        // Variaveis para a 4° questao
        LinkedList<String> enderecosDeRede = new LinkedList<String>();
        enderecosDeRede.add("192.168.2.78/24");
        enderecosDeRede.add("192.168.0.58/27");
        enderecosDeRede.add("192.168.0.0/22");
        enderecosDeRede.add("192.168.0.94/14");


        IP_PROTOCOL ip_protocol = new IP_PROTOCOL( );

        // Primeira questão
        System.out.println("1° Questao");
        ip_protocol.ipRange( enderecoRede );

        //Segunda questão
        System.out.println("2° Questao");
        ip_protocol.getSubnetNeeded( enderecoRede , numbersOfSubnet);

        // Terceira questão
        System.out.println("3° Questao");
        ip_protocol.check_if_ip_belongs_to_network( enderecoRede, givenIpAddress );

        // Quarta questão
        System.out.println("4° Questao");
        ip_protocol.checks_if_ip_belongs_to_subnet(enderecosDeRede, givenIpAddress);
    }
}
