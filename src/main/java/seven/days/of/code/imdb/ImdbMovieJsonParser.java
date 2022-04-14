package seven.days.of.code.imdb;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ImdbMovieJsonParser {
	
	private final String json;
	
	public ImdbMovieJsonParser(String json) {
		this.json = json;
	}

	public List<Movie> gson() {
		Gson gson = new Gson();
		String newJson = keepOnlyListOfMoviesInJson(json);

		Type listType = new TypeToken<ArrayList<Movie>>() {}.getType();
		List<Movie> list = gson.fromJson(newJson, listType);

		return Collections.unmodifiableList(list);
	}
	
	public List<Movie> jackson() {
		String newJson = keepOnlyListOfMoviesInJson(json);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<Movie> movies = new ArrayList<>();
		try {
			movies = mapper.readValue(newJson, new TypeReference<List<Movie>>() {});			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Collections.unmodifiableList(movies);
	}
	
	public List<Movie> manually() {
		String newJson = keepOnlyListOfMoviesInJson(json);
		List<Movie> listMovies = new ArrayList<>();
		
		
		String[] movies = newJson.split("},");
		
		for (String movie : movies) {
			String[] keyValue = movie.split("\",\"");
			String title = getValue(keyValue, 2);
			Integer year = Integer.valueOf(getValue(keyValue, 4));
			String image = getValue(keyValue, 5);
			String rating = getValue(keyValue, 7);
			listMovies.add(new Movie(title, year, image, rating));	
			
		}
		return Collections.unmodifiableList(listMovies);
	}

	private String getValue(String[] attributes, int i) {
		String attribute = attributes[i];
		int valuePosition = attribute.indexOf(":");
		// 2 to get first char after :"
		return attribute.substring(valuePosition + 2);
	}

	private String keepOnlyListOfMoviesInJson(String json) {
		return json.substring(9, (json.length() - 19));
	}
}
