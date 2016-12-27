package com.readify.test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.readify.test.MyResource;

public class MyResourceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(MyResource.class);
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testGetIt() {
		final String responseMsg = target().path("api").request()
				.get(String.class);

		assertEquals("Hello, Heroku!", responseMsg);

	}
	
	@Test
	public void testGetToken() {
		final String responseMsg = target().path("api/token").request()
				.get(String.class);
		String token = "\"d305d55d-dee8-44ff-837f-b080a3f6a4b0\"";
		assertEquals(token , responseMsg);

	}
	
	@Test
	public void testGetEquilateralTriangle() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", 5).queryParam("b", 5).queryParam("c", 5).request()
				.get(String.class);
		String EquilateralTriangle = "\"Equilateral\"";
		assertEquals(EquilateralTriangle , responseMsg);

	}
	
	@Test
	public void testGetIsoscelesTriangle() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", 3).queryParam("b", 3).queryParam("c", 4).request()
				.get(String.class);
		String EquilateralTriangle = "\"Isosceles\"";
		assertEquals(EquilateralTriangle , responseMsg);

	}
	
	@Test
	public void testGetScaleneTriangle() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", 5).queryParam("b", 6).queryParam("c", 7).request()
				.get(String.class);
		String EquilateralTriangle = "\"Scalene\"";
		assertEquals(EquilateralTriangle , responseMsg);

	}
	
	@Test
	public void testGetTriangleWithHighestCounts() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", Integer.MAX_VALUE).queryParam("b", Integer.MAX_VALUE).queryParam("c", Integer.MAX_VALUE).request()
				.get(String.class);
		String EquilateralTriangle = "\"Equilateral\"";
		assertEquals(EquilateralTriangle , responseMsg);

	}
	
	@Test
	public void testGetTriangleWithExceedingLimits() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", "2147483647").queryParam("b", "2147483647").queryParam("c", "2147483649").request()
				.get(String.class);
		
		
		String invalidRequest = "{\"message\":\"The request is invalid.\"}";
		assertEquals(invalidRequest , responseMsg);

	}
	
	@Test
	public void testGetTriangleWithNegativeValues() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", "-1").queryParam("b", "2147483647").queryParam("c", "-2").request()
				.get(String.class);
		
		
		String invalidRequest = "\"Error\"";
		assertEquals(invalidRequest , responseMsg);

	}
	
	@Test
	public void testGetNonDrawableTriangle() {
		final String responseMsg = target().path("api/triangletype")
				.queryParam("a", "3").queryParam("b", "2").queryParam("c", "1").request()
				.get(String.class);
		
		
		String invalidRequest = "\"Error\"";
		assertEquals(invalidRequest , responseMsg);

	}
	
	@Test
	public void testGetFab() {
		final String responseMsg = target().path("api/fibonacci").queryParam("n", 90).request()
				.get(String.class);
		String fab = "2880067194370816120";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetFabForNegative() {
		final String responseMsg = target().path("api/fibonacci").queryParam("n", -9).request()
				.get(String.class);
		String fab = "34";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetFabForOutOfProgramaticRange() {
		final String responseMsg = target().path("api/fibonacci").queryParam("n", 100).request()
				.get(String.class);
		String fab = "";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetFabForOutOfIntegerRange() {
		final String responseMsg = target().path("api/fibonacci").queryParam("n", "21474836475").request()
				.get(String.class);
		String fab = "";
		assertEquals(fab , responseMsg);

	}
		
	@Test
	public void testGetRevWord() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", "human").request()
				.get(String.class);
		String fab = "\"namuh\"";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetRevWordPreSpace() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", " human").request()
				.get(String.class);
		String fab = "\" namuh\"";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetRevWordPostSpace() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", "human ").request()
				.get(String.class);
		String fab = "\"namuh \"";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetRevSentence() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", "i am alive").request()
				.get(String.class);
		String fab = "\"i ma evila\"";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetRevWordWithSpecialChars() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", "hey?there!").request()
				.get(String.class);
		String fab = "\"!ereht?yeh\"";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetRevSentenceWithMultiSpaces() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", "hi there   how       are       you").request()
				.get(String.class);
		String fab = "\"ih ereht   woh       era       uoy\"";
		assertEquals(fab , responseMsg);

	}
	
	@Test
	public void testGetRevWordEmpty() {
		final String responseMsg = target().path("/api/reversewords").queryParam("sentence", "").request()
				.get(String.class);
		String fab = "\"\"";
		assertEquals(fab , responseMsg);

	}
}
