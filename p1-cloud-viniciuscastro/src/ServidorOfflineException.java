// Exceção unchecked (RuntimeException), lançada quando o servidor está desligado. RA: 25178392-2
public class ServidorOfflineException extends RuntimeException {

    public ServidorOfflineException(String msg) {
        super(msg);
    }
}
