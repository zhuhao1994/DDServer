package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.City;
import java.util.List;

public interface CityDao {
    List<?> findCountries();

    List<?> findProvinces(String var1);

    List<?> findAllProvinces();

    List<?> findCities(String var1);

    List<?> findDistricts(String var1);

    List<?> findDistrictsByProvince(String var1);

    City findById(String var1);
}