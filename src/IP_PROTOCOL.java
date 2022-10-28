// ======= IP_PROTOCOL ==============================================================

import java.util.LinkedList;

public class IP_PROTOCOL {

//    private LinkedList<String> listIp = new LinkedList<String>();
//
//    private String stringInitIp;
//    private String stringFinalIp;
//
//    private long longInitIp;
//    private long longFinalIp;
//
//    private String mask;
//    private int quantHosts;
//
//    public IP_PROTOCOL(String networkAddress) {
//        // Split endereçoRede e seta atributos
//        stringInitIp = getSplitedIp(networkAddress);
//        mask = getSplitedMask(networkAddress);
//
//
//    }

    // Funções que retornam ou o IP ou a Mascara
    private String getSplitedIp(String netAddress){
        String ip = netAddress.split("/")[0];
        return ip;
    }

    private String getSplitedMask(String netAddress){
        String _mask = netAddress.split("/")[1];
        return _mask;
    }


    // 1° Questão
    public void ipRange(String networkAddress) {
        LinkedList<String> listIp = new LinkedList<String>();

        String stringInitIp;
        String stringFinalIp;

        long longInitIp;
        long longFinalIp;

        String mask;
        int quantHosts;

        // Split endereçoRede e seta atributos
        stringInitIp = getSplitedIp(networkAddress);
        mask = getSplitedMask(networkAddress);


        // Transforma a String IP em uma LinkedList de String contendo os bits de cada numero
        listIp = linkedListIp(stringInitIp);

        // Transforma a string de binarios no long correspondente
        longInitIp = binaryToLong(listIp);


        // Pega a quantidade de Hosts da mascara de subrede
        quantHosts = getNumberOfHostsAllowed(mask);
        System.out.println("Quantidade de Hosts: " + quantHosts);


        // Soma o long do IP à quantHosts para saber o longFinal, que é o range do IP
        longFinalIp = longInitIp + quantHosts;


        //Agr basta transformar o numero longFinal(long) em Ip (String)
        stringFinalIp = longToIpAddressString(longFinalIp);

        System.out.println("Range: "+ stringInitIp + " ~~~ "+ stringFinalIp +"\n");
    }

    // 2° Questão
    public void getSubnetNeeded(String enderecoRede , int n){
        LinkedList<String> listIp = new LinkedList<String>();
        LinkedList<String> subnetList = new LinkedList<String>();

        listIp = linkedListIp( getSplitedIp(enderecoRede) );
        System.out.println("listIp " + listIp);

        String mask = getSplitedMask( enderecoRede );

        int result = (int) Math.ceil ( Math.log(n) / Math.log(2) );
        int quantHosts = getNumberOfHostsAllowed(mask);

        long longInitIp = binaryToLong( listIp );
        System.out.println("lonInitIp: "+ longInitIp);

        System.out.println("Bits necessarios para N hosts: " + result);



        // Mascara de subrede + resultado
        int newSubnet = Integer.parseInt(mask) + result;

        int numberNewHosts = getNumberOfHostsAllowed( Integer.toString(newSubnet)  );

        if(  n * numberNewHosts <= quantHosts){
            System.out.println("Quantidade de Hosts ("+quantHosts+") maior/igual que quantidade de novos hosts("+ (n * numberNewHosts) +")");

            long longIP = longInitIp;

            System.out.println("Novas Subredes: ");

            for (int i = 0; i < n; i++) {
                subnetList.add( longToIpAddressString(longIP + (i * 32)) +"/"+ newSubnet );
                System.out.println( subnetList.get(i) );
            }
        }else{
            System.out.println("Quantidade de Hosts ("+ quantHosts +") menor que quantidade de novos hosts("+ (n * numberNewHosts) +")");
            System.out.println("Não há como gerar subredes");
        }

        System.out.println("");
    }

    // 3° Questão
    public boolean check_if_ip_belongs_to_network(String netAddress, String ipAddress) {
        LinkedList<String> listIpAddress = new LinkedList<String>();
        long longIpAddress = 0;

        LinkedList<String> listNetAddress = new LinkedList<String>();
        long longInitNetAddress = 0;
        long longFinalNetAddress = 0;
        String ipNetAddress = getSplitedIp(netAddress);
        String maskNetAddress = getSplitedMask(netAddress);
        int range = 0;
        int nHosts = 0;


        // Retorta o long do ipAddress
        listIpAddress = linkedListIp(ipAddress);
        longIpAddress = binaryToLong(listIpAddress);

        // Retorta o long do netAddress
        listNetAddress = linkedListIp(ipNetAddress);
        longInitNetAddress = binaryToLong(listNetAddress);
        range = getNumberOfHostsAllowed(maskNetAddress);
        longFinalNetAddress = longInitNetAddress + range;
        nHosts = getNumberOfHostsAllowed( maskNetAddress );

//        System.out.println("IP e REDE testadas: ");
//        System.out.println("IP: " + ipAddress + " REDE: " + netAddress);
//        System.out.println("Numero de Hosts: " + nHosts);
//        System.out.println("Long Init Address: " + longInitNetAddress);
//        System.out.println("Long Ip Address:   " + longIpAddress);
//        System.out.println("Long Net Address:  " + longFinalNetAddress);
//        System.out.println();

        if ((longIpAddress >= longInitNetAddress) && (longIpAddress <= longFinalNetAddress)) {
            System.out.println("Esse IP: "+ ipAddress +" pertence a REDE: "+ netAddress +". \n");
            return true;
        } else {
            System.out.println("Esse IP: "+ ipAddress +" NAO pertence a REDE: "+ netAddress +". \n");
            return false;
        }
    }

    // 4° Questão
    public void checks_if_ip_belongs_to_subnet(LinkedList<String> subnets, String ip){
        String subnetIp = "";
        String ip32bits = "";
        String longestPrefix = "";
        int countLongestPrefix = 0;
        int mostEspecificNet = 0;

        ip32bits =  return32BitString(ip);

        for(String s : subnets){

            // Se IP pertencer a Subrede entao executa o codigo
            if( check_if_ip_belongs_to_network(s, ip) ) {

                // Retorna uma String no formato de IP ( Extrai somente o IP do endereço de rede )
                subnetIp = getSplitedIp(s);

                // Passa uma String no formato IP e retorna um String no formato 32bits
                subnetIp = return32BitString(subnetIp);

                // Checa o maior prefixo e mascara
                int sizeIp32bits = ip32bits.length();
                int count = 0;

                // Retorno em int da Mask da variavel "s"
                int maskSpecificity = Integer.parseInt( getSplitedMask(s) );



                // Compara os 32 bits do IP e compara se os bits são iguais
                // Para cada bit igual adiciona no contador
                for (int i = 0; i < sizeIp32bits; i++) {

                    char bitIp32bits = ip32bits.charAt(i);
                    char bitSubnetIp = subnetIp.charAt(i);

                    if (bitIp32bits == bitSubnetIp) {
                        count +=1;
                    }
                }


                if(count > countLongestPrefix ){
                    countLongestPrefix = count;
                    longestPrefix = s;
                }
            }

        }

        System.out.println("Longest Prefix: " + longestPrefix);

    }



    // Recebe um IP em String -> Faz sua formatação -> Transforma cada elemento em binario ->
    // Adiciona o elemento na linkedList ->
    // Chama a função completeLeftZeros para completar a LinkedList em 8 bits completando os 0 a esquerda faltando
    private LinkedList<String> linkedListIp(String x) {
        String[] octs = x.split("\\.");
        LinkedList<String> list = new LinkedList<String>();

        for (String oct : octs) {
            String l = "";

            l = toBinaryString(oct);
            list.add(l);
        }

        // completa os zeros a esquerda faltando nos elementos da LinkedList e retorna-o
        return completeLeftZeroes(list);
    }

    private LinkedList<String> completeLeftZeroes(LinkedList<String> x) {
        for (String s : x) {
            String newString = s;

            if (newString.length() < 8) {
                int tamanho = newString.length();

                while (tamanho < 8) {
                    newString = "0" + newString;
                    tamanho++;
                }

                x.set(x.indexOf(s), newString);
            }
        }

        return x;
    }



    private String toBinaryString(String x) {
        long l = Long.parseLong(x);
        String result = Long.toBinaryString(l);
        return result;
    }



    // Recede uma LinkedList de String e retorna o valor Long correspondente
    private Long binaryToLong(LinkedList<String> x) {
        String longString = "";
        long l = 0;

        // Retorna uma String de Binarios de 32Bits
        longString = getBinaryStringIp(x);


        int size = longString.length();

        for (int i = 0; i < size; i++) {
            int position = size - i - 1;

            char bit = longString.charAt(i);

            if (bit == '1') {
                l += Math.pow(2, position);
            }
        }

        return l;
    }



    // Recebe uma LinkedList de String Contendo os 4Bytes completos
    // Transforma e retorna em uma String de binarios de 32bits
    private String getBinaryStringIp(LinkedList<String> x){
        String binaryString = "";
        for (String s : x) {
            binaryString += s;
        }

        return binaryString;
    }



    private Integer getNumberOfHostsAllowed(String mask) {
        int quant = 0;

        // Seta a String de mascara de subrede
        String netMask = "";
        for (int i = 0; i < 32; i++) {
            if (i >= Long.parseLong(mask)) {
                netMask += "0";
                quant += 1;
            } else {
                netMask += "1";
            }
        }

        quant = (int) Math.pow(2, quant);

        // System.out.println("Subnet Maks: " + netMask);

        return quant;
    }



    private String longToIpAddressString(Long longIp) {
        String ip = "";
        ip += ((longIp & 0b11111111000000000000000000000000) >> 24) + ".";
        ip += ((longIp & 0b111111110000000000000000) >> 16) + ".";
        ip += ((longIp & 0b1111111100000000) >> 8) + ".";
        ip += (longIp & 0b11111111);

        return ip;
    }



    // Retorna a String de 32bits
    private String return32BitString(String s){
        String bit32String = "";

        // Cria uma LinkedList para pegar os 4Bytes da string de IP
        LinkedList<String> ipLinkedList = new LinkedList<String>();

        ipLinkedList = linkedListIp(s);

        // Retorna uma String de binario em 32bits
        bit32String = getBinaryStringIp(ipLinkedList);


        return bit32String;
   }






}