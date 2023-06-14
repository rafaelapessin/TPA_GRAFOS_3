// RAFAELA AMORIM PESSIN
// TPA 2023/1
// GRAFOS - PARTE 1

package util;

import java.util.Scanner;

// Classe para ser chamada na Main toda vez que precisava solicitar uma entrada de dados do usuário
public class Leitor {
    private static Scanner scanner = new Scanner(System.in);
    private Leitor(){}
    public static Scanner getLeitor(){
        return scanner;
    }
}