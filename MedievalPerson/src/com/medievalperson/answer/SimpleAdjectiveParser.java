package com.medievalperson.answer;

import com.medievalperson.adjectives.Adjective;
import com.medievalperson.answer.adjective.AdjectiveParser;
import com.medievalperson.beign.Being;

public class SimpleAdjectiveParser implements AdjectiveParser {

	@Override
	public String parse(String query, Being beign) {
		
		String[] parts = query.replaceAll("\\?", "").split(" ");
		
		String adjectiveQuery = parts[2];
		
		if(query.contains("you")) {
						
			for(Adjective adjective: beign.getAdjectives()) {
				
				if(adjective.getName().equalsIgnoreCase(adjectiveQuery)) {
			
					return "Yes, I am "+adjectiveQuery+".";
					
				}
				
			}
			
			return "No, I am not "+adjectiveQuery+".";
			
		}
		
		return null;
		
	}
	
}