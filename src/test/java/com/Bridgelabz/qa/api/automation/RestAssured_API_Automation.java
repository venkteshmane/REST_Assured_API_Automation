package com.Bridgelabz.qa.api.automation;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssured_API_Automation {
	String token;
	String user_id;
	String playlist_id;
	
	
	@BeforeTest
	public void getToken() {
		token = "Bearer BQCzOUhgb_qK16ksD7fJ3-52FYSU2Cled3uvKU2O7xY8UWYsp1zUtL-Ksc7AoMcccmrEVfwKi_jwpAsxH0zt6DJqP_89lQzF7xJi1EP5telj0blBYRayqjOhhcJJeeJ_vijMbm5PDM2f-dWkssc67i-HrOX3vvRgv1dvYcrkkPcp3YEz-V-kb-rzgFqytkKKf5A";
	}
	
	public String track1 ="spotify:track:16XEVyPh5NT31CAAqPbxQF";
	public String track2 ="spotify:track:6rkqqLPg9Lbsdh26JMqfp0";
	public String track3 ="spotify:track:6ZRzF7XiZdjnQUDYIk7w7u";
	public String track4 ="spotify:track:7fyIuR4aaWb6iltlAoSkxF";
	public String track5 ="spotify:track:1cpaDNciPGlC39qPs4RkMU";
	
	@Test 
	public void Get_Current_Users_Profile() {
		Response response = RestAssured.given()
//				.contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me");
		user_id = response.path("id");
		System.out.println("My user id is :" +user_id);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Users_Profile() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/user_id");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
//	-------------------------- playlist---------------------- //
	
	@Test
	public void Get_Current_Users_Playlists() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	@Test
	public void Create_Playlist() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"CFP-176\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("https://api.spotify.com/v1/users/31mi2phjiuagifqzvy3fbi5zmgmq/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}
	
	@Test
	public void Get_Playlist_Cover_Image() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/3fUq32Bqw176XtH6u8qAjD/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get__Playlist_Items() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/3fUq32Bqw176XtH6u8qAjD/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Playlist() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/3fUq32Bqw176XtH6u8qAjD");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Users_Playlists() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/users/31mi2phjiuagifqzvy3fbi5zmgmq/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Update_Playlist_Items() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"range_start\": 1,\r\n"
						+ "  \"insert_before\": 3,\r\n"
						+ "  \"range_length\": 2\r\n"
						+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/3fUq32Bqw176XtH6u8qAjD/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Change_Playlist_Details() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"Updated Playlist Name\",\r\n"
						+ "  \"description\": \"Updated playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.put("	https://api.spotify.com/v1/playlists/3fUq32Bqw176XtH6u8qAjD");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Add_Items_to_Playlist() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
//				.queryParam("uris",track1)
//				.queryParam("uris",track2)
				.queryParam("uris",track3)
//				.queryParam("uris",track4)
//				.queryParam("uris",track5)
				.when()
				.post("https://api.spotify.com/v1/playlists/3fUq32Bqw176XtH6u8qAjD/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}
	
	
//	---------------------------Track ----------------------
	
	@Test
	public void Get_Tracks_Audio_Analysis() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/audio-analysis/16XEVyPh5NT31CAAqPbxQF");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Tracks_Audio_Features() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Trackss_Audio_Features() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/16XEVyPh5NT31CAAqPbxQF");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Several_Tracks() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/tracks/16XEVyPh5NT31CAAqPbxQF");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test
	public void Get_Track() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/tracks/16XEVyPh5NT31CAAqPbxQF");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
}
