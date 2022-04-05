package seven.days.of.code;

import com.google.gson.Gson;

public class Movie {

	private String id;
	private Integer rank;
	private String title;
	private String fullTitle;
	private Integer year;
	private String image;
	private String crew;
	private Double imDbRating;
	private Long imDbRatingCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullTitle() {
		return fullTitle;
	}

	public void setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public Double getImDbRating() {
		return imDbRating;
	}

	public void setImDbRating(Double imDbRating) {
		this.imDbRating = imDbRating;
	}

	public Long getImDbRatingCount() {
		return imDbRatingCount;
	}

	public void setImDbRatingCount(Long imDbRatingCount) {
		this.imDbRatingCount = imDbRatingCount;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
