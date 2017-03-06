package demo;

/**
 * 'Word' object is nicely represented in JSON over a regular String.
 */
public class Word {

	private String word;

	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
