// RAFAELA AMORIM PESSIN
// TPA 2023/1
// GRAFOS - PARTE 1

package grafo;

import java.util.ArrayList;

// Um grafo é representado computacionalmente por uma lista de adjacências, que é uma lista de de vértices conectados por arestas
// Um grafo representa um conjunto de vértices (cidades) e um conjunto de arestas (caminhos) que conectam esses vértices
public class Grafo<T>{
    private ArrayList<Vertice<T>> vertices;

    public Grafo(){
        this.vertices =  new ArrayList<Vertice<T>>();
    }

    public void adicionarVertice(Vertice<T> vertice){
        this.vertices.add(vertice);
    }

    public Vertice<T> getVertice(T dado){
        if(dado == null) return null;
        for(Vertice<T> vert: this.vertices){
            // Verifica se o vertice atual contém o Objeto igual ao que está sendo procurado
            if(vert.getValor().equals(dado)){
                return vert;
            }
        }
        return null;
    }

    // Passa o vértice de origem e o vértice de destino e a distância entre os vértices para criar uma aresta/caminho entre cidades
    public void adicionarAresta(Float distancia, T vInicio, T vFim){
        Vertice<T> origem = this.getVertice(vInicio);
        Vertice<T> destino = this.getVertice(vFim);
        origem.adicionarDestino(new Aresta<T>(distancia, destino));
    }

    // Imprime todas as cidades vizinhas/adjacentes a uma cidade de origem
    public void obterCidadesVizinhas(T dado){        
        for(Vertice<T> vertice: vertices){
            // Verifica se o vertice atual contém a Cidade igual a que está sendo procurada
            if(vertice.getValor().equals(dado)){
                System.out.println("Cidade escolhida:" + vertice.getValor());
                // Imprime todas as cidades vizinhas
                for(Aresta<T> aresta: vertice.getDestinos()){
                    System.out.println(aresta);
                }
            }
        }
    }    

    // O método pega no grafo o vértice da cidade de origem
    // Em seguida faz um caminhamento no grafo para encontrar os caminhos a partir dele.
    public void obterCaminhos(T dado){
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();

        Vertice<T> atual = getVertice(dado);
        fila.add(atual);
        //Pego o primeiro vértice como ponto de partida e coloco na fila
        //Poderia escolher qualquer outro...
        //Mas note que dependendo do grafo pode ser que você não caminhe por todos os vétices

        //Enquanto houver vertice na fila...
        while (fila.size()>0){
            //Pego o próximo da fila, marco como visitado e o imprimo
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());
            //Depois pego a lista de adjacencia do nó e se o nó adjacente ainda
            //não tiver sido visitado, o coloco na fila
            
            ArrayList<Aresta<T>> destinos = atual.getDestinos();
            Vertice<T> proximo;

            for (int i=0; i<destinos.size();i++){
                proximo = destinos.get(i).getDestino();
                if(!marcados.contains(proximo) && !fila.contains(proximo)){
                    fila.add(proximo);
                }
            }
        }
    }

    // Para gerar a árvore geradora mínima foi implementado o algoritmo de PRIM
    // Este método computa a árvore geradora mínima, imprime na tela cada uma das arestas, imprime a soma total dos pesos das arestas da árvore geradora mínima e retorna um grafo
    // Uma árvore geradora é um conjunto de arestas do grafo que gera uma árvore
    // Uma árvore geradora é chamada mínima se, dentre todas as árvores geradoras que existem no grafo, a soma dos pesos nas arestas dela é o menor possível
    // O algoritmo encontra um subgrafo do grafo original no qual a soma total das arestas é minimizada e todos os vértices estão interligados
    // Veja um algoritmo genérico:
    // Escolha um vértice S para iniciar o subgrafo
    // enquanto houver vértices que não estão no subgrafo
    // selecione uma aresta 
    // insira a aresta e seu vértice no subgrafo
    public Grafo<T> gerarArvoreGeradoraMinima(T origem){
        Grafo<T> novoGrafo = new Grafo<T>();

        Vertice<T> novoVertice = null;

        // Verifica se o objeto passado como origem já está no grafo
        for(Vertice<T> vertice: vertices){
            if(vertice.getValor().equals(origem)){
                novoVertice = vertice;
                break;
            }
        }

        // novoVertice será direfente de 'null' se o objeto não estiver no grafo
        if(novoVertice != null){
            novoGrafo.adicionarVertice(novoVertice.clone());
            int tamanhoGrafoAtual = this.vertices.size();
            int tamanhonovoGrafo = novoGrafo.vertices.size();

            boolean achouAresta;
            T origemDaMenorAresta = null, destinoDaMenorAresta = null;
            float valorDaMenorAresta = 0;
            
            float valorNovaAresta = 0;

            ArrayList<T> listaDeOrigens = new ArrayList<T>();
            ArrayList<T> listaDeDestinos = new ArrayList<T>();
            ArrayList<Float> listaDePesos = new ArrayList<Float>();

            // pega a menor aresta do que está ligado dentre todos os vértices do novoGrafo
            while(tamanhonovoGrafo < tamanhoGrafoAtual){
                achouAresta = false;

                // Loop para achar a menor aresta
                for(Vertice<T> vertice : novoGrafo.vertices){
                    for(Aresta<T> novaAresta : vertice.getDestinos()){
                        valorNovaAresta = novaAresta.getPeso();                        

                        // Verifica se o vertice de destino já está no grafo
                        // Obtém o destino da aresta
                        T destino = novaAresta.getDestino().getValor();
                        // Busca destino no grafo
                        Vertice<T> v = novoGrafo.getVertice(destino);
                        
                        // Caso o vertice que contém o destino já esteja no grafo, forma um ciclo
                        boolean verticeDeDestinoJaEstaNoGrafoNovo = v != null;
                        // E se isso for acontecer esse vértice é ignorado
                        if(verticeDeDestinoJaEstaNoGrafoNovo) continue;

                        // Verifica se para o vertice em questão já foi encontrada uma aresta de menor valor
                        if(!achouAresta){
                            origemDaMenorAresta = vertice.getValor();
                            destinoDaMenorAresta = novaAresta.getDestino().getValor();
                            valorDaMenorAresta = valorNovaAresta;
                            achouAresta = true;
                        } else if(valorDaMenorAresta > valorNovaAresta){
                            // Caso já tenha encontrado algum, compara antiga menor aresta com a possível nova menor aresta
                            origemDaMenorAresta = vertice.getValor();
                            destinoDaMenorAresta = novaAresta.getDestino().getValor();
                            valorDaMenorAresta = valorNovaAresta;
                        }
                    }
                }              
                
                // Adiciona o vértice de destino ao grafo
                // clone() usado para duplicar as informações             
                novoGrafo.adicionarVertice(this.getVertice(destinoDaMenorAresta).clone());
                
                // Adiciona a aresta
                listaDeOrigens.add(origemDaMenorAresta);
                listaDeDestinos.add(destinoDaMenorAresta);
                listaDePesos.add(valorDaMenorAresta);
                
                tamanhonovoGrafo = novoGrafo.vertices.size();
            }

            // Remove todas as arestas do novoGrafo
            for(Vertice<T> vertice: novoGrafo.vertices){
                vertice.getDestinos().clear();
            }

            // Preenche as arestas do novoGrafo
            float _peso = 0;
            T _origem, _destino;
            for(int i = 0; i < listaDeOrigens.size(); i++){
                _peso = listaDePesos.get(i);
                _origem = listaDeOrigens.get(i);
                _destino = listaDeDestinos.get(i);
                novoGrafo.adicionarAresta(_peso, _origem, _destino);
            }

            return novoGrafo;
        } else {
            return null;
        }
    }

    // Imprime na tela cada uma das arestas (vértice de origem, vértice de destino e o peso) que vão compor a árvore geradora mínima
    // Imprime a soma total dos pesos das arestas da árvore geradora mínima
    public void imprimirArestas() {
        T origem, destino;
        float valorAresta;
        String strSaida;
        float pesoTotal = 0;
        System.out.println("===> Imprimindo Arestas <==");
        for(Vertice<T> vertice: this.vertices){
            origem = vertice.getValor();
            for(Aresta<T> aresta : vertice.getDestinos()){
                destino = aresta.getDestino().getValor();
                pesoTotal += valorAresta = aresta.getPeso();
                strSaida = String.format("origem = (%s) === peso: %.2f ==> destino = (%s)", origem, valorAresta, destino);
                System.out.println(strSaida);
            }
        }
        System.out.println("soma total dos pesos das arestas da árvore geradora mínima: " + pesoTotal);
    }

    @Override
    public Grafo<T> clone() {
        Grafo<T> cloneGrafo = new Grafo<T>();
        for(Vertice<T> vertice : this.vertices){
            // Passar pelas arestas alterando o destino da aresta usando getVertice do grafoClone
            // Cada vertice tem as arestas que ligam a um outro vértice
            cloneGrafo.adicionarVertice(vertice.clone());
        }
        // Resolve o problema de uma aresta ter como destino um vértice de outro grafo
        for(Vertice<T> vertice : cloneGrafo.vertices){
            for(Aresta<T> aresta : vertice.getDestinos()){
                Vertice<T> destinoAresta = aresta.getDestino();
                Vertice<T> verticeDeDestinoNoCloneGrafo = cloneGrafo.getVertice(destinoAresta.getValor());
                aresta.setDestino(verticeDeDestinoNoCloneGrafo);
            }
        }
        return cloneGrafo;
    }

    // Método para imprimir o predecessor
    // Um predecessor de um determinado vértice é qualquer vértice que tenha uma aresta direcionada para esse vértice
    // Se existe uma aresta direcionada do vértice A para o vértice B, então o vértice A é um predecessor do vértice B.
    private void imprimePredecessor(No<Vertice<T>> no, boolean primeiraChamada){
        if(no.getPredecessor() != null){
            imprimePredecessor(no.getPredecessor(), false);
        }
        Vertice<T> vert = no.getValor();
        // Imprime, neste caso, a cidade
        System.out.println(vert.getValor());
        if(primeiraChamada){
            System.out.println("Distancia total: " + no.getDistancia());
        }
    }

    // Método para calcular o caminho mínimo
    // Usa endereço de objetos
    // Recebe como parâmetros dois vértices e imprime na tela o caminho mínimo entre eles e a distância total entre os dois
    public void calcularCaminhoMinimo(T origem, T destino){
        // imprimir na tela o caminho minimo da origem para o destino e a distancia total entre os dois
        // Obter todos os vertices para ligar a distancia e o predececor a um vertice
        ArrayList<No<Vertice<T>>> nos = new ArrayList<No<Vertice<T>>>();
        // Povoa a lista de nós com todos os Vertices do grafo de cidades
        No<Vertice<T>> noOrigem = null, noDestino = null;
        for(Vertice<T> vertice: vertices){
            if(vertice.getValor().equals(origem)){
                // Marcar quem é o primeiro
                noOrigem = new No<Vertice<T>>(vertice, true);
                nos.add(noOrigem);
            } else if(vertice.getValor().equals(destino)){
                noDestino = new No<Vertice<T>>(vertice, false);
                nos.add(noDestino);
            } else {
                nos.add(new No<Vertice<T>>(vertice, false));
            }
        }
        ArrayList<No<Vertice<T>>> rotulados = new ArrayList<No<Vertice<T>>>();
        No<Vertice<T>> noAtual = noOrigem;
        while(this.vertices.size() < rotulados.size() || !rotulados.contains(noDestino)){
            // Adiciona o no atual a lista de rotulados
            rotulados.add(noAtual);
            // Pega distancia do no atual
            float distanciaNoAtual = noAtual.getDistancia();
            // Pega aresta que liga aos nós de destino
            for(Aresta<T> aresta : noAtual.getValor().getDestinos()){
                // Percorre cada vertice vizinho (Destino, nós adjacentes)
                Vertice<T> vert = aresta.getDestino();
                // Obtem o index do nó de destino
                No<Vertice<T>> noDestinoDoNoAtual = null;
                // Obtem o nó de destino do nó atual
                for(No<Vertice<T>> noDestinoDoAtual: nos){
                    if(noDestinoDoAtual.getValor().equals(vert)){
                        noDestinoDoNoAtual = noDestinoDoAtual;
                        break;
                    }
                }
                // Obtem a possível nova distância para o nó de destino
                float novaDistancia = distanciaNoAtual + aresta.getPeso();
                // verifica se a distância atual para o nó de destino é maior que a nova distância
                // Se for troca distância e o predecessor
                if(noDestinoDoNoAtual.getDistancia() > novaDistancia){
                    noDestinoDoNoAtual.setDistancia(novaDistancia);
                    noDestinoDoNoAtual.setPredecessor(noAtual);
                }
            }
            No<Vertice<T>> noDeMenorDistancia = null;
            // Encontra o nó de menor distância que não foi rotulado
            for(No<Vertice<T>> no: nos){
                if(!rotulados.contains(no)){
                    // Se nenhum objeto tiver sido atribuído à variável 'noDeMenorDistancia' quer dizer que ainda não existe nenhum
                    // nó de menor distância, logo podemos atribir o nó atual para tal
                    if(noDeMenorDistancia == null){
                        noDeMenorDistancia = no;
                    } else {
                        if(no.getDistancia() == No.INFINITO){
                            continue;
                        }
                        if(noDeMenorDistancia.getDistancia() > no.getDistancia()){
                            noDeMenorDistancia = no;
                        }
                    }
                }
            }
            noAtual = noDeMenorDistancia;
        }
        // imprimer o predecessor
        imprimePredecessor(noDestino, true);
    }

}