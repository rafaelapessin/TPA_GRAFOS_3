
// RAFAELA AMORIM PESSIN
// TPA 2023/1
// GRAFOS - PARTE 1

package grafo;

import java.util.ArrayList;

// Um vértice representa uma cidade de um grafo
// Cada vértice armazena uma lista de arestas adjacentes, ou seja, uma lista com todas as cidades vizinhas à cidade de origem
// Cada vértice tem também um valor, ou seja, uma cidade que contém código e nome da cidade
public class Vertice<T> {
    private T valor;
    private ArrayList<Aresta<T>> destinos; 

    public Vertice(T valor){
        this.valor = valor;
        this.destinos =  new ArrayList<Aresta<T>>();
    }

    // Obtém o valor do vértice (código e nome da cidade)
    public T getValor(){
        return valor;
    }

    // Define o valor do vértice (código e nome da cidade)
    public void setValor(T valor){
        this.valor = valor;
    }

    // Usada dentro da funçÃO adicionarAresta() na classe grafo para criar uma aresta/caminho/distância entre duas cidades
    // Ao chamar essa função, passa o vértice de origem e o vértice de destino e cria a aresta que liga essas duas cidades (distância entra as duas)
    //  origem.adicionarDestino(new Aresta<T>(distancia, destino));
    public void adicionarDestino(Aresta<T> aresta){
        this.destinos.add(aresta);
    }

    // Retorna todas as cidades vizinhas em relação a uma cidade de origem
    // Método usada dentro da obterCidadesVizinhas()
    public ArrayList<Aresta<T>> getDestinos(){
        return destinos;
    }

    @Override
    protected Vertice<T> clone() {
        Vertice<T> v = new Vertice<T>(this.valor);
        for(Aresta<T> a : this.destinos){
            v.destinos.add(a.clone());
        }
        return v;
    }

    @Override
    public String toString() {
        return this.valor.toString();
    }
    
}
