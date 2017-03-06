package demo.domain;

import demo.domain.Word.Role;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author lial.
 */
public class Sentence {

    private Map<Role, String> words = new TreeMap<>();

    public void addWord(Word word) {
        words.put(word.getRole(), word.getWord());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Role role : words.keySet()) {
            sb.append(words.get(role)).append(' ');
        }

        return sb.toString();
    }
}
