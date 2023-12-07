package org.dc;

import java.util.*;

public class Autocomplete {
    private Dictionary dictionary = new Dictionary();

    public Autocomplete(String[] wordSet) {
        for (var word: wordSet) {
            parseWord(this.dictionary, word, 0);
        }
    }

    List<String> trigger(String input) {
        return findMatchingWords(this.dictionary, input, 0);
    }


    private static ArrayList<String> findMatchingWords(
            Dictionary dictionary,
            String input,
            int inputIndex
    ) {
        if (inputIndex < input.length()) {
            var letter = input.charAt(inputIndex);
            if (dictionary.characters.containsKey(letter)) {
                return findMatchingWords(
                        dictionary.characters.get(letter),
                        input,
                        inputIndex + 1
                );
            }

            return new ArrayList<>();
        }

        String prefix = input.substring(0, inputIndex);
        return getWordsForPrefix(dictionary, prefix);
    }

    private static ArrayList<String> getWordsForPrefix(
            Dictionary dictionary,
            String prefix
    ) {
        var matchingWords = new ArrayList<String>();
        dictionary.characters.forEach((character, dict) -> {
            if (dict.characters.isEmpty()) {
                // word completed
                matchingWords.add(prefix + character);
            } else {
                var words = getWordsForPrefix(
                        dict, prefix + character
                );
                matchingWords.addAll(words);
            }
        });

        return matchingWords;
    }

    private static void parseWord(
            Autocomplete.Dictionary dictionary,
            String word,
            int index
    ) {
        if (index == word.length()) {
            return;
        }

        char letter = word.charAt(index);
        if (dictionary.characters.containsKey(letter)) {
            parseWord(dictionary.characters.get(letter), word, index + 1);
            return;
        }

        var nextDictionary = new Dictionary();
        dictionary.characters.put(letter, nextDictionary);
        parseWord(nextDictionary, word, index + 1);
    }

    static class Dictionary {
        TreeMap<Character, Dictionary> characters = new TreeMap<>();
    }
}
