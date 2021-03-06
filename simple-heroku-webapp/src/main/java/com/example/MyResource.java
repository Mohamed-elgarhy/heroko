package com.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

	/*
	 * @POST
	 * 
	 * @Produces(MediaType.TEXT_XML) public Response getPost() { JSONObject
	 * jsonObject = new JSONObject(); jsonObject.put("message", "api"); String
	 * res = jsonObject.toString(); return
	 * Response.status(200).entity(res).build(); User user = new User();
	 * user.setUsername("mkyong"); user.setPassword("password");
	 * user.setPin(123456);
	 * 
	 * //return user; return Response.status(200).entity(user).build(); }
	 */

	public static double FibonacciRecursive(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		else {
			double result = FibonacciRecursive(n - 1)
					+ FibonacciRecursive(n - 2);
			return result;
		}

	}

	/**
	 * Retrieves representation of an instance of Service
	 * 
	 * @return an instance of String
	 */

	@GET
	@Path("Token")
	@Produces("application/json")
	public Response getToken() {
		String result = "\"d305d55d-dee8-44ff-837f-b080a3f6a4b0\"";

		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("token")
	@Produces("application/json")
	public Response getToken1() {
		String result = "\"d305d55d-dee8-44ff-837f-b080a3f6a4b0\"";

		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("Token")
	@Produces("application/json")
	public Response getTokenPost() {
		String result = "\"d305d55d-dee8-44ff-837f-b080a3f6a4b0\"";

		return Response.status(200).entity(result).build();
	}

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
		return Response.status(200)
				.entity("\"" + reversedSentense + "\"").build();

	}

	public BigDecimal fibonacciDynamicProgramming(int x) {
		BigDecimal a = new BigDecimal(0);
		BigDecimal b = new BigDecimal(1);
		for (int i = 0; i < x; i++) {
			BigDecimal c = a.add(b);// System.out.println(c);
			a = b;
			b = c;
		}
		return a;
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
			// TODO Auto-generated catch block
			System.err.println("NumberFormatException");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("message", "The request is invalid.");
			String res = jsonObject.toString();
			return Response.status(200).entity(res).build();
		}

		if (num <= 92 && num >= -92) {
			result = fibonacciDynamicProgramming(Math.abs(num)) + "";
			return Response.status(200).entity(result).build();
			// return result+"";
		} else {
			return Response.status(200).entity(result).build();
		}

	}

	@GET
	@Path("triangletype")
	@Produces("application/json")
	public Response calculateTriangle(@QueryParam("a") String a,
			@QueryParam("b") String b, @QueryParam("c") String c ) {
		// TODO Auto-generated method stub
		/*
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("f value",
		 * n); String result = jsonObject.toString(); return
		 * Response.status(200).entity(result).build();
		 */
		String result = "Error";
		
		//System.out.println(Integer.valueOf(a));

		int a_side = 0;
		int b_side = 0;
		int c_side = 0;
		try {
			a_side = Integer.valueOf(a);
			b_side = Integer.valueOf(b);
			c_side = Integer.valueOf(c);
			
			if (!validateInputs(a_side,b_side,c_side))
			{
				return Response.status(200).entity("\"" + result + "\"").build(); 
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
			result = calculateTriangle(a_side, b_side, c_side);
			return Response.status(200).entity("\"" + result + "\"").build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Exception");
			return Response.status(200).entity("\"" + e.getMessage() + "\"")
					.build();
		}

	}

	private boolean validateInputs(int a, int b, int c) {
		boolean result = true ;
		
		if (a < 1 || b < 1 || c < 1)
		{
			result = false;
		}
		
		return result;
		
	}

	private String calculateTriangle(long a, long b, long c) throws Exception {

		if ((a + b) <= c || (a + c) <= b || (b + c) <= a) {
			throw new Exception("Error"); // FIXME
		}
		if (a == b && b == c) {
			return "Equilateral";
		} else if ((a == b && b != c) || (a == c && c != b)
				|| (b == c && c != a)) {
			return "Isosceles";
		} else {
			return "Scalene";
		}
	}

}
