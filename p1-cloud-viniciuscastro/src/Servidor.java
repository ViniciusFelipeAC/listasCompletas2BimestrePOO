import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Servidor virtual que hospeda os containers. RA: 25178392-2
public class Servidor {

    // A lista já é criada aqui pra não dar NullPointerException. RA: 25178392-2
    private String ip;
    private double precoPorHora;
    private double ramTotal;
    private boolean statusLigado;
    private List<Container> containers = new ArrayList<>();

    // Construtor, o servidor começa desligado. RA: 25178392-2
    public Servidor(String ip, double ramTotal, double precoPorHora) {
        this.ip = ip;
        this.ramTotal = ramTotal;
        this.precoPorHora = precoPorHora;
        this.statusLigado = false;
    }

    // Liga e desliga o servidor. RA: 25178392-2
    public void ligar() {
        statusLigado = true;
    }

    public void desligar() {
        statusLigado = false;
    }

    // Antes de adicionar verifica se o servidor está ligado e se ainda tem RAM livre. RA: 25178392-2
    public void alocarContainer(Container c) throws CapacidadeExcedidaException {
        if (!statusLigado) {
            throw new ServidorOfflineException("Erro: Não é possível alocar containers em um servidor desligado.");
        }
        if (calcularRamEmUso() + c.getRamNecessaria() > ramTotal) {
            throw new CapacidadeExcedidaException("Falha Crítica: Memória RAM insuficiente no servidor " + ip + ".");
        }
        containers.add(c);
    }

    // Soma a RAM de todos os containers usando stream. RA: 25178392-2
    public double calcularRamEmUso() {
        return containers.stream()
                .mapToDouble(Container::getRamNecessaria)
                .sum();
    }

    // Getters. RA: 25178392-2
    public String getIp() {
        return ip;
    }

    public double getPrecoPorHora() {
        return precoPorHora;
    }

    public double getRamTotal() {
        return ramTotal;
    }

    public boolean isStatusLigado() {
        return statusLigado;
    }

    public List<Container> getContainers() {
        return containers;
    }

    // Dois servidores são iguais se tiverem o mesmo IP. RA: 25178392-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Servidor outro = (Servidor) obj;
        return Objects.equals(ip, outro.ip);
    }

    // hashCode pelo IP, igual ao equals. RA: 25178392-2
    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @Override
    public String toString() {
        return ip + " (" + ramTotal + " GB, R$ " + precoPorHora + "/h)";
    }
}
