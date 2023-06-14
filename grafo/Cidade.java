// RAFAELA AMORIM PESSIN
// TPA 2023/1
// GRAFOS - PARTE 1

package grafo;

// Classe Cidade
// Cidade tem código e nome
public class Cidade{
    private int codigo;
    private String nome;
    
    public Cidade(int cod, String nome){
        this.codigo = cod;
        this.nome = nome;
    }

    // Obtém o código da cidade
    public int getCodigo() {
        return codigo;
    }

    // Obtém o nome da cidade
    public String getNome() {
        return nome;
    }

    // Retorna o código e nome da cidade
    @Override
    public String toString() {
        
        return "código: " + codigo + "; nome: " + nome;
    }

    // Compara os códigos da cidade
    @Override
    public boolean equals(Object obj) {
        
        if(obj == null) return false;
        int cod = ((Cidade) obj).codigo;
        return codigo == cod;
    }

   
}