package seven.days.of.code;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.apache.commons.codec.digest.DigestUtils; 

public class MarvelApiClient implements ApiClient {
	
	private final String ts;
	private final String publicKey;
	private final String hash;
	
    public MarvelApiClient(String publicKey, String privateKey) {
    	this.ts = String.valueOf(System.currentTimeMillis());
    	this.publicKey = publicKey;
    	this.hash = DigestUtils.md5Hex(ts + privateKey + publicKey);
    }

    @Override
    public String request() {
    	String response = "";
		try {
			HttpClient httpClient = HttpClient.newHttpClient();
			
			String uriString = String.
					format("https://gateway.marvel.com:443/v1/public/series?ts=%S&apikey=%s&hash=%s", ts, publicKey, hash);
			URI uri = new URI(uriString);

			HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
			HttpResponse<String> httpResponse = httpClient.send(request, BodyHandlers.ofString());
			response = httpResponse.body();			
		} catch (URISyntaxException | IOException | InterruptedException e) {
			response = e.getMessage();
		}
		return response;
    }
}