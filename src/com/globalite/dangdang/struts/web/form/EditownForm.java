package com.globalite.dangdang.struts.web.form;

/**
 * Created by zhu on 2016/9/5.
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class EditownForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private FormFile file;
    private String name;
    private String province;
    private String image;
    private String uploadMsg;
    private List provinces = new ArrayList();
    private String city;
    private List cities = new ArrayList();
    private String birthday;
    private String sex = "0";
    private String identity = "0";

    public EditownForm() {
    }

    public FormFile getFile() {
        return this.file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List getProvinces() {
        return this.provinces;
    }

    public void setProvinces(List provinces) {
        this.provinces = provinces;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List getCities() {
        return this.cities;
    }

    public void setCities(List cities) {
        this.cities = cities;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUploadMsg() {
        return this.uploadMsg;
    }

    public void setUploadMsg(String uploadMsg) {
        this.uploadMsg = uploadMsg;
    }
}
