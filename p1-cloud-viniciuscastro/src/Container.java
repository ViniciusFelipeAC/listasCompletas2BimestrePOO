import java.util.Objects;

// Aplicação que roda dentro de um servidor e consome RAM. RA: 25178392-2
public class Container {

    private String id;
    private String nomeApp;
    private double ramNecessaria;

    // Construtor com id, nome da aplicação e RAM necessária em GB. RA: 25178392-2
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    // Getters. RA: 25178392-2
    public String getId() {
        return id;
    }

    public String getNomeApp() {
        return nomeApp;
    }

    public double getRamNecessaria() {
        return ramNecessaria;
    }

    // Dois containers são iguais se tiverem o mesmo ID. RA: 25178392-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    // hashCode pelo ID, igual ao equals. RA: 25178392-2
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " (" + nomeApp + ", " + ramNecessaria + " GB)";
    }
}
