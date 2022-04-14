package seven.days.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seven.days.of.code.imdb.ImdbApiClient;
import seven.days.of.code.imdb.ImdbMovieJsonParser;
import seven.days.of.code.imdb.Movie;
import seven.days.of.code.marvel.MarvelApiClient;
import seven.days.of.code.marvel.MarvelJsonParser;
import seven.days.of.code.marvel.Serie;

public class Consumer {

	public static void main(String[] args) {
		String apiKeyImdb = args[0];
		String pathHtml = args[1]; // C:\Users\User\Desktop\imdb250.html
		String publicKeyMarvel = args[2];
		String privateKeyMarvel = args[3];

		String jsonImdb = new ImdbApiClient(apiKeyImdb).request();
		String jsonMarvel = new MarvelApiClient(publicKeyMarvel, privateKeyMarvel).request();
		
		ImdbMovieJsonParser parserImdb = new ImdbMovieJsonParser(jsonImdb);
		MarvelJsonParser parserMarvel = new MarvelJsonParser(jsonMarvel);

		List<Movie> movies = parserImdb.manually();
		List<Serie> series = parserMarvel.gson();
		
		List<Content> contents = Stream.of(movies, series)
			.flatMap(Collection::stream)
			.sorted(Comparator.comparing(Content::year).reversed())
			.collect(Collectors.toList());
		
		try (PrintWriter printWriter = new PrintWriter(new File(pathHtml))) {
			HTMLGenerator html = new HTMLGenerator(printWriter);
			html.generate(contents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
