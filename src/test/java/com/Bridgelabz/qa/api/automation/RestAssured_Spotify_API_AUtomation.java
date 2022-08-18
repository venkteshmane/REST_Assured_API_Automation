package com.Bridgelabz.qa.api.automation;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssured_Spotify_API_AUtomation {

	String token;
	String user_id;
	String playlist_id;
	String show_id = "7nhAoDPG8JvfCu49fzDsTE";
	String device_id = "4f176e862d152482e690890f51c9b9d3416f09e9";
	String track_id = "16XEVyPh5NT31CAAqPbxQF";
	String artist_id = "2AfmfGFbe0A0WsTYm0SDTx";
//	public String track_uris1="spotify:track:16XEVyPh5NT31CAAqPbxQF";
//	public String track_uris2="spotify:track:6rkqqLPg9Lbsdh26JMqfp0";
//	public String track_uris3="spotify:track:6ZRzF7XiZdjnQUDYIk7w7u";
//	public String track_uris4="spotify:track:7fyIuR4aaWb6iltlAoSkxF";
//	public String track_uris5="spotify:track:1cpaDNciPGlC39qPs4RkMU";
	
	@BeforeTest
	public void getToken() {
		token = "Bearer BQA-2uciXoGjvucvL6XGsI0Z_FswNvSaBN7eKAphnH8krFh927cKcgac-FLxh19djLnrqcKDoBy3_wEAKlcLjcpW3-NZ9eyTG7QWhyQkRZaK7sefk908rFY2C-wxXWPz9GE0x-OWX32mI47eOiHJT5B4axyzOa_7gfZrjHrVLM1c0wjnnwrQNQ88rVUJkj-qzmv9Cn80moMp3RoQRoxiIijpjviPqjlCyO4nveCWTtMNALC-ovTn5SQ7Vg6yLc3l84BXSHfppfg";
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
//		Assert.assertEquals(user_id, "31mi2phjiuagifqzvy3fbi5zmgmq");
		System.out.println("My user id is :" +user_id);
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
//		int statusCode = response.statusCode();
//		Assert.assertEquals(statusCode, 200);
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
				.queryParam("q", "Lata Mangeshkar")
				.queryParam("type", "show")
				.queryParam("market", "IN")
				.queryParam("limit", "5")
//				.pathParam("q", "Lata Mangeshkar")
//				.pathParam("type", "track")
				.when()
				.get("https://api.spotify.com/v1/search");
//				.get("https://api.spotify.com/v1/search/?q={q}&type={type}");
//		show_id = response.path("id");
//		System.out.println("My show id is :" +show_id);
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
//						+ "  \"range_start\": 0,\r\n"
//						+ "  \"insert_before\": 1,\r\n"
//						+ "  \"range_length\": 2\r\n"
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
						+ "  \"name\": \"REST_Assured_Music\",\r\n"
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
	
	//	------------------------------------ Show ----------------------------------  //	
	
	@Test (priority=19)
	public void Get_Several_Shows() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/shows/"+show_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=20)
	public void GetSeveral_Shows() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/shows/"+show_id+"/episodes");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	@Test (priority=21)
	public void Get_Shows() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("	https://api.spotify.com/v1/shows/"+show_id+"/");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	//	------------------------------------Player ----------------------------------  //
	
//	@Test (priority=22)
//	public void Get_Recently_Played_Tracks() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.when()
//				.get("https://api.spotify.com/v1/me/player/recently-played");
////		track_id = response.path("uri");
////		System.out.println("My track id is :" +track_id);
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
////	
//	@Test (priority=23)
//	public void Get_Playback_State() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.when()
//				.get("https://api.spotify.com/v1/me/player");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
	
//	@Test (priority=24)
//	public void Get_Available_Devices() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.when()
//				.get("https://api.spotify.com/v1/me/player/devices");
////		device_id = response.path("id");
////		System.out.println("My Device ID is :" +device_id);
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=25)
//	public void Get_Currently_Playing_Track() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.when()
//				.get("https://api.spotify.com/v1/me/player/currently-playing");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=26)
//	public void Skip_To_Next() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.when()
//				.post("https://api.spotify.com/v1/me/player/next/"+device_id+"/");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=27)
//	public void Skip_To_Previous() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("Device", "device_id")
//				.when()
//				.post("https://api.spotify.com/v1/me/player/previous");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=28)
//	public void Add_Item_to_Playback_Queue() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("Device", "device_id")
//				.queryParam("uris",	"spotify:track:16XEVyPh5NT31CAAqPbxQF")
//				.when()
//				.post("	https://api.spotify.com/v1/me/player/queue");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=29)
//	public void Pause_Playback() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("Device", "device_id")
//				.when()
//				.put("https://api.spotify.com/v1/me/player/pause");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=30)
//	public void Start_Resume_Playback() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("Device", "device_id")
//				.body("{\r\n"
//						+ "  \"context_uri\": \"spotify:track:16XEVyPh5NT31CAAqPbxQF\",\r\n"
//						+ "  \"offset\": {\r\n"
//						+ "    \"position\": 2\r\n"
//						+ "  },\r\n"
//						+ "  \"position_ms\": 0\r\n"
//						+ "}")
//				.when()
//				.put("https://api.spotify.com/v1/me/player/play");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=31)
//	public void Set_Repeat_Mode() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("state", "Maharashtra")
//				.queryParam("Device", "device_id")
//				.when()
//				.put("https://api.spotify.com/v1/me/player/repeat");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=32)
//	public void Seek_To_Position() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("position_ms", 2)
//				.queryParam("Device", "device_id")
//				.when()
//				.put("https://api.spotify.com/v1/me/player/seek");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=33)
//	public void Toggle_Playback_Shuffle() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("state", "Maharashtra")
//				.queryParam("Device", "device_id")
//				.when()
//				.put("	https://api.spotify.com/v1/me/player/shuffle");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=34)
//	public void Transfer_Playback() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.body("{\r\n"
//						+ "  \"device_ids\": [\r\n"
//						+ "    \"4f176e862d152482e690890f51c9b9d3416f09e9\"\r\n"
//						+ "  ]\r\n"
//						+ "}")
//				.when()
//				.put("https://api.spotify.com/v1/me/player");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
//	
//	@Test (priority=35)
//	public void Set_Playback_Volume() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.queryParam("volume_percent", "50")
//				.queryParam("Device", "device_id")
//				.when()
//				.put("https://api.spotify.com/v1/me/player/shuffle");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
	
	
		//	------------------------------------Personalization ----------------------------------  //
	
//	@Test (priority=36)
//	public void Get_Users_Top_Items() {
//		Response response = RestAssured.given()
//				.header("Accept","application/json")
//				.header("ContentType","application/json")
//				.header("Authorization", token)
//				.when()
//				.get("https://api.spotify.com/v1/me/top/"+artist_id+"/");
//		response.prettyPrint();
//		response.then().assertThat().statusCode(200);
//	}
	
		//	------------------------------------ Markets ----------------------------------  //
	
	@Test (priority=37)
	public void Get_Available_Markets() {
		Response response = RestAssured.given()
				.header("Accept","application/json")
				.header("ContentType","application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/markets");
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
	}
	
	
	
	
	
}
