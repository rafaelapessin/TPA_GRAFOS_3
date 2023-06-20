package grafo;

// Classe No
// Um predecessor de um determinado vértice é qualquer vértice que tenha uma aresta direcionada para esse vértice
// Se existe uma aresta direcionada do vértice A para o vértice B, então o vértice A é um predecessor do vértice B.
public class No<T>{
    private T valor;
    private float distancia;
    private No<T> predecessor;
    static final float INFINITO = Float.POSITIVE_INFINITY;

    public No(T valor, Boolean ehOrigem){
        this.valor = valor;
        if(ehOrigem){
            this.distancia = 0;
        } else {
            this.distancia = INFINITO;
        }
        this.predecessor = null;
    }

    // Obtém o valor
    public T getValor() {
        return valor;
    }

    // Obtém a distância
    public Float getDistancia() {
        return distancia;
    }

    // Define a distância
    public void setDistancia(Float distancia) {
        this.distancia = distancia;
    }

    // Obtém o predecessor
    public No<T> getPredecessor() {
        return predecessor;
    }

    // Define o predecessor
    public void setPredecessor(No<T> predecessor) {
        this.predecessor = predecessor;
    }
}