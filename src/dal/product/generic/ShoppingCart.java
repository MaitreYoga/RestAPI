package dal.product.generic;


public abstract class ShoppingCart {
    public String productLineList;

    public abstract void load(int userId);
	public abstract int getIdFromOwner(int userId);
	public abstract void save(int userId);
}
