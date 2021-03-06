package demo.domain;

/**
 * 'Word' object is nicely represented in JSON over a regular String.
 */
public class Word {

	private String word;
	private Role role;

	public Word(String word, Role role) {
		this(word);
		this.role = role;
	}
	
	public Word(String word) {
		this();
		this.word = word;
	}

	public Word() {
		super();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getWord() {
		return word;
	}
	
	public String getString() {
		return getWord();
	}

	public void setWord(String word) {
		this.word = word;
	}

	public enum Role {
		SUBJECT,VERB,ARTICLE,ADJECTIVE,NOUN;
	}
}
