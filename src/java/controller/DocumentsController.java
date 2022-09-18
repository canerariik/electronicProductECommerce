package controller;

import dao.DocumentsDAO;
import entity.Documents;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;

@Named(value = "documentsController")
@SessionScoped
public class DocumentsController implements Serializable {

    private Documents entity;
    private DocumentsDAO dao;
    private List<Documents> list;

    private Part doc;

    private final String uploadTo = "C:\\Users\\Monster\\Desktop\\upload\\";

    public DocumentsController() {
    }

    public String getFileName(int id) {
        Documents d = this.getDao().findByID(id);
        return d.getFile_name();
    }

    public void upload() {
        try {
            InputStream input = doc.getInputStream();
            File f = new File(uploadTo + doc.getSubmittedFileName());
            Files.copy(input, f.toPath());

            entity = this.getEntity();
            entity.setFile_path(f.getParent());
            entity.setFile_name(f.getName());
            entity.setFile_type(doc.getContentType());

            this.getDao().create(entity);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void update() {
        this.getDao().update(entity);
        entity = new Documents();
    }

    public void delete(Documents f) {
        this.getDao().delete(f);
        entity = new Documents();
    }

    public void clear() {
        entity = new Documents();
    }

    public Documents getEntity() {
        if (entity == null) {
            entity = new Documents();
        }
        return entity;
    }

    public void setEntity(Documents entity) {
        this.entity = entity;
    }

    public DocumentsDAO getDao() {
        if (dao == null) {
            dao = new DocumentsDAO();
        }
        return dao;
    }

    public void setDao(DocumentsDAO dao) {
        this.dao = dao;
    }

    public List<Documents> getList() {
        this.list = this.getDao().getList();
        return list;
    }

    public void setList(List<Documents> list) {
        this.list = list;
    }

    public Part getDoc() {
        return doc;
    }

    public void setDoc(Part doc) {
        this.doc = doc;
    }

    public String getUploadTo() {
        return uploadTo;
    }

}
