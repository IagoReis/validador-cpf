package br.com.mundodev.validator;

import br.com.mundodev.CpfInvalidoException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CpfValidatorImpl implements CpfValidator {

    private static final int PRODUTO_CALCULO_PRIMEIRO_DIGITO = 10;

    private static final int PRODUTO_CALCULO_SEGUNDO_DIGITO = 11;

    private static final int PRODUTO_FIXO_CALCULO_DIGITO = 11;

    private static final List<String> INVALID_CPFS = Arrays.asList(
        "00000000000",
        "11111111111",
        "22222222222",
        "33333333333",
        "44444444444",
        "55555555555",
        "66666666666",
        "77777777777",
        "88888888888",
        "99999999999"
    );

    @Override
    public boolean validate(final String cpf) {

        try {
            if (Objects.isNull(cpf) || cpf.length() != 11 || INVALID_CPFS.contains(cpf)) {
                return false;
            }

            final var cpfSemDigito = cpf.substring(0, 9);
            final var digito = cpf.substring(9, 11);

            final String digitoCalculado = calcularDigito(cpfSemDigito);

            return digito.equals(digitoCalculado);
        }
        catch (final CpfInvalidoException e) {
            return false;
        }
    }

    private String calcularDigito(final String cpf) {

        int calculoPrimeiroDigito = 0;
        int calculoSegundoDigito = 0;

        int contador = 0;

        for (char c : cpf.toCharArray()) {

            if (!Character.isDigit(c)) {
                throw new CpfInvalidoException();
            }

            calculoPrimeiroDigito += Integer.parseInt(String.valueOf(c)) * (PRODUTO_CALCULO_PRIMEIRO_DIGITO - contador);
            calculoSegundoDigito += Integer.parseInt(String.valueOf(c)) * (PRODUTO_CALCULO_SEGUNDO_DIGITO - contador);

            contador++;
        }

        final var primeiroDigitoCalculado = tratarCalculoDigito(calculoPrimeiroDigito);

        calculoSegundoDigito += primeiroDigitoCalculado * (PRODUTO_CALCULO_SEGUNDO_DIGITO - contador);

        final var segundoDigitoCalculado = tratarCalculoDigito(calculoSegundoDigito);

        return String.format("%d%d", primeiroDigitoCalculado, segundoDigitoCalculado);
    }

    private int tratarCalculoDigito(final int calculo) {

        final var resto = PRODUTO_FIXO_CALCULO_DIGITO - (calculo % PRODUTO_FIXO_CALCULO_DIGITO);

        return resto < 10 ? resto : 0;
    }

}
