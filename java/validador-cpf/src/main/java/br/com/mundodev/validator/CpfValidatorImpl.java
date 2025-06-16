package br.com.mundodev.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CpfValidatorImpl implements CpfValidator {

    private static final int PRODUTO_CALCULO_PRIMEIRO_DIGITO = 10;

    private static final int PRODUTO_CALCULO_SEGUNDO_DIGITO = 11;

    private static final int PRODUTO_FIXO_CALCULO_DIGITO = 11;

    private static final List<String> INVALID_CPFS = Arrays.asList("00000000000", "11111111111", "22222222222",
            "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999");

    @Override
    public boolean validate(final String cpf) {

        if (Objects.isNull(cpf) || cpf.trim().length() != 11 || !isCpfWithDigitsOnly(cpf) || INVALID_CPFS.contains(cpf)) {
            return false;
        }

        final var cpfSemDigito = cpf.substring(0, 9);
        final var digito = cpf.substring(9, 11);

        final String primeiroDigito = calcularDigitoUnitario(cpfSemDigito, PRODUTO_CALCULO_PRIMEIRO_DIGITO);
        final String segundoDigito = calcularDigitoUnitario(cpfSemDigito.concat(primeiroDigito), PRODUTO_CALCULO_SEGUNDO_DIGITO);

        return digito.equals(primeiroDigito.concat(segundoDigito));
    }

    private boolean isCpfWithDigitsOnly(final String cpf) {

        for (char c : cpf.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    private String calcularDigitoUnitario(final String cpf, int produto) {

        int calculo = 0;

        for (char c : cpf.toCharArray()) {
            calculo += Integer.parseInt(String.valueOf(c)) * produto--;
        }

        final var resto = PRODUTO_FIXO_CALCULO_DIGITO - (calculo % PRODUTO_FIXO_CALCULO_DIGITO);

        final var digitoUnitario = resto < 10 ? resto : 0;

        return String.valueOf(digitoUnitario);
    }

}
