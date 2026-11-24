// Exceção checked (Exception), lançada quando falta RAM no servidor. Precisa ser tratada com try-catch ou throws. RA: 25178392-2
public class CapacidadeExcedidaException extends Exception {

    public CapacidadeExcedidaException(String msg) {
        super(msg);
    }
}
