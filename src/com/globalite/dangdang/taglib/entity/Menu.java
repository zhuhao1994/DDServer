package com.globalite.dangdang.taglib.entity;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.taglib.entity.Mapping;
import com.globalite.dangdang.taglib.entity.Root;
import java.util.List;

public class Menu {
    private List<Root> roots = null;
    private List<Mapping> mappings = null;

    public Menu() {
    }

    public List<Root> getRoots() {
        return this.roots;
    }

    public void setRoots(List<Root> roots) {
        this.roots = roots;
    }

    public List<Mapping> getMappings() {
        return this.mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }
}