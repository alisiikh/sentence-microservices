package demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.AdjectiveClient;
import demo.dao.ArticleClient;
import demo.dao.NounClient;
import demo.dao.SubjectClient;
import demo.dao.VerbClient;
import demo.domain.Word;

import static demo.domain.Word.Role.*;

@Service
public class WordService implements IWordService {

	@Autowired
	private VerbClient verbClient;
	@Autowired
	private SubjectClient subjectClient;
	@Autowired
	private ArticleClient articleClient;
	@Autowired
	private AdjectiveClient adjectiveClient;
	@Autowired
	private NounClient nounClient;

	@HystrixCommand(fallbackMethod = "getSubjectFallback", commandKey = "Subject", threadPoolKey = "wordService")
	@Override
	public Word getSubject() {
		return subjectClient.getWord();
	}

	@HystrixCommand(commandKey = "Verb", fallbackMethod = "getVerbFallback", threadPoolKey = "wordService")
	@Override
	public Word getVerb() {
		return verbClient.getWord();
	}

	@HystrixCommand(commandKey = "Article", fallbackMethod = "getArticleFallback", threadPoolKey = "wordService")
	@Override
	public Word getArticle() {
		return articleClient.getWord();
	}


	@HystrixCommand(fallbackMethod = "getAdjectiveFallback", commandKey = "Adjective", threadPoolKey = "wordService")
	@Override
	public Word getAdjective() {
		return adjectiveClient.getWord();
	}


	@HystrixCommand(fallbackMethod = "getNounFallback", commandKey = "Noun", threadPoolKey = "wordService")
	@Override
	public Word getNoun() {
		return nounClient.getWord();
	}

	private Word getArticleFallback() {
		return new Word("", ARTICLE);
	}

	private Word getVerbFallback() {
		return new Word("does", VERB);
	}

	private Word getAdjectiveFallback() {
		return new Word("", ARTICLE);
	}

	private Word getNounFallback() {
		return new Word("something", NOUN);
	}

	private Word getSubjectFallback() {
		return new Word("Someone", SUBJECT);
	}
}
