/**
 * 
 */
package com.lrgoncalves.megasena;

import java.io.Serializable;

import javax.json.Json;
import javax.json.JsonObject;

/**
 * @author leandro_2
 *
 */
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1747102247038933371L;

	private final int FIRST_TEN;
	private final int SECOND_TEN;
	private final int THIRD_TEN;
	private final int FOUR_TEN;
	private final int FIVE_TEN;
	private final int SIXTY_TEN;

	public Result(final int ft,final int st,final int tt,final int frt,final int fvt,final int sxt) {
		FIRST_TEN = ft;
		SECOND_TEN = st;
		THIRD_TEN = tt;
		FOUR_TEN = frt;
		FIVE_TEN = fvt;
		SIXTY_TEN = sxt;
	}

	@Override
	public String toString(){
		
		return FIRST_TEN+";"+
				SECOND_TEN+";"+
				THIRD_TEN+";"+
				FOUR_TEN+";"+
				FIVE_TEN+";"+
				SIXTY_TEN;
	}

	public String  toJSON(){
		
		JsonObject json = Json.createObjectBuilder()
				.add("first-ten", FIRST_TEN)
				.add("second-ten", SECOND_TEN)
				.add("third-ten", THIRD_TEN)
				.add("fourth-ten", FOUR_TEN)
				.add("fifth-ten", FIVE_TEN)
				.add("sixth-ten", SIXTY_TEN)
				.build();
		
		return json.toString();
	}
	
	public boolean isValidToInsert(){
		if((FIRST_TEN+SECOND_TEN+THIRD_TEN+FOUR_TEN+FIVE_TEN+SIXTY_TEN)> 0){
			return true;
		}
		
		return false;
	}
	
}
