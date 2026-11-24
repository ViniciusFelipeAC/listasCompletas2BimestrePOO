// Roteiro de testes da atividade. RA: 25178392-2
public class MainCloud {

    // throws porque alocarContainer lança exceção checked (no passo 8 ela não acontece). RA: 25178392-2
    public static void main(String[] args) throws CapacidadeExcedidaException {

        // Passos 1 a 5: cria o manager, o cliente, o servidor e os containers. RA: 25178392-2
        CloudManager manager = new CloudManager();
        Cliente cliente = new Cliente("111.222.333-44", "Wayne Enterprises");
        Servidor servidor = new Servidor("192.168.0.1", 16.0, 5.50);

        manager.registrarCliente(cliente);
        manager.provisionarServidor(cliente, servidor);

        Container c1 = new Container("C-01", "API-Pagamentos", 10.0);
        Container c2 = new Container("C-02", "Banco-Relatorios", 8.0);

        // Passo 6: servidor desligado, lança ServidorOfflineException. Comentado depois de testar. RA: 25178392-2
        // servidor.alocarContainer(c1);

        // Passo 7: liga o servidor. RA: 25178392-2
        servidor.ligar();

        // Passo 8: agora o container de 10 GB cabe nos 16 GB. RA: 25178392-2
        servidor.alocarContainer(c1);
        System.out.println("Container alocado: " + c1);

        // Passo 9: 10 + 8 = 18 GB, passa de 16 GB e cai no catch. RA: 25178392-2
        try {
            servidor.alocarContainer(c2);
        } catch (CapacidadeExcedidaException e) {
            System.out.println("Erro ao alocar " + c2.getId() + ": " + e.getMessage());
        }

        // Passo 10: fatura do cliente. RA: 25178392-2
        System.out.printf("Fatura ativa: R$ %.2f%n", manager.calcularFaturaAtiva(cliente));
    }
}
