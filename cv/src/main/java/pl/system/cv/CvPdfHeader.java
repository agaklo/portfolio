package pl.system.cv;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class CvPdfHeader extends PdfPageEventHelper {
	private int counter = 0;
	
	Font ffontBig = new Font(Font.FontFamily.UNDEFINED, 10, Font.ITALIC);
	Font ffont = new Font(Font.FontFamily.UNDEFINED, 6, Font.ITALIC);
	
	String madeBy;
	String portfolio;
	String encoding;
	
	public CvPdfHeader(JsonObject o) throws DocumentException, IOException{
		super();
		
		
		madeBy 		= o.get("made_by").getAsString();
		portfolio 	= o.get("portfolio").getAsString();
		encoding 	= o.get("encoding").getAsString();
		
		BaseFont bfRegular = BaseFont.createFont(BaseFont.COURIER, encoding, BaseFont.EMBEDDED);
		Font ffontBig = new Font(bfRegular, 10, Font.ITALIC);
		Font ffont = new Font(bfRegular, 6, Font.ITALIC);			
	}
	
    public void onEndPage(PdfWriter writer, Document document) {
    	
    	
        PdfContentByte cb = writer.getDirectContent();
        //String headerText = "Curriculum Vitae";
        String headerText = "";
        
        Phrase header = new Phrase(headerText, ffontBig);
        Phrase verte = new Phrase("VERTE", ffontBig);
        Phrase footer = new Phrase(madeBy, ffont);
        Phrase info = new Phrase(portfolio, ffont);
        		
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                header,
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.top() + 10, 0);
        
        if(counter == 0){
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
        		verte,
                document.right()+ document.leftMargin()/2,
                document.bottom() - 5, 0);
        }
        
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
        		footer,
                document.right()+ document.leftMargin()/2,
                document.bottom() - 17, 0);
        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT,
        		info,
                document.right()+ document.leftMargin()/2,
                document.bottom() - 25, 0);
        
        
        ++counter;
    }
}
