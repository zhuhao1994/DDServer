package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.PhotoUploader;
import com.globalite.dangdang.dao.CityDao;
import com.globalite.dangdang.dao.CustomerDao;
import com.globalite.dangdang.dao.ImagesDao;
import com.globalite.dangdang.entity.City;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Images;
import com.globalite.dangdang.service.EditOwnService;
import com.globalite.dangdang.struts.web.form.EditownForm;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class EditOwnServiceImpl implements EditOwnService {
    private CityDao cityDao = null;
    private CustomerDao customerDao = null;
    private ImagesDao imagesDao = null;

    public EditOwnServiceImpl() {
    }

    public CityDao getCityDao() {
        return this.cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    public CustomerDao getCustomerDao() {
        return this.customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public ImagesDao getImagesDao() {
        return this.imagesDao;
    }

    public void setImagesDao(ImagesDao imagesDao) {
        this.imagesDao = imagesDao;
    }

    public List<?> getCities(String provinceId) {
        List cities = null;
        cities = this.cityDao.findDistrictsByProvince(provinceId + "-001");
        return cities;
    }

    public List<?> getProvinces() {
        List provinces = this.cityDao.findAllProvinces();
        return provinces;
    }

    public String upload(EditownForm vo, String path) throws IOException {
        if(vo.getFile() != null && vo.getFile().getFileName() != null) {
            if(!PhotoUploader.checkPhotoSize(vo.getFile().getFileSize())) {
                throw new IOException("上传图片尺寸请控制在300k以下");
            } else if(!PhotoUploader.checkPhotoType(vo.getFile().getFileName())) {
                throw new IOException("本站只支持jpg/png/gif的图片格式");
            } else {
                PhotoUploader loader = new PhotoUploader(path, vo.getFile());

                try {
                    return loader.upload(vo.getFile().getFileName());
                } catch (Exception var5) {
                    throw new IOException("图片上传错误");
                }
            }
        } else {
            return null;
        }
    }

    public void updateCustomer(Long cusId, EditownForm vo, Images images) {
        Customer cus = new Customer();
        cus.setId(cusId);
        cus.setBirthday(vo.getBirthday() != null && !"".equals(vo.getBirthday())?Date.valueOf(vo.getBirthday()):null);
        cus.setCity(this.getCity(vo.getCity(), vo.getProvince()));
        cus.setHead(images);
        cus.setSex(Byte.valueOf(vo.getSex()));
        cus.setIdentity(Byte.valueOf(vo.getIdentity()));
        cus.setName(vo.getName());
        this.customerDao.updateCustomer(cus);
    }

    public void saveImage(Images image) {
        this.imagesDao.saveImages(image);
    }

    private City getCity(String city, String province) {
        if((city == null || city.equals("000")) && (province == null || province.equals("000"))) {
            return null;
        } else {
            String id = city != null && !city.equals("000")?city:province;
            return new City(id);
        }
    }
}
