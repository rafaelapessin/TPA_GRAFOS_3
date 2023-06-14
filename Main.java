// RAFAELA AMORIM PESSIN
// TPA 2023/1
// GRAFOS - PARTE 1

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import grafo.Cidade;
import grafo.Grafo;
import grafo.Vertice;
import util.Leitor;

public class Main {
    static Grafo<Cidade> grafo = new Grafo<Cidade>();       // cria um novo grafo
    public static void main(String[] args) throws IOException{        
        try {
            // passando o arquivo gerado pelo gerador de arquivos para criar o grafo
            lerGrafo("entrada.txt", grafo);
            
            int selection, codCidade, codOrigem, codDestino;
            Cidade cidade, origem, destino;
            Grafo<Cidade> grafoNovo;

            do {
                printMenu();
                selection = getSelection();
                switch (selection) {
                    // 1 - Obter cidades vizinhas
                    // o usuário deve informar o código de uma cidade e o programa deve exibir:
                    // - os códigos e nomes de todas as cidades vizinhas da cidade informada (vértices adjacentes) 
                    // - a distância da cidade escolhida para cada uma das vizinhas
                    case 1:
                        System.out.println("Informe o código da cidade: ");
                        codCidade = Leitor.getLeitor().nextInt();               // retorna o código da cidade que o usuário informou
                        cidade = verificaSeCidadeExiste(codCidade);
                        if(cidade != null){
                            grafo.obterCidadesVizinhas(new Cidade(codCidade, ""));
                        }
                        else{
                            System.out.println("A cidade informada nao existe.");
                        }
                        break;
                    // 2 - Obter todos os caminhos a partir de uma cidade
                    // o usuário deve informar o código de uma cidade e o programa deve exibir:
                    // - todas as cidades (código e nome) às quais é possível chegar saindo da cidade dada (seria um caminhamento em largura no grafo usando a cidade dada como vértice de origem do caminhamento)
                    case 2:
                        System.out.println("Informe o código da cidade: ");
                        codCidade = Leitor.getLeitor().nextInt();               // retorna o código da cidade que o usuário informou
                        cidade = verificaSeCidadeExiste(codCidade);
                        if(cidade != null){
                            grafo.obterCaminhos(new Cidade(codCidade, ""));
                        }
                        else{
                            System.out.println("A cidade informada nao existe.");
                        }
                        break;
                    // 3- Calcular árvore geradora mínima
                    case 3:
                    System.out.println("Informe o código da cidade de origem: ");
                    codOrigem = Leitor.getLeitor().nextInt();
                    origem = verificaSeCidadeExiste(codOrigem);
                    if(origem != null){
                        grafoNovo = grafo.gerarArvoreGeradoraMinima(origem);
                        if(grafoNovo == null){
                            System.out.println("A origem não está no grafo.");
                        } else {
                            grafoNovo.imprimirArestas();
                        }
                    }else{
                        System.out.println("A cidade informada nao existe.");
                    }                        
                    break;
                    // 4- Calcular caminho mínimo entre duas cidades
                    case 4:
                        System.out.println("Informe o código da cidade de origem: ");
                        codOrigem = Leitor.getLeitor().nextInt();
                        System.out.println("Informe o código da cidade de destino: ");
                        codDestino = Leitor.getLeitor().nextInt();
                        origem = verificaSeCidadeExiste(codOrigem);
                        destino = verificaSeCidadeExiste(codDestino);
                        if(origem != null && destino != null){
                            grafo.calcularCaminhoMinimo_v2(origem, destino);
                        }else{
                            System.out.println("A cidade informada não existe.");
                        }                        
                        break;
                    // 5- Calcular caminho mínimo entre duas cidades considerando apenas a AGM
                    case 5:
                        System.out.println("Informe o código da cidade de origem: ");
                        codOrigem = Leitor.getLeitor().nextInt();
                        System.out.println("Informe o código da cidade de destino: ");
                        codDestino = Leitor.getLeitor().nextInt();
                        origem = verificaSeCidadeExiste(codOrigem);
                        destino = verificaSeCidadeExiste(codDestino);
                        if(origem != null && destino != null){
                            grafo.calcularCaminhoMinimo_v2(origem, destino);
                        }else{
                            System.out.println("A cidade informada não existe.");
                        }                        
                    break;
                    case 6:
                        // Encerra do programa
                        break;
                    default:
                        break;
                }
            } while(selection != 6);
        } catch (IOException e) {
            System.out.println("Erro ao abrir o arquivo");
        }
    }


    // MENU DE OPÇÕES
    private static void printMenu(){
        System.out.println("================= MENU =================");
        System.out.println("1 - Obter cidades vizinhas");
        System.out.println("2 - Obter todos os caminhos a partir de uma cidade");
        System.out.println("3 - Calcular árvore geradora mínima");
        System.out.println("4 - Calcular caminho mínimo entre duas cidades");
        System.out.println("5 - Calcular caminho mínimo entre duas cidades considerando apenas a AGM");
        System.out.println("6 - Sair");
        System.out.println("======================================");
    } 
    
    // Retorna a opção que o usuário escolheu no menu
    private static int getSelection(){
        System.out.println("Escolha uma opção: ");
        return Leitor.getLeitor().nextInt();
    }

    // 
    public static Cidade verificaSeCidadeExiste(int codCidade){
        Vertice<Cidade> vert = grafo.getVertice(new Cidade(codCidade, ""));
        return vert != null ? vert.getValor() : null;
    }

    // Método que vai criar o grafo a partir do arquivo de entrada
    // Faz um laço do tamanho do número de cidades informada no início do arquivo
    // A cada linha lida, cria um vértice e as arestas e adiciona no grafo
    // Um vértice é uma cidade, composta por código e nome
    // Uma aresta é a distância da cidade escolhida para cada uma das cidades vizinhas
    public static void lerGrafo(String path, Grafo<Cidade> grafo) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
	
		int qtdCidades = Integer.parseInt(buffRead.readLine());     // a primeira linha do arquivo tem apenas um número n inteiro indicando a quantidade de cidades

        // cada linha que contém código e cidade será um vértice do grafo
        // vai ler a linha, criar o vértice e adicionar o vértice no grafo
		for(int i = 0; i < qtdCidades; i++){                        
			linha = buffRead.readLine();
            String[] line = linha.split(";");
			int cod = Integer.parseInt(line[0]);
            String cidade = line[1];
            Vertice<Cidade> vertice = new Vertice<Cidade>(new Cidade(cod,cidade));
			grafo.adicionarVertice(vertice);
		}

        // Adiciona a aresta no grafo com a distância, a cidade de origem e a cidade de destino
		for(int i = 1; i < qtdCidades + 1; i++){
            linha = buffRead.readLine();
			if(linha != null){
                String[] line = linha.split(";");
                // faz um for na terceira parte do arquivo, traz os valores indicando a distância entre duas cidades
                for(int k = 0; k < qtdCidades; k++){
                    float distancia = Float.parseFloat((line[k]).replaceAll(",", "."));

                    if(distancia != 0){
                        Cidade origem = new Cidade(i,"");
                        Cidade destino = new Cidade((k+1),"");
                        grafo.adicionarAresta(distancia, origem, destino);
                    }
                }
            }
		}
		buffRead.close();
	}
}
