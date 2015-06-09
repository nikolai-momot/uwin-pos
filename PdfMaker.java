package Project.java;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class PdfMaker {
	//this static void main is for testing purposes only
	public static void main(String[] args) {
		createPDF("testpdf.pdf", "This is a sample test\n"
				+ "This is another line");//this is an example of how you would send in info to createPDF()
	}//the way you create new lines if by placing the '\n' at the end of the string

	public static void createPDF(String filename, String contentText) {
		PDDocument doc = null;
		PDPage page = null;

		try{
			doc = new PDDocument();
			page = new PDPage();

			doc.addPage(page);
			PDFont font = PDType1Font.HELVETICA_BOLD;

			PDPageContentStream content = new PDPageContentStream(doc, page);
			content.beginText();
			content.setFont( font, 12 );
			content.moveTextPositionByAmount( 150, 700 );
			
			String[] lines = contentText.split("\n");
			for (String line : lines) {
				content.moveTextPositionByAmount( 0, -20 );
				content.drawString(line);
			}
			//	           content.addLine(0, -20, 300, -20);        

			content.endText();
			content.close();
			doc.save(filename);
			doc.close();
		} catch (Exception e){
			System.out.println(e);
		}
	}
}
