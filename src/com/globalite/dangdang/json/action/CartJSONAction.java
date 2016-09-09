package com.globalite.dangdang.json.action;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.bo.ShoppingCart;
import com.globalite.dangdang.common.SpringManager;
import com.globalite.dangdang.dao.BookDao;
import com.globalite.dangdang.entity.Book;
import com.globalite.dangdang.framework.WebJSONAction;
import com.globalite.dangdang.json.builder.Builder;
import com.globalite.dangdang.json.builder.CartParamsBuilder;
import com.globalite.dangdang.service.CartService;
import com.globalite.dangdang.service.CartServiceAdapter;
import com.oracle.dd.tool.json.annotation.JSONRequestHandler;
import com.oracle.dd.tool.json.annotation.JSONResponseHandler;
import com.oracle.dd.tool.json.annotation.JSONUrl;
import com.oracle.dd.tool.json.exception.JSONFormatException;
import com.oracle.dd.tool.json.exception.JSONHanldException;
import com.oracle.dd.tool.json.framework.JSONRequest;
import com.oracle.dd.tool.json.framework.JSONResponse;
import com.oracle.dd.tool.json.request.entity.CartItemRequestParams;
import com.oracle.dd.tool.json.request.entity.CartRequestParams;
import com.oracle.dd.tool.json.response.entity.CartResponseParams;
import java.util.Iterator;
import java.util.List;

@JSONUrl(
        url = "/cart.json"
)
@JSONRequestHandler(
        requestClass = "com.oracle.dd.tool.json.request.CartJSONRequest"
)
@JSONResponseHandler(
        responseClass = "com.oracle.dd.tool.json.response.CartJSONResponse"
)
public class CartJSONAction extends WebJSONAction {
    private Builder builder = null;

    public CartJSONAction() {
        this.builder = new CartParamsBuilder();
    }

    public String doBusiness(JSONRequest<Object> jsonRequest, JSONResponse<Object> jsonResponse, String jsonRequestParams) throws JSONHanldException {
        try {
            CartRequestParams e = (CartRequestParams)jsonRequest.execute(jsonRequestParams);
            CartResponseParams respParams = null;
            if(e == null) {
                throw new JSONHanldException("没有获取JSON请求数据");
            } else {
                CartServiceAdapter service = new CartServiceAdapter(super.getServletRequest(), e.getRegname());
                if(!this.checkCart(e).booleanValue()) {
                    throw new JSONHanldException("无效的JSON请求数据格式错误");
                } else if(e.getOp() == null) {
                    ShoppingCart e1 = service.getCart();
                    respParams = (CartResponseParams)this.builder.build(e1, 0);
                    return jsonResponse.execute(respParams);
                } else {
                    try {
                        this.performShoppingCart(e, service);
                        respParams = (CartResponseParams)this.builder.build(Boolean.valueOf(true), 1);
                        return jsonResponse.execute(respParams);
                    } catch (Exception var8) {
                        respParams = (CartResponseParams)this.builder.build(Boolean.valueOf(false), 1);
                        return jsonResponse.execute(respParams);
                    }
                }
            }
        } catch (JSONFormatException var9) {
            throw new JSONHanldException("获取JSON请求数据格式错误");
        }
    }

    private void performShoppingCart(CartRequestParams requestParams, CartService service) {
        BookDao dao = (BookDao)SpringManager.getManager().getBean("bookDao");
        if("add".equals(requestParams.getOp())) {
            Book items1 = dao.findFullBook(Long.valueOf(requestParams.getBookid()), true);
            service.addBook(items1);
        } else {
            List items = requestParams.getBooks();
            if(requestParams.getBooks().size() == 0) {
                throw new IllegalArgumentException("没有获取任何可操作的书籍对象");
            } else {
                Iterator var6 = items.iterator();

                while(var6.hasNext()) {
                    CartItemRequestParams item = (CartItemRequestParams)var6.next();
                    Book book = new Book(Long.valueOf(item.getBookid()));
                    if("del".equals(requestParams.getOp())) {
                        service.removeBook(book);
                    }

                    if("edit".equals(requestParams.getOp())) {
                        service.editBook(book, item.getQuantity());
                    }
                }

            }
        }
    }

    public Boolean checkCart(CartRequestParams requestParams) {
        boolean ret = true;
        if(requestParams.getRegname() == null) {
            return Boolean.valueOf(false);
        } else {
            if("add".equals(requestParams.getOp())) {
                if(requestParams.getBookid() == null) {
                    return Boolean.valueOf(false);
                }

                try {
                    Long.valueOf(requestParams.getBookid());
                } catch (NumberFormatException var7) {
                    return Boolean.valueOf(false);
                }
            }

            if("del".equals(requestParams.getOp()) || "edit".equals(requestParams.getOp())) {
                if(requestParams.getBooks() == null || requestParams.getBooks().size() == 0) {
                    return Boolean.valueOf(false);
                }

                for(int i = 0; i < requestParams.getBooks().size(); ++i) {
                    if(((CartItemRequestParams)requestParams.getBooks().get(i)).getBookid() == null) {
                        return Boolean.valueOf(false);
                    }

                    try {
                        Long.valueOf(((CartItemRequestParams)requestParams.getBooks().get(i)).getBookid());
                    } catch (NumberFormatException var5) {
                        return Boolean.valueOf(false);
                    }

                    if("edit".equals(requestParams.getOp())) {
                        if(((CartItemRequestParams)requestParams.getBooks().get(i)).getQuantity() == null) {
                            return Boolean.valueOf(false);
                        }

                        try {
                            Long.valueOf(((CartItemRequestParams)requestParams.getBooks().get(i)).getQuantity());
                        } catch (NumberFormatException var6) {
                            return Boolean.valueOf(false);
                        }
                    }
                }
            }

            return Boolean.valueOf(ret);
        }
    }
}
