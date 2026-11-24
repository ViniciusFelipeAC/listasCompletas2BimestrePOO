import java.util.Objects;

// Cliente da NexusCloud, identificado pelo CPF. RA: 25178392-2
public class Cliente {

    private String cpf;
    private String nome;

    // Construtor recebendo cpf e nome. RA: 25178392-2
    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getters (sem setter de cpf porque ele é a identidade do cliente). RA: 25178392-2
    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    // Dois clientes são iguais se tiverem o mesmo CPF. RA: 25178392-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente outro = (Cliente) obj;
        return Objects.equals(cpf, outro.cpf);
    }

    // hashCode pelo CPF, igual ao equals, pra funcionar como chave do HashMap. RA: 25178392-2
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return nome + " (" + cpf + ")";
    }
}
