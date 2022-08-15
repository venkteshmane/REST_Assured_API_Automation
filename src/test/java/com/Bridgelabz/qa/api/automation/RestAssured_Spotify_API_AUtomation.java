package com.Bridgelabz.qa.api.automation;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssured_Spotify_API_AUtomation {

	String token;
	String user_id;
	String playlist_id;
	String track_id = "16XEVyPh5NT31CAAqPbxQF";
//	public String track_uris1="spotify:track:16XEVyPh5NT31CAAqPbxQF";
//	public String track_uris2="spotify:track:6rkqqLPg9Lbsdh26JMqfp0";
//	public String track_uris3="spotify:track:6ZRzF7XiZdjnQUDYIk7w7u";
//	public String track_uris4="spotify:track:7fyIuR4aaWb6iltlAoSkxF";
//	public String track_uris5="spotify:track:1cpaDNciPGlC39qPs4RkMU";
	
	@BeforeTest
	public void getToken() {
		token = "Bearer BQDRtR3K_GOnPy9OaDm1lTlbUXi4-zOsmGJaYEmve25mj9nOKuNAZeEUcmf8_EtOAyM3CLgcq9pU-2ElMrfcPzUjaF-BTKJSORBOftwKjQhRuOWUNgT_q_2AO25zcgystuEjmQbsqlElER7p3ygTmQ6xdSy2nS7rSqHmTQo81ENxiuXVwhuwrDmWIt6KU53QWPTB5gZgYtSO-vb3bOmKsFG3BSRbB1qhB9hqhAemHOQCE_EaGGqan_vhZP99E-rFLHwuoit6aSg";
	}
	
	
	
	@Test (priority=1)
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
	
	@Test (priority=2)
	public void Get_Users_Profile() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
//	-------------------------- Search -------------------------  //
	
	@Test (priority=17)
	public void Search_for_Item() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.queryParam("q", "Arjit singh")
				.queryParam("type", "track")
				.queryParam("market", "IN")
				.queryParam("limit", "5")
				.when()
				.get("https://api.spotify.com/v1/search");
//		track_id = response.path("uri");
//		System.out.println("My track id is :" +track_id);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	//	-------------------------- playlist -------------------------  //
	
	@Test (priority=3)
	public void Create_Playlist() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"REST_Assured1\",\r\n"
						+ "  \"description\": \"New playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.post("	https://api.spotify.com/v1/users/"+user_id+"/playlists");
		playlist_id = response.path("id");
		System.out.println("My user Playlist id is :" +playlist_id);
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}
	
	@Test (priority=4)
	public void Add_Items_to_Playlist() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)			 
				.queryParam("uris",	"spotify:track:16XEVyPh5NT31CAAqPbxQF,spotify:track:6rkqqLPg9Lbsdh26JMqfp0,spotify:track:6ZRzF7XiZdjnQUDYIk7w7u,spotify:track:7fyIuR4aaWb6iltlAoSkxF,spotify:track:1cpaDNciPGlC39qPs4RkMU")
//				.queryParam("position", "0")
//				.queryParam("uris0",track_uris1)
//				.queryParam("position", "1")
//				.queryParam("uris1", track_uris2)
//				.queryParam("position", "2")
//				.queryParam("uris2", track_uris3)
//				.queryParam("position", "3")
//				.queryParam("uris3", track_uris4)
//				.queryParam("position", "4")
//				.queryParam("uris4", track_uris5)
				.when()
				.post("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}
	
	@Test (priority=5)
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
	
	@Test (priority=6)
	public void Get_Playlist_Cover_Image() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=7)
	public void Get__Playlist_Items() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=8)
	public void Get_Playlist() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=9)
	public void Get_Users_Playlists() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
//	@Test (priority=10)
//	public void Update_Playlist_Items() {
//		Response response = RestAssured.given()
//				.header("Accept", "application/json")
//				.header("contentType","application/json")
//				.header("Authorization", token)
//				.queryParam("playlist_id", playlist_id)
//				.queryParam("uris", "spotify:track:16XEVyPh5NT31CAAqPbxQF")
//				.body("{\r\n"
//						+ "  \"range_start\": 1,\r\n"
//						+ "  \"insert_before\": 3,\r\n"
//						+ "  \"range_length\": 4\r\n"
//						+ "}")
//				.when()
//				.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(201);
//	}
	
	@Test (priority=11)
	public void Change_Playlist_Details() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
				.body("{\r\n"
						+ "  \"name\": \"REST_Assured_Music_Spotify\",\r\n"
						+ "  \"description\": \"Updated playlist description\",\r\n"
						+ "  \"public\": false\r\n"
						+ "}")
				.when()
				.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=18)
	public void Remove_Playlist_Items() {
		Response response = RestAssured.given()
				.header("Accept", "application/json")
				.header("contentType","application/json")
				.header("Authorization", token)
				.body("{ \"tracks\": [{ \"uri\": \"spotify:track:16XEVyPh5NT31CAAqPbxQF\" }]} ")
				.when()
				.delete("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
			//	------------------------------------Track ----------------------------------  //
	
	@Test (priority=12)
	public void Get_Tracks_Audio_Analysis() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/audio-analysis/"+track_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=13)
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
	
	@Test (priority=14)
	public void Get_Trackss_Audio_Features() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/audio-features/"+track_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=15)
	public void Get_Several_Tracks() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.queryParam("market", "IN")
				.queryParam("ids", track_id)
				.when()
				.get("	https://api.spotify.com/v1/tracks");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=16)
	public void Get_Track() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/tracks/"+track_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	//	------------------------------------Shows ----------------------------------  //	
	@Test (priority=19)
	public void Get_Several_Shows() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
//				.queryParam("q", "Arjit singh")
//				.queryParam("type", "track")
//				.queryParam("market", "IN")
//				.queryParam("limit", "5")
				.when()
				.get("https://api.spotify.com/v1/shows");
//		track_id = response.path("uri");
//		System.out.println("My track id is :" +track_id);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
}
