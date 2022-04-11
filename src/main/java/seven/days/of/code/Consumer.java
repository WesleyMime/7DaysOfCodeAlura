package seven.days.of.code;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Consumer {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();

		String request = consumer.request(args[0]);

//		List<Movie> list = consumer.moviesGson(request);
//		List<Movie> list = consumer.moviesJackson(request);
		List<Movie> list = consumer.moviesManually(request);
		list.forEach(System.out::println);
	}

	public String request(String api_key) {

		String response = "";
		try {
			HttpClient httpClient = HttpClient.newHttpClient();
			URI uri = new URI("https://imdb-api.com/API/Top250Movies/" + api_key);

			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

			HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
			response = httpResponse.body();
		} catch (URISyntaxException | IOException | InterruptedException e) {
			response = e.getMessage();
		}
		return response;
	}

	public List<Movie> moviesGson(String json) {
		Gson gson = new Gson();
		String newJson = keepOnlyListOfMoviesInJson(json);

		Type listType = new TypeToken<ArrayList<Movie>>() {}.getType();
		List<Movie> list = gson.fromJson(newJson, listType);

		return Collections.unmodifiableList(list);
	}
	
	public List<Movie> moviesJackson(String json) {
		String newJson = keepOnlyListOfMoviesInJson(json);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<Movie> movies = new ArrayList<>();
		try {
			movies = mapper.readValue(newJson, new TypeReference<List<Movie>>() {});			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	public List<Movie> moviesManually(String json) {
		String newJson = keepOnlyListOfMoviesInJson(json);
		List<Movie> listMovies = new ArrayList<>();
		
		newJson = newJson.replace("[", "");
		newJson = newJson.replace("{", "");

		
		String[] movies = newJson.split("},");
		
		for (String movie : movies) {
			String[] keyValue = movie.split("\",\"");
			String title = getValue(keyValue, 2);
			Integer year = Integer.valueOf(getValue(keyValue, 4));
			String image = getValue(keyValue, 5);
			Double rating = Double.valueOf(getValue(keyValue, 7));
			listMovies.add(new Movie(title, year, image, rating));	
			
		}
		return listMovies;
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
