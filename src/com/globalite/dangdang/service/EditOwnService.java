package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Images;
import com.globalite.dangdang.struts.web.form.EditownForm;
import java.io.IOException;
import java.util.List;

public interface EditOwnService {
    List<?> getProvinces();

    List<?> getCities(String var1);

    String upload(EditownForm var1, String var2) throws IOException;

    void updateCustomer(Long var1, EditownForm var2, Images var3);

    void saveImage(Images var1);
}
