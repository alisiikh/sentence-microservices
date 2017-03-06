package demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.AdjectiveClient;
import demo.dao.ArticleClient;
import demo.dao.NounClient;
import demo.dao.SubjectClient;
import demo.dao.VerbClient;
import demo.domain.Word;
import rx.Observable;

import static demo.domain.Word.Role.*;

@Service
public class WordServiceImpl implements WordService {

	@Autowired VerbClient verbClient;
	@Autowired SubjectClient subjectClient;
	@Autowired ArticleClient articleClient;
	@Autowired AdjectiveClient adjectiveClient;
	@Autowired NounClient nounClient;

	
	@HystrixCommand(fallbackMethod = "getSubjectFallback", commandKey = "Subject", threadPoolKey = "wordService")
	@Override
	public Observable<Word> getSubject() {
		return new ObservableResult<Word>() {
			@Override
			public Word invoke() {
				return new Word(subjectClient.getWord().getWord(), SUBJECT);
			}
		};
	}

	@HystrixCommand(commandKey = "Verb", fallbackMethod = "getVerbFallback", threadPoolKey = "wordService")
	@Override
	public Observable<Word> getVerb() {
		return new ObservableResult<Word>() {
			@Override
			public Word invoke() {
				return new Word(verbClient.getWord().getWord(), VERB);
			}
		};
	}

	@HystrixCommand(commandKey = "Article", fallbackMethod = "getArticleFallback", threadPoolKey = "wordService")
	@Override
	public Observable<Word> getArticle() {
		return new ObservableResult<Word>() {
			@Override
			public Word invoke() {
				return new Word(articleClient.getWord().getWord(), ARTICLE);
			}
		};
	}


	@HystrixCommand(fallbackMethod = "getAdjectiveFallback", commandKey = "Adjective", threadPoolKey = "wordService")
	@Override
	public Observable<Word> getAdjective() {
		return new ObservableResult<Word>() {
			@Override
			public Word invoke() {
				return new Word(adjectiveClient.getWord().getWord(), ADJECTIVE);
			}
		};
	}


	@HystrixCommand(fallbackMethod = "getNounFallback", commandKey = "Noun", threadPoolKey = "wordService")
	@Override
	public Observable<Word> getNoun() {
		return new ObservableResult<Word>() {

			@Override
			public Word invoke() {
				return new Word(nounClient.getWord().getWord(), NOUN);
			}
		};
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
