package com.packetdelivery.clientservice;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("CnpjValidator")
@Component
public class CnpjValidator implements IValidator {
    public boolean isValid(String data) {
        String cnpj = data;
        cnpj = cnpj.replaceAll("\\D", "");
        if (cnpj.length() != 14)
            return false;
        if (areAllDigitsSame(cnpj))
            return false;
        int digit1 = calculateVerificationDigit(cnpj, 5, 12);
        int digit2 = calculateVerificationDigit(cnpj, 6, 13);
        return (cnpj.charAt(12) - '0' == digit1) && (cnpj.charAt(13) - '0' == digit2);
    }

    private static boolean areAllDigitsSame(String cnpj) {
        return cnpj.chars().distinct().count() == 1;
    }

    private static int calculateVerificationDigit(String cnpj, int startWeight, int pos) {
        int sum = 0;
        for (int i = 0; i < pos; i++) {
            sum += (cnpj.charAt(i) - '0') * startWeight--;
            if (startWeight < 2)
                startWeight = 9;
        }
        int mod = sum % 11;
        return (mod < 2) ? 0 : 11 - mod;
    }
}
