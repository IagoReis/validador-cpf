package br.com.mundodev.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidatorImplTest {

    private final CpfValidator cpfValidator = new CpfValidatorImpl();

    @Test
    void validateInvalidCpfs() {

        assertFalse(cpfValidator.validate(null));
        assertFalse(cpfValidator.validate(""));
        assertFalse(cpfValidator.validate("a"));
        assertFalse(cpfValidator.validate("A"));
        assertFalse(cpfValidator.validate("ABCDEFGHIJK"));

        assertFalse(cpfValidator.validate("00000000000"));
        assertFalse(cpfValidator.validate("11111111111"));
        assertFalse(cpfValidator.validate("22222222222"));
        assertFalse(cpfValidator.validate("33333333333"));
        assertFalse(cpfValidator.validate("44444444444"));
        assertFalse(cpfValidator.validate("55555555555"));
        assertFalse(cpfValidator.validate("66666666666"));
        assertFalse(cpfValidator.validate("77777777777"));
        assertFalse(cpfValidator.validate("88888888888"));
        assertFalse(cpfValidator.validate("99999999999"));

        assertFalse(cpfValidator.validate("9249318707A"));
        assertFalse(cpfValidator.validate("92493187071"));
    }

    @Test
    void validateValidCpfs() {

        assertTrue(cpfValidator.validate("92493187075"));
        assertTrue(cpfValidator.validate("05972620049"));
        assertTrue(cpfValidator.validate("50357672054"));
    }

}
