package vo;

public class ItemDTO {
    int itemIdx;
    String itemName;
    int itemPrice;
    int itemCount;

    String orderDate;
    String memberId;

    public String getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    public int getItemIdx() {
        return this.itemIdx;
    }

    public void setItemIdx(int itemIdx) {
        this.itemIdx = itemIdx;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

}
