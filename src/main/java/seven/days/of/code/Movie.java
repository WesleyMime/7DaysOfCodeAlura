package seven.days.of.code;

import com.google.gson.Gson;

public final class Movie {

	private String title;
	private Integer year;
	private String image;
	private Double imDbRating;
	
	public Movie() {}
	
	public Movie(String title, Integer year, String image, Double imDbRating) {
		this.title = title;
		this.year = year;
		this.image = image;
		this.imDbRating = imDbRating;
	}

	public String getTitle() {
		return title;
	}

	public Integer getYear() {
		return year;
	}

	public String getImage() {
		return image;
	}

	public Double getImDbRating() {
		return imDbRating;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
