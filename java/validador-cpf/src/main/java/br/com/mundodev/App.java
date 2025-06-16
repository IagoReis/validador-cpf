package br.com.mundodev;

import br.com.mundodev.validator.CpfValidator;
import br.com.mundodev.validator.CpfValidatorImpl;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        final CpfValidator cpfValidator = new CpfValidatorImpl();

        System.out.println(cpfValidator.validate("32548926150"));
        System.out.println(cpfValidator.validate("40770313892"));
    }
}
