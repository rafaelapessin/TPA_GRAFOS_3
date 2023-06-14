
// RAFAELA AMORIM PESSIN
// TPA 2023/1
// GRAFOS - PARTE 1

package grafo;

// Uma aresta representa uma ligação ou conexão entre dois vértices, ou seja, o caminho/distância entre duas cidades
// Uma aresta tem peso (distância) e o vértice de destino
public class Aresta<T> {
    private float peso;
    private Vertice<T> destino;

    public Aresta(float peso, Vertice<T> destino){
        this.peso = peso;
        this.destino = destino;
    }

    // Obtém a distância (peso) entre duas cidades
    public float getPeso(){
        return peso;
    }

    // Define a distância (peso) entre duas cidades
    public void setPeso(float peso){
        this.peso = peso;
    }

    // Obtém o vértice de destino em relação ao vértice de origem
    public Vertice<T> getDestino(){
        return destino;
    }

    // Define o vértice de destino em relação ao vértice de origem
    public void setDestino(Vertice<T> destino){
        this.destino = destino;
    }

    // Retorna a cidade e a distância 
    @Override
    public String toString() {
        
        return destino.getValor() + "; peso: " + this.peso;
    }

    @Override
    public Aresta<T> clone(){
        return new Aresta<T>(this.peso, this.destino);
    }
    
}
