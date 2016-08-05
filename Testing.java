package grobid_test;

import org.grobid.core.utilities.GrobidPropertyKeys;
import org.grobid.core.data.BiblioItem;
import org.grobid.core.data.BibDataSet;
import org.grobid.core.data.PatentItem;
import org.grobid.core.data.Person;
import org.grobid.core.engines.Engine;
import org.grobid.core.factory.GrobidFactory;
import org.grobid.core.mock.MockContext;
import org.grobid.core.utilities.GrobidProperties;

import java.util.Properties;
import java.util.ArrayList;
import java.util.List; 

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;

public class Testing {

	/**
     * Command line execution.
     * 
     * @param args Command line arguments.
     */
    public static void main( String[] args ) {	
    	try {
			// context variable are read from the project property file grobid-example.properties
			Properties prop = new Properties();
			prop.load(new FileInputStream("grobid-test-ant.properties"));
			String pGrobidHome = prop.getProperty("grobid_test_ant.pGrobidHome");
			String pGrobidProperties = prop.getProperty("grobid_test_ant.pGrobidProperties");
			
			MockContext.setInitialContext(pGrobidHome, pGrobidProperties);
		    GrobidProperties.getInstance();

			System.out.println(">>>>>>>> GROBID_HOME="+GrobidProperties.get_GROBID_HOME_PATH());			
	
			Engine engine = GrobidFactory.getInstance().createEngine();	

			// test header extraction from article
			String pdfPath = "resources/Wang-paperAVE2008.pdf";
			BiblioItem resHeader = new BiblioItem();
			System.out.println(engine.processHeader(pdfPath, false, resHeader));
			
			
			List<Person> authors = new ArrayList<Person>();
			authors =  engine.processAuthorsHeader(resHeader.getAuthors()) ;
			
			
			String authorsString = resHeader.getAuthors() ;

			System.out.println(authorsString);
			System.out.println(authors);
			System.out.println(authors.get(0));
			System.out.println(authors.get(1));
		
			
	
		}
		catch (Exception e) {
	    	// If an exception is generated, print a stack trace
		    e.printStackTrace();
		}
		finally {
			try {
				MockContext.destroyInitialContext();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
    }

}

