import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Guarda os clientes e os servidores de cada um. RA: 25178392-2
public class CloudManager {

    // Chave = cliente, valor = conjunto de servidores sem repetição. RA: 25178392-2
    private Map<Cliente, Set<Servidor>> bancoDeDados;

    public CloudManager() {
        bancoDeDados = new HashMap<>();
    }

    // Registra o cliente com um conjunto vazio de servidores. RA: 25178392-2
    public void registrarCliente(Cliente c) {
        bancoDeDados.putIfAbsent(c, new HashSet<>());
    }

    // Adiciona o servidor no conjunto do cliente (registra o cliente se ainda não tiver). RA: 25178392-2
    public void provisionarServidor(Cliente c, Servidor s) {
        registrarCliente(c);
        bancoDeDados.get(c).add(s);
    }

    // Soma o preço por hora só dos servidores ligados do cliente. RA: 25178392-2
    public double calcularFaturaAtiva(Cliente c) {
        return bancoDeDados.getOrDefault(c, new HashSet<>())
                .stream()
                .filter(Servidor::isStatusLigado)
                .mapToDouble(Servidor::getPrecoPorHora)
                .sum();
    }
}
