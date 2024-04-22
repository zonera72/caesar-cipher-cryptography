package Cryptology.cryptanalysis;

import Cryptology.cryptography.service.CipherService;
import Cryptology.cryptography.service.impl.CaesarCipher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BruteForce {
    private static Set<String> wordsSet = new HashSet<>();
    private static boolean fileLoaded=false;

    public static String searchAttack(String encryptedTxt) throws IOException {
        if(!fileLoaded){
            loadWordsFromFile("src/Cryptology/cryptanalysis/words.txt");
        }
        int highestProbability=0;
        String textWithHighestProb = null;
        for(int key=1;key<26;key++){
            CipherService cipherService = new CaesarCipher(key);
            String decryptedText=cipherService.decrypt(encryptedTxt);
            int currentScore = calculateProbability(decryptedText);
            if(currentScore>highestProbability){
                highestProbability= currentScore;
                textWithHighestProb=decryptedText;
            }
            highestProbability= currentScore>highestProbability?currentScore:highestProbability;
        }
        return textWithHighestProb;
    }

    private static int calculateProbability(String decryptedText){
        int score =0;
        for(String word : decryptedText.split(" ")){
            if(wordsSet.contains(word.toLowerCase())){
                score++;
            }
        }
        return score;
    }

    private static void loadWordsFromFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        for(String line : lines){
            for(String word: line.split(",")){
                wordsSet.add(word);
            }
        }
        fileLoaded=true;
    }


}
