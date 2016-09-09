package com.globalite.dangdang.struts.web.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.PhotoUploader;
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Images;
import com.globalite.dangdang.service.EditOwnService;
import com.globalite.dangdang.struts.web.form.EditownForm;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EditownAction extends DispatchAction {
    private CustomerDao customerDao = null;
    private EditOwnService service = null;

    public EditownAction() {
    }

    public void setService(EditOwnService service) {
        this.service = service;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    private void showCustomerInfo(EditownForm vo, Customer cus) {
        this.showInfo(vo, cus);
        this.showHead(vo, cus);
        this.showProvince(vo, cus);
    }

    private void showInfo(EditownForm vo, Customer cus) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        vo.setBirthday(cus.getBirthday() == null?"":f.format(cus.getBirthday()));
        vo.setName(cus.getName());
        vo.setSex(cus.getSex() == null?"":String.valueOf(cus.getSex()));
        vo.setIdentity(cus.getIdentity() == null?"":String.valueOf(cus.getIdentity()));
    }

    private void showHead(EditownForm vo, Customer cus) {
        String head = null;
        if(cus.getHead() != null) {
            head = cus.getHead().getPath() + cus.getHead().getName();
        }

        vo.setImage(head == null?"img/heads/img_big.bmp":head);
    }

    private void showProvince(EditownForm vo) {
        vo.setProvinces(this.service.getProvinces());
        if(vo.getProvince() != null && !vo.getProvince().equals("000")) {
            vo.setCities(this.service.getCities(vo.getProvince()));
        }
    }

    private void showProvince(EditownForm vo, Customer cus) {
        vo.setProvinces(this.service.getProvinces());
        if(cus.getCity() != null) {
            if(cus.getCity().getType().byteValue() == 1) {
                vo.setProvince(cus.getCity().getId());
                vo.setCities(this.service.getCities(cus.getCity().getId()));
            }

            if(cus.getCity().getType().byteValue() > 1) {
                String province = cus.getCity().getParentid().split("[-]")[0];
                vo.setProvince(province);
                vo.setCities(this.service.getCities(province));
                vo.setCity(cus.getCity().getId());
            }

        }
    }

    public ActionForward load(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        EditownForm vo = (EditownForm)form;
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        this.showCustomerInfo(vo, cus);
        return mapping.findForward("default");
    }

    public ActionForward select(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        EditownForm vo = (EditownForm)form;
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        this.showHead(vo, cus);
        this.showProvince(vo);
        return mapping.findForward("default");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        EditownForm vo = (EditownForm)form;
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        String name = "img/heads/img_big.bmp";
        Images image = null;
        if(vo.getFile() != null && vo.getFile().getFileName() != null && vo.getFile().getFileSize() > 0) {
            try {
                name = this.service.upload(vo, super.getServlet().getServletContext().getRealPath("img/heads/"));
                image = this.createImages(name, vo);
                this.service.saveImage(image);
            } catch (IOException var10) {
                vo.setUploadMsg(var10.getMessage());
                this.showHead(vo, cus);
                this.showProvince(vo);
                return mapping.findForward("default");
            }
        }

        this.service.updateCustomer(cus.getId(), vo, image);
        this.refreshCustomer(request);
        return mapping.findForward("myself");
    }

    private void refreshCustomer(HttpServletRequest request) {
        Customer cus = (Customer)request.getSession().getAttribute("com.globalite.dangdang.constants.customername");
        request.getSession().setAttribute("com.globalite.dangdang.constants.customername", this.customerDao.findCustomer(cus.getRegname(), cus.getPassword()));
    }

    private Images createImages(String name, EditownForm vo) {
        Images images = new Images(PhotoUploader.DEFAULT_IMG_INDEX);
        if(name == null) {
            return images;
        } else {
            images = new Images();
            images.setName(name);
            images.setPath("img/heads/");
            images.setSize(Long.valueOf((long)vo.getFile().getFileSize()));
            images.setType(Byte.valueOf((byte)4));
            return images;
        }
    }
}
