package domain;

public class Ticket {

    private int ticketId;
    private String title;
    private String description;
    private String status;
    private String priority;
    private int reporterId;
    private Integer assigneeId;
    private int categoryId;

    // Constructor completo
    public Ticket(int ticketId, String title, String description, String status, String priority, int reporterId, Integer assigneeId, int categoryId) {
        setTicketId(ticketId);
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setPriority(priority);
        setReporterId(reporterId);
        setAssigneeId(assigneeId);
        setCategoryId(categoryId);
    }

    public Ticket(String title, String description, String status, String priority, int reporterId, Integer assigneeId, int categoryId) {
        setTitle(title);
        setDescription(description);
        setStatus(status);
        setPriority(priority);
        setReporterId(reporterId);
        setAssigneeId(assigneeId);
        setCategoryId(categoryId);
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public Integer getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Integer assigneeId) {
        this.assigneeId = assigneeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nStatus: " + status +
                "\nPriority: " + priority +
                "\nReporter ID: " + reporterId +
                "\nAssignee ID: " + (assigneeId != null ? assigneeId : "Unassigned") +
                "\nCategory ID: " + categoryId;
    }
}
