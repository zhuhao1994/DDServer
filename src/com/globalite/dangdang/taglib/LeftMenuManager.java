package com.globalite.dangdang.taglib;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.taglib.entity.Item;
import com.globalite.dangdang.taglib.entity.Mapping;
import com.globalite.dangdang.taglib.entity.Menu;
import com.globalite.dangdang.taglib.entity.Root;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LeftMenuManager {
    private static LeftMenuManager instance = null;
    private Document doc = null;

    private LeftMenuManager(InputStream stream, InputSource dtd) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder e = factory.newDocumentBuilder();
            e.setEntityResolver(new LeftMenuManager.MenuEntityResolver(dtd));
            this.doc = e.parse(stream);
        } catch (ParserConfigurationException var5) {
            this.doc = null;
        } catch (SAXException var6) {
            this.doc = null;
        } catch (IOException var7) {
            var7.printStackTrace();
            this.doc = null;
        }

    }

    public static LeftMenuManager getInstance(InputStream stream, InputSource dtd) {
        if(instance == null) {
            instance = new LeftMenuManager(stream, dtd);
        }

        return instance;
    }

    public Menu getMenu() {
        if(this.doc == null) {
            return null;
        } else {
            Menu menu = new Menu();
            menu.setMappings(this.getMappings());
            menu.setRoots(this.getRoots());
            return menu;
        }
    }

    private List<Root> getRoots() {
        ArrayList roots = new ArrayList();
        NodeList nlRoots = this.doc.getElementsByTagName("root");

        for(int i = 0; i < nlRoots.getLength(); ++i) {
            if(nlRoots.item(i) instanceof Element) {
                roots.add(this.getRoot((Element)nlRoots.item(i)));
            }
        }

        return roots;
    }

    private Root getRoot(Element elRoot) {
        Root root = new Root();
        root.setId(elRoot.getAttribute("id"));
        root.setName(elRoot.getAttribute("name"));
        root.setItems(this.getItems(elRoot));
        return root;
    }

    private List<Item> getItems(Element elRoot) {
        ArrayList items = new ArrayList();
        NodeList nlItems = elRoot.getElementsByTagName("item");

        for(int i = 0; i < nlItems.getLength(); ++i) {
            if(nlItems.item(i) instanceof Element) {
                items.add(this.getItem((Element)nlItems.item(i)));
            }
        }

        return items;
    }

    private Item getItem(Element elItem) {
        Item item = new Item();
        item.setId(elItem.getAttribute("id"));
        item.setUrl(elItem.getAttribute("url"));
        item.setContent(elItem.getTextContent());
        return item;
    }

    private List<Mapping> getMappings() {
        ArrayList mapping = new ArrayList();
        NodeList nlMappings = this.doc.getElementsByTagName("mapping");

        for(int i = 0; i < nlMappings.getLength(); ++i) {
            if(nlMappings.item(i) instanceof Element) {
                mapping.add(this.getMapping((Element)nlMappings.item(i)));
            }
        }

        return mapping;
    }

    private Mapping getMapping(Element elMapping) {
        Mapping mapping = new Mapping();
        mapping.setPage(elMapping.getElementsByTagName("page").item(0).getTextContent());
        mapping.setSelected(elMapping.getElementsByTagName("selected").item(0).getTextContent());
        mapping.setClazz(elMapping.getElementsByTagName("clazz").item(0).getTextContent());
        return mapping;
    }

    class MenuEntityResolver implements EntityResolver {
        private InputSource dtd = null;

        public MenuEntityResolver(InputSource dtd) {
            this.dtd = dtd;
        }

        public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
            return systemId.indexOf("leftmenu.dtd") >= 0?this.dtd:null;
        }
    }
}
