package seven.days.of.code;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class HTMLGenerator {

	private Writer writer;

	public HTMLGenerator(Writer writer) {
		this.writer = writer;
	}
	
	public void generate(List<? extends Content> contents) {
		String html = createHeader();
		for (Content content : contents) {
			String card = """							
						<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
							<h4 class=\"card-header\">%s</h4>
							<div class=\"card-body\">
								<img class=\"card-img\" src=\"%s\" alt=\"%s\">
								<p class=\"card-text mt-2\">%s - %s - Ano: %s</p>
							</div>
						</div>
					""";
			html = html.concat(
					String.format(
							card, content.title(), content.image(), content.title(), content.type(), content.rating(), content.year()));
		}
		html = html.concat("</body></html>");
		try {
			writer.write(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	private String createHeader() {
		String head =
				"""
				<html>
					<head>
						<meta charset=\"utf-8\">
						<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
						<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" 
							+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">					
					</head>
					<body>
				""";
		return head;
	}
}
