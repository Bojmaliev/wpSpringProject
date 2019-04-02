package mk.trkalo.dnp.dnpshop.model;

public enum Status {
    CONSTRUCTED, // when user open the page
    CREATED, // when user submited a order
    PACKED, // when is packed
    SHIPPED,  // when is shipped
    INFUTURE, // date for in future
    RETURNED, // if the package is returned
    CANCELED,  // if the order is canceled
    PICKED_UP // if everything go well
}
