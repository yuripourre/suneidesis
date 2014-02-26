package com.medievalperson.knowledge;

import com.medievalperson.beign.Beign;
import com.medievalperson.knowledge.action.ActionParser;
import com.medievalperson.knowledge.feeling.FeelingParser;

public class SimpleParser implements Parser{
		
	private FeelingParser feelingParser = new SimpleFeelingParser();
	
	private FamilyTreeParser familyTreeParser = new FamilyTreeParser();
	
	private ActionParser actionParser = new SimpleActionParser();
	
	public SimpleParser() {
		super();
	}

	@Override
	public String parse(String query, Beign beign) {
		
		String[] parts = query.replaceAll("\\?", "").split(" ");
		
		String response = null;
		
		if("How".equalsIgnoreCase(parts[0])){
			
			response = feelingParser.parse(query, beign);
			
		}else if("Who".equalsIgnoreCase(parts[0])){
						
			response = familyTreeParser.parse(query, beign);
			
		}else if("Did".equalsIgnoreCase(parts[0])){
						
			response = actionParser.parse(query, beign);
		}
		
		if(response==null){
			response = "I do not know";
		}
		
		return response;
	}	
	
}