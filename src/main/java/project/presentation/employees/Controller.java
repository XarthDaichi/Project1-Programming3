package project.presentation.employees;

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
import project.logic.Employee;
import project.logic.Service;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Controller {
    View view;

    Model model;

    public Controller(View view, Model model) {
        try {
            model.set_employees(Service.instance().employees_search(new Employee()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void search(Employee filter) {
        ArrayList<Employee> rows = null;
        try {
            rows = Service.instance().employees_search(filter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_id(Employee filter) {
        ArrayList<Employee> rows = null;
        try {
            rows = Service.instance().employees_search_id(filter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_id(String id) {
        Employee e = new Employee();
        e.set_id(id);
        ArrayList<Employee> rows = null;
        try {
            rows = Service.instance().employees_search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        rows = rows.stream().filter(element->element.get_id().contains(id)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_name(String name) {
        Employee e = new Employee();
        e.set_name(name);
        ArrayList<Employee> rows = null;
        try {
            rows = Service.instance().employees_search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        rows = rows.stream().filter(element->element.get_name().contains(name)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_phone(String phone) {
        Employee e = new Employee();
        e.set_phone(phone);
        ArrayList<Employee> rows = null;
        try {
            rows = Service.instance().employees_search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        rows = rows.stream().filter(element->element.get_phone().contains(phone)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void search_by_salary(String salary) {
        Employee e = new Employee();
        e.set_base_salary(Double.parseDouble(salary));
        ArrayList<Employee> rows = null;
        try {
            rows = Service.instance().employees_search(e);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        rows = rows.stream().filter(element -> Double.toString(element.get_base_salary()).contains(salary)).collect(toCollection(ArrayList::new));
        model.set_employees(rows);
        model.commit();
    }

    public void pre_add() {
        Application.employee_controller.pre_add();
    }

    public void edit(int row) {
        String id = model.get_employees().get(row).get_id();
        Employee e = null;
        try {
            e = Service.instance().get_employee(id);
            Application.employee_controller.edit(e);
        } catch (Exception ex) {}
    }

    public void erase(int row) {
        String id = model.get_employees().get(row).get_id();
        Employee e = null;
        try {
            e = Service.instance().get_employee(id);
            Service.instance().employee_delete(e);
            this.update();
        } catch (Exception ex) {}
    }

    public void add(Employee e) throws Exception {
        Service.instance().employee_add(e);
        this.update();
    }

    public void update() {
        ArrayList<Employee> rows = Service.instance().get_employees();
        model.set_employees(rows);
        model.commit();
    }

    public void show() {
        Application.window.setContentPane(view.getPanel());
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
        String dest = "employees.pdf";
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
        Table body = new Table(4);
        body.setWidth(400);
        body.setHorizontalAlignment(HorizontalAlignment.CENTER);
        body.addCell(get_cell(new Paragraph("Id").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(get_cell(new Paragraph("Name").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(get_cell(new Paragraph("Phone").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        body.addCell(get_cell(new Paragraph("Salary").setBackgroundColor(bkg).setFontColor(frg),TextAlignment.CENTER,true));
        for(Employee e: model.get_employees()){
            body.addCell(get_cell(new Paragraph(e.get_id()),TextAlignment.CENTER,true));
            body.addCell(get_cell(new Paragraph(e.get_name()),TextAlignment.CENTER,true));
            body.addCell(get_cell(new Paragraph(e.get_phone()),TextAlignment.CENTER,true));
            body.addCell(get_cell(new Paragraph(Double.toString(e.get_base_salary())),TextAlignment.CENTER,true));
        }
        document.add(body);
        document.close();
    }
}
