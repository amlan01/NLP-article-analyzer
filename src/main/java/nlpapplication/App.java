package nlpapplication;

import java.util.HashSet;


public class App {
	
	public static void main(String[] args) throws java.io.IOException,org.xml.sax.SAXException,
		de.l3s.boilerpipe.BoilerpipeProcessingException{
		
		String urlString = "http://www.reuters.com/article/us-essilor-m-a-luxottica-group-idUSKBN14Z110";
		
		String text = BoilerPipeExtractor.extractFromUrl(urlString);
		
		//output for extracted text from web source
		System.out.println(text);
		
		//object initialization for POS tagging
		PortfolioNewsAnalyzer obj1 = new PortfolioNewsAnalyzer();
		//obj1.tagPos(text);
		
		//output from the POS tagger-MaxentTagger whether string is proper noun,verb,noun
		System.out.println(obj1.tagPos(text));
		
		//splitting string after tagging if proper noun:NNP and storing in Hashset
		HashSet<String> output = PortfolioNewsAnalyzer.extractProperNouns(obj1.tagPos(text));
		System.out.println("\n" + output);
		
		//adding name of company to hash set: to check if references of particular interest
		//is made - name of city,country,company
		obj1.addPortfolioCompany("Luxottica");
		
		boolean mentioned = obj1.arePortfolioCompaniesMentioned(output);
		
		if(mentioned){
			System.out.println("\nArticle mentions our listed reference");
		}
		else{
			System.out.println("\nArticle does not mention any of our references");
		}
		
	}

}
