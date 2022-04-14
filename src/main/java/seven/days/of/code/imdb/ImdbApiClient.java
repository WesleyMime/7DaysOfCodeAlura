package seven.days.of.code.imdb;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import seven.days.of.code.ApiClient;

public class ImdbApiClient implements ApiClient{

	private final String api_key;
	
	public ImdbApiClient(String api_key) {
		this.api_key = api_key;
	}
	
	public String request() {
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

}
