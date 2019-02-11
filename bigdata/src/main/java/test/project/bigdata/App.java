package test.project.bigdata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webhoseio.sdk.WebhoseIOClient;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class App {
	
	public static ArrayList<String> txt = new ArrayList<String>();
	public static ArrayList<String> url = new ArrayList<String>();
	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> published = new ArrayList<String>();
	
	
	public static ArrayList<String> checkner = new ArrayList<String>();

	public static ArrayList<String> checkners2 = new ArrayList<String>();
	
	public static ArrayList<Integer> urles = new ArrayList<Integer>();
	public static ArrayList<String> urls = new ArrayList<String>();
	
	public static Set<String> hashSet = new HashSet<String>();
	public static Set<String> hashSet1 = new HashSet<String>();
	public static Set<Integer> hashset = new HashSet<Integer>();
	
	public static ArrayList<String> ne = new ArrayList<String>();
	public static ArrayList<String> nes = new ArrayList<String>();

	
	public static String nee="";
	
	
	public static String searchs="";
	static JsonElement ss;
	public static int z , m=0;


	public static String[] mcount=new String[1000];
	
	
	public static int[] tcount=new int[1000];
	public static  ArrayList<String> ucount = new ArrayList<String>();
	
	
	public static void webhose(String search,String search1) throws Exception, IOException {
		
		String searchq=search+" language:english site_type:news site_category:media";
		String searcht=search1;
		
		WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("4a37c0cb-5489-4016-a925-6a5d37c1ac7e");
		
        Map<String, String> queries = new HashMap<String, String>();
        
        queries.put("q",searchq);
        queries.put("ts", "1523642416246");
        queries.put("sort", searcht);

        JsonElement result = webhoseClient.query("filterWebContent", queries);
        
        System.out.println(" Total Number of Articles Found = "+result.getAsJsonObject().
        		get("totalResults"));     // Print posts count
        
        JsonArray postArray = result.getAsJsonObject().getAsJsonArray("posts");
        
        for(JsonElement o  : postArray) {
        
        	url.add((o.getAsJsonObject().get("url").toString()));
        	title.add((o.getAsJsonObject().get("title").toString()));
        	published.add((o.getAsJsonObject().get("published").toString()));
        	txt.add((o.getAsJsonObject().get("text").toString()));
        	
        	
	}
        
        }
	
	


	public static void nlp(String nlp,String searchi) throws Exception, IOException {
	
		
	
		//creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
	Properties props = new Properties();
	props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
	
	
	//High             //props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
	//Computation      // set a property for an annotator, in this case the coref annotator is being set to use the neural algorithm
	//Requirements     //props.setProperty("coref.algorithm", "neural");
			
	StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

	// read some text in the text variable
	String text = nlp;
	
	// create an empty Annotation just with the given text
	Annotation document = new Annotation(text);

	// run all Annotators on this text
	pipeline.annotate(document);
	
	List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
	
	String tocs="",tocs1="",tocs2=""; 
	
	for (CoreMap sentence : sentences) {
	    // traversing the words in the current sentence
	    // a CoreLabel is a CoreMap with additional token-specific methods
	
		for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
	        // this is the text of the token
	        String word = token.get(CoreAnnotations.TextAnnotation.class);
	        // this is the POS  tag of the token
	        //String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
	        // this is the NER label of the token
	        String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
	        
	        //System.out.println(String.format("Print: word:{%s} pos: {%s} ner: {%s}",word,pos,ner));
	        
	        if(word.equalsIgnoreCase(searchs)) {
	        	tocs=word;
	        	z++;
	        }
	        if(searchs.startsWith(word)) {
	        	tocs1=word;
	        	z++;
	        }
	        if(searchs.endsWith(word)) {
	        	tocs2=word;
	        	z++;
	        }
	        if(checkner.contains(ner)) {
	        	if(hashSet1.contains(word)) {tcount[checkners2.indexOf(word)]=tcount[checkners2.indexOf(word)]+1;}
	        		else {hashSet1.add(word);checkners2.add(word);}
	        	
	        
	        }
	        
	        
	        }
		
	    }
	nee=searchi+".";
    for(int l=0;l<checkners2.size();l++) {
    
    	nee=nee+"."+checkners2.get(l);
    
    	nes.add(nee);
    	
    	nee=searchi;
    }
	
	
    ne.removeAll(ne);
    nee="";
	}
	
	
	public static void webhos(String searches,String searchi) throws Exception, IOException, ArrayIndexOutOfBoundsException, NullPointerException {
		
		urles.add(urls.size());
		urls.add(searches);
	    
	for(int w=0;w<1000;w++) {mcount[w]="1";}
		
			String searchees=searches+" language:english site_type:news site_category:media";
			
			WebhoseIOClient webhoseClient = WebhoseIOClient.getInstance("4a37c0cb-5489-4016-a925-6a5d37c1ac7e");
	    
			Map<String, String> queries = new HashMap<String, String>();
	        
			queries.put("q",searchees);
	        queries.put("ts", "1523642416246");
	        queries.put("sort", "relevancy");

	        JsonElement result = webhoseClient.query("filterWebContent", queries);
	        
	        ss=result.getAsJsonObject().get("totalResults");     // Print posts count
	        ucount.add(ss.toString());m++;
	        JsonArray postArray = result.getAsJsonObject().getAsJsonArray("posts");
	       
	        for(JsonElement o  : postArray) {
	        	
	        	urls.add((o.getAsJsonObject().get("url").toString()));
	        	
	        	
	        	
	        	
		}
	        
	        
	        
	        }
	
	
	
	public static void main(String[] args) throws Exception, IOException, ArrayIndexOutOfBoundsException {

		System.out.println("Hi");
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter The Topic You would like to search about...");
		searchs=sc.nextLine();
		String search2="relevancy";
		
		webhose(searchs,search2);
		
		

		System.out.println("Press Enter to Start NLP...!");
		sc.nextLine();
		


checkner.add("PERSON");
checkner.add("ORGANIZATION");
checkner.add("CITY");
checkner.add("LOCATION");
checkner.add("NAATIONALITY");
checkner.add("TITLE");

		
		nlp(txt.get(0),searchs); //to get named entity recognition and ner words count pie
		
		int i=0;
		
		if(checkners2.size()<30) {i=checkners2.size();}
		else {i=30;}
		
		for(int j=0;j<i;j++) {
			webhos(checkners2.get(j),searchs);  //to get count of urls count for each ner object bar
			
		}
		

		File pie=new File("C:/Users/chvkr/Desktop/Spring 2018/Data Visualization/Projects/Term Project/1/Pie.csv");
		File bar=new File("C:/Users/chvkr/Desktop/Spring 2018/Data Visualization/Projects/Term Project/1/Bar.csv");
		File cloud=new File("C:/Users/chvkr/Desktop/Spring 2018/Data Visualization/Projects/Term Project/1/d3wordcloudmaster/example/example.words.js");
		File dd=new File("C:/Users/chvkr/Desktop/Spring 2018/Data Visualization/Projects/Term Project/1/Bubble.csv");

		BufferedWriter outputWriter = null;
		BufferedWriter outputWriter1 = null;
		BufferedWriter outputWriter2 = null;
		BufferedWriter outputWriter3 = null;
		
		outputWriter = new BufferedWriter(new FileWriter(pie));
		outputWriter1 = new BufferedWriter(new FileWriter(bar));
		outputWriter2 = new BufferedWriter(new FileWriter(cloud));
		outputWriter3 = new BufferedWriter(new FileWriter(dd));
		int p=0;
		String q="";
		//printing data for pie chart ie ners and words count
		outputWriter.write("ners,count");
		outputWriter.newLine();
		for(int x=0;x<checkners2.size()-1;x++) {
			outputWriter.write(checkners2.get(x));
			outputWriter.write(",");
			p=tcount[x]*5+2; q=Integer.toString(p);
			outputWriter.write(q);
			outputWriter.newLine();
		}
		outputWriter.close();
		System.out.println("Pie.csv File Ready..!");
		
		
		//printing data for bar chart ie  ners and urls count
		
		outputWriter1.write("ners,count");
		outputWriter1.newLine();
		for(int y=0;y<i-1;y++) {
			outputWriter1.write(checkners2.get(y));
			outputWriter1.write(",");
			outputWriter1.write(ucount.get(y));
			outputWriter1.newLine();
		}
		outputWriter1.close();
		System.out.println("Bar.csv File Ready..!");
		
		
		//printing data for dd ie ne and ucount
		
		outputWriter3.write("id,value");
		outputWriter3.newLine();
		for(int u=0;u<ucount.size()-1;u++) {
			outputWriter3.write(nes.get(u));
			outputWriter3.write(",");
			outputWriter3.write(ucount.get(u));
			outputWriter3.newLine();
			
		}
		outputWriter3.close();
		System.out.println("Bubble.csv File Ready..!");
		
		
		//printing data for word cloud ie 
    
		outputWriter2.write("var words = [");
		outputWriter2.newLine();
		for(int v=0;v<checkners2.size()-1;v++) {
			outputWriter2.write( "{text: '");
			outputWriter2.write(checkners2.get(v));
			outputWriter2.write("', size: ");
			p=tcount[v]*5+5; q=Integer.toString(p);
			outputWriter2.write(q);
			outputWriter2.write(", href: ");
			outputWriter2.write(urls.get(urls.indexOf(checkners2.get(v))+1));
			outputWriter2.write("},");
			outputWriter2.newLine();
			
		}
		outputWriter2.write("];");
		outputWriter2.close();
		System.out.println("Cloud.js File Ready..!");
		
sc.close();
		
System.out.println("My Score = "+z);
System.out.println();
System.out.println("		Completed..!  ;) ");
	}
}