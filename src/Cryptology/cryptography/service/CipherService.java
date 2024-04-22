package Cryptology.cryptography.service;

public interface CipherService {
    public String encrypt(String text);
    public String decrypt(String encryptedText);
}
