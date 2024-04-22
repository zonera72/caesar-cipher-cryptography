package Cryptology.cryptography.service.impl;

import Cryptology.cryptography.service.CipherService;

import static Cryptology.cryptography.constants.CaesarConstants.*;

public class CaesarCipher implements CipherService {
    private int key;

    public CaesarCipher(int key) {
        this.key = key;
    }


    @Override
    public String encrypt(String text) {

        StringBuilder builder = new StringBuilder();

        char[] characters = text.toCharArray();
        for (char alphabet : characters) {

            if (Character.isUpperCase(alphabet)) {
                int shift = key + alphabet - 65;
                builder.append(upperCaseAlphabets.charAt(shift));
            } else if (Character.isLowerCase(alphabet)) {
                int shift = key + alphabet - 97;
                builder.append(lowerCaseAlphabets.charAt(shift));
            } else if (specialChars.contains(String.valueOf(alphabet))) {
                int shift = specialChars.indexOf(alphabet)+key;
                builder.append(specialChars.charAt(shift));

            }
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String encryptedText) {

        StringBuilder builder = new StringBuilder();

        char[] characters = encryptedText.toCharArray();
        for (char alphabet : characters) {

            if (Character.isUpperCase(alphabet)) {
                int shift = alphabet - 65 - key;
                builder.append(shift < 0 ? upperCaseAlphabetsReverse.charAt(Math.abs(shift) - 1) : upperCaseAlphabets.charAt(shift));
            } else if (Character.isLowerCase(alphabet)) {
                int shift = alphabet - 97 - key;
                builder.append(shift < 0 ? lowerCaseAlphabetsReverse.charAt(Math.abs(shift) - 1) : lowerCaseAlphabets.charAt(shift));
            }else if (specialCharsReverse.contains(String.valueOf(alphabet))) {
                int shift = specialCharsReverse.indexOf(alphabet)+key;
                builder.append(specialCharsReverse.charAt(shift));
            }

        }
        return builder.toString();
    }


}
