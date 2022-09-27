package project.presentation.branch_offices;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import project.Application;
import project.logic.Branch_Office;
import project.logic.Employee;
import project.logic.Service;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.set_branch_offices(Service.instance().branch_offices_search(""));
        this.view = view;
        this.model = model;
        view.set_controller(this);
        view.set_model(model);
    }

    public <T> void search(T filter) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(filter);
        model.set_branch_offices(rows);
        model.commit();
    }

    public void search_by_code(String code) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(code);
        rows = rows.stream().filter(element->element.get_code().contains(code)).collect(toCollection(ArrayList::new));
        model.set_branch_offices(rows);
        model.commit();
    }

    public void search_by_reference(String reference) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(reference);
        rows = rows.stream().filter(element->element.get_reference().contains(reference)).collect(toCollection(ArrayList::new));
        model.set_branch_offices(rows);
        model.commit();
    }

    public void search_by_zonage_percentage(String zonage_percentage) {
        ArrayList<Branch_Office> rows = Service.instance().branch_offices_search(zonage_percentage);
        rows = rows.stream().filter(element -> Double.toString(element.get_zonage_percentage()).contains(zonage_percentage)).collect(toCollection(ArrayList::new));
        model.set_branch_offices(rows);
        model.commit();
    }

    public void pre_add() {
        Application.branch_office_controller.pre_add();
    }

    public void edit(int row) {
        String code = model.get_branch_offices().get(row).get_code();
        Branch_Office b = null;
        try {
            b = Service.instance().get_branch_office(code);
            Application.branch_office_controller.edit(b);
        } catch (Exception ex) {}
    }

    public void erase(int row) {
        String code = model.get_branch_offices().get(row).get_code();
        Branch_Office b = null;
        try {
            b = Service.instance().get_branch_office(code);
            Service.instance().branch_office_delete(b);
            this.update();
        } catch (Exception ex) {}
    }

    public void add(Branch_Office b) {
        Service.instance().branch_office_add(b);
        this.update();
    }

    public void update() {
        ArrayList<Branch_Office> rows = Service.instance().get_branch_offices();
        model.set_branch_offices(rows);
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.get_panel());
    }

    private Cell get_cell(Paragraph paragraph, TextAlignment alignment, boolean has_border) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        if(!has_border) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    private Cell get_cell(Image image, HorizontalAlignment alignment, boolean has_border) {
        Cell cell = new Cell().add(image);
        image.setHorizontalAlignment(alignment);
        cell.setPadding(0);
        if(!has_border) cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    public void print() throws Exception {
        String dest = "branch_offices.pdf";
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        //Document document = new Document(pdf, PageSize.A4.rotate());
        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);

        Table header = new Table(1);
        header.setWidth(400);
        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        header.addCell(get_cell(new Paragraph("Integrated System SISE").setFont(font).setBold().setFontSize(20f), TextAlignment.CENTER,false));
//        header.addCell(get_cell(new Image(ImageDataFactory.create("logo.jpg")), HorizontalAlignment.CENTER,false));
        document.add(header);

        document.add(new Paragraph(""));document.add(new Paragraph(""));

        Color bkg = ColorConstants.RED;
        Color frg= ColorConstants.WHITE;
        Table body = new Table(3);
        body.setWidth(400);
        body.setHorizontalAlignment(HorizontalAlignment.CENTER);
        body.addCell(get_cell(new Paragraph("Code").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(get_cell(new Paragraph("Reference").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(get_cell(new Paragraph("Zonage Percentage").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        for(Branch_Office b: model.get_branch_offices()){
            body.addCell(get_cell(new Paragraph(b.get_code()),TextAlignment.CENTER,true));
            body.addCell(get_cell(new Paragraph(b.get_reference()),TextAlignment.CENTER,true));
            body.addCell(get_cell(new Paragraph(Double.toString(b.get_zonage_percentage())),TextAlignment.CENTER,true));
        }
        document.add(body);
        document.close();
    }
}
