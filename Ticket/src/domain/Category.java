package domain;

public class Category {

    private int categoryId;
    private String categoryName;
    private int count;

    public Category(int categoryId, String categoryName, int count) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.count = count;
    }

    public int getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public int getCount() { return count; }

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setCount(int count) { this.count = count; }
}

