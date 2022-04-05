package seven.days.of.code;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class Consumer {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		System.out.println(consumer.request(args[0]));
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
}
