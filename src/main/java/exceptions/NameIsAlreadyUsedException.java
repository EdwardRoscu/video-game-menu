package exceptions;

public class NameIsAlreadyUsedException extends Exception {

    public NameIsAlreadyUsedException(String username) { super(String.format("%s is already used!", username)); }

}
