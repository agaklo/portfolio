package pl.system.cv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class CvPdf {
	final float SHEDULE_H_DELIMITER_WIDTH = 1f;
	final int SHEDULE_TEXT_INDENT = 5;
	
	final int SPACING_AFTER = 5;
	
	final int FONT_SMALL_SIZE = 10;
	final int FONT_REGULAR_SIZE = 12;
	final int FONT_INC_SIZE = 14;
	final int FONT_2INC_SIZE = 18;
		
	Font FONT_NORMAL_SMALL_SIZE = null;
	Font FONT_ITALIC_SMALL_SIZE = null;
	Font FONT_BOLD_SMALL_SIZE  = null;
	Font FONT_BOLDITALIC_SMALL_SIZE  = null;
		
	Font FONT_NORMAL_REG_SIZE  = null;
	Font FONT_BOLD_REG_SIZE  = null;
	Font FONT_BOLDITALIC_REG_SIZE  = null;
		
	Font FONT_NORMAL_INC_SIZE  = null;
	Font FONT_BOLD_INC_SIZE  = null;
	Font FONT_BOLDITALIC_INC_SIZE  = null;
	
	Font FONT_NORMAL_2INC_SIZE  = null;
	Font FONT_BOLD_2INC_SIZE  = null;
	Font FONT_BOLDITALIC_2INC_SIZE  = null;
	
	Font FONT_LEAD  = null;
	
	void createFonts(String encoding) throws DocumentException, IOException{
		BaseFont bfRegular = BaseFont.createFont(BaseFont.HELVETICA, encoding, BaseFont.EMBEDDED);
		BaseFont bfItalics = BaseFont.createFont(BaseFont.HELVETICA, encoding, BaseFont.EMBEDDED);
		BaseFont bfBold = BaseFont.createFont(BaseFont.HELVETICA, encoding, BaseFont.EMBEDDED);
		BaseFont bfBoldItalics = BaseFont.createFont(BaseFont.HELVETICA, encoding, BaseFont.EMBEDDED);
		
		FONT_NORMAL_SMALL_SIZE 	= new Font(bfRegular, FONT_SMALL_SIZE);
		FONT_NORMAL_REG_SIZE 	= new Font(bfRegular, FONT_REGULAR_SIZE);
		FONT_NORMAL_INC_SIZE 	= new Font(bfRegular, FONT_INC_SIZE);
		FONT_NORMAL_2INC_SIZE 	= new Font(bfRegular, FONT_2INC_SIZE);
		FONT_LEAD 				= new Font(bfRegular, 26);
		
		FONT_ITALIC_SMALL_SIZE 	= new Font(bfItalics, FONT_SMALL_SIZE, Font.ITALIC);

		FONT_BOLD_SMALL_SIZE 	= new Font(bfBold, FONT_SMALL_SIZE, Font.BOLD);		
		FONT_BOLD_REG_SIZE 		= new Font(bfBold, FONT_REGULAR_SIZE, Font.BOLD);
		FONT_BOLD_INC_SIZE 		= new Font(bfBold, FONT_INC_SIZE, Font.BOLD);
		FONT_BOLD_2INC_SIZE 	= new Font(bfBold, FONT_2INC_SIZE, Font.BOLD);
		
		FONT_BOLDITALIC_SMALL_SIZE 	= new Font(bfBoldItalics, FONT_SMALL_SIZE, Font.BOLDITALIC);
		FONT_BOLDITALIC_REG_SIZE 	= new Font(bfBoldItalics, FONT_REGULAR_SIZE, Font.BOLDITALIC);
		FONT_BOLDITALIC_INC_SIZE 	= new Font(bfBoldItalics, FONT_INC_SIZE, Font.BOLDITALIC);	
		FONT_BOLDITALIC_2INC_SIZE 	= new Font(bfBoldItalics, FONT_2INC_SIZE, Font.BOLDITALIC);
	}
	
	static Image readImage(String resourcePath, String fileName) throws BadElementException, MalformedURLException, IOException {
		return Image.getInstance(resourcePath+fileName);
	}

	private void _addParagraph(Document d, String text, int  alignement, Font font) throws DocumentException{
		Paragraph p;
		p = new Paragraph( text , font);
		p.setAlignment(alignement);
		d.add(p);
	}
	
	public PdfPCell getCell(String value, int alignment) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        Paragraph p = new Paragraph(value, FONT_NORMAL_REG_SIZE);
        p.setAlignment(alignment);
        cell.addElement(p);
        return cell;
    }

	public void _addBiCellSpacerSpacer(PdfPTable table){
			{
				PdfPCell c = new PdfPCell();
				c.setBorder(Rectangle.NO_BORDER);
				c.addElement( Chunk.NEWLINE );
		        table.addCell(c);
			}
			{
				PdfPCell c = new PdfPCell();
				c.setBorder(Rectangle.NO_BORDER);
				c.addElement( Chunk.NEWLINE );
		        table.addCell(c);
			}
	}
	
	public void _addScheduleEntry(PdfPTable table, JsonObject entry){

		{
			PdfPCell leftCell = new PdfPCell();
			leftCell.setBorder(Rectangle.NO_BORDER);
			leftCell.setUseAscender(true);
			leftCell.setUseDescender(true);
	        
			Paragraph p = new Paragraph(
					entry.get("since").getAsString()+"-"+entry.get("to").getAsString(), FONT_NORMAL_REG_SIZE);
			p.setIndentationRight(SHEDULE_TEXT_INDENT);
	        p.setAlignment(Element.ALIGN_RIGHT);
	        leftCell.addElement(p);
	        
	        
	        table.addCell(leftCell);
		}
		
		{
	    	PdfPCell rightCell = new PdfPCell();
	    	rightCell.setBorder(Rectangle.LEFT);
	    	rightCell.setUseAscender(true);
	    	rightCell.setUseDescender(true);
	    	rightCell.setBorderWidthLeft(SHEDULE_H_DELIMITER_WIDTH);
	    	{	    	    		    	
		    	Paragraph p = new Paragraph(  );
		    	p.setAlignment(Element.ALIGN_LEFT);
		    	p.setIndentationLeft(SHEDULE_TEXT_INDENT);
		        p.setSpacingAfter(2);		
				p.add(new Chunk(entry.get("company").getAsString(), FONT_BOLDITALIC_SMALL_SIZE));
				
				JsonElement position = entry.get("position"); 
				if( position != null){
					p.add(new Chunk(" ("+position.getAsString()+")", FONT_BOLDITALIC_SMALL_SIZE));
				}
		        rightCell.addElement(p);
	    	}
	    	
	    	for(JsonElement e: entry.getAsJsonArray("projects")){
	    		JsonObject o = e.getAsJsonObject();
	    		JsonElement technologies = o.get("technologies");
	    		
				Paragraph p = new Paragraph(  );
				
				//p.setSpacingBefore(10);
				if(technologies == null){		    		
					p.setSpacingAfter(SPACING_AFTER);
				}else{
					p.setSpacingAfter(2);
				}
				
				p.add(new Chunk(o.get("project").getAsString(), FONT_BOLD_REG_SIZE));
				p.add(new Chunk(" - "));
				p.add(new Chunk(o.get("text").getAsString(), FONT_NORMAL_REG_SIZE));
				
				p.setIndentationLeft(SHEDULE_TEXT_INDENT);
		        p.setAlignment(Element.ALIGN_LEFT);
		        rightCell.addElement(p);
		        
		        if(technologies != null){
		        	Paragraph tP = new Paragraph(technologies.getAsString(),FONT_ITALIC_SMALL_SIZE);
		        	tP.setSpacingAfter(SPACING_AFTER);								
					tP.setAlignment(Element.ALIGN_RIGHT);
			        rightCell.addElement(tP);			        	
		        }		    		
		        
		        //rightCell.addElement( Chunk.NEWLINE );
			
	    	}
	        
	        table.addCell(rightCell);
		}
	}

	
	
	
	
	public void AddTableWithVerticalDelimiters(Document d, JsonElement element) throws DocumentException{
		Paragraph p = new Paragraph( element.getAsJsonObject().get("title").getAsString(), FONT_BOLDITALIC_INC_SIZE );
		LineSeparator underline = new LineSeparator();
		underline.setOffset(-2);
		p.setSpacingBefore(10);
        p.setSpacingAfter(10);	        
		p.add(underline);
		d.add(p);

		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(SPACING_AFTER);
        //table.setSpacingAfter(SPACING_AFTER);
        table.setWidths(new int[]{1, 5});
        
        boolean first = true;
        JsonArray entries = element.getAsJsonObject().get("entries").getAsJsonArray();
        for(JsonElement e: entries ){
        	if( first ){
        		first = false;
        	}
        	else{
        		_addBiCellSpacerSpacer( table );
        	}
        	
    		JsonObject o = e.getAsJsonObject();		
    		_addScheduleEntry( table, o );
    		
        }
        
        d.add(table);		
	}
	

	public void _addLabeledEntry(PdfPTable table, JsonObject entry){
		{
			PdfPCell c = new PdfPCell();
			c.setBorder(Rectangle.NO_BORDER);
			c.setUseAscender(true);
			c.setUseDescender(true);	        
			
			Paragraph p = new Paragraph(entry.get("label").getAsString(), FONT_NORMAL_REG_SIZE);
	        p.setAlignment(Element.ALIGN_RIGHT);
	        p.setIndentationRight(SHEDULE_TEXT_INDENT);
	        c.addElement(p);	        
	        table.addCell(c);
		}

		{
			PdfPCell c = new PdfPCell();
			c.setBorder(Rectangle.NO_BORDER);
			c.setUseAscender(true);
			c.setUseDescender(true);
	        
			Paragraph p = new Paragraph(entry.get("text").getAsString(), FONT_NORMAL_REG_SIZE);
	        p.setAlignment(Element.ALIGN_LEFT);
	        c.addElement(p);	        
	        table.addCell(c);
		}						
    			
	}
	public void AddPlainText(Document d, JsonElement element) throws DocumentException{
		
		Font headeFont = FONT_BOLDITALIC_INC_SIZE;
		Font textFont  = FONT_NORMAL_REG_SIZE;
		
		JsonElement e = element.getAsJsonObject().get("size");
		if(e != null){
			if(e.getAsString().equals("SMALL") ){
				headeFont = FONT_BOLDITALIC_REG_SIZE;
				textFont  = FONT_NORMAL_SMALL_SIZE;
				
			}
		}
		
		Paragraph p = new Paragraph( element.getAsJsonObject().get("title").getAsString(), headeFont );
		LineSeparator underline = new LineSeparator();
		underline.setOffset(-2);
		p.setSpacingBefore(SPACING_AFTER);
        p.setSpacingAfter(SPACING_AFTER);	        
		p.add(underline);
		d.add(p);
		
		Paragraph pT = new Paragraph( element.getAsJsonObject().get("text").getAsString(), textFont );
		d.add(pT);
	}
	
	
	public void AddLabeledList(Document d, JsonElement element) throws DocumentException{
		Paragraph p = new Paragraph( element.getAsJsonObject().get("title").getAsString(), FONT_BOLDITALIC_INC_SIZE );
		LineSeparator underline = new LineSeparator();
		underline.setOffset(-2);
		p.setSpacingBefore(SPACING_AFTER);
        p.setSpacingAfter(SPACING_AFTER);	        
		p.add(underline);
		d.add(p);

		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(SPACING_AFTER);
        table.setSpacingAfter(SPACING_AFTER);
        table.setWidths(new int[]{2, 5});
                
        for(JsonElement e: element.getAsJsonObject().get("entries").getAsJsonArray() ){
    		JsonObject o = e.getAsJsonObject();		
    		_addLabeledEntry( table, o );    		
        }
        
        d.add(table);
			
	}

	public void AddLabeledListWithImage(Document d, JsonElement element, String resourcePath) throws DocumentException, MalformedURLException, IOException{
		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);        
        table.setSpacingBefore(SPACING_AFTER);
        table.setSpacingAfter(SPACING_AFTER);    
        table.setWidths(new int[]{1, 3});
        {
	        PdfPCell c = new PdfPCell();
			c.setBorder(Rectangle.NO_BORDER);
			c.setVerticalAlignment(Element.ALIGN_MIDDLE);
						
			JsonElement iEl = element.getAsJsonObject().get("image_name");
			if( iEl != null){
				Image image = readImage(resourcePath, iEl.getAsString());			
				image.scaleAbsolute(120f, 100f);
				c.addElement( image );
			}
	        table.addCell(c);
        }
        
        {
	        PdfPCell c = new PdfPCell();
			c.setBorder(Rectangle.NO_BORDER);
	        	        
	        Paragraph p = new Paragraph( 
	        		element.getAsJsonObject().get("title").getAsString(), 
	        		FONT_BOLDITALIC_INC_SIZE );
	        
			LineSeparator underline = new LineSeparator();
			underline.setOffset(-2);
			p.setSpacingBefore(SPACING_AFTER);
	        p.setSpacingAfter(2);	        
			p.add(underline);
			
			c.addElement(p);
	
			PdfPTable innerTable = new PdfPTable(2);
			innerTable.setWidthPercentage(100);
			innerTable.setSpacingBefore(5);
			innerTable.setSpacingAfter(SPACING_AFTER);
			innerTable.setWidths(new int[]{2, 5});
	                
	        for(JsonElement e: element.getAsJsonObject().get("entries").getAsJsonArray() ){
	    		JsonObject o = e.getAsJsonObject();		
	    		_addLabeledEntry( innerTable, o );    		
	        }
	        c.addElement( innerTable );
	        
	        {    
		        Paragraph p2 = new Paragraph( 
		        		element.getAsJsonObject().get("sub_header").getAsString(), 
		        		FONT_BOLDITALIC_INC_SIZE );
				LineSeparator underline2 = new LineSeparator();
				underline2.setOffset(-2);
		        p2.setSpacingAfter(2);	        
				p2.add(underline2);				
				c.addElement(p2);
				
				
				
				Paragraph p3 = new Paragraph();
				JsonElement leadEl = element.getAsJsonObject().get("sub_lead");
				if(leadEl != null){
					p3.add(new Chunk( element.getAsJsonObject().get("sub_lead").getAsString(), FONT_BOLD_REG_SIZE) );
				}
				p3.add(new Chunk( element.getAsJsonObject().get("sub_text").getAsString(), FONT_NORMAL_REG_SIZE) );
		        p3.setSpacingAfter(2);	        
		        c.addElement(p3);
				
		        table.addCell(c);	               		
	        }
	        
	        
	        
	        table.addCell(c);	               		
        }
        d.add(table);
	}

	
	
	public byte[] renderTestPdf(JsonElement element, String resourcePath) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {		
			Document document = new Document();

			PdfWriter writer = PdfWriter.getInstance(document, os);
			writer.setPageEvent( new CvPdfHeader( element.getAsJsonObject() ) );
			document.open();
			
			/*BaseFont bfRegular = BaseFont.createFont(
					"/home/bartekk/Downloads/OpenSans-Regular.ttf", 
					BaseFont.CP1250, 
					BaseFont.EMBEDDED);*/
			
			BaseFont bfRegular = BaseFont.createFont(
					BaseFont.HELVETICA,
					BaseFont.CP1250, 
					BaseFont.EMBEDDED);
			
			Font font 	= new Font(bfRegular, 10);
						
			String text = element.getAsJsonObject().get("main_title").getAsString();
			Paragraph p = new Paragraph(text, font);
			document.add(p);
						
			
			Paragraph p2 = new Paragraph("ĄĆĘŁŃÓŚŹŻąćęłńóśżź", font );
			document.add(p2);
			System.out.println(  text );
			document.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return os.toByteArray();
	}
		
	
	public byte[] renderPdf(JsonElement element, String resourcePath) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			createFonts(element.getAsJsonObject().get("encoding").getAsString());
			
			Document document = new Document();

			PdfWriter writer = PdfWriter.getInstance(document, os);
			writer.setPageEvent( new CvPdfHeader( element.getAsJsonObject() ) );

			
			if(element.getAsJsonObject().get("document_title")!= null){
				document.addTitle( element.getAsJsonObject().get("document_title").getAsString() );
			}
			if(element.getAsJsonObject().get("document_author")!= null){
				document.addAuthor( element.getAsJsonObject().get("document_author").getAsString() );
			}
			
			document.addCreator("CVPdf/iText5");
			
			document.open();			
			_addParagraph(document, element.getAsJsonObject().get("main_title").getAsString(), Element.ALIGN_CENTER, FONT_LEAD);
			
			for(JsonElement e: element.getAsJsonObject().get("components").getAsJsonArray() ){
				if(e == null){ continue; }
				JsonObject o = e.getAsJsonObject();
	    		String component = o.get("component").getAsString();
	    		if(component.equals("PLAIN") ){
	    			AddPlainText(document, e);
	    		}else if(component.equals("LIST") ){
	    			AddLabeledList(document, e);
	    		}else if(component.equals("LIST_WITH_IMAGE") ){ 
	    			AddLabeledListWithImage(document,e, resourcePath);
	    		}else if (component.equals("TABLE_WITH_VERTICAL_DELIMITERS")){
	    			AddTableWithVerticalDelimiters(document,e );
	    		}else{
	    			throw new Exception("Unknown compontnt ("+component+")in json data file");
	    		}
	        }
			document.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return os.toByteArray();
	}
}
