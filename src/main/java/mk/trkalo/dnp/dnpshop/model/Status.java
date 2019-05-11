package mk.trkalo.dnp.dnpshop.model;

public enum Status {
    SUBMITED, // when user submit the order
    SHOULD_BE_SHIPPED, // the date should be shipped

    PACKED, // when is packed
    SHIPPED,  // when is shipped
    RETURNED, // if the package is returned
    CANCELED,  // if the order is canceled
    DONE // if everything go well
}
