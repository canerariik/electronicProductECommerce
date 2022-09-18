package converter;

import dao.DocumentsDAO;
import entity.Documents;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("documentsConverter")
public class documentsConverter implements Converter {

    private DocumentsDAO filesDAO;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        int id = Integer.valueOf(string);
        Documents f = this.getFilesDAO().findByID(id);
        return f;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        Documents f = (Documents)t;
        return String.valueOf(f.getId());
    }

    public DocumentsDAO getFilesDAO() {
        if (filesDAO == null) {
            this.filesDAO = new DocumentsDAO();
        }
        return filesDAO;
    }

    public void setFilesDAO(DocumentsDAO filesDAO) {
        this.filesDAO = filesDAO;
    }

}
