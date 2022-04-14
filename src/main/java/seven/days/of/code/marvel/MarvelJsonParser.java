package seven.days.of.code.marvel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MarvelJsonParser{
	
	private final String json;
	
	public MarvelJsonParser(String json) {
		this.json = json;
	}

	public List<Serie> gson() {
		Gson gson = new Gson();
		String newJson = keepOnlyListOfMoviesInJson(json);

		Type listType = new TypeToken<ArrayList<Serie>>() {}.getType();
		List<Serie> list = gson.fromJson(newJson, listType);

		return Collections.unmodifiableList(list);
	}

	private String keepOnlyListOfMoviesInJson(String json) {
		int indexArray = json.indexOf("[");
		return json.substring(indexArray, (json.length() - 2));
	}
}
