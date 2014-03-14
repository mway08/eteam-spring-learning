package com.eteam.frame.persistence;


import com.eteam.frame.domain.LineItem;

import java.util.List;

public interface LineItemMapper {

    List<LineItem> getLineItemsByOrderId(int orderId);

    int insertLineItem(LineItem lineItem);

}
