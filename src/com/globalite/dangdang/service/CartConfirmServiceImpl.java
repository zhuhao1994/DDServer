package com.globalite.dangdang.service;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.CartItem;
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.dao.AddressDao;
import com.globalite.dangdang.dao.OrderitemsDao;
import com.globalite.dangdang.dao.OrdersDao;
import com.globalite.dangdang.entity.Consignmentaddress;
import com.globalite.dangdang.entity.Customer;
import com.globalite.dangdang.entity.Orderitems;
import com.globalite.dangdang.entity.Orders;
import com.globalite.dangdang.service.CartConfirmService;
import com.globalite.dangdang.struts.web.form.CartConfirmForm;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CartConfirmServiceImpl implements CartConfirmService {
    private OrderitemsDao orderitemsDao = null;
    private OrdersDao ordersDao = null;
    private AddressDao addressDao = null;

    public CartConfirmServiceImpl() {
    }

    public AddressDao getAddressDao() {
        return this.addressDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    public OrderitemsDao getOrderitemsDao() {
        return this.orderitemsDao;
    }

    public void setOrderitemsDao(OrderitemsDao orderitemsDao) {
        this.orderitemsDao = orderitemsDao;
    }

    public OrdersDao getOrdersDao() {
        return this.ordersDao;
    }

    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    public void doConfirmOrder(ShoppingCart cart, CartConfirmForm vo, Customer customer) {
        Consignmentaddress address = this.getAddress(vo);
        Orders orders = this.getOrders(vo, cart, address, customer);
        List items = this.getItems(cart, orders);
        this.ordersDao.saveOrder(orders);
        this.orderitemsDao.saveItems(items);
    }

    private Consignmentaddress getAddress(CartConfirmForm vo) {
        Consignmentaddress address = this.addressDao.findAddressById(Long.valueOf(vo.getAddress()));
        return address;
    }

    private String getOrderId() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMM");
        String orderPrefix = formater.format(new Date());
        Orders orders = this.ordersDao.findOrderByOrderId(orderPrefix);
        if(orders == null) {
            return orderPrefix + this.format("1");
        } else {
            Integer seq = Integer.valueOf(orders.getOrdid().substring(6));
            return orderPrefix + this.format(String.valueOf(Integer.valueOf(seq.intValue() + 1)));
        }
    }

    private String format(String value) {
        String str = "000" + value;
        return str.substring(str.length() - 4);
    }

    private Orders getOrders(CartConfirmForm vo, ShoppingCart cart, Consignmentaddress address, Customer customer) {
        Orders orders = new Orders();
        orders.setOrdid(this.getOrderId());
        orders.setConsignmentaddress(address);
        orders.setCustomer(customer);
        orders.setReceiver(address.getCustomer().getName());
        orders.setPostcode(address.getPostcode());
        orders.setPhone(address.getPhone());
        orders.setMobile(address.getMobile());
        orders.setPaytype(Byte.valueOf(vo.getPayType()));
        orders.setInvoicetype(Byte.valueOf(vo.getInvoiceType()));
        if(Byte.valueOf(vo.getInvoiceType()).byteValue() != 0) {
            orders.setInvoicecontent(Byte.valueOf(vo.getInvoiceContent()));
        } else {
            orders.setInvoicecontent((Byte)null);
        }

        orders.setDeliverprice(BigDecimal.valueOf(Double.valueOf(vo.getDeliverPrice()).doubleValue()));
        orders.setCreatedate(new Date());
        orders.setOrdertype(Byte.valueOf((byte)0));
        orders.setDeleteflag(Byte.valueOf((byte)0));
        orders.setPrice(cart.getTotalPrice());
        return orders;
    }

    private List<Orderitems> getItems(ShoppingCart cart, Orders orders) {
        List cartItems = cart.getItems();
        ArrayList items = new ArrayList();
        Iterator var6 = cartItems.iterator();

        while(var6.hasNext()) {
            CartItem cartItem = (CartItem)var6.next();
            Orderitems item = new Orderitems();
            item.setBook(cartItem.getBook().getOwn());
            item.setQuantity(cartItem.getQuantity());
            item.setSubtotal(cartItem.getSubtotal());
            item.setOrders(orders);
            items.add(item);
        }

        return items;
    }
}
