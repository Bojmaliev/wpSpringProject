package mk.trkalo.dnp.dnpshop.model;

public enum Status {
    CREATED , //when user created the order
    SUBMITTED, // when user submit the order

    PACKED, // when is packed
    SHIPPED,  // when is shipped
    RETURNED, // if the package is returned
    CANCELED,  // if the order is canceled
    DONE // if everything go well
}
