package seven.days.of.code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Consumer {

	public static void main(String[] args) {
		String api_key = args[0];
		String pathHtml = args[1]; //	C:\Users\User\Desktop\imdb250.html

		String json = new ImdbApiClient(api_key).request();
		ImdbMovieJsonParser parser = new ImdbMovieJsonParser(json);

//		List<Movie> list = parser.gson();
//		List<Movie> list = parser.jackson();
		List<Movie> list = parser.manually();
		list.forEach(System.out::println);
		
		try (PrintWriter printWriter = new PrintWriter(new File(pathHtml))) {
			HTMLGenerator html = new HTMLGenerator(printWriter);
			html.generate(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
