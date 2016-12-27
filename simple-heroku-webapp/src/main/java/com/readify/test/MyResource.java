package com.readify.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 * 
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Hello, Heroku!";
	}


	/**
	 * Retrieves representation of an instance of Service
	 * 
	 * @return an instance of Response
	 */

	@GET
	@Path("token")
	@Produces("application/json")
	public Response getToken() {
		String result = "\"d305d55d-dee8-44ff-837f-b080a3f6a4b0\"";

		return Response.status(200).entity(result).build();
	}

	/**
	 * reverseSentense provided by client and pass it back
	 * 
	 * @return an instance of Response
	 */

	@GET
	@Produces("application/json")
	@Path("reversewords")
	public Response reverseSentense(@Context UriInfo info) {

		String sentence = info.getQueryParameters().getFirst("sentence");
		StringBuilder sentenceBuilder = new StringBuilder();
		StringBuilder wordBuilder = new StringBuilder();
		char[] charSentence = sentence.toCharArray();
		List<StringBuilder> result = new ArrayList<StringBuilder>();
		boolean newWordFlag = true;
		for (int i = 0; i < charSentence.length; i++) {

			if (charSentence[i] == ' ') {
				if (newWordFlag) {
					newWordFlag = false;
					result.add(wordBuilder);
					wordBuilder = new StringBuilder();
				}

			} else {

				if (!newWordFlag) {
					newWordFlag = true;
					result.add(wordBuilder);
					wordBuilder = new StringBuilder();
				}
			}

			wordBuilder.append(charSentence[i]);

		}
		result.add(wordBuilder);

		for (Iterator<StringBuilder> iterator = result.iterator(); iterator
				.hasNext();) {
			StringBuilder stringBuilder = iterator.next();
			// System.out.println(stringBuilder);
			sentenceBuilder.append(stringBuilder.reverse());

		}
		String reversedSentense = sentenceBuilder.toString();
		reversedSentense.replaceAll("\"", "\\\"");
		return Response.status(200).entity("\"" + reversedSentense + "\"")
				.build();

	}

	

	@GET
	@Path("fibonacci")
	@Produces("application/json")
	public Response fibonacciSeries(@QueryParam("n") String n) {

		String result = "";

		int num = 0;
		try {
			num = Integer.valueOf(n);
		} catch (NumberFormatException e) {
			System.err.println("NumberFormatException");
			e.printStackTrace();
			/*JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "The request is invalid.");
			String res = jsonObject.toString();*/
			return Response.status(200).entity(result).build();
		}

		if (num <= 92 && num >= -92) {
			result = ReadifyUtility.fibonacciDynamicProgramming(Math.abs(num)) + ""; // getting fibonacci for absolute value as done on sample API
			return Response.status(200).entity(result).build();
			// return result+"";
		} else {
			return Response.status(200).entity(result).build();
		}

	}

	/**
	 * Calculates triangletype with input of 3 sides
	 * 
	 * 
	 * @return Response
	 */
	
	@GET
	@Path("triangletype")
	@Produces("application/json")
	public Response calculateTriangle(@QueryParam("a") String a,
			@QueryParam("b") String b, @QueryParam("c") String c) {
		
		String result = "Error";

		int a_side = 0;
		int b_side = 0;
		int c_side = 0;
		try {
			a_side = Integer.valueOf(a);
			b_side = Integer.valueOf(b);
			c_side = Integer.valueOf(c);

			if (!ReadifyUtility.validateInputs(a_side, b_side, c_side)) { 
				return Response.status(200).entity("\"" + result + "\"")
						.build();
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.err.println("NumberFormatException");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "The request is invalid.");
			String res = jsonObject.toString();
			return Response.status(200).entity(res).build();
		}

		try {
			result = ReadifyUtility.calculateTriangle(a_side, b_side, c_side);
			return Response.status(200).entity("\"" + result + "\"").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Exception");
			e.printStackTrace();
			return Response.status(200).entity("\"" + e.getMessage() + "\"")
					.build();
		}

	}

	
	
	
	

}
