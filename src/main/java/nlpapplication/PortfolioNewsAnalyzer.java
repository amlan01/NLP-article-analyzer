package nlpapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.StringUtils;

public class PortfolioNewsAnalyzer{
	
	private HashSet<String> portfolio;
	
	//model or trainer to be used for POS tagging-assigned as reference
	private static final String modelPath = "edu\\stanford\\nlp\\models\\pos-tagger\\english-left3words\\english-left3words-distsim.tagger";
    private MaxentTagger tagger;
    
    
    //constructor for POS tagging and adding a company name to Hash set
    public PortfolioNewsAnalyzer(){
    	
    	tagger = new MaxentTagger(modelPath);
    	
    	portfolio = new HashSet<String>();
    }
    
    
    //MaxentTagger after initialized as constructor tagString method is called for tagging
    //text String
    public String tagPos(String input){
    	return tagger.tagString(input);
    }
    
    
    //we need to tag out proper nouns only - query out company names from input data set
    //splitting and storing of proper nouns in a Hashset - no duplicate entries
    //split by empty space
    //then split by _ (underscore)
    //store only if proper noun-'NNP'
    //in case of proper nouns like Carl Zeiss
    //we get them as separate-however we need to combine them as one proper noun
    //for this we capture consecutive proper nouns & store as one,only after joining
    public static HashSet<String> extractProperNouns(String taggedOutput) {
    	   HashSet<String> propNounSet = new HashSet<String>();
    	   String[] split = taggedOutput.split(" ");
    	   List<String> propNounList = new ArrayList<String>();
    	   for (String token: split ){
    	       String[] splitTokens = token.split("_");
    	       if(splitTokens[1].equals("NNP")){
    	           propNounList.add(splitTokens[0]);
    	       } else {
    	           if (!propNounList.isEmpty()) {
    	               propNounSet.add(StringUtils.join(propNounList, " "));
    	               propNounList.clear();
    	           }
    	       }
    	   }
    	   if (!propNounList.isEmpty()) {
    	       propNounSet.add(StringUtils.join(propNounList, " "));
    	       propNounList.clear();
    	   }
    	   return propNounSet;
    	}
    
    
    	public void addPortfolioCompany(String company) {
    	   portfolio.add(company);
    	}
    	
    	public boolean arePortfolioCompaniesMentioned(HashSet<String> articleProperNouns){
    	   return !Collections.disjoint(articleProperNouns, portfolio);
    	}
	
}