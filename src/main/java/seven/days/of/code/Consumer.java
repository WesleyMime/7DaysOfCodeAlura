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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Consumer {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();

		String request = consumer.request(args[0]);

		List<Movie> list = consumer.movies(request);
		List<List<String>> listFromFields = consumer.listFromFields(list);

		for (int i = 0; i < listFromFields.get(0).size(); i++) {
			for (int j = 0; j < listFromFields.size(); j++) {
				System.out.println(listFromFields.get(j).get(i));
			}
		}
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

	public List<Movie> movies(String json) {
		Gson gson = new Gson();
		String newJson = json.substring(9, (json.length() - 19));

		Type listType = new TypeToken<ArrayList<Movie>>() {
		}.getType();
		List<Movie> list = gson.fromJson(newJson, listType);

		return list;
	}

	public List<List<String>> listFromFields(List<Movie> list) {
		List<String> title = new ArrayList<>();
		List<String> url = new ArrayList<>();

		list.forEach(m -> {
			title.add(m.getTitle());
			url.add(m.getImage());
		});
		return List.of(title, url);
	}
}
